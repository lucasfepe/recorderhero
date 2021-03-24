package microservices.book.multiplication.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import microservices.book.multiplication.util.Clef;
import microservices.book.multiplication.util.Mode;
import microservices.book.multiplication.util.Notes;

import javax.persistence.*;

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
