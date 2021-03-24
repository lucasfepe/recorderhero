package microservices.book.multiplication.model;

import lombok.Data;
import microservices.book.multiplication.util.Clef;
import microservices.book.multiplication.util.Key;
import microservices.book.multiplication.util.Mode;

import javax.persistence.*;

@Entity
@Data
public class Session implements Comparable<Session>{

	@Id @GeneratedValue
	private int id;
	@Column( nullable = false)
	private Long startTime;
	@Column( nullable = true)
	private Long endTime;
	@Column( nullable = false)
	private String username;
	@Column( nullable = false)
	private String highNote;
	@Column( nullable = false)
	private String lowNote;
	@Column( nullable = false)
	private Clef clef;

	@Enumerated(EnumType.ORDINAL)
	private Mode mode;
	@Enumerated(EnumType.ORDINAL)
	private Key keym;
	@ManyToOne
	private Challenges challenge;
	
	
	



	@Override
	public int compareTo(Session o) {
		return (int) (this.startTime - o.getStartTime());
	}
	
	
	
	
}
