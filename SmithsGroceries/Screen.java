import javafx.geometry.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.image.*;

public abstract class Screen {

   protected GridPane gridPane;
   protected ImageView logo, pic;
   protected Label welcomeLabel, headerLabel;
   protected Button backButton;
   protected boolean toggle;
      
   protected Screen() {
      gridPane = new GridPane();
      gridPane.setAlignment(Pos.CENTER);
      welcomeLabel = GUI.getWelcome();
      gridPane.add(welcomeLabel, 0, 0, 3, 2);
      pic = GUI.getPic();
      gridPane.add(pic, 0, 0, 3, 3);
      backButton = GUI.getBackButton("Back");
      setUpPane();
   }
   
   protected Screen(boolean toggle) {
      this.toggle = toggle;
      gridPane = new GridPane();
      gridPane.setAlignment(Pos.CENTER);
      welcomeLabel = GUI.getWelcome();
      gridPane.add(welcomeLabel, 0, 0, 3, 2);
      pic = GUI.getPic();
      gridPane.add(pic, 0, 0, 3, 3);
      backButton = GUI.getBackButton("Back");
      setUpPane();
   }
      
   protected abstract void setUpPane();
   
   protected abstract void addUIControls();

   protected GridPane getPane() {
      return gridPane;
   }

   protected ImageView getLogo(int top, int right, int bottom, int left) {
      logo = GUI.getLogo();
      gridPane.setMargin(logo, new Insets(top, right, bottom, left));
      return logo;
   }

}