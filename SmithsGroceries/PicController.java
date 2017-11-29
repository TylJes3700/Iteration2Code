import javafx.geometry.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.event.*;

public class PicController extends Controller {
 
// CLICK EVENTS

   // Cancel Button
   public EventHandler<ActionEvent> cancelClick(){
      return 
         new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
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
               
               GUI.showAlert(Alert.AlertType.INFORMATION, screen.getPane().getScene().getWindow(), "Success!", "Profile Picture Updated");
                  
                  // Return to menu
               GUI.setPane(GUI.settingsNum);
               
            }
         };
   }
 
 // Icon Button
   public EventHandler<ActionEvent> iconClick(int newPic){
      return GUI.changePic(newPic);
   }
 
// METHODS
 
    // Cast and return controller's screen
   protected PicScreen screen() {
      return (PicScreen)screen;
   }
}