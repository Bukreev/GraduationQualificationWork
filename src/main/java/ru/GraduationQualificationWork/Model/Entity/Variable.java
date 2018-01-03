package ru.GraduationQualificationWork.Model.Entity;

import com.sun.istack.internal.NotNull;

import javax.persistence.*;
import java.util.Set;


/**
 * Created by suzuka on 13.11.2017.
 */

@Entity
@Table(name = "Variables")
public class Variable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "Name")
    private String name;

    @Column(name = "Value")
    private String value;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    @JoinColumn(name = "link_id")
    private Link link;

    public Link getLinks() {
        return link;
    }

    public void setLinks(Link links) {
        this.link = links;
    }

    public String getValue() {

        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {

        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
