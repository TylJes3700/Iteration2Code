import javafx.geometry.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.event.*;

public class LookUpController extends Controller {
 
// CLICK EVENTS
 
    // Cancel Button
   public EventHandler<ActionEvent> cancelClick(){
      return GUI.changeScreen(GUI.checkoutNum);
   }
 
// METHODS
 
    // Cast and return controller's screen
   protected LookUpScreen screen() {
      return (LookUpScreen)screen;
   }
}