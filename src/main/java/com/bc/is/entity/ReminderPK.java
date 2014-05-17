package com.bc.is.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author bruno
 */
@Embeddable
public class ReminderPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "asset_id")
    private int assetId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "sequence")
    private int sequence;

    public ReminderPK() {
    }

    public ReminderPK(int assetId, int sequence) {
        this.assetId = assetId;
        this.sequence = sequence;
    }

    public int getAssetId() {
        return assetId;
    }

    public void setAssetId(int assetId) {
        this.assetId = assetId;
    }

    public int getSequence() {
        return sequence;
    }

    public void setSequence(int sequence) {
        this.sequence = sequence;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) assetId;
        hash += (int) sequence;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ReminderPK)) {
            return false;
        }
        ReminderPK other = (ReminderPK) object;
        if (this.assetId != other.assetId) {
            return false;
        }
        if (this.sequence != other.sequence) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bc.is.entity.ReminderPK[ assetId=" + assetId + ", sequence=" + sequence + " ]";
    }
    
}
