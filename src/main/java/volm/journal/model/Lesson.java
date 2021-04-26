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
import java.util.Date;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "lesson")
public class Lesson {

    @Id
    @GeneratedValue
    private long id;

    @ManyToOne
    private Group group;

    @Column(name = "lesson_date")
    private Date lessonDate;


    public Lesson(Group group, Date lessonDate) {
        this.group = group;
        this.lessonDate = lessonDate;
    }
}