package persistence;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import compositeKeys.EventCauseComp;
import compositeKeys.MCCMNCComp;

import entity.EventCause;
import entity.FailureClass;
import entity.MCC_MNC;
import entity.UEType;

@SuppressWarnings({"serial", "unchecked"})
public class PersistenceUtil implements Serializable {
	protected static EntityManagerFactory emf = Persistence.createEntityManagerFactory("dt340a");
	
	public static void useLiveDatabase(boolean usingLiveDatabase){
		if(!usingLiveDatabase){
			emf = Persistence.createEntityManagerFactory("dt340atest");
		}
	}
	
	/** 
	 * Persists a List of objects in the database. Only requires one connection/transaction.
	 * 
	 * @param entities	List of objects to be stored in the database
	 */
	public static void persistMany(List<Object> entities){
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		for(Object e : entities){
			em.persist(e);
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
	 * Drops the following tables in the current database:<br />
	 * <ul><li>EventCause</li>
	 *     <li>FailureClass</li>
	 *     <li>MCC_MNC</li>
	 *     <li>UEType</li></ul>
	 */
	public static void dropSecondaryTables(){		
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.createNativeQuery("DROP TABLE IF EXISTS EventCause, FailureClass, MCC_MNC, UEType").executeUpdate();
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
	
	public static EventCause findEventCauseByEventIdAndCauseCode(int eventId, int causeCode){
		EntityManager em = emf.createEntityManager();
		List<EventCause> eventCauses = (List<EventCause>) em.createNamedQuery("EventCause.findByEventIdAndCauseCode")
															.setParameter("id", new EventCauseComp(eventId, causeCode))
															.getResultList();
		em.close();
		
		if(eventCauses.isEmpty()){
			return null;
		} else{
			return eventCauses.get(0);
		}
	}
	
	public static MCC_MNC findMCCMNCByMCCAndMNC(int mcc, int mnc){
		EntityManager em = emf.createEntityManager();
		List<MCC_MNC> mcc_mncs = (List<MCC_MNC>) em.createNamedQuery("MCC_MNC.findByMCCANDMNC")
															.setParameter("id", new MCCMNCComp(mcc, mnc))
															.getResultList();
		em.close();
		
		if(mcc_mncs.isEmpty()){
			return null;
		} else{
			return mcc_mncs.get(0);
		}
	}
	
	/**
	 * Queries
	 */
	
	public static List<EventCause> findEventCauseByIMSI(long imsi){
		EntityManager em = emf.createEntityManager();
		List<EventCause> eventCauses = (List<EventCause>) em.createNamedQuery("ErrorEvent.EventCauseByIMSI")
														.setParameter("IMSI", imsi).getResultList();
		em.close();
		
		return eventCauses;
	}
}