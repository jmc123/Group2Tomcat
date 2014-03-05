package entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import persistence.PersistenceUtil;

@Entity
public class UserType {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ID")
	private int id;
	@Column(name="Description")
	private String desc;
	
	public UserType(){
		
	}
	
	public UserType(String desc){
		super();
		this.desc = desc;
	}
	
	public static void createTypes(){
		List<Object> userTypes = new ArrayList<>();
		
		userTypes.add(new UserType("System Administrator"));
		userTypes.add(new UserType("Network Management Engineer"));
		userTypes.add(new UserType("Support Engineer"));
		userTypes.add(new UserType("Customer Service Rep"));
		
		PersistenceUtil.persistMany(userTypes);
	}
}