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
@Table(name = "spending", catalog = "test", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Spending.findAll", query = "SELECT s FROM Spending s"),
    @NamedQuery(name = "Spending.findById", query = "SELECT s FROM Spending s WHERE s.id = :id"),
    @NamedQuery(name = "Spending.findByDescription", query = "SELECT s FROM Spending s WHERE s.description = :description"),
    @NamedQuery(name = "Spending.findBySum", query = "SELECT s FROM Spending s WHERE s.sum = :sum"),
    @NamedQuery(name = "Spending.findByCurrency", query = "SELECT s FROM Spending s WHERE s.currency = :currency"),
    @NamedQuery(name = "Spending.findByDate", query = "SELECT s FROM Spending s WHERE s.date = :date")})
public class Spending implements Serializable {
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

    public Spending() {
    }

    public Spending(Integer id) {
        this.id = id;
    }

    public Spending(String description, float sum, String currency, String date) {
        this.description = description;
        this.sum = sum;
        this.currency = currency;
        this.date = date;
    }
    
    public Spending(Integer id, String description, float sum, String currency, String date) {
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
        int hash = 3;
        hash = 53 * hash + (this.id != null ? this.id.hashCode() : 0);
        hash = 53 * hash + (this.description != null ? this.description.hashCode() : 0);
        hash = 53 * hash + Float.floatToIntBits(this.sum);
        hash = 53 * hash + (this.currency != null ? this.currency.hashCode() : 0);
        hash = 53 * hash + (this.date != null ? this.date.hashCode() : 0);
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
        final Spending other = (Spending) obj;
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
        return "Spending{" + "id=" + id + ", description=" + description + ", sum=" + sum + ", currency=" + currency + ", date=" + date + '}';
    }
    
}
