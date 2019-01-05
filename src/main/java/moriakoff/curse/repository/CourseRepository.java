package moriakoff.curse.repository;

import moriakoff.curse.entity.CourseEntity;
import moriakoff.curse.repository.custom.CustomCourseRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface CourseRepository extends JpaRepository <CourseEntity, Long>, CustomCourseRepository {

    @Query("SELECT c FROM course c WHERE c.courseName=:courseName")
    CourseEntity findByCourseName(@Param("courseName") String courseName);

    @Query("SELECT c FROM course c WHERE c.price BETWEEN :minPrice AND :maxPrice")
    List<CourseEntity> getAllCoursesBetweenPrice (@Param("minPrice") Double min, @Param("maxPrice") Double max);

    @Query("SELECT c FROM course c WHERE c.courseName LIKE %:courseName%")
    List<CourseEntity> getAllCoursesByNameLike(@Param("courseName") String courseName);

    @Query("SELECT c FROM course c WHERE c.createDate BETWEEN :fromDate AND :toDate")
    List <CourseEntity> getAllCoursesBetweenDate(@Param("fromDate") LocalDate from, @Param("toDate") LocalDate to);

}
