package cz.rohlik.skolenispring.skoleniSpring.controllers;

import cz.rohlik.skolenispring.skoleniSpring.Student;
import cz.rohlik.skolenispring.skoleniSpring.repos.StudentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@RestController
@Slf4j
public class StudentController {

    private StudentRepository studentRepository;

    public StudentController(StudentRepository studentRepository){
        this.studentRepository = studentRepository;
    }

    /*
    GET method basic string on localhost
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String writeOk(){
        log.info("The GET request method running");
        return "student string";
    }

    /*
     POST request to add ONE student to database
     */
    @RequestMapping(value ="/students", method = RequestMethod.POST)
    public Student addStudent(@RequestBody Student newStudent) {
        log.info("The POST request method running");
        return studentRepository.save(newStudent);
    }

    /*
    GET requests to list all students
     */
    @RequestMapping(value = "/students", method = RequestMethod.GET)
    public List<Student> all(){
        log.info("The Post findAll request method running");
        return studentRepository.findAll();
    }

    /*
    GET request to find one student by its id
     */
    @RequestMapping(value = "/students/{id}", method = RequestMethod.GET)
    public Student findStudentById(@PathVariable Long id){

        return studentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException());
    }

    /*
    DELETE request to delete a student bz id
     */
    @RequestMapping(value = "/students/{id}", method = RequestMethod.DELETE)
    public void deleteStudent(@PathVariable Long id){
        studentRepository.deleteById(id);
    }

    /*
    PUT request to modify a student...searching by an id
     */
    @RequestMapping("/students/{id}")
    public Student updateStudent(@RequestBody Student newStudent, @PathVariable Long id) {

        return studentRepository.findById(id)
                .map(student -> {
                    student.setFirstName(newStudent.getFirstName());
                    student.setLastName(newStudent.getLastName());
                    return studentRepository.save(student);
                })
                .orElseGet(() -> {
                    newStudent.setId(id);
                    return studentRepository.save(newStudent);
                });
    }
}
