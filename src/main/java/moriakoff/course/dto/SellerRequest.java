package moriakoff.course.dto;


import lombok.*;
import moriakoff.course.entity.CourseEntity;

import javax.validation.constraints.Email;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class SellerRequest {

    @Email
    private String email;

    private String sellerName;

    private List <CourseEntity> courses;
}
