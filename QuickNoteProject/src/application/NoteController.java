package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import model.Note;
import java.sql.Connection;

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
	 * @param event - on click.; 
	 */
	@FXML
	public void saveOnlineAction(ActionEvent event) {
		//TODO comprovar que sean correctos los datos.
		//Create a new note object with users entry.
		note = new Note(titleNote.getText(),bodyText.getText(),1);
		
		
		/*String title = note.getTitle();
		String body = note.getBody();*/
		
		//Store at data base.
		//Connection to data base.	
		try {
			Connection notesConnection = DriverManager.getConnection("jdbc:mysql://sql8.freesqldatabase.com:3306/sql8620870","sql8620870","Br7vTpCslf");
			
			//Statement newS = notesConnection.createStatement();
			
			//TODO que solo salgan las notas que queremos, con un WHERE idUser = a user logeado.
			/*ResultSet result = newS.executeQuery("INSERT INTO User(name, username, password, email) VALUES (" + title + "," + body +" )"); */
			
			String title = note.getTitle();
			Date date = Date.valueOf(note.getNoteDate());
			String body = note.getBody();
			int userId= note.getIdUser();
            
			/*Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
			alert1.setHeaderText(null);
			alert1.setTitle("Informació");
			alert1.setContentText("S'ha creat la nota correctament."+ date + " "+title+" "+ body+" "+userId);
			alert1.showAndWait();*/
            
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
		
		// Careamos una instancia de la clase DatabaseConnection.
		/*DatabaseConnection connectNow = new DatabaseConnection();
				
		// Obtenermos una conexión a la base de datos.
		Connection connectDB = connectNow.getConnection();
		
		// Añadimos los datos a la base de datos
		String addRegister = "INSERT INTO User(name, username) VALUES (name,username)"; 
		
		try {
			
			// Hacemos la consexion para añadir el nuevo registro a usuario.
			Statement statement = connectDB.createStatement();
			statement.executeUpdate(addRegister);
			correctMessageSignIn.setText("User has been registered successfully!");
			
		} catch (Exception e) {
			e.printStackTrace();
			e.getCause();
		}*/
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
