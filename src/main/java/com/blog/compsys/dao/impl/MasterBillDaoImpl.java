package com.blog.compsys.dao.impl;

import com.blog.compsys.dao.MasterBillDao;
import com.blog.compsys.entity.MasterBill;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Lavan chandra
 */
@Repository
public class MasterBillDaoImpl extends AbstractDaoImpl<MasterBill,String> implements MasterBillDao {

    protected MasterBillDaoImpl() {
        super(MasterBill.class);
    }
    protected MasterBillDaoImpl(Class<MasterBill> entityClass) {
        super(entityClass);
    }

    @Override
    public List<MasterBill> getMasterBillByPageNumber(int page) {
        return null;
    }

    @Override
    public List<MasterBill> getAllMasterBills() {
        System.out.println("Here the request goes out++++++++++++++++++++++++");
        final List<MasterBill> list = getCurrentSession().createQuery("from  MasterBill").list();
        System.out.println("Here the request comes back++++++++++++++++++++++++");

        for(MasterBill masterBill : list) {
            System.out.println("HOUSE BILL LADING NUMBER--------"+masterBill.getHouseBillLadingNumber());
            System.out.println("MASTER BILL LADING NUMBER--------"+masterBill.getMasterBillLadingNumber());
            System.out.println("VOYAGE NUMBER--------"+masterBill.getVoyageNumber());
            System.out.println("MISC--------"+masterBill.getMisc());
        }

        return list;

    }

    @Override
    public List<MasterBill> getAllMasterBills(String masterBillIdentifier) {
        return findByCriteria(Restrictions.eqProperty("masterBillLandingNumber",masterBillIdentifier));
    }


}
