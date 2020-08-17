package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Table(name = "follows")
@NamedQueries({
    @NamedQuery(
            name = "getAllFollows",
            query = "SELECT f FROM Follow AS f ORDER BY f.id DESC"
          ),
     @NamedQuery(
             name = "getFollowsCount",
             query = "SELECT COUNT(f) FROM Follow AS f"
          ),
     @NamedQuery(
             name = "checkFollow",
             query = "SELECT f FROM Follow AS f WHERE f.followerd_id = :fol_id AND f.employee = :emp"
             )
    })
@Entity
public class Follow {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;


    @Column(name = "followerd_id",nullable = false)
    private Integer followerd_id;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Integer getFollowerd_id() {
        return followerd_id;
    }

    public void setFollowerd_id(Integer followerd_id) {
        this.followerd_id = followerd_id;
    }



}
