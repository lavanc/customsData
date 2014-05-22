package com.compsys.service.impl;

import com.compsys.dao.MasterBillDao;
import com.compsys.entity.MasterBill;
import com.compsys.service.MasterBillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.logging.Logger;

/**
 * @author Lavan chandra
 */
@Service("masterBillService")
@Transactional(readOnly = true)
public class MasterBillServiceImpl implements MasterBillService {

    private static final Logger logger = Logger.getLogger(MasterBillServiceImpl.class.getName());


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
    public List<MasterBill> findMasterBills(MasterBill masterBill) {
        return masterBillDao.getAllMasterBills(masterBill);
    }

    @Override
    public List<MasterBill> findMasterBills() {
        logger.info("MasterBillService : findMasterBills is called");
        return masterBillDao.getAllMasterBills();
    }

    @Override
    public List<MasterBill> findMasterBills(int pageNumber, int pageSize) {
        logger.info("MasterBillService : findMasterBills is called with pageNumber:" + pageNumber + "with pageSize:" + pageSize);
        return masterBillDao.getMasterBillByPageNumber(pageNumber, pageSize);
    }
}
