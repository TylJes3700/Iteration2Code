import javafx.scene.control.*;
import javafx.scene.image.*;

public abstract class Controller {

   protected Screen screen;
   protected Alert.AlertType alert;

   protected Controller() {
      alert = Alert.AlertType.ERROR;
   }
   
   public Screen getScreen() {
      return screen;
   }
   
   public void setScreen(Screen screen) {
      this.screen = (Screen)screen;
   }

   public void changeWelcome() {
      screen.welcomeLabel.setText("[ " + Store.dataAdapter.getName() + " ]");
      screen.pic.setImage(new Image("/images/icons/" + Store.dataAdapter.getPic() + ".png"));
   }
   
   protected abstract Screen screen();
}