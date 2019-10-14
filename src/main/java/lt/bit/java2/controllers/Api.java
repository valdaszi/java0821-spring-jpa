package lt.bit.java2.controllers;

import lt.bit.java2.entities.Student;
import lt.bit.java2.repositories.StudentRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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

    @GetMapping
    public ResponseEntity<Page<Student>> getStudentsPage(
            @RequestParam(required = false, defaultValue = "10") int pageSize,
            @RequestParam(required = false, defaultValue = "1") int pageNo,
            @RequestParam(required = false) String orderBy,
            @RequestParam(required = false, defaultValue = "true") Boolean asc) {

        Sort sort = orderBy == null ? Sort.unsorted() :
                Sort.by(asc == null || asc
                        ? Sort.Order.asc(orderBy)
                        : Sort.Order.desc(orderBy));

        Page<Student> page = studentRepository.findAll(
                PageRequest.of(pageNo - 1, pageSize, sort));

        return ResponseEntity.ok(page);
    }

    @PostMapping
    public ResponseEntity<Student> create(@RequestBody Student student) {
        return ResponseEntity.ok(studentRepository.save(student));
    }

    @PutMapping
    public ResponseEntity<Student> edit(@RequestBody Student student) {
        return ResponseEntity.ok(studentRepository.save(student));
    }

    @GetMapping("/findByEmail/{email}")
    public ResponseEntity<Student> findByEmail(@PathVariable String email) {
        return ResponseEntity.ok(studentRepository.findByEmail(email));
    }
}
