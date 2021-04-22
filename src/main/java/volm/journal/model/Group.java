package volm.journal.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
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


    public Group(Long id, String info) {
        this.id = id;
        this.info = info;
    }
}