package lt.bit.java2.repositories;

import lt.bit.java2.entities.Student;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface StudentRepository extends PagingAndSortingRepository<Student, Integer> {

    Student findByEmail(String email);

    List<Student> findAllByLastNameAndFirstName(String lastName, String firstName);

}
