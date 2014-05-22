package com.compsys.dao;

import com.compsys.entity.MasterBill;

import java.util.List;

/**
 * @author Lavan chandra
 */
public interface MasterBillDao {

    List<MasterBill> getMasterBillByPageNumber(int page,int pageSize);

    List<MasterBill> getAllMasterBills();

    List<MasterBill> getAllMasterBills(MasterBill masterBillIdentifier);

    void delete(MasterBill masterBill);
}
