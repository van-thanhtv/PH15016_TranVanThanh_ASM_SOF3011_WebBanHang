package Dao;

import entitys.UsersEntity;
import utils.JpaUtil;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class UserDao {
    private EntityManager em;

    public UserDao() {
        this.em= JpaUtil.getEntityManager();
    }
    public UsersEntity add(UsersEntity entity) throws Exception{
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
    public UsersEntity edit(UsersEntity entity) throws Exception{
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
    public UsersEntity delete(UsersEntity entity) throws Exception{
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
    public List<UsersEntity> findAll(){
        String jpsql = "SELECT o FROM UsersEntity o";
        TypedQuery<UsersEntity> query = this.em.createQuery(jpsql,UsersEntity.class);
        List<UsersEntity> ds =query.getResultList();
        return ds;
    }
    public UsersEntity findById(Integer id) {
        UsersEntity entity = this.em.find(UsersEntity.class,id);
        return entity;
    }
    public UsersEntity findByEmail(String email) {
        String jpql = "SELECT obj FROM UsersEntity obj "
                + "WHERE obj.email = :email";
        TypedQuery<UsersEntity> query = this.em.createQuery(jpql,
                UsersEntity.class);
        query.setParameter("email", email);
        List<UsersEntity> result = query.getResultList();
        return result.size() == 0 ? null : query.getSingleResult();
    }
}
