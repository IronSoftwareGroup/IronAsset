/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bc.is.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author bruno
 */
@Entity
@Table(name = "lov")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Lov.findAll", query = "SELECT l FROM Lov l"),
    @NamedQuery(name = "Lov.findBySubject", query = "SELECT l FROM Lov l WHERE l.lovPK.subject = :subject"),
    @NamedQuery(name = "Lov.findByEntry", query = "SELECT l FROM Lov l WHERE l.lovPK.entry = :entry"),
    @NamedQuery(name = "Lov.findByDescription", query = "SELECT l FROM Lov l WHERE l.description = :description")})
public class Lov implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected LovPK lovPK;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 256)
    @Column(name = "description")
    private String description;

    public Lov() {
    }

    public Lov(LovPK lovPK) {
        this.lovPK = lovPK;
    }

    public Lov(LovPK lovPK, String description) {
        this.lovPK = lovPK;
        this.description = description;
    }

    public Lov(String subject, String entry) {
        this.lovPK = new LovPK(subject, entry);
    }

    public LovPK getLovPK() {
        return lovPK;
    }

    public void setLovPK(LovPK lovPK) {
        this.lovPK = lovPK;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (lovPK != null ? lovPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Lov)) {
            return false;
        }
        Lov other = (Lov) object;
        if ((this.lovPK == null && other.lovPK != null) || (this.lovPK != null && !this.lovPK.equals(other.lovPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bc.is.entity.Lov[ lovPK=" + lovPK + " ]";
    }
    
}
