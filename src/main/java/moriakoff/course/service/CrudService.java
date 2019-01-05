package moriakoff.course.service;

import moriakoff.course.dto.CourseCreateDTO;
import moriakoff.course.dto.CourseRequest;
import moriakoff.course.dto.SellerRequest;
import moriakoff.course.entity.CourseEntity;
import moriakoff.course.exception.CourseDoesntExistExcepion;
import moriakoff.course.exception.DataIsNotCorrectException;
import moriakoff.course.exception.SellerDoesntExistException;

public interface CrudService {

    CourseEntity addCourse(CourseCreateDTO course) throws DataIsNotCorrectException;

    CourseEntity updateCourse(CourseRequest course) throws SellerDoesntExistException;

    CourseEntity deleteCourse(CourseRequest course) throws CourseDoesntExistExcepion;

    SellerRequest addSeller(SellerRequest seller);

    SellerRequest updateSeller(SellerRequest seller) throws SellerDoesntExistException;

    SellerRequest deleteSeller(SellerRequest seller) throws SellerDoesntExistException;

    CourseRequest addCourseToSeller(String sellerEmail, CourseRequest course) throws SellerDoesntExistException;

}
