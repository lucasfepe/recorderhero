package microservices.book.multiplication.model;


import lombok.Data;
import microservices.book.multiplication.util.NotesAcc;
import microservices.book.multiplication.util.NotesAccSep;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;


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
