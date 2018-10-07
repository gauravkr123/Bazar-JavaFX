package sample;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class loginHandler {
    private static String userName;
    private static String pass;
    private static Object[] ret = new Object[2];
    public static Object[] login(){
        Stage window = new Stage();
        window.setTitle("Log-In");
        window.initModality(Modality.APPLICATION_MODAL);

        VBox layout = new VBox();
        TextField user_id = new TextField();
            user_id.setPromptText("User Name");
        PasswordField password = new PasswordField();
            password.setPromptText("Your Password");
        Button btn_login = new Button("Log In");
        Button btn_signUp = new Button("Sign Up");
        Label label_userId = new Label("User ID");
        Label label_Pass = new Label("Password");
        // emptyPane = new Pane();

        HBox button_box = new HBox(30);
        button_box.getChildren().addAll(btn_login);
        button_box.setAlignment(Pos.CENTER);
        HBox login_box = new HBox(10);
        login_box.getChildren().addAll(label_userId,user_id);
        HBox pass_box = new HBox(10);
        pass_box.getChildren().addAll(label_Pass,password);
        layout.getChildren().addAll(login_box,pass_box,button_box);


        btn_login.setOnAction(event -> {

            userName = user_id.getText();
            pass = password.getText();
            System.out.println(""+pass);
            try{
                Class.forName("java.sql.Driver");

                Connection con = DriverManager.getConnection("jdbc:mysql://localhost/bazar","root","mayank");
                Statement s1 = con.createStatement();
                String query = "SELECT user_name FROM user_aes WHERE user_name='"+userName+"' AND password=AES_ENCRYPT('"+pass+"','"+userName+"');";
                //String query = "SELECT user_name FROM user_login WHERE user_name='"+userName+"' AND pass='"+pass+"';";
                ResultSet rs1 = s1.executeQuery(query);
                if(rs1.next()){
                    ret[0] = 1;
                    ret[1] = rs1.getString(1);
                    System.out.println(rs1.getString(1));
                }
                else{
                    System.out.println("From login handler : Error while login");
                    ret[0] = 0;
                    ret[1] = null;
                }

            }
            catch (Exception e){
                e.printStackTrace();
            }
            window.close();
        });

        window.setScene(new Scene(layout));
        window.showAndWait();
        return ret;
    }
}
