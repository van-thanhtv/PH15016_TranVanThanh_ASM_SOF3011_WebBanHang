package Dao;

import entitys.OrdersDetailEntity;
import utils.JpaUtil;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class ordersDetailDao {
    private EntityManager em;
    public ordersDetailDao() {
        this.em= JpaUtil.getEntityManager();
    }
    public OrdersDetailEntity add(OrdersDetailEntity entity) throws Exception{
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
    public OrdersDetailEntity edit(OrdersDetailEntity entity) throws Exception{
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
    public OrdersDetailEntity delete(OrdersDetailEntity entity) throws Exception{
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
    public List<OrdersDetailEntity> findAll(){
        String jpsql = "SELECT o FROM OrdersDetailEntity o";
        TypedQuery<OrdersDetailEntity> query = this.em.createQuery(jpsql,OrdersDetailEntity.class);
        List<OrdersDetailEntity> ds =query.getResultList();
        return ds.size()==0? null: ds;
    }
    public OrdersDetailEntity findById(Integer id) {
        OrdersDetailEntity entity = this.em.find(OrdersDetailEntity.class,id);
        return entity;
    }
    public OrdersDetailEntity findByName(String name) {
        String jpql = "SELECT obj FROM OrdersDetailEntity obj "
                + "WHERE obj.id = :name";
        TypedQuery<OrdersDetailEntity> query = this.em.createQuery(jpql,
                OrdersDetailEntity.class);
        query.setParameter("name", name);
        List<OrdersDetailEntity> result = query.getResultList();
        return result.size() == 0 ? null : result.get(0);
    }
}
