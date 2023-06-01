package application;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

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
import model.Note;
import java.sql.Connection;

import java.sql.*;
//import javax.sql.*;
import javax.swing.*;


/**
 * NoteController class.
 * @author Yarií Soto - Nasera Boulehoual
 * @version 1.0, 24 May 2023
 */
public class NoteController implements Initializable{
	//Variables from NoteView
	@FXML private Menu deleteNote;
	
	@FXML private Button exitButton;
	
	@FXML private ScrollPane scrollNote;
	
	@FXML private TextArea titleNote;
	
	@FXML private TextArea bodyText;
	
	@FXML private Button saveOnlineB;
	
	@FXML private Button saveLocalB;
	
	//A variable to store the current note.
	private Note note;
	
	//Variable to know if the file has been opened.
	private static boolean openFile;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    	
    }
	
	/**
	 * Method to save the note online.
	 */
	@FXML
	public void saveOnlineAction() {
		//Create a new note object with users entry.
		if(createNote()){
			//Connection to data base.	
			try {
				Connection notesConnection = DriverManager.getConnection("jdbc:mysql://sql8.freesqldatabase.com:3306/sql8622418","sql8622418","ckypqL8v3e");
				
				String title = note.getTitle();
				Date date = Date.valueOf(note.getNoteDate());
				String body = note.getBody();
				Integer userId= note.getIdUser();
				
	            
				String sql = "insert into Note(noteDate,title,body,idUser) values('" + date + "','" + title + "','" + body + "','" + userId + "')";
	           
				Statement statement = notesConnection.createStatement();
				statement.executeUpdate(sql);
				
				//Close resources.
				statement.close();
				
	            //Confirm creation.
	            Alert alert = new Alert(Alert.AlertType.INFORMATION);
	            alert.setHeaderText(null);
	            alert.setTitle("Information");
	            alert.setContentText("The note was created successfully.");
	            alert.showAndWait();
	            
			} catch (SQLException e) {
				Alert alert = new Alert(Alert.AlertType.ERROR);
			    alert.setHeaderText(null);
			    alert.setTitle("Error");
			    alert.setContentText("Failed to connect to database.");
			    alert.showAndWait();
			}
			
			openNotePadWindows();
		}else {
			Alert alert = new Alert(Alert.AlertType.ERROR);
		    alert.setHeaderText(null);
		    alert.setTitle("Error");
		    alert.setContentText("The title is too long, maximum 40 characters.");
		    alert.showAndWait();
		}
		
	}
	
	/**
	 * Method to save the note at local drive.
	 * @param event
	 */
	@FXML
	private void saveLocal() {
		
  		if(createNote()){
  			//Create a note with user entry.
  	        createNote();
  	        
  			//Save as if the file exists on the disk.
  			if(!openFile) {
  				//Create a file selector
  				JFileChooser saveFile=new JFileChooser();
  				
  				//Save the user choose.
  				int opc = saveFile.showSaveDialog(null);
  				
  				//Check user option
  				if(opc == JFileChooser.APPROVE_OPTION) {
  					
  					File userFile = saveFile.getSelectedFile();
  					
  					try {
  						//Create the FileWriter.
  						FileWriter fw = new FileWriter(userFile.getPath());
  						
  						//Get the text user note.
  						String text = note.getTitle()+"\n"+note.getBody();
  						
  						//A loop to white at the file.
  						for(int i=0; i<text.length();i++) {
  							fw.write(text.charAt(i));
  						}
  						
  						//Close the FileWrite.
  						fw.close();
  						
  					} catch (IOException e) {
  						Alert alertError = new Alert(Alert.AlertType.ERROR);
  						alertError.setHeaderText(null);
  						alertError.setTitle("Error");
  						alertError.setContentText("File not found.");
  						alertError.showAndWait();
  					}
  					openNotePadWindows();
  				}
  			}
  		}else {
  			Alert alert = new Alert(Alert.AlertType.ERROR);
  		    alert.setHeaderText(null);
  		    alert.setTitle("Error");
  		    alert.setContentText("The title is too long, maximum 40 characters.");
  		    alert.showAndWait();
  		}
	}
	
	/**
	 * Method to create a note.
	 */
	private boolean createNote() {
		boolean titleCorrect=false;
		
		//Maximum title length.
		final int MAX_LONG=40;
		
		if(titleNote.getText().length()<=MAX_LONG) {
			
			//Create a new note object with users entry
			note = new Note(titleNote.getText(),bodyText.getText(),ViewsLoginController.loginUserId);
	      	return titleCorrect=true;
		}else {
			return titleCorrect=false;
		}
	}
	
	/**
	 * Method to change to NotePadWindows.
	 */
	private void openNotePadWindows() {
		try {
			FXMLLoader loader = new FXMLLoader();
			
			loader.setLocation(Main.class.getResource("/view/NotePadView.fxml"));
			
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
	 * Method to exit the note.
	 */
	@FXML
	public void closeWindows() {
		openNotePadWindows();
	}
	
	/**		
	 * Method to scroll at note.
	 * @param event
	 */
	/*public void scrollNoteController() {
		//TODO 
	}*/
}
