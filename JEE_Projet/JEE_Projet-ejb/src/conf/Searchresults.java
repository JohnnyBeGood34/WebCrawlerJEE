/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conf;

import java.io.Serializable;
import java.util.Collection;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Stef
 */
@Entity
@Table(name = "searchresults")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Searchresults.findAll", query = "SELECT s FROM Searchresults s"),
    @NamedQuery(name = "Searchresults.findByIdSearchResult", query = "SELECT s FROM Searchresults s WHERE s.idSearchResult = :idSearchResult"),
    @NamedQuery(name = "Searchresults.findByEmailResult", query = "SELECT s FROM Searchresults s WHERE s.emailResult = :emailResult")})
public class Searchresults implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_SEARCH_RESULT")
    private Integer idSearchResult;
    @Size(max = 60)
    @Column(name = "EMAIL_RESULT")
    private String emailResult;
    @OneToMany(mappedBy = "idSearchResult")
    private Collection<FaitReference> faitReferenceCollection;
    @JoinColumn(name = "ID_SEARCH", referencedColumnName = "ID_SEARCH")
    @ManyToOne
    private Search idSearch;

    public Searchresults() {
    }

    public Searchresults(Integer idSearchResult) {
        this.idSearchResult = idSearchResult;
    }

    public Integer getIdSearchResult() {
        return idSearchResult;
    }

    public void setIdSearchResult(Integer idSearchResult) {
        this.idSearchResult = idSearchResult;
    }

    public String getEmailResult() {
        return emailResult;
    }

    public void setEmailResult(String emailResult) {
        this.emailResult = emailResult;
    }

    @XmlTransient
    public Collection<FaitReference> getFaitReferenceCollection() {
        return faitReferenceCollection;
    }

    public void setFaitReferenceCollection(Collection<FaitReference> faitReferenceCollection) {
        this.faitReferenceCollection = faitReferenceCollection;
    }

    public Search getIdSearch() {
        return idSearch;
    }

    public void setIdSearch(Search idSearch) {
        this.idSearch = idSearch;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idSearchResult != null ? idSearchResult.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Searchresults)) {
            return false;
        }
        Searchresults other = (Searchresults) object;
        if ((this.idSearchResult == null && other.idSearchResult != null) || (this.idSearchResult != null && !this.idSearchResult.equals(other.idSearchResult))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "conf.Searchresults[ idSearchResult=" + idSearchResult + " ]";
    }
    
}
