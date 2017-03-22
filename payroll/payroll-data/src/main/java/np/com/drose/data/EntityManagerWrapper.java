package np.com.drose.data;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
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

    @PersistenceContext(name = "PAYROLL_PU")
    EntityManager em;

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public <T> T persist(T t) throws SecurityException {
        this.em.persist(t);
        this.em.flush();
        this.em.refresh(t);
        return t;
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

    @SuppressWarnings("rawtypes")
    public List executeDynamicQuery(String query) {
        Query qry = this.em.createQuery(query);
        return qry.getResultList();
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

}
