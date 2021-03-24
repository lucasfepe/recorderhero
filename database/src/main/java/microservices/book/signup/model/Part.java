package microservices.book.signup.model;


import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
public class Part {

	@Id 
	private int part;

	public int getPart() {
		return part;
	}

	public void setPart(int part) {
		this.part = part;
	}
	
	
	
	
}
