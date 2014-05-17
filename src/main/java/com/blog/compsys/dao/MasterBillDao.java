package com.blog.compsys.dao;

import com.blog.compsys.entity.MasterBill;

import java.util.List;

/**
 * @author Lavan chandra
 */
public interface MasterBillDao {

    List<MasterBill> getMasterBillByPageNumber(int page);

    List<MasterBill> getAllMasterBills();

    List<MasterBill> getAllMasterBills(String masterBillIdentifier);

    void delete(MasterBill masterBill);
}
