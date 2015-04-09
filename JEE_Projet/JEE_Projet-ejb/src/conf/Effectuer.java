/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conf;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Stef
 */
@Entity
@Table(name = "effectuer")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Effectuer.findAll", query = "SELECT e FROM Effectuer e"),
    @NamedQuery(name = "Effectuer.findByIdRow", query = "SELECT e FROM Effectuer e WHERE e.idRow = :idRow"),
    @NamedQuery(name = "Effectuer.findByDateSearch", query = "SELECT e FROM Effectuer e WHERE e.dateSearch = :dateSearch")})
public class Effectuer implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_ROW")
    private Integer idRow;
    @Column(name = "DATE_SEARCH")
    @Temporal(TemporalType.DATE)
    private Date dateSearch;
    @JoinColumn(name = "ID_SEARCH", referencedColumnName = "ID_SEARCH")
    @ManyToOne
    private Search idSearch;
    @JoinColumn(name = "ID_USER", referencedColumnName = "ID_USER")
    @ManyToOne
    private User idUser;

    public Effectuer() {
    }

    public Effectuer(Integer idRow) {
        this.idRow = idRow;
    }

    public Integer getIdRow() {
        return idRow;
    }

    public void setIdRow(Integer idRow) {
        this.idRow = idRow;
    }

    public Date getDateSearch() {
        return dateSearch;
    }

    public void setDateSearch(Date dateSearch) {
        this.dateSearch = dateSearch;
    }

    public Search getIdSearch() {
        return idSearch;
    }

    public void setIdSearch(Search idSearch) {
        this.idSearch = idSearch;
    }

    public User getIdUser() {
        return idUser;
    }

    public void setIdUser(User idUser) {
        this.idUser = idUser;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idRow != null ? idRow.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Effectuer)) {
            return false;
        }
        Effectuer other = (Effectuer) object;
        if ((this.idRow == null && other.idRow != null) || (this.idRow != null && !this.idRow.equals(other.idRow))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "conf.Effectuer[ idRow=" + idRow + " ]";
    }
    
}
