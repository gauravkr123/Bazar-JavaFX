package sample;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;

public class ListItemObject {
    public HBox layout = new HBox(50);
    public VBox vBox1 = new VBox(20);
    public VBox vBox2 = new VBox(20);
    public Pane emptyPane = new Pane();
    public Label img = new Label("Image");
    public Label name = new Label("Name");
    public Label price = new Label("Price");
    public Label quantity = new Label("Rating");
    public Button cart = new Button("Add to cart");
    public Button buy = new Button("Buy Now");

    public ListItemObject(String Name, int Price, int Quantity){
        name.setText(""+Name);
        price.setText("Price : $"+Price);
        quantity.setText("Quantity left : "+Quantity);
        vBox1.getChildren().addAll(name,quantity,price);
        vBox2.getChildren().addAll(cart,buy);
        layout.getChildren().addAll(img,vBox1,emptyPane,vBox2);
        HBox.setHgrow(emptyPane, Priority.ALWAYS);
        emptyPane.prefWidth(Region.USE_COMPUTED_SIZE);
        cart.setOnAction(event -> {
            System.out.println("Item : "+Name+" added to cart");
        });
        buy.setOnAction(event -> {
            System.out.println("Item : "+Name +" bought");
        });
    }

    public HBox getLayout() {
        return layout;
    }
    /*@Override
    protected void updateItem(String item, boolean empty) {
        super.updateItem(item, empty);
        setText(null);  // No text in label of super class
        setGraphic(null);

        if(name != null && !empty){
            setGraphic(layout);
        }
    }*/



}
