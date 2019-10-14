package lt.bit.java2.rest;

import lt.bit.java2.entities.Student;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(path = "student")
public interface StudentRestRepository extends PagingAndSortingRepository<Student, Integer> {

    Student findByEmail(String email);

    List<Student> findAllByLastNameAndFirstName(String lastName, String firstName);
}
