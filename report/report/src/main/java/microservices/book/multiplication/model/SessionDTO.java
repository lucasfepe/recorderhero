package microservices.book.multiplication.model;

import lombok.Data;
import lombok.ToString;
import microservices.book.multiplication.util.Clef;
import microservices.book.multiplication.util.Key;
import microservices.book.multiplication.util.Mode;
import microservices.book.multiplication.util.Notes;


@Data
@ToString
public class SessionDTO {




	private Notes highNote;
	private Notes lowNote;
	private Clef clef;

	private Mode mode;
	private Key keym;
	private Challenges challenge;

	
	
	
	
	

	
	
	
}
