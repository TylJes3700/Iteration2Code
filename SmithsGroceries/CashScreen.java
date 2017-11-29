import javafx.geometry.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
   
public class CashScreen extends Screen {

   private ColumnConstraints column1, column2, column3, column4;
   private Label totalLabel, totalValueLabel, paymentLabel, changeLabel, changeValueLabel;
   private TextField paymentField;
   private Button confirmButton, endButton;
      
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
      headerLabel = GUI.getHeader("Cash Payment");
      gridPane.add(headerLabel, 1, 0, 2, 1);
      
      // Total Label
      totalLabel = new Label("Total : ");
      totalLabel.setId("cash-payment-label");
      gridPane.add(totalLabel, 1, 1);
      GridPane.setHalignment(totalLabel, HPos.RIGHT);
      
      // Total Value Label
      totalValueLabel = new Label("$ " + GUI.formatter.format(Store.getCheckoutController().getTotal()));
      totalValueLabel.setId("cash-payment-label");
      gridPane.add(totalValueLabel, 2, 1);
      GridPane.setHalignment(totalValueLabel, HPos.LEFT);
      
      // Payment Label
      paymentLabel = new Label("Payment : ");
      paymentLabel.setId("cash-payment-label");
      gridPane.add(paymentLabel, 1, 2);
      GridPane.setHalignment(paymentLabel, HPos.RIGHT);
      
      // Payment Field
      paymentField = GUI.getField("", 120);
      gridPane.add(paymentField, 2, 2);
      
      // Change Label
      changeLabel = new Label("Change : ");
      changeLabel.setId("cash-payment-label");
      gridPane.add(changeLabel, 1, 3);
      GridPane.setHalignment(changeLabel, HPos.RIGHT);
      
      // Change Value Label
      changeValueLabel = new Label();
      changeValueLabel.setId("cash-payment-label");
      gridPane.add(changeValueLabel, 2, 3);
      GridPane.setHalignment(changeValueLabel, HPos.LEFT);
      
      // Confirm Button
      confirmButton = new Button("Confirm");
      confirmButton.setId("cash-payment-button");
      confirmButton.setPrefHeight(54);
      confirmButton.setDefaultButton(true);
      confirmButton.setPrefWidth(150);
      gridPane.add(confirmButton, 1, 4, 2, 1);
      GridPane.setHalignment(confirmButton, HPos.CENTER);
      
      confirmButton.setOnAction(Store.getCashController().confirmClick());
      
      // End Transaction Button
      endButton = new Button("End Transaction");
      endButton.setId("cash-payment-button");
      endButton.setPrefHeight(54);
      endButton.setDefaultButton(true);
      endButton.setPrefWidth(200);
      endButton.setVisible(false);
      endButton.setDisable(true);
      gridPane.add(endButton, 1, 4, 2, 1);
      GridPane.setHalignment(endButton, HPos.CENTER);
         
      endButton.setOnAction(Store.getCashController().endClick());
      
      // Footer (Logo)
      logo = getLogo(91, 20, 0, 0);
      gridPane.add(logo, 3, 5);
      
      // Cancel Button
      gridPane.add(backButton, 3, 0);
      GridPane.setMargin(backButton, new Insets(20, 20, 205, 0));
      backButton.setOnAction(Store.getCashController().cancelClick());
   }

// ATTRIBUTES USED BY CONTROLLER

    // Payment Field   
   public TextField getPayment() {
      return paymentField;
   }
 
    // Change Label  
   public Label getChange() {
      return changeValueLabel;
   }
   
    // Total Label
   public Label getTotal() {
      return totalValueLabel;
   }

    // End Button
   public Button getEndButton() {
      return endButton;
   }
 
    // Confirm Button  
   public Button getConfirmButton() {
      return confirmButton;
   }
}