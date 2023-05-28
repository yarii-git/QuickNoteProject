package model;

import java.time.LocalDate;
import java.time.ZoneId;


/**
 * 
 * @author Yarií Soto - Nasera Boulehoual
 * @version 1.0, 24 May 2023
 */
public class Note {
	/**
	 * Variable to save the note date.
	 */
	private LocalDate noteDate;
	
	/**
	 * Variable to save the note title.
	 */
	private String title;
	
	/**
	 * Variable to save the note body.
	 */
	private String body;
	
	/**
	 * Variable to save the userId
	 */
	private int idUser;
	
	/**
	 * Note constructor.
	 * @param title - The note title.
	 * @param body - The note body.
	 */
	public Note(String title, String body, int idUser) {
		super();
		this.title = title;
		this.body = body;
		this.idUser=idUser;
		noteDate=LocalDate.now();
	}

	/**
	 * Second note constructor.
	 */
	public Note() {
		super();
		noteDate=LocalDate.now();
	}
	
	/**
	 * Note title getter.
	 * @return - the note title.
	 */
	public String getTitle() {
		return title;
	}
	
	/**
	 * Note title setter.
	 * @param title - the note title.
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	
	/**
	 * Note body getter.
	 * @return - the note body.
	 */
	public String getBody() {
		return body;
	}
	
	/**
	 * Note body setter.
	 * @param body - the note body.
	 */
	public void setBody(String body) {
		this.body = body;
	}
	
	/** Note date getter.
	 * @return - the note date.
	 */
	public LocalDate getNoteDate() {
		return noteDate;
	}
	
	/**
	 * User id getter.
	 * @return - user id.
	 */
	public int getIdUser() {
		return idUser;
	}

	/**
	 * Method to delete the note.
	 */
	public void delete() {
		
	}
}
