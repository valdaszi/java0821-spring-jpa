package lt.bit.java2.controllers;

import lt.bit.java2.entities.Student;
import lt.bit.java2.repositories.StudentRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api")
public class Api {

    private final StudentRepository studentRepository;

    public Api(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable int id) {
        Optional<Student> student = studentRepository.findById(id);
        return student.isPresent() ?
                ResponseEntity.ok(student.get()) :
                ResponseEntity.notFound().build();
    }
}
