import javafx.geometry.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
   
public class LookUpScreen extends Screen {
      
   private ColumnConstraints column1, column2, column3, column4;
      
   protected void setUpPane() {
      column1 = GUI.getColumn(25.0, HPos.CENTER); 
      column2 = GUI.getColumn(25.0, HPos.CENTER);  
      column3 = GUI.getColumn(25.0, HPos.CENTER);
      column4 = GUI.getColumn(25.0, HPos.CENTER);
      
      gridPane.getColumnConstraints().addAll(column1, column2, column3, column4);
      
      addUIControls();
   }
   
   protected void addUIControls() {
      
      // Header
      headerLabel = GUI.getHeader("Look Up Item");
      gridPane.add(headerLabel, 1, 0, 2, 1);
      
      // Cancel Button
      gridPane.add(backButton, 2, 0);
      backButton.setOnAction(Store.getLookUpController().cancelClick());
      
      // Footer (Logo)
      logo = getLogo(40, 20, 0, 0);
      gridPane.add(logo, 3, 4);
   }

// ATTRIBUTES USED BY CONTROLLER

}