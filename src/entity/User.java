package entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import persistence.PersistenceUtil;

@NamedQueries( {
	@NamedQuery(name="User.findPasswordAndUserTypeByUsername",
				query="SELECT o.userPassword, o.userType FROM User o WHERE o.userName =:userName"),
	@NamedQuery(name="User.findAllUserNames",
				query="SELECT o.userName FROM User o"),
	@NamedQuery(name="User.findAllUsers",
				query="SELECT o FROM User o"),
})

@Entity
public class User implements DatasetEntity{
	@Id
	@Column(name="UserName")
	private String userName;
	@Column(name="UserPassword")
	private String userPassword;
	@ManyToOne
	@JoinColumn(name="UserType", referencedColumnName = "ID")
	private UserType userType;
	@Column(name="First_Name")
	private String firstName;
	@Column(name="Last_Name")
	private String lastName;
	@Column(name="Email")
	private String emailAddress;
	@Column(name="Phone_Number")
	private String phoneNumber;
	
	public User(){
		
	}
	
	public User(String userName, String userPassword, int userType, String firstName, String lastName,
				String emailAddress, String phoneNumber){
		this.userName = userName;
		this.userPassword = userPassword;
		this.userType = PersistenceUtil.getUserTypeById(userType);
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailAddress = emailAddress;
		this.phoneNumber = phoneNumber;
	}
	
	public UserType getUserType(){
		return this.userType;
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

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}

	@Override
	public Object getPrimaryKey() {
		return userName;
	}
}
