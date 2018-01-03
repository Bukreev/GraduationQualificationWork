package ru.GraduationQualificationWork.Model.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.GraduationQualificationWork.Model.Entity.Link;
import ru.GraduationQualificationWork.Model.Repositories.LinkRepository;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;


/**
 * Created by suzuka on 13.11.2017.
 */

@Service
public class LinkDao {


    LinkRepository linkRepository;

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

    public final void save(String url) {
        Link link = new Link();
        link.setAdress(url);
        linkRepository.save(link);
    }

    // Найти ссылку по адресу

    public final Link getLinkByAdress(String adress) {
        return linkRepository.findByAdress(adress);
    }
}
