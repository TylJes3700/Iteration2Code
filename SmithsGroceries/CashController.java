import javafx.scene.control.*;
import javafx.event.*;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class CashController extends Controller {
   
   private double payment, change = 0;

// CLICK EVENTS
   
    // Confirm Button
   public EventHandler<ActionEvent> confirmClick(){
      return 
         new EventHandler<ActionEvent>() {
            @Override
                    public void handle(ActionEvent event) {
               Alert.AlertType alert = Alert.AlertType.ERROR;
               String title = "Form Error!";
               String message = "";
               if (screen().getPayment().getText().isEmpty()) {
                  message = "Please enter a payment amount";
               }
               else {
                  try {
                     payment = Double.parseDouble(screen().getPayment().getText());
                     if (payment < Store.getCheckoutController().getTotal()) {
                        message = "Payment must be >= Total";
                     }
                     if (payment > 999999999) {
                        message = "No billionares allowed";
                     }
                  }
                  catch (NumberFormatException e) {
                     message = "Payment must be numerical";
                  }
               }
               if (message.equals("")) {
                  change = payment - Store.getCheckoutController().getTotal(); 
                  updateChangeLabel(); 
                  screen().getPayment().setText("$ " + screen().getPayment().getText());
                  switchButtons();
                  return;
               }
                         
                         // Display appropriate error pop-up
               GUI.showAlert(alert, screen.getPane().getScene().getWindow(), title, message);
            }
         };
   }
   
    // End Button
   public EventHandler<ActionEvent> endClick() {
      return 
         new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
               DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
               String lastID = Store.dataAdapter.getLastID("Orders");
               int id = (lastID == null) ? 0 : Integer.parseInt(lastID) + 1;
                  
               // Create order
               Order order = new Order(id, Store.getCheckoutController().getTotal(), dateFormat.format(new Date()).toString().substring(0,10), dateFormat.format(new Date()).toString().substring(11,19), "cash", Store.dataAdapter.getName(), Store.getCheckoutController().getProducts());
               if (Store.dataAdapter.saveOrder(order)) {
                  GUI.showAlert(Alert.AlertType.INFORMATION, screen.getPane().getScene().getWindow(), "Success!", "Payment Successful\nOrder logged");
                  GUI.setPane(2);
                  resetFields();
                  Store.getCheckoutController().clearScroll();
               }
               else {
                  GUI.showAlert(Alert.AlertType.ERROR, screen.getPane().getScene().getWindow(), "SQL ERROR!", "Database Access Error");
               }
            }
         };
   }
   
    // Cancel Button
   public EventHandler<ActionEvent> cancelClick() {
      return 
         new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
               if (screen().getEndButton().isVisible()) {
                  resetFields();
               }
               GUI.setPane(6);
            }
         };
   }

// METHODS
   
    // Cast and return controller's screen
   protected CashScreen screen() {
      return (CashScreen)screen;
   }
   
    // Reset field to empty and swap buttons
   private void resetFields() {
      switchButtons();
      screen().getPayment().setText("");
      screen().getChange().setText("");
   }
   
    // Switch confirm/end transation buttons
   private void switchButtons() {
      if(screen().getPayment().isDisabled()) {
         screen().getPayment().setDisable(false);
         screen().getConfirmButton().setDisable(false);
         screen().getConfirmButton().setVisible(true);
         screen().getEndButton().setDisable(true);
         screen().getEndButton().setVisible(false);
      }
      else {
         screen().getPayment().setDisable(true);
         screen().getConfirmButton().setDisable(true);
         screen().getConfirmButton().setVisible(false);
         screen().getEndButton().setDisable(false);
         screen().getEndButton().setVisible(true);
      }
   }
   
    // Update change 
   private void updateChangeLabel() {
      screen().getChange().setText("$ " + GUI.formatter.format(change));
   }
   
    // Update total
   public void updateTotalLabel() {
      screen().getTotal().setText("$ " + GUI.formatter.format(Store.getCheckoutController().getTotal()));
   }
}