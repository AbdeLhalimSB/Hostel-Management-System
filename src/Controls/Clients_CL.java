
package Controls;

import Model.Client;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author sahba
 */
public class Clients_CL {
    
    @FXML
    TextField name_tx,idcard_tx,phone_tx,search_tx;
    @FXML
    TableView dataview;
    @FXML
    TableColumn id,name,idcard,phone;
    
    @FXML
    public void initialize() throws SQLException {
        Displaydata();
    }
    
    Statement state ;
    int ID;
    
    public void Displaydata() throws SQLException{
        id.setCellValueFactory(new PropertyValueFactory<>("ID"));
        name.setCellValueFactory(new PropertyValueFactory<>("Name"));
        idcard.setCellValueFactory(new PropertyValueFactory<>("IDCard"));
        phone.setCellValueFactory(new PropertyValueFactory<>("Phone"));
        dataview.setItems(getalldata());
    }
    
    public ObservableList<Client> getalldata() throws SQLException{
        ObservableList ob =FXCollections.observableArrayList();
        state = ConnectionDB.openConnection().createStatement();
        ResultSet result =state.executeQuery("SELECT * FROM client");
        while(result.next()){
            Client obj = new Client();
            obj.setID(result.getInt(1));
            obj.setName(result.getString(2));
            obj.setIDCard(result.getString(3));
            obj.setPhone(result.getString(4));
            ob.add(obj);
        }
        ConnectionDB.closeConnection();
        return ob;
    }
    
    public void add(Event e){
        if(name_tx.getText().isEmpty() || idcard_tx.getText().isEmpty() || phone_tx.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Add Client State");
            alert.setHeaderText("Add Idea");
            alert.setContentText("Pleas fill all information & add");
            alert.showAndWait().ifPresent(rs -> {
                if (rs == ButtonType.OK) {
                    System.out.println("Pressed OK.");
            }
            });
        }
        else{
            try {
                state = ConnectionDB.openConnection().createStatement();
                state.executeUpdate("INSERT INTO `client`(`Name`, `IDCard`, `Phone`) VALUES ('"+name_tx.getText()+"', '"+idcard_tx.getText()+"','"+phone_tx.getText()+"')");
                ConnectionDB.closeConnection();
                Displaydata();
            } catch (SQLException ex) {
                ConnectionDB.closeConnection();
                Logger.getLogger(Clients_CL.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void clickTable(Event e){
        Client obj =  (Client) dataview.getSelectionModel().getSelectedItem();
        ID=obj.getID();
        name_tx.setText(obj.getName());
        idcard_tx.setText(obj.getIDCard());
        phone_tx.setText(obj.getPhone());   
    }
    
    public void clear(){
        name_tx.setText("");
        idcard_tx.setText("");
        phone_tx.setText("");   
    }
    
    public void update(Event e){
        try {
            state = ConnectionDB.openConnection().createStatement();
            state.executeUpdate("UPDATE `client` SET `Name`='"+name_tx.getText()+"' ,`IDCard`='"+idcard_tx.getText()+"' ,`Phone`='"+phone_tx.getText()+"'  WHERE id="+ID );
            Displaydata();
            ConnectionDB.closeConnection();
        } catch (SQLException ex) {
            Logger.getLogger(Clients_CL.class.getName()).log(Level.SEVERE, null, ex);
            ConnectionDB.closeConnection();
        }
    }
    
    public void del(Event e){
        delete(ID);
        clear();
    }
    
    public void delete(int id)
    {
        try {
            state = ConnectionDB.openConnection().createStatement();
            state.executeUpdate("Delete FROM `client` WHERE id = " + id);
            ConnectionDB.closeConnection();
            Displaydata();
        } catch (SQLException ex) {
            ConnectionDB.closeConnection();
            Logger.getLogger(Clients_CL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public ObservableList<Client> getSearchProduct(String name) throws SQLException{
        ObservableList ob =FXCollections.observableArrayList();
        state = ConnectionDB.openConnection().createStatement();
        ResultSet result =state.executeQuery("SELECT * FROM client where Name Like '%"+name+"%' ");
        while(result.next()){
            Client obj = new Client();
            obj.setID(result.getInt(1));
            obj.setName(result.getString(2));
            obj.setIDCard(result.getString(3));
            obj.setPhone(result.getString(4));
            ob.add(obj);
        }
        ConnectionDB.closeConnection();
        return ob;
    }
    
    public void Search(Event e) throws SQLException{
        dataview.setItems(getSearchProduct(search_tx.getText()));
    }
    
}
