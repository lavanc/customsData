package com.blog.compsys.service.impl;

import com.blog.compsys.dao.MasterBillDao;
import com.blog.compsys.entity.MasterBill;
import com.blog.compsys.service.MasterBillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Lavan chandra
 */
@Service("masterBillService")
@Transactional(readOnly = true)
public class MasterBillServiceImpl implements MasterBillService {

    @Autowired
    private MasterBillDao masterBillDao;

    @Override
    public MasterBill findByMasterBillNumber(String masterBillIdentifier) {
        return null;
    }

    @Override
    public void saveMasterBill(MasterBill masterBill) {

    }

    @Override
    public void delete(String masterBillIdentifier) {
        final MasterBill masterBill = new MasterBill();
        masterBill.setHouseBillLadingNumber(masterBillIdentifier);
        masterBillDao.delete(masterBill);
    }

    @Override
    public List<MasterBill> findMasterBills(String masterBillIdentifier) {
        return masterBillDao.getAllMasterBills();
    }

    @Override
    public List<MasterBill> findMasterBills() {
        return masterBillDao.getAllMasterBills();
    }
}
