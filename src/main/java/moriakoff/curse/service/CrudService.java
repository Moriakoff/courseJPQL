package moriakoff.curse.service;

import moriakoff.curse.dto.CourseCreateDTO;
import moriakoff.curse.dto.CourseRequest;
import moriakoff.curse.dto.SellerRequest;
import moriakoff.curse.entity.CourseEntity;
import moriakoff.curse.exception.CourseDoesntExistExcepion;
import moriakoff.curse.exception.DataIsNotCorrectException;
import moriakoff.curse.exception.SellerDoesntExistException;

public interface CrudService {

    CourseEntity addCourse(CourseCreateDTO course) throws DataIsNotCorrectException;

    CourseEntity updateCourse(CourseRequest course) throws SellerDoesntExistException;

    CourseEntity deleteCourse(CourseRequest course) throws CourseDoesntExistExcepion;

    SellerRequest addSeller(SellerRequest seller);

    SellerRequest updateSeller(SellerRequest seller) throws SellerDoesntExistException;

    SellerRequest deleteSeller(SellerRequest seller) throws SellerDoesntExistException;

    CourseRequest addCourseToSeller(String sellerEmail, CourseRequest course) throws SellerDoesntExistException;

}
