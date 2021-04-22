package volm.journal.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "homework")
public class Homework {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Lesson lesson;

    @ManyToOne
    private User student;

    private boolean done;

    @Column(name = "hw_description", length = 2000)
    private String hwDescription;


    public Homework(Lesson lesson, User student) {
        this.lesson = lesson;
        this.student = student;
    }
}