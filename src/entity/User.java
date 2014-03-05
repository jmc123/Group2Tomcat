package entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import org.apache.commons.codec.digest.DigestUtils;

import persistence.PersistenceUtil;

@NamedQueries( {
	@NamedQuery(name="User.findPasswordByUsername",
				query="SELECT o.userPassword FROM User o WHERE o.userName =:userName"),
})

@Entity
public class User {
	@Id
	@Column(name="UserName")
	private String userName;
	@Column(name="UserPassword")
	private String userPassword;
	@ManyToOne
	@JoinColumn(name="UserType", referencedColumnName = "ID")
	private UserType userType;
	
	public User(){
		
	}
	
	public User(String userName, String userPassword, int userType){
		this.userName = userName;
		this.userPassword = userPassword;
		this.userType = PersistenceUtil.findUserTypeById(userType);
	}
	
	public static void createAdmin(){
		PersistenceUtil.persist(new User("user", DigestUtils.sha1Hex("pass"), 1));
	}
}
