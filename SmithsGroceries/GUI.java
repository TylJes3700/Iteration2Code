import java.text.DecimalFormat;
import javafx.geometry.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.event.*;
import javafx.collections.*;
import javafx.stage.*;
import java.util.*;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class GUI {

   private static List<GridPane> grid = new ArrayList<GridPane>();
   private static int currentScreen = 0;
   private static int screenWidth = 1024, screenHeight = 768;
   private static String cssFile = "smithsgroceries.css";
   private static String icon = "/images/Smith's Groceries Icon.png";
   private static String title = "Smith's Groceries";

   public static int loginNum = 0, managerNum = 1, checkoutNum = 2, addItemNum = 3, editItemNum = 4, lookUpNum = 5, payNum = 6, cashNum = 7, cardNum = 8, settingsNum = 9, passNum = 10, picNum = 11, userNum = 12, reportNum = 13;
   public static DecimalFormat formatter = new DecimalFormat("###,###,##0.00");
   public static DecimalFormat formatter2 = new DecimalFormat("0.00");
   private static DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

    // Add screens to screen list
   public static void loadScreens() {
      grid.add(Store.getLoginController().getScreen().getPane());    // 0 Login
      grid.add(Store.getManagerController().getScreen().getPane());  // 1 Manager Menu
      grid.add(Store.getCheckoutController().getScreen().getPane()); // 2 Checkout
      grid.add(Store.getAddItemController().getScreen().getPane());  // 3 Add Item
      grid.add(Store.getEditItemController().getScreen().getPane()); // 4 Edit Item
      grid.add(Store.getLookUpController().getScreen().getPane());   // 5 Look Up Item
      grid.add(Store.getPayController().getScreen().getPane());      // 6 Payment Menu
      grid.add(Store.getCashController().getScreen().getPane());     // 7 Cash
      grid.add(Store.getCardController().getScreen().getPane());     // 8 Card (Debit / Credit)
      grid.add(Store.getSettingsController().getScreen().getPane()); // 9 Settings (password/pic)
      grid.add(Store.getPasswordController().getScreen().getPane()); // 10 Change Password
      grid.add(Store.getPicController().getScreen().getPane());      // 11 Change Profile Picture
      grid.add(Store.getUserController().getScreen().getPane());     // 12 Add User
      grid.add(Store.getReportController().getScreen().getPane());   // 13 Business Report  
   }

    // Gets login screen ready
   public static void initializeScreen() {
      Store.getRoot().getChildren().add(grid.get(0));
   }

    // Switch screens to designated one
   public static void setPane(int newScreen) {
      Store.getRoot().getChildren().remove(grid.get(currentScreen));
      Store.getRoot().getChildren().add(grid.get(newScreen));
      currentScreen = newScreen;
   }

   public static String getCSS() {
      return cssFile;
   }

   public static String getIcon() {
      return icon;
   }

   public static String getTitle() {
      return title;
   }

   public static int getWidth() {
      return screenWidth;
   }
   
   public static int getHeight() {
      return screenHeight;
   }

    // Returns switch screen action handler to reduce number of anonymous classes
   public static EventHandler<ActionEvent> changeScreen(int newPane) {
      return 
         new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
               setPane(newPane);
               if (newPane == loginNum && !Store.dataAdapter.isManager()) {
                  Store.getCheckoutController().clearScroll();
               }
            }
         };
   }

    // Changes user pic to designated one and updates welcomes
   public static EventHandler<ActionEvent> changePic(int newPic) {
      return 
         new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
               Store.dataAdapter.setPic(newPic);
               Store.dataAdapter.savePic();
               setPane(settingsNum);
               changeWelcome();
            }
         };
   }

    // Returns %-width column properties
   public static ColumnConstraints getColumn(double width, HPos position) {
      ColumnConstraints column = new ColumnConstraints();
      column.setPercentWidth(width);
      column.setHalignment(position);
      return column;
   }
   
    // Returns pixel-width column properties
   public static ColumnConstraints getColumn(int width, HPos position) {
      ColumnConstraints column = new ColumnConstraints();
      column.setMinWidth(width);
      column.setMaxWidth(width);
      column.setPrefWidth(width);
      column.setHalignment(position);
      return column;
   }
   
    // Returns standard confirm/submit/continue button, centered, top-aligned (100 x 40)
   public static Button getConfirmButton(String label) {
      Button confirmButton = new Button(label);
      confirmButton.setId("confirm-button");
      confirmButton.setPrefHeight(40);
      confirmButton.setDefaultButton(true);
      confirmButton.setPrefWidth(100);
      GridPane.setValignment(confirmButton, VPos.TOP);
      GridPane.setHalignment(confirmButton, HPos.CENTER);
      return confirmButton;
   }
   
    // Returns standard cancel/logout/back button, right-aligned (128 x 54) (inset 20, 20, 108, 0)
   public static Button getBackButton(String label) {
      Button cancelButton = new Button(label);
      cancelButton.setId("cancel-button");
      cancelButton.setPrefHeight(54);
      cancelButton.setPrefWidth(128);
      cancelButton.setCancelButton(true);
      GridPane.setHalignment(cancelButton, HPos.RIGHT);
      GridPane.setMargin(cancelButton, new Insets(20, 20, 108, 0));
      return cancelButton;
   }
   
    // Returns medium-size button, centered (236 x 108) (inset 20, 20, 0, 0)
   public static Button getMediumButton(String label) {
      Button medium = new Button(label);
      medium.setPrefHeight(108);
      medium.setPrefWidth(236);
      GridPane.setHalignment(medium, HPos.CENTER);
      GridPane.setMargin(medium, new Insets(20, 20, 0, 0));
      return medium;
   }
   
    // Returns large button, centered (512 x 108)
   public static Button getBigButton(String label) {
      Button big = new Button(label);
      big.setPrefHeight(108);
      big.setPrefWidth(512);
      GridPane.setHalignment(big, HPos.CENTER);
      return big;
   }
    // Returns radio button placed within Toggle Group (h: 20)
   public static RadioButton getRadioButton(String label, ToggleGroup group) {
      RadioButton button = new RadioButton(label);
      button.setToggleGroup(group);
      button.setPrefHeight(20);
      return button;
   }
    
    // Returns logo, right-aligned, bottom-aligned (236 x 112)
   public static ImageView getLogo() {
      Image image = new Image("/images/Smith's Groceries Logo.png");
      ImageView logo = new ImageView(image);
      logo.setFitHeight(112);
      logo.setFitWidth(236);
      GridPane.setHalignment(logo, HPos.RIGHT);
      GridPane.setValignment(logo, VPos.BOTTOM);
      return logo;
   }
   
   // Returns empty profile pic, left-aligned, top-aligned (100 x 100)
   public static ImageView getPic() {
      ImageView pic = new ImageView();
      pic.setFitHeight(100);
      pic.setFitWidth(100);
      GridPane.setMargin(pic, new Insets(20, 0, 0, 20));
      GridPane.setHalignment(pic, HPos.LEFT);
      GridPane.setValignment(pic, VPos.TOP);
      return pic;
   }
   
    // Returns screen header, centered (fontsize 40px)
   public static Label getHeader(String title) {
      Label header = new Label(title);
      header.setId("header");
      GridPane.setHalignment(header, HPos.CENTER);
      return header;
   }
   
    // Returns text-centered, left-aligned field (h: 40)
   public static TextField getField(String label, int width) {
      TextField field = new TextField(label);
      field.setPrefHeight(40);
      field.setAlignment(Pos.CENTER);
      GridPane.setHalignment(field, HPos.LEFT);
      field.setMaxWidth(width);
      return field;
   }
   
    // Returns 3-digit-length, text-centered, left-aligned field (60 x 40)
   public static TextField getContactField1() {
      return getField("", 60);
   }
   
    // Returns 3-digit-length, text-centered, left-aligned field (60 x 40) (inset 0, 0, 0, 80)
   public static TextField getContactField2() {
      TextField contact2 = getField("", 60);
      GridPane.setMargin(contact2, new Insets(0, 0, 0, 80));
      return contact2;
   }
   
    // Returns 4-digit-length, left-aligned field (70 x 40) (inset 0, 0, 0, 160)
   public static TextField getContactField3() {
      TextField contact3 = getField("", 70);
      GridPane.setMargin(contact3, new Insets(0, 0, 0, 160));
      return contact3;
   }
   
    // Returns dash, left-aligned, inset on left as indicated
   public static Label getDash(int indent) {
      Label dash = new Label(" - ");
      GridPane.setHalignment(dash, HPos.LEFT);
      GridPane.setMargin(dash, new Insets(0, 0, 0, indent));
      return dash;
   }
   
    // Returns year drop-down, left-aligned, (w: 100)
   public static ChoiceBox<String> getYearBox() {
      ChoiceBox<String> expYear = new ChoiceBox<>(FXCollections.observableArrayList("YYYY", "2017", "2018", "2019", "2020", "2021", "2022", "2023", "2024", "2025"));
      expYear.getSelectionModel().selectFirst();
      GridPane.setHalignment(expYear, HPos.LEFT);
      expYear.setMinWidth(100);
      return expYear;
   }
   
    // Returns month drop-down, left-aligned, (w: 80)
   public static ChoiceBox<String> getMonthBox() {
      ChoiceBox<String> expMonth = new ChoiceBox<>(FXCollections.observableArrayList("MM", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"));
      expMonth.getSelectionModel().selectFirst();
      GridPane.setHalignment(expMonth, HPos.LEFT);
      expMonth.setMinWidth(80);
      return expMonth;
   }
   
    // Returns day drop-down, left-aligned, (w: 80)
   public static ChoiceBox<String> getDayBox() {
      ChoiceBox<String> expDay = new ChoiceBox<>(FXCollections.observableArrayList("DD", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"));
      expDay.getSelectionModel().selectFirst();
      GridPane.setHalignment(expDay, HPos.LEFT);
      expDay.setMinWidth(80);
      return expDay;
   }
   
   // Returns state drop-down, left-aligned, (w: 80)
   public static ChoiceBox<String> getStateBox() {
      ChoiceBox<String> stateChoice = new ChoiceBox<>(FXCollections.observableArrayList("--", "AL", "AK", "AZ", "AR", "CA", "CO", "CT", "DE", "FL", "GA", "HI", "ID", "IL", "IN", "IA", "KS", "KY", "LA", "ME", "MD", "MA", "MI", "MN", "MS", "MO", "MT", "NE", "NV", "NH", "NJ", "NM", "NY", "NC", "ND", "OH", "OK", "OR", "PA", "RI", "SC", "SD", "TN", "TX", "UT", "VT", "VA", "WA", "WV", "WI", "WY", "GU", "PR", "VI"));
      stateChoice.getSelectionModel().selectFirst();
      stateChoice.setMinWidth(80);
      return stateChoice;
   }
   
   // Returns welcome label with empty text
   public static Label getWelcome() {
      Label welcomeLabel = new Label();
      welcomeLabel.setId("welcome-label");
      welcomeLabel.setPrefHeight(54);
      GridPane.setHalignment(welcomeLabel, HPos.LEFT);
      GridPane.setValignment(welcomeLabel, VPos.TOP);
      GridPane.setMargin(welcomeLabel, new Insets(120, 0, 0, 20));
      return welcomeLabel;
   }
   
   // Change name on welcome messages (for potentially multiple screens)
   public static void changeWelcome(){
      if (Store.dataAdapter.isManager()) {
         Store.getManagerController().changeWelcome();
         Store.getAddItemController().changeWelcome();
         Store.getEditItemController().changeWelcome();
         Store.getUserController().changeWelcome();
         Store.getReportController().changeWelcome();
      }
      else {
         Store.getCheckoutController().changeWelcome();
         Store.getLookUpController().changeWelcome();
         Store.getPayController().changeWelcome();
         Store.getCashController().changeWelcome();
         Store.getCardController().changeWelcome();
      }
      Store.getSettingsController().changeWelcome();
      Store.getPasswordController().changeWelcome();
      Store.getPicController().changeWelcome();
   }
      
    // Return current date formatted as yyyy/MM/dd
   public static String getDate() {
      return dateFormat.format(new Date()).toString().substring(0,10);
   }
   
   // Return current time formatted as HH:mm:ss
   public static String getTime() {
      return dateFormat.format(new Date()).toString().substring(11,19);
   }
           
    // Displays pop-up message with indicated alert type, title, and message
   public static void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
      Alert alert = new Alert(alertType);
      alert.setTitle(title);
      alert.setHeaderText(null);
      alert.setContentText(message);
      alert.initOwner(owner);
      alert.show();
   }
   
   public static void testOutput(Product product) {
      System.out.println("\nID: " + product.getID());
      System.out.println("Name: " + product.getName());
      System.out.println("Unit: " + product.getUnit());
      System.out.println("Price: " + product.getPrice());
      System.out.println("Tax: " + product.getTax());
      System.out.println("Quantity: " + product.getQuantity());
      System.out.println("ExpDate: " + product.getExpDate());
      System.out.println("Vendor: " + product.getVendor());
      System.out.println("Contact: " + product.getContact());
   }
   
   public static void testOutput(OrderDetail detail) {
      System.out.println("\nID: " + detail.getID());
      System.out.println("OrderID: " + detail.getOrderID());
      System.out.println("ProductID: " + detail.getProductID());
      System.out.println("Quantity: " + detail.getQuantity());
   }

}