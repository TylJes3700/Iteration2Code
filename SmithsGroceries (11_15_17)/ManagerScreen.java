import javafx.geometry.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
   
public class ManagerScreen extends Screen {
      
   private ColumnConstraints column1, column2, column3;
   private Button addUserButton, addItemButton, editItemButton, reportButton, settingsButton;

   protected void setUpPane() {
      gridPane.setVgap(20);
      
      column1 = GUI.getColumn(25.0, HPos.LEFT);  
      column2 = GUI.getColumn(50.0, HPos.CENTER);
      column3 = GUI.getColumn(25.0, HPos.RIGHT);
      
      gridPane.getColumnConstraints().addAll(column1, column2, column3);
      
      addUIControls();
   }
   
   protected void addUIControls() {
      
      // Footer (Logo)
      logo = getLogo(77, 20, 0, 0);
      gridPane.add(logo, 2, 5);
      
      // Logout Button
      backButton.setText("Logout");
      gridPane.add(backButton, 2, 0);
      backButton.setOnAction(Store.getManagerController().cancelClick());
      
      // Settings Button
      settingsButton = GUI.getBackButton("Settings");
      GridPane.setMargin(settingsButton, new Insets(94, 20, 0, 0));
      gridPane.add(settingsButton, 2, 0);
      settingsButton.setOnAction(Store.getManagerController().settingsClick());
      
      // Add New User Button
      addUserButton = GUI.getBigButton("Add User");
      gridPane.setValignment(addUserButton, VPos.BOTTOM);
      GridPane.setMargin(addUserButton, new Insets(125, 0, 0, 0));
      gridPane.add(addUserButton, 1, 0, 1, 2);
      addUserButton.setOnAction(Store.getManagerController().addUserClick());
      
      // AddItem Button
      addItemButton = GUI.getBigButton("Add Item");
      gridPane.add(addItemButton, 1, 2);
      GridPane.setMargin(addItemButton, new Insets(10, 0, 10, 0));
      addItemButton.setOnAction(Store.getManagerController().addItemClick());
      
      // EditItem Button
      editItemButton = GUI.getBigButton("Edit Item");
      GridPane.setMargin(editItemButton, new Insets(0, 0, 10, 0));
      gridPane.add(editItemButton, 1, 3);
      editItemButton.setOnAction(Store.getManagerController().editItemClick());
      
      // Business Report Button
      reportButton = GUI.getBigButton("Business Report");
      gridPane.setValignment(reportButton, VPos.TOP);
      gridPane.add(reportButton, 1, 4, 1, 2);
      reportButton.setOnAction(Store.getManagerController().reportClick());
   }

// ATTRIBUTES USED BY CONTROLLER
}