package models;

import javax.persistence.Entity;

import play.db.jpa.Model;

@Entity
public class User extends Model {

	public String name;
	public String password;
	public String email;

	public User(String name, String password, String email) {
		this.name = name;
		this.password = password;
		this.email = email;
	}

	public static User connect(String name, String email) {
		return User.find("byNameAndEmail", name, email).first();
	}

}
