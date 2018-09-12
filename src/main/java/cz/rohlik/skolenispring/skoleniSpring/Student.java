package cz.rohlik.skolenispring.skoleniSpring;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class Student {

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Long id;
    private String firstName;
    private String lastName;
    private String birth;

    public Student(String firstName, String lastName, String birth) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birth = birth;
    }
}
