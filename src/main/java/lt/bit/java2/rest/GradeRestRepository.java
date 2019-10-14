package lt.bit.java2.rest;

import lt.bit.java2.entities.Grade;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "grade")
public interface GradeRestRepository extends PagingAndSortingRepository<Grade, Integer> {
}
