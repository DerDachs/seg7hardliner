/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hspf.hardliner.model;

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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author dachs
 */
@Entity
@Table(name = "BERICHT", catalog = "", schema = "MEUSER")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Bericht.findAll", query = "SELECT b FROM Bericht b")
    , @NamedQuery(name = "Bericht.findByBerichtid", query = "SELECT b FROM Bericht b WHERE b.berichtid = :berichtid")
    , @NamedQuery(name = "Bericht.findByDatum", query = "SELECT b FROM Bericht b WHERE b.datum = :datum")
    , @NamedQuery(name = "Bericht.findByGang", query = "SELECT b FROM Bericht b WHERE b.gang = :gang")
    , @NamedQuery(name = "Bericht.findByName", query = "SELECT b FROM Bericht b WHERE b.name = :name")
    , @NamedQuery(name = "Bericht.findByStatus", query = "SELECT b FROM Bericht b WHERE b.status = :status")})
public class Bericht implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    @Column(name = "BERICHTID", nullable = false)
    private Long berichtid;
    @Size(max = 255)
    @Column(name = "DATUM", length = 255)
    private String datum;
    @Column(name = "GANG")
    private Integer gang;
    @Size(max = 255)
    @Column(name = "NAME", length = 255)
    private String name;
    @Column(name = "STATUS")
    private Boolean status;
    @JoinColumn(name = "FK_FILIALE", referencedColumnName = "FILIALID")
    @ManyToOne
    private Filiale fkFiliale;

    public Bericht() {
    }

    public Bericht(Long berichtid) {
        this.berichtid = berichtid;
    }

    public Long getBerichtid() {
        return berichtid;
    }

    public void setBerichtid(Long berichtid) {
        this.berichtid = berichtid;
    }

    public String getDatum() {
        return datum;
    }

    public void setDatum(String datum) {
        this.datum = datum;
    }

    public Integer getGang() {
        return gang;
    }

    public void setGang(Integer gang) {
        this.gang = gang;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Filiale getFkFiliale() {
        return fkFiliale;
    }

    public void setFkFiliale(Filiale fkFiliale) {
        this.fkFiliale = fkFiliale;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (berichtid != null ? berichtid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Bericht)) {
            return false;
        }
        Bericht other = (Bericht) object;
        if ((this.berichtid == null && other.berichtid != null) || (this.berichtid != null && !this.berichtid.equals(other.berichtid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "de.hspf.hardliner.model.Bericht[ berichtid=" + berichtid + " ]";
    }
    
}
