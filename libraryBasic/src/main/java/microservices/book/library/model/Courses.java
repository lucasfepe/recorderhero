package microservices.book.library.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import microservices.book.library.util.Clef;
import microservices.book.library.util.Mode;
import microservices.book.library.util.Notes;

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
