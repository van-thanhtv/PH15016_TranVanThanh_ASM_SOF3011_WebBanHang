package utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JpaUtil {
    public static EntityManagerFactory getFactory(){
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("PH15016_TranVanThanh_ASM_SOF3011_WebBanHang");
        return factory;
    }
    public static EntityManager getEntityManager(){
        EntityManager em = JpaUtil.getFactory().createEntityManager();
        return em;
    }
}
