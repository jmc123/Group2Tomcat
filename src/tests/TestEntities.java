package tests;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;

import entity.ErrorEvent;
import entity.EventCause;
import entity.FailureClass;
import entity.InvalidErrorEvent;
import entity.MCC_MNC;
import entity.UEType;
import entity.User;
import entity.UserType;

public class TestEntities {

	@Test
	public void testUserPK() {
		User user = new User("userTest", DigestUtils.sha1Hex("pass"), 1, "Test", "User", "test@email.com","01234567");
		assertEquals("Should be 'userTest'", "userTest", (String) user.getPrimaryKey());
	}
	
	@Test
	public void testUserPassword() {
		User user = new User("userPersistTest", DigestUtils.sha1Hex("pass"), 1, "Test", "User", "test@email.com","01234567");
		assertEquals("Should be SHA1 of 'pass'", DigestUtils.sha1Hex("pass"), user.getUserPassword());
	}
	
	@Test
	public void testErrorEventPK(){
		ErrorEvent errorEvent = new ErrorEvent(new Date(), new EventCause(), new FailureClass(), new UEType(), new MCC_MNC(),
				4, 1000, "neVersion", 5000L, 3L, 32L, 321L);
		assertEquals("Should be '0'", 0, errorEvent.getPrimaryKey());
	}
	
	@Test
	public void testInvalidErrorEventPK(){
		InvalidErrorEvent invalidErrorEvent = new InvalidErrorEvent(new Date(), 0, "FC", 1, 2, 3,
				4, 1000, "CC", "neVersion", 5000L, 3L, 32L, 321L);
		assertEquals("Should be '0'", 0, invalidErrorEvent.getPrimaryKey());
	}

	@Test
	public void testUserTypePK(){
		UserType userType = new UserType("Desc");
		assertEquals("Should be '0'", 0, userType.getPrimaryKey());
	}
}
