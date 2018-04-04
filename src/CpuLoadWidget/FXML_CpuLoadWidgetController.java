/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CpuLoadWidget;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author marin
 */
public class FXML_CpuLoadWidgetController implements Initializable {

    @FXML
    private Arc arc_progress;
    
    @FXML
    private Label lbl_percent;
    
    @FXML
    private ImageView btn_close;

    @FXML
    private void btn_close_clicked(MouseEvent event) {
        System.exit(0);
    }
    double xOffset = 0;
    double yOffset = 0;
    @FXML
    private void btn_about_clicked() {
        try {
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/customaboutdialog/FXML_CustomAboutDialog.fxml"));
            
            root.setOnMousePressed((MouseEvent event) -> {
                xOffset = stage.getX() - event.getScreenX();
                yOffset = stage.getY() - event.getScreenY();
            });

            root.setOnMouseDragged((MouseEvent event) -> {
                stage.setX(event.getScreenX() + xOffset);
                stage.setY(event.getScreenY() + yOffset);
            });
        
            Scene scene = new Scene(root);
            scene.setFill(Color.TRANSPARENT);
            String css = this.getClass().getResource("/customaboutdialog/resources/CSS_CustomAboutDialog_WhiteAndBlue.css").toExternalForm(); 
            scene.getStylesheets().add(css);

            stage.initStyle(StageStyle.TRANSPARENT);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ProgressThread pt = new ProgressThread(arc_progress, lbl_percent);
        pt.start();
    }  
}
