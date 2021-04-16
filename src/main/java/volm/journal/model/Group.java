package volm.journal.model;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "sgroup")
public class Group {

    @Id
    private Long id;

    @JsonIgnore
    @OneToMany(mappedBy = "group")
    private List<User> users;

    @Column(length = 2000)
    private String info;


    public Group() {
    }

    public Group(Long id) {
        this.id = id;
    }

    public Group(Long id, String info) {
        this.id = id;
        this.info = info;
    }

    public Group(Long id, List<User> users, String info) {
        this.id = id;
        this.users = users;
        this.info = info;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}