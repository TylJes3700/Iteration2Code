import javafx.scene.control.*;
import javafx.event.*;

public class CardController extends Controller {
 
   private boolean debit = false;
   private String card, phone;

// CLICK EVENTS 
 
    // Cancel Button
   public EventHandler<ActionEvent> cancelClick(){
      return GUI.changeScreen(GUI.payNum);
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
                  String expDate = screen().getExpYear().getValue() + "/" + screen().getExpMonth().getValue();
                  String payType = debit ? "debit" : "credit";
                  String lastID = Store.dataAdapter.getLastID("Orders");
                  int id = (lastID == null) ? 0 : Integer.parseInt(lastID) + 1;
                  
               // Create order
                  Order order = new Order(id, Store.getCheckoutController().getTotal(), GUI.getDate(), GUI.getTime(), payType, Store.dataAdapter.getName(), Store.getCheckoutController().getProducts());
               
                  // Add order/details/payment??/customer?? to order table
                  if (Store.dataAdapter.saveOrder(order)) {
                     GUI.showAlert(Alert.AlertType.INFORMATION, screen.getPane().getScene().getWindow(), "Success!", "Database Updated");
                     resetFields();
                     Store.getCheckoutController().clearScroll();
                  
                  // Return to menu
                     GUI.setPane(GUI.checkoutNum);
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
   protected CardScreen screen() {
      return (CardScreen)screen;
   }
 
    // Check if any inputs are empty
   private boolean anyEmpty() {
      String message;
      if (screen().getCard1().getText().isEmpty() || screen().getCard2().getText().isEmpty() || screen().getCard3().getText().isEmpty() || screen().getCard4().getText().isEmpty()) {
         message = "Please enter a card number \n#### - #### - #### - ####";   
      }
      else if (screen().getExpMonth().getValue().equals("MM") || screen().getExpYear().getValue().equals("YYYY")) {
         message = "Please select an expiration date";
      }
      else if (screen().getCsc().getText().isEmpty()) {
         message = "Please enter a CSC";
      }
      else if (screen().getFirstName().getText().isEmpty()) {
         message = "Please enter a first name";
      }
      else if (screen().getLastName().getText().isEmpty()) {
         message = "Please enter a last name";
      }
      else if (screen().getAddress().getText().isEmpty()) {
         message = "Please enter an address"; 
      }
      else if (screen().getCity().getText().isEmpty()) {
         message = "Please enter a city";  
      }
      else if (screen().getState().getValue().equals("--")) {
         message = "Please select a state";  
      }
      else if (screen().getZip().getText().isEmpty()) {
         message = "Please enter a zip code"; 
      }
      else if (debit && screen().getPin().getText().isEmpty()) {
         message = "Please enter a PIN"; 
      }
      else if (screen().getContact1().getText().isEmpty() || screen().getContact2().getText().isEmpty() || screen().getContact3().getText().isEmpty()) {
         message = "Please enter a phone number \n(***) - (***) - (****)";   
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
      if (screen().getFirstName().getText().length() > 30) {
         message = "First name must be between 1 and 30 characters"; 
      }
      else if (screen().getLastName().getText().length() > 30) {
         message = "Last name must be between 1 and 30 characters"; 
      }
      else if (screen().getAddress().getText().length() > 30) {
         message = "Address must be between 1 and 30 characters"; 
      }
      else if (screen().getCity().getText().length() > 12) {
         message = "City must be between 1 and 12 characters"; 
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
      String card1Text = screen().getCard1().getText(), card2Text = screen().getCard2().getText(), card3Text = screen().getCard3().getText(), card4Text = screen().getCard4().getText();
      String c1Text = screen().getContact1().getText(), c2Text = screen().getContact2().getText(), c3Text = screen().getContact3().getText();
      int c1 = 0, c2 = 0, c3 = 0, card1 = 0, card2 = 0, card3 = 0, card4 = 0, csc, zip, pin;
      try {
         c1 = Integer.parseInt(screen().getContact1().getText());
         c2 = Integer.parseInt(screen().getContact2().getText());
         c3 = Integer.parseInt(screen().getContact3().getText());
         if (c1Text.length() != 3 || c2Text.length() != 3 || c3Text.length() != 4) {
            throw new NumberFormatException();
         }
      }
      catch (NumberFormatException e) {
         message = "Phone # must be 10 digits \n(***) - (***) - (****)";
      }
      if (debit) {
         try {
            pin = Integer.parseInt(screen().getPin().getText());
            if (pin < 1000 || pin > 9999) {
               throw new NumberFormatException();
            }
         }
         catch (NumberFormatException e) {
            message = "PIN must be 4 digits ####";
         }
      }
      try {
         zip = Integer.parseInt(screen().getZip().getText());
         if (screen().getZip().getText().length() != 5) {
            throw new NumberFormatException();
         }
      }
      catch (NumberFormatException e) {
         message = "Zip Code must be 5 digits #####";
      }
      try {
         csc = Integer.parseInt(screen().getCsc().getText());
         if (screen().getCsc().getText().length() != 3) {
            throw new NumberFormatException();
         }
      }
      catch (NumberFormatException e) {
         message = "CSC must be 3 digits ###";
      }
      try {
         card1 = Integer.parseInt(card1Text);
         card2 = Integer.parseInt(card2Text);
         card3 = Integer.parseInt(card3Text);
         card4 = Integer.parseInt(card4Text);
         if (card1Text.length() != 4 || card2Text.length() != 4 || card3Text.length() != 4 || card4Text.length() != 4) {
            throw new NumberFormatException();
         }
      }
      catch (NumberFormatException e) {
         message = "Card number must be 16 digits \n#### - #### - #### - ####";
      }
      if (message.equals("")) {
         phone = c1Text + "-" + c2Text + "-" + c3Text;
         card = card1Text + "-" + card2Text + "-" + card3Text + "-" + card4Text;
         return false;
      }
      GUI.showAlert(alert, screen.getPane().getScene().getWindow(), "Form Error", message);
      return true;
   }
   
    // Reset fields to empty
   private void resetFields() {
      screen().getCard1().setText("");
      screen().getCard2().setText("");
      screen().getCard3().setText("");
      screen().getCard4().setText("");
      screen().getExpYear().setValue("YYYY");
      screen().getExpMonth().setValue("MM");
      screen().getCsc().setText("");
      screen().getFirstName().setText("");
      screen().getLastName().setText("");
      screen().getAddress().setText("");
      screen().getCity().setText("");
      screen().getState().setValue("--");
      screen().getZip().setText("");
      screen().getPin().setText("");
      screen().getContact1().setText("");
      screen().getContact2().setText("");
      screen().getContact3().setText("");
   }

}