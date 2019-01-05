package moriakoff.course.contoller;

import moriakoff.course.dto.CourseCreateDTO;
import moriakoff.course.dto.CourseRequest;
import moriakoff.course.dto.SellerRequest;
import moriakoff.course.entity.CourseEntity;
import moriakoff.course.exception.CourseDoesntExistExcepion;
import moriakoff.course.exception.DataIsNotCorrectException;
import moriakoff.course.exception.SellerDoesntExistException;
import moriakoff.course.service.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/crud")
public class CrudCourseController {

    @Autowired
    CrudService service;

    @PostMapping("/course")
    public CourseEntity addCourse(@RequestBody CourseCreateDTO course){
        try {
            return service.addCourse(course);
        } catch (DataIsNotCorrectException e) {
            e.printStackTrace();
        }

        return new CourseEntity();
    }

    @PutMapping("/course")
    public CourseEntity updateCourse(@RequestBody CourseRequest course){
        try {
            return service.updateCourse(course);
        } catch (SellerDoesntExistException e) {
            e.printStackTrace();
        }

        return new CourseEntity();
    }

    @DeleteMapping("/course")
    public CourseEntity deleteCourse(@RequestBody CourseRequest course){
        try {
            return service.deleteCourse(course);
        } catch (CourseDoesntExistExcepion courseDoesntExistExcepion) {
            courseDoesntExistExcepion.printStackTrace();
        }

        return new CourseEntity();
    }

    @PostMapping("/seller")
    public SellerRequest addSeller(@RequestBody SellerRequest seller){
        return service.addSeller(seller);
    }

    @PutMapping("/seller")
    public  SellerRequest updateSeller(@RequestBody SellerRequest seller){
        try {
            return service.updateSeller(seller);
        } catch (SellerDoesntExistException e) {
            e.printStackTrace();
        }
        return new SellerRequest();
    }

    @DeleteMapping("/seller")
    public SellerRequest deleteSeller(@RequestBody SellerRequest seller){
        try {
            return service.deleteSeller(seller);
        } catch (SellerDoesntExistException e) {
            e.printStackTrace();
        }

        return new SellerRequest();
    }

    @PutMapping("/seller/add-course/{email}")
    public CourseRequest addCourseToSeller(@PathVariable("email") String sellerEmail,
                                           @RequestBody CourseRequest course){
        try {
            return service.addCourseToSeller(sellerEmail, course);
        } catch (SellerDoesntExistException e) {
            e.printStackTrace();
        }

        return new CourseRequest();
    }




}
