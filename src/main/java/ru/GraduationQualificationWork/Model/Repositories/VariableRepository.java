package ru.GraduationQualificationWork.Model.Repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.GraduationQualificationWork.Model.Entity.Variable;

/**
 * Created by suzuka on 13.11.2017.
 */
@Repository
public interface VariableRepository extends CrudRepository<Variable, Long> {
}
