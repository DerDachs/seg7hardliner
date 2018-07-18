/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hspf.hardliner.model;

import java.io.Serializable;
import java.util.Collection;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.codehaus.jackson.annotate.JsonIgnore;

/**
 *
 * @author dachs
 */
@Entity
@Table(name = "FILIALE", catalog = "", schema = "MEUSER")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Filiale.findAll", query = "SELECT f FROM Filiale f")
    , @NamedQuery(name = "Filiale.findByFilialid", query = "SELECT f FROM Filiale f WHERE f.filialid = :filialid")
    , @NamedQuery(name = "Filiale.findByFilialname", query = "SELECT f FROM Filiale f WHERE f.filialname = :filialname")
    , @NamedQuery(name = "Filiale.findByBundesland", query = "SELECT f FROM Filiale f WHERE f.bundesland = :bundesland")
    , @NamedQuery(name = "Filiale.findByRegion", query = "SELECT f FROM Filiale f WHERE f.region = :region")
    , @NamedQuery(name = "Filiale.findAllDistinct", query = "select DISTINCT(f.bundesland) from Filiale f")
    , @NamedQuery(name = "Filiale.findRegionDistinct", query = "SELECT DISTINCT(f.region) from Filiale f WHERE f.bundesland = :bundesland")
    , @NamedQuery(name = "Filiale.findFilialeDistinct", query = "SELECT DISTINCT(f.filialname) from Filiale f WHERE f.region = :region")})
public class Filiale implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    @Column(name = "FILIALID", nullable = false)
    private Long filialid;
    @Size(max = 100)
    @Column(name = "FILIALNAME", length = 100)
    private String filialname;
    @Size(max = 100)
    @Column(name = "BUNDESLAND", length = 100)
    private String bundesland;
    @Size(max = 100)
    @Column(name = "REGION", length = 100)
    private String region;
    @OneToMany(mappedBy = "fkFiliale")
    private Collection<Bericht> berichtCollection;

    public Filiale() {
    }

    public Filiale(Long filialid) {
        this.filialid = filialid;
    }

    public Long getFilialid() {
        return filialid;
    }

    public void setFilialid(Long filialid) {
        this.filialid = filialid;
    }

    public String getFilialname() {
        return filialname;
    }

    public void setFilialname(String filialname) {
        this.filialname = filialname;
    }

    public String getBundesland() {
        return bundesland;
    }

    public void setBundesland(String bundesland) {
        this.bundesland = bundesland;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    @XmlTransient
    @JsonIgnore
    public Collection<Bericht> getBerichtCollection() {
        return berichtCollection;
    }

    public void setBerichtCollection(Collection<Bericht> berichtCollection) {
        this.berichtCollection = berichtCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (filialid != null ? filialid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Filiale)) {
            return false;
        }
        Filiale other = (Filiale) object;
        if ((this.filialid == null && other.filialid != null) || (this.filialid != null && !this.filialid.equals(other.filialid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "de.hspf.hardliner.model.Filiale[ filialid=" + filialid + " ]";
    }
    
}
