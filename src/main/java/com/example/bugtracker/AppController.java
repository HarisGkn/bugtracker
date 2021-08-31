package com.example.bugtracker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class AppController {

    @Autowired
    private TicketService service;


    @RequestMapping("/")
    public String viewHomePage(Model model) {
        List<Ticket> listTickets = service.listAll();
        model.addAttribute("listTickets", listTickets);

        return "index";
    }

    @RequestMapping("/new")
    public String showNewProductPage(Model model) {
        Ticket ticket = new Ticket();
        model.addAttribute("ticket", ticket);

        return "new_ticket";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveProduct(@ModelAttribute("ticket") Ticket ticket) {
        service.save(ticket);

        return "redirect:/";
    }

    @RequestMapping("/edit/{id}")
    public ModelAndView showEditProductPage(@PathVariable(name = "id") int id) {
        ModelAndView mav = new ModelAndView("edit_ticket");
        Ticket ticket = service.get(id);
        mav.addObject("ticket", ticket);

        return mav;
    }

    @RequestMapping("/delete/{id}")
    public String deleteProduct(@PathVariable(name = "id") int id) {
        service.delete(id);
        return "redirect:/";
    }

}
