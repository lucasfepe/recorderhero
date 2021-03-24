package microservices.book.multiplication.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import microservices.book.multiplication.util.Clef;
import microservices.book.multiplication.util.Key;
import microservices.book.multiplication.util.Mode;

@Entity
@AllArgsConstructor
@Data
public class Session implements Comparable<Session>{

	@Id @GeneratedValue
	private int id;
	@Column( nullable = false)
	private Long startTime;
	@Column( nullable = true)
	private Long endTime;
	@ManyToOne
	private User user;
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


	public Session(int id) {
		this.id = id;
	}

	@Override
	public int compareTo(Session o) {
		return (int) (this.startTime - o.getStartTime());
	}
	
	
	
	
}
