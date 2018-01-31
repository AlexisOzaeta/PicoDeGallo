package pico.de.gallo.view;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.fxml.Initializable;
import pico.de.gallo.Main;

public class MenuPrincipalController implements Initializable {
    
    Main main;
    
    @FXML private Button btnNuevo;
    @FXML private Button btnRenovar;
    @FXML private Button btnMiembros;
    @FXML private Button btnEstadisticas;
    @FXML private Button btnConfiguracion;
    @FXML private Button btnAcercaDe;
    @FXML
    private void habersijalaswey(){
        main.mostrarNuevoMiembroView();
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }
    
    public void setMain(Main main){
        this.main = main;
    }
}
