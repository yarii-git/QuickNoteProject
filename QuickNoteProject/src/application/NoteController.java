package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import javafx.stage.Window;
import model.Note;
import java.sql.Connection;

import java.sql.*;
import javax.sql.*;

/**
 * NoteController class.
 * @author Yarií Soto - Nasera Boulehoual
 * @version 1.0, 24 May 2023
 */
public class NoteController implements Initializable{
	//Variables from NoteView
	@FXML
	private Menu deleteNote;
	
	@FXML
	private Button exitButton;
	
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
	 * Method to scroll at note.
	 * @param event
	 */
	public void scrollNoteController(ActionEvent event) {
		//TODO 
	}
	
	/**
	 * Method to save the note online.
	 * @param event - on click.; 
	 */
	@FXML
	public void saveOnlineAction(ActionEvent event) {
		//TODO comprovar que sean correctos los datos.
		//Create a new note object with users entry.
		note = new Note(titleNote.getText(),bodyText.getText(),1);
		
		//Connection to data base.	
		try {
			Connection notesConnection = DriverManager.getConnection("jdbc:mysql://sql8.freesqldatabase.com:3306/sql8620870","sql8620870","Br7vTpCslf");
			
			//Statement newS = notesConnection.createStatement();
			
			//TODO que se ponga la id de usuario.
			String title = note.getTitle();
			Date date = Date.valueOf(note.getNoteDate());
			String body = note.getBody();
			int userId= note.getIdUser();
			
            
			String sql = "insert into Note(noteDate,title,body,idUser) values('" + date + "','" + title + "','" + body + "','" + userId + "')";
           
			// Hacemos la consexion para añadir el nuevo registro a usuario.
			Statement statement = notesConnection.createStatement();
			statement.executeUpdate(sql);
						
			/*PreparedStatement ps = notesConnection.prepareStatement(sql);
            
            ps.setString(1,note.getTitle());
            ps.setDate(2,Date.valueOf(note.getNoteDate()));
            ps.setString(3,note.getBody());
            ps.setInt(4,note.getIdUser());
            
            ps.executeUpdate();*/
            
            //Confirm creation.
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setTitle("Informació");
            alert.setContentText("S'ha creat la nota correctament.");
            alert.showAndWait();
            
		} catch (SQLException e) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
		    alert.setHeaderText(null);
		    alert.setTitle("Error");
		    alert.setContentText("No s'ha pogut connectar amb la base de dades.");
		    alert.showAndWait();
		}
		
		openNotePadWindows();
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
	 */
	@FXML
	public void closeWindows() {
		openNotePadWindows();
	}
	
	/**
	 * Method to change to NotePadWindows.
	 */
	private void openNotePadWindows() {
		try {
			FXMLLoader loader = new FXMLLoader();
			
			loader.setLocation(Main.class.getResource("/view/NotePadView.fxml"));
			
			Parent windows = loader.load();
			
			//NoteController controller = loader.getController();
			
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
}
