
package Controls;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

/**
 *
 * @author sahba
 */
public class Dashboard_CL{
    
    @FXML
    Label Name,Email,ttres;
    
    @FXML
    public void initialize() throws SQLException {
        gettotalreservtoday();
    }
    
    Statement state;
    
//    public void SetInfos(String Name,String Email){
//        this.Name.setText(Name);
//        this.Email.setText(Email);
//    }
    
    public void gettotalreservtoday() throws SQLException{
        state = ConnectionDB.openConnection().createStatement();
        ResultSet result =state.executeQuery("SELECT count(*) FROM `reservation` WHERE reservation.From = CURDATE()");
        if(result.next()){
            ttres.setText(Integer.toString(result.getInt(1)));
        }
        ConnectionDB.closeConnection();
    }
            
}
