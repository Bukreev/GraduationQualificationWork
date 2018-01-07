package ru.GraduationQualificationWork.Model.Repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.GraduationQualificationWork.Model.Entity.Link;

import java.util.List;

/**
 * Репозиторий URL ссылок
 */

@Repository
public interface LinkRepository extends CrudRepository<Link, Long> {

    @Query("select l from #{#entityName} l where l.adress = ?1")
    Link findByAdress(String adress);

//    @Query("update #{#entityName} l set l.parents = ?1 where l.adress = ?2")
//    void updateParents(List<Link> parents, String adress);
}
