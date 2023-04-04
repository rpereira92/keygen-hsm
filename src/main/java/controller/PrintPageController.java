package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Classe que cria o controlador da página de impressão
 */
public class PrintPageController implements Initializable {
    @FXML
    private VBox root;

    @FXML
    private ImageView logoImage;

    @FXML
    private Text printPageCompTypeText;

    @FXML
    private Text printPageCompKeyText;

    @FXML
    private Text printPageKCVText;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Load logo image
        Image logo = new Image(getClass().getResourceAsStream("/logo.png"));
        logoImage.setImage(logo);

        // Set fonts
        Font boldFont = Font.font("System Bold", 12);
        printPageCompTypeText.setFont(boldFont);
        printPageCompKeyText.setFont(boldFont);
        printPageKCVText.setFont(boldFont);
    }

    public Node getAllPanelContent() {
        return root;
    }

}
