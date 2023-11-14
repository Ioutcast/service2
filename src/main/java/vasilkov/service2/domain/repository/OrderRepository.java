package vasilkov.service2.domain.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import vasilkov.service2.api.request.OrderCheckRequest;

@Repository
public class OrderRepository {
    @PersistenceContext
    private EntityManager entityManager;
    public boolean existsById(OrderCheckRequest orderCheckRequest) {
        Long id = orderCheckRequest.getOrderId();
        String query = "SELECT CASE WHEN COUNT(o) > 0 THEN true ELSE false END FROM _order  o WHERE o.id = :id";
        return (boolean) entityManager.createNativeQuery(query)
                .setParameter("id", id)
                .getSingleResult();
    }

}