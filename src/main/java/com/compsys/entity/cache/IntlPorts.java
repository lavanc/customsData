package com.compsys.entity.cache;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Lavan chandra
 */

public class IntlPorts {


    private String forPortName;


    private String countryName;

    private String forPortCd;

    public String getForPortName() {
        return forPortName;
    }

    public void setForPortName(String forPortName) {
        this.forPortName = forPortName;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getForPortCd() {
        return forPortCd;
    }

    public void setForPortCd(String forPortCd) {
        this.forPortCd = forPortCd;
    }
}
