package persistence;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import compositeKeys.EventCauseComp;
import compositeKeys.MCCMNCComp;

import entity.DatasetEntity;
import entity.EventCause;
import entity.FailureClass;
import entity.MCC_MNC;
import entity.UEType;
import entity.User;
import entity.UserType;

@SuppressWarnings({"serial", "unchecked"})
public class PersistenceUtil implements Serializable {
	protected static EntityManagerFactory emf = Persistence.createEntityManagerFactory("dt340a");

	public static void useTestDatabase(){
		emf = Persistence.createEntityManagerFactory("dt340atest");
	}

	public static void persist(Object entity) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(entity);
		em.getTransaction().commit();
		em.close();
	}

	public static int persistMany(List<DatasetEntity> entities){
		EntityManager em = emf.createEntityManager();
		int count = 0;
		
		for(DatasetEntity entity : entities){
			Object primaryKey = em.find(entity.getClass(), entity.getPrimaryKey());
			
			if(primaryKey == null){
				em.getTransaction().begin();
				em.persist(entity);
				em.getTransaction().commit();
				count++;
			}
		}
		em.close();
		return count;
	}
	
	public static void persistManyFailures(List<DatasetEntity> entities){
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();
		for(DatasetEntity entity : entities){
			em.persist(entity);
		}
		em.getTransaction().commit();
		em.close();
	}

	public static void remove(Object entity) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Object mergedEntity = em.merge(entity);
		em.remove(mergedEntity);
		em.getTransaction().commit();
		em.close();
	}

	/**
	 * Returns the total number of ErrorEvent entries in the ErrorEvent table in the database
	 * 
	 * @return	the number of ErrorEvents in the database
	 */
	public static long numberOfErrorEvents(){
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		long count = ((BigInteger) em.createNativeQuery("SELECT COUNT(*) FROM ErrorEvent").getSingleResult()).longValue();
		em.getTransaction().commit();
		em.close();

		return count;
	}

	/**
	 * Returns the total number of InvalidErrorEvent entries in the InvalidErrorEvent table in the database
	 * 
	 * @return	the number of InvalidErrorEvents in the database
	 */
	public static long numberOfInvalidErrorEvents(){
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		long count = ((BigInteger) em.createNativeQuery("SELECT COUNT(*) FROM InvalidErrorEvent").getSingleResult()).longValue();
		em.getTransaction().commit();
		em.close();

		return count;
	}

	public static FailureClass getFailureClass(int failureClassId) {
		EntityManager em = emf.createEntityManager();
		FailureClass failureClass = em.find(FailureClass.class, failureClassId);
		em.close();

		return failureClass;
	}

	public static UEType getUEType(int ueTypeId) {
		EntityManager em = emf.createEntityManager();
		UEType ueType = em.find(UEType.class, ueTypeId);
		em.close();

		return ueType;
	}
	
	public static User getUserByUsername(String userName){
		EntityManager em = emf.createEntityManager();
		User user = em.find(User.class, userName);
		em.close();
	
		return user;
	}
	
	public static UserType getUserTypeById(int userTypeId){
		EntityManager em = emf.createEntityManager();
		UserType userType = em.find(UserType.class, userTypeId);
		em.close();

		return userType;
	}

	public static EventCause getEventCauseByEventIdAndCauseCode(int eventId, int causeCode){
		EntityManager em = emf.createEntityManager();
		List<EventCause> eventCauses = (List<EventCause>) em.createNamedQuery("EventCause.findByEventIdAndCauseCode")
				.setParameter("id", new EventCauseComp(eventId, causeCode))
				.getResultList();
		em.close();
		
		return eventCauses.get(0);
	}

	public static MCC_MNC getMCC_MNCByMCCAndMNC(int mcc, int mnc){
		EntityManager em = emf.createEntityManager();
		List<MCC_MNC> mcc_mncs = (List<MCC_MNC>) em.createNamedQuery("MCC_MNC.findByMCCANDMNC")
				.setParameter("id", new MCCMNCComp(mcc, mnc))
				.getResultList();
		em.close();

		return mcc_mncs.get(0);
	}

	public static List<String> findAllUserNames(){
		EntityManager em = emf.createEntityManager();
		List<String> users = (List<String>) em.createNamedQuery("User.findAllUserNames").getResultList();
		em.close();

		return users;
	}
	
	public static List<User> findAllUsers(){
		EntityManager em = emf.createEntityManager();
		List<User> users = (List<User>) em.createNamedQuery("User.findAllUsers").getResultList();
		em.close();
		
		return users;
	}
	
	public static void registerUser(String userName, String password, int userTypeId, String firstName,
									String lastName, String emailAddress, String phoneNumber){
		EntityManager em = emf.createEntityManager();
		User newUser = new User(userName, password, userTypeId, firstName, lastName, emailAddress, phoneNumber);

		em.getTransaction().begin();
		em.persist(newUser);
		em.getTransaction().commit();
		em.close();
	}

	/**
	 * Queries
	 */

	public static List<EventCause> findEventCauseByIMSI(long imsi){
		EntityManager em = emf.createEntityManager();
		List<EventCause> eventCauses = (List<EventCause>) em.createNamedQuery("ErrorEvent.EventCauseByIMSI")
				.setParameter("imsi", imsi).getResultList();
		em.close();

		return eventCauses;
	}

	public static User findUserByUsername(String userName){
		EntityManager em = emf.createEntityManager();

		User user = em.find(User.class, userName);

		return user;
	}
	
	public static List<Object[]> findNumberOfFailuresAndDuration(Date fromDate, Date toDate){
		EntityManager em = emf.createEntityManager();
		
		List<Object[]> queryDetails = (List<Object[]>) em.createNamedQuery("ErrorEvent.NumOfFailuresAndDuration")
				.setParameter("fromDate", fromDate).setParameter("toDate", toDate).getResultList();
		em.close();
		
		return queryDetails;
	}
	
	public static List<Object[]> findNumberOfFailures(long imsi, Date fromDate, Date toDate){
		EntityManager em = emf.createEntityManager();
		
		List<Object[]> queryDetails = em.createNamedQuery("ErrorEvent.NumOfFailures")
				.setParameter("imsi", imsi).setParameter("fromDate",fromDate).setParameter("toDate",toDate).getResultList();
		
		em.close();
		return queryDetails;
	}
	
	public static String returnDate(String dataParam){
		char[] charOfDate = dataParam.toCharArray();
		
		StringBuffer sqlDate = new StringBuffer();
		for(int i=0; i<10; i++){
			sqlDate.append(charOfDate[i]);
		}
		sqlDate.append(" " + charOfDate[11] + charOfDate[12] + ":" + charOfDate[14] + charOfDate[15] + ":00");
		return sqlDate.toString();
	}
	
	public static List<Object[]> findUniqueEventCauseAndOccurancesByModel(String model){
		EntityManager em = emf.createEntityManager();
		List<Object[]> eventCauses = (List<Object[]>) em.createNamedQuery("ErrorEvent.UniqueEventCauseAndOccurancesByModel")
					.setParameter("model", model).getResultList();
		em.close();

		return eventCauses;
	}
	
	public static List<Long> findCallFailuresBetweenDates(Date fromDate, Date toDate){
		EntityManager em = emf.createEntityManager();
		
		List<Long> queryDetails = (List<Long>) em.createNamedQuery("ErrorEvent.ListIMSIFail")
				.setParameter("fromDate",fromDate).setParameter("toDate",toDate).getResultList();
		em.close();
		
		return queryDetails;
	}
	
	public static List<Integer> findUniqueCauseByIMSI(long imsi){
		EntityManager em = emf.createEntityManager();
		List<Integer> uniqueCauses = (List<Integer>)
				em.createNativeQuery("SELECT DISTINCT Cause_Code FROM ErrorEvent WHERE IMSI= ? ORDER BY Cause_Code;")
				.setParameter(1, imsi).getResultList();
		em.close();

		return uniqueCauses;
	}
	
	public static List<Long> findNumberOfFailuresByModelOverTime(String model, Date fromDate, Date toDate){
		EntityManager em = emf.createEntityManager();

		List<Long> queryDetails = (List<Long>) em.createNamedQuery("ErrorEvent.ErrorsByModelOverTime")
				.setParameter("model", model).setParameter("fromDate",fromDate).setParameter("toDate",toDate).getResultList();
		em.close();
		
		return queryDetails;
	}
}