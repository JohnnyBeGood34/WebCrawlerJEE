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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Stef
 */
@Entity
@Table(name = "file")
@XmlRootElement
@NamedQueries(
  {
    @NamedQuery(name = "File.findAll", query = "SELECT f FROM FileMail f"),
    @NamedQuery(name = "File.findByIdFile", query = "SELECT f FROM FileMail f WHERE f.idFile = :idFile"),
    @NamedQuery(name = "File.findByPath", query = "SELECT f FROM FileMail f WHERE f.path = :path"),
    @NamedQuery(name = "File.findByIsInBody", query = "SELECT f FROM FileMail f WHERE f.isInBody = :isInBody"),
    @NamedQuery(name = "File.finByIdMail", query = "SELECT f FROM FileMail f WHERE f.idMail = :idMail")
  })
public class FileMail implements Serializable
  {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_FILE")
    private Integer idFile;
    @Size(max = 80)
    @Column(name = "PATH")
    private String path;
    @Column(name = "IS_IN_BODY")
    private Boolean isInBody;
    @JoinColumn(name = "ID_MAIL", referencedColumnName = "ID_MAIL")
    @ManyToOne
    private Mail idMail;

    public FileMail()
      {
      }

    public FileMail(Integer idFile)
      {
        this.idFile = idFile;
      }

    public Integer getIdFile()
      {
        return idFile;
      }

    public void setIdFile(Integer idFile)
      {
        this.idFile = idFile;
      }

    public String getPath()
      {
        return path;
      }

    public void setPath(String path)
      {
        this.path = path;
      }

    public Boolean getIsInBody()
      {
        return isInBody;
      }

    public void setIsInBody(Boolean isInBody)
      {
        this.isInBody = isInBody;
      }

    public Mail getIdMail()
      {
        return idMail;
      }

    public void setIdMail(Mail idMail)
      {
        this.idMail = idMail;
      }

    @Override
    public int hashCode()
      {
        int hash = 0;
        hash += (idFile != null ? idFile.hashCode() : 0);
        return hash;
      }

    @Override
    public boolean equals(Object object)
      {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FileMail))
          {
            return false;
          }
        FileMail other = (FileMail) object;
        if ((this.idFile == null && other.idFile != null) || (this.idFile != null && !this.idFile.equals(other.idFile)))
          {
            return false;
          }
        return true;
      }

    @Override
    public String toString()
      {
        return "conf.File[ idFile=" + idFile + " ]";
      }

  }
