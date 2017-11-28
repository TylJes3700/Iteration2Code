import javafx.geometry.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.image.*;
   
public class PicScreen extends Screen {

   private ColumnConstraints column1, column2, column3, column4, column5, column6, column7, column8, column9;
   private Button confirmButton;
   
   private static int numRows = 5;
   private static int numCols = 9;
      
   protected void setUpPane() {
      gridPane.setVgap(10);
      
      column1 = GUI.getColumn(132, HPos.CENTER); // 100px icon + 10px space on right + 22px space on left
      column2 = GUI.getColumn(110, HPos.CENTER); // 100px icon + 10px space on right
      column3 = GUI.getColumn(110, HPos.CENTER); // 100px icon + 10px space on right
      column4 = GUI.getColumn(110, HPos.CENTER); // 100px icon + 10px space on right
      column5 = GUI.getColumn(110, HPos.CENTER); // 100px icon + 10px space on right
      column6 = GUI.getColumn(110, HPos.CENTER); // 100px icon + 10px space on right
      column7 = GUI.getColumn(110, HPos.CENTER); // 100px icon + 10px space on right
      column8 = GUI.getColumn(110, HPos.CENTER); // 100px icon + 10px space on right
      column9 = GUI.getColumn(122, HPos.CENTER); // 100px icon + 22px space on right
      
      gridPane.getColumnConstraints().addAll(column1, column2, column3, column4, column5, column6, column7, column8, column9);
      
      addUIControls();
   }
   
   protected void addUIControls() {
      
      // Header
      headerLabel = GUI.getHeader("Change Profile Picture");
      gridPane.add(headerLabel, 2, 0, 5, 1);
      
      GridPane.setMargin(welcomeLabel, new Insets(20, 0, 0, 20));
      pic = GUI.getPic();
      
      loadIcons();
      
      // Confirm Button
      confirmButton = new Button("Confirm");
      confirmButton.setId("cash-payment-button");
      confirmButton.setPrefHeight(54);
      confirmButton.setDefaultButton(true);
      confirmButton.setPrefWidth(150);
      gridPane.add(confirmButton, 3, 6, 3, 1);
      GridPane.setHalignment(confirmButton, HPos.CENTER);
      
      confirmButton.setOnAction(Store.getPicController().confirmClick());
      
      // Footer (Logo)
      logo = getLogo(2, 20, 0, 0);
      gridPane.add(logo, 6, 6, 3, 1);
      
      // Cancel Button
      gridPane.add(backButton, 7, 0, 2, 1);
      GridPane.setMargin(backButton, new Insets(20, 20, 0, 0));
      backButton.setOnAction(Store.getPicController().cancelClick());
   }
  
  // Load and add all icons to screen
   public void loadIcons() {
      for (int i = 0; i < numRows*numCols; i++) {
         int col = i%9, row = i/9 + 1;
         Button iconButton = new Button();
         Image image = new Image("/images/icons/" + i + ".png", 100, 100, false, false);
         ImageView icon = new ImageView(image);
         iconButton.setGraphic(icon);
         iconButton.setId("icon-button");
         GridPane.setHalignment(iconButton, HPos.LEFT);
         if (col == 8) {
            GridPane.setMargin(iconButton, new Insets(0, 22, 0, 0));
         }
         else if (col == 0) {
            GridPane.setMargin(iconButton, new Insets(0, 10, 0, 22));
         }
         else {
            GridPane.setMargin(iconButton, new Insets(0, 10, 0, 0));
         }
         iconButton.setOnAction(Store.getPicController().iconClick(i));
         getPane().add(iconButton, col, row);
      }
   }
  
// ATTRIBUTES USED BY CONTROLLER
  
}