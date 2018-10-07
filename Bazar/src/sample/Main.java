package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

    public static Object[] authentication;
    Parent root;
    @Override
    public void start(Stage primaryStage) throws Exception{

        primaryStage.setTitle("Bazaar");

        Label welcomeLabel = new Label("Welcome to Sacred Bazar\nLog-in or Sign-up to continue");
        Button btn_login = new Button("Log In");
        Button btn_signUp = new Button("Sign Up");
        VBox mainBAse_VBox = new VBox(10);
        mainBAse_VBox.setAlignment(Pos.CENTER);
        mainBAse_VBox.getChildren().addAll(welcomeLabel,btn_login,btn_signUp);
        Scene mainBasic_layout = new Scene(mainBAse_VBox,700,500);
        //initial stage with login and sign up option
        primaryStage.setScene(mainBasic_layout);


        btn_login.setOnAction((ActionEvent event) -> {
            //calling the login handler class which returns ack of successful login
            authentication = loginHandler.login();
            //System.out.println(""+authentication[0]);
            if((int)authentication[0]==1){
                System.out.println("Login successful");
                System.out.println("From main : "+authentication[1]);
                try {
                    root = FXMLLoader.load(getClass().getResource("home.fxml"));
                    primaryStage.close();
                    primaryStage.setScene(new Scene(root));
                    primaryStage.show();
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
            else{
                welcomeLabel.setText("Login Error, try Signing up.");
            }

        });
        btn_signUp.setOnAction(event -> {
            if(signUpHandler.signUp()){
                welcomeLabel.setText("SignUp Complete\nTry Logging in now.");
            }
            else{
                welcomeLabel.setText("Error while Sign Up\nPlease try again.");
            }
        });
        primaryStage.show();
    }
    public static String getUser(){
        return (String)authentication[1];
    }

    public static void main(String[] args) {
        launch(args);
    }
}
