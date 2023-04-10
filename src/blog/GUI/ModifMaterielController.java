/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blog.GUI;

import blog.Entities.Fournisseur;
import blog.Entities.Materiel;
import static blog.GUI.ModifFournisseurController.idF;
import blog.Services.FournisseurService;
import blog.Services.MaterielService;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Ahmed Ben Abid
 */
public class ModifMaterielController implements Initializable {

    @FXML
    private Button homeButton;

    @FXML
    private Button returnDetailM;

    @FXML
    private Button validModifM;

    @FXML
    public TextField modifnomM;

    @FXML
    public TextField modifrefM;

    public static int idM, qttM;
    public static String dateM;

    public static int getQttM() {
        return qttM;
    }

    public static void setQttM(int qttM) {
        ModifMaterielController.qttM = qttM;
    }

    public static String getDateM() {
        return dateM;
    }

    public static void setDateM(String dateM) {
        ModifMaterielController.dateM = dateM;
    }

    public int getIdM() {
        return idM;
    }

    public void setIdM(int idF) {
        this.idM = idF;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    private boolean verifs() {
        if (modifnomM.getText().isEmpty() || !modifnomM.getText().matches("[a-zA-Z ]+")) {
            JOptionPane.showMessageDialog(null, "Le champ nom est obligatoire et doit contenir uniquement des lettres majuscule de l'alphabet.");
            return false;
        }
        if (modifrefM.getText().isEmpty() || !modifrefM.getText().matches("[A-Z0-9]{8}")) {
            JOptionPane.showMessageDialog(null, "Le champ reference est obligatoire et et doit contenir uniquement 8 lettres ou chiffres!");
            return false;
        }
        return true;
    }

    @FXML
    private void ModifierFF(javafx.event.ActionEvent event) throws IOException, SQLException {
        if (verifs()) {
            Materiel modifiedFournisseur = new Materiel(
                    modifnomM.getText(),
                    modifrefM.getText(),
                    dateM,
                    qttM);
            MaterielService fs = new MaterielService();
            fs.update(modifiedFournisseur, idF);
            modifiedFournisseur.setId(idF);
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("DetailMateriel.fxml"));
                Parent root = loader.load();
                DetailMaterielController controller = loader.getController();
                controller.setFournisseurr(modifiedFournisseur);
                Scene scene = new Scene(root);
                Stage stage = (Stage) validModifM.getScene().getWindow();
                stage.setScene(scene);
                stage.show();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    @FXML
    private void puser(javafx.event.ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/backOfficeHome.fxml"));
        Parent root = loader.load();
        homeButton.getScene().setRoot(root);

    }

    @FXML
    private void returnbackModif(javafx.event.ActionEvent event) throws IOException, SQLException {
        MaterielService fs = new MaterielService();
        Materiel f = fs.getOneById(idM);
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("DetailMateriel.fxml"));
            Parent root = loader.load();
            DetailMaterielController controller = loader.getController();
            controller.setFournisseurr(f);
            Scene scene = new Scene(root);
            Stage stage = (Stage) returnDetailM.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
