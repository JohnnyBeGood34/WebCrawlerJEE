/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conf;

import java.io.Serializable;
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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Stef
 */
@Entity
@Table(name = "fait_reference")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FaitReference.findAll", query = "SELECT f FROM FaitReference f"),
    @NamedQuery(name = "FaitReference.findByIdRowResult", query = "SELECT f FROM FaitReference f WHERE f.idRowResult = :idRowResult")})
public class FaitReference implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_ROW_RESULT")
    private Integer idRowResult;
    @JoinColumn(name = "ID_MAIL", referencedColumnName = "ID_MAIL")
    @ManyToOne
    private Mail idMail;
    @JoinColumn(name = "ID_SEARCH_RESULT", referencedColumnName = "ID_SEARCH_RESULT")
    @ManyToOne
    private Searchresults idSearchResult;

    public FaitReference() {
    }

    public FaitReference(Integer idRowResult) {
        this.idRowResult = idRowResult;
    }

    public Integer getIdRowResult() {
        return idRowResult;
    }

    public void setIdRowResult(Integer idRowResult) {
        this.idRowResult = idRowResult;
    }

    public Mail getIdMail() {
        return idMail;
    }

    public void setIdMail(Mail idMail) {
        this.idMail = idMail;
    }

    public Searchresults getIdSearchResult() {
        return idSearchResult;
    }

    public void setIdSearchResult(Searchresults idSearchResult) {
        this.idSearchResult = idSearchResult;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idRowResult != null ? idRowResult.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FaitReference)) {
            return false;
        }
        FaitReference other = (FaitReference) object;
        if ((this.idRowResult == null && other.idRowResult != null) || (this.idRowResult != null && !this.idRowResult.equals(other.idRowResult))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "conf.FaitReference[ idRowResult=" + idRowResult + " ]";
    }
    
}
