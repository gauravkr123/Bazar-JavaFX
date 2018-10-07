package sample;

import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class homeController implements Initializable{
    public TextField tField_search;
    public Button btn_search;
    public Button btn_logIn;
    public Button btn_signUp;
    public TreeView<String> tree_departments;
    public Label label_userImage;
    public ListView<HBox> list_display;

    public void addListItem(){
        HBox temp;
        try{
            Class.forName("java.sql.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/classicmodels","root", "mayank");

            Statement s1 = con.createStatement();
            String query1 = "select productName, quantityInStock, buyPrice from products order by productName;";
            ResultSet rs1 = s1.executeQuery(query1);

            while(rs1.next()){
                //System.out.println(""+rs1.getString(2));
                temp = new ListItemObject(rs1.getString("productName"),rs1.getInt(3),rs1.getInt(2)).getLayout();
                list_display.getItems().addAll(temp);
            }

        }
        catch(Exception e){
            e.printStackTrace();

        }
    }
    public void resetList(){
        list_display.getItems().clear();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        label_userImage.setText(Main.getUser());
        TreeItem<String> electronics = new TreeItem<>("Electronics");
        TreeItem<String> elec_mobile = new TreeItem<>("Mobiles");
        TreeItem<String> elec_computer = new TreeItem<>("Computers");
        TreeItem<String> elec_accesories = new TreeItem<>("Accesories");

        electronics.getChildren().addAll(elec_mobile,elec_computer,elec_accesories);
        tree_departments.setRoot(electronics);
    }
}
