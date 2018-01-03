package ru.GraduationQualificationWork.Model.Repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.GraduationQualificationWork.Model.Entity.Link;

/**
 * Репозиторий URL ссылок
 */

@Repository
public interface LinkRepository extends CrudRepository<Link, Long> {

    @Query("select u from #{#entityName} u where u.adress = ?1")
    Link findByAdress(String adress);
}
