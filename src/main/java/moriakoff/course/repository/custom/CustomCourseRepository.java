package moriakoff.course.repository.custom;

import moriakoff.course.entity.CourseEntity;

import java.util.List;

public interface CustomCourseRepository {

    List <CourseEntity> getCourseByPriceRangeSorted(Boolean naturalOrder, Double min, Double max);
}
