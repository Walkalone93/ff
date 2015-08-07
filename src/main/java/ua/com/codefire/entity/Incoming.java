package ua.com.codefire.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "incoming", catalog = "test", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Incoming.findAll", query = "SELECT i FROM Incoming i"),
    @NamedQuery(name = "Incoming.findById", query = "SELECT i FROM Incoming i WHERE i.id = :id"),
    @NamedQuery(name = "Incoming.findByDescription", query = "SELECT i FROM Incoming i WHERE i.description = :description"),
    @NamedQuery(name = "Incoming.findBySum", query = "SELECT i FROM Incoming i WHERE i.sum = :sum"),
    @NamedQuery(name = "Incoming.findByCurrency", query = "SELECT i FROM Incoming i WHERE i.currency = :currency"),
    @NamedQuery(name = "Incoming.findByDate", query = "SELECT i FROM Incoming i WHERE i.date = :date")})
public class Incoming implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(name = "description", nullable = false, length = 45)
    private String description;
    @Basic(optional = false)
    @Column(name = "sum", nullable = false)
    private float sum;
    @Basic(optional = false)
    @Column(name = "currency", nullable = false, length = 45)
    private String currency;
    @Basic(optional = false)
    @Column(name = "date", nullable = false, length = 45)
    private String date;

    public Incoming() {
    }

    public Incoming(Integer id) {
        this.id = id;
    }

    public Incoming(String description, float sum, String currency, String date) {
        this.description = description;
        this.sum = sum;
        this.currency = currency;
        this.date = date;
    }

    public Incoming(Integer id, String description, float sum, String currency, String date) {
        this.id = id;
        this.description = description;
        this.sum = sum;
        this.currency = currency;
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getSum() {
        return sum;
    }

    public void setSum(float sum) {
        this.sum = sum;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 17 * hash + (this.id != null ? this.id.hashCode() : 0);
        hash = 17 * hash + (this.description != null ? this.description.hashCode() : 0);
        hash = 17 * hash + Float.floatToIntBits(this.sum);
        hash = 17 * hash + (this.currency != null ? this.currency.hashCode() : 0);
        hash = 17 * hash + (this.date != null ? this.date.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Incoming other = (Incoming) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        if ((this.description == null) ? (other.description != null) : !this.description.equals(other.description)) {
            return false;
        }
        if (Float.floatToIntBits(this.sum) != Float.floatToIntBits(other.sum)) {
            return false;
        }
        if ((this.currency == null) ? (other.currency != null) : !this.currency.equals(other.currency)) {
            return false;
        }
        if ((this.date == null) ? (other.date != null) : !this.date.equals(other.date)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Incoming{" + "id=" + id + ", description=" + description + ", sum=" + sum + ", currency=" + currency + ", date=" + date + '}';
    }

}
