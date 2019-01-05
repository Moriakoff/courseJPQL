package moriakoff.course.repository.custom;

import moriakoff.course.entity.CourseEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class CustomCourseRepositoryImpl implements CustomCourseRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List <CourseEntity> getCourseByPriceRangeSorted(Boolean naturalOrder, Double min, Double max) {
        String query = "SELECT c FROM course c WHERE c.price BETWEEN :minPrice AND :maxPrice ORDER BY c.price ";

        if(naturalOrder){
            query = query + "ASC";
        } else {
            query = query + "DESC";
        }

        Query jpaQuery = entityManager.createQuery(query);
        jpaQuery.setParameter("minPrice", min);
        jpaQuery.setParameter("maxPrice", max);

        return jpaQuery.getResultList();
    }
}
