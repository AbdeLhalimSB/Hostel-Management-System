
package Controls;

import java.io.IOException;
import java.sql.SQLException;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author sahba
 */
public class AbdeLhalim_Methods {
    
    @FXML
    public void initialize() throws SQLException {
        
    }
    
    
    // open form on mdi form
    public void openDashboard(Event e) throws IOException{
//        AnchorPane pane = FXMLLoader.load(getClass().getResource("/View/Dashboard.fxml"));
//        main.getChildren().setAll(pane);
    }
    
    
    // open new form new window
    public void openEmploi(Event e) throws IOException{
        Node node = (Node) e.getSource();
            Stage stage = (Stage) node.getScene().getWindow();                  
            stage.close();
            Parent root = FXMLLoader.load(getClass().getResource("/View/Emploi.fxml"));       
            Scene scene = new Scene(root);       
            stage.setScene(scene);
            stage.show();
    }
    
    
}
