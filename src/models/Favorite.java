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

@Table(name = "favorites")
@NamedQueries({
    @NamedQuery(
            name = "getAllFavorites",
            query = "SELECT f FROM Favorite AS f ORDER BY f.id DESC"
          ),
     @NamedQuery(
             name = "getFavoritesCount",
             query = "SELECT COUNT(f) FROM Favorite AS f WHERE f.report = :f_report"
          ),
     @NamedQuery(
             name = "checkFavorite",
             query = "SELECT f FROM Favorite AS f WHERE f.employee = :em_id AND f.report = :repo"
             )
    })
@Entity
public class Favorite {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "report_id", nullable = false)
    private Report report;

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

    public Report getReport() {
        return report;
    }

    public void setReport(Report report) {
        this.report = report;
    }


}

