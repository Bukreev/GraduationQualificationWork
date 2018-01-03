package ru.GraduationQualificationWork.Model.Entity;

import com.sun.istack.internal.Nullable;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

/**
 * Created by suzuka on 13.11.2017.
 */

@Entity
@Table(name = "Links")
public class Link {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "Level")
    private Integer level;

    @Column(name = "Adress")
    private String adress;

    @Nullable
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "link")
    @Column(name = "Variables")
    private List<Variable> variable;


    public final Long getId() {
        return id;
    }

    public final void setId(Long id) {
        this.id = id;
    }

    public final Integer getLevel() {
        return level;
    }

    public final void setLevel(Integer level) {
        this.level = level;
    }

    public final String getAdress() {
        return adress;
    }

    public final void setAdress(String adress) {
        this.adress = adress;
    }

    public final List<Variable> getVariable() {
        return variable;
    }

    public final void setVariable(List<Variable> variable) {
        this.variable = variable;
    }
}
