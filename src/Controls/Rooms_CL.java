
package Controls;

import Model.Client;
import Model.Reservation;
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
    TextField cname_tx,cidcard_tx,cphone_tx,search_tx,search_tx2,name_tx,price_tx,position_tx,beds_tx,mp_tx,room_price;
    @FXML
    TableView rooms_dataview,reserv_dataview;
    @FXML
    TableColumn id,name,price,position,np,beds,mp,id2,from,to,price2,room,reserver;
    
    @FXML
    public void initialize() throws SQLException {
        Displaydata();
        Displaydata2();
    }
    
    Statement state ;
    int ID;
    
    public void Displaydata() throws SQLException{
        id.setCellValueFactory(new PropertyValueFactory<>("ID"));
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
    
    public void add(Event e){
        if(name_tx.getText().isEmpty() || price_tx.getText().isEmpty()|| position_tx.getText().isEmpty() || beds_tx.getText().isEmpty() || mp_tx.getText().isEmpty() ){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Add Idea State");
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
                state.executeUpdate("INSERT INTO `room`(`Name`, `Price`, `Position`, `Beds`, `MP`) VALUES ('"+name_tx.getText()+"','"+price_tx.getText()+"','"+position_tx.getText()+"','"+beds_tx.getText()+"','"+mp_tx.getText()+"');");
                ConnectionDB.closeConnection();
                Displaydata();
            } catch (SQLException ex) {
                ConnectionDB.closeConnection();
                Logger.getLogger(Rooms_CL.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void clickTable(Event e){
        Room obj =  (Room) rooms_dataview.getSelectionModel().getSelectedItem();
        ID=obj.getID();
        name_tx.setText(obj.getName());
        price_tx.setText(obj.getPrice());
        position_tx.setText(obj.getPosition());
        beds_tx.setText(obj.getBeds());
        mp_tx.setText(obj.getMP());
    }
    
    public void update(Event e){
        try {
            state = ConnectionDB.openConnection().createStatement();
            state.executeUpdate("UPDATE `room` SET `Name`='"+name_tx.getText()+"',`Price`='"+price_tx.getText()+"',`Position`='"+position_tx.getText()+"',`Beds`='"+beds_tx.getText()+"',`MP`='"+mp_tx.getText()+"' WHERE id="+ID );
            Displaydata();
            ConnectionDB.closeConnection();
        } catch (SQLException ex) {
            Logger.getLogger(Rooms_CL.class.getName()).log(Level.SEVERE, null, ex);
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
            state.executeUpdate("Delete FROM `room` WHERE id = " + id);
            ConnectionDB.closeConnection();
            Displaydata();
        } catch (SQLException ex) {
            ConnectionDB.closeConnection();
            Logger.getLogger(Rooms_CL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void clear(){
        name_tx.setText("");
        price_tx.setText("");
        position_tx.setText("");
        beds_tx.setText("");
        mp_tx.setText("");
    }
    
    public void Displaydata2() throws SQLException{
        id2.setCellValueFactory(new PropertyValueFactory<>("ID"));
        from.setCellValueFactory(new PropertyValueFactory<>("From"));
        to.setCellValueFactory(new PropertyValueFactory<>("To"));
        price2.setCellValueFactory(new PropertyValueFactory<>("Price"));
        room.setCellValueFactory(new PropertyValueFactory<>("Room"));
        reserver.setCellValueFactory(new PropertyValueFactory<>("Reserver"));
        reserv_dataview.setItems(getalldata2());
    }
    
    public ObservableList<Reservation> getalldata2() throws SQLException{
        ObservableList ob =FXCollections.observableArrayList();
        state = ConnectionDB.openConnection().createStatement();
        ResultSet result =state.executeQuery("SELECT * FROM reservation");
        while(result.next()){
            Reservation obj = new Reservation();
            obj.setID(result.getInt(1));
            obj.setFrom(result.getString(2));
            obj.setTo(result.getString(3));
            obj.setRoom(result.getString(4));
            obj.setReserver(result.getString(5));
            obj.setPrice(result.getString(6));
            ob.add(obj);
        }
        ConnectionDB.closeConnection();
        return ob;
    }
    
    public void clickTable2(Event e){
        Reservation obj =  (Reservation) reserv_dataview.getSelectionModel().getSelectedItem();
        ID=obj.getID();
//        from_tx.setValue(LocalDate.parse(obj.getFrom()));
//        to_tx.setValue(LocalDate.parse(obj.getTo()));
        room_price.setText(obj.getPrice());
    }
    
    public void update2(Event e){
        try {
            state = ConnectionDB.openConnection().createStatement();
            state.executeUpdate("UPDATE `reservation` SET `Price`='"+room_price.getText()+"' WHERE id="+ID );
            Displaydata2();
            ConnectionDB.closeConnection();
        } catch (SQLException ex) {
            Logger.getLogger(Rooms_CL.class.getName()).log(Level.SEVERE, null, ex);
            ConnectionDB.closeConnection();
        }
    }
    
    public void del2(Event e){
        delete2(ID);
        clear2();
    }
    
    public void delete2(int id)
    {
        try {
            state = ConnectionDB.openConnection().createStatement();
            state.executeUpdate("Delete FROM `reservation` WHERE id = " + id);
            ConnectionDB.closeConnection();
            Displaydata2();
        } catch (SQLException ex) {
            ConnectionDB.closeConnection();
            Logger.getLogger(Rooms_CL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void clear2(){
        room_price.setText("");
    }
    
    public ObservableList<Room> getSearchProduct(String name) throws SQLException{
        ObservableList ob =FXCollections.observableArrayList();
        state = ConnectionDB.openConnection().createStatement();
        ResultSet result =state.executeQuery("SELECT * FROM room where Name Like '%"+name+"%' ");
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
    
    public void Search(Event e) throws SQLException{
        rooms_dataview.setItems(getSearchProduct(search_tx.getText()));
    }
    
    public ObservableList<Reservation> getSearchProduct2(String name) throws SQLException{
        ObservableList ob =FXCollections.observableArrayList();
        state = ConnectionDB.openConnection().createStatement();
        ResultSet result =state.executeQuery("SELECT * FROM reservation where Reserver Like '%"+name+"%' ");
        while(result.next()){
            Reservation obj = new Reservation();
            obj.setID(result.getInt(1));
            obj.setFrom(result.getString(2));
            obj.setTo(result.getString(3));
            obj.setRoom(result.getString(4));
            obj.setReserver(result.getString(5));
            obj.setPrice(result.getString(6));
            ob.add(obj);
        }
        ConnectionDB.closeConnection();
        return ob;
    }
    
    public void Search2(Event e) throws SQLException{
        reserv_dataview.setItems(getSearchProduct2(search_tx2.getText()));
    }
    
}
