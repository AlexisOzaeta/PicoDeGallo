package pico.de.gallo;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import pico.de.gallo.view.MenuPrincipalController;

public class Main extends Application {
    private Stage primaryStage;
    public BorderPane rootLayout;
    Conector con = new Conector();
    
    @Override
    public void start(Stage primaryStage) throws ClassNotFoundException {
        con.connect();
        //con.registrarMiembro();
        con.close();
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Nombre por especificar...");
        menuLateral();
        //registro();
    }
    private void menuLateral(){
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/MenuPrincipalView.fxml"));
            rootLayout = (BorderPane) loader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
            MenuPrincipalController controller = loader.getController();
            controller.setMain(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void mostrarNuevoMiembroView() {
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/NuevoMiembroView.fxml"));
            AnchorPane nuevoMiembroOverview = (AnchorPane) loader.load();

            // Set person overview into the center of root layout.
            rootLayout.setCenter(nuevoMiembroOverview);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
        launch(args);
    }
    
}
