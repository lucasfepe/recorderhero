package microservices.book.signup.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import lombok.Data;
import microservices.book.signup.util.NotesAcc;
import microservices.book.signup.util.NotesAccSep;

@Entity
@Data
public class NoteHistory {

	@Id @GeneratedValue 
	private int id;
	private Long reactionTime;
	@Column( nullable = true)
	
	@Enumerated(EnumType.ORDINAL)
	private NotesAccSep note;
	@Column( nullable = true)
	private NotesAcc noteChosen;

	@ManyToOne
	private Session session;
	
	@Transient
	private boolean fake;

	
	
	
	

	
}
