package org.example.eiscuno.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.example.eiscuno.model.unoenum.EISCUnoEnum;
import java.io.IOException;

/**
 * Represents a stage that displays the instructions for the game.
 *
 * @author Jerson Alexis Ortiz Velasco
 * @author Jhon Antony Murillo Olave
 * @author Camilo Vidales Lucumi
 * @version 1.0
 * @since 1.0
 */
public class InstructionsStage extends Stage {

    /**
     * Constructs a new instance of InstructionsStage.
     *
     * @throws IOException if an error occurs while loading the FXML file for the instructions interface.
     */
    public InstructionsStage() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/eiscuno/instructions-view.fxml"));
        Parent root;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new IOException("Error while loading FXML file", e);
        }

        Scene scene = new Scene(root);

        setTitle("INSTRUCCIONES");
        getIcons().add(
                new Image(
                        String.valueOf(getClass().getResource(EISCUnoEnum.FAVICON.getFilePath()))));
        setScene(scene);
        setResizable(false);
        show();
        this.setOnCloseRequest(event -> InstructionsStage.deleteInstance());
    }

    /**
     * Closes the instance of InstructionsStage.
     * This method is used to clean up resources when the instructions stage is no longer needed.
     */
    public static void deleteInstance() {
        InstructionsStage.instructionsStageHolder.INSTANCE.close();
        InstructionsStage.instructionsStageHolder.INSTANCE = null;
    }

    /**
     * Retrieves the singleton instance of InstructionsStage.
     *
     * @return the singleton instance of InstructionsStage.
     * @throws IOException if an error occurs while creating the instance.
     */
    public static InstructionsStage getInstance() throws IOException {
        return InstructionsStage.instructionsStageHolder.INSTANCE != null ?
                InstructionsStage.instructionsStageHolder.INSTANCE :
                (InstructionsStage.instructionsStageHolder.INSTANCE = new InstructionsStage());
    }

    /**
     * Holder class for the singleton instance of InstructionsStage.
     * This class ensures lazy initialization of the singleton instance.
     */
    private static class instructionsStageHolder {
        private static InstructionsStage INSTANCE;
    }
}
