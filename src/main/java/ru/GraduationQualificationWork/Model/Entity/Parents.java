//package ru.GraduationQualificationWork.Model.Entity;
//
//import com.sun.istack.internal.NotNull;
//
//import javax.persistence.*;
//import java.util.List;
//
//@Entity
//@Table(name = "Parents")
//public class Parents {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private Long id;
//
//    @NotNull
//    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
//    @JoinColumn(name = "link_id")
//    private Link link;
//
//    @NotNull
//    @Column(name = "Parents")
//    private List<Long> parents;
//
//}
