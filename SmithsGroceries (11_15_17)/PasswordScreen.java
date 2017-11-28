import javafx.geometry.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
   
public class PasswordScreen extends Screen {

   private ColumnConstraints column1, column2, column3, column4;
   private Label currentLabel, newLabel, confirmLabel;
   private TextField currentField, newField, confirmField;
   private Button confirmButton;
      
   protected void setUpPane() {
      gridPane.setVgap(20);
      
      column1 = GUI.getColumn(25.0, HPos.CENTER);
      column2 = GUI.getColumn(25.0, HPos.CENTER);
      column3 = GUI.getColumn(25.0, HPos.CENTER);
      column4 = GUI.getColumn(25.0, HPos.CENTER);
      
      gridPane.getColumnConstraints().addAll(column1, column2, column3, column4);
      
      addUIControls();
   }
   
   protected void addUIControls() {
      
      // Header
      headerLabel = GUI.getHeader("Change Password");
      gridPane.add(headerLabel, 1, 0, 2, 1);
      
      // Current Password Label
      currentLabel = new Label("Current : ");
      currentLabel.setId("cash-payment-label");
      gridPane.add(currentLabel, 0, 1, 2, 1);
      GridPane.setHalignment(currentLabel, HPos.RIGHT);
      
      // New Password Label
      newLabel = new Label("New : ");
      newLabel.setId("cash-payment-label");
      gridPane.add(newLabel, 0, 2, 2, 1);
      GridPane.setHalignment(newLabel, HPos.RIGHT);
      
      // Confirm Password Label
      confirmLabel = new Label("Confirm New : ");
      confirmLabel.setId("cash-payment-label");
      gridPane.add(confirmLabel, 0, 3, 2, 1);
      GridPane.setHalignment(confirmLabel, HPos.RIGHT);
      
      // Current Password Field
      currentField = GUI.getField("", 200);
      gridPane.add(currentField, 2, 1);
      
      // New Password Field
      newField = GUI.getField("", 200);
      gridPane.add(newField, 2, 2);
      
      // Confirm Password Field
      confirmField = GUI.getField("", 200);
      gridPane.add(confirmField, 2, 3);
      
      // Confirm Button
      confirmButton = new Button("Confirm");
      confirmButton.setId("cash-payment-button");
      confirmButton.setPrefHeight(54);
      confirmButton.setDefaultButton(true);
      confirmButton.setPrefWidth(150);
      gridPane.add(confirmButton, 1, 4, 2, 1);
      GridPane.setHalignment(confirmButton, HPos.CENTER);
      
      confirmButton.setOnAction(Store.getPasswordController().confirmClick());
      
      // Footer (Logo)
      logo = getLogo(83, 20, 0, 0);
      gridPane.add(logo, 3, 5);
      
      // Cancel Button
      gridPane.add(backButton, 3, 0);
      GridPane.setMargin(backButton, new Insets(20, 20, 205, 0));
      backButton.setOnAction(Store.getPasswordController().cancelClick());
   }
  
// ATTRIBUTES USED BY CONTROLLER

    // Current Password Field
   public TextField getCurrentPass() {
      return currentField;
   }

    // New Password Field
   public TextField getNewPass() {
      return newField;
   }
   
   // Confirm Password Field
   public TextField getConfirmPass() {
      return confirmField;
   }

}