package net.unesc.ip.adsecommerce.repositories.sql.dao;

import net.unesc.ip.adsecommerce.entities.sql.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductDao {

    @PersistenceContext
    private EntityManager em;

    public ProductDao(EntityManager em) {
        this.em = em;
    }

    private List<String> parseParamValues(List<String> values) {
        return values
                .stream()
                .map(String::trim)
                .map(String::toLowerCase)
                .collect(Collectors.toList());
    }

    public List<Product> findByFilters(List<String> brands, List<String> models, List<String> categories) {
        return findByFilters(brands, models, categories, Pageable.unpaged());
    }

    public List<Product> findByFilters(List<String> brands, List<String> models, List<String> categories, Pageable page) {

        String jpql = "SELECT p FROM Product p " +
                "JOIN p.brand b " +
                "JOIN p.category c " +
                "JOIN p.model m " +
                "WHERE 1=1 ";

        if (brands != null && !brands.isEmpty()) {
            jpql += "AND lower(b.description) IN (:brands)";
        }
        if (models != null && !models.isEmpty()) {
            jpql += "AND lower(m.description) IN (:models)";
        }
        if (categories != null && !categories.isEmpty()) {
            jpql += "AND lower(c.description) IN (:categories)";
        }

        Query query = em.createQuery(jpql, Product.class);

        if (brands != null && !brands.isEmpty()) {
            query.setParameter("brands", parseParamValues(brands));
        }
        if (models != null && !models.isEmpty()) {
            query.setParameter("models", parseParamValues(models));
        }
        if (categories != null && !categories.isEmpty()) {
            query.setParameter("categories", parseParamValues(categories));
        }

        if (page.isPaged()) {
            query
                .setMaxResults(page.getPageSize())
                .setFirstResult(page.getPageNumber() * page.getPageSize());
        }

        return query.getResultList();
    }
}
