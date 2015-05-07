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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Stef
 */
@Entity
@Table(name = "row_campaign")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RowCampaign.findAll", query = "SELECT r FROM RowCampaign r"),
    @NamedQuery(name = "RowCampaign.findByIdRow", query = "SELECT r FROM RowCampaign r WHERE r.idRow = :idRow"),
    @NamedQuery(name = "RowCampaign.findByIdCampaign", query = "SELECT r FROM RowCampaign r WHERE r.idCampaign = :idCampaign"),
    @NamedQuery(name = "RowCampaign.findByIdMail", query = "SELECT r FROM RowCampaign r WHERE r.idMail = :idMail")})
public class RowCampaign implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_row")
    private Integer idRow;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_campaign")
    private int idCampaign;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_mail")
    private int idMail;
    @JoinColumn(name = "id_row", referencedColumnName = "ID_MAILING", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private MailingCampaign mailingCampaign;

    public RowCampaign() {
    }

    public RowCampaign(Integer idRow) {
        this.idRow = idRow;
    }

    public RowCampaign(Integer idRow, int idCampaign, int idMail) {
        this.idRow = idRow;
        this.idCampaign = idCampaign;
        this.idMail = idMail;
    }

    public Integer getIdRow() {
        return idRow;
    }

    public void setIdRow(Integer idRow) {
        this.idRow = idRow;
    }

    public int getIdCampaign() {
        return idCampaign;
    }

    public void setIdCampaign(int idCampaign) {
        this.idCampaign = idCampaign;
    }

    public int getIdMail() {
        return idMail;
    }

    public void setIdMail(int idMail) {
        this.idMail = idMail;
    }

    public MailingCampaign getMailingCampaign() {
        return mailingCampaign;
    }

    public void setMailingCampaign(MailingCampaign mailingCampaign) {
        this.mailingCampaign = mailingCampaign;
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
        if (!(object instanceof RowCampaign)) {
            return false;
        }
        RowCampaign other = (RowCampaign) object;
        if ((this.idRow == null && other.idRow != null) || (this.idRow != null && !this.idRow.equals(other.idRow))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "conf.RowCampaign[ idRow=" + idRow + " ]";
    }
    
}
