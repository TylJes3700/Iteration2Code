import javafx.application.Application;
import javafx.stage.*;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.image.*;
import javafx.scene.text.*;
import java.sql.*;
import java.util.*;

public class Store extends Application {

   private static AnchorPane root;
   private static Connection connection;
   private static Scene scene;
   
   // Screen Controllers
   private static Controller login_c, manager_c, checkout_c, addItem_c, editItem_c, lookUp_c, pay_c, cash_c, card_c, settings_c, pass_c, pic_c, user_c, report_c;
   
   public static DataAdapter dataAdapter;

    // Establish Database Connection and Setup Screens/Controllers
   public void start(Stage primaryStage) throws Exception {
   
   // create SQLite database connection
      try {
         Class.forName("org.sqlite.JDBC");
         connection = DriverManager.getConnection("jdbc:sqlite:SmithsGroceries.db");
      }
      catch (ClassNotFoundException ex) {
         System.out.println("ERROR: SQLite is not installed");
         System.exit(1);
      }
      
      catch (SQLException ex) {
         System.out.println("ERROR: SQLite database is not ready");
         System.exit(2);
      }
   
      // Create data adapter
      dataAdapter = new DataAdapter(connection);
   
      // Window Icon
      primaryStage.getIcons().add(new Image(GUI.getIcon()));
      
      // Base Screen
      root = new AnchorPane();
      
      // Get controllers
      setUpControllers();
      
      // Load Screens
      GUI.loadScreens();
      
      // Begin on first screen
      GUI.initializeScreen();
      
      // Create scene and set window size
      scene = new Scene(root, GUI.getWidth(), GUI.getHeight());
      
      // Add css stylesheet
      scene.getStylesheets().add(GUI.getCSS());
      
      // Prepare and display program
      primaryStage.setScene(scene);
      primaryStage.setTitle(GUI.getTitle());
      primaryStage.setResizable(false);
      primaryStage.show();
   }
   
    // Return root anchor pane
   public static AnchorPane getRoot() {
      return root;
   }

   public static void main(String[] args) {
      launch(args);
   }
   
   @Override
   public void init() throws Exception {
      super.init();
      // Perform necessary initializations
   }
   
   @Override
   public void stop() throws Exception {
      super.stop();
      //Destroy Resources / Perform Cleanup
   }

    // Create controllers and set appropriate screens
   private static void setUpControllers() {
      login_c = new LoginController();
      login_c.setScreen(new LoginScreen());
      manager_c = new ManagerController();
      manager_c.setScreen(new ManagerScreen());
      checkout_c = new CheckoutController();
      checkout_c.setScreen(new CheckoutScreen());
      addItem_c = new ItemController();
      addItem_c.setScreen(new ItemScreen(false));
      editItem_c = new ItemController();
      editItem_c.setScreen(new ItemScreen(true));
      lookUp_c = new LookUpController();
      lookUp_c.setScreen(new LookUpScreen());
      pay_c = new PayController();
      pay_c.setScreen(new PayScreen());
      cash_c = new CashController();
      cash_c.setScreen(new CashScreen());
      card_c = new CardController();
      card_c.setScreen(new CardScreen());
      settings_c = new SettingsController();
      settings_c.setScreen(new SettingsScreen());
      pass_c = new PasswordController();
      pass_c.setScreen(new PasswordScreen());
      pic_c = new PicController();
      pic_c.setScreen(new PicScreen());
      user_c = new UserController();
      user_c.setScreen(new UserScreen());
      report_c = new ReportController();
      report_c.setScreen(new ReportScreen());
      ((ReportController)report_c).updateScroll();
   }

// CONTROLLER GET METHODS

    // Login
   public static LoginController getLoginController() {
      return (LoginController)login_c;
   }
   
    // Manager Menu
   public static ManagerController getManagerController() {
      return (ManagerController)manager_c;
   }
   
    // Checkout
   public static CheckoutController getCheckoutController() {
      return (CheckoutController)checkout_c;
   }
   
    // Item Add
   public static ItemController getAddItemController() {
      return (ItemController)addItem_c;
   }
   
   // Item Edit
   public static ItemController getEditItemController() {
      return (ItemController)editItem_c;
   }
      
    // Look Up Item
   public static LookUpController getLookUpController() {
      return (LookUpController)lookUp_c;
   }
   
    // Pay Menu
   public static PayController getPayController() {
      return (PayController)pay_c;
   }
   
    // Cash Payment
   public static CashController getCashController() {
      return (CashController)cash_c;
   }
   
    // Card Payment
   public static CardController getCardController() {
      return (CardController)card_c;
   }
   
    // Settings
   public static SettingsController getSettingsController() {
      return (SettingsController)settings_c;
   }
   
    // Change Password
   public static PasswordController getPasswordController() {
      return (PasswordController)pass_c;
   }
   
    // Change Profile Pic
   public static PicController getPicController() {
      return (PicController)pic_c;
   }
   
    // Add New User
   public static UserController getUserController() {
      return (UserController)user_c;
   }
   
    // Business Report
   public static ReportController getReportController() {
      return (ReportController)report_c;
   }
}