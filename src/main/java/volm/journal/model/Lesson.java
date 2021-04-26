package volm.journal.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Date;


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


    public Lesson() {
    }

    public Lesson(long id, Group group, Date lessonDate) {
        this.id = id;
        this.group = group;
        this.lessonDate = lessonDate;
    }

    public Lesson(Group group, Date lessonDate) {
        this.group = group;
        this.lessonDate = lessonDate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public Date getLessonDate() {
        return lessonDate;
    }

    public void setLessonDate(Date lessonDate) {
        this.lessonDate = lessonDate;
    }
}