import javafx.geometry.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.event.*;

public class UserController extends Controller {
   
   private String defaultPass = "12345";
   
// CLICK EVENTS

    // Cancel Button
   public EventHandler<ActionEvent> cancelClick(){
      return 
         new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
               resetFields();
               GUI.setPane(GUI.managerNum);
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
               if (anyEmpty() || badNewName()) {
                  return;
               }
               else {
                  boolean type = ((RadioButton)screen().getGroup().getSelectedToggle()).getText().equals("Cashier") ? false : true;
                  User user = new User(screen().getUsername().getText(), defaultPass, type);
                  if (Store.dataAdapter.saveUser(user)) {
                     GUI.showAlert(Alert.AlertType.INFORMATION, screen.getPane().getScene().getWindow(), "Success!", "User Added\nDefault Password: 12345");
                     resetFields();
                                
                     // Return to menu
                     GUI.setPane(GUI.managerNum);
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
   protected UserScreen screen() {
      return (UserScreen)screen;
   }
   
    // Check if any fields are empty
   private boolean anyEmpty() {
      String message;
      if (screen().getUsername().getText().isEmpty()) {
         message = "Please enter a new Username";
      }
      else {
         return false;
      }
      GUI.showAlert(alert, screen.getPane().getScene().getWindow(), "Form Error", message);
      return true;
   }

    // Check if new username is incorrect format or already exists
   private boolean badNewName() {
      String name = screen().getUsername().getText();
      String message;
      if (name.length() > 10 || name.length() < 5) {
         message = "Username must be between 5 and 10 characters"; 
      }
      else if (!name.matches("^[a-zA-Z0-9]+$")) {
         message = "Username must be alphanumeric";
      }
      else if (Store.dataAdapter.findUser(name)) {
         message = "User already exists";
      }
      else {
         return false;
      }
      GUI.showAlert(alert, screen.getPane().getScene().getWindow(), "Form Error", message);
      return true;
   }

    // Reset fields to empty/defaults
   private void resetFields() {
      screen().getUsername().setText("");
      //type button reset
   }
}