package com.compsys.controller;

import com.compsys.entity.MasterBill;

import com.compsys.service.MasterBillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.apache.log4j.Logger;


import java.util.List;


/**
 * @author Lavan chandra
 */

@Controller
public class MasterBillController {

    private static final Logger logger = Logger.getLogger(MasterBillController.class);

    @Autowired
    private MasterBillService masterBillService;

    @RequestMapping(value = "/masterBill", method = RequestMethod.GET)
    public String showMasterBillForm(Model model) {
        model.addAttribute("masterBill",new MasterBill());
        model.addAttribute("search",false);
        return "masterBillForm";
    }

    @RequestMapping(value = "/masterBillAdmin", method = RequestMethod.GET)
    public String showMasterBillAdminForm(Model model) {
        model.addAttribute("masterBill",new MasterBill());
        model.addAttribute("search",false);
        model.addAttribute("admin",true);
        return "masterBillForm";
    }

    @RequestMapping(value = {"/", "/welcome**"}, method = RequestMethod.GET)
    public ModelAndView welcomePage() {

        ModelAndView model = new ModelAndView();
        model.addObject("title", "Welcome Page For TR-AID");
        model.addObject("message", "This is welcome page!");
        model.addObject("admin", "/sphb/admin");
        model.addObject("data", "/sphb/welcome");
        model.addObject("masterBill", "/sphb/masterBill");
        model.setViewName("hello");
        return model;

    }

    @RequestMapping(value = "/admin**", method = RequestMethod.GET)
    public ModelAndView adminPage() {

        ModelAndView model = new ModelAndView();
        model.addObject("title", "ADMIN");
        model.addObject("message", "ONLY FOR ADMIN USERS");
        model.addObject("masterBill", "/sphb/masterBillAdmin");
        model.setViewName("admin");

        return model;

    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login(@RequestParam(value = "error", required = false) String error,
                              @RequestParam(value = "logout", required = false) String logout) {

        logger.info("MasterBillController :login is called ");

        ModelAndView model = new ModelAndView();
        if (error != null) {
            model.addObject("error", "Invalid username and password!");
        }

        if (logout != null) {
            model.addObject("msg", "You've been logged out successfully.");
        }
        model.setViewName("login");
        return model;
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
        List<MasterBill> masterBills = masterBillService.findMasterBills(masterBill);
        model.addAttribute("foundMasterBills", masterBills);
        model.addAttribute("search", true);
        return "masterBillForm";
    }


    @RequestMapping(value = "/pageMasterBills", method = RequestMethod.POST)
    public String pageMasterBills(Model model, MasterBill masterBill,@PathVariable String page) {
        int p = Integer.valueOf(page);
        List<MasterBill> masterBills = masterBillService.findMasterBills(p,-1);
        model.addAttribute("pagedMasterBills", masterBills);
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

    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public ModelAndView accesssDenied() {

        ModelAndView model = new ModelAndView();

        //check if user is login
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            UserDetails userDetail = (UserDetails) auth.getPrincipal();
            model.addObject("username", userDetail.getUsername());
        }

        model.setViewName("denied");
        return model;

    }
}
