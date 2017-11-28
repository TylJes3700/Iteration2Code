import javafx.geometry.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
   
public class PayScreen extends Screen {
   
   private ColumnConstraints column1, column2, column3;
   private Button cashButton, debitButton, creditButton;
      
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
      headerLabel = GUI.getHeader("Payment Method");
      gridPane.add(headerLabel, 1, 0);
      
      // Footer (Logo)
      logo = GUI.getLogo();
      gridPane.add(logo, 2, 4);
      gridPane.setMargin(logo, new Insets(40, 20, 0, 0));
      
      // Cancel Button
      GridPane.setMargin(backButton, new Insets(20, 20, 98, 0));
      gridPane.add(backButton, 2, 0);
      backButton.setOnAction(Store.getPayController().cancelClick());
      
      // Cash Button
      cashButton = GUI.getBigButton("Cash");
      gridPane.add(cashButton, 1, 1);
      cashButton.setOnAction(Store.getPayController().cashClick());
      
      // Debit Button
      debitButton = GUI.getBigButton("Debit");
      gridPane.add(debitButton, 1, 2);
      GridPane.setMargin(debitButton, new Insets(10, 0, 10, 0));
      debitButton.setOnAction(Store.getPayController().debitClick());
      
      // Credit Button
      creditButton = GUI.getBigButton("Credit");
      gridPane.add(creditButton, 1, 3);
      creditButton.setOnAction(Store.getPayController().creditClick());
   }

// ATTRIBUTES USED BY CONTROLLER
}