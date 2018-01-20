package ru.GraduationQualificationWork.Model.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.GraduationQualificationWork.Model.Entity.Link;
import ru.GraduationQualificationWork.Model.Repositories.LinkRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;


/**
 * Created by suzuka on 13.11.2017.
 */

@Service
public class LinkDao {


    private final LinkRepository linkRepository;

    @Autowired
    public LinkDao(LinkRepository linkRepository) {
        this.linkRepository = linkRepository;
    }


    // Найти ссылку по id

    public final Link getLinkById(Long id) {
        return linkRepository.findOne(id);
    }

    // Найти все ссылки

    public final List<Link> getAllLinks() {
        List<Link> linkSet = StreamSupport.stream(linkRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());

        return linkSet;
    }

    // Сохранить ссылку

    public final void save(Link link) {
        linkRepository.save(link);
    }

    // Сохраняет ссылку с указанием родителя

    public final void save(String url, Link parentLink) {
        List<Link> parents = new ArrayList();
        Link link = new Link();
        link.setAdress(url);
//        link.setParentLinks(parents);
        linkRepository.save(link);
    }

    // Добавить родителя

    public final void addParent(Link link) {

    }

    // Найти ссылку по адресу

    public final Link getLinkByAdress(String adress) {
        return linkRepository.findByAdress(adress);
    }

    // Добавить родительскую ссылку

//    public final void addParentLink(String url, Long parentId) {
//        Link link = linkRepository.findByAdress(url);
//        link.setParentId(parentId);
//        linkRepository.save(link);
//    }
}
