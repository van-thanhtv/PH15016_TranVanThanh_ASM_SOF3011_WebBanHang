package Dao;

import entitys.ColorEntity;
import entitys.CustomerEntity;
import utils.JpaUtil;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class customerDao {
    private EntityManager em;
    public customerDao() {
        this.em= JpaUtil.getEntityManager();
    }
    public CustomerEntity add(CustomerEntity entity) throws Exception{
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
    public CustomerEntity edit(CustomerEntity entity) throws Exception{
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
    public CustomerEntity delete(CustomerEntity entity) throws Exception{
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
    public List<CustomerEntity> findAll(){
        String jpsql = "SELECT o FROM CustomerEntity o";
        TypedQuery<CustomerEntity> query = this.em.createQuery(jpsql,CustomerEntity.class);
        List<CustomerEntity> ds =query.getResultList();
        return ds.size()==0? null: ds;
    }

    public ColorEntity findById(Integer id) {
        ColorEntity entity = this.em.find(ColorEntity.class,id);
        return entity;
    }
    public CustomerEntity findByEmail(String email) {
        String jpql = "SELECT obj FROM CustomerEntity obj "
                + "WHERE obj.email = :name";
        TypedQuery<CustomerEntity> query = this.em.createQuery(jpql,
                CustomerEntity.class);
        query.setParameter("name", email);
        List<CustomerEntity> result = query.getResultList();
        return result.size() == 0 ? null : result.get(0);
    }
    public CustomerEntity findByPhone(String phone) {
        String jpql = "SELECT obj FROM CustomerEntity obj "
                + "WHERE obj.numberPhone = :name";
        TypedQuery<CustomerEntity> query = this.em.createQuery(jpql,
                CustomerEntity.class);
        query.setParameter("name", phone);
        List<CustomerEntity> result = query.getResultList();
        return result.size() == 0 ? null : result.get(0);
    }
}
