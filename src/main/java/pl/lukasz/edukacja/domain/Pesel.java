package pl.lukasz.edukacja.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import pl.lukasz.edukacja.domain.User;

@Entity
public class Pesel {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	int id;
	
	long PESEL;
	
	@OneToOne(mappedBy="pesel")
	private User user;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public long getPESEL() {
		return PESEL;
	}

	public void setPESEL(long pESEL) {
		PESEL = pESEL;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
