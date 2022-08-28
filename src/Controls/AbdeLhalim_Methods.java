
package Controls;

import Model.User;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
    
    //Login method
    Statement st ;
    public void Signin(Event e) throws SQLException, IOException{
//        email_tx.setText("admin");
//        password_tx.setText("admin");
//        log_error.setText("");
//        st = ConnectionDB.openConnection().createStatement();
//        User user = new User();
//        user.setEmail(email_tx.getText());
//        user.setPassword(password_tx.getText());
//        ResultSet res = st.executeQuery("select * from users where Email='"+user.getEmail()+"' and Password='"+user.getPassword()+"'");
//        
//        if(res.next()){
//            user.setName(res.getString(2));
//            System.out.println(user.getName());
//            Dashboard_CL dsh = new Dashboard_CL();
//            dsh.setName(log_error);
//            dsh.setEmail(log_error);
//            Node node = (Node) e.getSource();
//            Stage stage = (Stage) node.getScene().getWindow();                  
//            stage.close();
//            Parent root = FXMLLoader.load(getClass().getResource("/View/Home.fxml"));       
//            Scene scene = new Scene(root);       
//            stage.setScene(scene);
//            stage.show();
//        }
//        else{
//            log_error.setText("Password is incorrect !!");
//        }
//        ConnectionDB.closeConnection();
    }
    
    
    //Strat method
//    try {
//            Parent root = FXMLLoader.load(getClass().getResource("/View/Login.fxml"));
//            Scene scene = new Scene(root);
//            stage.setScene(scene);
//            stage.show();
//        } catch (IOException ex) {
//            System.out.println(ex.toString());
//    }
    
    
}
