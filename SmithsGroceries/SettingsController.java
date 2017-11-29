import javafx.event.*;

public class SettingsController extends Controller {
 
// CLICK EVENTS

    // Cancel Button
   public EventHandler<ActionEvent> cancelClick(){
      return 
         new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
               if (Store.dataAdapter.isManager()) {
                  GUI.setPane(GUI.managerNum);
               }
               else {
                  GUI.setPane(GUI.checkoutNum);
               }
            }
         };
   }
 
    // Change Password Button
   public EventHandler<ActionEvent> changePassClick(){
      return GUI.changeScreen(GUI.passNum);
   }
 
    // Change Picture Button
   public EventHandler<ActionEvent> changePicClick(){
      return GUI.changeScreen(GUI.picNum);
   }
 
// METHODS
 
    // Cast and return controller's screen
   protected SettingsScreen screen() {
      return (SettingsScreen)screen;
   }
 
}