package com.compsys.service;

import com.compsys.entity.MasterBill;


import java.util.List;

/**
 * @author Lavan chandra
 */
public interface MasterBillService {

    MasterBill findByMasterBillNumber(String masterBillIdentifier);
    void saveMasterBill(MasterBill masterBill);
    void delete(String masterBillIdentifier);
    List<MasterBill> findMasterBills(MasterBill masterBill);
    List<MasterBill> findMasterBills(int pageNumber,int pageSize);
    List<MasterBill> findMasterBills();
}
