package com.example.library.periodical.controller;

import com.example.library.periodical.service.PeriodicalService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/periodical")
public class PeriodicalController {

    private final PeriodicalService periodicalService;

    public PeriodicalController(PeriodicalService periodicalService) {
        this.periodicalService = periodicalService;
    }

    @GetMapping
    public String index() { return "periodical/index"; }

    @GetMapping("/visit")
    public String visit(Model model) {
        model.addAttribute("visitList", periodicalService.listVisits());
        model.addAttribute("interviewList", periodicalService.listInterviewRecords());
        return "periodical/visit";
    }

    @PostMapping("/visit/add")
    public String addVisit(@RequestParam String title,
                           @RequestParam String issn,
                           @RequestParam String recommender,
                           @RequestParam String recommendDate,
                           @RequestParam String reason) {
        periodicalService.addVisitRecord(title, issn, recommender, recommendDate, reason);
        return "redirect:/periodical/visit";
    }

    @PostMapping("/visit/interview/add")
    public String addInterview(@RequestParam String title,
                               @RequestParam String issn,
                               @RequestParam String publisher,
                               @RequestParam String interviewer,
                               @RequestParam String interviewDate,
                               @RequestParam String notes) {
        periodicalService.addInterviewRecord(title, issn, publisher, interviewer, interviewDate, notes);
        return "redirect:/periodical/visit";
    }

    @GetMapping("/order")
    public String order(Model model) {
        model.addAttribute("orderList", periodicalService.listOrders());
        return "periodical/order";
    }

    @PostMapping("/order/add")
    public String addOrder(@RequestParam String title,
                           @RequestParam String issn,
                           @RequestParam String supplier,
                           @RequestParam Integer quantity,
                           @RequestParam Double unitPrice,
                           @RequestParam String orderDate) {
        periodicalService.addOrder(title, issn, supplier, quantity, unitPrice, orderDate);
        return "redirect:/periodical/order";
    }

    @GetMapping("/verify")
    public String verify(Model model) {
        model.addAttribute("acceptanceList", periodicalService.listAcceptanceRecords());
        return "periodical/verify";
    }

    @PostMapping("/verify/add")
    public String addAcceptance(@RequestParam String title,
                                @RequestParam String issn,
                                @RequestParam String publisher,
                                @RequestParam Integer receivedQuantity,
                                @RequestParam String checker,
                                @RequestParam String acceptanceDate,
                                @RequestParam String status) {
        periodicalService.addAcceptanceRecord(title, issn, publisher, receivedQuantity, checker, acceptanceDate, status);
        return "redirect:/periodical/verify";
    }

    @GetMapping("/bind")
    public String bind(Model model) {
        model.addAttribute("bindingList", periodicalService.listBindingRecords());
        model.addAttribute("catalogList", periodicalService.listCatalogEntries());
        return "periodical/bind";
    }

    @PostMapping("/bind/add")
    public String addBind(@RequestParam String title,
                          @RequestParam String issn,
                          @RequestParam String volumeInfo,
                          @RequestParam String binder,
                          @RequestParam String bindDate,
                          @RequestParam String shelfLocation) {
        periodicalService.addBindingRecordAndCatalog(title, issn, volumeInfo, binder, bindDate, shelfLocation);
        return "redirect:/periodical/bind";
    }

    @GetMapping("/query")
    public String query(Model model) {
        model.addAttribute("catalogList", periodicalService.listCatalogEntries());
        return "periodical/query";
    }
}
