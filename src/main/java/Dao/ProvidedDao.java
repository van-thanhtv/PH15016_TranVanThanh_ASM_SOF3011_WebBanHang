package Dao;

import entitys.ProductEntity;
import entitys.ProvidedEntity;
import utils.JpaUtil;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class ProvidedDao {
    private EntityManager em;
    public ProvidedDao() {
        this.em= JpaUtil.getEntityManager();
    }
    public ProvidedEntity add(ProvidedEntity entity) throws Exception{
        try {
            this.em.getTransaction().begin();
            this.em.persist(entity);
            this.em.getTransaction().commit();
            return entity;
        }catch (Exception e){
            e.printStackTrace();
            this.em.getTransaction().rollback();
            throw e;
        }
    }
    public int batchInsertadd(List<ProvidedEntity> entitys) throws Exception{
        int count = 0;
        for (ProvidedEntity p:entitys) {
            count++;
            try {
                this.em.getTransaction().begin();
                this.em.persist(p);
                this.em.getTransaction().commit();
            }catch (Exception e){
                e.printStackTrace();
                this.em.getTransaction().rollback();
                throw e;
            }
            if(entitys.indexOf(p)%50==0){
                this.em.flush();
                this.em.clear();
            }
        }
        return count;
    }
    public ProvidedEntity edit(ProvidedEntity entity) throws Exception{
        try {
            this.em.getTransaction().begin();
            this.em.merge(entity);
            this.em.getTransaction().commit();
            return entity;
        }catch (Exception e){
            e.printStackTrace();
            this.em.getTransaction().rollback();
            throw e;
        }
    }
    public ProvidedEntity delete(ProvidedEntity entity) throws Exception{
        try {
            this.em.getTransaction().begin();
            this.em.remove(entity);
            this.em.getTransaction().commit();
            return entity;
        }catch (Exception e){
            e.printStackTrace();
            this.em.getTransaction().rollback();
            throw e;
        }
    }
    public List<ProvidedEntity> findAll(){
        String jpsql = "SELECT o FROM ProvidedEntity o";
        TypedQuery<ProvidedEntity> query = this.em.createQuery(jpsql,ProvidedEntity.class);
        List<ProvidedEntity> ds =query.getResultList();
        return ds;
    }
    public List<ProvidedEntity> findByProduct(Integer idProduct){
        String jpsql = "SELECT o FROM ProductEntity o WHERE o.idCategory.id = :id";
        TypedQuery<ProvidedEntity> query = this.em.createQuery(jpsql,ProvidedEntity.class);
        query.setParameter("id", idProduct);
        List<ProvidedEntity> ds =query.getResultList();
        return ds;
    }
    public ProvidedEntity findById(Integer id) {
        ProvidedEntity entity = this.em.find(ProvidedEntity.class,id);
        return entity;
    }
}
