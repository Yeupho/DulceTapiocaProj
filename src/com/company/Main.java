package com.company;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main extends Application{
    Stage window;

    //Totals
    Double exIngTot = 0.0;
    Double exIngTotDiff = 0.0;
    Double typeTot = 0.0;
    Double tempTot = 0.0;
    Double tempTotDiff = 0.0;
    Double runningTot = 0.0;
    Double finTot = 0.0;
    Double realfinTot = 0.0;

    //Decimal Format
    String COMMA_SEPERATED = "###,###.###";
    DecimalFormat decimalFormat = new DecimalFormat(COMMA_SEPERATED);


        //Insertion Arrays
    List<Integer> drinkIDList = new ArrayList<>();
    List<Integer> iceIDList = new ArrayList<>();
    List<Integer> sugIDList = new ArrayList<>();
    List<Integer> sizeIDList = new ArrayList<>();
    List<Integer> tempIDList = new ArrayList<>();
    List<Integer> finTopIDList = new ArrayList<>();
    List<Double> finTotalIDList = new ArrayList<>();



    //Random Position ID
    Random RNG = new Random();
    int finalpositiontimeID;

    String ticketID;
    String transID;
    String orderID ;



    //Fine Tune Page
    int sizeNum = 1;
    int iceNum = 2;
    int sugarNum = 2;
    int tempNum = 2;
    int qtNum = 1;
    int iT = 0;
    int cartTracker = 0;

    //IDs for SQL Inserts
    int iceID = 2, sugarID = 2, tempID = 2;
    int finaltoppingID;
    int finalsmoothieID;
    int finalteaID;
    int finalcoffeeID;
    int finalmilkteaID;
    int finalfrioID;
    int finaldrinkID;
    int finalQTY = 1;
    int realfinQTY = 0;
    int realfinaltoppingID;



    //Image labels
    Label qtImage = new Label();
    Label tempImage = new Label();
    Label sugarImage = new Label();
    Label iceImage = new Label();
    Label Size = new Label();



    //Button gb1 extra ingre
    Button gb1 = new Button("Go Back");
    Button co1 = new Button("Cancel Order");
    Button cont1 = new Button("Skip");




    //Grab last transaction ID from table
    String ticketIDSQL = "SELECT TOP 1 TicketID FROM Ticket" +
                        " ORDER BY TicketID DESC;";

    String transIDSQL = "SELECT TOP 1 TransactID FROM TrantnOrder" +
                         " ORDER BY TransactID DESC;";

    String orderIDSQL = "SELECT TOP 1 OrderID FROM OrderTable" +
                         " ORDER BY OrderID DESC;";

    String pullMe = "";



    int finalticketID;
    int finaltransID;
    int finalOrderID;




    //Begin Carts
    ListView<String> c = new ListView<>();
    ListView<String> d = new ListView<>();
    ListView<String> da = new ListView<>();
    ListView<String> db = new ListView<>();
    ListView<String> dc = new ListView<>();
    ListView<String> dd = new ListView<>();
    ListView<String> f = new ListView<>();
    ListView<String> g = new ListView<>();
    ListView<String> h = new ListView<>();
    ListView<String> finCart = new ListView<>();


    String cart;
    ArrayList<String> cart1 = new ArrayList<>();
    ObservableList<String> cartItems = FXCollections.observableArrayList(cart1);

    ArrayList<String> runningCart = new ArrayList<>();
    ObservableList<String> excartItems = FXCollections.observableArrayList(runningCart);

    ArrayList<String> ftrunningCart = new ArrayList<>();
    ObservableList<String> ftcartItems = FXCollections.observableArrayList(ftrunningCart);

    ArrayList<String> ftrunningCart2 = new ArrayList<>();
    ObservableList<String> ftcartItems2 = FXCollections.observableArrayList(ftrunningCart2);

    ArrayList<String> ftrunningCart3 = new ArrayList<>();
    ObservableList<String> ftcartItems3 = FXCollections.observableArrayList(ftrunningCart3);

    ArrayList<String> ftrunningCart4 = new ArrayList<>();
    ObservableList<String> ftcartItems4 = FXCollections.observableArrayList(ftrunningCart4);

    ArrayList<String> ftrunningCart5 = new ArrayList<>();
    ObservableList<String> ftcartItems5 = FXCollections.observableArrayList(ftrunningCart5);

    ArrayList<String> fincartItems = new ArrayList<>();
    ObservableList<String> fincartItemsGo = FXCollections.observableArrayList(ftcartItems);

    ArrayList<String> fincartItems2 = new ArrayList<>();
    ObservableList<String> fincartItemsGoNow = FXCollections.observableArrayList(fincartItems2);


    //End Carts


    /*===================================DATABASE DATA==================================*/
    //Smoothie Drink Types
    ObservableList<ObservableList> drinkData = FXCollections.observableArrayList();

    //Tea
    ObservableList<ObservableList> teaData = FXCollections.observableArrayList();

    //Coffee
    ObservableList<ObservableList> coffeeData = FXCollections.observableArrayList();

    //Frio
    ObservableList<ObservableList> frioData = FXCollections.observableArrayList();

    //Milk Tea
    ObservableList<ObservableList> milkteaData = FXCollections.observableArrayList();

    //Toppings
    ObservableList<ObservableList> toppingData = FXCollections.observableArrayList();



    /*======================================SQL STATEMENTS===========================*/
    // SQL FOR SELECTING ALL SMOOTHIE
    String SQL = "SELECT DrinkName FROM Drink WHERE DrinkTypeID = 1";
    String SQLa = "SELECT Cost FROM Drink WHERE DrinkTypeID = 1";
    String SQLb = "SELECT DrinkID FROM Drink WHERE DrinkTypeID = 1";

    // SQL FOR SELECTING ALL TEA
    String SQL2 = "SELECT DrinkName FROM Drink WHERE DrinkTypeID = 2";
    String SQL2a = "SELECT Cost FROM Drink WHERE DrinkTypeID = 2";
    String SQL2b = "SELECT DrinkID FROM Drink WHERE DrinkTypeID = 2";

    // SQL FOR SELECTING ALL COFFEE
    String SQL3 = "SELECT DrinkName FROM Drink WHERE DrinkTypeID = 3";
    String SQL3a = "SELECT Cost FROM Drink WHERE DrinkTypeID = 3";
    String SQL3b = "SELECT DrinkID FROM Drink WHERE DrinkTypeID = 3";

    // SQL FOR SELECTING ALL Milk Tea
    String SQL4 = "SELECT DrinkName FROM Drink WHERE DrinkTypeID = 4";
    String SQL4a = "SELECT Cost FROM Drink WHERE DrinkTypeID = 4";
    String SQL4b = "SELECT DrinkID FROM Drink WHERE DrinkTypeID = 4";

    // SQL FOR SELECTING ALL Frio
    String SQL5 = "SELECT DrinkName FROM Drink WHERE DrinkTypeID = 10";
    String SQL5a = "SELECT Cost FROM Drink WHERE DrinkTypeID = 10";
    String SQL5b = "SELECT DrinkID FROM Drink WHERE DrinkTypeID = 10";

    // SQL FOR SELECTING ALL Toppings
    String SQL6 = "SELECT ToppingName FROM Topping";
    String SQL6a = "SELECT Cost FROM Topping";
    String SQL6b = "SELECT ToppingID FROM Topping";




    public Main() throws SQLException {
    }


    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;
        window.setTitle("Dulce Tapioca");



        //Image Defaults
        Image defaultSize = new Image ("size1.png");
        Image defaultQT = new Image ("qty1.png");
        Image defaultIce = new Image ("ice2.png");
        Image defaultTemp = new Image ("temp2.png");
        Image defaultSugar = new Image ("sugar2.png");





        /*==================LOGIN GRID=================*/
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(10);
        grid.setHgap(10);
        grid.setStyle("-fx-background-color: #B09268");
        grid.setAlignment(Pos.CENTER);





        /*==================Order Total Labels=================*/
        //Customer First Page
        Label ordertot = new Label("Current Order Total:   $" + (decimalFormat.format(runningTot)));

        //Smoothie, Tea, Coffee, Milk Tea, Frio
        Label ordertot1 = new Label("Current Order Total:   $" + (decimalFormat.format(runningTot)));
        Label ordertot1a = new Label("Current Order Total:   $" + (decimalFormat.format(runningTot)));
        Label ordertot1b = new Label("Current Order Total:   $" + (decimalFormat.format(runningTot)));
        Label ordertot1c = new Label("Current Order Total:   $" + (decimalFormat.format(runningTot)));
        Label ordertot1d = new Label("Current Order Total:   $" + (decimalFormat.format(runningTot)));

        //Extra Ingredients
        Label ordertot2 = new Label("Current Order Total:   $" + (decimalFormat.format(runningTot)));

        //Fine Tune
        Label ordertot3 = new Label("Current Order Total:   $" + (decimalFormat.format(runningTot)));

        //Confirm
        Label ordertot4 = new Label("Current Order Total:   $");






        /*==================WINDOW DISPLAY================
         * This sets up the scenes and shows the windows. */
        Scene logMenu = new Scene(grid, 400, 400);
        window.setScene(logMenu);
        window.show();






        /*==================HELP SCENE=================*/
        BorderPane Anchor1 = new BorderPane();

        Button mainscr = new Button("START");
        mainscr.setMinSize(250, 50);
        mainscr.setStyle("-fx-font: 20 arial; -fx-base: #7CD674");

        Label welcome = new Label("Welcome to Dulce Tapioca");
        welcome.setStyle("-fx-font: 40 arial;");

        VBox welcomeb = new VBox();
        welcomeb.setAlignment(Pos.TOP_CENTER);
        welcomeb.getChildren().add(welcome);

        VBox inception1 = new VBox();
        inception1.setAlignment(Pos.BOTTOM_LEFT);
        inception1.getChildren().addAll(mainscr);


        BackgroundImage bg = new BackgroundImage(new Image("DulceInstructions.png",1000,600,false,true), BackgroundRepeat.NO_REPEAT
                , BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        Anchor1.setBackground(new Background(bg));


        Anchor1.setBottom(inception1);





        Scene scenehelp = new Scene(Anchor1, 1000, 600);

        scenehelp.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode().equals(KeyCode.F12)) {
                    window.setScene(logMenu);
                }
            }});




        /*==================SCENE 2 - CUSTOMER SECOND PAGE=================*/
        BorderPane bordPane = new BorderPane();
        bordPane.setStyle("-fx-background-color: #B09268");
        HBox topMenu = new HBox();
        HBox bottomMenu = new HBox();
        VBox centerMenu = new VBox();
        VBox rightMenu = new VBox();



        centerMenu.setStyle("-fx-font: 20 arial; -fx-base: #FFC524");

        bordPane.setTop(topMenu);
        bordPane.setBottom(bottomMenu);
        bordPane.setCenter(centerMenu);
        bordPane.setRight(rightMenu);

        Scene scene2 = new Scene(bordPane, 1000, 600);

        mainscr.setOnAction(e -> {


            try {
                 ticketID = DatabasePull.getTransID(pullMe, ticketIDSQL);
                 transID = DatabasePull.getTransID(pullMe, transIDSQL);
                 orderID = DatabasePull.getTransID(pullMe, orderIDSQL);
            } catch (SQLException e1) {
                e1.printStackTrace();
            }


            finalticketID = Integer.parseInt(ticketID)+1;
            finaltransID = Integer.parseInt(transID)+1;
            finalOrderID = Integer.parseInt(orderID)+1;
            finalpositiontimeID = 1+RNG.nextInt(99);

            System.out.println("The Transaction ID for this order is: "+finaltransID);
            System.out.println("The Ticket ID for this order is: "+finalticketID);
            System.out.println("The Order ID for this order is: "+finalOrderID);
            System.out.println("The Position ID for this order is: "+finalpositiontimeID);

            window.setScene(scene2);
        });




        /*==================SCENE 3 - SMOOTHIES=================*/
        BorderPane bord3 = new BorderPane();
        bord3.setStyle("-fx-background-color: #B09268");

        HBox topMenu3 = new HBox();
        HBox bottomMenu3 = new HBox();
        VBox rightMenu3 = new VBox();

        FlowPane smoothFlow = new FlowPane();
        smoothFlow.setPadding(new Insets(10, 30, 10, 0));
        smoothFlow.setVgap(4);
        smoothFlow.setHgap(4);
        smoothFlow.setAlignment(Pos.TOP_CENTER);
        smoothFlow.setPrefWrapLength(200);
        smoothFlow.setStyle("-fx-font: 20 arial; -fx-base: #FFC524");

        Scene scene3 = new Scene(bord3, 1000, 600);

        bord3.setTop(topMenu3);
        bord3.setBottom(bottomMenu3);
        bord3.setCenter(smoothFlow);
        bord3.setRight(rightMenu3);


        /*==================SCENE 3a - TEA=================*/
        BorderPane bord3a = new BorderPane();
        bord3a.setStyle("-fx-background-color: #B09268");

        HBox topMenu3a = new HBox();
        HBox bottomMenu3a = new HBox();
        VBox rightMenu3a = new VBox();

        FlowPane smoothFlowa = new FlowPane();
        smoothFlowa.setPadding(new Insets(10, 30, 10, 0));
        smoothFlowa.setVgap(4);
        smoothFlowa.setHgap(4);
        smoothFlowa.setAlignment(Pos.TOP_CENTER);
        smoothFlowa.setPrefWrapLength(200);
        smoothFlowa.setStyle("-fx-font: 20 arial; -fx-base: #FFC524");

        Scene scene3a = new Scene(bord3a, 1000, 600);

        bord3a.setTop(topMenu3a);
        bord3a.setBottom(bottomMenu3a);
        bord3a.setCenter(smoothFlowa);
        bord3a.setRight(rightMenu3a);

        /*==================SCENE 3b - COFFEE================*/
        BorderPane bord3b = new BorderPane();
        bord3b.setStyle("-fx-background-color: #B09268");

        HBox topMenu3b = new HBox();
        HBox bottomMenu3b = new HBox();
        VBox rightMenu3b = new VBox();

        FlowPane smoothFlowb = new FlowPane();
        smoothFlowb.setPadding(new Insets(10, 30, 10, 0));
        smoothFlowb.setVgap(4);
        smoothFlowb.setHgap(4);
        smoothFlowb.setAlignment(Pos.TOP_CENTER);
        smoothFlowb.setPrefWrapLength(200);
        smoothFlowb.setStyle("-fx-font: 20 arial; -fx-base: #FFC524");

        Scene scene3b = new Scene(bord3b, 1000, 600);

        bord3b.setTop(topMenu3b);
        bord3b.setBottom(bottomMenu3b);
        bord3b.setCenter(smoothFlowb);
        bord3b.setRight(rightMenu3b);

        /*==================SCENE 3c - MILK TEA=================*/
        BorderPane bord3c = new BorderPane();
        bord3c.setStyle("-fx-background-color: #B09268");

        HBox topMenu3c = new HBox();
        HBox bottomMenu3c = new HBox();
        VBox rightMenu3c = new VBox();

        FlowPane smoothFlowc = new FlowPane();
        smoothFlowc.setPadding(new Insets(10, 30, 10, 0));
        smoothFlowc.setVgap(4);
        smoothFlowc.setHgap(4);
        smoothFlowc.setAlignment(Pos.TOP_CENTER);
        smoothFlowc.setPrefWrapLength(200);
        smoothFlowc.setStyle("-fx-font: 20 arial; -fx-base: #FFC524");

        Scene scene3c = new Scene(bord3c, 1000, 600);

        bord3c.setTop(topMenu3c);
        bord3c.setBottom(bottomMenu3c);
        bord3c.setCenter(smoothFlowc);
        bord3c.setRight(rightMenu3c);

        /*==================SCENE 3d - FRIO=================*/
        BorderPane bord3d = new BorderPane();
        bord3d.setStyle("-fx-background-color: #B09268");

        HBox topMenu3d = new HBox();
        HBox bottomMenu3d = new HBox();
        VBox rightMenu3d = new VBox();

        FlowPane smoothFlowd = new FlowPane();
        smoothFlowd.setPadding(new Insets(10, 30, 10, 0));
        smoothFlowd.setVgap(4);
        smoothFlowd.setHgap(4);
        smoothFlowd.setAlignment(Pos.TOP_CENTER);
        smoothFlowd.setPrefWrapLength(200);
        smoothFlowd.setStyle("-fx-font: 20 arial; -fx-base: #FFC524");

        Scene scene3d = new Scene(bord3d, 1000, 600);

        bord3d.setTop(topMenu3d);
        bord3d.setBottom(bottomMenu3d);
        bord3d.setCenter(smoothFlowd);
        bord3d.setRight(rightMenu3d);




        /*==================SCENE 4 - Extra Ingredients=================*/
        BorderPane bord4 = new BorderPane();
        bord4.setStyle("-fx-background-color: #B09268");

        HBox topMenu4 = new HBox();
        HBox bottomMenu4 = new HBox();
        VBox rightMenu4 = new VBox();




        FlowPane exFlow = new FlowPane();
        exFlow.setPadding(new Insets(10, 30, 10, 0));
        exFlow.setVgap(4);
        exFlow.setHgap(4);
        exFlow.setAlignment(Pos.TOP_CENTER);
        exFlow.setPrefWrapLength(200);
        exFlow.setStyle("-fx-font: 20 arial; -fx-base: #FFC524");

        Scene scene4 = new Scene(bord4, 1000, 600);

        bord4.setTop(topMenu4);
        bord4.setBottom(bottomMenu4);
        bord4.setCenter(exFlow);
        bord4.setRight(rightMenu4);





        /*==================SCENE 5 - Fine Tune=================*/
        BorderPane bord5 = new BorderPane();
        bord5.setStyle("-fx-background-color: #B09268");

        HBox topMenu5 = new HBox();
        HBox bottomMenu5 = new HBox();
        VBox rightMenu5 = new VBox();

        GridPane ftGrid = new GridPane();
        ftGrid.setPadding(new Insets(10, 10, 10, 10));
        ftGrid.setVgap(20);
        ftGrid.setHgap(30);
        ftGrid.setStyle("-fx-background-color: #B09268");



        Scene scene5 = new Scene(bord5, 1000, 600);

        bord5.setTop(topMenu5);
        bord5.setBottom(bottomMenu5);
        bord5.setCenter(ftGrid);
        bord5.setRight(rightMenu5);

        /*==================SCENE 6 - Confirm=================*/
        BorderPane confirmPane = new BorderPane();
        confirmPane.setStyle("-fx-background-color: #B09268");
        HBox topMenu6 = new HBox();
        HBox bottomMenu6 = new HBox();
        VBox centerMenu6 = new VBox();
        VBox rightMenu6 = new VBox();



        centerMenu6.setStyle("-fx-font: 20 arial; -fx-base: #FFC524");

        confirmPane.setTop(topMenu6);
        confirmPane.setBottom(bottomMenu6);
        confirmPane.setCenter(centerMenu6);
        confirmPane.setRight(rightMenu6);

        Scene scene6 = new Scene(confirmPane, 1000, 600);





        /*===================LOGIN PAGE=================
 * Login page will have Username and password
 * -Pressing the Login button checks the typed Username and Password, then checks the database for it as well
 * -Create if-else statement that takes User to either "Welcome Page" or "Employee Page"
 * -Once they get to the Welcome Page, there is no turning back without closing the program
 * -"Welcome Page" is used by the Customer, "Employee Page" will be for employees of course.
 * -
 * */
        //Name	Label, placed in grid 0, 0
        Label userLabel = new Label("Username");
        GridPane.setConstraints(userLabel, 0, 1);
        userLabel.setStyle("-fx-font: 16.5 arial;");

        //Name Input, placed in grid 1, 0
        TextField userIn = new TextField();
        userIn.setPromptText("username");
        GridPane.setConstraints(userIn, 1, 1);

        //Password Label, placed in grid 0, 1
        Label passLabel = new Label("Password");
        passLabel.setStyle("-fx-font: 16.5 arial;");
        GridPane.setConstraints(passLabel, 0, 2);

        //Password Input
       /* TextField passIn = new TextField();
        passIn.setPromptText("password"); */
        PasswordField passIn = new PasswordField();
        passIn.setPromptText("password");

        GridPane.setConstraints(passIn, 1, 2);

        //Login Button,
        Button logButt = new Button("Login");
        logButt.setStyle("-fx-font: 16.5 arial; -fx-base: #FFC524");
        logButt.setMinWidth(250);
        logButt.setAlignment(Pos.CENTER);
        GridPane.setConstraints(logButt, 1, 4);

        Image disImg = new Image("tapioca.png");
        ImageView iv1 = new ImageView();
        iv1.setImage(disImg);
        GridPane.setConstraints(iv1, 1, 0);

        grid.getChildren().addAll(userLabel, userIn, passLabel, passIn, logButt, iv1);




 /*
		 * @@@@@@@@@@@@EMPLOYEE Main Menu@@@@@@@@@@@@@@@@@@ /
		 */

		/*
		 * @@@@@@@@@@@@@@@@@@@ EMPLOYEE Menu Bar + TreeList @@@@@@@@@@@@@@@@@@@
		 * This is the menu bar that will go into ALL of the pages of the
		 * Employee GUI The tree list is a quick and simple navigation
		 */
        MenuBar menus = new MenuBar();
        menus.setStyle("-fx-background-color: #b78345;" + "-fx-font-size:30;" + "-fx-stroke: black;"
                + "-fx-stroke-width: 3;" + "-fx-text-fill: white;");
        Menu menuFile = new Menu("File");
        MenuItem menFNew = new MenuItem("New...");
        MenuItem menFRef = new MenuItem("Refresh");
        MenuItem menFProp = new MenuItem("Properties");
        MenuItem menFLogout = new MenuItem("Log out");
        MenuItem menFexit = new MenuItem("Close");
        menFexit.setOnAction(e -> window.close());
        menuFile.getItems().addAll(menFNew, menFRef, menFProp, menFLogout, menFexit);

        Menu menuAdd = new Menu("Add");
        MenuItem menAItems = new MenuItem("Menu Item");
        MenuItem menAEmployee = new MenuItem("Employee");
        MenuItem menALocation = new MenuItem("Location");
        menuAdd.getItems().addAll(menAEmployee, menAItems, menALocation);

        menAEmployee.setOnAction(e -> AddtoThings.display());
        menAItems.setOnAction(e -> AddtoThings.displayOrder());
        menALocation.setOnAction(e -> AddtoThings.displayLocation());
        menFLogout.setOnAction(e -> window.setScene(logMenu));

        Menu menuView = new Menu("View");
        MenuItem menVOrd = new MenuItem("Order Reports");
        MenuItem menVEmp = new MenuItem("Emplyoee Reports");
        menVOrd.setOnAction(e -> ViewOrders.GeneralOrder());
        menVEmp.setOnAction(e -> ViewEmployee.GeneralEmplyoee());
        menuView.getItems().addAll(menVOrd, menVEmp);
        menuView.getItems().add(new MenuItem("Employee Reports"));
        menuView.getItems().add(new MenuItem("Location Reports"));

        Menu menuHelp = new Menu("Help");
        menuHelp.getItems().add(new MenuItem("About Team Solar"));
        menuHelp.getItems().add(new MenuItem("Documentation"));
        menuHelp.getItems().add(new MenuItem());
        menus.getMenus().addAll(menuFile, menuAdd, menuView, menuHelp);


		/*
		 * ==============================Employee Center
		 * Display=======================================
		 */
        VBox EmpFlow = new VBox();
        EmpFlow.setStyle("-fx-background-color: #B09268");
        Label EmpFlow1a = new Label("Orders");
        Label EmpFlow2a = new Label("Employees");
        Label EmpFlow3a = new Label("Location");
        EmpFlow1a.setStyle("-fx-font-size:20; -fx-padding: 10, 0, 0, 0;");
        EmpFlow2a.setStyle("-fx-font-size:20; -fx-padding: 10, 0, 0, 0;");
        EmpFlow3a.setStyle("-fx-font-size:20; -fx-padding: 10, 0, 0, 0;");

        DropShadow dropShadow = new DropShadow();
        dropShadow.setRadius(5.0);
        dropShadow.setOffsetX(3.0);
        dropShadow.setOffsetY(3.0);
        dropShadow.setColor(Color.color(0.3, 0.3, 0.3));

        FlowPane EmpFlow1 = new FlowPane();
        EmpFlow1.setStyle("-fx-background-color: #B09268");
        EmpFlow1.setVgap(20);
        EmpFlow1.setHgap(20);
        EmpFlow1.setPrefWrapLength(800);
        EmpFlow1.setPadding(new Insets(20, 0, 0, 20));
        FlowPane EmpFlow2 = new FlowPane();
        EmpFlow2.setStyle("-fx-background-color: #B09268");
        EmpFlow2.setVgap(20);
        EmpFlow2.setHgap(20);
        EmpFlow2.setPrefWrapLength(800);
        EmpFlow2.setPadding(new Insets(20, 0, 0, 20));
        FlowPane EmpFlow3 = new FlowPane();
        EmpFlow3.setStyle("-fx-background-color: #B09268");
        EmpFlow3.setVgap(20);
        EmpFlow3.setHgap(20);
        EmpFlow3.setPrefWrapLength(800);
        EmpFlow3.setPadding(new Insets(20, 0, 0, 20));

        // First Row
        Button ButtVOrd = new Button("");
        ButtVOrd.setOnAction(e -> ViewOrders.GeneralOrder());
        Button ButtAddIngr = new Button("");
        ButtAddIngr.setOnAction(e -> AddtoThings.displayOrder());
        Button ButtEditIngr = new Button("");
        ButtEditIngr.setOnAction(e -> EditThings.OrderPage());

        // Second Row
        Button ButtVEmp = new Button("");
        ButtVEmp.setOnAction(e -> ViewEmployee.GeneralEmplyoee());
        Button ButtAddemp = new Button("");
        ButtAddemp.setOnAction(e -> AddtoThings.display());
        Button ButtUpEmp = new Button("");
        ButtUpEmp.setOnAction(e -> EditThings.EmployeePage());

        // Third Row
        Button ButtVRep = new Button("");
        ButtVRep.setOnAction(e -> ViewReports.ReportDisplay());
        Button ButtAddLoc = new Button("");
        ButtAddLoc.setOnAction(e -> AddtoThings.displayLocation());
        Button ButtUpLoc = new Button("");
        ButtUpLoc.setOnAction(e -> EditThings.LocationPage());

        ButtVOrd.setEffect(dropShadow);
        ButtVOrd.setMinSize(200, 100);
        ButtVOrd.setMaxSize(200, 100);
        ButtVOrd.setPadding(new Insets(30, 20, 30, 20));
        ButtVOrd.setStyle("" + "-fx-font-size: 20px;" + "-fx-border-radius: 50; " + "-fx-background-radius: 50; "
                + "-fx-background-color: #DECC8C;"
                + "-fx-background-image: url('ord.png')");
        ButtVEmp.setEffect(dropShadow);
        ButtVEmp.setMinSize(200, 100);
        ButtVEmp.setMaxSize(200, 100);
        ButtVEmp.setStyle("" + "-fx-font-size: 20px;" + "-fx-border-radius: 50; " + "-fx-background-radius: 50; "
                + "-fx-background-color: #F28A99;"
                + "-fx-background-image: url('empinfo.png')");
        ButtVRep.setEffect(dropShadow);
        ButtVRep.setMinSize(200, 100);
        ButtVRep.setMaxSize(200, 100);
        ButtVRep.setStyle("" + "-fx-font-size: 20px;" + "-fx-border-radius: 50; " + "-fx-background-radius: 50; "
                + "-fx-background-color: #D0B040; "
                + "-fx-background-image: url('reports.png');");
        ButtAddIngr.setEffect(dropShadow);
        ButtAddIngr.setMinSize(200, 100);
        ButtAddIngr.setMaxSize(200, 100);
        ButtAddIngr.setPadding(new Insets(30, 20, 30, 20));
        ButtAddIngr.setStyle("" + "-fx-font-size: 20px;" + "-fx-border-radius: 50; " + "-fx-background-radius: 50; "
                + "-fx-background-color: #DE986D;"
                + "-fx-background-image: url('addord.png');");
        ButtAddemp.setEffect(dropShadow);
        ButtAddemp.setMinSize(200, 100);
        ButtAddemp.setMaxSize(200, 100);
        ButtAddemp.setPadding(new Insets(30, 20, 30, 20));
        ButtAddemp.setStyle("" + "-fx-font-size: 20px;" + "-fx-border-radius: 50; " + "-fx-background-radius: 50; "
                + "-fx-background-color: #AB6890;"
                + "-fx-background-image: url('addemp.png')");

        ButtAddLoc.setEffect(dropShadow);
        ButtAddLoc.setMinSize(200, 100);
        ButtAddLoc.setMaxSize(200, 100);
        ButtAddLoc.setPadding(new Insets(30, 20, 30, 20));
        ButtAddLoc.setStyle("" + "-fx-font-size: 20px;" + "-fx-border-radius: 50; " + "-fx-background-radius: 50; "
                + "-fx-background-color: #68AB9F; "
                + "-fx-background-image: url('addloc.png')");

        ButtEditIngr.setEffect(dropShadow);
        ButtEditIngr.setMinSize(200, 100);
        ButtEditIngr.setMaxSize(200, 100);
        ButtEditIngr.setPadding(new Insets(30, 20, 30, 20));
        ButtEditIngr.setStyle("" + "-fx-font-size: 20px;" + "-fx-border-radius: 50; " + "-fx-background-radius: 50; "
                + "-fx-background-color: #7096AE;"
                + "-fx-background-image: url('upord.png');");

        ButtUpEmp.setEffect(dropShadow);
        ButtUpEmp.setMinSize(200, 100);
        ButtUpEmp.setMaxSize(200, 100);
        ButtUpEmp.setPadding(new Insets(30, 20, 30, 20));
        ButtUpEmp.setStyle("" + "-fx-font-size: 15px;" + "-fx-border-radius: 50; " + "-fx-background-radius: 50; "
                + "-fx-background-color: #DAA9B5;"
                + "-fx-background-image: url('upemp.png');");
        ButtUpLoc.setEffect(dropShadow);
        ButtUpLoc.setMinSize(200, 100);
        ButtUpLoc.setMaxSize(200, 100);
        ButtUpLoc.setPadding(new Insets(30, 20, 30, 20));
        ButtUpLoc.setStyle("" + "-fx-font-size: 20px;" + "-fx-border-radius: 50; " + "-fx-background-radius: 50; "
                + "-fx-background-color: #AFE197; "
                + "-fx-background-image: url('editloc.png');");

        EmpFlow1.getChildren().addAll(ButtVOrd, ButtAddIngr, ButtEditIngr);
        EmpFlow2.getChildren().addAll(ButtVEmp, ButtAddemp, ButtUpEmp);
        EmpFlow3.getChildren().addAll(ButtVRep, ButtAddLoc, ButtUpLoc);
        EmpFlow.getChildren().addAll(EmpFlow1a, EmpFlow1, EmpFlow2a, EmpFlow2, EmpFlow3a, EmpFlow3);

		/*
		 * @@@@@@@@@@@@@@@@@@@@EMPLYOEE Main
		 * Page @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ First page of the Employee
		 * GUI, contains List to add data, edit data, display form, display
		 * reports
		 */

        BorderPane empMenu1 = new BorderPane();
        VBox EmpLeftSet = new VBox();
        EmpLeftSet.setStyle("-fx-background-color: #B09268");
        Image disImg1 = new Image("tapioca.png");
        ImageView iv12 = new ImageView();
        iv12.setImage(disImg1);
        EmpLeftSet.getChildren().add(iv12);
        empMenu1.setTop(menus);
        empMenu1.setLeft(EmpLeftSet);
        empMenu1.setCenter(EmpFlow);
        Scene EmpScene = new Scene(empMenu1, 1000, 600);




        /*==================================CONDITIONAL LOGIN==============================================*/


        logButt.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {


                if ((userIn.getText() != null && userIn.getText().contains("Employee")) && passIn.getText().contains("Employee")) {
                    window.setScene(EmpScene);
                } else if ((userIn.getText() != null && userIn.getText().contains("Customer")) && passIn.getText().contains("Customer")) {
                    window.setScene(scenehelp);
                } else {
                    Okay.display("Incorrect Login", "Bakaaaaa~~~ Please verify your credentials");
                    System.out.println("looololol, try 'Employee' in both fields or 'Customer' in both fields");

                }

            }

        });

        passIn.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.ENTER) {
                    if ((userIn.getText() != null && userIn.getText().contains("Employee")) && passIn.getText().contains("Employee")) {
                        window.setScene(EmpScene);
                    } else if ((userIn.getText() != null && userIn.getText().contains("Customer")) && passIn.getText().contains("Customer")) {
                        window.setScene(scenehelp);
                    } else {
                        Okay.display("Incorrect Login", "Bakaaaaa~~~ Please verify your credentials");
                        System.out.println("looololol, try 'Employee' in both fields or 'Customer' in both fields");

                    }
                }
            }
        });




        /*=============CUSTOMER SECOND PAGE==============
 * -Top label asks What type of drink customer would like
 * -Bottom Menu has option to: Go Back, Cancel, Continue
 * -Right Menu displays order, plus kawaii image on top
 * -Center menu displays the Options as buttons
 * -Once Type of ingredient is clicked, the scene takes you to Third Page
 * -Third page will be hard because it will have to read from database
 * 		FIRST before displaying options, because of what gets picked in
 * 		SECOND page.
 *
 *
 * */
        //Top Menu Items
        Label label1 = new Label("What would you like today?");
        label1.setPadding(new Insets(30, 30, 30, 30));
        label1.setStyle("-fx-font: 35 arial; -fx-base: #FFC524");
        topMenu.getChildren().addAll(label1);

        //Bottom Menu Items
        bottomMenu.setPadding(new Insets(30, 30, 30, 30));
        bottomMenu.setSpacing(30);

        Button ButtonA = new Button("Go Back");
        ButtonA.setStyle("-fx-font: 20 arial; -fx-base: #FFC524");
        ButtonA.setMinWidth(200);
        ButtonA.setAlignment(Pos.CENTER);

        Button ButtonB = new Button("Cancel Order");
        ButtonB.setStyle("-fx-font: 20 arial; -fx-base: #FFC524");
        ButtonB.setMinWidth(200);
        ButtonB.setAlignment(Pos.CENTER);

        //Find Me - Check out buttons
        Button checkoutReturn = new Button("Return");
        Button checkoutReturn3 = new Button("Return");

        checkoutReturn.setStyle("-fx-font: 20 arial; -fx-base: #7CD674");
        checkoutReturn.setMinWidth(200);
        checkoutReturn.setAlignment(Pos.CENTER);
        checkoutReturn.setVisible(false);
        checkoutReturn.setOnAction(e ->
        {
            window.setScene(scene6);
            clearCart(c);
            clearCart(d);
            clearCart(da);
            clearCart(db);
            clearCart(dc);
            clearCart(dd);
            clearCart(f);
            clearCart(g);

            //Whenever cancel is clicked everything should reset to default values


            tempTot = 0.0;
            tempTotDiff = 0.0;
            typeTot = 0.0;
            exIngTot = 0.0;
            exIngTotDiff = 0.0;
            runningTot = 0.0;
            finTot = realfinTot;
            iT -= 1;



            sizeNum = 1;
            iceNum = 2;
            sugarNum = 2;
            tempNum = 2;
            qtNum = 1;

        });


        bottomMenu.setMinHeight(-10);
        bottomMenu.getChildren().addAll(ButtonA, ButtonB, checkoutReturn);

        //Right Menu Items
        ImageView iv2 = new ImageView();
        Image step1 = new Image("step1.png");
        iv2.setImage(step1);
        iv2.setFitHeight(100);
        iv2.setFitWidth(150);

        rightMenu.setAlignment(Pos.CENTER);
        rightMenu.setPadding(new Insets(50, 40, 30, 30));
        rightMenu.setSpacing(20);


        buildCart(c);

        VBox vcart = new VBox();
        Label carttit = new Label("Shopping Cart");
        carttit.setStyle("-fx-font: 15 arial;");

        ordertot.setStyle("-fx-font: 15 arial;");

        vcart.getChildren().addAll(carttit, c, ordertot);




        rightMenu.getChildren().addAll(iv2, vcart);

        //Scene 2 Center Menu Items
        centerMenu.setPadding(new Insets(50, 40, 30, 30));
        centerMenu.setSpacing(10);

        Button ButtonD = new Button("Smoothie");
        ButtonD.setMinSize(200, 60);
        ButtonD.setAlignment(Pos.CENTER);
        ButtonD.setOnAction(e -> window.setScene(scene3));

        Button ButtonE = new Button("Tea");
        ButtonE.setMinSize(200, 60);
        ButtonE.setAlignment(Pos.CENTER);
        ButtonE.setOnAction(e -> window.setScene(scene3a));


        Button ButtonF = new Button("Coffee");
        ButtonF.setMinSize(200, 60);
        ButtonF.setAlignment(Pos.CENTER);
        ButtonF.setOnAction(e -> window.setScene(scene3b));

        Button ButtonG = new Button("Frio");
        ButtonG.setMinSize(200, 60);
        ButtonG.setAlignment(Pos.CENTER);
        ButtonG.setOnAction(e -> window.setScene(scene3d));

        Button ButtonH = new Button("Milk Tea");
        ButtonH.setMinSize(200, 60);
        ButtonH.setAlignment(Pos.CENTER);
        ButtonH.setOnAction(e -> window.setScene(scene3c));


        centerMenu.setSpacing(0);
        centerMenu.getChildren().addAll(ButtonD, ButtonE, ButtonF, ButtonG, ButtonH);

        ButtonA.setOnAction(e -> {
            //clearCart();
            window.setScene(scenehelp);
        });


        //Button B always go back to main menu, NOTE: ADD CONFIRMATION scene to this-
        ButtonB.setOnAction(e ->{
        boolean ans = ConfirmOrder.display("Confirm Cancel", "Are you sure? Canceling will delete all progress!");

        if (ans == true) {
            Okay.display("Sad Day:(", "Please come again!");
            window.setScene(scenehelp);
            clearCart(g);
            clearCart(finCart);

            //Whenever cancel is clicked everything should reset to default values
            tempTot = 0.0;
            tempTotDiff = 0.0;
            typeTot = 0.0;
            exIngTot = 0.0;
            exIngTotDiff = 0.0;
            runningTot = 0.0;
            finTot = 0.0;
            realfinTot = 0.0;
            iT = 0;

            finaltoppingID = 0;
            finalsmoothieID = 0;
            finalteaID = 0;
            finalcoffeeID = 0;
            finalmilkteaID = 0;
            finalfrioID = 0;

            ButtonA.setVisible(true);
            checkoutReturn.setVisible(false);
            checkoutReturn3.setVisible(false);


            sizeNum = 1;
            iceNum = 2;
            sugarNum = 2;
            tempNum = 2;
            qtNum = 1;

            //Defaults
            qtImage.setGraphic(new ImageView(defaultQT));
            tempImage.setGraphic(new ImageView(defaultTemp));
            iceImage.setGraphic(new ImageView(defaultIce));
            Size.setGraphic(new ImageView(defaultSize));
            sugarImage.setGraphic(new ImageView(defaultSugar));


            ordertot2.setText("Order Total: $" + (decimalFormat.format(runningTot)));
            ordertot3.setText("Order Total: $" + (decimalFormat.format(runningTot)));
            ordertot4.setText("Order Total: $" + (decimalFormat.format(runningTot)));
        } else {
            Okay.display("Continue", "Please Continue!");
        }
        });

/*=============CUSTOMER SMOOTHIE PAGE=============== */

        //Top Menu Items
        Label lab1 = new Label("What flavor would you like?");
        lab1.setPadding(new Insets(30, 30, 30, 30));
        lab1.setStyle("-fx-font: 35 arial; -fx-base: #FFC524");
        topMenu3.getChildren().addAll(lab1);

        buildCart(d);
        d.setItems(cartItems);

        //Bottom Menu Items
        bottomMenu3.setPadding(new Insets(30, 30, 30, 30));
        bottomMenu3.setSpacing(30);

        Button ButtonA3 = new Button("Go Back");
        ButtonA3.setStyle("-fx-font: 20 arial; -fx-base: #FFC524");
        ButtonA3.setMinWidth(200);
        ButtonA3.setAlignment(Pos.CENTER);

        Button ButtonB3 = new Button("Cancel Order");
        ButtonB3.setStyle("-fx-font: 20 arial; -fx-base: #FFC524");
        ButtonB3.setMinWidth(200);
        ButtonB3.setAlignment(Pos.CENTER);


        checkoutReturn3.setStyle("-fx-font: 20 arial; -fx-base: #7CD674");
        checkoutReturn3.setMinWidth(200);
        checkoutReturn3.setAlignment(Pos.CENTER);
        checkoutReturn3.setVisible(false);
        checkoutReturn3.setOnAction(e ->
        {
            window.setScene(scene6);
            clearCart(c);
            clearCart(d);
            clearCart(da);
            clearCart(db);
            clearCart(dc);
            clearCart(dd);
            clearCart(f);
            clearCart(g);

            //Whenever cancel is clicked everything should reset to default values
            tempTot = 0.0;
            tempTotDiff = 0.0;
            typeTot = 0.0;
            exIngTot = 0.0;
            exIngTotDiff = 0.0;
            runningTot = 0.0;
            finTot = realfinTot;
            iT -= 1;



            sizeNum = 1;
            iceNum = 2;
            sugarNum = 2;
            tempNum = 2;
            qtNum = 1;

        });

        bottomMenu3.setMinHeight(-10);
        bottomMenu3.getChildren().addAll(ButtonA3, ButtonB3, checkoutReturn3);

        //Clear cart whenever the customer goes back or clicks cancel
        ButtonB3.setOnAction(e -> {
            boolean ans = ConfirmOrder.display("Confirm Cancel", "Are you sure? Canceling will delete all progress!");

            if (ans == true) {
                Okay.display("Sad Day:(", "Please come again!");
                window.setScene(scenehelp);
                clearCart(g);
                clearCart(finCart);

                //Whenever cancel is clicked everything should reset to default values
                tempTot = 0.0;
                tempTotDiff = 0.0;
                typeTot = 0.0;
                exIngTot = 0.0;
                exIngTotDiff = 0.0;
                runningTot = 0.0;
                finTot = 0.0;
                realfinTot = 0.0;
                iT = 0;

                finaltoppingID = 0;
                finalsmoothieID = 0;
                finalteaID = 0;
                finalcoffeeID = 0;
                finalmilkteaID = 0;
                finalfrioID = 0;

                ButtonA.setVisible(true);
                checkoutReturn.setVisible(false);
                checkoutReturn3.setVisible(false);


                sizeNum = 1;
                iceNum = 2;
                sugarNum = 2;
                tempNum = 2;
                qtNum = 1;

                //Defaults
                qtImage.setGraphic(new ImageView(defaultQT));
                tempImage.setGraphic(new ImageView(defaultTemp));
                iceImage.setGraphic(new ImageView(defaultIce));
                Size.setGraphic(new ImageView(defaultSize));
                sugarImage.setGraphic(new ImageView(defaultSugar));


                ordertot2.setText("Order Total: $" + (decimalFormat.format(runningTot)));
                ordertot3.setText("Order Total: $" + (decimalFormat.format(runningTot)));
                ordertot4.setText("Order Total: $" + (decimalFormat.format(runningTot)));
            } else {
                Okay.display("Continue", "Please Continue!");
            }
        });
        ButtonA3.setOnAction(e -> {
            window.setScene(scene2);
            clearCart(d);
            clearCart(da);
            typeTot = 0.0;
        });




        //Right Menu - Image
        ImageView iv3 = new ImageView();
        Image step2 = new Image("step2.png");
        iv3.setImage(step2);
        iv3.setFitHeight(100);
        iv3.setFitWidth(150);

        rightMenu3.setPadding(new Insets(50, 40, 30, 30));
        rightMenu3.setSpacing(20);


        VBox vcart1 = new VBox();
        Label carttit1 = new Label("Shopping Cart");
        carttit1.setStyle("-fx-font: 15 arial;");
        ordertot1.setStyle("-fx-font: 15 arial;");

        vcart1.getChildren().addAll(carttit1, d, ordertot1);

        rightMenu3.setAlignment(Pos.CENTER);
        rightMenu3.getChildren().addAll(iv3, vcart1);


        //Scene 2 Center Menu Items - Call method to load all the smoothies from that table
        RetrieveDrinks.getData(drinkData, SQL);

        ArrayList<Double> drinkDataCosts2 = new ArrayList<>();
        ArrayList<Integer> smoothiedrinkIDs = new ArrayList<>();

        RetrieveDrinks.getDataID(smoothiedrinkIDs, SQLb);
        RetrieveDrinks.getDataCosts(drinkDataCosts2, SQLa);

        System.out.println(smoothiedrinkIDs);


        Button smoothieTypes[] = new Button[drinkData.size()];
        for (int i=0; i<drinkData.size(); i++) {


            String smooType = drinkData.get(i).toString();
            String smooCost = drinkDataCosts2.get(i).toString();
            Double smooCostb = drinkDataCosts2.get(i);



            Tooltip t = new Tooltip("Cost: " + smooCost);

            smoothieTypes[i] = new Button(smooType.substring(1, smooType.length() - 1));
            smoothieTypes[i].setTooltip(t);
            smoothieTypes[i].setAlignment(Pos.CENTER);
            smoothieTypes[i].setMinSize(200, 75);
            int tracker = i;

            smoothieTypes[i].setOnAction(e ->{
                String smooType2 = smoothieTypes[tracker].getText();
                cart =  smooType2 + "    ("+smooCost+")";

                addCart("Item " + (iT+1) + " " + cart, cartItems);
                addCart("Item " + (iT+1) + " " + cart, fincartItemsGo);
                typeTot += smooCostb;
                runningTot += typeTot;

                finalsmoothieID = smoothiedrinkIDs.get(tracker);
                System.out.println("Selected smoothie ID " +finalsmoothieID);

                window.setScene(scene4);
                ordertot2.setText("Order Total: $" + (decimalFormat.format(runningTot)));
            });

            smoothFlow.getChildren().add(smoothieTypes[i]);
        }


        /*=============CUSTOMER TEA PAGE=============== */

        //Top Menu Items
        Label lab1a = new Label("What flavor would you like?");
        lab1a.setPadding(new Insets(30, 30, 30, 30));
        lab1a.setStyle("-fx-font: 35 arial; -fx-base: #FFC524");
        topMenu3a.getChildren().addAll(lab1a);

        buildCart(da);
        da.setItems(cartItems);

        //Bottom Menu Items
        bottomMenu3a.setPadding(new Insets(30, 30, 30, 30));
        bottomMenu3a.setSpacing(30);

        Button ButtonA3a = new Button("Go Back");
        ButtonA3a.setStyle("-fx-font: 20 arial; -fx-base: #FFC524");
        ButtonA3a.setMinWidth(200);
        ButtonA3a.setAlignment(Pos.CENTER);

        Button ButtonB3a = new Button("Cancel Order");
        ButtonB3a.setStyle("-fx-font: 20 arial; -fx-base: #FFC524");
        ButtonB3a.setMinWidth(200);
        ButtonB3a.setAlignment(Pos.CENTER);

        Button checkoutReturn3a = new Button("Return");
        checkoutReturn3a.setStyle("-fx-font: 20 arial; -fx-base: #7CD674");
        checkoutReturn3a.setMinWidth(200);
        checkoutReturn3a.setAlignment(Pos.CENTER);
        checkoutReturn3a.setVisible(false);
        checkoutReturn3a.setOnAction(e ->
        {
            window.setScene(scene6);
            clearCart(c);
            clearCart(d);
            clearCart(da);
            clearCart(db);
            clearCart(dc);
            clearCart(dd);
            clearCart(f);
            clearCart(g);

            //Whenever cancel is clicked everything should reset to default values
            tempTot = 0.0;
            tempTotDiff = 0.0;
            typeTot = 0.0;
            exIngTot = 0.0;
            exIngTotDiff = 0.0;
            runningTot = 0.0;
            finTot = 0.0;
            iT -= 1;



            sizeNum = 1;
            iceNum = 2;
            sugarNum = 2;
            tempNum = 2;
            qtNum = 1;

        });

        bottomMenu3a.setMinHeight(-10);
        bottomMenu3a.getChildren().addAll(ButtonA3a, ButtonB3a, checkoutReturn3a);

        //Clear cart whenever the customer goes back or clicks cancel
        ButtonB3a.setOnAction(e -> {
            boolean ans = ConfirmOrder.display("Confirm Cancel", "Are you sure? Canceling will delete all progress!");

            if (ans == true) {
                Okay.display("Sad Day:(", "Please come again!");
                window.setScene(scenehelp);
                clearCart(g);
                clearCart(finCart);

                //Whenever cancel is clicked everything should reset to default values
                tempTot = 0.0;
                tempTotDiff = 0.0;
                typeTot = 0.0;
                exIngTot = 0.0;
                exIngTotDiff = 0.0;
                runningTot = 0.0;
                finTot = 0.0;
                realfinTot = 0.0;
                iT = 0;

                finaltoppingID = 0;
                finalsmoothieID = 0;
                finalteaID = 0;
                finalcoffeeID = 0;
                finalmilkteaID = 0;
                finalfrioID = 0;

                ButtonA.setVisible(true);
                checkoutReturn.setVisible(false);
                checkoutReturn3.setVisible(false);


                sizeNum = 1;
                iceNum = 2;
                sugarNum = 2;
                tempNum = 2;
                qtNum = 1;

                //Defaults
                qtImage.setGraphic(new ImageView(defaultQT));
                tempImage.setGraphic(new ImageView(defaultTemp));
                iceImage.setGraphic(new ImageView(defaultIce));
                Size.setGraphic(new ImageView(defaultSize));
                sugarImage.setGraphic(new ImageView(defaultSugar));


                ordertot2.setText("Order Total: $" + (decimalFormat.format(runningTot)));
                ordertot3.setText("Order Total: $" + (decimalFormat.format(runningTot)));
                ordertot4.setText("Order Total: $" + (decimalFormat.format(runningTot)));
            } else {
                Okay.display("Continue", "Please Continue!");
            }
        });
        ButtonA3a.setOnAction(e -> {
            window.setScene(scene2);
            clearCart(da);
            typeTot = 0.0;
        });




        //Right Menu - Image
        ImageView iv3a = new ImageView();
        Image step2a = new Image("step2.png");
        iv3a.setImage(step2a);
        iv3a.setFitHeight(100);
        iv3a.setFitWidth(150);

        rightMenu3a.setPadding(new Insets(50, 40, 30, 30));
        rightMenu3a.setSpacing(20);


        VBox vcart1a = new VBox();
        Label carttit1a = new Label("Shopping Cart");
        carttit1a.setStyle("-fx-font: 15 arial;");
        ordertot1a.setStyle("-fx-font: 15 arial;");

        vcart1a.getChildren().addAll(carttit1a, da, ordertot1a);

        rightMenu3a.setAlignment(Pos.CENTER);
        rightMenu3a.getChildren().addAll(iv3a, vcart1a);


        //Scene 2 Center Menu Items - Call method to load all the smoothies from that table
        RetrieveDrinks.getData(teaData, SQL2);

        ArrayList<Double> drinkDataCosts2a = new ArrayList<>();
        ArrayList<Integer> teaIDs = new ArrayList<>();

        RetrieveDrinks.getDataID(teaIDs, SQL2b);
        RetrieveDrinks.getDataCosts(drinkDataCosts2a, SQL2a);

        System.out.println(teaIDs);


        Button teaTypes[] = new Button[teaData.size()];
        for (int i=0; i<teaData.size(); i++) {


            String teaType = teaData.get(i).toString();
            String teaCost = drinkDataCosts2a.get(i).toString();
            Double teaCostb = drinkDataCosts2a.get(i);



            Tooltip ta = new Tooltip("Cost: " + teaCost);

            teaTypes[i] = new Button(teaType.substring(1, teaType.length() - 1));
            teaTypes[i].setTooltip(ta);
            teaTypes[i].setAlignment(Pos.CENTER);
            teaTypes[i].setMinSize(200, 75);
            int tracker = i;

            teaTypes[i].setOnAction(e ->{
                String teaType2 = teaTypes[tracker].getText();
                cart =  teaType2 + "    ("+teaCost+")";

                addCart("Item " + (iT+1) + " " + cart, cartItems);
                addCart("Item " + (iT+1) + " " + cart, fincartItemsGo);
                typeTot += teaCostb;
                runningTot += typeTot;

                finalteaID = teaIDs.get(tracker);
                System.out.println("Selected tea ID " +finalteaID);



                window.setScene(scene4);
                ordertot2.setText("Order Total: $" + (decimalFormat.format(runningTot)));
            });



            smoothFlowa.getChildren().add(teaTypes[i]);
        }

        /*=============CUSTOMER COFFEE PAGE=============== */

        //Top Menu Items
        Label lab1b = new Label("What flavor would you like?");
        lab1b.setPadding(new Insets(30, 30, 30, 30));
        lab1b.setStyle("-fx-font: 35 arial; -fx-base: #FFC524");
        topMenu3b.getChildren().addAll(lab1b);

        buildCart(db);
        db.setItems(cartItems);

        //Bottom Menu Items
        bottomMenu3b.setPadding(new Insets(30, 30, 30, 30));
        bottomMenu3b.setSpacing(30);

        Button ButtonA3b = new Button("Go Back");
        ButtonA3b.setStyle("-fx-font: 20 arial; -fx-base: #FFC524");
        ButtonA3b.setMinWidth(200);
        ButtonA3b.setAlignment(Pos.CENTER);

        Button ButtonB3b = new Button("Cancel Order");
        ButtonB3b.setStyle("-fx-font: 20 arial; -fx-base: #FFC524");
        ButtonB3b.setMinWidth(200);
        ButtonB3b.setAlignment(Pos.CENTER);

        Button checkoutReturn3b = new Button("Return");
        checkoutReturn3b.setStyle("-fx-font: 20 arial; -fx-base: #7CD674");
        checkoutReturn3b.setMinWidth(200);
        checkoutReturn3b.setAlignment(Pos.CENTER);
        checkoutReturn3b.setVisible(false);
        checkoutReturn3b.setOnAction(e ->
        {
            window.setScene(scene6);
            clearCart(c);
            clearCart(d);
            clearCart(da);
            clearCart(db);
            clearCart(dc);
            clearCart(dd);
            clearCart(f);
            clearCart(g);

            //Whenever cancel is clicked everything should reset to default values
            tempTot = 0.0;
            tempTotDiff = 0.0;
            typeTot = 0.0;
            exIngTot = 0.0;
            exIngTotDiff = 0.0;
            runningTot = 0.0;
            finTot = 0.0;
            iT -= 1;



            sizeNum = 1;
            iceNum = 2;
            sugarNum = 2;
            tempNum = 2;
            qtNum = 1;

        });

        bottomMenu3b.setMinHeight(-10);
        bottomMenu3b.getChildren().addAll(ButtonA3b, ButtonB3b, checkoutReturn3b);

        //Clear cart whenever the customer goes back or clicks cancel
        ButtonB3b.setOnAction(e -> {
            boolean ans = ConfirmOrder.display("Confirm Cancel", "Are you sure? Canceling will delete all progress!");

            if (ans == true) {
                Okay.display("Sad Day:(", "Please come again!");
                window.setScene(scenehelp);
                clearCart(g);
                clearCart(finCart);

                //Whenever cancel is clicked everything should reset to default values
                tempTot = 0.0;
                tempTotDiff = 0.0;
                typeTot = 0.0;
                exIngTot = 0.0;
                exIngTotDiff = 0.0;
                runningTot = 0.0;
                finTot = 0.0;
                realfinTot = 0.0;
                iT = 0;

                finaltoppingID = 0;
                finalsmoothieID = 0;
                finalteaID = 0;
                finalcoffeeID = 0;
                finalmilkteaID = 0;
                finalfrioID = 0;

                ButtonA.setVisible(true);
                checkoutReturn.setVisible(false);
                checkoutReturn3.setVisible(false);


                sizeNum = 1;
                iceNum = 2;
                sugarNum = 2;
                tempNum = 2;
                qtNum = 1;

                //Defaults
                qtImage.setGraphic(new ImageView(defaultQT));
                tempImage.setGraphic(new ImageView(defaultTemp));
                iceImage.setGraphic(new ImageView(defaultIce));
                Size.setGraphic(new ImageView(defaultSize));
                sugarImage.setGraphic(new ImageView(defaultSugar));


                ordertot2.setText("Order Total: $" + (decimalFormat.format(runningTot)));
                ordertot3.setText("Order Total: $" + (decimalFormat.format(runningTot)));
                ordertot4.setText("Order Total: $" + (decimalFormat.format(runningTot)));
            } else {
                Okay.display("Continue", "Please Continue!");
            }
        });
        ButtonA3b.setOnAction(e -> {
            window.setScene(scene2);
            clearCart(db);
            typeTot = 0.0;
        });




        //Right Menu - Image
        ImageView iv3b = new ImageView();
        Image step2b = new Image("step2.png");
        iv3b.setImage(step2b);
        iv3b.setFitHeight(100);
        iv3b.setFitWidth(150);

        rightMenu3b.setPadding(new Insets(50, 40, 30, 30));
        rightMenu3b.setSpacing(20);


        VBox vcart1b = new VBox();
        Label carttit1b = new Label("Shopping Cart");
        carttit1b.setStyle("-fx-font: 15 arial;");
        ordertot1b.setStyle("-fx-font: 15 arial;");

        vcart1b.getChildren().addAll(carttit1b, db, ordertot1b);

        rightMenu3b.setAlignment(Pos.CENTER);
        rightMenu3b.getChildren().addAll(iv3b, vcart1b);


        //Scene 2 Center Menu Items - Call method to load all the smoothies from that table
        RetrieveDrinks.getData(coffeeData, SQL3);

        ArrayList<Double> drinkDataCosts2b = new ArrayList<>();
        ArrayList<Integer> coffeeIDs = new ArrayList<>();

        RetrieveDrinks.getDataID(coffeeIDs, SQL3b);
        RetrieveDrinks.getDataCosts(drinkDataCosts2b, SQL3a);

        System.out.println(coffeeIDs);


        Button coffeeTypes[] = new Button[coffeeData.size()];
        for (int i=0; i<coffeeData.size(); i++) {


            String coffeeType = coffeeData.get(i).toString();
            String coffeeCost = drinkDataCosts2b.get(i).toString();
            Double coffeeCostb = drinkDataCosts2b.get(i);



            Tooltip tb = new Tooltip("Cost: " + coffeeCost);

            coffeeTypes[i] = new Button(coffeeType.substring(1, coffeeType.length() - 1));
            coffeeTypes[i].setTooltip(tb);
            coffeeTypes[i].setAlignment(Pos.CENTER);
            coffeeTypes[i].setMinSize(200, 75);
            int tracker = i;

            coffeeTypes[i].setOnAction(e ->{
                String coffeeType2 = coffeeTypes[tracker].getText();
                cart =  coffeeType2 + "    ("+coffeeCost+")";

                addCart("Item " + (iT+1) + " " + cart, cartItems);
                addCart("Item " + (iT+1) + " " + cart, fincartItemsGo);
                typeTot += coffeeCostb;
                runningTot += typeTot;

                finalcoffeeID = coffeeIDs.get(tracker);
                System.out.println("Selected coffee ID " +finalcoffeeID);



                window.setScene(scene4);
                ordertot2.setText("Order Total: $" + (decimalFormat.format(runningTot)));
            });



            smoothFlowb.getChildren().add(coffeeTypes[i]);
        }

        /*=============CUSTOMER MILK TEA PAGE=============== */

        //Top Menu Items
        Label lab1c = new Label("What flavor would you like?");
        lab1c.setPadding(new Insets(30, 30, 30, 30));
        lab1c.setStyle("-fx-font: 35 arial; -fx-base: #FFC524");
        topMenu3c.getChildren().addAll(lab1c);

        buildCart(dc);
        dc.setItems(cartItems);

        //Bottom Menu Items
        bottomMenu3c.setPadding(new Insets(30, 30, 30, 30));
        bottomMenu3c.setSpacing(30);

        Button ButtonA3c = new Button("Go Back");
        ButtonA3c.setStyle("-fx-font: 20 arial; -fx-base: #FFC524");
        ButtonA3c.setMinWidth(200);
        ButtonA3c.setAlignment(Pos.CENTER);

        Button ButtonB3c = new Button("Cancel Order");
        ButtonB3c.setStyle("-fx-font: 20 arial; -fx-base: #FFC524");
        ButtonB3c.setMinWidth(200);
        ButtonB3c.setAlignment(Pos.CENTER);

        Button checkoutReturn3c = new Button("Return");
        checkoutReturn3c.setStyle("-fx-font: 20 arial; -fx-base: #7CD674");
        checkoutReturn3c.setMinWidth(200);
        checkoutReturn3c.setAlignment(Pos.CENTER);
        checkoutReturn3c.setVisible(false);
        checkoutReturn3c.setOnAction(e ->
        {
            window.setScene(scene6);
            clearCart(c);
            clearCart(d);
            clearCart(da);
            clearCart(db);
            clearCart(dc);
            clearCart(dd);
            clearCart(f);
            clearCart(g);

            //Whenever cancel is clicked everything should reset to default values
            tempTot = 0.0;
            tempTotDiff = 0.0;
            typeTot = 0.0;
            exIngTot = 0.0;
            exIngTotDiff = 0.0;
            runningTot = 0.0;
            finTot = 0.0;
            iT -= 1;



            sizeNum = 1;
            iceNum = 2;
            sugarNum = 2;
            tempNum = 2;
            qtNum = 1;

        });

        bottomMenu3c.setMinHeight(-10);
        bottomMenu3c.getChildren().addAll(ButtonA3c, ButtonB3c, checkoutReturn3c);

        //Clear cart whenever the customer goes back or clicks cancel
        ButtonB3c.setOnAction(e -> {
            boolean ans = ConfirmOrder.display("Confirm Cancel", "Are you sure? Canceling will delete all progress!");

            if (ans == true) {
                Okay.display("Sad Day:(", "Please come again!");
                window.setScene(scenehelp);
                clearCart(g);
                clearCart(finCart);

                //Whenever cancel is clicked everything should reset to default values
                tempTot = 0.0;
                tempTotDiff = 0.0;
                typeTot = 0.0;
                exIngTot = 0.0;
                exIngTotDiff = 0.0;
                runningTot = 0.0;
                finTot = 0.0;
                realfinTot = 0.0;
                iT = 0;

                finaltoppingID = 0;
                finalsmoothieID = 0;
                finalteaID = 0;
                finalcoffeeID = 0;
                finalmilkteaID = 0;
                finalfrioID = 0;

                ButtonA.setVisible(true);
                checkoutReturn.setVisible(false);
                checkoutReturn3.setVisible(false);


                sizeNum = 1;
                iceNum = 2;
                sugarNum = 2;
                tempNum = 2;
                qtNum = 1;

                //Defaults
                qtImage.setGraphic(new ImageView(defaultQT));
                tempImage.setGraphic(new ImageView(defaultTemp));
                iceImage.setGraphic(new ImageView(defaultIce));
                Size.setGraphic(new ImageView(defaultSize));
                sugarImage.setGraphic(new ImageView(defaultSugar));


                ordertot2.setText("Order Total: $" + (decimalFormat.format(runningTot)));
                ordertot3.setText("Order Total: $" + (decimalFormat.format(runningTot)));
                ordertot4.setText("Order Total: $" + (decimalFormat.format(runningTot)));
            } else {
                Okay.display("Continue", "Please Continue!");
            }
        });
        ButtonA3c.setOnAction(e -> {
            window.setScene(scene2);
            clearCart(dc);
            typeTot = 0.0;
        });




        //Right Menu - Image
        ImageView iv3c = new ImageView();
        Image step2c = new Image("step2.png");
        iv3c.setImage(step2c);
        iv3c.setFitHeight(100);
        iv3c.setFitWidth(150);

        rightMenu3c.setPadding(new Insets(50, 40, 30, 30));
        rightMenu3c.setSpacing(20);


        VBox vcart1c = new VBox();
        Label carttit1c = new Label("Shopping Cart");
        carttit1c.setStyle("-fx-font: 15 arial;");
        ordertot1c.setStyle("-fx-font: 15 arial;");

        vcart1c.getChildren().addAll(carttit1c, dc, ordertot1c);

        rightMenu3c.setAlignment(Pos.CENTER);
        rightMenu3c.getChildren().addAll(iv3c, vcart1c);


        //Scene 2 Center Menu Items - Call method to load all the smoothies from that table
        RetrieveDrinks.getData(milkteaData, SQL4);

        ArrayList<Double> drinkDataCosts2c = new ArrayList<>();
        ArrayList<Integer> milkteaIDs = new ArrayList<>();

        RetrieveDrinks.getDataID(milkteaIDs, SQL4b);
        RetrieveDrinks.getDataCosts(drinkDataCosts2c, SQL4a);

        System.out.println(milkteaIDs);


        Button milkteaTypes[] = new Button[milkteaData.size()];
        for (int i=0; i<milkteaData.size(); i++) {


            String milkteaType = milkteaData.get(i).toString();
            String milkteaCost = drinkDataCosts2c.get(i).toString();
            Double milkteaCostb = drinkDataCosts2c.get(i);



            Tooltip tc = new Tooltip("Cost: " + milkteaCost);

            milkteaTypes[i] = new Button(milkteaType.substring(1, milkteaType.length() - 1));
            milkteaTypes[i].setTooltip(tc);
            milkteaTypes[i].setAlignment(Pos.CENTER);
            milkteaTypes[i].setMinSize(200, 75);
            int tracker = i;

            milkteaTypes[i].setOnAction(e ->{
                String milkteaType2 = milkteaTypes[tracker].getText();
                cart =  milkteaType2 + "    ("+milkteaCost+")";

                addCart("Item " + (iT+1) + " " + cart, cartItems);
                addCart("Item " + (iT+1) + " " + cart, fincartItemsGo);
                typeTot += milkteaCostb;
                runningTot += typeTot;

                finalmilkteaID = milkteaIDs.get(tracker);
                System.out.println("Selected milk tea ID " +finalmilkteaID);



                window.setScene(scene4);
                ordertot2.setText("Order Total: $" + (decimalFormat.format(runningTot)));
            });



            smoothFlowc.getChildren().add(milkteaTypes[i]);
        }

        /*=============CUSTOMER FRIO PAGE=============== */

        //Top Menu Items
        Label lab1d = new Label("What flavor would you like?");
        lab1d.setPadding(new Insets(30, 30, 30, 30));
        lab1d.setStyle("-fx-font: 35 arial; -fx-base: #FFC524");
        topMenu3d.getChildren().addAll(lab1d);

        buildCart(dd);
        dd.setItems(cartItems);

        //Bottom Menu Items
        bottomMenu3d.setPadding(new Insets(30, 30, 30, 30));
        bottomMenu3d.setSpacing(30);

        Button ButtonA3d = new Button("Go Back");
        ButtonA3d.setStyle("-fx-font: 20 arial; -fx-base: #FFC524");
        ButtonA3d.setMinWidth(200);
        ButtonA3d.setAlignment(Pos.CENTER);

        Button ButtonB3d = new Button("Cancel Order");
        ButtonB3d.setStyle("-fx-font: 20 arial; -fx-base: #FFC524");
        ButtonB3d.setMinWidth(200);
        ButtonB3d.setAlignment(Pos.CENTER);

        Button checkoutReturn3d = new Button("Return");
        checkoutReturn3d.setStyle("-fx-font: 20 arial; -fx-base: #7CD674");
        checkoutReturn3d.setMinWidth(200);
        checkoutReturn3d.setAlignment(Pos.CENTER);
        checkoutReturn3d.setVisible(false);
        checkoutReturn3d.setOnAction(e ->
        {
            window.setScene(scene6);
            clearCart(c);
            clearCart(d);
            clearCart(da);
            clearCart(db);
            clearCart(dc);
            clearCart(dd);
            clearCart(f);
            clearCart(g);

            //Whenever cancel is clicked everything should reset to default values
            tempTot = 0.0;
            tempTotDiff = 0.0;
            typeTot = 0.0;
            exIngTot = 0.0;
            exIngTotDiff = 0.0;
            runningTot = 0.0;
            finTot = 0.0;
            iT -= 1;



            sizeNum = 1;
            iceNum = 2;
            sugarNum = 2;
            tempNum = 2;
            qtNum = 1;

        });

        bottomMenu3d.setMinHeight(-10);
        bottomMenu3d.getChildren().addAll(ButtonA3d, ButtonB3d, checkoutReturn3d);

        //Clear cart whenever the customer goes back or clicks cancel
        ButtonB3d.setOnAction(e -> {
            boolean ans = ConfirmOrder.display("Confirm Cancel", "Are you sure? Canceling will delete all progress!");

            if (ans == true) {
                Okay.display("Sad Day:(", "Please come again!");
                window.setScene(scenehelp);
                clearCart(g);
                clearCart(finCart);

                //Whenever cancel is clicked everything should reset to default values
                tempTot = 0.0;
                tempTotDiff = 0.0;
                typeTot = 0.0;
                exIngTot = 0.0;
                exIngTotDiff = 0.0;
                runningTot = 0.0;
                finTot = 0.0;
                realfinTot = 0.0;
                iT = 0;

                finaltoppingID = 0;
                finalsmoothieID = 0;
                finalteaID = 0;
                finalcoffeeID = 0;
                finalmilkteaID = 0;
                finalfrioID = 0;

                ButtonA.setVisible(true);
                checkoutReturn.setVisible(false);
                checkoutReturn3.setVisible(false);


                sizeNum = 1;
                iceNum = 2;
                sugarNum = 2;
                tempNum = 2;
                qtNum = 1;

                //Defaults
                qtImage.setGraphic(new ImageView(defaultQT));
                tempImage.setGraphic(new ImageView(defaultTemp));
                iceImage.setGraphic(new ImageView(defaultIce));
                Size.setGraphic(new ImageView(defaultSize));
                sugarImage.setGraphic(new ImageView(defaultSugar));


                ordertot2.setText("Order Total: $" + (decimalFormat.format(runningTot)));
                ordertot3.setText("Order Total: $" + (decimalFormat.format(runningTot)));
                ordertot4.setText("Order Total: $" + (decimalFormat.format(runningTot)));
            } else {
                Okay.display("Continue", "Please Continue!");
            }
        });
        ButtonA3d.setOnAction(e -> {
            window.setScene(scene2);
            clearCart(dd);
            typeTot = 0.0;
        });




        //Right Menu - Image
        ImageView iv3d = new ImageView();
        Image step2d = new Image("step2.png");
        iv3d.setImage(step2d);
        iv3d.setFitHeight(100);
        iv3d.setFitWidth(150);

        rightMenu3d.setPadding(new Insets(50, 40, 30, 30));
        rightMenu3d.setSpacing(20);


        VBox vcart1d = new VBox();
        Label carttit1d = new Label("Shopping Cart");
        carttit1d.setStyle("-fx-font: 15 arial;");
        ordertot1d.setStyle("-fx-font: 15 arial;");

        vcart1d.getChildren().addAll(carttit1d, dd, ordertot1d);

        rightMenu3d.setAlignment(Pos.CENTER);
        rightMenu3d.getChildren().addAll(iv3d, vcart1d);


        //Scene 2 Center Menu Items - Call method to load all the smoothies from that table
        RetrieveDrinks.getData(frioData, SQL5);

        ArrayList<Double> drinkDataCosts2d = new ArrayList<>();
        ArrayList<Integer> frioIDs = new ArrayList<>();

        RetrieveDrinks.getDataID(frioIDs, SQL5b);
        RetrieveDrinks.getDataCosts(drinkDataCosts2d, SQL5a);

        System.out.println(frioIDs);


        Button frioTypes[] = new Button[frioData.size()];
        for (int i=0; i<frioData.size(); i++) {


            String frioType = frioData.get(i).toString();
            String frioCost = drinkDataCosts2d.get(i).toString();
            Double frioCostb = drinkDataCosts2d.get(i);



            Tooltip td = new Tooltip("Cost: " + frioCost);

            frioTypes[i] = new Button(frioType.substring(1, frioType.length() - 1));
            frioTypes[i].setTooltip(td);
            frioTypes[i].setAlignment(Pos.CENTER);
            frioTypes[i].setMinSize(200, 75);
            int tracker = i;

            frioTypes[i].setOnAction(e ->{
                String frioType2 = frioTypes[tracker].getText();
                cart =  frioType2 + "    ("+frioCost+")";

                addCart("Item " + (iT+1) + " " + cart, cartItems);
                addCart("Item " + (iT+1) + " " + cart, fincartItemsGo);
                typeTot += frioCostb;
                runningTot += typeTot;

                finalfrioID = frioIDs.get(tracker);
                System.out.println("Selected frio ID " +finalfrioID);



                window.setScene(scene4);
                ordertot2.setText("Order Total: $" + (decimalFormat.format(runningTot)));
            });



            smoothFlowd.getChildren().add(frioTypes[i]);
        }



     /*=============EXTRA INGREDIENT PAGE===============*/

        //Bottom Menu Buttons

        gb1.setStyle("-fx-font: 20 arial; -fx-base: #FFC524");
        gb1.setMinWidth(200);
        gb1.setAlignment(Pos.CENTER);
        gb1.setOnAction(e ->{

            fincartItemsGo.removeAll(cartItems);

            if (finalteaID > 0){
                window.setScene(scene3a);
            }
            else if (finalcoffeeID > 0){
                window.setScene(scene3b);
            }
            else if (finalmilkteaID > 0) {
                window.setScene(scene3c);
            }

            else if (finalfrioID > 0) {
                window.setScene(scene3d);
            }
            else{
                window.setScene(scene3);
            }

            clearCart(f);

            finaltoppingID = 0;
            finalsmoothieID = 0;
            finalteaID = 0;
            finalcoffeeID = 0;
            finalmilkteaID = 0;
            finalfrioID = 0;
            typeTot = 0.0;
            exIngTot = 0.0;
            exIngTotDiff = 0.0;
            runningTot = 0.0;

            ordertot2.setText("Order Total: $" + (decimalFormat.format(runningTot)));
            cont1.setText("Skip");
        });

        Button checkoutReturn2 = new Button("Return");
        checkoutReturn2.setStyle("-fx-font: 20 arial; -fx-base: #7CD674");
        checkoutReturn2.setMinWidth(200);
        checkoutReturn2.setAlignment(Pos.CENTER);
        checkoutReturn2.setVisible(false);
        checkoutReturn2.setOnAction(e ->
        {
            window.setScene(scene6);
            clearCart(c);
            clearCart(d);
            clearCart(da);
            clearCart(db);//Changed
            clearCart(dc);//Changed
            clearCart(dd);
            clearCart(f);
            clearCart(g);

            //Whenever cancel is clicked everything should reset to default values
            tempTot = 0.0;
            tempTotDiff = 0.0;
            typeTot = 0.0;
            exIngTot = 0.0;
            exIngTotDiff = 0.0;
            runningTot = 0.0;
            finTot = 0.0;
            iT -= 1;



            sizeNum = 1;
            iceNum = 2;
            sugarNum = 2;
            tempNum = 2;
            qtNum = 1;

        });




        //Top Menu Items
        Label l4 = new Label("Add Extra Ingredients?");
        l4.setPadding(new Insets(30, 30, 30, 30));
        l4.setStyle("-fx-font: 35 arial; -fx-base: #FFC524");
        topMenu4.getChildren().addAll(l4);

        buildCart(f);
        f.setItems(cartItems);


        //Center Menu
        //Scene 3 Center Menu Items - Call method to load all the extra ingredients from that table

        RetrieveDrinks.getData(toppingData, SQL6);
        ArrayList<Double> toppingDataCosts2 = new ArrayList<>();
        ArrayList<Integer> extraingredientIDs = new ArrayList<>();

        RetrieveDrinks.getDataCosts(toppingDataCosts2, SQL6a);
        RetrieveDrinks.getDataID(extraingredientIDs, SQL6b);

        System.out.println(extraingredientIDs);


        Button toppingTypes[] = new Button[toppingData.size()];
        for (int i=0; i<toppingData.size(); i++) {


            String topType = toppingData.get(i).toString();
            String topCost = toppingDataCosts2.get(i).toString();
            Double topCostb = toppingDataCosts2.get(i);




            Tooltip t1 = new Tooltip("Cost: " + topCostb);

            toppingTypes[i] = new Button(topType.substring(1, topType.length() - 1));
            toppingTypes[i].setTooltip(t1);
            toppingTypes[i].setAlignment(Pos.CENTER);
            toppingTypes[i].setMinSize(200, 75);
            int tracker1 = i;


            toppingTypes[i].setOnAction(e ->{
                String topType2 = toppingTypes[tracker1].getText();
                cart =  "    -" + topType2 + "    ("+topCost+")";


                addCart((iT+1+")") +cart, cartItems);
                addCart((iT+1+")") +cart, excartItems);
                addCart((iT+1+")") +cart, ftcartItems);
                addCart((iT+1+")") +cart, fincartItemsGo);

                addCart((iT+1+")")+"    -Size (Small)", cartItems);
                addCart((iT+1+")")+"    -Ice  (Regular)", cartItems);
                addCart((iT+1+")")+"    -Sugar  (Regular)", cartItems);
                addCart((iT+1+")")+"    -Temp.  (Regular)", cartItems);
                addCart((iT+1+")")+"    -Quantity (1)", cartItems);


                addCart((iT+1+")")+"    -Size (Small)", ftcartItems);
                addCart((iT+1+")")+"    -Ice  (Regular)", ftcartItems2);
                addCart((iT+1+")")+"    -Sugar  (Regular)", ftcartItems3);
                addCart((iT+1+")")+"    -Temp.  (Regular)", ftcartItems4);
                addCart((iT+1+")")+"    -Quantity (1)", ftcartItems5);

                addCart((iT+1+")")+"    -Size (Small)", fincartItemsGo);
                addCart((iT+1+")")+"    -Ice  (Regular)", fincartItemsGo);
                addCart((iT+1+")")+"    -Sugar  (Regular)", fincartItemsGo);
                addCart((iT+1+")")+"    -Temp.  (Regular)", fincartItemsGo);
                addCart((iT+1+")")+"    -Quantity (1)", fincartItemsGo);



                exIngTot += topCostb;
                runningTot += exIngTot;

                finaltoppingID = extraingredientIDs.get(tracker1);
                System.out.println("Selected topping ID " + finaltoppingID);

                window.setScene(scene5);
                ordertot3.setText("Order Total: $" + (decimalFormat.format(runningTot)));
            });

            exFlow.getChildren().add(toppingTypes[i]);
        }



        //Bottom Menu Items
        bottomMenu4.setPadding(new Insets(30, 30, 30, 30));
        bottomMenu4.setSpacing(30);

        //Go Back - gb1 original


        //Cancel Order
        co1.setStyle("-fx-font: 20 arial; -fx-base: #FFC524");
        co1.setMinWidth(200);
        co1.setAlignment(Pos.CENTER);
        co1.setOnAction(e -> {

            boolean ans = ConfirmOrder.display("Confirm Cancel", "Are you sure? Canceling will delete all progress!");

            if (ans == true) {
                Okay.display("Sad Day:(", "Please come again!");
                window.setScene(scenehelp);
                clearCart(g);
                clearCart(finCart);

                //Whenever cancel is clicked everything should reset to default values
                tempTot = 0.0;
                tempTotDiff = 0.0;
                typeTot = 0.0;
                exIngTot = 0.0;
                exIngTotDiff = 0.0;
                runningTot = 0.0;
                finTot = 0.0;
                realfinTot = 0.0;
                iT = 0;

                finaltoppingID = 0;
                finalsmoothieID = 0;
                finalteaID = 0;
                finalcoffeeID = 0;
                finalmilkteaID = 0;
                finalfrioID = 0;

                ButtonA.setVisible(true);
                checkoutReturn.setVisible(false);
                checkoutReturn3.setVisible(false);


                sizeNum = 1;
                iceNum = 2;
                sugarNum = 2;
                tempNum = 2;
                qtNum = 1;

                //Defaults
                qtImage.setGraphic(new ImageView(defaultQT));
                tempImage.setGraphic(new ImageView(defaultTemp));
                iceImage.setGraphic(new ImageView(defaultIce));
                Size.setGraphic(new ImageView(defaultSize));
                sugarImage.setGraphic(new ImageView(defaultSugar));


                ordertot2.setText("Order Total: $" + (decimalFormat.format(runningTot)));
                ordertot3.setText("Order Total: $" + (decimalFormat.format(runningTot)));
                ordertot4.setText("Order Total: $" + (decimalFormat.format(runningTot)));
            } else {
                Okay.display("Continue", "Please Continue!");
            }

        });


        //Skip/Continue
        cont1.setStyle("-fx-font: 20 arial; -fx-base: #7CD674");
        cont1.setMinWidth(200);
        cont1.setAlignment(Pos.CENTER);
        cont1.setOnAction(e -> {
            window.setScene(scene5);

            addCart((iT+1+")")+"    -Size (Small)", cartItems);
            addCart((iT+1+")")+"    -Ice  (Regular)", cartItems);
            addCart((iT+1+")")+"    -Sugar  (Regular)", cartItems);
            addCart((iT+1+")")+"    -Temp.  (Regular)", cartItems);
            addCart((iT+1+")")+"    -Quantity (1)", cartItems);


            addCart((iT+1+")")+"    -Size (Small)", ftcartItems);
            addCart((iT+1+")")+"    -Ice  (Regular)", ftcartItems2);
            addCart((iT+1+")")+"    -Sugar  (Regular)", ftcartItems3);
            addCart((iT+1+")")+"    -Temp.  (Regular)", ftcartItems4);
            addCart((iT+1+")")+"    -Quantity (1)", ftcartItems5);

            addCart((iT+1+")")+"    -Size (Small)", fincartItemsGo);
            addCart((iT+1+")")+"    -Ice  (Regular)", fincartItemsGo);
            addCart((iT+1+")")+"    -Sugar  (Regular)", fincartItemsGo);
            addCart((iT+1+")")+"    -Temp.  (Regular)", fincartItemsGo);
            addCart((iT+1+")")+"    -Quantity (1)", fincartItemsGo);

            ordertot3.setText("Order Total: $" + (decimalFormat.format(runningTot)));
        });



        bottomMenu4.getChildren().addAll(gb1, co1, cont1, checkoutReturn2);




        //Right Menu - Image
        ImageView iv4 = new ImageView();
        Image step3 = new Image("step3.png");
        iv4.setImage(step3);
        iv4.setFitHeight(100);
        iv4.setFitWidth(100);

        rightMenu4.setPadding(new Insets(50, 40, 30, 30));
        rightMenu4.setSpacing(20);


        VBox vcart2 = new VBox();
        Label carttit2 = new Label("Shopping Cart");

        carttit2.setStyle("-fx-font: 15 arial;");
        ordertot2.setStyle("-fx-font: 15 arial;");

        vcart2.setMinHeight(175);
        vcart2.getChildren().addAll(carttit2, f, ordertot2);

        /*
        Button removeItem = new Button ("Remove All Extra Ingredients");
        removeItem.setStyle("-fx-base: #FFC524");
        removeItem.setOnAction(e ->{
            cartItems.removeAll(excartItems);

            ordertot2.setText("Order Total: $" + (typeTot));

            runningTot -= exIngTot;

            exIngTot = 0.0;
            exIngTotDiff = 0.0;

            cont1.setText("Skip");
        });
*/

        rightMenu4.setAlignment(Pos.CENTER);
        rightMenu4.getChildren().addAll(iv4, vcart2);


        /*=============SCENE 5 - FINE TUNE PAGE===============*/

        //Bottom Menu Buttons
        Button gb2 = new Button("Go Back");
        Button co2 = new Button("Cancel Order");
        Button cont2 = new Button("Continue");




        //Top Menu Items
        Label ftL1 = new Label("Fine Tune your drink! ");
        ftL1.setPadding(new Insets(30, 30, 30, 30));
        ftL1.setStyle("-fx-font: 35 arial; -fx-base: #FFC524");
        topMenu5.getChildren().addAll(ftL1);

        buildCart(g);

        g.setItems(cartItems);


        //CENTER MENU GRID

        //LABELS

        Label sizeTop = new Label("Size");
        GridPane.setHalignment(sizeTop, HPos.CENTER);

        Label Ice = new Label("Ice");

        GridPane.setHalignment(Ice, HPos.CENTER);

        Label Sugar = new Label("Sugar");

        GridPane.setHalignment(Sugar, HPos.CENTER);
        GridPane.setHalignment(sugarImage, HPos.CENTER);
        sugarImage.setMaxSize(35, 35);

        Label Temp = new Label("Temp.");

        GridPane.setHalignment(Temp, HPos.CENTER);
        GridPane.setHalignment(tempImage, HPos.CENTER);
        tempImage.setMaxSize(35, 35);

        Label QT = new Label("Qty");

        GridPane.setHalignment(QT, HPos.CENTER);
        GridPane.setHalignment(qtImage, HPos.CENTER);
        qtImage.setMaxSize(35, 35);



        //SIZE
        /*
        Default (Small) = 1
        Medium = 2
        Large = 3
         */




        Image medSize = new Image ("size2.png");
        Image largeSize = new Image ("size3.png");
        Size.setGraphic(new ImageView(defaultSize));

        //Size Label
        sizeTop.setStyle("-fx-font: 20 arial;");
        GridPane.setHalignment(Size, HPos.CENTER);
        GridPane.setConstraints(sizeTop, 1, 0);



        Button ftSI = new Button();
        ftSI.setStyle("-fx-background-image: url('plus.png')");
        ftSI.setMinSize(75, 37);


        ftSI.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {



                if (sizeNum == 1) {

                    cartItems.removeAll(ftcartItems);
                    fincartItemsGo.removeAll(ftcartItems);

                    addCart((iT+1+")")+"    -Size (Medium)", cartItems);
                    addCart((iT+1+")")+"    -Size (Medium)", ftcartItems);
                    addCart((iT+1+")")+"    -Size (Medium)", fincartItemsGo);


                    Size.setGraphic(new ImageView(medSize));
                    sizeNum += 1;
                    if (sizeNum == 2){

                        if (qtNum == 1){
                            tempTot = ((runningTot+.5));
                        }

                        else if (qtNum == 2){
                            tempTot = ((runningTot+.5)*2);
                        }

                        else if (qtNum == 3){
                            tempTot = ((runningTot+.5)*3);
                        }

                        else if (qtNum == 4){
                            tempTot = ((runningTot+.5)*4);
                        }

                        else if (qtNum == 5){
                            tempTot = ((runningTot+.5)*5);
                        }

                        //runningTot = runningTot + .5;
                        ordertot3.setText("Order Total: $" + (decimalFormat.format(tempTot)));

                    }

                } else if (sizeNum == 2) {

                    cartItems.removeAll(ftcartItems);
                    fincartItemsGo.removeAll(ftcartItems);

                    addCart((iT+1+")")+"    -Size (Large)", cartItems);
                    addCart((iT+1+")")+"    -Size (Large)", ftcartItems);
                    addCart((iT+1+")")+"    -Size (Large)", fincartItemsGo);

                    sizeNum += 1;
                    Size.setGraphic(new ImageView(largeSize));

                    if (sizeNum == 3){

                        if (qtNum == 1){
                            tempTot = ((runningTot+1));
                        }

                        else if (qtNum == 2){
                            tempTot = ((runningTot+1)*2);
                        }

                        else if (qtNum == 3){
                            tempTot = ((runningTot+1)*3);
                        }

                        else if (qtNum == 4){
                            tempTot = ((runningTot+1)*4);
                        }

                        else if (qtNum == 5){
                            tempTot = ((runningTot+1)*5);
                        }

                        //runningTot = runningTot + .5;
                        ordertot3.setText("Order Total: $" + (decimalFormat.format(tempTot)));

                    }
                }
            }
        });

        GridPane.setConstraints(ftSI, 1, 1);

        Size.setAlignment(Pos.CENTER);
        Size.setStyle("-fx-font: 16.5 arial;");


        GridPane.setConstraints(Size, 1, 2);
        Size.setMaxSize(35, 35);

        Button ftSD = new Button();
        ftSD.setStyle("-fx-background-image: url('minus.png')");
        ftSD.setMinSize(75, 37);
        ftSD.setOnAction(e -> {
            //Size can either be 2 or 3 for if
            if (sizeNum == 2){

                cartItems.removeAll(ftcartItems);
                fincartItemsGo.removeAll(ftcartItems);

                addCart((iT+1+")")+"    -Size (Small)", cartItems);
                addCart((iT+1+")")+"    -Size (Small)", ftcartItems);
                addCart((iT+1+")")+"    -Size (Small)", fincartItemsGo);

                Size.setGraphic(new ImageView(defaultSize));

                sizeNum -= 1;

                if (sizeNum == 1){

                    if (qtNum == 1){
                        tempTot = ((runningTot));
                    }

                    else if (qtNum == 2){
                        tempTot = ((runningTot)*2);
                    }

                    else if (qtNum == 3){
                        tempTot = ((runningTot)*3);
                    }

                    else if (qtNum == 4){
                        tempTot = ((runningTot)*4);
                    }

                    else if (qtNum == 5){
                        tempTot = ((runningTot)*5);
                    }

                    //runningTot = runningTot - .5;
                    ordertot3.setText("Order Total: $" + (decimalFormat.format(tempTot)));
                }



            }

            else if (sizeNum == 3){


                cartItems.removeAll(ftcartItems);
                fincartItemsGo.removeAll(ftcartItems);

                addCart((iT+1+")")+"    -Size (Medium)", cartItems);
                addCart((iT+1+")")+"    -Size (Medium)", ftcartItems);
                addCart((iT+1+")")+"    -Size (Medium)", fincartItemsGo);

                Size.setGraphic(new ImageView(medSize));
                sizeNum -= 1;
                if (sizeNum == 2){
                    if (qtNum == 1){
                        tempTot = ((runningTot+.5));
                    }

                    else if (qtNum == 2){
                        tempTot = ((runningTot+.5)*2);
                    }

                    else if (qtNum == 3){
                        tempTot = ((runningTot+.5)*3);
                    }

                    else if (qtNum == 4){
                        tempTot = ((runningTot+.5)*4);
                    }

                    else if (qtNum == 5){
                        tempTot = ((runningTot+.5)*5);
                    }
                    //runningTot = runningTot - .5;
                    ordertot3.setText("Order Total: $" + (decimalFormat.format(tempTot)));
                }
            }
        });

        GridPane.setConstraints(ftSD, 1, 3);


        //ICE
        /*
        Default (Small) = 1
        Medium = 2
        Large = 3
         */

        //Ice Label
        Ice.setStyle("-fx-font: 20 arial;");
        GridPane.setConstraints(Ice, 2, 0);



        Image medIce = new Image ("ice1.png");
        Image largeIce = new Image ("ice3.png");
        iceImage.setGraphic(new ImageView(defaultIce));

        iceImage.setMaxSize(50, 50);

        iceImage.setAlignment(Pos.CENTER);
        GridPane.setHalignment(iceImage, HPos.CENTER);


        Button ftII = new Button();
        ftII.setStyle("-fx-background-image: url('plus.png')");
        ftII.setMinSize(75, 37);
        ftII.setOnAction(e ->{


            if (iceNum == 1) {

                cartItems.removeAll(ftcartItems2);
                fincartItemsGo.removeAll(ftcartItems2);
                addCart((iT+1+")")+"    -Ice (Regular)", cartItems);
                addCart((iT+1+")")+"    -Ice (Regular)", ftcartItems2);
                addCart((iT+1+")")+"    -Ice (Regular)", fincartItemsGo);


                iceImage.setGraphic(new ImageView(defaultIce));
                iceNum += 1;
                iceID = 3;

            } else if (iceNum == 2) {

                cartItems.removeAll(ftcartItems2);
                fincartItemsGo.removeAll(ftcartItems2);
                addCart((iT+1+")")+"    -Ice (Extra)", cartItems);
                addCart((iT+1+")")+"    -Ice (Extra)", ftcartItems2);
                addCart((iT+1+")")+"    -Ice (Extra)", fincartItemsGo);

                iceNum += 1;
                iceImage.setGraphic(new ImageView(largeIce));
                iceID = 5;
            }
        });

        GridPane.setConstraints(ftII, 2, 1);

        GridPane.setConstraints(iceImage, 2, 2);




        Button ftID = new Button();
        ftID.setStyle("-fx-background-image: url('minus.png')");
        ftID.setMinSize(75, 37);
        ftID.setOnAction(e -> {
            //Size can either be 2 or 3 for if
            if (iceNum == 2){

                cartItems.removeAll(ftcartItems2);
                fincartItemsGo.removeAll(ftcartItems2);
                addCart((iT+1+")")+"    -Ice (Less)", cartItems);
                addCart((iT+1+")")+"    -Ice (Less)", ftcartItems2);
                addCart((iT+1+")")+"    -Ice (Less)", fincartItemsGo);

                iceImage.setGraphic(new ImageView(medIce));
                iceNum -= 1;
                iceID = 2;
            }
            else if (iceNum == 3){

                cartItems.removeAll(ftcartItems2);
                fincartItemsGo.removeAll(ftcartItems2);
                addCart((iT+1+")")+"    -Ice (Regular)", cartItems);
                addCart((iT+1+")")+"    -Ice (Regular)", ftcartItems2);
                addCart((iT+1+")")+"    -Ice (Regular)", fincartItemsGo);

                iceNum -= 1;
                iceImage.setGraphic(new ImageView(defaultIce));
                iceID = 3;
            }
        });
        GridPane.setConstraints(ftID, 2, 3);


        //Sugar

        //Sugar Label
        Sugar.setStyle("-fx-font: 20 arial;");
        GridPane.setConstraints(Sugar, 3, 0);


        Image medSugar = new Image ("sugar1.png");
        Image largeSugar = new Image ("sugar3.png");
        sugarImage.setGraphic(new ImageView(defaultSugar));


        Button ftSugI = new Button();
        ftSugI.setStyle("-fx-background-image: url('plus.png')");
        ftSugI.setMinSize(75, 37);
        ftSugI.setOnAction(e ->{
            if (sugarNum == 1) {

                cartItems.removeAll(ftcartItems3);
                fincartItemsGo.removeAll(ftcartItems3);
                addCart((iT+1+")")+"    -Sugar (Regular)", cartItems);
                addCart((iT+1+")")+"    -Sugar (Regular)", ftcartItems3);
                addCart((iT+1+")")+"    -Sugar (Regular)", fincartItemsGo);

                sugarImage.setGraphic(new ImageView(defaultSugar));
                sugarNum += 1;
                sugarID = 2;

            } else if (sugarNum == 2) {

                cartItems.removeAll(ftcartItems3);
                fincartItemsGo.removeAll(ftcartItems3);
                addCart((iT+1+")")+"    -Sugar (Extra)", cartItems);
                addCart((iT+1+")")+"    -Sugar (Extra)", ftcartItems3);
                addCart((iT+1+")")+"    -Sugar (Extra)", fincartItemsGo);

                sugarNum += 1;
                sugarImage.setGraphic(new ImageView(largeSugar));
                sugarID = 1;
            }
        });

        GridPane.setConstraints(ftSugI, 3, 1);

        GridPane.setConstraints(sugarImage, 3, 2);




        Button ftSugD = new Button();
        ftSugD.setStyle("-fx-background-image: url('minus.png')");
        ftSugD.setMinSize(75, 37);
        ftSugD.setOnAction(e -> {
            //Size can either be 2 or 3 for if
            if (sugarNum == 2){

                cartItems.removeAll(ftcartItems3);
                fincartItemsGo.removeAll(ftcartItems3);
                addCart((iT+1+")")+"    -Sugar (Less)", cartItems);
                addCart((iT+1+")")+"    -Sugar (Less)", ftcartItems3);
                addCart((iT+1+")")+"    -Sugar (Less)", fincartItemsGo);

                sugarImage.setGraphic(new ImageView(medSugar));
                sugarNum -= 1;
                sugarID = 3;
            }
            else if (sugarNum == 3){

                cartItems.removeAll(ftcartItems3);
                fincartItemsGo.removeAll(ftcartItems3);
                addCart((iT+1+")")+"    -Sugar (Regular)", cartItems);
                addCart((iT+1+")")+"    -Sugar (Regular)", ftcartItems3);
                addCart((iT+1+")")+"    -Sugar (Regular)", fincartItemsGo);

                sugarNum -= 1;
                sugarImage.setGraphic(new ImageView(defaultSugar));
                sugarID = 2;
            }
        });
        GridPane.setConstraints(ftSugD, 3, 3);


        //TEMPERATURE
        Temp.setStyle("-fx-font: 20 arial;");
        GridPane.setConstraints(Temp, 4, 0);


        Image medTemp = new Image ("temp1.png");
        Image largeTemp = new Image ("temp3.png");
        tempImage.setGraphic(new ImageView(defaultTemp));


        Button ftTI = new Button();
        ftTI.setStyle("-fx-background-image: url('plus.png')");
        ftTI.setMinSize(75, 37);
        ftTI.setOnAction(e ->{
            if (tempNum == 1) {

                cartItems.removeAll(ftcartItems4);
                fincartItemsGo.removeAll(ftcartItems4);
                addCart((iT+1+")")+"    -Temp. (Regular)", cartItems);
                addCart((iT+1+")")+"    -Temp. (Regular)", ftcartItems4);
                addCart((iT+1+")")+"    -Temp. (Regular)", fincartItemsGo);

                tempImage.setGraphic(new ImageView(defaultTemp));
                tempNum += 1;
                tempID = 2;

            } else if (tempNum == 2) {

                cartItems.removeAll(ftcartItems4);
                fincartItemsGo.removeAll(ftcartItems4);
                addCart((iT+1+")")+"    -Temp. (Hot)", cartItems);
                addCart((iT+1+")")+"    -Temp. (Hot)", ftcartItems4);
                addCart((iT+1+")")+"    -Temp. (Hot)", fincartItemsGo);

                tempNum += 1;
                tempID = 2;
                tempImage.setGraphic(new ImageView(largeTemp));
            }
        });

        GridPane.setConstraints(ftTI, 4, 1);

        GridPane.setConstraints(tempImage, 4, 2);




        Button ftTD = new Button();
        ftTD.setStyle("-fx-background-image: url('minus.png')");
        ftTD.setMinSize(75, 37);
        ftTD.setOnAction(e -> {
            //Size can either be 2 or 3 for if
            if (tempNum == 2){

                cartItems.removeAll(ftcartItems4);
                fincartItemsGo.removeAll(ftcartItems4);
                addCart((iT+1+")")+"    -Temp. (Cold)", cartItems);
                addCart((iT+1+")")+"    -Temp. (Cold)", ftcartItems4);
                addCart((iT+1+")")+"    -Temp. (Cold)", fincartItemsGo);

                tempImage.setGraphic(new ImageView(medTemp));
                tempNum -= 1;
                tempID = 1;
            }
            else if (tempNum == 3){

                cartItems.removeAll(ftcartItems4);
                fincartItemsGo.removeAll(ftcartItems4);
                addCart((iT+1+")")+"    -Temp. (Regular)", cartItems);
                addCart((iT+1+")")+"    -Temp. (Regular)", ftcartItems4);
                addCart((iT+1+")")+"    -Temp. (Regular)", fincartItemsGo);

                tempNum -= 1;
                tempID = 2;
                tempImage.setGraphic(new ImageView(defaultTemp));
            }
        });
        GridPane.setConstraints(ftTD, 4, 3);





        //QUANTITY
        QT.setStyle("-fx-font: 20 arial;");
        GridPane.setConstraints(QT, 5, 0);


        Image QT2 = new Image ("qty2.png");
        Image QT3 = new Image ("qty3.png");
        Image QT4 = new Image ("qty4.png");
        Image QT5 = new Image ("qty5.png");
        qtImage.setGraphic(new ImageView(defaultQT));


        Button ftQI = new Button();
        ftQI.setStyle("-fx-background-image: url('plus.png')");
        ftQI.setMinSize(75, 37);
        ftQI.setOnAction(e ->{
            if (qtNum == 1) {

                if (sizeNum == 1){
                    tempTot = (runningTot*2);
                }

                else if (sizeNum == 2){
                    tempTot = ((runningTot+.5)*2);
                }

                else if (sizeNum == 3){
                    tempTot = ((runningTot+1)*2);
                }

                cartItems.removeAll(ftcartItems5);
                fincartItemsGo.removeAll(ftcartItems5);
                addCart((iT+1+")")+"    -Quantity (2)", cartItems);
                addCart((iT+1+")")+"    -Quantity (2)", ftcartItems5);
                addCart((iT+1+")")+"    -Quantity (2)", fincartItemsGo);

                qtImage.setGraphic(new ImageView(QT2));
                qtNum += 1;
                finalQTY = 2;

                ordertot3.setText("Order Total: $" + (decimalFormat.format(tempTot)));

            } else if (qtNum == 2) {

                if (sizeNum == 1){
                    tempTot = (runningTot*3);
                }

                else if (sizeNum == 2){
                    tempTot = ((runningTot+.5)*3);
                }

                else if (sizeNum == 3){
                    tempTot = ((runningTot+1)*3);
                }


                cartItems.removeAll(ftcartItems5);
                fincartItemsGo.removeAll(ftcartItems5);
                addCart((iT+1+")")+"    -Quantity (3)", cartItems);
                addCart((iT+1+")")+"    -Quantity (3)", ftcartItems5);
                addCart((iT+1+")")+"    -Quantity (3)", fincartItemsGo);

                qtNum += 1;
                finalQTY = 3;
                qtImage.setGraphic(new ImageView(QT3));

                ordertot3.setText("Order Total: $" + (decimalFormat.format(tempTot)));
            }

            else if (qtNum == 3) {

                if (sizeNum == 1){
                    tempTot = (runningTot*4);
                }

                else if (sizeNum == 2){
                    tempTot = ((runningTot+.5)*4);
                }

                else if (sizeNum == 3){
                    tempTot = ((runningTot+1)*4);
                }

                cartItems.removeAll(ftcartItems5);
                fincartItemsGo.removeAll(ftcartItems5);
                addCart((iT+1+")")+"    -Quantity (4)", cartItems);
                addCart((iT+1+")")+"    -Quantity (4)", ftcartItems5);
                addCart((iT+1+")")+"    -Quantity (4)", fincartItemsGo);

                qtNum += 1;
                finalQTY = 4;
                qtImage.setGraphic(new ImageView(QT4));

                ordertot3.setText("Order Total: $" + (decimalFormat.format(tempTot)));
            }

            else if (qtNum == 4) {

                if (sizeNum == 1){
                    tempTot = (runningTot*5);
                }

                else if (sizeNum == 2){
                    tempTot = ((runningTot+.5)*5);
                }

                else if (sizeNum == 3){
                    tempTot = ((runningTot+1)*5);
                }



                cartItems.removeAll(ftcartItems5);
                fincartItemsGo.removeAll(ftcartItems5);
                addCart((iT+1+")")+"    -Quantity (5)", cartItems);
                addCart((iT+1+")")+"    -Quantity (5)", ftcartItems5);
                addCart((iT+1+")")+"    -Quantity (5)", fincartItemsGo);

                qtNum += 1;
                finalQTY = 5;
                qtImage.setGraphic(new ImageView(QT5));

                ordertot3.setText("Order Total: $" + (decimalFormat.format(tempTot)));
            }
        });

        GridPane.setConstraints(ftQI, 5, 1);

        GridPane.setConstraints(qtImage, 5, 2);




        Button ftQD = new Button();
        ftQD.setStyle("-fx-background-image: url('minus.png')");
        ftQD.setMinSize(75, 37);
        ftQD.setOnAction(e -> {
            //Size can either be 2 or 3 for if
            if (qtNum == 5) {

                if (sizeNum == 1){
                    tempTot = (runningTot*4);
                }

                else if (sizeNum == 2){
                    tempTot = ((runningTot+.5)*4);
                }

                else if (sizeNum == 3){
                    tempTot = ((runningTot+1)*4);
                }

                cartItems.removeAll(ftcartItems5);
                fincartItemsGo.removeAll(ftcartItems5);
                addCart((iT+1+")")+"    -Quantity (4)", cartItems);
                addCart((iT+1+")")+"    -Quantity (4)", ftcartItems5);
                addCart((iT+1+")")+"    -Quantity (4)", fincartItemsGo);

                qtImage.setGraphic(new ImageView(QT4));
                qtNum -= 1;
                finalQTY = 4;
                ordertot3.setText("Order Total: $" + (decimalFormat.format(tempTot)));

            } else if (qtNum == 4) {

                if (sizeNum == 1){
                    tempTot = (runningTot*3);
                }

                else if (sizeNum == 2){
                    tempTot = ((runningTot+.5)*3);
                }

                else if (sizeNum == 3){
                    tempTot = ((runningTot+1)*3);
                }

                cartItems.removeAll(ftcartItems5);
                fincartItemsGo.removeAll(ftcartItems5);
                addCart((iT+1+")")+"    -Quantity (3)", cartItems);
                addCart((iT+1+")")+"    -Quantity (3)", ftcartItems5);
                addCart((iT+1+")")+"    -Quantity (3)", fincartItemsGo);

                qtNum -= 1;
                finalQTY = 3;
                qtImage.setGraphic(new ImageView(QT3));
                ordertot3.setText("Order Total: $" + (decimalFormat.format(tempTot)));
            }

            else if (qtNum == 3) {

                if (sizeNum == 1){
                    tempTot = (runningTot*2);
                }

                else if (sizeNum == 2){
                    tempTot = ((runningTot+.5)*2);
                }

                else if (sizeNum == 3){
                    tempTot = ((runningTot+1)*2);
                }

                cartItems.removeAll(ftcartItems5);
                fincartItemsGo.removeAll(ftcartItems5);
                addCart((iT+1+")")+"    -Quantity (2)", cartItems);
                addCart((iT+1+")")+"    -Quantity (2)", ftcartItems5);
                addCart((iT+1+")")+"    -Quantity (2)", fincartItemsGo);

                qtNum -= 1;
                finalQTY = 2;
                qtImage.setGraphic(new ImageView(QT2));
                ordertot3.setText("Order Total: $" + (decimalFormat.format(tempTot)));
            }

            else if (qtNum == 2) {

                if (sizeNum == 1){
                    tempTot = (runningTot);
                }

                else if (sizeNum == 2){
                    tempTot = ((runningTot+.5));
                }

                else if (sizeNum == 3){
                    tempTot = ((runningTot+1));
                }

                cartItems.removeAll(ftcartItems5);
                fincartItemsGo.removeAll(ftcartItems5);
                addCart((iT+1+")")+"    -Quantity (1)", cartItems);
                addCart((iT+1+")")+"    -Quantity (1)", ftcartItems5);
                addCart((iT+1+")")+"    -Quantity (1)", fincartItemsGo);

                qtNum -= 1;
                qtImage.setGraphic(new ImageView(defaultQT));
                ordertot3.setText("Order Total: $" + (decimalFormat.format(tempTot)));

                finalQTY = 1;


            }
        });

        GridPane.setConstraints(ftQD, 5, 3);



        ftGrid.getChildren().addAll(sizeTop,ftSI, ftSD,Size, ftII, ftID, Ice, iceImage, Sugar,
                ftSugD, ftSugI, sugarImage, Temp, ftTD, ftTI, tempImage, QT, ftQI, ftQD, qtImage);



        //Bottom Menu Items
        bottomMenu5.setPadding(new Insets(30, 30, 30, 30));
        bottomMenu5.setSpacing(30);

        //Go Back
        gb2.setStyle("-fx-font: 20 arial; -fx-base: #FFC524");
        gb2.setMinWidth(200);
        gb2.setAlignment(Pos.CENTER);
        gb2.setOnAction(e ->{
            window.setScene(scene4);
            cartItems.removeAll(excartItems);
            cartItems.removeAll(ftcartItems);
            cartItems.removeAll(ftcartItems2);
            cartItems.removeAll(ftcartItems3);
            cartItems.removeAll(ftcartItems4);
            cartItems.removeAll(ftcartItems5);

            fincartItemsGo.removeAll(excartItems);
            fincartItemsGo.removeAll(ftcartItems);
            fincartItemsGo.removeAll(ftcartItems2);
            fincartItemsGo.removeAll(ftcartItems3);
            fincartItemsGo.removeAll(ftcartItems4);
            fincartItemsGo.removeAll(ftcartItems5);

            ordertot2.setText("Order Total: $" + (typeTot));

            runningTot -= exIngTot;

            exIngTot = 0.0;
            exIngTotDiff = 0.0;
            finTot = 0.0;
            tempTot = 0.0;


            sizeNum = 1;
            iceNum = 2;
            sugarNum = 2;
            tempNum = 2;
            qtNum = 1;

            //Defaults
            qtImage.setGraphic(new ImageView(defaultQT));
            tempImage.setGraphic(new ImageView(defaultTemp));
            iceImage.setGraphic(new ImageView(defaultIce));
            Size.setGraphic(new ImageView(defaultSize));
            sugarImage.setGraphic(new ImageView(defaultSugar));



        });

        //Cancel Order
        co2.setStyle("-fx-font: 20 arial; -fx-base: #FFC524");
        co2.setMinWidth(200);
        co2.setAlignment(Pos.CENTER);
        co2.setOnAction(e -> {

            boolean ans = ConfirmOrder.display("Confirm Cancel", "Are you sure? Canceling will delete all progress!");

            if (ans == true) {
                Okay.display("Sad Day:(", "Please come again!");
                window.setScene(scenehelp);
                clearCart(g);
                clearCart(finCart);

                //Whenever cancel is clicked everything should reset to default values
                tempTot = 0.0;
                tempTotDiff = 0.0;
                typeTot = 0.0;
                exIngTot = 0.0;
                exIngTotDiff = 0.0;
                runningTot = 0.0;
                finTot = 0.0;
                realfinTot = 0.0;
                iT = 0;

                finaltoppingID = 0;
                finalsmoothieID = 0;
                finalteaID = 0;
                finalcoffeeID = 0;
                finalmilkteaID = 0;
                finalfrioID = 0;

                ButtonA.setVisible(true);
                checkoutReturn.setVisible(false);
                checkoutReturn3.setVisible(false);


                sizeNum = 1;
                iceNum = 2;
                sugarNum = 2;
                tempNum = 2;
                qtNum = 1;

                //Defaults
                qtImage.setGraphic(new ImageView(defaultQT));
                tempImage.setGraphic(new ImageView(defaultTemp));
                iceImage.setGraphic(new ImageView(defaultIce));
                Size.setGraphic(new ImageView(defaultSize));
                sugarImage.setGraphic(new ImageView(defaultSugar));


                ordertot2.setText("Order Total: $" + (decimalFormat.format(runningTot)));
                ordertot3.setText("Order Total: $" + (decimalFormat.format(runningTot)));
                ordertot4.setText("Order Total: $" + (decimalFormat.format(runningTot)));
            } else {
                Okay.display("Continue", "Please Continue!");
            }

        });


        //Continue
        cont2.setStyle("-fx-font: 20 arial; -fx-base: #7CD674");
        cont2.setMinWidth(200);
        cont2.setAlignment(Pos.CENTER);
        cont2.setOnAction(e -> {

            if (runningTot >= tempTot)
            {
                finTot = runningTot;

            }
            else{
                finTot = tempTot;
            }


            window.setScene(scene6);
            if (realfinTot > 0) {
                System.out.println("Real Total: "+realfinTot);
                System.out.println("Temp Total: "+tempTot);
                System.out.println("Final Total: "+finTot);
                ordertot4.setText("Order Total: $" + (decimalFormat.format((realfinTot+=finTot))));
            }
            else{

                System.out.println("Me2: "+finTot);
                ordertot4.setText("Order Total: $" + (decimalFormat.format((realfinTot+=finTot))));
            }

        });



        bottomMenu5.getChildren().addAll(gb2, co2, cont2);




        //Right Menu - Image
        ImageView iv5 = new ImageView();
        Image step4 = new Image("step4.png");
        iv5.setImage(step4);
        iv5.setFitHeight(100);
        iv5.setFitWidth(100);

        rightMenu5.setPadding(new Insets(50, 40, 30, 30));
        rightMenu5.setSpacing(20);


        VBox vcart3 = new VBox();
        Label carttit3 = new Label("Shopping Cart");

        carttit3.setStyle("-fx-font: 15 arial;");
        ordertot3.setStyle("-fx-font: 15 arial;");
        ordertot3.setText("Order Total: $" + (decimalFormat.format(runningTot)));

        vcart3.setMinHeight(175);
        vcart3.getChildren().addAll(carttit3, g, ordertot3);

      /*This was a button to remove default selections but not needed
      Button removeFTItem = new Button ("Clear Selections");
        removeFTItem.setStyle("-fx-base: #FFC524");
        removeFTItem.setOnAction(e ->{


                    cartItems.removeAll(ftcartItems);
                    cartItems.removeAll(ftcartItems2);
                    cartItems.removeAll(ftcartItems3);
                    cartItems.removeAll(ftcartItems4);
                    cartItems.removeAll(ftcartItems5);


                    ordertot2.setText("Order Total: $" + (exIngTot));
                    ordertot3.setText("Order Total: $" + (runningTot));

                    tempTot = runningTot;
                    tempTotDiff = 0.0;

                    cont1.setText("Skip");
        });*/


        rightMenu5.setAlignment(Pos.CENTER);
        rightMenu5.getChildren().addAll(iv5, vcart3);//removeFTItem


        /*===========================SCENE 6 CONFIRM/ADD ANOTHER DRINK===========================*/
        buildCart(finCart);
        finCart.setItems(fincartItemsGo);






        //Top Menu Items
        Label confirmLabel = new Label("Confirm your order");
        confirmLabel.setPadding(new Insets(30, 30, 30, 30));
        confirmLabel.setStyle("-fx-font: 35 arial; -fx-base: #FFC524");

        topMenu6.getChildren().addAll(confirmLabel);



        //Bottom Menu Items
        bottomMenu6.setPadding(new Insets(30, 30, 30, 30));
        bottomMenu6.setSpacing(30);

        Button confirmBack = new Button("Go Back");
        confirmBack.setStyle("-fx-font: 20 arial; -fx-base: #FFC524");
        confirmBack.setMinWidth(200);
        confirmBack.setAlignment(Pos.CENTER);
        confirmBack.setOnAction(e ->{
            window.setScene(scene5);

            ordertot3.setText("Order Total: $" + (decimalFormat.format(finTot)));
            if (iT == 0){
                realfinTot = 0.0;
            }
            else{
                realfinTot-=finTot;
            }




        });

        Button confirmCancel = new Button("Cancel Order");
        confirmCancel.setStyle("-fx-font: 20 arial; -fx-base: #FFC524");
        confirmCancel.setMinWidth(200);
        confirmCancel.setAlignment(Pos.CENTER);
        confirmCancel.setOnAction(e -> {

            boolean ans = ConfirmOrder.display("Confirm Cancel", "Are you sure? Canceling will delete all progress!");

            if (ans == true) {
                Okay.display("Sad Day:(", "Please come again!");
                window.setScene(scenehelp);
                clearCart(g);
                clearCart(finCart);

                //Whenever cancel is clicked everything should reset to default values
                tempTot = 0.0;
                tempTotDiff = 0.0;
                typeTot = 0.0;
                exIngTot = 0.0;
                exIngTotDiff = 0.0;
                runningTot = 0.0;
                finTot = 0.0;
                realfinTot = 0.0;
                iT = 0;

                finaltoppingID = 0;
                finalsmoothieID = 0;
                finalteaID = 0;
                finalcoffeeID = 0;
                finalmilkteaID = 0;
                finalfrioID = 0;

                ButtonA.setVisible(true);
                checkoutReturn.setVisible(false);
                checkoutReturn3.setVisible(false);


                sizeNum = 1;
                iceNum = 2;
                sugarNum = 2;
                tempNum = 2;
                qtNum = 1;

                //Defaults
                qtImage.setGraphic(new ImageView(defaultQT));
                tempImage.setGraphic(new ImageView(defaultTemp));
                iceImage.setGraphic(new ImageView(defaultIce));
                Size.setGraphic(new ImageView(defaultSize));
                sugarImage.setGraphic(new ImageView(defaultSugar));


                ordertot2.setText("Order Total: $" + (decimalFormat.format(runningTot)));
                ordertot3.setText("Order Total: $" + (decimalFormat.format(runningTot)));
                ordertot4.setText("Order Total: $" + (decimalFormat.format(runningTot)));
            } else {
                Okay.display("Continue", "Please Continue!");
            }

        });

        bottomMenu6.setMinHeight(-10);




        //Right Menu Items
        ImageView iv6 = new ImageView();
        Image step5 = new Image("step5.png");
        iv6.setImage(step5);
        iv6.setFitHeight(100);
        iv6.setFitWidth(150);

        rightMenu6.setAlignment(Pos.CENTER);
        rightMenu6.setPadding(new Insets(50, 40, 30, 30));
        rightMenu6.setSpacing(20);



        VBox confirmBox = new VBox();
        Label confirmCart = new Label("Shopping Cart");
        confirmBox.setStyle("-fx-font: 15 arial;");

        confirmCart.setStyle("-fx-font: 15 arial;");


        confirmBox.getChildren().addAll(confirmCart, finCart, ordertot4);


        ordertot4.setText("Order Total: $" + (decimalFormat.format(finTot)));



        rightMenu6.getChildren().addAll(iv6, confirmBox);


        //Scene 6 Center Menu Items
        centerMenu6.setPadding(new Insets(50, 40, 30, 30));
        centerMenu6.setSpacing(10);

        Button moreDrinks = new Button("Add Another Drink");
        moreDrinks.setMinSize(200, 200);
        moreDrinks.setAlignment(Pos.CENTER_RIGHT);

        //Add New Drink Logic
        moreDrinks.setOnAction(e -> {
            clearCart(d);
            clearCart(da);
            clearCart(f);
            clearCart(g);

            finCart.setItems(fincartItemsGo);



            if (finalteaID > 0){
                finaldrinkID = 2;
            }
            else if (finalcoffeeID > 0){
                finaldrinkID = 3;
            }
            else if (finalmilkteaID > 0) {
                finaldrinkID = 4;
            }

            else if (finalfrioID > 0) {
                finaldrinkID = 10;
            }
            else{
                finaldrinkID = 1;
            }


                drinkIDList.add(finaldrinkID);
                iceIDList.add(iceID);
                sugIDList.add(sugarID);
                sizeIDList.add(sizeNum);
                tempIDList.add(tempID);
                finTopIDList.add(finaltoppingID);
                finTotalIDList.add(finTot);
                realfinQTY = finalQTY;



            ButtonA.setVisible(false);
            checkoutReturn.setVisible(true);
            checkoutReturn3.setVisible(true);

            //Whenever cancel is clicked everything should reset to default values
            tempTot = 0.0;
            tempTotDiff = 0.0;
            typeTot = 0.0;
            exIngTot = 0.0;
            exIngTotDiff = 0.0;
            runningTot = 0.0;
            finTot = 0.0;

            finaltoppingID = 0;
            finalsmoothieID = 0;
            finalteaID = 0;
            finalcoffeeID = 0;
            finalmilkteaID = 0;
            finalfrioID = 0;



            sizeNum = 1;
            iceNum = 2;
            sugarNum = 2;
            tempNum = 2;
            qtNum = 1;

            //Defaults
            qtImage.setGraphic(new ImageView(defaultQT));
            tempImage.setGraphic(new ImageView(defaultTemp));
            iceImage.setGraphic(new ImageView(defaultIce));
            Size.setGraphic(new ImageView(defaultSize));
            sugarImage.setGraphic(new ImageView(defaultSugar));


            ordertot2.setText("Order Total: $" + (decimalFormat.format(runningTot)));
            ordertot3.setText("Order Total: $" + (decimalFormat.format(runningTot)));
            ordertot4.setText("Order Total: $" + (decimalFormat.format(runningTot)));
            ordertot4.setText("Order Total: $" + (decimalFormat.format(realfinTot)));
            window.setScene(scene2);



            iT++;

        });

        Button CheckoutButton = new Button ("Checkout");
        CheckoutButton.setStyle("-fx-font: 20 arial; -fx-base: #7CD674");
        CheckoutButton.setMinWidth(200);
        CheckoutButton.setOnAction(e -> {
            boolean ans = ConfirmOrder.display("Print Order", "Would you like to print your order?");

            if (ans == true) {
                Okay.display("Order Complete", "Printing Order");
                clearCart(d);
                clearCart(da);
                clearCart(db);
                clearCart(dc);
                clearCart(dd);
                clearCart(f);
                clearCart(g);
                clearCart(finCart);

                if (finalteaID > 0){
                    finaldrinkID = 2;
                }
                else if (finalcoffeeID > 0){
                    finaldrinkID = 3;
                }
                else if (finalmilkteaID > 0) {
                    finaldrinkID = 4;
                }

                else if (finalfrioID > 0) {
                    finaldrinkID = 10;
                }
                else{
                    finaldrinkID = 1;
                }



                drinkIDList.add(finaldrinkID);
                iceIDList.add(iceID);
                sugIDList.add(sugarID);
                sizeIDList.add(sizeNum);
                tempIDList.add(tempID);
                finTopIDList.add(finaltoppingID);
                finTotalIDList.add(finTot);
                realfinQTY = finalQTY;


                if (cartTracker == iT){
                    String insertOrderSQL ="ALTER TABLE Topping NOCHECK CONSTRAINT all;" +
                            "\nINSERT INTO OrderTable (OrderID, DrinkID, Ice, Sugar, Size, Temperature, Topping)" +
                            " VALUES ("+finalOrderID+", "+finaldrinkID+", "+iceID+", "+sugarID+
                            ", "+sizeNum+", "+tempID+","+ finaltoppingID+");";

                    String insertTicketSQL = "INSERT INTO Ticket (TicketID, PositionTimeID, PhoneNumber, TotCost,TicketDate)" +
                            " VALUES ("+finalticketID+", "+finalpositiontimeID+", NULL, "+realfinTot+", GETDATE());";

                    String insertTransSQL =  "INSERT INTO TrantnOrder (TransactID, OrderID, Quantity, Cost, TicketID) " +
                            "VALUES ("+finaltransID+", "+finalOrderID+", "+finalQTY+", "+finTot+", "+finalticketID+");";

                    try {
                        DatabaseInsert.insertThis(insertOrderSQL);
                        DatabaseInsert.insertThis(insertTicketSQL);
                        DatabaseInsert.insertThis(insertTransSQL);


                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }

                    System.out.println("This was executed");
                }
                else {
                    while (cartTracker <= iT) {

                        //INSERT EVERYTHING BEFORE RESETTING VALUES
                        System.out.println(finalOrderID);
                        System.out.println(finaldrinkID);
                        System.out.println("The Transaction ID for this order is: " + finaltransID);
                        System.out.println("The Ticket ID for this order is: " + finalticketID);


                    /*
                        drinkIDList.add(finaldrinkID);
                        iceIDList.add(iceID);
                        sugIDList.add(sugarID);
                        sizeIDList.add(sizeNum);
                        tempIDList.add(tempID);
                        finTopIDList.add(finaltoppingID);
                        finTotalIDList.add(finTot);
                     */


                        // SQL TO INSERT INTO ORDER
                        String insertOrderSQL = "ALTER TABLE Topping NOCHECK CONSTRAINT all;" +
                                "INSERT INTO OrderTable (OrderID, DrinkID, Ice, Sugar, Size, Temperature, Topping)" +
                                " VALUES (" + (finalOrderID+cartTracker) + ", " + drinkIDList.get(cartTracker)+ ", " + iceIDList.get(cartTracker)
                                + ", " + sugIDList.get(cartTracker) + ", " + sizeIDList.get(cartTracker) + ", " + tempIDList.get(cartTracker)
                                + "," + finTopIDList.get(cartTracker) + ");";





                        try {
                            DatabaseInsert.insertThis(insertOrderSQL);

                        } catch (SQLException e1) {
                            e1.printStackTrace();
                        }

                        System.out.println(cartTracker+" Nope, This was executed");
                        cartTracker++;

                    }

                    //Insert Once
                    String insertTicketSQL = "INSERT INTO Ticket (TicketID, PositionTimeID, PhoneNumber, TotCost,TicketDate)" +
                            " VALUES (" + finalticketID + ", " + finalpositiontimeID + ", NULL, " + realfinTot + ", GETDATE());";

                    String insertTransSQL = "INSERT INTO TrantnOrder (TransactID, OrderID, Quantity, Cost, TicketID) " +
                            "VALUES (" + finaltransID + ", " + finalOrderID + ", " + realfinQTY + ", " + finTot + ", " + finalticketID + ");";

                    try {
                        DatabaseInsert.insertThis(insertTicketSQL);
                        DatabaseInsert.insertThis(insertTransSQL);


                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }

                }

                System.out.println("Final Total:"+finTot);
                System.out.println("Temp Total:"+tempTot);


                tempTot = 0.0;
                tempTotDiff = 0.0;
                typeTot = 0.0;
                exIngTot = 0.0;
                exIngTotDiff = 0.0;
                runningTot = 0.0;
                finTot = 0.0;
                realfinTot = 0.0;
                iT = 0;

                ButtonA.setVisible(true);
                checkoutReturn.setVisible(false);
                checkoutReturn3.setVisible(false);


                sizeNum = 1;
                iceNum = 2;
                sugarNum = 2;
                tempNum = 2;
                qtNum = 1;

                //Defaults
                qtImage.setGraphic(new ImageView(defaultQT));
                tempImage.setGraphic(new ImageView(defaultTemp));
                iceImage.setGraphic(new ImageView(defaultIce));
                Size.setGraphic(new ImageView(defaultSize));
                sugarImage.setGraphic(new ImageView(defaultSugar));


                ordertot2.setText("Order Total: $" + (decimalFormat.format(runningTot)));
                ordertot3.setText("Order Total: $" + (decimalFormat.format(runningTot)));
                ordertot4.setText("Order Total: $" + (decimalFormat.format(runningTot)));
                ordertot4.setText("Order Total: $" + (decimalFormat.format(runningTot)));

                window.setScene(scenehelp);
            } else {
                Okay.display("Continue", "Please Continue");
            }
        });


        bottomMenu6.getChildren().addAll(confirmBack, confirmCancel, CheckoutButton);

        centerMenu6.setAlignment(Pos.CENTER);
        centerMenu6.getChildren().addAll(moreDrinks);




        /* INSERT INTO ORDER finaldrinkID, finaltoppingID, iceID, sugarID, tempID
           INSERT INTO TRANS OrderID, QTY, Cost, TicketID
           INSERT INTO Ticket (TicketID, PositionTimeID, PhoneNumber, TotCost,TicketDate)
          *System.out.println("Selected Drink ID " + finalsmoothieID);
            System.out.println("Selected Topping ID " + finaltoppingID);
            System.out.println("Selected Ice ID " + iceID);
            System.out.println("Selected Sugar ID " + sugarID);
            System.out.println("Selected Temp ID " + tempID);
          *
          * */



    }


   /* ====================================METHODS================================= */

    private void buildCart(ListView<String> c1){
        c1.setStyle("-fx-font: 13 arial; -fx-base: #FFC524");
        c1.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }

    private void addCart(String item, ObservableList<String> l){


        l.add(item);
    }

    private void clearCart(ListView<String> c3) {
        c3.getItems().clear();
    }


}



























/* ========================================DISCONTINUED=====================================
========================================DISCONTINUED=====================================
========================================DISCONTINUED=====================================
========================================DISCONTINUED=====================================
========================================DISCONTINUED=====================================
========================================DISCONTINUED=====================================
========================================DISCONTINUED=====================================
========================================DISCONTINUED=====================================
========================================DISCONTINUED=====================================
========================================DISCONTINUED=====================================
========================================DISCONTINUED=====================================
========================================DISCONTINUED=====================================


Button clearFT = new Button ("Clear Recent Additions");
        clearFT.setStyle("-fx-base: #FFC524");
        clearFT.setOnAction(e ->{

            fincartItemsGo.removeAll(excartItems);
            fincartItemsGo.removeAll(ftcartItems);
            fincartItemsGo.removeAll(ftcartItems2);
            fincartItemsGo.removeAll(ftcartItems3);
            fincartItemsGo.removeAll(ftcartItems4);
            fincartItemsGo.removeAll(ftcartItems5);

            realfinTot-= tempTot;

        });

 public DoubleProperty ordertotal;

    public double getOrderTotal() {
        return ordertotal.get();
    }

    public void setOrderTotal(double newOrderTotal) {
        ordertotal.set(newOrderTotal);
    }

    public DoubleProperty OrderTotalProperty() {
        return ordertotal;
    }



        Button ei5 = new Button("More Whiskey");
        ei5.setMinWidth(300);
        ei5.setAlignment(Pos.CENTER);
        ei5.setMinHeight(150);
        ei5.setOnAction(e -> {

            DoubleProperty orderr = new SimpleDoubleProperty(orderTotal);
            ObservableDoubleValue orderrT = Bindings.createDoubleBinding(() -> {
                double t = orderr.get();
                return t;
            });

            System.out.println(orderrT.getValue());
        });


        *************************SET BACKGROUND FOR HELP MENU********************************

        BackgroundImage bg = new BackgroundImage(new Image("12.jpg",1000,600,false,true), BackgroundRepeat.NO_REPEAT
                                , BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        bord4.setBackground(new Background(bg));


        //None Button
         Button ei4 = new Button("None");
        ei4.setMinSize(200, 75);
        ei4.setAlignment(Pos.CENTER);
       /* ei4.setOnAction(e -> {
            ei1.setVisible(false);
            ei2.setVisible(false);
            ei3.setVisible(false);
        });

        /*
        ==================SCENE 1 - CUSTOMER FIRST PAGE=================
        BorderPane Anchor = new BorderPane();

        Button helpscr = new Button("Help");
        helpscr.setStyle("-fx-font: 30 arial; -fx-base: #FFC524");


        VBox inception = new VBox();
        inception.setAlignment(Pos.CENTER);
        inception.setStyle("-fx-background-color: #B09268");

        HBox incep = new HBox();
        incep.setAlignment(Pos.TOP_RIGHT);
        incep.setStyle("-fx-background-color: #B09268");
        incep.getChildren().addAll(helpscr);

        Anchor.setTop(incep);


        Scene scene1 = new Scene(Anchor, 1000, 600);


    private void updateCart(ObservableList<String> c3) {

    }

    private double addOrderTotal (double currentValue, double addedValue){
        return currentValue + addedValue;
    }

    private double removeOrderTotal (double currentValue, double removedValue){
        return currentValue - removedValue;
    }

 Connection conn;
        conn = DBconnect.connect();




        // ResultSet
        ResultSet rs = conn.createStatement().executeQuery(SQL);

        while (rs.next()) {
            // Iterate Row
            ObservableList<String> row = FXCollections.observableArrayList();
            for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                // Iterate Column
                String notthere = "---";
                rs.getString(i);
                if (rs.wasNull()) {
                    row.add(notthere);
                } else {
                    row.add(rs.getString(i));
                }

            }
            System.out.println("Row added " + row);
            drinkData.add(row);

        }

Button sf4 = new Button("Fresh Avocado");
        sf4.setMinSize(200, 75);
        sf4.setAlignment(Pos.CENTER);
        sf4.setOnAction(e -> {
            cart = sf4.getText() + "    ($420)";
            addCart(cart, cartItems);
            typeTot += 420;
            runningTot += typeTot;

            window.setScene(scene4);
            ordertot2.setText("Order Total: $" + runningTot);

        });


        Button sf5 = new Button("Fresh Strawberry");
        sf5.setMinSize(200, 75);
        sf5.setAlignment(Pos.CENTER);
        sf5.setOnAction(e -> {
            cart = sf5.getText() + "    ($420)";
            addCart(cart, cartItems);

            typeTot += 420;
            runningTot += typeTot;

            window.setScene(scene4);
            ordertot2.setText("Order Total: $" + runningTot);
        });


=================CUSTOMER FIRST PAGE==================
 * First Page is the Welcome Page for Customers
 * -Displays "WELCOME TO DULCE TAPIOCA and "Began Order" button
 * -Start Button takes User to Customer Second Page
        Label lab2 = new Label("Welcome");
        lab2.setPadding(new Insets(10, 10, 10, 10));
        lab2.setStyle("-fx-font: 40 arial;");

        Label lab3 = new Label("to");
        lab3.setPadding(new Insets(10, 10, 10, 10));
        lab3.setStyle("-fx-font: 40 arial;");

        Label lab4 = new Label("Dulce Tapioca!");
        lab4.setPadding(new Insets(10, 10, 10, 10));
        lab4.setStyle("-fx-font: 40 arial;");

        Button start = new Button("Begin Order");
        start.setStyle("-fx-font: 30 arial; -fx-base: #FFC524");
        inception.getChildren().addAll(lab2, lab3, lab4, start);
        Anchor.setCenter(inception);

        start.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode().equals(KeyCode.F12)) {
                    window.setScene(logMenu);
                }
            }
        });

        Pane can1 = new Pane();
        can1.setPrefSize(0, 100);
        Anchor.setLeft(can1);
        inception.setAlignment(Pos.CENTER);
        Anchor.setStyle("");




        start.setOnAction(e -> window.setScene(scene2));




        Button ei1 = new Button("Chocolate Syrup");
        ei1.setMinSize(200, 75);
        ei1.setAlignment(Pos.CENTER);
        ei1.setOnAction(e -> {
            cart = "    -"+ ei1.getText() + "\n      $500";
            exIngTot += 500;

            runningTot += (exIngTot - exIngTotDiff);

            exIngTotDiff += 500;


            addCart(cart, cartItems);
            addCart(cart, excartItems);

            cont1.setText("Continue");
            ordertot2.setText("Order Total: $" + runningTot);
        });


        Button ei2 = new Button("Honey");
        ei2.setMinSize(200, 75);
        ei2.setAlignment(Pos.CENTER);
        ei2.setOnAction(e -> {
            cart = "    -"+ ei1.getText() + "\n      $500";
            exIngTot += 500;

            runningTot += (exIngTot - exIngTotDiff);

            exIngTotDiff += 500;

            addCart(cart, cartItems);
            addCart(cart, excartItems);

            cont1.setText("Continue");
            ordertot2.setText("Order Total: $" + runningTot);
        });

        Button ei3 = new Button("Whiskey");
        ei3.setMinSize(200, 75);
        ei3.setAlignment(Pos.CENTER);
        ei3.setOnAction(e -> {
            cart = "    -" + ei3.getText() + "\n      $125";
            addCart(cart, cartItems);
            addCart(cart, excartItems);

            exIngTot += 125;

            runningTot += (exIngTot - exIngTotDiff);

            exIngTotDiff += 125;

            ordertot2.setText("Order Total: $" + runningTot);

            cont1.setText("Continue");

        });
*/




