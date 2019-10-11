package lt.bit.java2.repositories;

import lt.bit.java2.entities.Student;
import org.springframework.data.repository.CrudRepository;

public interface StudentRepository extends CrudRepository<Student, Integer> {
}
