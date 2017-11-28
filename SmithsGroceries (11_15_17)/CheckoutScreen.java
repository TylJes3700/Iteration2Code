import javafx.geometry.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
   
public class CheckoutScreen extends Screen {
      
   private GridPane scrollContents;
   private ColumnConstraints column1, column2, column3, column4, column5, scrollColumn1, scrollColumn2;
   private Label productLabel, priceLabel, totalLabel, totalValueLabel, idLabel;
   private TextField idField;
   private Button settingsButton, clearButton, addButton, lookUpButton, payButton;
   private ScrollPane productView;
      
   protected void setUpPane() {
      column1 = GUI.getColumn(12.5, HPos.LEFT);
      column2 = GUI.getColumn(25.0, HPos.LEFT);
      column3 = GUI.getColumn(25.0, HPos.LEFT); 
      column4 = GUI.getColumn(12.5, HPos.LEFT);
      column5 = GUI.getColumn(25.0, HPos.RIGHT);
      
      gridPane.getColumnConstraints().addAll(column1, column2, column3, column4, column5);
      
      addUIControls();
   }
   
   protected void addUIControls() {
      
      // Header
      headerLabel = GUI.getHeader("Checkout");
      gridPane.add(headerLabel, 1, 0, 2, 1);
      gridPane.setMargin(headerLabel, new Insets(0, 0, 20, 0));
      
      // Product Label
      productLabel = new Label("                         Product                             ");
      productLabel.setId("checkout-title");
      gridPane.add(productLabel, 1, 1, 2, 1);
      gridPane.setValignment(productLabel, VPos.BOTTOM);
      
      // Price Label
      priceLabel = new Label("      Price      ");
      priceLabel.setId("checkout-title");
      gridPane.add(priceLabel, 2, 1);
      gridPane.setHalignment(priceLabel, HPos.RIGHT);
      gridPane.setValignment(priceLabel, VPos.BOTTOM);
      
      // Footer (Logo)
      logo = getLogo(45, 20, 0, 0);
      gridPane.add(logo, 4, 5);
      
      // Total Label
      totalLabel = new Label(" Total :                               ");
      totalLabel.setId("checkout-total-label");
      gridPane.add(totalLabel, 1, 5, 2, 1);
      gridPane.setHalignment(totalLabel, HPos.RIGHT);
      gridPane.setValignment(totalLabel, VPos.TOP);
      
      // Total Value Label
      totalValueLabel = new Label("$ " + GUI.formatter.format(Store.getCheckoutController().getTotal()));
      totalValueLabel.setId("checkout-total-value-label");
      gridPane.add(totalValueLabel, 1, 5, 2, 1);
      gridPane.setHalignment(totalValueLabel, HPos.RIGHT);
      gridPane.setValignment(totalValueLabel, VPos.TOP);
      gridPane.setMargin(totalValueLabel, new Insets(0, 37, 0, 0));
      
      // Logout Button
      backButton.setText("Logout");
      gridPane.add(backButton, 4, 0);
      backButton.setOnAction(Store.getCheckoutController().cancelClick());
      
      // Settings Button
      settingsButton = GUI.getBackButton("Settings");
      GridPane.setMargin(settingsButton, new Insets(94, 20, 0, 0));
      gridPane.add(settingsButton, 4, 0);
      settingsButton.setOnAction(Store.getCheckoutController().settingsClick());
      
      // Clear Button
      clearButton = GUI.getBackButton("Clear");
      gridPane.add(clearButton, 3, 0, 2, 1);
      GridPane.setMargin(clearButton, new Insets(20, 168, 108, 0));
      clearButton.setOnAction(Store.getCheckoutController().clearClick());
      
      // ID Search
      idLabel = new Label("Product ID");
      idLabel.setId("id-search-label");
      gridPane.add(idLabel, 4, 2);
      GridPane.setMargin(idLabel, new Insets(0, 20, 0, 0));
      GridPane.setHalignment(idLabel, HPos.CENTER);
      GridPane.setValignment(idLabel, VPos.TOP);
      
      // ID Field
      idField = GUI.getField("", 100);
      gridPane.add(idField, 4, 2);
      GridPane.setMargin(idField, new Insets(58, 0, 10, 0));
      GridPane.setValignment(idField, VPos.BOTTOM);
      
      // Add Button
      addButton = GUI.getConfirmButton("Add");
      gridPane.add(addButton, 4, 2);
      GridPane.setMargin(addButton, new Insets(0, 20, 10, 120));
      GridPane.setHalignment(addButton, HPos.LEFT);
      GridPane.setValignment(addButton, VPos.BOTTOM);
      addButton.setOnAction(Store.getCheckoutController().addClick());
      
      // Look Up Button
      lookUpButton = GUI.getMediumButton("Look Up");
      gridPane.add(lookUpButton, 4, 3);
      lookUpButton.setOnAction(Store.getCheckoutController().lookUpClick());
      
      // Pay Button
      payButton = GUI.getMediumButton("Pay");
      gridPane.add(payButton, 4, 4);
      payButton.setOnAction(Store.getCheckoutController().payClick());
         
      // Product ScrollPane
      productView = new ScrollPane();
      productView.setPrefViewportHeight(364);
      gridPane.add(productView, 1, 2, 2, 3);
      gridPane.setHgap(20);
      scrollContents = new GridPane();
      scrollColumn1 = GUI.getColumn(374, HPos.CENTER);
      scrollColumn2 = GUI.getColumn(100, HPos.RIGHT);
      scrollContents.getColumnConstraints().addAll(scrollColumn1, scrollColumn2);
      productView.setContent(scrollContents);
   }

// ATTRIBUTES USED BY CONTROLLER

    // Scrolling GridPane   
   public GridPane getScroll() {
      return scrollContents;
   }

    // Total Label
   public Label getTotal() {
      return totalValueLabel;
   }

    // ID Field
   public TextField getID() {
      return idField;
   }
}