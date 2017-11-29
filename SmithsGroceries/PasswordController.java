import javafx.scene.control.*;
import javafx.event.*;

public class PasswordController extends Controller {
 
// CLICK EVENTS

    // Cancel Button
   public EventHandler<ActionEvent> cancelClick(){
      return 
         new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
               resetFields();
               GUI.setPane(GUI.settingsNum);
            }
         };
   }

    // Confirm Button
   public EventHandler<ActionEvent> confirmClick(){
      return 
         new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
               String title = "Form Error!";
               String message = "";
               if (anyEmpty() || badCurrentPass() || badNewPass() || unmatched()) {
                  return;
               }
               else {
                  User user = new User(Store.dataAdapter.getName(), screen().getNewPass().getText(), Store.dataAdapter.isManager(), Store.dataAdapter.getPic());
                  if (Store.dataAdapter.saveUser(user)) {
                     GUI.showAlert(Alert.AlertType.INFORMATION, screen.getPane().getScene().getWindow(), "Success!", "Password Updated");
                     resetFields();
                  
                  // Return to menu
                     GUI.setPane(GUI.settingsNum);
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
   protected PasswordScreen screen() {
      return (PasswordScreen)screen;
   }
 
    // Check if any fields are empty
   private boolean anyEmpty() {
      String message;
      if (screen().getCurrentPass().getText().isEmpty()) {
         message = "Please enter current password";
      }
      else if (screen().getNewPass().getText().isEmpty()) {
         message = "Please enter a new password"; 
      }
      else if (screen().getConfirmPass().getText().isEmpty()) {
         message = "Please enter new password again";  
      }
      else {
         return false;
      }
      GUI.showAlert(alert, screen.getPane().getScene().getWindow(), "Form Error", message);
      return true;
   }
   
    // Check if current password doesn't match current user
   private boolean badCurrentPass() {
      if (!Store.dataAdapter.checkPass(screen().getCurrentPass().getText())) {
         GUI.showAlert(alert, screen.getPane().getScene().getWindow(), "Form Error", "Incorrect Current Password");
      }
      else {
         return false;
      }
      return true;
   }
   
    // Check if new password is incorrect format or same as current password
   private boolean badNewPass() {
      String pass = screen().getNewPass().getText();
      String message;
      if (pass.length() > 10 || pass.length() < 5) {
         message = "Password must be between 5 and 10 characters"; 
      }
      else if (!pass.matches("^[a-zA-Z0-9]+$")) {
         message = "Password must be alphanumeric";
      }
      else if (Store.dataAdapter.checkPass(pass)) {
         message = "New Password must be different from Current Password";
      }
      else {
         return false;
      }
      GUI.showAlert(alert, screen.getPane().getScene().getWindow(), "Form Error", message);
      return true;
   }
   
    // Check if confirm password doesn't match new password 
   private boolean unmatched() {
      if (!screen().getNewPass().getText().equals(screen().getConfirmPass().getText())) {
         GUI.showAlert(alert, screen.getPane().getScene().getWindow(), "Form Error", "New password inputs do not match");
      }
      else {
         return false;
      }
      return true;
   }
   
    // Reset fields to empty
   private void resetFields() {
      screen().getCurrentPass().setText("");
      screen().getNewPass().setText("");
      screen().getConfirmPass().setText("");
   }
}