package moriakoff.curse.dto;


import lombok.*;

import javax.validation.constraints.Email;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class CourseCreateDTO {

    String courseName;

    @Email
    String email;

    String sellerName;

    Double price;

    LocalDate createDate;

}
