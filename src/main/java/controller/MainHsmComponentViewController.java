package controller;

import hsm.HSMEndpointThales;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;

import lombok.extern.log4j.Log4j2;
import service.HsmService;
import service.PrintService;


/**
 * Classe controladora da tela principal do gerador de componente
 * @author renato.pereira
 */

@Log4j2
public class MainHsmComponentViewController implements Initializable {

    PrintPageController printPageController;
    HsmService hsmService;
    PrintService printService;

    @FXML
    private TextField txtKC1;
    @FXML
    private TextField txtKCV1;
    @FXML
    private RadioButton radio3DES;
    @FXML
    private RadioButton radioDES;
    @FXML
    private RadioButton radioDESKeyBlock;
    @FXML
    private ToggleGroup grpTypeKey;
    @FXML
    private Button btnGenerateKey;
    @FXML
    private Button btnPrint;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/viewFxml/PagePrintKey.fxml"));
        hsmService   = new HsmService  ();
        printService = new PrintService();
        try {
            loader.load();
            printPageController = loader.getController();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void printKey(MouseEvent mouseEvent) {
        HSMEndpointThales hsmEndpointThales = new HSMEndpointThales();
        printService.printPanel(printPageController.getAllPanelContent());
    }


}
