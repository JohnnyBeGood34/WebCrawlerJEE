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
import javax.persistence.CascadeType;
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
@Table(name = "mailing_campaign")
@XmlRootElement
@NamedQueries(
  {
    @NamedQuery(name = "MailingCampaign.findAll", query = "SELECT m FROM MailingCampaign m"),
    @NamedQuery(name = "MailingCampaign.findByIdMailing", query = "SELECT m FROM MailingCampaign m WHERE m.idMailing = :idMailing"),
    @NamedQuery(name = "MailingCampaign.findByIdUser", query = "SELECT m FROM MailingCampaign m WHERE m.idUser = :idUser"),
    @NamedQuery(name = "MailingCampaign.findByTitle", query = "SELECT m FROM MailingCampaign m WHERE m.title = :title"),
    @NamedQuery(name = "MailingCampaign.findByLangue", query = "SELECT m FROM MailingCampaign m WHERE m.langue = :langue"),
    @NamedQuery(name = "MailingCampaign.findByDateEnvoi", query = "SELECT m FROM MailingCampaign m WHERE m.dateEnvoi = :dateEnvoi"),
    @NamedQuery(name = "MailingCampaign.findByIdSearch", query = "SELECT m FROM MailingCampaign m WHERE m.idSearch = :idSearch")
  })
public class MailingCampaign implements Serializable
  {
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "campaignId")
    private Collection<Mail> mailCollection;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_MAILING")
    private Integer idMailing;
    @Column(name = "ID_USER")
    private Integer idUser;
    @Size(max = 80)
    @Column(name = "TITLE")
    private String title;
    @Size(max = 5)
    @Column(name = "LANGUE")
    private String langue;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DATE_ENVOI")
    @Temporal(TemporalType.DATE)
    private Date dateEnvoi;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_SEARCH")
    private int idSearch;

    public MailingCampaign()
      {
      }

    public MailingCampaign(Integer idMailing)
      {
        this.idMailing = idMailing;
      }

    public MailingCampaign(Integer idMailing, Date dateEnvoi, int idSearch)
      {
        this.idMailing = idMailing;
        this.dateEnvoi = dateEnvoi;
        this.idSearch = idSearch;
      }

    public Integer getIdMailing()
      {
        return idMailing;
      }

    public void setIdMailing(Integer idMailing)
      {
        this.idMailing = idMailing;
      }

    public Integer getIdUser()
      {
        return idUser;
      }

    public void setIdUser(Integer idUser)
      {
        this.idUser = idUser;
      }

    public String getTitle()
      {
        return title;
      }

    public void setTitle(String title)
      {
        this.title = title;
      }

    public String getLangue()
      {
        return langue;
      }

    public void setLangue(String langue)
      {
        this.langue = langue;
      }

    public Date getDateEnvoi()
      {
        return dateEnvoi;
      }

    public void setDateEnvoi(Date dateEnvoi)
      {
        this.dateEnvoi = dateEnvoi;
      }

    public int getIdSearch()
      {
        return idSearch;
      }

    public void setIdSearch(int idSearch)
      {
        this.idSearch = idSearch;
      }

    @Override
    public int hashCode()
      {
        int hash = 0;
        hash += (idMailing != null ? idMailing.hashCode() : 0);
        return hash;
      }

    @Override
    public boolean equals(Object object)
      {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MailingCampaign))
          {
            return false;
          }
        MailingCampaign other = (MailingCampaign) object;
        if ((this.idMailing == null && other.idMailing != null) || (this.idMailing != null && !this.idMailing.equals(other.idMailing)))
          {
            return false;
          }
        return true;
      }

    @Override
    public String toString()
      {
        return "conf.MailingCampaign[ idMailing=" + idMailing + " ]";
      }

    @XmlTransient
    public Collection<Mail> getMailCollection()
      {
        return mailCollection;
      }

    public void setMailCollection(Collection<Mail> mailCollection)
      {
        this.mailCollection = mailCollection;
      }
    
  }
