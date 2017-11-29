import javafx.geometry.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.image.*;
   
public class LoginScreen extends Screen {
      
   private ColumnConstraints column1, column2, column3, column4;
   private Label userLabel, passwordLabel;
   private TextField userField;
   private PasswordField passwordField;
   private Button submitButton;
   private Image image;
   private ImageView bigLogo;
      
   protected void setUpPane() {
      gridPane.setHgap(20);
      gridPane.setVgap(20);
      
      column1 = GUI.getColumn(256, HPos.CENTER); 
      column2 = GUI.getColumn(226, HPos.RIGHT);  
      column3 = GUI.getColumn(226, HPos.LEFT);
      column4 = GUI.getColumn(256, HPos.CENTER);
      
      gridPane.getColumnConstraints().addAll(column1, column2, column3, column4);
      
      addUIControls();
   }
   
   protected void addUIControls() {
      
      // Header (Logo)
      image = new Image("/images/Smith's Groceries Text Logo.png");
      bigLogo = new ImageView(image);
      bigLogo.setFitHeight(118);
      bigLogo.setFitWidth(951);
      gridPane.add(bigLogo, 0, 0, 4, 1);
      gridPane.setHalignment(bigLogo, HPos.CENTER);
      gridPane.setValignment(bigLogo, VPos.CENTER);
      gridPane.setMargin(bigLogo, new Insets(40, 0, 55, 0));
      
      // Header Text
      headerLabel = GUI.getHeader("Login");
      gridPane.add(headerLabel, 1, 1, 2, 1);
      gridPane.setMargin(headerLabel, new Insets(0, 0, 20, 0));
      
      // Username Label
      userLabel = new Label("Username :");
      userLabel.setId("cash-payment-label");
      GridPane.setHalignment(userLabel, HPos.RIGHT);
      gridPane.add(userLabel, 1, 2);
      
      // Username Text Field
      userField = GUI.getField("", 200);
      gridPane.add(userField, 2, 2);
      
      // Password Label
      passwordLabel = new Label("Password :");
      passwordLabel.setId("cash-payment-label");
      GridPane.setHalignment(passwordLabel, HPos.RIGHT);
      gridPane.add(passwordLabel, 1, 4);
      
      // Password Field
      passwordField = new PasswordField();
      passwordField.setPrefHeight(40);
      passwordField.setMaxWidth(200);
      passwordField.setAlignment(Pos.CENTER);
      GridPane.setHalignment(passwordField, HPos.LEFT);
      gridPane.add(passwordField, 2, 4);
      
      // Submit Button
      submitButton = GUI.getConfirmButton("Submit");
      gridPane.setMargin(submitButton, new Insets(20, 0, 0, 0));
      gridPane.add(submitButton, 1, 5, 2, 1);
      submitButton.setOnAction(Store.getLoginController().submit());
   }
 
// ATTRIBUTES USED BY CONTROLLER
 
    // User Field  
   public TextField getUser() {
      return userField;
   }

    // Password Field   
   public PasswordField getPass() {
      return passwordField;
   }
}