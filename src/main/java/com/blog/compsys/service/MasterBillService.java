package com.blog.compsys.service;

import com.blog.compsys.entity.MasterBill;


import java.util.List;

/**
 * @author Lavan chandra
 */
public interface MasterBillService {

    MasterBill findByMasterBillNumber(String masterBillIdentifier);
    void saveMasterBill(MasterBill masterBill);
    void delete(String masterBillIdentifier);
    List<MasterBill> findMasterBills(String masterBillIdentifier);
    List<MasterBill> findMasterBills();
}
