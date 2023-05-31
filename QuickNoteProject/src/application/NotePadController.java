package application;

import java.sql.*;
import java.time.LocalDate;

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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
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
	
	@FXML private MenuBar menuBarNotePad;
	
	@FXML private Menu openExternalNote;
	
	@FXML private Button newNote;
	
	@FXML private Menu editNote;
	
	@FXML private Menu deleteNote;
	
	@FXML private Button exitButton;
	
	@FXML private ScrollBar scrollNotes;
	
	/*-----------------Table section----------------*/
	//Table.
	@FXML private TableView<Note> notesTableView;
	
	//Columns.
	@FXML private TableColumn<Note, Integer>colID;
	
	@FXML private TableColumn<Note, String>colTitle;
	
	@FXML private TableColumn<Note, String> colBody;
	
	@FXML private TableColumn<Note, LocalDate> colDate;
	
	@FXML private TableColumn<Note, Integer> colIdUser;
	
	
	/**
	 * A variable to store an local file. -- TODO HA DE SER FILE, pero para pruevas és boolean.
	 */
	static boolean openFile=false;
	
	/**
	 * ObservableList to store the note objects.
	 */
	static ObservableList<Note> notes;
	
	/**
	 * Variable to store the current note selection.
	 */
	private Note currentNote;
	
	private int userId;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		notes = FXCollections.observableArrayList();
		
		//Connection to data base.	
		try {
			Connection notesConnection = DriverManager.getConnection("jdbc:mysql://sql8.freesqldatabase.com:3306/sql8622418","sql8622418","ckypqL8v3e");
			//TODO comprovar el id de usuario WHERE idUser=idUserActual.
		
			Statement statement = notesConnection.createStatement();
			ResultSet sql = statement.executeQuery("SELECT * FROM Note");
				
			//Get the notes.
			while(sql.next()) {
				//Get data.
				Integer noteId= sql.getInt("idNote");
				LocalDate date = sql.getDate("noteDate").toLocalDate() ;
				String title = sql.getString("title");
				String body = sql.getString("body");
				Integer userId= sql.getInt("idUser");
				
				//Create note.
				Note n = new Note(noteId,date,title,body,userId);
				
				notes.add(n);
			}
		} catch (SQLException e) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
		    alert.setHeaderText(null);
		    alert.setTitle("Error");
		    alert.setContentText("No s'ha pogut connectar amb la base de dades.");
		    alert.showAndWait();
		}
		
			
		//Set the notes title at notesListView.
		this.notesTableView.setItems(notes);
		
		//Link columns with attributes.
		this.colID.setCellValueFactory(new PropertyValueFactory<Note, Integer>("idNote"));
		this.colDate.setCellValueFactory(new PropertyValueFactory<Note, LocalDate>("noteDate"));
		this.colTitle.setCellValueFactory(new PropertyValueFactory<Note, String>("title"));
		this.colBody.setCellValueFactory(new PropertyValueFactory<Note, String>("body"));
		this.colIdUser.setCellValueFactory(new PropertyValueFactory<Note, Integer>("idUser"));
		
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
	@FXML
	private void deleteNote() {
		if(notesTableView.getSelectionModel().isEmpty()) {
			
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setTitle("Informació");
            alert.setContentText("Selecciona una fila per esborrar.");
            alert.showAndWait();
		}else {
			currentNote = notesTableView.getSelectionModel().getSelectedItem();
			System.out.println(currentNote.getIdNote());
			//Connection to data base.	
			try {
				Connection notesConnection = DriverManager.getConnection("jdbc:mysql://sql8.freesqldatabase.com:3306/sql8622418","sql8622418","ckypqL8v3e");
				//TODO comprovar el id de usuario WHERE idUser=idUserActual.
				
				PreparedStatement ps = notesConnection.prepareStatement("DELETE FROM Table WHERE idNote = ?");
				ps.setInt(1,currentNote.getIdNote());
				
				
				ps.executeUpdate(); 
				notesConnection.close();
				
			} catch (SQLException e) {
				Alert alert = new Alert(Alert.AlertType.ERROR);
			    alert.setHeaderText(null);
			    alert.setTitle("Error");
			    alert.setContentText("Error a la connexió amb la base de dades.");
			    alert.showAndWait();
			}
			
			
		}
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

	public void setLonginUserId(int longinUserId) {
		
	}
}
