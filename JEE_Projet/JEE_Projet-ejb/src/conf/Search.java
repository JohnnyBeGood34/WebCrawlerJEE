/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package conf;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author JOHN
 */
@Entity
@Table(name = "search")
@XmlRootElement
@NamedQueries(
  {
    @NamedQuery(name = "Search.findAll", query = "SELECT s FROM Search s"),
    @NamedQuery(name = "Search.findByIdSearch", query = "SELECT s FROM Search s WHERE s.idSearch = :idSearch"),
    @NamedQuery(name = "Search.findByTherm", query = "SELECT s FROM Search s WHERE s.therm = :therm"),
    @NamedQuery(name = "Search.findByDeepLevel", query = "SELECT s FROM Search s WHERE s.deepLevel = :deepLevel"),
    @NamedQuery(name = "Search.findByIsFr", query = "SELECT s FROM Search s WHERE s.isFr = :isFr"),
    @NamedQuery(name = "Search.findByDateSearch", query = "SELECT s FROM Search s WHERE s.dateSearch = :dateSearch"),
    @NamedQuery(name = "Search.findByThermAndDeepLevel", query = "SELECT s FROM Search s WHERE s.therm = :therm AND s.deepLevel = :deepLevel")
  })
public class Search implements Serializable
  {
    @OneToMany(mappedBy = "idSearch")
    private Collection<Searchresults> searchresultsCollection;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_SEARCH")
    private Integer idSearch;
    @Size(max = 40)
    @Column(name = "THERM")
    private String therm;
    @Column(name = "DEEP_LEVEL")
    private Integer deepLevel;
    @Column(name = "IS_FR")
    private Boolean isFr;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DATE_SEARCH")
    @Temporal(TemporalType.DATE)
    private Date dateSearch;

    public Search()
      {
      }

    public Search(Integer idSearch)
      {
        this.idSearch = idSearch;
      }

    public Search(Integer idSearch, Date dateSearch)
      {
        this.idSearch = idSearch;
        this.dateSearch = dateSearch;
      }

    public Integer getIdSearch()
      {
        return idSearch;
      }

    public void setIdSearch(Integer idSearch)
      {
        this.idSearch = idSearch;
      }

    public String getTherm()
      {
        return therm;
      }

    public void setTherm(String therm)
      {
        this.therm = therm;
      }

    public Integer getDeepLevel()
      {
        return deepLevel;
      }

    public void setDeepLevel(Integer deepLevel)
      {
        this.deepLevel = deepLevel;
      }

    public Boolean getIsFr()
      {
        return isFr;
      }

    public void setIsFr(Boolean isFr)
      {
        this.isFr = isFr;
      }

    public Date getDateSearch()
      {
        return dateSearch;
      }

    public void setDateSearch(Date dateSearch)
      {
        this.dateSearch = dateSearch;
      }

    @Override
    public int hashCode()
      {
        int hash = 0;
        hash += (idSearch != null ? idSearch.hashCode() : 0);
        return hash;
      }

    @Override
    public boolean equals(Object object)
      {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Search))
          {
            return false;
          }
        Search other = (Search) object;
        if ((this.idSearch == null && other.idSearch != null) || (this.idSearch != null && !this.idSearch.equals(other.idSearch)))
          {
            return false;
          }
        return true;
      }

    @Override
    public String toString()
      {
        return "conf.Search[ idSearch=" + idSearch + " ]";
      }

    @XmlTransient
    public Collection<Searchresults> getSearchresultsCollection()
      {
        return searchresultsCollection;
      }

    public void setSearchresultsCollection(Collection<Searchresults> searchresultsCollection)
      {
        this.searchresultsCollection = searchresultsCollection;
      }
    
  }
