package application;

import java.sql.*;
import javax.sql.*;
import javax.swing.JFileChooser;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.SelectionMode;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import model.Note;
import application.NoteController;

/**
 * NotePadController class.
 * @author Yarií Soto - Nasera Boulehoual
 * @version 1.0, 24 May 2023
 */
public class NotePadController implements Initializable{
	
	//Variables from NoteView
	@FXML
	private MenuBar menuBarNotePad;
	
	@FXML
	private Menu openExternalNote;
	
	@FXML
	private Button newNote;
	
	@FXML
	private Menu editNote;
	
	@FXML
	private Menu deleteNote;
	
	@FXML
	private Button exitButton;
	
	@FXML
	private ScrollBar scrollNotes;
	
	@FXML
	private ListView<String> notesListView;
	
	/**
	 * A variable to store an local file. -- TODO HA DE SER FILE, pero para pruevas és String.
	 */
	private boolean openFile=false;
	
	/**
	 * ObservableList to store the note objects.
	 */
	private ObservableList<Note> notes;
	
	/**
	 * ObservableList to store the notes title.
	 */
	private ObservableList<String> arrayListTitles = FXCollections.observableArrayList();;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		notes = FXCollections.observableArrayList();
		
		notes.add(new Note("Nota","Soy una nota.",1));
		notes.add(new Note("Nota2","Soy una nota 2.",2));
		notes.add(new Note("Nota 3","Soy una nota 3.",3));
		
		
		
		//Connection to data base.	
		/*try {
			Connection notesConnection = DriverManager.getConnection("jdbc:mysql://sql8.freesqldatabase.com:3306/sql8620870","sql8620870","Br7vTpCslf");
			
			//TODO comprovar el id de usuario.
			/*
			Date date = Date.valueOf(note.getNoteDate());
			String title = note.getTitle();
			String body = note.getBody();
			int userId= note.getIdUser();*/
		
			/*Statement statement = notesConnection.createStatement();
			ResultSet sql = statement.executeQuery("SELECT title FROM Note");
           
			
			while(sql.next()) {
				arrayListTitles.add(sql.getString(1));
			}
			
			//Get the notes title.
			/*for(Note note : notes) {
				arrayListTitles.add(note.getTitle());
			}*/
			
			
			//String title = note.getTitle();
            
            //Confirm creation.
           /* Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setTitle("Informació");
            alert.setContentText("S'ha creat la connexió");
            alert.showAndWait();
            
		} catch (SQLException e) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
		    alert.setHeaderText(null);
		    alert.setTitle("Error");
		    alert.setContentText("No s'ha pogut connectar amb la base de dades.");
		    alert.showAndWait();
		}*/
		
		
		notesListView.setItems(arrayListTitles);
		
		notesListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
	}
	
	/**
	 * Method to open an external note.
	 * @param event
	 */
	/*public void OpenExtNote() {
		//TODO Se ha de codificar
		
		//Create a file selector
		JFileChooser fileSelector=new JFileChooser();
		
		//Indicate what type of file the user can see
		fileSelector.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		
		/*File logoFile = new File();
		Image logoImage = new Image(logoFile.toURI().toString());
		imageLogo.setImage(logoImage);*/
		
		// Carga la image del icono atravez de la ruta donde se encuantra.
		
		/*File iconFile = new File("/home/nasera/git/Repository_YN/Project_QuickNote/Image/Captura desde 2023-05-26 06-35-02.png");
		Image iconImage = new Image(iconFile.toURI().toString());
		ImageIcon.setImage(iconImage);*/
		/*NoteController.initializeOpenFile(openFile);
	}*/
	
	/**
	 * Method to change to Note windows.
	 * @param event
	 */
	@FXML
	private void newNoteAction(ActionEvent event) {
		NoteController.initializeOpenFile(openFile);
		openNoteWindows();
	}
	
	/**
	 * Method to open the note windows.
	 */
	private void openNoteWindows() {
		try {
			FXMLLoader loader = new FXMLLoader();
			
			loader.setLocation(Main.class.getResource("/view/NoteView.fxml"));
			
			Parent windows = loader.load();
			
			Scene scene = new Scene(windows);
			
			Stage stage = new Stage();
			
			stage.setScene(scene);
			
			stage.show();
			
			Stage myStage = (Stage) this.exitButton.getScene().getWindow();
			myStage.close();
			
		} catch (IOException e) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
		    alert.setHeaderText(null);
		    alert.setTitle("Error");
		    alert.setContentText("No s'ha trobat la vista.");
		    alert.showAndWait();
		}
		
		
	}
	
	
	/**		
	 * Method to delete a note.
	 * @param event
	 */
	public void deleteNote(ActionEvent event) {
		//TODO 
	}
	
	//TODO DA ERROR POR ALGUNA RAZON!!!
	/**
	 * Method to close the NotePad.
	 * @param event - on click.
	 */
	@FXML
	private void exitNotePad(ActionEvent event) {
		Node source = (Node) event.getSource();
	    Stage stage = (Stage) source.getScene().getWindow();
	    stage.close();
	}
	
	
	/**
	 * Method to scroll at notes windows.
	 * @param event - 
	 */
	/*public void scrollNotes(ActionEvent event) {
		//TODO to implement.
	}*/
	
	
	@FXML
	private void editNoteAction() {
		
	}
}
