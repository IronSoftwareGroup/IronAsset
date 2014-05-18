package com.bc.is.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author bruno
 */
@Entity
@Table(name = "asset")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Asset.findAll", query = "SELECT a FROM Asset a"),
    @NamedQuery(name = "Asset.findAllByDate", query = "SELECT a FROM Asset a ORDER BY a.endDate DESC"),
    @NamedQuery(name = "Asset.countByType", query = "SELECT count(a)  FROM Asset a where a.type = :type"),
    @NamedQuery(name = "Asset.findById", query = "SELECT a FROM Asset a WHERE a.id = :id"),
    @NamedQuery(name = "Asset.findByName", query = "SELECT a FROM Asset a WHERE a.name = :name"),
    @NamedQuery(name = "Asset.findByDescription", query = "SELECT a FROM Asset a WHERE a.description = :description"),
    @NamedQuery(name = "Asset.findByType", query = "SELECT a FROM Asset a WHERE a.type = :type"),
    @NamedQuery(name = "Asset.findByType2", query = "SELECT a FROM Asset a WHERE a.type2 = :type2"),
    @NamedQuery(name = "Asset.findByType3", query = "SELECT a FROM Asset a WHERE a.type3 = :type3"),
    @NamedQuery(name = "Asset.findBySerial", query = "SELECT a FROM Asset a WHERE a.serial = :serial"),
    @NamedQuery(name = "Asset.findByWarranty", query = "SELECT a FROM Asset a WHERE a.warranty = :warranty"),
    @NamedQuery(name = "Asset.findByRequestDate", query = "SELECT a FROM Asset a WHERE a.requestDate = :requestDate"),
    @NamedQuery(name = "Asset.findByStartDate", query = "SELECT a FROM Asset a WHERE a.startDate = :startDate"),
    @NamedQuery(name = "Asset.findByEndDate", query = "SELECT a FROM Asset a WHERE a.endDate = :endDate"),
    @NamedQuery(name = "Asset.findByUnitPrice", query = "SELECT a FROM Asset a WHERE a.unitPrice = :unitPrice"),
    @NamedQuery(name = "Asset.findByTotalPrice", query = "SELECT a FROM Asset a WHERE a.totalPrice = :totalPrice"),
    @NamedQuery(name = "Asset.findByPriceMetric", query = "SELECT a FROM Asset a WHERE a.priceMetric = :priceMetric"),
    @NamedQuery(name = "Asset.findByCurrency", query = "SELECT a FROM Asset a WHERE a.currency = :currency"),
    @NamedQuery(name = "Asset.findByUnits", query = "SELECT a FROM Asset a WHERE a.units = :units"),
    @NamedQuery(name = "Asset.findByUom", query = "SELECT a FROM Asset a WHERE a.uom = :uom"),
    @NamedQuery(name = "Asset.findByVendorCompany", query = "SELECT a FROM Asset a WHERE a.vendorCompany = :vendorCompany"),
    @NamedQuery(name = "Asset.findByVendorName", query = "SELECT a FROM Asset a WHERE a.vendorName = :vendorName"),
    @NamedQuery(name = "Asset.findByVendorEmail", query = "SELECT a FROM Asset a WHERE a.vendorEmail = :vendorEmail"),
    @NamedQuery(name = "Asset.findByVendorPhone", query = "SELECT a FROM Asset a WHERE a.vendorPhone = :vendorPhone"),
    @NamedQuery(name = "Asset.findByVendorWebsite", query = "SELECT a FROM Asset a WHERE a.vendorWebsite = :vendorWebsite"),
    @NamedQuery(name = "Asset.findByNote", query = "SELECT a FROM Asset a WHERE a.note = :note")})
public class Asset implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Size(max = 60)
    @Column(name = "name")
    private String name;
    @Size(max = 256)
    @Column(name = "description")
    private String description;
    @Size(max = 60)
    @Column(name = "type")
    private String type;
    @Size(max = 60)
    @Column(name = "type2")
    private String type2;
    @Size(max = 60)
    @Column(name = "type3")
    private String type3;
    @Size(max = 120)
    @Column(name = "serial")
    private String serial;
    @Size(max = 60)
    @Column(name = "warranty")
    private String warranty;
    @Column(name = "request_date")
    @Temporal(TemporalType.DATE)
    private Date requestDate;
    @Column(name = "start_date")
    @Temporal(TemporalType.DATE)
    private Date startDate;
    @Column(name = "end_date")
    @Temporal(TemporalType.DATE)
    private Date endDate;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "unit_price")
    private Float unitPrice;
    @Column(name = "total_price")
    private Float totalPrice;
    @Size(max = 60)
    @Column(name = "price_metric")
    private String priceMetric;
    @Size(max = 3)
    @Column(name = "currency")
    private String currency;
    @Column(name = "units")
    private Float units;
    @Size(max = 10)
    @Column(name = "uom")
    private String uom;
    @Size(max = 60)
    @Column(name = "vendor_company")
    private String vendorCompany;
    @Size(max = 60)
    @Column(name = "vendor_name")
    private String vendorName;
    @Size(max = 60)
    @Column(name = "vendor_email")
    private String vendorEmail;
    @Size(max = 60)
    @Column(name = "vendor_phone")
    private String vendorPhone;
    @Size(max = 256)
    @Column(name = "vendor_website")
    private String vendorWebsite;
    @Size(max = 256)
    @Column(name = "note")
    private String note;
    @Column(name = "tot_reminder")
    private Integer totReminder;
    @Column(name = "sent_reminder")
    private Integer sentReminder;

    public Asset() {
    }

    public Asset(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType2() {
        return type2;
    }

    public void setType2(String type2) {
        this.type2 = type2;
    }

    public String getType3() {
        return type3;
    }

    public void setType3(String type3) {
        this.type3 = type3;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public String getWarranty() {
        return warranty;
    }

    public void setWarranty(String warranty) {
        this.warranty = warranty;
    }

    public Date getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(Date requestDate) {
        this.requestDate = requestDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Float getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Float unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getPriceMetric() {
        return priceMetric;
    }

    public void setPriceMetric(String priceMetric) {
        this.priceMetric = priceMetric;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Float getUnits() {
        return units;
    }

    public void setUnits(Float units) {
        this.units = units;
    }

    public String getUom() {
        return uom;
    }

    public void setUom(String uom) {
        this.uom = uom;
    }

    public String getVendorCompany() {
        return vendorCompany;
    }

    public void setVendorCompany(String vendorCompany) {
        this.vendorCompany = vendorCompany;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public String getVendorEmail() {
        return vendorEmail;
    }

    public void setVendorEmail(String vendorEmail) {
        this.vendorEmail = vendorEmail;
    }

    public String getVendorPhone() {
        return vendorPhone;
    }

    public void setVendorPhone(String vendorPhone) {
        this.vendorPhone = vendorPhone;
    }

    public String getVendorWebsite() {
        return vendorWebsite;
    }

    public void setVendorWebsite(String vendorWebsite) {
        this.vendorWebsite = vendorWebsite;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Asset)) {
            return false;
        }
        Asset other = (Asset) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bc.is.entity.Asset[ id=" + id + " ]";
    }

    public Integer getTotReminder() {
        return totReminder;
    }

    public void setTotReminder(Integer totReminder) {
        this.totReminder = totReminder;
    }

    public Integer getSentReminder() {
        return sentReminder;
    }

    public void setSentReminder(Integer sentReminder) {
        this.sentReminder = sentReminder;
    }
    
}
