package moriakoff.curse.repository.custom;

import moriakoff.curse.entity.CourseEntity;

import java.util.List;

public interface CustomCourseRepository {

    List <CourseEntity> getCourseByPriceRangeSorted(Boolean naturalOrder, Double min, Double max);
}
