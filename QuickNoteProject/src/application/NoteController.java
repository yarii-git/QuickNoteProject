package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import model.Note;

import java.sql.*;
import javax.sql.*;

public class NoteController implements Initializable{
	//Variables from NoteView
	@FXML
	private Menu deleteNote;
	
	@FXML
	private Menu exitNote;
	
	@FXML
	private ScrollPane scrollNote;
	
	@FXML
	private TextArea titleNote;
	
	@FXML
	private TextArea bodyText;
	
	@FXML
	private Button saveOnlineB;
	
	@FXML
	private Button saveLocalB;
	
	private Note note;

	/**
	 * 
	 */
    //private ObservableList<Note> notes;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }
    
    /**
     * Method to initialize the notes ObservableList
     * @param notes
     */
   /* public void initAttributtes(ObservableList<Note> notes) {
        this.notes = notes;
    }*/

	
	/**
	 * Method to delete a note.
	 * @param event
	 */
	public void deleteNote(ActionEvent event) {
		//TODO 
	}
	
	/**
	 * Method to set the title note.
	 * @param event
	 */
	public void setTitleNoteController(ActionEvent event) {
		//TODO 
	}
	
	/**
	 * Method to set the body note.
	 * @param event - 
	 */
	public void setBodyNoteController(ActionEvent event) {
		//TODO 
	}
	
	/**		
	 * Method to scroll at note.
	 * @param event
	 */
	public void scrollNoteController(ActionEvent event) {
		//TODO 
	}
	
	/**
	 * Method to save the note online.
	 * @param event - on click.
	 */
	public void saveOnline(ActionEvent event) {
		//TODO comprovar que sean correctos los datos.
		//Create a new note object with users entry.
		note = new Note(titleNote.getText(),bodyText.getText());
		
		//Store at data base.
		//Connection to data base.	
		try {
			Connection notesConnection = DriverManager.getConnection("jdbc:mysql://sql8.freesqldatabase.com:3306/sql8620870","sql8620870","Br7vTpCslf");
			Statement newS = notesConnection.createStatement();
			
			//TODO que solo salgan las notas que queremos, con un WHERE idUser = a user logeado.
			ResultSet result = newS.executeQuery("INSERT INTO Note (noteDate,title,body)");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		//Return to NotePad view.
		   
	}
	
	/**
	 * Method to save the note at local drive.
	 * @param event
	 */
	public void saveLocal(ActionEvent event) {
		//TODO 
	}
	
	
	/**
	 * Method to exit the note.
	 * @param event
	 */
	@FXML
	public void closeWindows() {
		/*Stage stage = (Stage) exitNote.getScene().getWindow();
		stage.close();*/
	}
	
	/**
	 * Method to get the new note.
	 * @return - a note.
	 */
	public Note getNote() {
        return note;
    }

	
}
