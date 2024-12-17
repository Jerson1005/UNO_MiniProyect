package org.example.eiscuno.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.example.eiscuno.model.unoenum.EISCUnoEnum;
import java.io.IOException;

/**
 * Represents a stage displayed when the player wins the game.
 *
 * @author Jerson Alexis Ortiz Velasco
 * @author Jhon Antony Murillo Olave
 * @author Camilo Vidales Lucumi
 * @version 1.0
 * @since 1.0
 */
public class WinStage extends Stage {

    /**
     * Constructs a new instance of WinStage.
     *
     * @throws IOException if an error occurs while loading the FXML file for the game interface.
     */
    public WinStage() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/eiscuno/win-view.fxml"));
        Parent root;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new IOException("Error while loading FXML file", e);
        }

        Scene scene = new Scene(root);

        setTitle("UNO");
        getIcons().add(
                new Image(
                        String.valueOf(getClass().getResource(EISCUnoEnum.FAVICON.getFilePath()))));
        setScene(scene);
        setResizable(false);
        show();
    }

    /**
     * Closes the instance of WinStage.
     * This method is used to clean up resources when the game stage is no longer needed.
     */
    public static void deleteInstance() {
        WinStageHolder.INSTANCE.close();
        WinStageHolder.INSTANCE = null;
    }

    /**
     * Retrieves the singleton instance of WinStage.
     *
     * @return the singleton instance of WinStage.
     * @throws IOException if an error occurs while creating the instance.
     */
    public static WinStage getInstance() throws IOException {
        return WinStageHolder.INSTANCE != null ?
                WinStageHolder.INSTANCE :
                (WinStageHolder.INSTANCE = new WinStage());
    }

    /**
     * Holder class for the singleton instance of WinStage.
     * This class ensures lazy initialization of the singleton instance.
     */
    private static class WinStageHolder {
        private static WinStage INSTANCE;
    }
}
