package application;

import java.sql.*;
import java.time.LocalDate;

//import javax.sql.*;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.ScrollBar;
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
	
	//ObservableList to store the note objects.
	static ObservableList<Note> notes;
	
	//Variable to store the current note selection.
	private Note currentNote;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		//Get notes lists from data base.
		refreshTableConnection();
	}
	
	/**
	 * Method to get the notes list from the database
	 */
	public void refreshTableConnection() {
		//Connection to data base.	
		try {
			notes = FXCollections.observableArrayList();
			
			Connection notesConnection = DriverManager.getConnection("jdbc:mysql://sql8.freesqldatabase.com:3306/sql8622418","sql8622418","ckypqL8v3e");
			Statement statement = notesConnection.createStatement();
			
			
			Integer user = (int) ViewsLoginController.loginUserId;
			ResultSet sql = statement.executeQuery("SELECT * FROM Note WHERE idUser="+user);
			
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
			
			//Set the notes title at notesListView.
			this.notesTableView.setItems(notes);
			
			//Link columns with attributes.
			this.colID.setCellValueFactory(new PropertyValueFactory<Note, Integer>("idNote"));
			this.colDate.setCellValueFactory(new PropertyValueFactory<Note, LocalDate>("noteDate"));
			this.colTitle.setCellValueFactory(new PropertyValueFactory<Note, String>("title"));
			this.colBody.setCellValueFactory(new PropertyValueFactory<Note, String>("body"));
			this.colIdUser.setCellValueFactory(new PropertyValueFactory<Note, Integer>("idUser"));
			
			//Close resources
			statement.close();
			sql.close();
		} catch (SQLException e) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
		    alert.setHeaderText(null);
		    alert.setTitle("Error");
		    alert.setContentText("Failed to connect to database.");
		    alert.showAndWait();
		}
	}
	
	/**
	 * Method to open an external note.
	 * @param event
	 */
	/*private void openExtNote() {
		
	}*/
	
	/**
	 * Method to change to Note windows.
	 * @param event
	 */
	@FXML
	private void newNoteAction(ActionEvent event) {
		//NoteController.initializeOpenFile(openFile);
		//NoteController.initializeUserId(userId);
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
		    alert.setContentText("View not found.");
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
            alert.setTitle("Information");
            alert.setContentText("Select row to delete.");
            alert.showAndWait();
		}else {
			currentNote = notesTableView.getSelectionModel().getSelectedItem();
			
			
			try {
				Connection notesConnection = DriverManager.getConnection("jdbc:mysql://sql8.freesqldatabase.com:3306/sql8622418","sql8622418","ckypqL8v3e");
				
				//Create a PreparedStatement.
				PreparedStatement ps = notesConnection.prepareStatement("DELETE FROM Note WHERE idNote = ?");
				ps.setInt(1,currentNote.getIdNote());
				
				//Execute the statement.
				ps.executeUpdate(); 
				
				//Close resources.
				ps.close();
				
			} catch (SQLException e) {
				Alert alert = new Alert(Alert.AlertType.ERROR);
			    alert.setHeaderText(null);
			    alert.setTitle("Error");
			    alert.setContentText("Failed to connect to database.");
			    alert.showAndWait();
			}
			
			//Get notes lists from data base.
			refreshTableConnection();
		}
	}
	
	/**
	 * Method to edit a note (stored at data base).
	 */
	@FXML
	private void editNoteAction() {
		
		if(notesTableView.getSelectionModel().isEmpty()) {
			
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setTitle("Information");
            alert.setContentText("Select row to edit.");
            alert.showAndWait();
		}else {
			currentNote = notesTableView.getSelectionModel().getSelectedItem();
			
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
			    alert.setContentText("View not found.");
			    alert.showAndWait();
			}
		}
	}
	
	/**
	 * Method to close the NotePad.
	 * @param event - on click.
	 */
	@FXML
	private void exitNotePad() {
	     Stage stage = (Stage) this.exitButton.getScene().getWindow();
	     stage.close();
	}
	
	/**
	 * Method to scroll at notes windows.
	 */
	/*public void scrollNotes() {
		//TODO to implement.
	}*/
}
