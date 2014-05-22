package com.compsys.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Lavan chandra
 */
@Entity
@Table(name="MASTERBILL")
public class MasterBill {

    @Id
    @Column(name = "MASTERBILLLADINGNUMBER", nullable = false)
    private String masterBillLadingNumber;
    @Column(name = "HOUSEBILLLADINGNUMBER", nullable = false)
    private String houseBillLadingNumber;
    @Column(name = "VOYAGENUMBER", nullable = false)
    private String voyageNumber;
    @Column(name = "MISC", nullable = false)
    private String misc;

    public String getMasterBillLadingNumber() {
        return masterBillLadingNumber;
    }

    public void setMasterBillLadingNumber(String masterBillLadingNumber) {
        this.masterBillLadingNumber = masterBillLadingNumber;
    }

    public String getHouseBillLadingNumber() {
        return houseBillLadingNumber;
    }

    public void setHouseBillLadingNumber(String houseBillLadingNumber) {
        this.houseBillLadingNumber = houseBillLadingNumber;
    }

    public String getVoyageNumber() {
        return voyageNumber;
    }

    public void setVoyageNumber(String voyageNumber) {
        this.voyageNumber = voyageNumber;
    }

    public String getMisc() {
        return misc;
    }

    public void setMisc(String misc) {
        this.misc = misc;
    }
}
