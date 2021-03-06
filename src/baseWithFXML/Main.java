package baseWithFXML; /** SOURCES:
 * https://docs.oracle.com/javafx/2/get_started/hello_world.htm //basic window
 * https://o7planning.org/en/11533/opening-a-new-window-in-javafx //second window
 * https://o7planning.org/en/10623/javafx-tutorial-for-beginners //FX Scene Builder
 * https://stackoverflow.com/questions/15041760/javafx-open-new-window //new window on button click with FXML
 * https://stackoverflow.com/questions/1053467/how-do-i-save-a-string-to-a-text-file-using-java //Writing to file
 * https://stackoverflow.com/questions/19961844/creating-multiple-objects-with-different-names-in-a-loop-to-store-in-an-array-li
 * https://stackoverflow.com/questions/5868369/how-to-read-a-large-text-file-line-by-line-using-java
 */

//TODO: add hoverOverLabels to addItemWindow

import baseWithFXML.ui.PrimaryController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {

    //private static PrimaryController psc;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/baseWithFXML/ui/PrimaryScene.fxml"));
            Parent root = loader.load();
            PrimaryController psc = loader.getController();

            primaryStage.setOnCloseRequest(e -> psc.getDatamodel().saveList());
            primaryStage.setTitle("Gear List Application");
            primaryStage.setScene(new Scene(root));
            primaryStage.setResizable(false);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}