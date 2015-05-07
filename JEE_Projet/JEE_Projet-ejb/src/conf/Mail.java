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
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Stef
 */
@Entity
@Table(name = "mail")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Mail.findAll", query = "SELECT m FROM Mail m"),
    @NamedQuery(name = "Mail.findByIdMail", query = "SELECT m FROM Mail m WHERE m.idMail = :idMail"),
    @NamedQuery(name = "Mail.findByObjet", query = "SELECT m FROM Mail m WHERE m.objet = :objet"),
    @NamedQuery(name = "Mail.findByDistributed", query = "SELECT m FROM Mail m WHERE m.distributed = :distributed"),
    @NamedQuery(name = "Mail.findByStatut", query = "SELECT m FROM Mail m WHERE m.statut = :statut"),
    @NamedQuery(name = "Mail.findByMailjetId", query = "SELECT m FROM Mail m WHERE m.mailjetId = :mailjetId")})
public class Mail implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_MAIL")
    private Integer idMail;
    @Size(max = 40)
    @Column(name = "OBJET")
    private String objet;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 16777215)
    @Column(name = "MESSAGE")
    private String message;
    @Column(name = "DISTRIBUTED")
    private Boolean distributed;
    @Size(max = 40)
    @Column(name = "STATUT")
    private String statut;
    @Column(name = "MAILJET_ID")
    private Integer mailjetId;
    @OneToMany(mappedBy = "idMail")
    private Collection<File> fileCollection;
    @OneToMany(mappedBy = "idMail")
    private Collection<FaitReference> faitReferenceCollection;

    public Mail() {
    }

    public Mail(Integer idMail) {
        this.idMail = idMail;
    }

    public Mail(Integer idMail, String message) {
        this.idMail = idMail;
        this.message = message;
    }

    public Integer getIdMail() {
        return idMail;
    }

    public void setIdMail(Integer idMail) {
        this.idMail = idMail;
    }

    public String getObjet() {
        return objet;
    }

    public void setObjet(String objet) {
        this.objet = objet;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getDistributed() {
        return distributed;
    }

    public void setDistributed(Boolean distributed) {
        this.distributed = distributed;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public Integer getMailjetId() {
        return mailjetId;
    }

    public void setMailjetId(Integer mailjetId) {
        this.mailjetId = mailjetId;
    }

    @XmlTransient
    public Collection<File> getFileCollection() {
        return fileCollection;
    }

    public void setFileCollection(Collection<File> fileCollection) {
        this.fileCollection = fileCollection;
    }

    @XmlTransient
    public Collection<FaitReference> getFaitReferenceCollection() {
        return faitReferenceCollection;
    }

    public void setFaitReferenceCollection(Collection<FaitReference> faitReferenceCollection) {
        this.faitReferenceCollection = faitReferenceCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMail != null ? idMail.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Mail)) {
            return false;
        }
        Mail other = (Mail) object;
        if ((this.idMail == null && other.idMail != null) || (this.idMail != null && !this.idMail.equals(other.idMail))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "conf.Mail[ idMail=" + idMail + " ]";
    }
    
}
