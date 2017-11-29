import javafx.geometry.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
   
public class CardScreen extends Screen {
      
   private ColumnConstraints column1, column2, column3, column4;
   private Label cardNumLabel, cardDash1, cardDash2, cardDash3, expDateLabel, cscLabel, firstNameLabel, lastNameLabel, addressLabel, cityLabel, stateLabel, zipLabel, pinLabel;
   private TextField cardField1, cardField2, cardField3, cardField4, cscField, firstNameField, lastNameField, addressField, cityField, zipField, pinField, contactField1, contactField2, contactField3;
   private ChoiceBox<String> expMonth, expYear, stateChoice;
      
   protected void setUpPane() {
      gridPane.setVgap(20);
      
      column1 = GUI.getColumn(256, HPos.CENTER);
      column2 = GUI.getColumn(142, HPos.RIGHT);
      column3 = GUI.getColumn(350, HPos.LEFT);
      column4 = GUI.getColumn(276, HPos.CENTER);
      
      gridPane.getColumnConstraints().addAll(column1, column2, column3, column4);
      
      addUIControls();
   }
   
   protected void addUIControls() {
      
      // Header
      headerLabel = GUI.getHeader("Card Payment");
      gridPane.add(headerLabel, 1, 0, 2, 1);
      
      // Card # Label
      cardNumLabel = new Label("Card # : ");
      gridPane.add(cardNumLabel, 1, 1);
      
      // Card # Fields
      cardField1 = GUI.getContactField3();
      gridPane.add(cardField1, 2, 1);
      GridPane.setMargin(cardField1, new Insets(0, 0, 0, 0));
      
      cardDash1 = GUI.getDash(70);
      gridPane.add(cardDash1, 2, 1);
      
      cardField2 = GUI.getContactField3();
      GridPane.setMargin(cardField2, new Insets(0, 0, 0, 90));
      gridPane.add(cardField2, 2, 1);
      
      cardDash2 = GUI.getDash(160);
      gridPane.add(cardDash2, 2, 1);
      
      cardField3 = GUI.getContactField3();
      GridPane.setMargin(cardField3, new Insets(0, 0, 0, 180));
      gridPane.add(cardField3, 2, 1);
      
      cardDash3 = GUI.getDash(250);
      gridPane.add(cardDash3, 2, 1);
      
      cardField4 = GUI.getContactField3();
      GridPane.setMargin(cardField4, new Insets(0, 0, 0, 270));
      gridPane.add(cardField4, 2, 1);
      
      // Expiration Date Label
      expDateLabel = new Label("Exp. Date : ");
      gridPane.add(expDateLabel, 1, 2);
      
      // Expiration Date Drop-Down
      expMonth = GUI.getMonthBox();
      expYear = GUI.getYearBox();
      GridPane.setMargin(expYear, new Insets(0, 0, 0, 100));
      gridPane.add(expMonth, 2, 2);
      gridPane.add(expYear, 2, 2);
      
      // CSC Label
      cscLabel = new Label("CSC : ");
      gridPane.setHalignment(cscLabel, HPos.RIGHT);
      GridPane.setMargin(cscLabel, new Insets(0, 60, 0, 0));
      gridPane.add(cscLabel, 2, 2);
      
      // CSC Field
      cscField = GUI.getContactField1();
      gridPane.setHalignment(cscField, HPos.RIGHT);
      gridPane.add(cscField, 2, 2);
      
      // First Name Label
      firstNameLabel = new Label("First Name : ");
      gridPane.add(firstNameLabel, 1, 3);
      
      // First Name Field
      firstNameField = new TextField();
      firstNameField.setPrefHeight(40);
      gridPane.add(firstNameField, 2, 3);
      
      // Last Name Label
      lastNameLabel = new Label("Last Name : ");
      gridPane.add(lastNameLabel, 1, 4);
      
      // Last Name Field
      lastNameField = new TextField();
      lastNameField.setPrefHeight(40);
      gridPane.add(lastNameField, 2, 4);
      
      // Address Label
      addressLabel = new Label("Address : ");
      gridPane.add(addressLabel, 1, 5);
      
      // Address Field
      addressField = new TextField();
      addressField.setPrefHeight(40);
      gridPane.add(addressField, 2, 5);
      
      // City Label
      cityLabel = new Label("City : ");
      gridPane.add(cityLabel, 1, 6);
      
      // City Field
      cityField = new TextField();
      cityField.setPrefHeight(40);
      gridPane.setHalignment(cityField, HPos.LEFT);
      cityField.setMaxWidth(160);
      gridPane.add(cityField, 2, 6);
      
      // State Label
      stateLabel = new Label("State : ");
      gridPane.setHalignment(stateLabel, HPos.RIGHT);
      GridPane.setMargin(stateLabel, new Insets(0, 80, 0, 0));
      gridPane.add(stateLabel, 2, 6);
      
      // State Drop-Down
      stateChoice = GUI.getStateBox();
      gridPane.setHalignment(stateChoice, HPos.RIGHT);
      gridPane.add(stateChoice, 2, 6);
      
      // ZipCode Label
      zipLabel = new Label("Zip Code : ");
      gridPane.add(zipLabel, 1, 7);
      
      // ZipCode Field
      zipField = GUI.getField("", 80);
      gridPane.add(zipField, 2, 7);
      
      // Pin # Label
      pinLabel = new Label("PIN : ");
      gridPane.setHalignment(pinLabel, HPos.RIGHT);
      GridPane.setMargin(pinLabel, new Insets(0, 70, 0, 0));
      gridPane.add(pinLabel, 2, 7);
      
      // Pin # Field
      pinField = GUI.getField("", 70);
      gridPane.setHalignment(pinField, HPos.RIGHT);
      gridPane.add(pinField, 2, 7);
      
      // Contact Label
      Label contactLabel = new Label("Contact # : ");
      gridPane.add(contactLabel, 1, 8);
      
      // Contact Text Field
      contactField1 = GUI.getContactField1();
      gridPane.add(contactField1, 2, 8);
      
      Label phoneDash1 = GUI.getDash(60);
      gridPane.add(phoneDash1, 2, 8);
      
      contactField2 = GUI.getContactField2();
      gridPane.add(contactField2, 2, 8);
      
      Label phoneDash2 = GUI.getDash(140);
      gridPane.add(phoneDash2, 2, 8);
      
      contactField3 = GUI.getContactField3();
      gridPane.add(contactField3, 2, 8);
      
      // Footer (Logo)
      logo = getLogo(4, 20, 0, 0);
      gridPane.add(logo, 3, 9);
      
      // Cancel Button
      GridPane.setMargin(backButton, new Insets(20, 20, 58, 0));
      gridPane.add(backButton, 3, 0);
      backButton.setOnAction(Store.getCardController().cancelClick());
      
      // Confirm Button
      Button confirmButton = GUI.getConfirmButton("Confirm");
      gridPane.add(confirmButton, 1, 9, 2, 1);
      confirmButton.setOnAction(Store.getCardController().confirmClick());
   }
   
// ATTRIBUTES USED BY CONTROLLER
    
    // Card Field 1
   public TextField getCard1() {
      return cardField1;
   }  
    
    // Card Field 2
   public TextField getCard2() {
      return cardField2;
   }  
    
    // Card Field 3
   public TextField getCard3() {
      return cardField3;
   }  
  
    // Card Field 4
   public TextField getCard4() {
      return cardField4;
   }  
   
    // Expiration Month ChoiceBox
   public ChoiceBox<String> getExpMonth() {
      return expMonth;
   }   
   
    // Expiration Year ChoiceBox
   public ChoiceBox<String> getExpYear() {
      return expYear;
   }
   
    // State ChoiceBox
   public ChoiceBox<String> getState() {
      return stateChoice;
   }
   
    // CSC Field
   public TextField getCsc() {
      return cscField;
   }
   
    // First Name Field
   public TextField getFirstName() {
      return firstNameField;
   }
   
    // Last Name Field
   public TextField getLastName() {
      return lastNameField;
   }
   
    // Address Field
   public TextField getAddress() {
      return addressField;
   }
   
    // City Field
   public TextField getCity() {
      return cityField;
   }
   
    // Zip Code Field
   public TextField getZip() {
      return zipField;
   }
   
    // PIN Field
   public TextField getPin() {
      return pinField;
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
}