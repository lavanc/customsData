package com.blog.compsys.controller;

import com.blog.compsys.entity.MasterBill;

import com.blog.compsys.service.MasterBillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * @author Lavan chandra
 */

@Controller
public class MasterBillController {

    @Autowired
    private MasterBillService masterBillService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String showMasterBillForm(Model model) {
        model.addAttribute("masterBill",new MasterBill());
        model.addAttribute("search",false);
        return "masterBillForm";
    }

    @RequestMapping(value = "/createMasterBill", method = RequestMethod.POST)
    public String saveMasterBillEntry( Model model, MasterBill masterBill) {
        MasterBill existing = masterBillService.findByMasterBillNumber(masterBill.getMasterBillLadingNumber());
        if (existing != null) {
            model.addAttribute("status", "exist");
            return "index";
        }
        //TODO:Ask david to put new column in MASTERBILLLADINGNUMBER for date
       // masterBill.setCreatedOn(new Date());
        masterBillService.saveMasterBill(masterBill);
        model.addAttribute("saved", "success");
        return "index";
    }

    @RequestMapping(value = "/searchMasterBills", method = RequestMethod.POST)
    public String searchMasterBills(Model model, MasterBill masterBill) {
        List<MasterBill> masterBills = masterBillService.findMasterBills(masterBill.getMasterBillLadingNumber());
        model.addAttribute("foundMasterBills", masterBills);
        model.addAttribute("search", true);
        return "masterBillForm";
    }

    @RequestMapping(value = "/editMasterBills/{masterBillIdentifier}", method = RequestMethod.GET)
    public String updateMasterBillEntry(Model model, @PathVariable String masterBillIdentifier) {
        MasterBill masterBill = masterBillService.findByMasterBillNumber(masterBillIdentifier);
        model.addAttribute("masterBill", masterBill);
        return "update";
    }

    @RequestMapping(value = "/updateMasterBill", method = RequestMethod.POST)
    public String updateMasterBillEntry( Model model, MasterBill masterBill) {
        masterBillService.saveMasterBill(masterBill);
        model.addAttribute("saved", "success");
        return "update";
    }
    @RequestMapping(value = "/delete/{masterBillIdentifier}", method = RequestMethod.GET)
    public String deleteMasterBillEntry(Model model, @PathVariable String masterBillIdentifier) {
        masterBillService.delete(masterBillIdentifier);
        model.addAttribute("deleted", "success");
        model.addAttribute("masterBill", new MasterBill());
        return "index";
    }

}
