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
import java.sql.Statement;

public class signUpHandler {

    private static boolean status;
    public static boolean signUp(){
        Stage window = new Stage();
        window.setTitle("Log-In");
        window.initModality(Modality.APPLICATION_MODAL);

        VBox layout = new VBox();
        TextField user_id = new TextField();
        user_id.setPromptText("User Name");
        PasswordField password = new PasswordField();
        password.setPromptText("Your Password");
        PasswordField rePassword = new PasswordField();
        rePassword.setPromptText("Re-enter Password");
        Button btn_signUp = new Button("Sign Up");
        Label label_userId = new Label("User ID");
        Label label_Pass = new Label("Password");
        Label label_Pass2 = new Label("Re-enter password");
        Label label_output = new Label("");

        // emptyPane = new Pane();

        HBox button_box = new HBox(30);
        button_box.getChildren().addAll(btn_signUp);
        button_box.setAlignment(Pos.CENTER);
        HBox login_box = new HBox(10);
        login_box.getChildren().addAll(label_userId,user_id);
        HBox pass_box1 = new HBox(10);
        pass_box1.getChildren().addAll(label_Pass,password);
        HBox pass_box2 = new HBox(10);
        pass_box2.getChildren().addAll(label_Pass2,rePassword);
        VBox passVbox = new VBox(5);
        passVbox.getChildren().addAll(pass_box1,pass_box2);
        layout.getChildren().addAll(login_box,passVbox,button_box);


        btn_signUp.setOnAction(event -> {
            String userName = user_id.getText();
            String pass = password.getText();
            String pass2 = rePassword.getText();
            if(!pass.equals(pass2)){
                window.setTitle("Password mismatch");
            }
            else {
                try {
                    Class.forName("java.sql.Driver");

                    //Creating a new user
                    Connection con_user = DriverManager.getConnection("jdbc:mysql://localhost/bazar", "root", "mayank");
                    Statement s1 = con_user.createStatement();
                    String query_createUser = "INSERT INTO user_aes VALUES('" + userName + "', AES_ENCRYPT('" + pass + "','" + userName + "'))";
                    //String query = "INSERT INTO user_login VALUES('"+userName+"','"+pass+"');";
                    s1.executeUpdate(query_createUser);

                    System.out.println("User login created successfully");

                    //Creating a shopping cart for the newly created user
                    Connection con_userCart = DriverManager.getConnection("jdbc:mysql://localhost/user_cart","root","mayank");
                    Statement s2 = con_userCart.createStatement();
                    String query_createUserCart = "CREATE table "+userName+"Cart (productCode VARCHAR(15) PRIMARY KEY, productName VARCHAR(70),buyPrice DECIMAL(10,2) , quantity SMALLINT(6));";
                    s2.executeUpdate(query_createUserCart);

                    System.out.println("Cart created successfully");

                    status = true;

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
            window.close();
        });

        window.setScene(new Scene(layout));
        window.showAndWait();
        return status;
    }
}
