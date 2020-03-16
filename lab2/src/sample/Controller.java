package sample;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.*;
import java.nio.charset.Charset;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.cell.PropertyValueFactory;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;


public class Controller {


    @FXML
    private Menu ProgramMenu, LanguageMenu;
    @FXML
    private RadioMenuItem EnglishMenu, PolishMenu;
    @FXML
    private MenuItem AboutMenu, ExitMenu;
    @FXML
    private Label startL, endL, objectCountL;
    @FXML
    private DatePicker startDate, endDate;
    @FXML
    private Button okBtn;
    @FXML
    private TableView<Asteroid> asteroidTable;
    @FXML
    private TableColumn<Asteroid,String> asteroidName;
    @FXML
    private TableColumn<Asteroid,String>  asteroidLink;
    @FXML
    private TableColumn<Asteroid,String> asteroidHazardous;


    ResourceBundle resourceBundle = ResourceBundle.getBundle("language");
    Locale locale;
   //

    public void aboutProgram(ActionEvent actionEvent) {
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setContentText(resourceBundle.getString("aboutInfo"));
        a.showAndWait();
        return;
    }

    public void loadLang(String lang)
    {
        locale = new Locale(lang);
        resourceBundle = ResourceBundle.getBundle("language",locale);

    }
    public void LangPolish(ActionEvent actionEvent){
        loadLang("pl");
        updateText();
    }

    public void LangEnglish(ActionEvent actionEvent){
        loadLang("en");
        updateText();
    }

    public void updateText()
    {
        ProgramMenu.setText(resourceBundle.getString("Program"));
        AboutMenu.setText(resourceBundle.getString("About_Program"));
        ExitMenu.setText(resourceBundle.getString("Exit"));
        LanguageMenu.setText(resourceBundle.getString("Language"));
        EnglishMenu.setText(resourceBundle.getString("English"));
        PolishMenu.setText(resourceBundle.getString("Polish"));
        startL.setText(resourceBundle.getString("start_date"));
        endL.setText(resourceBundle.getString("end_date"));
        okBtn.setText(resourceBundle.getString("OK"));
        objectCountL.setText(resourceBundle.getString("Object_count"));
        asteroidName.setText(resourceBundle.getString("asteroid_name"));
        asteroidLink.setText(resourceBundle.getString("asteroid_link"));
        asteroidHazardous.setText(resourceBundle.getString("hazardous"));

    }

    public class Asteroid {


        private String asteroidName;
        private String asteroidLink;
        private boolean asteroidHazardous;

        public Asteroid(String asteroidName, String asteroidLink, boolean asteroidHazardous) {


            this.asteroidName = asteroidName;
            this.asteroidLink = asteroidLink;
            this.asteroidHazardous = asteroidHazardous;
        }
        public String getAsteroidName() {
                return asteroidName.toString();
        }

        public String getAsteroidLink() {
            return asteroidLink.toString();
        }

        public boolean getAsteroidHazardous() {
            return asteroidHazardous;
        }

        public void setAsteroidName(String asteroidName) {
            this.asteroidName = asteroidName;
        }

        public void setAsteroidLink(String asteroidLink) {
            this.asteroidLink = asteroidLink;
        }

        public void setAsteroidHazardous(boolean asteroidHazardous) {
            this.asteroidHazardous = asteroidHazardous;
        }
    }


    public void GetData(ActionEvent actionEvent) throws IOException {

        LocalDate date_start = startDate.getValue();
        LocalDate date_end = endDate.getValue();

        asteroidName.setCellValueFactory(new PropertyValueFactory<Asteroid, String>("name"));
        asteroidLink.setCellValueFactory(new PropertyValueFactory<Asteroid, String>("nasa_jpl_url"));
        asteroidHazardous.setCellValueFactory(new PropertyValueFactory<Asteroid, String>("is_potentially_hazardous_asteroid"));

        StringBuilder sb = new StringBuilder();

        sb.append("https://api.nasa.gov/neo/rest/v1/feed?start_date=");
        sb.append(date_start.toString());
        sb.append("&end_date=");
        sb.append(date_end.toString());
        sb.append("&api_key=8AaFaFjggCvH9QBFIxYm2og9Lf7jvF8KdfE0AqcC");


        JSONObject json = readJsonFromUrl(sb.toString());


        objectCountL.setText(resourceBundle.getString("Object_count") + json.get("element_count"));

        JSONObject res = json.getJSONObject("near_earth_objects");

        List<String> keyList = new ArrayList<>();
        Iterator iterator = res.keys();
        while (iterator.hasNext()) {
            String key = iterator.next().toString();
            keyList.add(key);
        }

        System.out.println(keyList);

       List<Asteroid> asteroid = new ArrayList<>();



        for (String s : keyList) {
            JSONArray jsonArray = res.getJSONArray(s);
            for (int i = 0; i < jsonArray.length(); i++) {
                String asteroidName = jsonArray.getJSONObject(i).getString("name");
                String asteroidLink = jsonArray.getJSONObject(i).getString("nasa_jpl_url");
                boolean asteroidHazardous = (boolean) jsonArray.getJSONObject(i).get("is_potentially_hazardous_asteroid");
                asteroid.add(new Asteroid(asteroidName, asteroidLink, asteroidHazardous));
                //asteroidTable.setItems(asteroid);
                System.out.println("Name: " +asteroidName + " Link: " + asteroidLink + " Hazardous: " + asteroidHazardous);

            }
        }

        for (Asteroid a : asteroid) {
            asteroidTable.getItems().add(a);
            //asteroidTable.setItems();
        }

    }


    private static String readAll(Reader rd) throws IOException {

        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp=rd.read())!=-1)
        {
            sb.append((char)cp);
        }
        return sb.toString();
    }

    public static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {

        InputStream is = new URL(url).openStream();
        try{
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            JSONObject json = new JSONObject(jsonText);
            return json;
        }finally {
            is.close();
        }
    }

    public void exitProgram(ActionEvent actionEvent) { System.exit(0);}
}
