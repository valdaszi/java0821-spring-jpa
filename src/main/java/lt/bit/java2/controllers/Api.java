package lt.bit.java2.controllers;

import lt.bit.java2.entities.Grade;
import lt.bit.java2.entities.Student;
import lt.bit.java2.repositories.StudentRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
        Optional<Student> studentDb = studentRepository.findById(student.getId());
        if (!studentDb.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        Student studentUpd = studentDb.get();

        studentUpd.setEmail(student.getEmail());
        studentUpd.setFirstName(student.getFirstName());
        studentUpd.setLastName(student.getLastName());

        return ResponseEntity.ok(studentRepository.save(studentUpd));
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Integer> delete(@PathVariable int id) {
        Optional<Student> student = studentRepository.findById(id);
        if (!student.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        studentRepository.delete(student.get());
        return ResponseEntity.ok(id);
    }

    @PostMapping("/{id}")
    ResponseEntity<Student> addGrade(@PathVariable int id, @RequestBody Grade grade) {
        Optional<Student> studentOpt = studentRepository.findById(id);
        if (!studentOpt.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        Student student = studentOpt.get();
        if (student.getGrades() == null) student.setGrades(new ArrayList<>());

        grade.setStudentId(student.getId());
        student.getGrades().add(grade);
        return ResponseEntity.ok(studentRepository.save(student));
    }


    @GetMapping("/findByEmail/{email}")
    public ResponseEntity<Student> findByEmail(@PathVariable String email) {
        return ResponseEntity.ok(studentRepository.findByEmail(email));
    }

}
