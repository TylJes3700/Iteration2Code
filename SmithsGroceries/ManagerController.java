import javafx.event.*;

public class ManagerController extends Controller {
 
// CLICK EVENTS

    // Cancel Button
   public EventHandler<ActionEvent> cancelClick(){
      return GUI.changeScreen(GUI.loginNum);
   }

    // Settings Button
   public EventHandler<ActionEvent> settingsClick(){
      return GUI.changeScreen(GUI.settingsNum);
   }

    // Add User Button
   public EventHandler<ActionEvent> addUserClick(){
      return GUI.changeScreen(GUI.userNum);
   }

    // Add Item Button
   public EventHandler<ActionEvent> addItemClick(){
      return 
         new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
               Store.getAddItemController().resetID();
               Store.getAddItemController().toggleDisable(false);
               GUI.setPane(GUI.addItemNum);
            }
         };
   }

    // Edit Item Button
   public EventHandler<ActionEvent> editItemClick(){
      return 
         new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
               Store.getEditItemController().toggleDisable(true);
               GUI.setPane(GUI.editItemNum);
            }
         };
   } 
 
    // Business Report Button
   public EventHandler<ActionEvent> reportClick(){
      return GUI.changeScreen(GUI.reportNum);
   }

// METHODS

    // Cast and return controller's screen
   protected ManagerScreen screen() {
      return (ManagerScreen)screen;
   }
}