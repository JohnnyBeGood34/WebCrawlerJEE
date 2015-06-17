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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author JOHN
 */
@Entity
@Table(name = "user")
@XmlRootElement
@NamedQueries(
  {
    @NamedQuery(name = "User.findAll", query = "SELECT u FROM User u"),
    @NamedQuery(name = "User.findByIdUser", query = "SELECT u FROM User u WHERE u.idUser = :idUser"),
    @NamedQuery(name = "User.findByName", query = "SELECT u FROM User u WHERE u.name = :name"),
    @NamedQuery(name = "User.findByFirstname", query = "SELECT u FROM User u WHERE u.firstname = :firstname"),
    @NamedQuery(name = "User.findByEmail", query = "SELECT u FROM User u WHERE u.email = :email"),
    @NamedQuery(name = "User.findByPass", query = "SELECT u FROM User u WHERE u.pass = :pass"),
    @NamedQuery(name = "User.findByDenied", query = "SELECT u FROM User u WHERE u.denied = :denied")
  })
public class User implements Serializable
  {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_USER")
    private Integer idUser;
    @Size(max = 40)
    @Column(name = "NAME")
    private String name;
    @Size(max = 40)
    @Column(name = "FIRSTNAME")
    private String firstname;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 50)
    @Column(name = "EMAIL")
    private String email;
    @Size(max = 32)
    @Column(name = "PASS")
    private String pass;
    @Column(name = "denied")
    private Boolean denied;

    public User()
      {
      }

    public User(Integer idUser)
      {
        this.idUser = idUser;
      }

    public Integer getIdUser()
      {
        return idUser;
      }

    public void setIdUser(Integer idUser)
      {
        this.idUser = idUser;
      }

    public String getName()
      {
        return name;
      }

    public void setName(String name)
      {
        this.name = name;
      }

    public String getFirstname()
      {
        return firstname;
      }

    public void setFirstname(String firstname)
      {
        this.firstname = firstname;
      }

    public String getEmail()
      {
        return email;
      }

    public void setEmail(String email)
      {
        this.email = email;
      }

    public String getPass()
      {
        return pass;
      }

    public void setPass(String pass)
      {
        this.pass = pass;
      }

    public Boolean getDenied()
      {
        return denied;
      }

    public void setDenied(Boolean denied)
      {
        this.denied = denied;
      }

    @Override
    public int hashCode()
      {
        int hash = 0;
        hash += (idUser != null ? idUser.hashCode() : 0);
        return hash;
      }

    @Override
    public boolean equals(Object object)
      {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof User))
          {
            return false;
          }
        User other = (User) object;
        if ((this.idUser == null && other.idUser != null) || (this.idUser != null && !this.idUser.equals(other.idUser)))
          {
            return false;
          }
        return true;
      }

    @Override
    public String toString()
      {
        return "conf.User[ idUser=" + idUser + " ]";
      }
    
  }
