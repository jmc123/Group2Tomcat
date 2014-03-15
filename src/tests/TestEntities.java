package tests;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;

import entity.CallFailure;
import entity.EventCause;
import entity.FailureClass;
import entity.InvalidCallFailure;
import entity.MCC_MNC;
import entity.UEType;
import entity.User;
import entity.UserType;

public class TestEntities {

	@Test
	public void testUserPK() {
		User user = new User("userTest", DigestUtils.sha1Hex("pass"), 1, "Test", "User", "test@email.com", "01234567");
		assertEquals("Should be 'userTest'", "userTest", (String) user.getPrimaryKey());
	}
	
	@Test
	public void testUserPassword() {
		User user = new User("userPersistTest", DigestUtils.sha1Hex("pass"), 1, "Test", "User", "test@email.com", "01234567");
		assertEquals("Should be SHA1 of 'pass'", DigestUtils.sha1Hex("pass"), user.getUserPassword());
	}
	
	@Test
	public void testUserFirstName() {
		User user = new User("userPersistTest", DigestUtils.sha1Hex("pass"), 1, "Test", "User", "test@email.com", "01234567");
		assertEquals("Should be 'Test'", "Test", user.getFirstName());
	}
	
	@Test
	public void testUserLastName() {
		User user = new User("userPersistTest", DigestUtils.sha1Hex("pass"), 1, "Test", "User", "test@email.com", "01234567");
		assertEquals("Should be 'User'", "User", user.getLastName());
	}
	
	@Test
	public void testUserPhone() {
		User user = new User("userPersistTest", DigestUtils.sha1Hex("pass"), 1, "Test", "User", "test@email.com", "01234567");
		assertEquals("Should be '01234567'", "01234567", user.getPhoneNumber());
	}
	
	
	@Test
	public void testCallFailurePK(){
		CallFailure callFailure = new CallFailure(new Date(), new EventCause(), new FailureClass(), new UEType(), new MCC_MNC(),
				4, 1000, "neVersion", 5000L, 3L, 32L, 321L);
		assertEquals("Should be '0'", 0, callFailure.getPrimaryKey());
	}
	
	@Test
	public void testInvalidCallFailurePK(){
		InvalidCallFailure invalidCallFailure = new InvalidCallFailure(new Date(), 0, "FC", 1, 2, 3,
				4, 1000, "CC", "neVersion", 5000L, 3L, 32L, 321L);
		assertEquals("Should be '0'", 0, invalidCallFailure.getPrimaryKey());
	}

	@Test
	public void testUserTypePK(){
		UserType userType = new UserType("Desc");
		assertEquals("Should be '0'", 0, userType.getPrimaryKey());
	}
}
