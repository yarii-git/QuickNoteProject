package application;

import java.sql.*;
import javax.sql.*;
import javax.swing.*;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

import model.Note;

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
	static boolean openFile=false;
	
	/**
	 * ObservableList to store the note objects.
	 */
	static ObservableList<Note> notes = FXCollections.observableArrayList();
	
	/**
	 * ObservableList to store the notes title.
	 */
	private ObservableList<String> arrayListTitles = FXCollections.observableArrayList();
	
	
	/**
	 * Variable to store the current note selection.
	 */
	private String currentNote;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		notes = FXCollections.observableArrayList();
		
		//TESTS
		/*notes.add(new Note("Nota","Soy una nota.",1));
		notes.add(new Note("Nota2","Soy una nota 2.",2));
		notes.add(new Note("Nota 3","Soy una nota 3.",3));*/
		
		
		
		//Connection to data base.	
		try {
			Connection notesConnection = DriverManager.getConnection("jdbc:mysql://sql8.freesqldatabase.com:3306/sql8622418","sql8622418","ckypqL8v3e");
			
			//TODO comprovar el id de usuario.
			
			/*
			Date date = Date.valueOf(note.getNoteDate());
			String title = note.getTitle();
			String body = note.getBody();
			int userId= note.getIdUser();*/
		
			Statement statement = notesConnection.createStatement();
			ResultSet sql = statement.executeQuery("SELECT * FROM Note");
           
			//Get the notes title.
			while(sql.next()) {
				arrayListTitles.add(sql.getString(3));
			}
			
			//Get the notes.
			while(sql.next()) {
				notes.add(new Note(sql.getInt(1),sql.getDate(2).toLocalDate(),sql.getString(3),sql.getString(4)));
			}
            
            //Confirm creation. TESTS
           /* Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setTitle("Informació");
            alert.setContentText("S'ha creat la connexió");
            alert.showAndWait();*/
            
		} catch (SQLException e) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
		    alert.setHeaderText(null);
		    alert.setTitle("Error");
		    alert.setContentText("No s'ha pogut connectar amb la base de dades.");
		    alert.showAndWait();
		}
		
		//Set the notes title at notesListView.
		notesListView.setItems(arrayListTitles);
		
		//
		notesListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		
		//Select an item.
		notesListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
				
				currentNote = notesListView.getSelectionModel().getSelectedItem();
			}	
		});
	}
	
	/**
	 * Method to open an external note.
	 * @param event
	 */
	private void OpenExtNote() {
		//TODO Se ha de codificar
		
		//Create a file selector
		//JFileChooser fileSelector=new JFileChooser();
		
		//Indicate what type of file the user can see
		//fileSelector.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		
		/*File logoFile = new File();
		Image logoImage = new Image(logoFile.toURI().toString());
		imageLogo.setImage(logoImage);*/
		
		// Carga la image del icono atravez de la ruta donde se encuantra.
		
		/*File iconFile = new File("/home/nasera/git/Repository_YN/Project_QuickNote/Image/Captura desde 2023-05-26 06-35-02.png");
		Image iconImage = new Image(iconFile.toURI().toString());
		ImageIcon.setImage(iconImage);*/
		//NoteController.initializeOpenFile(openFile);
	}
	
	/**
	 * Method to change to Note windows.
	 * @param event
	 */
	@FXML
	private void newNoteAction(ActionEvent event) {
		//NoteController.initializeOpenFile(openFile);
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
	private void deleteNote() {
		//
				
		
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
	
	/**
	 * Method to close the NotePad.
	 * @param event - on click.
	 */
	@FXML
	private void exitNotePad() {
		/*Node source = (Node) event.getSource();
	    Stage stage = (Stage) source.getScene().getWindow();
	    stage.close();*/
	     Stage stage = (Stage) this.exitButton.getScene().getWindow();
	     stage.close();
	}
}
