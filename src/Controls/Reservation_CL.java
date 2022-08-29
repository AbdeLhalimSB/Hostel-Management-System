
package Controls;

import Model.Client;
import Model.Room;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 *
 * @author sahba
 */
public class Reservation_CL {
    
    @FXML
    DatePicker from_tx,to_tx;
    @FXML
    ComboBox rooms_tx;
    @FXML
    Label room_price,room_beds,room_position,room_mp;
    @FXML
    TextField cname_tx,cidcard_tx,cphone_tx;
    
    @FXML
    public void initialize() throws SQLException {
        getallRooms();
        deletfinishedreservations();
    }
    
    Statement state ;
    
    
    public void getallRooms() throws SQLException{
        ObservableList<String> l =FXCollections.observableArrayList();
        state = ConnectionDB.openConnection().createStatement();
        ResultSet result =state.executeQuery("SELECT Name FROM `room`");
        while(result.next()){
            l.add(result.getString(1));
        }
        ConnectionDB.closeConnection();
        rooms_tx.setItems(l);
        rooms_tx.getSelectionModel().select("Choose...");
    }
    
    public void getselectedroominfos(Event e) throws SQLException{
        String Searchedroom=rooms_tx.getValue().toString();
        state = ConnectionDB.openConnection().createStatement();
        ResultSet result =state.executeQuery("SELECT  `Price`, `Position`, `Beds`, `MP` FROM `room`  where Name ='"+Searchedroom+"'");
        if(result.next()){
            room_price.setText(result.getString(1));
            room_position.setText(result.getString(2));
            room_beds.setText(Integer.toString(result.getInt(3)));
            room_mp.setText(Integer.toString(result.getInt(4)));
        }
        ConnectionDB.closeConnection();
    }
    
    public boolean checkreservation() throws SQLException{
        state = ConnectionDB.openConnection().createStatement();
        ResultSet result =state.executeQuery("SELECT Room FROM `reservation` where Room='"+rooms_tx.getValue().toString()+"'");
        if(result.next()){
            ConnectionDB.closeConnection();
            return true;
        }
        else{
            ConnectionDB.closeConnection();
            return false;
        }
    }
    
    public void deletfinishedreservations() throws SQLException{
        state = ConnectionDB.openConnection().createStatement();
        ResultSet result = state.executeQuery("SELECT count(*) FROM `reservation`");
        result.next();
        if(result.getInt(1)!=0){
            result =state.executeQuery("SELECT `id`, `To` FROM `reservation` ");
            result.next();
            if(LocalDate.now().isAfter(LocalDate.parse(result.getString(2)))){
                state.executeUpdate("Delete FROM `reservation` WHERE id = "+result.getInt(1));
            }
        }
    }
    
    public void add(Event e) throws SQLException{
        if(cname_tx.getText().isEmpty() || cidcard_tx.getText().isEmpty() || cphone_tx.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Add Idea State");
            alert.setHeaderText("Add Reservation");
            alert.setContentText("Pleas fill all information & add");
            alert.showAndWait().ifPresent(rs -> {
                if (rs == ButtonType.OK) {
                    System.out.println("Pressed OK.");
            }
            });
        }
        else{
            if(from_tx.getValue().isAfter(to_tx.getValue()) || from_tx.getValue().isBefore(LocalDate.now())){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Add Idea State");
                alert.setHeaderText("Add Idea");
                alert.setContentText("Check the dates pleas the from date is after to date");
                alert.showAndWait().ifPresent(rs -> {
                    if (rs == ButtonType.OK) {
                        System.out.println("Pressed OK.");
                }
                });
            }
            else{
                if(checkreservation()==false){
                    try {
                        state = ConnectionDB.openConnection().createStatement();
                        state.executeUpdate("INSERT INTO `reservation`(`From`, `To`, `Room`, `Reserver`, `Price`) VALUES ('"+from_tx.getValue().toString()+"','"+to_tx.getValue().toString()+"','"+rooms_tx.getValue().toString()+"', '"+cname_tx.getText()+"','"+room_price.getText()+"' )");
                        state.executeUpdate("INSERT INTO `client`(`Name`, `IDCard`, `Phone`) VALUES ('"+cname_tx.getText()+"', '"+cidcard_tx.getText()+"', '"+cphone_tx.getText()+"' )");
                        ConnectionDB.closeConnection();
                        clear();
                    } catch (SQLException ex) {
                        ConnectionDB.closeConnection();
                        Logger.getLogger(Reservation_CL.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                else{
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Add Idea State");
                    alert.setHeaderText("Add Idea");
                    alert.setContentText("Sorry this room is token !!");
                    alert.showAndWait().ifPresent(rs -> {
                        if (rs == ButtonType.OK) {
                            System.out.println("Pressed OK.");
                    }
                    });
                }
            }
        }
    }
    
    public void clear(){
        cname_tx.setText("");
        cidcard_tx.setText("");
        cphone_tx.setText("");
    }
    
    
    
}
