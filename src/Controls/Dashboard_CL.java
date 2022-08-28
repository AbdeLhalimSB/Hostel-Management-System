
package Controls;

import java.sql.SQLException;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

/**
 *
 * @author sahba
 */
public class Dashboard_CL{
    
    @FXML
    Label Name,Email;
    
    @FXML
    public void initialize() throws SQLException {
        
    }
    
    public void SetInfos(String Name,String Email){
        this.Name.setText(Name);
        this.Email.setText(Email);
    }
    
            
}
