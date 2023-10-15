package ma.enset.billingservice.repositories;

import ma.enset.billingservice.entities.ProductItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface ProductItemRepository extends JpaRepository<ProductItem, Long> {
    public Collection<ProductItem> findById(long id);
}
