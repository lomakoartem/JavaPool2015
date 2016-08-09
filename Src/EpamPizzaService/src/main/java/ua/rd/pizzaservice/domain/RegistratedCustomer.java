package ua.rd.pizzaservice.domain;

import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.time.LocalDate;
import java.util.Date;

/**
 * Created by lomak on 22.04.2016.
 */
@Entity
public class RegistratedCustomer extends Customer {
  @Temporal(TemporalType.DATE)
    private Date creationDate;

    public RegistratedCustomer(Date date) {
        this.creationDate = date;
    }

    public RegistratedCustomer() {
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    @Override
    public String toString() {
        return "RegistratedCustomer{" +
                "creationDate=" + creationDate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RegistratedCustomer)) return false;

        RegistratedCustomer that = (RegistratedCustomer) o;

        return creationDate != null ? creationDate.equals(that.creationDate) : that.creationDate == null;

    }

    @Override
    public int hashCode() {
        return creationDate != null ? creationDate.hashCode() : 0;
    }
}
