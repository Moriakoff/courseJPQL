package moriakoff.curse.dto;


import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class CourseRequest {

    String courseName;

    Double price;

    String sellerName;
}
