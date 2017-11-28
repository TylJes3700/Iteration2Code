import javafx.scene.control.*;
import javafx.event.*;

public class LoginController extends Controller {
 
// CLICK EVENTS
 
    // Submit Button
   public EventHandler<ActionEvent> submit(){
      return 
         new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
               String title = "Form Error!";
               String message = "";
               String user = screen().getUser().getText(), pass = screen().getPass().getText();
               if (user.isEmpty()) {
                  message = "Please enter your User ID";
               }
               else if (pass.isEmpty()) {
                  message = "Please enter your password";  
               }
               else if (!Store.dataAdapter.findUser(user)) {
                  message = "Invalid User ID";   
               }
               else if (!Store.dataAdapter.validateLogin(pass)) {
                  message = "Invalid Password"; 
               }
               else {
                  if (Store.dataAdapter.isManager()) {
                     GUI.setPane(GUI.managerNum);
                  }
                  else {
                     GUI.setPane(GUI.checkoutNum);
                  }
                  return;
               }
               // Display appropriate error pop-up
               GUI.showAlert(alert, screen.getPane().getScene().getWindow(), title, message); 
            }
         };
   }
   
// METHODS
 
    // Cast and return controller's screen
   protected LoginScreen screen() {
      return (LoginScreen)screen;
   }
}