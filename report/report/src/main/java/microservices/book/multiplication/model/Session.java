package microservices.book.multiplication.model;

import lombok.Data;
import microservices.book.multiplication.util.Clef;
import microservices.book.multiplication.util.Key;
import microservices.book.multiplication.util.Mode;


@Data
public class Session implements Comparable<Session>{

	private int id;
	private Long startTime;
	private Long endTime;
	private String username;
	private String highNote;
	private String lowNote;
	private Clef clef;

	private Mode mode;
	private Key keym;
	private Challenges challenge;
	
	
	



	@Override
	public int compareTo(Session o) {
		return (int) (this.startTime - o.getStartTime());
	}
	
	
	
	
}
