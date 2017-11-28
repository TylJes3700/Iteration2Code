import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.event.*;

public class PayController extends Controller {
 
// CLICK EVENTS

    // Cancel Button
   public EventHandler<ActionEvent> cancelClick(){
      return GUI.changeScreen(GUI.checkoutNum);
   }

    // Cash Button
   public EventHandler<ActionEvent> cashClick(){
      return GUI.changeScreen(GUI.cashNum);
   }

    // Debit Button
   public EventHandler<ActionEvent> debitClick(){
      return GUI.changeScreen(GUI.cardNum);
   }

    // Credit Button
   public EventHandler<ActionEvent> creditClick(){
      return GUI.changeScreen(GUI.cardNum);
   } 
 
// METHODS 
 
    // Cast and return controller's screen
   protected PayScreen screen() {
      return (PayScreen)screen;
   }
}