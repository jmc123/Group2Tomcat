package persistence;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import entity.EventCause;
import entity.FailureClass;
import entity.MCC_MNC;
import entity.UEType;

@SuppressWarnings("serial")
public class PersistenceUtil implements Serializable {
	protected static EntityManagerFactory emf;
	private static boolean liveDatabase = false;
	
	public static void setDatabaseState(boolean usingLiveDatabase){
		liveDatabase = usingLiveDatabase;
	}
	
	private static void checkDatabaseState() {
		if(liveDatabase)
			emf = Persistence.createEntityManagerFactory("dt340a");
		else
			emf = Persistence.createEntityManagerFactory("dt340atest");
	}
	
	/** 
	 * Persists a List of objects in the database. Only requires one connection/transaction.
	 * 
	 * @param entities	List of objects to be stored in the database
	 */
	public static void persistMany(List<Object> entities){
		checkDatabaseState();
	
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		for(Object e : entities)
			em.persist(e);
		em.getTransaction().commit();
		em.close();
	}

	public static void remove(Object entity) {
		checkDatabaseState();
		
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Object mergedEntity = em.merge(entity);
		em.remove(mergedEntity);
		em.getTransaction().commit();
		em.close();
	}
	
	public static Object merge(Object entity) {
		checkDatabaseState();
		
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		entity = em.merge(entity);
		em.getTransaction().commit();		
		em.close();
		return entity;
	}

	public static EntityManager createEM() {
		checkDatabaseState();
		
		return emf.createEntityManager();
	}
	
	/**
	 * Drops the following tables in the current database:<br />
	 * <ul><li>EventCause</li>
	 *     <li>FailureClass</li>
	 *     <li>MCC_MNC</li>
	 *     <li>UEType</li></ul>
	 */
	public static void dropSecondaryTables(){
		checkDatabaseState();
		
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.createNativeQuery("DROP TABLE IF EXISTS EventCause, FailureClass, MCC_MNC, UEType").executeUpdate();
		em.getTransaction().commit();
	    em.close();
	}
	
	/**
	 * Truncates the following tables in the current database:<br />
	 * <ul><li>EventCause</li>
	 *     <li>FailureClass</li>
	 *     <li>MCC_MNC</li>
	 *     <li>UEType</li></ul>
	 */
	public static void truncateSecondaryTables(){
		checkDatabaseState();
		
		EntityManager em = emf.createEntityManager();		
		em.getTransaction().begin();
		em.createNativeQuery("TRUNCATE TABLE EventCause").executeUpdate();
	    em.createNativeQuery("TRUNCATE TABLE FailureClass").executeUpdate();
	    em.createNativeQuery("TRUNCATE TABLE MCC_MNC").executeUpdate();
	    em.createNativeQuery("TRUNCATE TABLE UEType").executeUpdate();
		em.getTransaction().commit();
	    em.close();
	}
	
	/**
	 * Returns the total number of ErrorEvent entries in the ErrorEvent table in the database
	 * 
	 * @return	the number of ErrorEvents in the database
	 */
	public static long numberOfErrorEvents(){
		checkDatabaseState();
		
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
		checkDatabaseState();
		
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		long count = ((BigInteger) em.createNativeQuery("SELECT COUNT(*) FROM InvalidErrorEvent").getSingleResult()).longValue();
		em.getTransaction().commit();
	    em.close();
	    
	    return count;
	}

	public static EventCause getEventCause(int eventIdOrCauseCode) {
		checkDatabaseState();
		EntityManager em = emf.createEntityManager();
		EventCause eventCause = em.find(EventCause.class, eventIdOrCauseCode);
		em.close();
		
		return eventCause;
	}

	public static FailureClass getFailureClass(int failureClassId) {
		checkDatabaseState();
		EntityManager em = emf.createEntityManager();
		FailureClass failureClass = em.find(FailureClass.class, failureClassId);
		em.close();
		
		return failureClass;
	}

	public static UEType getUEType(int ueTypeId) {
		checkDatabaseState();
		EntityManager em = emf.createEntityManager();
		UEType ueType = em.find(UEType.class, ueTypeId);
		em.close();
		
		return ueType;
	}

	public static MCC_MNC getMCC_MNC(int marketOrOperator) {
		checkDatabaseState();
		EntityManager em = emf.createEntityManager();
		MCC_MNC mcc_mnc = em.find(MCC_MNC.class, marketOrOperator);
		em.close();
		
		return mcc_mnc;
	}
	
	
}