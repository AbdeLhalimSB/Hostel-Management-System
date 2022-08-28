
package Controls;

import Model.Room;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author sahba
 */
public class Rooms_CL {
    @FXML
    DatePicker from_tx,to_tx;
    @FXML
    ComboBox rooms_tx;
    @FXML
    TextField cname_tx,cidcard_tx,cphone_tx,search_tx,search_tx2;
    @FXML
    TableView rooms_dataview,reserv_dataview;
    @FXML
    TableColumn id,name,price,position,np,beds,mp,id2,from,to,price2,room,reserver;
    
    @FXML
    public void initialize() throws SQLException {
        Displaydata();
    }
    
    Statement state ;
    
    public void Displaydata() throws SQLException{
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        name.setCellValueFactory(new PropertyValueFactory<>("Name"));
        price.setCellValueFactory(new PropertyValueFactory<>("Price"));
        position.setCellValueFactory(new PropertyValueFactory<>("Position"));
        beds.setCellValueFactory(new PropertyValueFactory<>("Beds"));
        mp.setCellValueFactory(new PropertyValueFactory<>("MP"));
        rooms_dataview.setItems(getalldata());
    }
    
    public ObservableList<Room> getalldata() throws SQLException{
        ObservableList ob =FXCollections.observableArrayList();
        state = ConnectionDB.openConnection().createStatement();
        ResultSet result =state.executeQuery("SELECT * FROM room");
        while(result.next()){
            Room obj = new Room();
            obj.setID(result.getInt(1));
            obj.setName(result.getString(2));
            obj.setPrice(result.getString(3));
            obj.setPosition(result.getString(4));
            obj.setBeds(Integer.toString(result.getInt(5)));
            obj.setMP(Integer.toString(result.getInt(6)));
            ob.add(obj);
        }
        ConnectionDB.closeConnection();
        return ob;
    }
    
    
}
