package moriakoff.curse.service;

import moriakoff.curse.dto.CourseCreateDTO;
import moriakoff.curse.dto.CourseRequest;
import moriakoff.curse.dto.SellerRequest;
import moriakoff.curse.entity.CourseEntity;
import moriakoff.curse.entity.SellerEntity;
import moriakoff.curse.exception.CourseDoesntExistExcepion;
import moriakoff.curse.exception.DataIsNotCorrectException;
import moriakoff.curse.exception.SellerDoesntExistException;
import moriakoff.curse.repository.CourseRepository;
import moriakoff.curse.repository.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;

@Service
public class CrudServiceImpl implements CrudService {

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    SellerRepository sellerRepository;


    @Override
    @Transactional
    public CourseEntity addCourse(CourseCreateDTO course) throws DataIsNotCorrectException {
        if (course.getCourseName() == null || course.getEmail() == null || course.getSellerName() == null) {
            throw new DataIsNotCorrectException("Data isn't correct, please check request data");
        }

        if(courseRepository.findByCourseName(course.getCourseName()) != null) return new CourseEntity();

        SellerEntity sellerEntity = sellerRepository.findById(course.getEmail()).orElse(null);
        if (sellerEntity == null) {
            sellerEntity = SellerEntity.builder()
                    .sellerName(course.getSellerName())
                    .courses(new ArrayList <>())
                    .email(course.getEmail())
                    .build();
        }

        CourseEntity courseEntity = courseRepository.findByCourseName(course.getCourseName());

        if (courseEntity == null) {

            LocalDate date = course.getCreateDate() == null ? LocalDate.now() : course.getCreateDate();

            Double price = course.getPrice() == null ? 0. : course.getPrice();

            courseEntity = CourseEntity.builder()
                    .courseName(course.getCourseName())
                    .price(price)
                    .createDate(date)
                    .build();
        }

        sellerEntity.getCourses().add(courseEntity);

        sellerRepository.save(sellerEntity);

        return courseEntity;
    }

    @Override
    @Transactional
    public CourseEntity updateCourse(CourseRequest course) throws SellerDoesntExistException {
        SellerEntity sellerEntity = sellerRepository.getSellerByName(course.getSellerName());

        if (sellerEntity == null) {
            throw new SellerDoesntExistException("Seller by name " + course.getSellerName() +
                    " doesnt't exist, please check request data");
        }

        CourseEntity courseEntity = courseRepository.findByCourseName(course.getCourseName());

        courseEntity.setCourseName(course.getCourseName());
        courseEntity.setPrice(course.getPrice());
        courseEntity.setCreateDate(LocalDate.now());


        return courseRepository.save(courseEntity);
    }

    @Override
    @Transactional
    public CourseEntity deleteCourse(CourseRequest course) throws CourseDoesntExistExcepion {
        CourseEntity courseEntity = courseRepository.findByCourseName(course.getCourseName());

        if (courseEntity == null) {
            throw new CourseDoesntExistExcepion("Course by name " + course.getCourseName() +
                    " doesn't exist, please check request data");
        }

        courseRepository.delete(courseEntity);
        return courseEntity;
    }

    @Override
    @Transactional
    public SellerRequest addSeller(SellerRequest seller) {
        SellerEntity sellerEntity = sellerRepository.findById(seller.getEmail()).orElse(null);
        if (sellerEntity != null) return new SellerRequest();

        sellerEntity = new SellerEntity();
        sellerEntity.setEmail(seller.getEmail());
        sellerEntity.setSellerName(seller.getSellerName());

        sellerRepository.save(sellerEntity);

        return seller;
    }

    @Override
    @Transactional
    public SellerRequest updateSeller(SellerRequest seller) throws SellerDoesntExistException {
        SellerEntity sellerEntity = sellerRepository.findById(seller.getEmail()).orElse(null);

        if (sellerEntity == null){
            throw new SellerDoesntExistException("Seller by email " + seller.getEmail() +
                    " doesnt't exist, please check request data");
        }

        sellerEntity.setSellerName(seller.getSellerName());
        sellerEntity.setEmail(seller.getEmail());

        sellerRepository.save(sellerEntity);

        return seller;
    }

    @Override
    @Transactional
    public SellerRequest deleteSeller(SellerRequest seller) throws SellerDoesntExistException {
        SellerEntity sellerEntity = sellerRepository.findById(seller.getEmail()).orElse(null);

        if (sellerEntity == null) {
            throw new SellerDoesntExistException("Seller by email " + seller.getEmail() +
                    " doesnt't exist, please check request data");
        }

        sellerRepository.delete(sellerEntity);
        return seller;
    }

    @Override
    @Transactional
    public CourseRequest addCourseToSeller(String sellerEmail, CourseRequest course) throws SellerDoesntExistException {
        SellerEntity sellerEntity = sellerRepository.findById(sellerEmail).orElse(null);

        if(sellerEntity == null){
            throw new SellerDoesntExistException("Seller by email " + sellerEmail+
                    " doesnt't exist, please check request data");
        }

        CourseEntity courseEntity = courseRepository.findByCourseName(course.getCourseName());

        if (courseEntity == null) {

            courseEntity= new CourseEntity();

        }

        Double price = course.getPrice() == null ? 0. : course.getPrice();

        courseEntity.setPrice(price);
        courseEntity.setCourseName(course.getCourseName());
        courseEntity.setCreateDate(LocalDate.now());

        courseRepository.save(courseEntity);

        sellerEntity.getCourses().add(courseEntity);

        sellerRepository.save(sellerEntity);

        return course;
    }
}
