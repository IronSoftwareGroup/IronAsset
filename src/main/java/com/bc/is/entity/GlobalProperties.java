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
@Table(name = "global_properties")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GlobalProperties.findAll", query = "SELECT g FROM GlobalProperties g"),
    @NamedQuery(name = "GlobalProperties.findBySection", query = "SELECT g FROM GlobalProperties g WHERE g.globalPropertiesPK.section = :section"),
    @NamedQuery(name = "GlobalProperties.findByEntry", query = "SELECT g FROM GlobalProperties g WHERE g.globalPropertiesPK.entry = :entry"),
    @NamedQuery(name = "GlobalProperties.findByValue", query = "SELECT g FROM GlobalProperties g WHERE g.value = :value"),
    @NamedQuery(name = "GlobalProperties.findByDescription", query = "SELECT g FROM GlobalProperties g WHERE g.description = :description")})
public class GlobalProperties implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected GlobalPropertiesPK globalPropertiesPK;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 256)
    @Column(name = "value")
    private String value;
    @Size(max = 256)
    @Column(name = "description")
    private String description;

    public GlobalProperties() {
    }

    public GlobalProperties(GlobalPropertiesPK globalPropertiesPK) {
        this.globalPropertiesPK = globalPropertiesPK;
    }

    public GlobalProperties(GlobalPropertiesPK globalPropertiesPK, String value) {
        this.globalPropertiesPK = globalPropertiesPK;
        this.value = value;
    }

    public GlobalProperties(String section, String entry) {
        this.globalPropertiesPK = new GlobalPropertiesPK(section, entry);
    }

    public GlobalPropertiesPK getGlobalPropertiesPK() {
        return globalPropertiesPK;
    }

    public void setGlobalPropertiesPK(GlobalPropertiesPK globalPropertiesPK) {
        this.globalPropertiesPK = globalPropertiesPK;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
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
        hash += (globalPropertiesPK != null ? globalPropertiesPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GlobalProperties)) {
            return false;
        }
        GlobalProperties other = (GlobalProperties) object;
        if ((this.globalPropertiesPK == null && other.globalPropertiesPK != null) || (this.globalPropertiesPK != null && !this.globalPropertiesPK.equals(other.globalPropertiesPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bc.is.entity.GlobalProperties[ globalPropertiesPK=" + globalPropertiesPK + " ]";
    }
    
}
