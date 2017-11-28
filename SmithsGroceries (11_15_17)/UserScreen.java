import javafx.geometry.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
   
public class UserScreen extends Screen {

   private ColumnConstraints column1, column2, column3, column4;
   private Label usernameLabel, typeLabel;
   private TextField usernameField;
   private Button confirmButton;
   private RadioButton cashierButton, managerButton;
   private ToggleGroup group;
      
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
      headerLabel = GUI.getHeader("Add New User");
      gridPane.add(headerLabel, 1, 0, 2, 1);
      
      // New Username Label
      usernameLabel = new Label("Username : ");
      usernameLabel.setId("cash-payment-label");
      gridPane.add(usernameLabel, 0, 1, 2, 1);
      GridPane.setHalignment(usernameLabel, HPos.RIGHT);
      
      // User Type Label
      typeLabel = new Label("User Type : ");
      typeLabel.setId("cash-payment-label");
      gridPane.add(typeLabel, 0, 2, 2, 1);
      GridPane.setHalignment(typeLabel, HPos.RIGHT);
      
      // New Username Field
      usernameField = GUI.getField("", 200);
      gridPane.add(usernameField, 2, 1);
      
      // User Type Radio Button Group
      group = new ToggleGroup();
      cashierButton = GUI.getRadioButton("Cashier", group);
      cashierButton.setSelected(true);
      GridPane.setMargin(cashierButton, new Insets(0, 0, 0, 20));
      managerButton = GUI.getRadioButton("Manager", group);
      GridPane.setMargin(managerButton, new Insets(0, 0, 0, 130));
      gridPane.setHalignment(cashierButton, HPos.LEFT);
      gridPane.setHalignment(managerButton, HPos.LEFT);
      gridPane.add(cashierButton, 2, 2);
      gridPane.add(managerButton, 2, 2);
      
      // Confirm Button
      confirmButton = new Button("Confirm");
      confirmButton.setId("cash-payment-button");
      confirmButton.setPrefHeight(54);
      confirmButton.setDefaultButton(true);
      confirmButton.setPrefWidth(150);
      gridPane.add(confirmButton, 1, 3, 2, 1);
      GridPane.setHalignment(confirmButton, HPos.CENTER);
      
      confirmButton.setOnAction(Store.getUserController().confirmClick());
      
      // Footer (Logo)
      logo = getLogo(127, 20, 0, 0);
      gridPane.add(logo, 3, 5);
      
      // Cancel Button
      gridPane.add(backButton, 3, 0);
      GridPane.setMargin(backButton, new Insets(20, 20, 205, 0));
      backButton.setOnAction(Store.getUserController().cancelClick());
   }

// ATTRIBUTES USED BY CONTROLLER

    // Username Field
   public TextField getUsername() {
      return usernameField;
   }

    // User Type Toggle Group
   public ToggleGroup getGroup() {
      return group;
   }
}