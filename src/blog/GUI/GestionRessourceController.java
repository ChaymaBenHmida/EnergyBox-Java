/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blog.GUI;

import blog.Services.soundService;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 * FXML Controller class
 *
 * @author Ahmed Ben Abid
 */
public class GestionRessourceController implements Initializable {

    @FXML
    private Button GestionF;

    @FXML
    private Button GestionC;

    @FXML
    private Button GestionM;
    @FXML
    private Button returnR;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
        private void GoToF(javafx.event.ActionEvent event) throws IOException 
       {
             soundService ss = new soundService();
        ss.btn2();
           FXMLLoader loader= new FXMLLoader(getClass().getResource("../GUI/ListFournisseur.fxml"));
                   Parent root= loader.load();
                 GestionF.getScene().setRoot(root);
                   
       }
         @FXML
        private void puser(javafx.event.ActionEvent event) throws IOException 
       {
           FXMLLoader loader= new FXMLLoader(getClass().getResource("../GUI/backOfficeHome.fxml"));
                   Parent root= loader.load();
                 returnR.getScene().setRoot(root);
                   
       }
        @FXML
        private void GoToC(javafx.event.ActionEvent event) throws IOException 
       {  soundService ss = new soundService();
        ss.btn2();
           FXMLLoader loader= new FXMLLoader(getClass().getResource("../GUI/ListCharge.fxml"));
                   Parent root= loader.load();
                 GestionC.getScene().setRoot(root);
                   
       }
        @FXML
        private void GoToM(javafx.event.ActionEvent event) throws IOException 
       {  soundService ss = new soundService();
        ss.btn2();
           FXMLLoader loader= new FXMLLoader(getClass().getResource("../GUI/ListMateriel.fxml"));
                   Parent root= loader.load();
                 GestionM.getScene().setRoot(root);
                   
       }
}
