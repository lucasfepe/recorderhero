package microservices.book.signup.model;


import 	javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import microservices.book.signup.util.Clef;
import microservices.book.signup.util.Mode;
import microservices.book.signup.util.Notes;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Courses implements Comparable<Courses>{

	@Id 
	@Column(length = 50)
	private String code;
	@Enumerated(EnumType.ORDINAL)
	private Mode instrument;
	@Enumerated(EnumType.ORDINAL)
	private Clef clef;
	private int octaveShift;
	
	@Enumerated(EnumType.ORDINAL)
	private Notes lowNote;
	@Enumerated(EnumType.ORDINAL)
	private Notes highNote;
	@ManyToOne 
	private Part part;

	@Override
	public int compareTo(Courses otherCourses) {
		
		return code.compareToIgnoreCase(otherCourses.code);
		
			
		
		
	}
	
	
	
	
	
}
