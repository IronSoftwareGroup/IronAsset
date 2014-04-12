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
public class LovPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "subject")
    private String subject;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "entry")
    private String entry;

    public LovPK() {
    }

    public LovPK(String subject, String entry) {
        this.subject = subject;
        this.entry = entry;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
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
        hash += (subject != null ? subject.hashCode() : 0);
        hash += (entry != null ? entry.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LovPK)) {
            return false;
        }
        LovPK other = (LovPK) object;
        if ((this.subject == null && other.subject != null) || (this.subject != null && !this.subject.equals(other.subject))) {
            return false;
        }
        if ((this.entry == null && other.entry != null) || (this.entry != null && !this.entry.equals(other.entry))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bc.is.entity.LovPK[ subject=" + subject + ", entry=" + entry + " ]";
    }
    
}
