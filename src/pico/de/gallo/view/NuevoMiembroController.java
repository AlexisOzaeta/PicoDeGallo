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
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;

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
     * Método para el spinner de la cantidad de meses.
     * 
     * Manda a llamar al metodo verificar().
     */
    @FXML
    private void clickSpinner(){verificar();}
    /**
     * Método para el datePicker.
     * 
     * Comprueba que la fecha no sea futura o muy reciente.
     * Manda a llamar al metodo verificar().
     */
    @FXML
    private void clickDatePicker(){
        LocalDate fechaActual = LocalDate.now();
        LocalDate fechaSeleccionada = DPfechaNac.getValue();
        if(fechaActual.isBefore(fechaSeleccionada)){
            System.out.println("¿Estás intentando registrar a alguien que viene del futuro?");
            DPfechaNac.getEditor().clear();
        }else{
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
        //Campo Nombre
        if("".equals(txtNombres.getText())) campos[0] = 0;
        else campos[0] = 12;
        //Campo Apellido
        if("".equals(txtApellidos.getText())) campos[1] = 0;
        else campos[1] = 12;
        //Campo Celular
        if("".equals(txtCelular.getText())) campos[2] = 0;
        else campos[2] = 10;
        //Campo Genero
        if(CheckHombre.isSelected() == false && CheckMujer.isSelected() == false) campos[3] = 0;
        else campos[3] = 10;
        //Campo Fecha
        if(DPfechaNac.getValue() == null) campos[4] = 0;
        else campos[4] = 16;
        //Campo Tipo
        if(CBtipo.getValue() == null) campos[5] = 0;
        else campos[5] = 10;
        //Campo Horario
        if(CBhorario.getValue() == null) campos[6] = 0;
        else campos[6] = 10;
        //Campo Pago
        if(CheckAbono.isSelected() == false && CheckPagoCompleto.isSelected() == false) campos[7] = 0;
        else campos[7] = 10;
        //Campo Meses
        if(SpinnerMeses.getValue() == 0) campos[8] = 0;
        else campos[8] = 10;
        //Calculamos el porcentaje de la barra de progreso                    
        int tot = 0;
        for(int i = 0; i < 9; i++)
            tot += campos[i];
        ProgressText.setText(tot+"% Completo");
        double progreso = (double) tot / 100;
        ProgressBar.setProgress(progreso);
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        SpinnerValueFactory svf = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 12);
        SpinnerMeses.setValueFactory(svf);
        NumberValidator validator = new NumberValidator();
        validator.setMessage("Value must be a number");
        txtCelular.getValidators().add(validator);
    }
       
}

