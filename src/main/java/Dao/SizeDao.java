package Dao;

import entitys.ColorEntity;
import entitys.ProductEntity;
import entitys.SizeEntity;
import utils.JpaUtil;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class SizeDao {
    private EntityManager em;
    public SizeDao() {
        this.em= JpaUtil.getEntityManager();
    }
    public SizeEntity add(SizeEntity entity) throws Exception{
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
    public int batchInsertadd(List<SizeEntity> entitys) throws Exception{
        int count = 0;
        for (SizeEntity p:entitys) {
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
    public SizeEntity edit(SizeEntity entity) throws Exception{
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
    public SizeEntity delete(SizeEntity entity) throws Exception{
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
    public List<SizeEntity> findAllKey(String wild){
        String jpsql = "SELECT o FROM SizeEntity o WHERE o.nameSize like :wild";
        TypedQuery<SizeEntity> query = this.em.createQuery(jpsql,SizeEntity.class);
        query.setParameter("wild",wild);
        List<SizeEntity> ds =query.getResultList();
        this.em.refresh(ds);
        return ds;
    }
    public List<SizeEntity> findAll(){
        String jpsql = "SELECT o FROM SizeEntity o";
        TypedQuery<SizeEntity> query = this.em.createQuery(jpsql,SizeEntity.class);
        List<SizeEntity> ds =query.getResultList();
        this.em.refresh(ds);
        return ds.size()==0?null:ds;
    }
    public List<SizeEntity> findByProduct(Integer idProduct){
        String jpsql = "SELECT o FROM SizeEntity o WHERE ProductDetailsEntity.idSize = :id";
        TypedQuery<SizeEntity> query = this.em.createQuery(jpsql,SizeEntity.class);
        query.setParameter("id", idProduct);
        List<SizeEntity> ds =query.getResultList();
        return ds;
    }
    public SizeEntity findById(Integer id) {
        SizeEntity entity = this.em.find(SizeEntity.class,id);
        return entity;
    }
    public SizeEntity findByname(String name) {
        String jpql = "SELECT obj FROM SizeEntity obj "
                + "WHERE obj.nameSize = :name";
        TypedQuery<SizeEntity> query = this.em.createQuery(jpql,
                SizeEntity.class);
        query.setParameter("name", name);
        List<SizeEntity> result = query.getResultList();
        return result.size() == 0 ? null : query.getSingleResult();
    }
}
