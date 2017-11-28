import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.event.*;

public class ItemController extends Controller {
 
   private double quantity;
   private double price, tax;
   private String contact;

// CLICK EVENTS
 
    // Cancel Button
   public EventHandler<ActionEvent> cancelClick(){
      return GUI.changeScreen(1);
   }
 
    // Load Button
   public EventHandler<ActionEvent> loadClick(){
      return 
         new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
               int id;
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
               if (message.equals("")) {
                  Store.getEditItemController().loadProductInfo(product);
                  toggleDisable(false);
               }
            }
         };
   }
 
    // Confirm Button
   public EventHandler<ActionEvent> confirmClick(){
      return 
         new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
               if (anyEmpty() || anyBadLengths() || anyBadNumbers()) {
                  return;
               }
               else {
                  String year = screen().getExpYear().getValue(), month = screen().getExpMonth().getValue(), day = screen().getExpDay().getValue();
                  if (year.equals("YYYY")) {
                     year = "----";
                  }
                  if (month.equals("MM")) {
                     month = "--";
                  }
                  if (day.equals("DD")) {
                     day = "--";
                  }
                  String date = year + "/" + month + "/" + day;
                  
                  // Add product to product Table (if ID is new) or edit existing product (if ID is old)
                  if (Store.dataAdapter.saveProduct(new Product(screen().getName().getText(), Integer.parseInt(screen().getID().getText()), ((RadioButton)screen().getGroup().getSelectedToggle()).getText(), price, tax, quantity, date, screen().getVendor().getText(), contact))) {
                     GUI.showAlert(Alert.AlertType.INFORMATION, screen.getPane().getScene().getWindow(), "Success!", "Database Updated");
                     resetFields();
                  
                  // Return to menu
                     GUI.setPane(1);
                  }
                  else {
                     GUI.showAlert(alert, screen.getPane().getScene().getWindow(), "SQL ERROR!", "Database Access Error");
                  }
               }
            }
         };
   }
 
// METHODS

    // Cast and return controller's screen
   protected ItemScreen screen() {
      return (ItemScreen)screen;
   }

    // Check if any fields are empty
   private boolean anyEmpty() {
      String message;
      if (screen().getName().getText().isEmpty()) {
         message = "Please enter a name";
      }
      else if (screen().getQuantity().getText().isEmpty()) {
         message = "Please enter a quantity"; 
      }
      else if (screen().getPrice().getText().isEmpty()) {
         message = "Please enter a price";  
      }
      else if (screen().getTax().getText().isEmpty()) {
         message = "Please enter a tax rate";  
      }
      else if (screen().getVendor().getText().isEmpty()) {
         message = "Please enter a vendor"; 
      }
      else if (screen().getContact1().getText().isEmpty() || screen().getContact2().getText().isEmpty() || screen().getContact3().getText().isEmpty()) {
         message = "Please enter vendor phone number (***) - (***) - (****)";   
      }
      else {
         return false;
      }
      GUI.showAlert(alert, screen.getPane().getScene().getWindow(), "Form Error", message);
      return true;
   }
   
    // Check if any text inputs are incorrect length
   private boolean anyBadLengths() {
      String message;
      if (screen().getName().getText().length() > 30) {
         message = "Name must be between 1 and 30 characters"; 
      }
      else if (screen().getVendor().getText().length() > 30) {
         message = "Vendor must be between 1 and 30 characters";
      }
      else {
         return false;
      }
      GUI.showAlert(alert, screen.getPane().getScene().getWindow(), "Form Error", message);
      return true;
   }
   
    // Check if any number inputs are incorrect format/length
   private boolean anyBadNumbers() {
      String message = "";
      String c1Text = screen().getContact1().getText(), c2Text = screen().getContact2().getText(), c3Text = screen().getContact3().getText();
      int c1 = 0, c2 = 0, c3 = 0;
      try {
         c1 = Integer.parseInt(c1Text);
         c2 = Integer.parseInt(c2Text);
         c3 = Integer.parseInt(c3Text);
         if (c1Text.length() != 3 || c2Text.length() != 3 || c3Text.length() != 4) {
            throw new NumberFormatException();
         }
      }
      catch (NumberFormatException e) {
         message = "Contact # must be 10 digits (***)-(***)-(****)";
      }
      try {
         tax = Double.parseDouble(screen().getTax().getText());
         if (tax < 0 || tax > 100) {
            message = "Tax Rate must be between 0 and 100";
         }
      }
      catch (NumberFormatException e) {
         message = "Tax Rate must be a number";
      }
      try {
         price = Double.parseDouble(screen().getPrice().getText());
         if (price < 0.01 || price > 10000) {
            message = "Price must be between 0.01 and 10,000";
         }
      }
      catch (NumberFormatException e) {
         message = "Price must be a number";
      }
      try {
         quantity = Double.parseDouble(screen().getQuantity().getText());
         if (((RadioButton)screen().getGroup().getSelectedToggle()).getText().equals("Single")) {
            if (quantity < 0 || quantity > 99999 || quantity % 1 != 0) {
               message = "Quantity must be integer between 0 and 99,999";
            }
         }
         else {
            if (quantity < 0 || quantity > 99999) {
               message = "Quantity must be between 0 and 99,999";
            }
         }
      }
      catch (NumberFormatException e) {
         message = "Quantity must be an integer (single) or double (lb/oz)";
      }
      if (message.equals("")) {
         contact = c1Text + "-" + c2Text + "-" + c3Text;
         return false;
      }
      GUI.showAlert(alert, screen.getPane().getScene().getWindow(), "Form Error", message);
      return true;
   }
   
    // Reset fields to empty/defaults
   private void resetFields() {
      resetID();
      screen().getName().setText("");
      screen().getQuantity().setText("0");
      screen().getPrice().setText("");
      screen().getTax().setText("9");
      screen().getExpYear().setValue("YYYY");
      screen().getExpMonth().setValue("MM");
      screen().getExpDay().setValue("DD");
      screen().getVendor().setText("");
      screen().getContact1().setText("");
      screen().getContact2().setText("");
      screen().getContact3().setText("");
   }
   
    // Set ID to next available one
   public void resetID() {
      String id = Store.dataAdapter.getLastID("Products");
      if (id == null) { // Table empty
         screen().getID().setText("0");
      }
      else {
         screen().getID().setText(Integer.toString(Integer.parseInt(id) + 1));
      }
   }
   
   public void toggleDisable(boolean toggle) {
      screen().getName().setDisable(toggle);
      screen().getQuantity().setDisable(toggle);
      screen().getSingle().setDisable(toggle);
      screen().getOunce().setDisable(toggle);
      screen().getPound().setDisable(toggle);
      screen().getPrice().setDisable(toggle);
      screen().getTax().setDisable(toggle);
      screen().getExpMonth().setDisable(toggle);
      screen().getExpDay().setDisable(toggle);
      screen().getExpYear().setDisable(toggle);
      screen().getVendor().setDisable(toggle);
      screen().getContact1().setDisable(toggle);
      screen().getContact2().setDisable(toggle);
      screen().getContact3().setDisable(toggle);
      screen().getID().setDisable(!toggle);
      screen().getConfirm().setDisable(toggle);
      screen().getConfirm().setVisible(!toggle);
      screen().getLoad().setDisable(!toggle);
      screen().getLoad().setVisible(toggle);
   }
   
    // Load designated product from database
   public void loadProductInfo(Product product) {
      String contact = product.getContact();
      screen().getName().setText(product.getName());
      String year = product.getExpDate().substring(0,4);
      String month = product.getExpDate().substring(5,7);
      String day = product.getExpDate().substring(8,10);
      screen().getID().setText(Integer.toString(product.getID()));
      if (product.getUnit().equals("Ounce")) {
         screen().getGroup().selectToggle(screen().getOunce());
      }
      else if (product.getUnit().equals("Pound")) {
         screen().getGroup().selectToggle(screen().getPound());
      }
      else {
         screen().getGroup().selectToggle(screen().getSingle());
      }
      screen().getQuantity().setText(Double.toString(product.getQuantity()));
      screen().getPrice().setText(GUI.formatter.format(product.getPrice()));
      screen().getTax().setText(Double.toString(product.getTax()));
      if (!year.equals("----")) {
         screen().getExpYear().setValue(year);
      }
      else {
         screen().getExpYear().setValue("Year");
      }
      if (!month.equals("--")) {
         screen().getExpMonth().setValue(month);
      }
      else {
         screen().getExpMonth().setValue("Month");
      }
      if (!day.equals("--")) {
         screen().getExpDay().setValue(day);
      }
      else {
         screen().getExpDay().setValue("Day");
      }
      screen().getVendor().setText(product.getVendor());
      screen().getContact1().setText(contact.substring(0,3));
      screen().getContact2().setText(contact.substring(4,7));
      screen().getContact3().setText(contact.substring(8,12));
   }

}