package entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import persistence.PersistenceUtil;

	
	@Entity
	public class UserType {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ID")
	private int id;
	@Column(name="UserType")
	private String userType;


	
	public UserType(){

}

public UserType(String userType){
super();
this.userType = userType;
}

public static void createTypes() {
	

	
	UserType systemAdmin = new UserType("System Administrator");
	UserType  networkManagementEngineer= new UserType("Network Management Engineer");
	UserType supportEngineer = new UserType("Support Engineer");
	UserType customerServiceRep = new UserType("Customer Service Rep");
	
	PersistenceUtil.persist(systemAdmin);
	PersistenceUtil.persist(networkManagementEngineer);
	PersistenceUtil.persist(supportEngineer);
	PersistenceUtil.persist(customerServiceRep);
	
}
	

}
	

