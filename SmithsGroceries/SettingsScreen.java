import javafx.geometry.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
   
public class SettingsScreen extends Screen {
      
   private ColumnConstraints column1, column2, column3;
   private Button changePassButton, changePicButton;
      
   protected void setUpPane() {
      gridPane.setVgap(20);
      
      column1 = GUI.getColumn(25.0, HPos.LEFT);  
      column2 = GUI.getColumn(50.0, HPos.CENTER);
      column3 = GUI.getColumn(25.0, HPos.RIGHT);
      
      gridPane.getColumnConstraints().addAll(column1, column2, column3);
      
      addUIControls();
   }
   
   protected void addUIControls() {
      
      // Header
      headerLabel = GUI.getHeader("Settings");
      gridPane.add(headerLabel, 1, 0);
      gridPane.setMargin(headerLabel, new Insets(0, 0, 20, 0));
      
      // Footer (Logo)
      logo = getLogo(118, 20, 0, 0);
      gridPane.add(logo, 2, 3);
      
      // Cancel Button
      gridPane.add(backButton, 2, 0);
      backButton.setOnAction(Store.getSettingsController().cancelClick());
      
      // ChangePassword Button
      changePassButton = GUI.getBigButton("Change Password");
      gridPane.add(changePassButton, 1, 1);
      GridPane.setMargin(changePassButton, new Insets(50, 0, 0, 0));
      changePassButton.setOnAction(Store.getSettingsController().changePassClick());
      
      // ChangePic Button
      changePicButton = GUI.getBigButton("Change Profile Picture");
      gridPane.add(changePicButton, 1, 2);
      GridPane.setMargin(changePicButton, new Insets(10, 0, 0, 0));
      changePicButton.setOnAction(Store.getSettingsController().changePicClick());
   }

// ATTRIBUTES USED BY CONTROLLER
}