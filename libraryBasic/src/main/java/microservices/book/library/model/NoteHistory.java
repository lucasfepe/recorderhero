package microservices.book.library.model;


import lombok.Data;
import microservices.book.library.util.NotesAcc;
import microservices.book.library.util.NotesAccSep;


@Data
public class NoteHistory {

	private int id;
	private Long reactionTime;

	private NotesAccSep note;
	private NotesAcc noteChosen;
	private Session session;

	
	private boolean fake;

	
	
	
	

	
}
