/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bc.is.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author bruno
 */
@Embeddable
public class GlobalPropertiesPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "section")
    private String section;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "entry")
    private String entry;

    public GlobalPropertiesPK() {
    }

    public GlobalPropertiesPK(String section, String entry) {
        this.section = section;
        this.entry = entry;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getEntry() {
        return entry;
    }

    public void setEntry(String entry) {
        this.entry = entry;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (section != null ? section.hashCode() : 0);
        hash += (entry != null ? entry.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GlobalPropertiesPK)) {
            return false;
        }
        GlobalPropertiesPK other = (GlobalPropertiesPK) object;
        if ((this.section == null && other.section != null) || (this.section != null && !this.section.equals(other.section))) {
            return false;
        }
        if ((this.entry == null && other.entry != null) || (this.entry != null && !this.entry.equals(other.entry))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bc.is.entity.GlobalPropertiesPK[ section=" + section + ", entry=" + entry + " ]";
    }
    
}
