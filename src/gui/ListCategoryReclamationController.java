/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.CategoryReclamation;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;
import services.CategoryReclamationService;

/**
 * FXML Controller class
 *
 * @author azizo
 */
public class ListCategoryReclamationController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private AnchorPane listCategRecPane;
    
    @FXML
    void open_addCategoryReclamation(ActionEvent event) throws IOException {
        Parent fxml= FXMLLoader.load(getClass().getResource("ajoutCategoryReclamation.fxml"));
        listCategRecPane.getChildren().removeAll();
        listCategRecPane.getChildren().setAll(fxml);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        AfficherCategoryReclamation();
    }   
    
    
    /*TableView CategoryReclamation*/
    @FXML
    private TableColumn<CategoryReclamation, String> NomCategRecCell;
    @FXML
    private TableColumn<CategoryReclamation, String> PrioriteCategRecCell;
    @FXML
    private TableColumn<CategoryReclamation, String> DescriptionCategRecCell;
    @FXML
    private TableView<CategoryReclamation> tableCategReclamation;
    
    
    @FXML
    private Button btnDeleteCategRec;
    @FXML
    private TextField txtSearchCategRec;
    
    
    ObservableList<CategoryReclamation> dataCategoryReclamation = FXCollections.observableArrayList();
    
    
    public void AfficherCategoryReclamation()
    {
        CategoryReclamationService crs = new CategoryReclamationService();
        crs.Show().stream().forEach((c) -> {
            dataCategoryReclamation.add(c);
        });
        
        NomCategRecCell.setCellValueFactory(new PropertyValueFactory<>("nom_category"));
        NomCategRecCell.setCellFactory(TextFieldTableCell.forTableColumn());
        NomCategRecCell.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<CategoryReclamation, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<CategoryReclamation, String> event) {
                CategoryReclamation cr = event.getRowValue();
                cr.setNom_category(event.getNewValue());
                CategoryReclamationService crs = new CategoryReclamationService();
                crs.modifier(cr);
            }
        });
        DescriptionCategRecCell.setCellValueFactory(new PropertyValueFactory<>("description_category"));
        DescriptionCategRecCell.setCellFactory(TextFieldTableCell.forTableColumn());
        DescriptionCategRecCell.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<CategoryReclamation, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<CategoryReclamation, String> event) {
                CategoryReclamation cr = event.getRowValue();
                cr.setDescription_category(event.getNewValue());
                CategoryReclamationService crs = new CategoryReclamationService();
                crs.modifier(cr);
            }
        });
        PrioriteCategRecCell.setCellValueFactory(new PropertyValueFactory<>("priorite_category"));
        PrioriteCategRecCell.setCellFactory(TextFieldTableCell.forTableColumn());
        PrioriteCategRecCell.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<CategoryReclamation, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<CategoryReclamation, String> event) {
                CategoryReclamation cr = event.getRowValue();
                cr.setPriorite_category(event.getNewValue());
                CategoryReclamationService crs = new CategoryReclamationService();
                crs.modifier(cr);
            }
        });
        tableCategReclamation.setItems(dataCategoryReclamation);
    }
    
    @FXML
    private void supprimerCategoryReclamation(ActionEvent event) throws SQLException {
        CategoryReclamationService crs = new CategoryReclamationService();
        
        if (tableCategReclamation.getSelectionModel().getSelectedItem() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Veuillez sélectionner la catégorie à supprimer");
            alert.showAndWait();
            return;
        }

        // Afficher une boîte de dialogue de confirmation
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Voulez-vous vraiment supprimer cette catégorie ?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            // Récupérer l'ID du planning sélectionnée dans la vue de la table
            int id = tableCategReclamation.getSelectionModel().getSelectedItem().getId();

            // Supprimer la réclamation de la base de données
            crs.supprimer(id);
            // Rafraîchir la liste de données
            dataCategoryReclamation.clear();
            AfficherCategoryReclamation();
            // Rafraîchir la vue de la table
            tableCategReclamation.refresh();
        }
    }
    
    
}