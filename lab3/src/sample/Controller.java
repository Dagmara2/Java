package sample;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.DirectoryChooser;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.nio.file.DirectoryIteratorException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Controller {

    DirectoryChooser directoryChooser;

    int current = 0;
    boolean changeTurn = false;


    ArrayList<String> nameList;
    ReferenceQueue<Image> imageViewReferenceQueue = new ReferenceQueue<Image>();

    WeakReference<Image> topSoftReference;

    @FXML
    private Button FileChooserButton;


    @FXML
    private Button nextBtn;

    @FXML
    private Button prevBtn;

    @FXML
    private Label ImagaChooserLabel;

    @FXML
    private ImageView imgView;



    public void ImgController()
    {

        imgView = new ImageView();
        imgView.maxHeight(300);
        imgView.maxWidth(200);
    }


    @FXML
    void handleFileChooserButton(ActionEvent event) throws FileNotFoundException {
        nameList = new ArrayList<String>();


        directoryChooser = new DirectoryChooser();
        File selectedFile = directoryChooser.showDialog(null);

        if (selectedFile.isDirectory()){
            Path tempPath = selectedFile.toPath();
            try (DirectoryStream<Path> stream = Files.newDirectoryStream(tempPath)) {
                for (Path filePath: stream) {

                    String url = filePath.getParent().toString() +"\\"+ filePath.getFileName().toString() ;
                    nameList.add(url);


                }


            } catch (IOException | DirectoryIteratorException x) {
            }

        } else {
            System.out.println("Error! Directory wasn't selected");
        }

        System.out.println(nameList.get(0).toString());

        topSoftReference = new WeakReference<Image>(new Image(new FileInputStream(nameList.get(0).toString())));


        SetUpPictures();

    }

    void SetUpPictures(){
        imgView.setImage((Image)topSoftReference.get());

    }

    @FXML
    void nextImg() throws FileNotFoundException {
        if(current==0){
            current =nameList.size()-1;
        }else  current--;


        topSoftReference = new WeakReference<Image>(new Image(new FileInputStream(nameList.get(current))),imageViewReferenceQueue);
        SetUpPictures();

    }


    @FXML
    void prevImg(ActionEvent event) throws FileNotFoundException {

        if(current==(nameList.size()-1)){
            current =0;
        }else  current++;


        topSoftReference = null;
        SetUpPictures();

    }

}

