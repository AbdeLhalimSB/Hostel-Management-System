
package Controls;

import Model.User;
import java.io.IOException;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 *
 * @author sahba
 */
public class Login_CL extends Thread{
    
    @FXML
    TextField email_tx;
    @FXML
    PasswordField password_tx;
    @FXML
    Button btn_signin;
    @FXML
    Label log_error,btn_noaccount;
    @FXML
    ImageView support;
    @FXML
    private Parent root;
    Statement st ;
    public void Signin(Event e) throws SQLException, IOException{
        email_tx.setText("admin");
        password_tx.setText("admin");
        st = ConnectionDB.openConnection().createStatement();
        User user = new User();
        user.setEmail(email_tx.getText());
        user.setPassword(password_tx.getText());
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/Dashboard.fxml"));
        root = loader.load();
        Dashboard_CL dsh = loader.getController();
        dsh.SetInfos("slm", user.getEmail());
        ResultSet res = st.executeQuery("select * from users where Email='"+user.getEmail()+"' and Password='"+user.getPassword()+"'");
        
        if(res.next()){
            user.setName(res.getString(2));
            System.out.println(user.getName());
            Node node = (Node) e.getSource();
            Stage stage = (Stage) node.getScene().getWindow();                  
            stage.close();
            Parent root = FXMLLoader.load(getClass().getResource("/View/Home.fxml"));       
            Scene scene = new Scene(root);       
            stage.setScene(scene);
            stage.show();
        }
        else{
            log_error.setText("Password is incorrect !!");
        }
        ConnectionDB.closeConnection();
    }
    
}
