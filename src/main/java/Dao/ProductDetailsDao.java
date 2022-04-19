package Dao;

import entitys.ProductDetailsEntity;
import entitys.ProductEntity;
import utils.JpaUtil;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class ProductDetailsDao {
    private EntityManager em;

    public ProductDetailsDao() {
        this.em= JpaUtil.getEntityManager();
    }
    public ProductDetailsEntity add(ProductDetailsEntity entity) throws Exception{
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
    public int batchInsertadd(List<ProductDetailsEntity> entitys) throws Exception{
        int count = 0;
        for (ProductDetailsEntity p:entitys) {
            count++;
            try {
                this.em.getTransaction().begin();
                this.em.persist(p);
                if(entitys.indexOf(p)%50==0){
                    this.em.flush();
                    this.em.clear();
                }
                this.em.getTransaction().commit();
            }catch (Exception e){
                e.printStackTrace();
                this.em.getTransaction().rollback();
                throw e;
            }

        }
        return count;
    }
    public ProductDetailsEntity edit(ProductDetailsEntity entity) throws Exception{
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
    public ProductDetailsEntity delete(ProductDetailsEntity entity) throws Exception{
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
    public List<ProductDetailsEntity> findAll(){
        int st =0;
        String jpsql = "SELECT o FROM ProductDetailsEntity o WHERE o.status = :st ";
        TypedQuery<ProductDetailsEntity> query = this.em.createQuery(jpsql,ProductDetailsEntity.class);
        query.setParameter("st", st);
        List<ProductDetailsEntity> ds =query.getResultList();
        return ds.size()== 0 ? null :ds;
    }
    public List<ProductDetailsEntity> findBySize(Integer idSize){
        String jpsql = "SELECT o FROM ProductDetailsEntity o WHERE o.idSize = :id";
        TypedQuery<ProductDetailsEntity> query = this.em.createQuery(jpsql,ProductDetailsEntity.class);
        query.setParameter("id", idSize);
        List<ProductDetailsEntity> ds =query.getResultList();
        return ds;
    }
    public List<ProductDetailsEntity> findByColor(Integer idColor){
        String jpsql = "SELECT o FROM ProductDetailsEntity o WHERE o.idColor = :id";
        TypedQuery<ProductDetailsEntity> query = this.em.createQuery(jpsql,ProductDetailsEntity.class);
        query.setParameter("id", idColor);
        List<ProductDetailsEntity> ds =query.getResultList();
        return ds;
    }
    public List<ProductDetailsEntity> findByProvided(Integer idProvided){
        String jpsql = "SELECT o FROM ProductDetailsEntity o WHERE o.idProvided = :id";
        TypedQuery<ProductDetailsEntity> query = this.em.createQuery(jpsql,ProductDetailsEntity.class);
        query.setParameter("id", idProvided);
        List<ProductDetailsEntity> ds =query.getResultList();
        return ds;
    }
    public List<ProductDetailsEntity> findByProduct(Integer idProduct){
        String jpsql = "SELECT o FROM ProductDetailsEntity o WHERE o.idProduct.id = :id";
        TypedQuery<ProductDetailsEntity> query = this.em.createQuery(jpsql,ProductDetailsEntity.class);
        query.setParameter("id", idProduct);
        List<ProductDetailsEntity> ds =query.getResultList();
        return ds;
    }
    public ProductDetailsEntity findById(Integer id) {
        ProductDetailsEntity entity = this.em.find(ProductDetailsEntity.class,id);
        return entity;
    }
}
