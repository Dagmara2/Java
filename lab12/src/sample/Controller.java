package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;


public class Controller {

    @FXML
    private ImageView ogImg, changedImg;
    @FXML
    private Label ogLabel, changedLabel;
    @FXML
    private Button bwBtn, sepiaBtn, picBtn;
    @FXML
    private ComboBox<String> choiceBox;

    private FileChooser fileChooser;
    private ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
    private List<String> files = null;

    ObservableList<String> options;

    public void initialize() {
        files = getJSFiles();
        options = FXCollections.observableArrayList(files);
        choiceBox.setItems(options);

    }

    public void picChoice(ActionEvent actionEvent) {
        fileChooser = new FileChooser();
        File selectedFile = fileChooser.showOpenDialog(null);

        if (fileChooser != null) {
            Image img = new Image(selectedFile.toURI().toString());
            ogImg.setImage(img);
        }

    }


    private List<String> getJSFiles() {
        File dir = new File(".");
        File[] files = dir.listFiles((File dir1, String name1) -> name1.endsWith(".js"));
        LinkedList<String> result = new LinkedList<String>();
        assert files != null;
        result.add("None");
        for (File file : files) {
            result.push(file.getName());
        }

        return result;
    }

    public void formatChoice(ActionEvent actionEvent) throws FileNotFoundException, ScriptException {
        String converterName = choiceBox.getValue();

        assert converterName != null;
        if (converterName.equals("None")){
            return;
        }

            engine.eval(new FileReader(converterName));

    }


    public void format(ActionEvent actionEvent) throws ScriptException, NoSuchMethodException {
        if (Objects.requireNonNull(choiceBox.getValue()).equals("None")) {
            changedImg.setImage(ogImg.getImage());
            return;
        }
           changedImg.setImage(null);
            Invocable invoke = (Invocable) engine;

            Object result = null;

                result = invoke.invokeFunction("process", ogImg.getImage());

            changedImg.setImage((Image) result);
        }

}
