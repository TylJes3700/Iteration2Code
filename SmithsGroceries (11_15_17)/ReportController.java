import javafx.geometry.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.event.*;
import javafx.scene.input.MouseEvent;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import java.util.*;

public class ReportController extends Controller {
 
   private List<Product> reportList = Store.dataAdapter.updateReportList(GUI.getDate());
   private int scrollRow = 0;
   private double total;
   private boolean id = true, name = true, price = true, quantity = true, profit = true;
 
// CLICK EVENTS
 
    // Cancel Button
   public EventHandler<ActionEvent> cancelClick(){
      return GUI.changeScreen(GUI.managerNum);
   }
   
    // ID Title
   public EventHandler<MouseEvent> idClick(){
      return columnClick("ID");
   }
 
    // Name Title
   public EventHandler<MouseEvent> nameClick(){
      return columnClick("Name");
   }
 
 
    // Price Title
   public EventHandler<MouseEvent> priceClick(){
      return columnClick("Price");
   }
 
 
    // Quantity Title
   public EventHandler<MouseEvent> quantityClick(){
      return columnClick("Quantity");
   }
 
 
    // Profit Title
   public EventHandler<MouseEvent> profitClick(){
      return columnClick("Profit");
   }
 
    // Column Title
   public EventHandler<MouseEvent> columnClick(String column){
      return 
         new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
               if (column.equals("ID")) {
                  if (id) {
                     reportList.sort(Comparator.comparingInt(Product::getID));
                  }
                  else {
                     reportList.sort(Comparator.comparingInt(Product::getID).reversed());
                  }
                  id ^= true;
               }
               else if (column.equals("Name")) {
                  if (name) {
                     reportList.sort(Comparator.comparing(Product::getName));
                  }
                  else {
                     reportList.sort(Comparator.comparing(Product::getName).reversed());
                  }
                  name ^= true;
               }
               else if (column.equals("Price")) {
                  if (price) {
                     reportList.sort(Comparator.comparingDouble(Product::getPrice));
                  }
                  else {
                     reportList.sort(Comparator.comparingDouble(Product::getPrice).reversed());
                  }
                  price ^= true;
               }
               else if (column.equals("Quantity")) {
                  if (quantity) {
                     reportList.sort(Comparator.comparingDouble(Product::getQuantity));
                  }
                  else {
                     reportList.sort(Comparator.comparingDouble(Product::getQuantity).reversed());
                  }
                  quantity ^= true;
               }
               else if (column.equals("Profit")) {
                  if (profit) {
                     reportList.sort(Comparator.comparingDouble(Product::getTax));
                  }
                  else {
                     reportList.sort(Comparator.comparingDouble(Product::getTax).reversed());
                  }
                  profit ^= true;
               }
               updateScroll();
            }
         };
   }

    // Date Choice Boxes
   public ChangeListener<String> dateSelect() {
      return 
         new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
               if (!screen().getDate().contains("M") && !screen().getDate().contains("Y") && !screen().getDate().contains("D")) {
                  reportList = Store.dataAdapter.updateReportList(screen().getDate());
                  updateScroll();
               }
            }
         };
   }
 
// METHODS
 
    // Cast and return controller's screen
   protected ReportScreen screen() {
      return (ReportScreen)screen;
   }
 
    // Add info to scroll
   public void updateScroll() {
      clearScroll();
      Product product;
      if (reportList.size() == 0) { 
         addLabels(new Product()); 
      }
      while (scrollRow < reportList.size()) {
         product = reportList.get(scrollRow);
         
         addLabels(product);
      
         scrollRow++;
         total += product.getTax();
      }
      screen().getTotal().setText(total == 0 ? "----------" : "$ " + GUI.formatter.format(total));
   }
   
   private void addLabels(Product product) {
   
      // ID Column
      Label idLabel = new Label(product.getID() == -1 ? "-----" : Integer.toString(product.getID()));
      //GridPane.setMargin(idLabel, new Insets(0, 20, 0, 0));
      idLabel.setPrefWidth(52);
      GridPane.setHalignment(idLabel, HPos.CENTER);
      screen().getScroll().add(idLabel, 0, scrollRow);
         
      // Name Column
      Label nameLabel = new Label(product.getName());
      //GridPane.setMargin(nameLabel, new Insets(0, 20, 0, 0));
      nameLabel.setPrefWidth(276);
      GridPane.setHalignment(nameLabel, HPos.RIGHT);
      screen().getScroll().add(nameLabel, 1, scrollRow);
         
      // Price Column
      Label priceLabel = new Label(product.getPrice() == 0 ? "---------------" : GUI.formatter.format(product.getPrice()));
      priceLabel.setPrefWidth(112);
      GridPane.setHalignment(priceLabel, HPos.RIGHT);
      screen().getScroll().add(priceLabel, 2, scrollRow);
         
      // Qty Sold Column
      String unit = product.getUnit();
      Label qtyLabel = new Label((unit.equals("Single")) ? Integer.toString((int)product.getQuantity()) : ((unit.equals("Ounce")) ? Double.toString(product.getQuantity()) + " oz" : Double.toString(product.getQuantity()) + " lb"));
      qtyLabel.setText(qtyLabel.getText().substring(0,1).equals("0") ? "---------------" : qtyLabel.getText());
      GridPane.setHalignment(qtyLabel, HPos.RIGHT);
      qtyLabel.setPrefWidth(112);
      screen().getScroll().add(qtyLabel, 3, scrollRow);
         
      // Profit Column
      Label profitLabel = new Label(product.getTax() == 0 ? "---------------" : GUI.formatter.format(product.getTax())); // Tax is actually occupied by profit for these products
      profitLabel.setPrefWidth(112);
      GridPane.setHalignment(profitLabel, HPos.RIGHT);
      screen().getScroll().add(profitLabel, 4, scrollRow);
   }
   
    // Empty the scroll
   public void clearScroll() {
      scrollRow = 0;
      screen().getScroll().getChildren().clear();
      total = 0;
      screen().getTotal().setText("$ 0.00");
   }
 
}