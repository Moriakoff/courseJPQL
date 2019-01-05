package moriakoff.course.contoller;

import moriakoff.course.entity.CourseEntity;
import moriakoff.course.repository.CourseRepository;
import moriakoff.course.repository.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/search")
public class SearchCourseController {

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    SellerRepository sellerRepository;

    @GetMapping("/course/price")
    public List <CourseEntity> searchCourseByPriceBetween(@RequestParam("min") Double min,
                                                          @RequestParam("max") Double max) {

        return courseRepository.getAllCoursesBetweenPrice(min, max);

    }

    @GetMapping("/course/name")
    public List <CourseEntity> searchCourseByNameLike(@RequestParam("name") String name) {

        return courseRepository.getAllCoursesByNameLike(name);

    }

    @GetMapping("/course/by-seller")
    public List <CourseEntity> searchCourseBySellerName(@RequestParam("name") String name) {

        return sellerRepository.getSellerByName(name).getCourses();

    }

    @GetMapping("/course/by-date")
    public List <CourseEntity> searchCourseByDateBetween(
            @RequestParam(value = "from") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,
            @RequestParam(value = "to") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to) {

        return courseRepository.getAllCoursesBetweenDate(from, to);

    }

    @GetMapping("/course/price-sorted")
    public List <CourseEntity> searchCourseByPriceBetweenSorted(@RequestParam("min") Double min,
                                                                @RequestParam("max") Double max,
                                                                @RequestParam(name = "isOrdered",
                                                                        defaultValue = "true") Boolean isOrdered) {

        return courseRepository.getCourseByPriceRangeSorted(isOrdered, min, max);
    }
}
