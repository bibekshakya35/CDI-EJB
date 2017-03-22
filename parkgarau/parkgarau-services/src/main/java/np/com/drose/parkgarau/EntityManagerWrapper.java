package np.com.drose.parkgarau;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

/**
 *
 * @author SurajChhetry
 */
@Stateless
@LocalBean
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class EntityManagerWrapper {

    @PersistenceContext(name = "PARK_GARAU_PU")
    EntityManager em;
    private static final Logger LOG = Logger.getLogger(EntityManagerWrapper.class.getName());

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public <T> T persist(T t) throws SecurityException {
        this.em.persist(t);
        this.em.flush();
        this.em.refresh(t);
        return t;
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public <T> void addAll(List<T> t) throws SecurityException {
        t.stream().map((t1) -> {
            this.em.persist(t1);
            return t1;
        }).map((t1) -> {
            this.em.flush();
            return t1;
        }).forEach((t1) -> {
            this.em.refresh(t1);
        });

    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public <T> void deleteAll(List<T> t) throws SecurityException {
        for (T t1 : t) {
            em.remove(t1);
            em.flush();
            em.clear();
        }

    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public <T> void editAll(List<T> t) throws SecurityException {
        for (T t1 : t) {
            this.merge(t1);
            this.em.flush();
        }
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public <T> T merge(T t) {
        T upT = this.em.merge(t);
        this.em.flush();
        return (T) upT;
    }

    public <T> T find(Class<T> type, Object id) {
        if (id == null) {
            return null;
        }
        return (T) this.em.find(type, id);
    }

    @SuppressWarnings("unchecked")
    public <T> T find(String queryName, Map<String, Object> parameters) {
        List<T> list = findAll(queryName, parameters);
        if (list.size() > 0) {
            return list.get(0);
        }
        return null;

    }

    @SuppressWarnings("rawtypes")
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public List findAll(String queryName) {
        return this.em.createNamedQuery(queryName).getResultList();
    }

    @SuppressWarnings("rawtypes")
    public List findAll(String namedQueryName, Map<String, Object> parameters) {
        return findWithRowLimit(namedQueryName, parameters, 0);
    }

    @SuppressWarnings("rawtypes")
    public List findWithRowLimit(String queryName, int resultLimit) {
        return this.em.createNamedQuery(queryName).setMaxResults(resultLimit).getResultList();
    }

    @SuppressWarnings("rawtypes")
    public List findWithRowLimit(String namedQueryName, Map<String, Object> parameters, int resultLimit) {
        Set<Entry<String, Object>> rawParameters = parameters.entrySet();
        Query query = this.em.createNamedQuery(namedQueryName);
        if (resultLimit > 0) {
            query.setMaxResults(resultLimit);
        }
        rawParameters.stream().forEach((entry) -> {
            query.setParameter(entry.getKey(), entry.getValue());
        });
        return query.getResultList();
    }

    @SuppressWarnings("rawtypes")
    public List findWithPaging(String namedQueryName, Map<String, Object> parameters, int first, int pageSize) {
        Set<Entry<String, Object>> rawParameters = parameters.entrySet();
        Query query = this.em.createNamedQuery(namedQueryName);
        query.setFirstResult(first);
        query.setMaxResults(pageSize);
        rawParameters.stream().forEach((entry) -> {
            query.setParameter(entry.getKey(), entry.getValue());
        });
        return query.getResultList();
    }

    //executimedymanicwithpaging
    @SuppressWarnings("rawtypes")
    public List executeDynamicQuery(String query) {
        LOG.log(Level.INFO, "qquery named ....... {0}", query);
        Query qry = this.em.createQuery(query);
        LOG.log(Level.INFO, "query size {0}", qry.getResultList().size());
        return qry.getResultList();
    }

    @SuppressWarnings("rawtypes")
    public List executeDynamicQuery(String query, int first, int pageSize) {
        Query qry = this.em.createQuery(query);
        qry.setFirstResult(first);
        qry.setMaxResults(pageSize);
        return qry.getResultList();
    }
    
    @SuppressWarnings("rawtypes")
    public List executeDynamicNamedQuery(String query) {
        LOG.log(Level.INFO, "qquery named ....... {0}", query);
        Query qry = this.em.createNamedQuery(query);
        LOG.log(Level.INFO, "query size {0}", qry.getResultList().size());
        return qry.getResultList();
    }

    public Long countSize(Object t) {
        try {
            CriteriaBuilder qb = this.em.getCriteriaBuilder();
            CriteriaQuery<Long> cq = qb.createQuery(Long.class);
            cq.select(qb.count(cq.from(t.getClass())));
            Query q = this.em.createQuery(cq);
            Object obj = q.getSingleResult();
            if (obj == null) {
                return ((long) 0);
            }
            return ((Long) obj);
        } catch (Exception exception) {
            return ((long) 0);
        }
    }

    @SuppressWarnings("unchecked")
    public <T> T getSingleResult(String queryName, Map<String, Object> parameters) {
        try {
            Set<Entry<String, Object>> rawParameters = parameters.entrySet();
            Query query = this.em.createNamedQuery(queryName);
            rawParameters.stream().forEach((entry) -> {
                query.setParameter(entry.getKey(), entry.getValue());
            });
            return (T) query.getSingleResult();
        } catch (javax.persistence.NoResultException ex) {
            return null;
        }
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public <T> void deleteByOtherId(String queryName, Map<String, Object> parameters) {
        try {
            Set<Entry<String, Object>> rawParameters = parameters.entrySet();
            Query query = this.em.createNamedQuery(queryName);
            rawParameters.stream().forEach((entry) -> {
                query.setParameter(entry.getKey(), entry.getValue());
            });
            query.executeUpdate();
            em.flush();
            em.clear();
        } catch (NoResultException ex) {

        }
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public <T> void remove(T t) {
        em.remove(t);
        em.flush();
        em.clear();
    }

}
