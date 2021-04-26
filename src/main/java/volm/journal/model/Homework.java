package volm.journal.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

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


    public Homework() {
    }

    public Homework(Lesson lesson, User student) {
        this.lesson = lesson;
        this.student = student;
    }

    public Homework(Long id, Lesson lesson, User student, boolean done, String hwDescription) {
        this.id = id;
        this.lesson = lesson;
        this.student = student;
        this.done = done;
        this.hwDescription = hwDescription;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Lesson getLesson() {
        return lesson;
    }

    public void setLesson(Lesson lesson) {
        this.lesson = lesson;
    }

    public User getStudent() {
        return student;
    }

    public void setStudent(User student) {
        this.student = student;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public String getHwDescription() {
        return hwDescription;
    }

    public void setHwDescription(String hwDescription) {
        this.hwDescription = hwDescription;
    }
}
