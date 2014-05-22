package com.compsys.dao;

import com.compsys.entity.cache.IntlPorts;
import com.compsys.dao.impl.AbstractDaoImpl;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author lavan chandra
 */

public class IntlPortsDao extends AbstractDaoImpl<IntlPorts,String>{

    public IntlPortsDao(Class<IntlPorts> entityClass) {
        super(entityClass);
    }

   /* @Cacheable(value = "intlPortsCache")
    public List<IntlPorts> getEmployees() {
        System.out.println("Here the request goes out++++++++++++++++++++++++");
        final List<IntlPorts> list = getCurrentSession().createQuery("from  IntlPorts").list();
        System.out.println("Here the request comes back++++++++++++++++++++++++");
        return list;
    }

    @Cacheable(value = "intlPortsCache", key = "#forPortName")
    public IntlPorts getEmployee(String name, List<IntlPorts> ports) {
        System.out.println("*** Get the employee using name : " + name + " ***");
        IntlPorts emp = null;
        for (IntlPorts employee : ports) {
            if (employee.getForPortName().equalsIgnoreCase(name)) {
                emp = employee;
            }
        }
        return emp;
    }*/
}
