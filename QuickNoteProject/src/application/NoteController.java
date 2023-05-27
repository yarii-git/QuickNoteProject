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
	
	private String note;

    private ObservableList<String> notes;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public void initAttributtes(ObservableList<String> notes) {
        this.notes = notes;
    }

	
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
	 * @param event - 
	 */
	public void saveOnline(ActionEvent event) {
		//TODO 
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
	
	public void saveLocal() {
		// Carga la image del logo atravez de la ruta donde se encuantra.
		
		/*File logoFile = new File("/home/nasera/git/Repository_YN/Project_QuickNote/Image/Captura desde 2023-05-26 06-21-34.png");
		Image logoImage = new Image(logoFile.toURI().toString());
		imageLogo.setImage(logoImage);*/
		
		// Carga la image del icono atravez de la ruta donde se encuantra.
		
		/*File iconFile = new File("/home/nasera/git/Repository_YN/Project_QuickNote/Image/Captura desde 2023-05-26 06-35-02.png");
		Image iconImage = new Image(iconFile.toURI().toString());
		ImageIcon.setImage(iconImage);*/
		
	}
	
	public String getNote() {
        return note;
    }

	
}
