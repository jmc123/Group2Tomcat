package persistence;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

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
//		SessionFactory sessionFactory = ((org.hibernate.impl.SessionImpl) em.getDelegate()).getSessionFactory();
//		Session session = sessionFactory.openSession();
//	    session.createSQLQuery("DROP TABLE IF EXISTS EventCause, FailureClass, MCC_MNC, UEType").executeUpdate();
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
//		SessionFactory sessionFactory = ((org.hibernate.impl.SessionImpl) em.getDelegate()).getSessionFactory();
//		Session session = sessionFactory.openSession();
//	    session.createSQLQuery("TRUNCATE TABLE EventCause").executeUpdate();
//	    session.createSQLQuery("TRUNCATE TABLE FailureClass").executeUpdate();
//	    session.createSQLQuery("TRUNCATE TABLE MCC_MNC").executeUpdate();
//	    session.createSQLQuery("TRUNCATE TABLE UEType").executeUpdate();
//	    session.clear();
//	    session.close();
//	    sessionFactory.close();
		
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
//		SessionFactory sessionFactory = ((org.hibernate.impl.SessionImpl) em.getDelegate()).getSessionFactory();
//		Session session = sessionFactory.openSession();
//	    long count = ((BigInteger) session.createSQLQuery("SELECT COUNT(*) FROM ErrorEvent").uniqueResult()).longValue();
//	    session.close();
//	    session.clear();
//	    sessionFactory.close();
		
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
//		SessionFactory sessionFactory = ((org.hibernate.impl.SessionImpl) em.getDelegate()).getSessionFactory();
//		Session session = sessionFactory.openSession();
//	    long count = ((BigInteger) session.createSQLQuery("SELECT COUNT(*) FROM InvalidErrorEvent").uniqueResult()).longValue();
		em.getTransaction().begin();
		long count = ((BigInteger) em.createNativeQuery("SELECT COUNT(*) FROM InvalidErrorEvent").getSingleResult()).longValue();
		em.getTransaction().commit();
	    em.close();
	    
	    return count;
	}
}