package models;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Table(name = "clients")
@NamedQueries({
    @NamedQuery(
            name = "getAllClients",
            query = "SELECT c FROM Client AS c ORDER BY c.id DESC"
            ),
    @NamedQuery(
            name = "getClientsCount",
            query = "SELECT Count(c) FROM Client AS c"
            ),
    @NamedQuery(
            name = "checkRegisteredNum",
            query = "SELECT COUNT(c) FROM Client AS c WHERE c.num = :num"
            )
})
@Entity
public class Client {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "num", nullable = false, unique = true)
    private String num;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "created_at",nullable = false)
    private Timestamp created_at;

    @Column(name = "updated_at", nullable = false)
    private Timestamp updated_at;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }

    public Timestamp getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Timestamp updated_at) {
        this.updated_at = updated_at;
    }



}
