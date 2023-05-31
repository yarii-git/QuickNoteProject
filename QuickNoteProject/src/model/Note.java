package model;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;


import javafx.beans.property.*;


/**
 * 
 * @author Yarií Soto - Nasera Boulehoual
 * @version 1.0, 24 May 2023
 */
public class Note {
	
	/**
	 * Variable to save the note id.
	 */
	private IntegerProperty idNote;
	
	/**
	 * Variable to save the note date.
	 */
	private ObjectProperty<LocalDate> noteDate;
	
	/**
	 * Variable to save the note title.
	 */
	private StringProperty title;
	
	/**
	 * Variable to save the note body.
	 */
	private StringProperty body;
	
	/**
	 * Variable to save the userId
	 */
	private IntegerProperty idUser;
	
	/**
	 * Note constructor.
	 * @param title - The note title.
	 * @param body - The note body.
	 */
	public Note(String title, String body, Integer idUser) {
		super();
		this.title.set(title);
		this.body.set(body);
		this.idUser.set(idUser);
		noteDate.set(LocalDate.now());
	}

	/**
	 * Second note constructor.
	 */
	/*public Note() {
		super();
		noteDate=LocalDate.now();
	}*/
	
	/**
	 * Note constructor for TableView.
	 */
	public Note(Integer idNote, LocalDate noteDate, String title, String body, Integer idUser) {
		super();
		this.idNote = new SimpleIntegerProperty(idNote);
		this.noteDate = new SimpleObjectProperty<LocalDate>(noteDate);
		this.title = new SimpleStringProperty(title);
		this.body = new SimpleStringProperty(body);	
		this.idUser = new SimpleIntegerProperty(idUser);
		
	}
	
	/**
	 * Note id getter.
	 * @return - note id.
	 */
	public int getIdNote() {
		return idNote.get();
	}
	
	/**
	 * Note title getter.
	 * @return - the note title.
	 */
	public String getTitle() {
		return title.get();
	}
	
	/**
	 * Note title setter.
	 * @param title - the note title.
	 */
	public void setTitle(String title) {
		this.title.set(title);
	}
	
	/**
	 * Note body getter.
	 * @return - the note body.
	 */
	public String getBody() {
		return body.get();
	}
	
	/**
	 * Note body setter.
	 * @param body - the note body.
	 */
	public void setBody(String body) {
		this.body.set(body);
	}
	
	/** Note date getter.
	 * @return - the note date.
	 */
	public LocalDate getNoteDate() {
		return noteDate.get();
	}
	
	/**
	 * User id getter.
	 * @return - user id.
	 */
	public int getIdUser() {
		return idUser.get();
	}
}
