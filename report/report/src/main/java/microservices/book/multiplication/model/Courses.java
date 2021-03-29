package microservices.book.multiplication.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import microservices.book.multiplication.util.Clef;
import microservices.book.multiplication.util.Mode;
import microservices.book.multiplication.util.Notes;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Courses implements Comparable<Courses>{

	private String code;
	private Mode instrument;
	private Clef clef;
	private int octaveShift;
	
	private Notes lowNote;
	private Notes highNote;
	private Part part;

	@Override
	public int compareTo(Courses otherCourses) {
		
		return code.compareToIgnoreCase(otherCourses.code);
		
			
		
		
	}
	
	
	
	
	
}
