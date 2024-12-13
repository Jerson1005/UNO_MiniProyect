package org.example.eiscuno.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.example.eiscuno.model.unoenum.EISCUnoEnum;

import java.io.IOException;

public class InstructionsStage extends Stage {


    public InstructionsStage() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/eiscuno/instructions-view.fxml"));
        Parent root;
        try {
            root = loader.load();
        } catch (IOException e) {
            // Re-throwing the caught IOException
            throw new IOException("Error while loading FXML file", e);
        }

        Scene scene = new Scene(root);

        // Configuring the stage
        setTitle("INSTRUCIONES "); // Sets the title of the stage
        getIcons().add(
                new Image(
                        String.valueOf(getClass().getResource(EISCUnoEnum.FAVICON.getFilePath()))));
        setScene(scene); // Sets the scene for the stage
        setResizable(false); // Disallows resizing of the stage
        show(); // Displays the stage
        this.setOnCloseRequest(event -> InstructionsStage.deleteInstance());
    }


    public static void deleteInstance() {
        InstructionsStage.instructionsStageHolder.INSTANCE.close();
        InstructionsStage.instructionsStageHolder.INSTANCE = null;
    }

    public static InstructionsStage getInstance() throws IOException {
        return InstructionsStage.instructionsStageHolder.INSTANCE != null ?
                InstructionsStage.instructionsStageHolder.INSTANCE :
                (InstructionsStage.instructionsStageHolder.INSTANCE = new InstructionsStage());
    }


    private static class instructionsStageHolder {
        private static InstructionsStage INSTANCE;
    }
}
