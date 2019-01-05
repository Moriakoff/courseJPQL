package moriakoff.curse.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
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
    @Email
    String email;

    @Column(name = "name")
    String sellerName;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
    List <CourseEntity> courses = new ArrayList <>();
}
