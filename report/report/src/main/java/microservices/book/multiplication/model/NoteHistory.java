package microservices.book.multiplication.model;


import lombok.Data;
import microservices.book.multiplication.util.NotesAcc;
import microservices.book.multiplication.util.NotesAccSep;


@Data
public class NoteHistory {

	private int id;
	private Long reactionTime;

	private NotesAccSep note;
	private NotesAcc noteChosen;
	private Session session;

	
	private boolean fake;

	
	
	
	

	
}
