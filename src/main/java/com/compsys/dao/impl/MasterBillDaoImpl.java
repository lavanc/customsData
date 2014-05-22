package com.compsys.dao.impl;

import com.compsys.dao.MasterBillDao;
import com.compsys.entity.MasterBill;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Lavan chandra
 */
@Repository
public class MasterBillDaoImpl extends AbstractDaoImpl<MasterBill, String> implements MasterBillDao {

    protected MasterBillDaoImpl() {
        super(MasterBill.class);
    }

    protected MasterBillDaoImpl(Class<MasterBill> entityClass) {
        super(entityClass);
    }

    @Override
    public List<MasterBill> getMasterBillByPageNumber(int page, int pageSize) {

        //Not using this page this is just a hook for the future if need be.
        int pageNumber = page;

        final Session session = getCurrentSession();
        List<MasterBill> masterBills = null;
        try {

            session.beginTransaction();
            Criteria criteria = session.createCriteria(MasterBill.class);
            criteria.setProjection(Projections.rowCount());
            Integer integer = (Integer) criteria.uniqueResult();

            try {
                pageNumber = integer / 100;
                System.out.println("Page Number Calculated is :" + pageNumber);
            } catch (Throwable th) {
                System.out.println("Error occurred while calculating page number:" + th);
            }

            criteria.setFirstResult((pageNumber - 1) * pageSize);
            criteria.setMaxResults(pageSize);
            masterBills = (List<MasterBill>) criteria.list();

            if (masterBills != null) {
                System.out.println("Total Results size:" + masterBills.size());
            }

            session.getTransaction().commit();

        } catch (HibernateException e) {

            e.printStackTrace();

            session.getTransaction().rollback();

        }
        return masterBills;

    }

    @Override
    public List<MasterBill> getAllMasterBills() {
        System.out.println("Here the request goes out++++++++++++++++++++++++");
        final List<MasterBill> list = getCurrentSession().createQuery("from  MasterBill").list();
        System.out.println("Here the request comes back++++++++++++++++++++++++");
        return list;
    }

    @Override
    public List<MasterBill> getAllMasterBills(MasterBill masterBill) {

        final String masterBillLadingNumber = capitalize(masterBill.getMasterBillLadingNumber());
        final String houseBillLadingNumber = capitalize(masterBill.getHouseBillLadingNumber());
        final String voyageNumber = capitalize(masterBill.getVoyageNumber());
        final String misc = capitalize(masterBill.getMisc());

        final String hql = "FROM MasterBill E WHERE TRIM(E.masterBillLadingNumber) =" + "'" + masterBillLadingNumber + "' or TRIM(E.houseBillLadingNumber)=" + "'" + houseBillLadingNumber + "' or TRIM(E.voyageNumber)=" + "'" + voyageNumber + "' or TRIM(E.misc)=" + "'" + misc + "'";
        Query query = getCurrentSession().createQuery(hql);
        List results = query.list();
        return results;



    }

    private String capitalize(String r) {
        return r == null ? "" : r.toUpperCase();
    }
}
