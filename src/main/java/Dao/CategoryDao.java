package Dao;

import entitys.CategoryEntity;
import entitys.ProvidedEntity;
import entitys.UsersEntity;
import utils.JpaUtil;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class CategoryDao {
    private EntityManager em;
    public CategoryDao() {
        this.em= JpaUtil.getEntityManager();
    }
    public CategoryEntity add(CategoryEntity entity) throws Exception{
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
    public CategoryEntity edit(CategoryEntity entity) throws Exception{
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
    public CategoryEntity delete(CategoryEntity entity) throws Exception{
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
    public List<CategoryEntity> findAll(){
        String jpsql = "SELECT o FROM CategoryEntity o";
        TypedQuery<CategoryEntity> query = this.em.createQuery(jpsql,CategoryEntity.class);
        List<CategoryEntity> ds =query.getResultList();
        return ds;
    }
    public CategoryEntity findById(Integer id) {
        CategoryEntity entity = this.em.find(CategoryEntity.class,id);
        return entity;
    }
    public CategoryEntity findByName(String name) {
        String jpql = "SELECT obj FROM CategoryEntity obj "
                + "WHERE obj.nameCategory = :name";
        TypedQuery<CategoryEntity> query = this.em.createQuery(jpql,
                CategoryEntity.class);
        query.setParameter("name", name);
        List<CategoryEntity> result = query.getResultList();
        return result.size() == 0 ? null : query.getSingleResult();
    }
}
