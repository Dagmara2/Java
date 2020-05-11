package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class showClientsController implements Initializable {

    private List<client> clientList =  new ArrayList<>();
    private ObservableList<client> clientListObservable = FXCollections.observableArrayList();
    private Connection conn = null;
    private Statement statement = null;
  //  public sqlConnect c;
    public sqlConnect c = new sqlConnect();

    private String firstName, lastName;

    private int idToRemove;

    @FXML
    private Button showBtn, removeBtn, addBtn;
    @FXML
    private TableView<client> clientTable;
    @FXML
    private TableColumn<client, Integer> idColumn;
    @FXML
    private TableColumn<client, String> firstNameColumn;
    @FXML
    private TableColumn<client, String> lastNameColumn;
    @FXML
    private TextField removeField, firstNameField, lastNameField;
    @FXML
    private Label connectionLabel;

    public showClientsController() throws SQLException, ClassNotFoundException {
    }


    public void showClients() {
        clientListObservable.clear();
        clientListObservable =FXCollections.observableArrayList(c.selectClient());

       /* if(conn!=null)
        {
            try {
                statement = conn.createStatement();
                ResultSet rs = statement.executeQuery("SELECT Client.id_client, Client.firs_name_client, Client.last_name_client " +
                        "FROM Client ; ");
                while(rs.next()) {
                    client tmpClient = new client();

                    tmpClient.setClientNumber(rs.getInt("ID"));
                    tmpClient.setFirstName(rs.getString("First name"));
                    tmpClient.setLastName(rs.getString("Last name"));

                    clientList.add(tmpClient);
                    clientListObservable.add(tmpClient);
                }
                clientTable.getItems().clear();
                clientTable.getItems().addAll(clientListObservable);

                clientTable.refresh();
                rs.close();

            } catch (SQLException e) {
                e.printStackTrace();
            }

        }*/



    }

    public void removeClients() throws IOException, SQLException{

        this.idToRemove= Integer.parseInt(removeField.getText());
       /* if(conn!=null) {
            try {
                Statement st = conn.createStatement();
                st.executeUpdate("DELETE FROM Client WHERE id_client=" + idToRemove);
            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
            }
        }*/
       c.removeClient(idToRemove);
       showClients();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {
            c = new sqlConnect();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
       clientListObservable =FXCollections.observableArrayList(new client());

        idColumn.setCellValueFactory(new PropertyValueFactory<client,Integer>("Client number"));

        firstNameColumn.setCellValueFactory(new PropertyValueFactory<client, String>("First name"));

        lastNameColumn.setCellValueFactory(new PropertyValueFactory<client,String>("Last name"));


        clientTable.setEditable(true);
        clientTable.getSelectionModel().cellSelectionEnabledProperty().set(true);
    }


    public void addClient() throws IOException, SQLException{

        this.firstName=firstNameField.getText();
        this.lastName=lastNameField.getText();

        c.addClient(firstName,lastName);
        showClients();

    /*if(conn !=null) {
        System.out.println("conn");
        try{
        Statement st = conn.createStatement();
        st.executeUpdate("INSERT INTO Client (first_name_client, last_name_client) " +
            "VALUES ( " + firstName + ", " + lastName + ");");
        System.out.println("added");

            st.close();

            clientTable.getItems().clear();
            clientTable.getItems().addAll(clientListObservable);
            clientTable.refresh();
        }
        catch (SQLException ex)
        {
            System.err.println(ex.getMessage());
        }

    }*/


    }

    public void showOtherTables(ActionEvent actionEvent)throws IOException, SQLException  {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("showPayment.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();

        //FXMLLoader fxmlLoader2 = new FXMLLoader(getClass().getResource("showInstall.fxml"));
        //Parent root2 = (Parent) fxmlLoader.load();


        Stage stage = new Stage();
        stage.setScene(new Scene(root1));
        stage.show();

    }
}
