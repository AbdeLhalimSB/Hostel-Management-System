
package Controls;

import static com.sun.javafx.scene.control.skin.Utils.getResource;
import java.io.IOException;
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
public class Home_CL {
    
    @FXML
    AnchorPane main;
    
    public void openDashboard(Event e) throws IOException{
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/View/Dashboard.fxml"));
        main.getChildren().setAll(pane);
    }
    public void openRes(Event e) throws IOException{
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/View/Reservation.fxml"));
        main.getChildren().setAll(pane);
    }
    public void openRooms(Event e) throws IOException{
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/View/Rooms.fxml"));
        main.getChildren().setAll(pane);
    }
    public void openClients(Event e) throws IOException{
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/View/Clients.fxml"));
        main.getChildren().setAll(pane);
    }
    public void openAide(Event e) throws IOException{
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/View/Aide.fxml"));
        main.getChildren().setAll(pane);
    }
    
    public void Dis(Event e) throws IOException{
        Node node = (Node) e.getSource();
            Stage stage = (Stage) node.getScene().getWindow();                  
            stage.close();
            Parent root = FXMLLoader.load(getClass().getResource("/View/Login.fxml"));       
            Scene scene = new Scene(root);       
            stage.setScene(scene);
            stage.show();
    }
    
}
