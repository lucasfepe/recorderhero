package microservices.book.signup.model;

import lombok.Data;
import microservices.book.signup.user.User;
import microservices.book.signup.util.Clef;
import microservices.book.signup.util.Key;
import microservices.book.signup.util.Mode;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


@Entity
@Data
public class Session implements Comparable<Session>{

	@Id @GeneratedValue
	private int id;
	@Column( nullable = true)
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
	
	
	
	

	@Override
	public int compareTo(Session o) {
		return (int) (this.startTime - o.getStartTime());
	}
	
	
	
	
}
