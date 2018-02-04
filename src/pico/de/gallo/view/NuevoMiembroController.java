package pico.de.gallo.view;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXProgressBar;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.NumberValidator;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.input.KeyEvent;

public class NuevoMiembroController implements Initializable {
    private final int[] campos = new int[9];
    
    @FXML private JFXTextField txtNombres;
    @FXML private JFXTextField txtApellidos;
    @FXML private JFXTextField txtCelular;
    @FXML private JFXCheckBox CheckHombre;
    @FXML private JFXCheckBox CheckMujer;
    @FXML private JFXDatePicker DPfechaNac;
    @FXML private JFXButton btnLimpiarDatos;
    @FXML private JFXButton btnLimpiarServicio;
    @FXML private JFXComboBox<String> CBtipo;
    @FXML private JFXComboBox<String> CBhorario;
    @FXML private JFXRadioButton CheckPagoCompleto;
    @FXML private JFXRadioButton CheckAbono;
    @FXML private JFXTextField txtAbono;
    @FXML private Spinner<Integer> SpinnerMeses;
    @FXML private JFXProgressBar ProgressBar;
    @FXML private Label ProgressText;
    @FXML private JFXButton btnCancelar;
    @FXML private JFXButton btnGuardar;
    
    /**
     * Método para el radioButton CheckAbono.
     * 
     * Al llaarlo se:
     * Desactiva CheckPagoCompleto,
     * Activa txtAbono,
     * Manda a llamar al metodo verificar().
     */
    @FXML
    private void clickCheckAbono(){
        CheckPagoCompleto.setSelected(false);
        txtAbono.setDisable(false);
        verificar();
    }
    /**
     * Método para el radioButton CheckPagoCompleto.
     * 
     * Al llamarlo se:
     * Desactivan CheckAbono y txtAbono,
     * Manda a llamar al metodo verificar().
     */
    @FXML
    private void clickCheckPagoCompleto(){
        txtAbono.setDisable(true);
        CheckAbono.setSelected(false);
        verificar();
    }
    /**
     * Método para los TextFields.
     * 
     * Manda a llamar al metodo verificar().
     */
    @FXML
    private void typeTextField(){verificar();}
    /**
     * Método para los componentes que se verifican con click solamente.
     * 
     * Manda a llamar al metodo verificar().
     */
    @FXML
    private void clickComponent(){verificar();}
    /**
     * Método para el datePicker.
     * 
     * Comprueba que la fecha no sea futura o muy reciente.
     * Manda a llamar al metodo verificar().
     */
    @FXML
    private void clickDatePicker(){
        if(DPfechaNac.getValue() != null){
            LocalDate fechaActual = LocalDate.now();
            LocalDate fechaSeleccionada = DPfechaNac.getValue();
            if(fechaActual.isBefore(fechaSeleccionada)){
                System.out.println("¿Estás intentando registrar a alguien que viene del futuro?");
                DPfechaNac.setValue(null);
            }else
                verificar();
        }
    }
    /**
     * Método para el checkButton CheckHombre.
     * 
     * Al llamarlo se:
     * Desactiva CheckMujer,
     * Manda a llamar al metodo verificar().
     */
    @FXML
    private void clickCheckHombre(){
        CheckMujer.setSelected(false);
        verificar();
    }
    /**
     * Método para el checkButton CheckMujer.
     * 
     * Al llamarlo se:
     * Desactiva CheckHombre,
     * Manda a llamar al metodo verificar().
     */
    @FXML
    private void clickCheckMujer(){
        CheckHombre.setSelected(false);
        verificar();
    }
    /**
     * Método encargado de verificar que cada campo no esté vacio.
     * 
     * Actualiza la barra de progreso.
     */
    private void verificar(){
        //Field Name
        if("".equals(txtNombres.getText())) campos[0] = 0;
        else campos[0] = 12;
        //Field Last Name
        if("".equals(txtApellidos.getText())) campos[1] = 0;
        else campos[1] = 12;
        //Field Number Cellphone
        if("".equals(txtCelular.getText())) campos[2] = 0;
        else campos[2] = 10;
        //Field Gender
        if(CheckHombre.isSelected() == false && CheckMujer.isSelected() == false) campos[3] = 0;
        else campos[3] = 10;
        //Field DateBird
        if(DPfechaNac.getValue() == null) campos[4] = 0;
        else campos[4] = 16;
        //Field Type
        if(CBtipo.getValue() == null) campos[5] = 0;
        else campos[5] = 10;
        //Field Schedule
        if(CBhorario.getValue() == null) campos[6] = 0;
        else campos[6] = 10;
        //Field Payment
        if(CheckAbono.isSelected() == false && CheckPagoCompleto.isSelected() == false) campos[7] = 0;
        else{
            if(CheckAbono.isSelected() == true){
                campos[7] = 5;
                if(!"".equals(txtAbono.getText())) campos[7] = 10;
            }else
                campos[7] = 10;
        }
        //Field Months
        if(SpinnerMeses.getValue() == 0) campos[8] = 0;
        else campos[8] = 10;
        //Calculate the percentage of the progress bar                  
        int tot = 0;
        for(int i = 0; i < 9; i++)
            tot += campos[i];
        ProgressText.setText(tot+"% Completo");
        double progreso = (double) tot / 100;
        ProgressBar.setProgress(progreso);
        //Verify if the progress bar is full to show btnGuardar
        if(ProgressBar.getProgress() == 1)
            btnGuardar.setDisable(false);
        else
            btnGuardar.setDisable(true);
    }
    /**
     * Método encargado de vaciar todo los campos de la sección Datos Personales.
     */
    @FXML
    private void clickBtnLimparDatos(){
        txtNombres.setText("");
        txtApellidos.setText("");
        txtCelular.setText("");
        DPfechaNac.setValue(null);
        CheckHombre.setSelected(false);
        CheckMujer.setSelected(false);
        verificar();
    }
    /**
     * Método encargado de vaciar todo los campos de la sección Servicio.
     */
    @FXML
    private void clickBtnLimparServicio(){
        CBtipo.getSelectionModel().select(-1);
        CBhorario.getSelectionModel().select(-1);
        CheckPagoCompleto.setSelected(false);
        CheckAbono.setSelected(false);
        txtAbono.setText("");
        txtAbono.setDisable(false);
        SpinnerMeses.getValueFactory().setValue(0);
        verificar();
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initFilters();
        CBtipo.getItems().add("h");
        CBhorario.getItems().add("h");
    }
    
    private void initFilters(){
        //Set month range
        SpinnerValueFactory svf = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 12);
        SpinnerMeses.setValueFactory(svf);
        //Set KeyEvent to only read numbers in txtCelular
        txtCelular.addEventFilter(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>(){
            @Override
            public void handle(KeyEvent evt){
                char c = evt.getCharacter().charAt(0);
                if(c < '0' || c > '9') evt.consume();
            }
        });
        //Set KeyEvent to only read numbers in txtAbono
        txtAbono.addEventFilter(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>(){
            @Override
            public void handle(KeyEvent evt){
                char c = evt.getCharacter().charAt(0);
                if(c < '0' || c > '9') evt.consume();
            }
        });
        //Set KeyEvent to only read letters in txtNombres
        txtNombres.addEventFilter(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>(){
            @Override
            public void handle(KeyEvent evt){
                char c = evt.getCharacter().charAt(0);
                if((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || (c == 'ñ' && c == 'Ñ') || (c >= ' ' && c <= ' ')){ }else evt.consume();
            }
        });
        //Set KeyEvent to only read letters in txtApellidos
        txtNombres.addEventFilter(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>(){
            @Override
            public void handle(KeyEvent evt){
                char c = evt.getCharacter().charAt(0);
                if((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || (c == 'ñ' && c == 'Ñ') || (c >= ' ' && c <= ' ')){ }else evt.consume();
            }
        });
    }
}

