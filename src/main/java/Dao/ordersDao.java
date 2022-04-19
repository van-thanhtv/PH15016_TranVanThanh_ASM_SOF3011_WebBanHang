package Dao;

import entitys.OrdersEntity;
import utils.JpaUtil;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class ordersDao {
    private EntityManager em;
    public ordersDao() {
        this.em= JpaUtil.getEntityManager();
    }
    public OrdersEntity add(OrdersEntity entity) throws Exception{
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
    public OrdersEntity edit(OrdersEntity entity) throws Exception{
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
    public OrdersEntity delete(OrdersEntity entity) throws Exception{
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
    public List<OrdersEntity> findAll(){
        String jpsql = "SELECT o FROM OrdersEntity o";
        TypedQuery<OrdersEntity> query = this.em.createQuery(jpsql,OrdersEntity.class);
        List<OrdersEntity> ds =query.getResultList();
        return ds.size()==0? null: ds;
    }
    public OrdersEntity findById(Integer id) {
        OrdersEntity entity = this.em.find(OrdersEntity.class,id);
        return entity;
    }
    public OrdersEntity findByName(String name) {
        String jpql = "SELECT obj FROM ColorEntity obj "
                + "WHERE obj.nameColor = :name";
        TypedQuery<OrdersEntity> query = this.em.createQuery(jpql,
                OrdersEntity.class);
        query.setParameter("name", name);
        List<OrdersEntity> result = query.getResultList();
        return result.size() == 0 ? null : result.get(0);
    }
}
