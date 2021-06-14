package microservices.book.multiplication.model;

import java.util.List;


import lombok.AllArgsConstructor;
import lombok.Data;
import microservices.book.multiplication.util.Clef;
import microservices.book.multiplication.util.Key;
import microservices.book.multiplication.util.Mode;

@AllArgsConstructor
@Data
public class Session implements Comparable<Session>{

	private int id;
	private Long startTime;
	private Long endTime;
	private User user;
	private String highNote;
	private String lowNote;
	private Clef clef;

	private Mode mode;
	private Key keym;
	private Challenges challenge;


	public Session(int id) {
		this.id = id;
	}

	@Override
	public int compareTo(Session o) {
		return (int) (this.startTime - o.getStartTime());
	}
	
	
	
	
}
