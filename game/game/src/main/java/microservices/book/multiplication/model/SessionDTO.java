package microservices.book.multiplication.model;

import lombok.Data;
import lombok.ToString;
import microservices.book.multiplication.util.Clef;
import microservices.book.multiplication.util.Key;
import microservices.book.multiplication.util.Mode;
import microservices.book.multiplication.util.Notes;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


@Data
@ToString
public class SessionDTO {




	@Enumerated(EnumType.ORDINAL)
	private Notes highNote;
	@Enumerated(EnumType.ORDINAL)
	private Notes lowNote;
	@Column( nullable = false)
	private Clef clef;

	@Enumerated(EnumType.ORDINAL)
	private Mode mode;
	@Enumerated(EnumType.ORDINAL)
	private Key keym;
	@ManyToOne
	private Challenges challenge;

	
	
	
	
	

	
	
	
}
