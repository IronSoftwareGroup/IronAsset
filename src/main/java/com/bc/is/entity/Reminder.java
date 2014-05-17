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
@Table(name = "reminder")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Reminder.findAll", query = "SELECT r FROM Reminder r"),
    @NamedQuery(name = "Reminder.findByAssetId", query = "SELECT r FROM Reminder r WHERE r.reminderPK.assetId = :assetId"),
    @NamedQuery(name = "Reminder.findBySequence", query = "SELECT r FROM Reminder r WHERE r.reminderPK.sequence = :sequence"),
    @NamedQuery(name = "Reminder.findByDays", query = "SELECT r FROM Reminder r WHERE r.days = :days"),
    @NamedQuery(name = "Reminder.findBySent", query = "SELECT r FROM Reminder r WHERE r.sent = :sent")})
public class Reminder implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ReminderPK reminderPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "days")
    private int days;
    @Size(max = 3)
    @Column(name = "sent")
    private String sent;

    public Reminder() {
    }

    public Reminder(ReminderPK reminderPK) {
        this.reminderPK = reminderPK;
    }

    public Reminder(ReminderPK reminderPK, int days) {
        this.reminderPK = reminderPK;
        this.days = days;
    }

    public Reminder(int assetId, int sequence) {
        this.reminderPK = new ReminderPK(assetId, sequence);
    }

    public ReminderPK getReminderPK() {
        return reminderPK;
    }

    public void setReminderPK(ReminderPK reminderPK) {
        this.reminderPK = reminderPK;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public String getSent() {
        return sent;
    }

    public void setSent(String sent) {
        this.sent = sent;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (reminderPK != null ? reminderPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Reminder)) {
            return false;
        }
        Reminder other = (Reminder) object;
        if ((this.reminderPK == null && other.reminderPK != null) || (this.reminderPK != null && !this.reminderPK.equals(other.reminderPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bc.is.entity.Reminder[ reminderPK=" + reminderPK + " ]";
    }
    
}
