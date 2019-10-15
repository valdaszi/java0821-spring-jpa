package lt.bit.java2.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "studentai")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "vardas")
    private String firstName;

    @Column(name = "pavarde")
    private String lastName;

    @Column(name = "el_pastas")
    private String email;

    //@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    //@JoinColumn(name = "studentas_id")
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "student")
    private List<Grade> grades;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Grade> getGrades() {
        return grades;
    }

    public void setGrades(List<Grade> grades) {
        this.grades = grades;
    }
}
