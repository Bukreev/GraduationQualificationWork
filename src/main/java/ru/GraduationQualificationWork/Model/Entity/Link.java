package ru.GraduationQualificationWork.Model.Entity;

import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;

import javax.persistence.*;
import java.util.HashSet;
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

    @NotNull
    @Column(name = "Level")
    private Integer level;

    @NotNull
    @Column(name = "Adress")
    private String adress;

    @Nullable
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "link")
    @Column(name = "Variables")
    private List<Variable> variable;

//    @ElementCollection
//    @CollectionTable(name = "Parents", joinColumns = @JoinColumn(name = "id"))
    @Column(name = "Parents")
    private Long parents;


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

    public final List<Variable> getVariables() {
        return variable;
    }

    public final void setVariable(List<Variable> variable) {
        this.variable = variable;
    }

    public final Long getParentIdSet() {
        return parents;
    }

    public final void setParentId(Long parentId) {
        this.parents = parentId;
    }
}
