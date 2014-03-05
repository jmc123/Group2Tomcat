package entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import persistence.PersistenceUtil;

@Entity
public class User {
@Id
@GeneratedValue(strategy=GenerationType.AUTO)
@Column(name="UserName")
private String userName;
@Column(name="UserPassword")
private String userPassword;
@ManyToOne(optional=false)
private UserType userType;

public User(){

}

public User(String userName, String userPassword, int userTypeId){
super();
this.userName = userName;
this.userPassword = userPassword;


this.userType = PersistenceUtil.findUserTypeByUserTypeId(userTypeId);
}

public String getUserName() {
return userName;
}

public void setUserName(String userName) {
this.userName = userName;
}

public String getUserPassword() {
return userPassword;
}

public void setUserPassword(String userPassword) {
this.userPassword = userPassword;

}

public static void createAdmin() {
	User user =new User("user", "pass", 1);
	PersistenceUtil.persist(user);
}


}