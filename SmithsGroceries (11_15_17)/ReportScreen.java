import javafx.geometry.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
   
public class ReportScreen extends Screen {
      
   private GridPane scrollContents;
   private ScrollPane reportView; 
   private ColumnConstraints column1, column2, column3, column4, column5, column6, column7, idColumn, nameColumn, priceColumn, quantityColumn, profitColumn;
   private Label headerLabel, dateLabel, idLabel, nameLabel, priceLabel, quantityLabel, profitLabel, totalProfitLabel, totalProfitValueLabel;
   private ChoiceBox<String> year, month, day;
      
   protected void setUpPane() {
      column1 = GUI.getColumn(5.0, HPos.CENTER);  
      column2 = GUI.getColumn(5.0, HPos.CENTER);
      column3 = GUI.getColumn(27.0, HPos.CENTER);
      column4 = GUI.getColumn(11.0, HPos.CENTER);
      column5 = GUI.getColumn(11.0, HPos.CENTER);
      column6 = GUI.getColumn(11.0, HPos.CENTER);
      column7 = GUI.getColumn(30.0, HPos.CENTER);
      
      gridPane.getColumnConstraints().addAll(column1, column2, column3, column4, column5, column6, column7);
      
      addUIControls();
   }
   
   protected void addUIControls() {
      
      // Header
      headerLabel = GUI.getHeader("Business Report");
      gridPane.add(headerLabel, 0, 0, 7, 1);
      gridPane.setMargin(headerLabel, new Insets(0, 0, 20, 0));
      
      // Footer (Logo)
      logo = getLogo(45, 49, 0, 0);
      gridPane.add(logo, 5, 5, 2, 1);
      
      // Cancel Button
      gridPane.add(backButton, 6, 0);
      GridPane.setMargin(backButton, new Insets(20, 49, 108, 0));
      backButton.setOnAction(Store.getReportController().cancelClick());
   
      // Report Date Label
      dateLabel = new Label("Report Date");
      dateLabel.setId("cash-payment-label");
      gridPane.setHalignment(dateLabel, HPos.CENTER);
      gridPane.add(dateLabel, 6, 4);
      GridPane.setMargin(dateLabel, new Insets(0, 0, 100, 0));
      
      // Report Date Drop Downs
      String date = GUI.getDate();
      month = GUI.getMonthBox();
      day = GUI.getDayBox();
      year = GUI.getYearBox();
      GridPane.setMargin(month, new Insets(0, 0, 0, 14));
      GridPane.setMargin(day, new Insets(0, 0, 0, 94));
      GridPane.setMargin(year, new Insets(0, 0, 0, 174));
      gridPane.add(month, 6, 4);
      gridPane.add(day, 6, 4);
      gridPane.add(year, 6, 4);
      month.getSelectionModel().selectedItemProperty().addListener(Store.getReportController().dateSelect());
      day.getSelectionModel().selectedItemProperty().addListener(Store.getReportController().dateSelect());
      year.getSelectionModel().selectedItemProperty().addListener(Store.getReportController().dateSelect());
   
      // ID Label
      idLabel = new Label("ID");
      idLabel.setId("report-title");
      idLabel.setMaxWidth(Double.MAX_VALUE);
      gridPane.add(idLabel, 1, 1, 2,1);
      gridPane.setValignment(idLabel, VPos.BOTTOM);
      idLabel.setOnMouseClicked(Store.getReportController().idClick());
   
      // Name Label
      nameLabel = new Label("Name");
      nameLabel.setId("report-title");
      nameLabel.setMaxWidth(Double.MAX_VALUE);
      gridPane.add(nameLabel, 2, 1);
      gridPane.setValignment(nameLabel, VPos.BOTTOM);
      gridPane.setHalignment(nameLabel, HPos.CENTER);
      nameLabel.setOnMouseClicked(Store.getReportController().nameClick());
      
      // Price Label
      priceLabel = new Label("Price");
      priceLabel.setId("report-title");
      priceLabel.setMaxWidth(Double.MAX_VALUE);
      gridPane.add(priceLabel, 3, 1);
      gridPane.setValignment(priceLabel, VPos.BOTTOM);
      priceLabel.setOnMouseClicked(Store.getReportController().priceClick());
      
      // Quantity Label
      quantityLabel = new Label("Qty Sold");
      quantityLabel.setId("report-title");
      quantityLabel.setMaxWidth(Double.MAX_VALUE);
      gridPane.add(quantityLabel, 4, 1, 2, 1);
      gridPane.setValignment(quantityLabel, VPos.BOTTOM);
      quantityLabel.setOnMouseClicked(Store.getReportController().quantityClick());
      
      // Profit Label
      profitLabel = new Label("Profit");
      profitLabel.setId("report-title");
      profitLabel.setMaxWidth(Double.MAX_VALUE);
      gridPane.add(profitLabel, 5, 1);
      gridPane.setValignment(profitLabel, VPos.BOTTOM);
      profitLabel.setOnMouseClicked(Store.getReportController().profitClick());
      
      // Total Profit Label
      totalProfitLabel = new Label("Total Profit :");
      totalProfitLabel.setId("checkout-total-label");
      totalProfitLabel.setMaxWidth(Double.MAX_VALUE);
      gridPane.add(totalProfitLabel, 3, 5, 3, 1);
      gridPane.setValignment(totalProfitLabel, VPos.TOP);
      
      // Total Profit Value Label
      totalProfitValueLabel = new Label();
      totalProfitValueLabel.setId("checkout-total-value-label");
      gridPane.add(totalProfitValueLabel, 4, 5, 2, 1);
      //totalProfitValueLabel.setMaxWidth(Double.MAX_VALUE);
      gridPane.setValignment(totalProfitValueLabel, VPos.TOP);
      gridPane.setHalignment(totalProfitValueLabel, HPos.CENTER);
      //gridPane.setMargin(totalProfitValueLabel, new Insets(0, 37, 0, 0));
   
   // Product ScrollPane
      reportView = new ScrollPane();
      reportView.setPrefViewportHeight(364);
      gridPane.add(reportView, 1, 2, 5, 3);
      scrollContents = new GridPane();
      idColumn = GUI.getColumn(52, HPos.CENTER);
      nameColumn = GUI.getColumn(276, HPos.CENTER);
      priceColumn = GUI.getColumn(112, HPos.CENTER);
      quantityColumn = GUI.getColumn(112, HPos.CENTER);
      profitColumn = GUI.getColumn(112, HPos.RIGHT);
      scrollContents.getColumnConstraints().addAll(idColumn, nameColumn, priceColumn, quantityColumn, profitColumn);
      reportView.setContent(scrollContents);
   }

// ATTRIBUTES USED BY CONTROLLER

    // Scrolling GridPane
   public GridPane getScroll() {
      return scrollContents;
   }

    // Total Profit Label
   public Label getTotal() {
      return totalProfitValueLabel;
   }

    // Date Dropdowns
   public String getDate() {
      return  year.getValue() + "/" + month.getValue() + "/" + day.getValue();
   }
}