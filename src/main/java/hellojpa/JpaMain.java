package hellojpa;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            String qlString = "select m from Member m where m.username like '%kim%'";
            List<Member> result = em.createQuery(
                    qlString
                    ,Member.class)
                    .getResultList();

            for (Member member : result) {
                System.out.println("member = " + member);
            }
            tx.commit();
        } catch (Exception e){
            tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
        emf.close();
    }
}
