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
@Table(name = "BERICHT")
@XmlRootElement
public class Bericht implements Serializable {
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "BERICHTID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Size(max = 100)
    @Column(name = "NAME")
    private String name;
    @Size(max = 100)
    @Column(name = "DATUM")
    private String date;
    @Size(max = 100)
    @Column(name = "GANG")
    private int gang;
    @Size(max = 100)
    @Column(name = "FILIALE")
    private String Filiale;
    
    public Bericht(){
        
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getGang() {
        return gang;
    }

    public void setGang(int gang) {
        this.gang = gang;
    }

    public String getFiliale() {
        return Filiale;
    }

    public void setFiliale(String Filiale) {
        this.Filiale = Filiale;
    }
        
}
