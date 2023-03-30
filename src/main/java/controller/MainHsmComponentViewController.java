package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.print.*;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import lombok.extern.log4j.Log4j2;

/**
 * FXML Controller class
 *
 * @author renato.pereira
 */
@Log4j2
public class MainHsmComponentViewController implements Initializable {

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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        txtKC1.setText("RENATO");
        txtKCV1.setText("TESTE");
    }


    public void printKey(MouseEvent mouseEvent) {
        // Cria um objeto de impressão padrão
        Printer printer = Printer.getDefaultPrinter();
        // Cria um job de impressão
        PageLayout pageLayout = printer.createPageLayout(Paper.A4, PageOrientation.PORTRAIT, Printer.MarginType.DEFAULT);

        String keyComponent1 = txtKC1.getText();
        String kcv1 = txtKCV1.getText();

        if (keyComponent1.isEmpty() || kcv1.isEmpty()) {
            log.warn("Key Component or KCV is empty. Cannot print.");
            return;
        }

        PrinterJob printerJob = PrinterJob.createPrinterJob(printer);

        if (printerJob != null && printerJob.showPrintDialog(txtKC1.getScene().getWindow())) {

            List<Node> nodesToPrint = new ArrayList<>();
            nodesToPrint.add(new Text(txtKC1.getText()));
            nodesToPrint.add(new Text(txtKCV1.getText()));

            // definindo o conteúdo a ser impresso
            Text text = new Text("NOME " + keyComponent1 + " VERSAO: " + kcv1);
            text.setWrappingWidth(pageLayout.getPrintableWidth());
            text.setTextAlignment(TextAlignment.LEFT);
            text.setFill(Color.BLACK);

            Scene scene = new Scene(new Group());
            for (Node node : nodesToPrint) {
                ((Group) scene.getRoot()).getChildren().add(node);
            }

            // imprimindo o conteúdo
            boolean success =   printerJob.printPage(pageLayout, scene.getRoot());;
            if (success) {
                printerJob.endJob();
                log.info("Key Component and KCV printed successfully.");
            } else {
                log.warn("Printing failed.");
            }
        } else {
            log.warn("Printer not available.");
        }


    }
}
