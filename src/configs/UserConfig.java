package configs;

import java.util.List;
import persistence.PersistenceUtil;
import entity.User;


public class UserConfig {
	
	public static void main(String[] args){
		UserConfig config = new UserConfig();
	}

	public UserConfig(){
//		createUser("user", "pass");
	}


	public void viewUser(){
		List<User> users = PersistenceUtil.findAllUsers();
		for(User s:users){
			System.out.println("User "+s.getUserName()+ " exists.");
		}
	}
	
//	public void createUser(String name, String password, int userTypeId){
//		User user = new User(name, password, userTypeId);
//		PersistenceUtil.persist(user);
//		System.out.println("User registered");
//	}
			

}
