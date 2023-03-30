package keygen;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import lombok.extern.log4j.Log4j2;


/**
 *
 * @author renato.pereira
 */
@Log4j2
public class HsmGenerateKeyComponentApp extends Application {


    private double xOffset = 0;
    private double yOffset = 0;
    
    @Override
    public void start(Stage stage) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/viewFxml/mainHsmComponentView.fxml"));
            root.setOnMousePressed((MouseEvent event) -> {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        });
        root.setOnMouseDragged((MouseEvent event) -> {
            stage.setX(event.getScreenX() - xOffset);
            stage.setY(event.getScreenY() - yOffset);
        });
        Scene scene = new Scene(root);
        stage.setTitle("HSM TEST:: Version 1.0");
        stage.initStyle(StageStyle.DECORATED);
        stage.setResizable(false);

        stage.setScene(scene);

        stage.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
         
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        log.error("TESTE TESTE 2");
        launch(args);
    }
    
}
