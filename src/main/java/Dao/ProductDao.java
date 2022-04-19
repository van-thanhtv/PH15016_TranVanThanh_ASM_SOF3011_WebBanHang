package Dao;

import entitys.ProductEntity;
import entitys.UsersEntity;
import utils.JpaUtil;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class ProductDao {
    private EntityManager em;
    public ProductDao() {
        this.em= JpaUtil.getEntityManager();
    }
    public ProductEntity add(ProductEntity entity) throws Exception{
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
    public int batchInsertadd(List<ProductEntity> entitys) throws Exception{
        int count = 0;
        for (ProductEntity p:entitys) {
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
    public ProductEntity edit(ProductEntity entity) throws Exception{
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
    public ProductEntity delete(ProductEntity entity) throws Exception{
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
    public List<ProductEntity> findAll(){
        String jpsql = "SELECT o FROM ProductEntity o WHERE o.status = 0";
        TypedQuery<ProductEntity> query = this.em.createQuery(jpsql,ProductEntity.class);
        List<ProductEntity> ds =query.getResultList();
        return ds;
    }
    public List<ProductEntity> findByCategory(Integer idCategory){
        String jpsql = "SELECT o FROM ProductEntity o WHERE o.idCategory.id = :id";
        TypedQuery<ProductEntity> query = this.em.createQuery(jpsql,ProductEntity.class);
        query.setParameter("id", idCategory);
        List<ProductEntity> ds =query.getResultList();
        return ds;
    }
    public ProductEntity findById(Integer id) {
        ProductEntity entity = this.em.find(ProductEntity.class,id);
        return entity;
    }
    public ProductEntity findByName(String name) {
        String jpql = "SELECT obj FROM ProductEntity obj "
                + "WHERE obj.nameProduct = :name";
        TypedQuery<ProductEntity> query = this.em.createQuery(jpql,
                ProductEntity.class);
        query.setParameter("name", name);
        List<ProductEntity> result = query.getResultList();
        return result.size() == 0 ? null : query.getSingleResult();
    }
}
