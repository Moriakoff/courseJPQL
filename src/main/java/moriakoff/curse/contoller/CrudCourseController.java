package moriakoff.curse.contoller;

import moriakoff.curse.dto.CourseCreateDTO;
import moriakoff.curse.dto.CourseRequest;
import moriakoff.curse.dto.SellerRequest;
import moriakoff.curse.entity.CourseEntity;
import moriakoff.curse.exception.CourseDoesntExistExcepion;
import moriakoff.curse.exception.DataIsNotCorrectException;
import moriakoff.curse.exception.SellerDoesntExistException;
import moriakoff.curse.service.CrudService;
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
