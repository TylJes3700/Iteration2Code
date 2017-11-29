import javafx.geometry.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
   
public class ItemScreen extends Screen {
      
   private ColumnConstraints column1, column2, column3, column4;
   private Label nameLabel, idLabel, quantityLabel, priceLabel, taxLabel, expDateLabel, unitLabel, contactLabel, phoneDash1, phoneDash2, vendorLabel;
   private TextField nameField, idField, quantityField, priceField, taxField, vendorField, contactField1, contactField2, contactField3;
   private Button confirmButton, loadButton;
   private RadioButton singleButton, ounceButton, poundButton;
   private ToggleGroup group;
   private ChoiceBox<String> expYear, expMonth, expDay;
   private boolean edit;
      
   public ItemScreen(boolean edit) {
      super(edit);
      this.edit = toggle;
   }
      
   protected void setUpPane() {
      edit = toggle;
      gridPane.setVgap(20);
      
      column1 = GUI.getColumn(256, HPos.CENTER);
      column2 = GUI.getColumn(142, HPos.RIGHT);
      column3 = GUI.getColumn(350, HPos.CENTER);
      column4 = GUI.getColumn(276, HPos.CENTER);
      
      gridPane.getColumnConstraints().addAll(column1, column2, column3, column4);
      
      addUIControls();
   }
   
   protected void addUIControls() {
      System.out.println(edit);
      // Header
      headerLabel = GUI.getHeader(edit ? "Edit Item" : "Add Item");
      gridPane.add(headerLabel, 1, 0, 2, 1);
      gridPane.setMargin(headerLabel, new Insets(0, 0, 20, 0));
     
      // Footer (Logo)
      logo = getLogo(30, 20, 0, 0);
      gridPane.add(logo, 3, 8);
      
      // Cancel Button
      gridPane.add(backButton, 3, 0);
      backButton.setOnAction(Store.getAddItemController().cancelClick());
      
      // Name Label
      nameLabel = new Label("Name : ");
      gridPane.add(nameLabel, 1, 1);
      
      // Name Text Field
      nameField = new TextField();
      nameField.setPrefHeight(40);
      gridPane.add(nameField, 2, 1);
      nameField.requestFocus();
      
      // ID Label
      idLabel = new Label("ID : ");
      gridPane.add(idLabel, 1, 2);
      
      // ID Text Field
      idField = GUI.getField("", 80);
      idField.setDisable(true);
      gridPane.add(idField, 2, 2);
      
      // Quantity Label
      quantityLabel = new Label("Quantity : ");
      GridPane.setHalignment(quantityLabel, HPos.RIGHT);
      GridPane.setMargin(quantityLabel, new Insets(0, 80, 0, 0));
      gridPane.add(quantityLabel, 2, 2);
      
      // Quantity Text Field
      quantityField = GUI.getField("0", 80);
      GridPane.setHalignment(quantityField, HPos.RIGHT);
      gridPane.add(quantityField, 2, 2);
      
      // Unit Type Label
      unitLabel = new Label("Unit Type : ");
      gridPane.add(unitLabel, 1, 3);
      
      // Unit Type Radio Button Group
      group = new ToggleGroup();
      singleButton = GUI.getRadioButton("Single", group);
      singleButton.setSelected(true);
      GridPane.setMargin(singleButton, new Insets(0, 0, 0, 20));
      ounceButton = GUI.getRadioButton("Ounce", group);
      GridPane.setMargin(ounceButton, new Insets(0, 0, 0, 130));
      poundButton = GUI.getRadioButton("Pound", group);
      GridPane.setMargin(poundButton, new Insets(0, 0, 0, 240));
      gridPane.setHalignment(singleButton, HPos.LEFT);
      gridPane.setHalignment(ounceButton, HPos.LEFT);
      gridPane.setHalignment(poundButton, HPos.LEFT);
      gridPane.add(singleButton, 2, 3);
      gridPane.add(ounceButton, 2, 3);
      gridPane.add(poundButton, 2, 3);
      
      // Price Label
      priceLabel = new Label("Price ($) : ");
      gridPane.add(priceLabel, 1, 4);
      
      // Price Text Field
      priceField = GUI.getField("", 120);
      gridPane.add(priceField, 2, 4);
      
      // Tax Label
      taxLabel = new Label("Tax (%) : ");
      gridPane.add(taxLabel, 2, 4);
      GridPane.setMargin(taxLabel, new Insets(0, 80, 0, 0));
      GridPane.setHalignment(taxLabel, HPos.RIGHT);
      
      // Tax Text Field
      taxField = GUI.getField("9", 80);
      gridPane.add(taxField, 2, 4);
      GridPane.setHalignment(taxField, HPos.RIGHT);
      
      // Expiration Date Label
      expDateLabel = new Label("Exp. Date : ");
      gridPane.add(expDateLabel, 1, 5);
      
      // Expiration Date Drop Downs
      expMonth = GUI.getMonthBox();
      expDay = GUI.getDayBox();
      expYear = GUI.getYearBox();
      GridPane.setMargin(expDay, new Insets(0, 0, 0, 100));
      GridPane.setMargin(expYear, new Insets(0, 0, 0, 200));
      gridPane.add(expMonth, 2, 5);
      gridPane.add(expDay, 2, 5);
      gridPane.add(expYear, 2, 5);
      
      // Vendor Label
      vendorLabel = new Label("Vendor : ");
      gridPane.add(vendorLabel, 1, 6);
      
      // Vendor Text Field
      vendorField = new TextField();
      vendorField.setPrefHeight(40);
      gridPane.add(vendorField, 2, 6);
      
      // Contact Label
      contactLabel = new Label("Contact # : ");
      gridPane.add(contactLabel, 1, 7);
      
      // Contact Text Field
      contactField1 = GUI.getContactField1();
      gridPane.add(contactField1, 2, 7);
      
      phoneDash1 = GUI.getDash(60);
      gridPane.add(phoneDash1, 2, 7);
      
      contactField2 = GUI.getContactField2();
      gridPane.add(contactField2, 2, 7);
      
      phoneDash2 = GUI.getDash(140);
      gridPane.add(phoneDash2, 2, 7);
      
      contactField3 = GUI.getContactField3();
      gridPane.add(contactField3, 2, 7);
      
      // Confirm Button
      confirmButton = GUI.getConfirmButton("Confirm");
      gridPane.add(confirmButton, 1, 8, 2, 1);
      confirmButton.setOnAction(edit ? Store.getEditItemController().confirmClick() : Store.getAddItemController().confirmClick());
      
      // Load Button
      loadButton = GUI.getConfirmButton("Load");
      gridPane.add(loadButton, 1, 8, 2, 1);
      if (edit) {
         loadButton.setOnAction(Store.getEditItemController().loadClick());
      }
   }
  
// ATTRIBUTES USED BY CONTROLLER
  
    // Header Label
   public Label getHeader() {
      return headerLabel;
   }
  
    // Name Field
   public TextField getName() {
      return nameField;
   }
   
   // ID Field
   public TextField getID() {
      return idField;
   }
   
   // Quantity Field
   public TextField getQuantity() {
      return quantityField;
   }
   
   // Price Field
   public TextField getPrice() {
      return priceField;
   }   
   
   // Tax Field
   public TextField getTax() {
      return taxField;
   }   
   
   // Vendor Field
   public TextField getVendor() {
      return vendorField;
   }   
   
   // Contact Field 1
   public TextField getContact1() {
      return contactField1;
   }  
   
   // Contact Field 2
   public TextField getContact2() {
      return contactField2;
   }   
   
   // Contact Field 3
   public TextField getContact3() {
      return contactField3;
   }   
   
   // Expiration Day ChoiceBox
   public ChoiceBox<String> getExpDay() {
      return expDay;
   }   
   
   // Expiration Month ChoiceBox
   public ChoiceBox<String> getExpMonth() {
      return expMonth;
   }   
   
   // Expiration Year ChoiceBox
   public ChoiceBox<String> getExpYear() {
      return expYear;
   }
   
   // Unit Type Toggle Group
   public ToggleGroup getGroup() {
      return group;
   }

    // Ounce Radio Button
   public RadioButton getOunce() {
      return ounceButton;
   }
    
    // Pound Radio Button
   public RadioButton getPound() {
      return poundButton;
   }
   
    // Single Radio Button
   public RadioButton getSingle() {
      return singleButton;
   }
   
   // Confirm Button
   public Button getConfirm() {
      return confirmButton;
   }
   
   // Load Button
   public Button getLoad() {
      return loadButton;
   }
}