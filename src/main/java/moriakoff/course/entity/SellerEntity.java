package moriakoff.course.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity(name = "seller")
public class SellerEntity {

    @Id
    String email;

    @Column(name = "name")
    String sellerName;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
    List <CourseEntity> courses = new ArrayList <>();
}
