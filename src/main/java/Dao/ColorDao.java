package Dao;

import entitys.CategoryEntity;
import entitys.ColorEntity;
import entitys.ProductEntity;
import utils.JpaUtil;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.validation.constraints.Null;
import java.util.List;

public class ColorDao {
    private EntityManager em;
    public ColorDao() {
        this.em= JpaUtil.getEntityManager();
    }
    public ColorEntity add(ColorEntity entity) throws Exception{
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
    public int batchInsertadd(List<ColorEntity> entitys) throws Exception{
        int count = 0;
        for (ColorEntity p:entitys) {
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
    public ColorEntity edit(ColorEntity entity) throws Exception{
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
    public ColorEntity delete(ColorEntity entity) throws Exception{
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
    public List<ColorEntity> findAll(){
        String jpsql = "SELECT o FROM ColorEntity o";
        TypedQuery<ColorEntity> query = this.em.createQuery(jpsql,ColorEntity.class);
        List<ColorEntity> ds =query.getResultList();
        return ds.size()==0? null: ds;
    }
    public List<ColorEntity> findByProduct(Integer idProduct){
        String jpsql = "SELECT o FROM ProductEntity o WHERE o.idCategory = :id";
        TypedQuery<ColorEntity> query = this.em.createQuery(jpsql,ColorEntity.class);
        query.setParameter("id", idProduct);
        List<ColorEntity> ds =query.getResultList();
        return ds;
    }
    public ColorEntity findById(Integer id) {
        ColorEntity entity = this.em.find(ColorEntity.class,id);
        return entity;
    }
    public ColorEntity findByName(String name) {
        String jpql = "SELECT obj FROM ColorEntity obj "
                + "WHERE obj.nameColor = :name";
        TypedQuery<ColorEntity> query = this.em.createQuery(jpql,
                ColorEntity.class);
        query.setParameter("name", name);
        List<ColorEntity> result = query.getResultList();
        return result.size() == 0 ? null : result.get(0);
    }
}
