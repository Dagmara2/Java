package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class showPaymentController {

    private List<payment> paymentList =  new ArrayList<>();
    private ObservableList<payment> paymentListObservable = FXCollections.observableArrayList();
    private List<recive> reciveList =  new ArrayList<>();
    private ObservableList<recive> reciveListObservable = FXCollections.observableArrayList();
    private Connection conn = null;
    private Statement statement = null;
    public sqlConnect c;


    private String paymentDate, payTo;
    private int paymentID, reciveID, clientID, amount, toPay;

    @FXML
    private Button showBtn;
    @FXML
    private TableView<payment> paymentTable;
    @FXML
    private TableColumn<payment, Integer> paymentIDColumn;
    @FXML
    private TableColumn<payment, String> dateColumn;
    @FXML
    private TableColumn<payment, Integer> amountColumn;
    @FXML
    private TableView<recive> reciveTable;
    @FXML
    private TableColumn<recive, Integer> reciveIDColumn;
    @FXML
    private TableColumn<recive, String> payToColumn;
    @FXML
    private TableColumn<recive, Integer> toPayColumn;
    @FXML
    private Label connectionLabel;

    public void showData(ActionEvent actionEvent) {
        paymentListObservable.clear();
        paymentListObservable =FXCollections.observableArrayList(new payment());
        reciveListObservable.clear();
        reciveListObservable =FXCollections.observableArrayList(new recive());

        c.selectPayment();
        c.selectRecive();


    }

    public void initialize(URL location, ResourceBundle resources) {



        paymentListObservable =FXCollections.observableArrayList(new payment());

        paymentIDColumn.setCellValueFactory(new PropertyValueFactory<payment,Integer>("ID"));

        dateColumn.setCellValueFactory(new PropertyValueFactory<payment, String>("Payment Date"));

        amountColumn.setCellValueFactory(new PropertyValueFactory<payment,Integer>("Amount"));


        paymentTable.setEditable(true);
        paymentTable.getSelectionModel().cellSelectionEnabledProperty().set(true);


        reciveListObservable =FXCollections.observableArrayList(new recive());

        reciveIDColumn.setCellValueFactory(new PropertyValueFactory<recive,Integer>("ID"));

        payToColumn.setCellValueFactory(new PropertyValueFactory<recive, String>("Pay to"));

        toPayColumn.setCellValueFactory(new PropertyValueFactory<recive,Integer>("To pay"));


        reciveTable.setEditable(true);
        reciveTable.getSelectionModel().cellSelectionEnabledProperty().set(true);


    }

}
