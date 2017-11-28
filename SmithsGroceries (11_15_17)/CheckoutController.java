import javafx.geometry.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.event.*;
import java.util.*;

public class CheckoutController extends Controller {
 
   private List<Product> productList = new ArrayList<Product>();
   private int scrollRow = 0; 
   private double total = 0.00;
 
// CLICK EVENTS
 
    // Cancel Button
   public EventHandler<ActionEvent> cancelClick(){
      return GUI.changeScreen(GUI.loginNum);
   }
 
    // Settings Button
   public EventHandler<ActionEvent> settingsClick(){
      return GUI.changeScreen(GUI.settingsNum);
   }
 
    // Clear Button
   public EventHandler<ActionEvent> clearClick(){
      return 
         new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
               clearScroll();
            }
         };
   }
   
    // Add Button
   public EventHandler<ActionEvent> addClick(){
      return 
         new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
               int id;
               Alert.AlertType alert = Alert.AlertType.ERROR;
               String title = "Form Error!";
               String message = "";
               if (screen().getID().getText().isEmpty()) {
                  message = "Please enter a product ID";
                  GUI.showAlert(alert, screen.getPane().getScene().getWindow(), title, message);
                  return;
               }
               else {
                  try { 
                     id = Integer.parseInt(screen().getID().getText());
                     if (id < 0) {
                        throw new NumberFormatException();
                     }
                  }
                  catch (NumberFormatException e) {
                     message = "Product ID must be a positive integer";
                     GUI.showAlert(alert, screen.getPane().getScene().getWindow(), title, message);
                     return;
                  }
               }
               Product product = Store.dataAdapter.loadProduct(id);
               if (product == null) {
                  message = "Invalid product ID";
                  GUI.showAlert(alert, screen.getPane().getScene().getWindow(), title, message);
                  return;
               }
               Double available = product.getQuantity();
               int i = 0;
               while (i < productList.size()) {
                  if (productList.get(i).getID() == product.getID()) {
                     available -= productList.get(i).getQuantity();
                  }
                  i++;
               }
               String stock = product.getUnit().equals("Single") ? Integer.toString(available.intValue()) : available + ((product.getUnit().equals("Ounce")) ? " oz" : " lb");
               if (message.equals("")) {
                  if (product.getUnit().equals("Single")) {
                     product.setQuantity(1);
                     total += product.getPrice()*(1 + product.getTax()/100);
                  }
                  else {
                     product.setQuantity(weightDialog(product.getName(), product.getUnit()));
                     total += product.getPrice()*(1 + product.getTax()/100)*product.getQuantity();
                  }
                  if (available - product.getQuantity() < 0) {
                     total -= (product.getUnit().equals("Single")) ? product.getPrice()*(1 + product.getTax()/100): product.getPrice()*(1 + product.getTax()/100)*product.getQuantity();
                     GUI.showAlert(alert, screen.getPane().getScene().getWindow(), "Stock Error!", "Current " + product.getName() + " Stock: " + stock);
                     return;
                  }
                  updateScroll(product);
                  screen().getTotal().setText("$ " + GUI.formatter.format(total));
                  return;
               }
            }
         };
   }
 
    // Look Up Button
   public EventHandler<ActionEvent> lookUpClick(){
      return GUI.changeScreen(GUI.lookUpNum);
   }
 
    // Pay Button
   public EventHandler<ActionEvent> payClick(){
      return 
         new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
               if (total == 0) {
                  GUI.showAlert(alert, screen.getPane().getScene().getWindow(), "Checkout Error", "Your cart is empty");
                  return;
               }
               Store.getCashController().updateTotalLabel();
               GUI.setPane(6);
            }
         };
   }
 
// METHODS
 
    // Cast and return controller's screen
   protected CheckoutScreen screen() {
      return (CheckoutScreen)screen;
   }
 
    // Add product to scroll
   private void updateScroll(Product product) {
      addProduct(product);
      String unit = product.getUnit();
      String unitLabel = (unit.equals("Single")) ? "" : ((unit.equals("Ounce")) ? " ( " + product.getQuantity() + " oz )" : " ( " + product.getQuantity() + " lb )");
      Label name = new Label(product.getName() + unitLabel);
      GridPane.setMargin(name, new Insets(0, 20, 0, 0));
      screen().getScroll().add(name, 0, scrollRow);
      
      Label price = new Label(GUI.formatter.format(product.getPrice()*product.getQuantity()));
      GridPane.setHalignment(price, HPos.RIGHT);
      GridPane.setMargin(price, new Insets(0, 20, 0, 0));
      screen().getScroll().add(price, 1, scrollRow);
      
      scrollRow++;
   }
   
    // Empty the scroll
   public void clearScroll() {
      productList.clear();
      screen().getScroll().getChildren().clear();
      total = 0;
      screen().getTotal().setText("$ 0.00");
      screen().getID().setText("");
   }
   
    // Get formatted total
   public double getTotal() {
      return Double.valueOf(GUI.formatter2.format(total));
   }

    // Get Product List
   public List<Product> getProducts() {
      return productList;
   }

    // Add product to list
   private void addProduct(Product product) {
      int i = 0;
      while (i < productList.size()) {
         if (productList.get(i).getID() == product.getID()) {
            productList.get(i).addQuantity(product.getQuantity());
            return;
         }
         i++;
      }
      productList.add(product);
   }

    // Initiate weight pop-up and return inputted value
   private double weightDialog(String name, String unit) {
      TextInputDialog dialog = new TextInputDialog("");
      dialog.setTitle("Weight of Product");
      dialog.setHeaderText(name);
      dialog.setContentText("Weight (" + unit + "s) : ");
   
      Optional<String> result = dialog.showAndWait();
      try {
         String str = result.toString();
         String answer = str.substring(9,str.length()-1);
         double weight = Double.parseDouble(answer);
         if (weight > 0) {
            return weight;
         }
         else {
            throw new NumberFormatException();
         }
      }
      catch (NumberFormatException e) {
         return weightDialog(name, unit);
      }
   }
}