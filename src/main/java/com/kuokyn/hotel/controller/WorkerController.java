package com.kuokyn.hotel.controller;

import com.kuokyn.hotel.entity.Worker;
import com.kuokyn.hotel.filter.WorkerFilter;
import com.kuokyn.hotel.service.SecurityService;
import com.kuokyn.hotel.service.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;
import java.util.Optional;

@Controller
@RequestMapping
@SessionAttributes("workerForm")
public class WorkerController {

    private final PasswordEncoder passwordEncoder;
    private final WorkerService workerService;
    private final SecurityService securityService;

    @Autowired
    public WorkerController(WorkerService workerService, SecurityService securityService, PasswordEncoder passwordEncoder) {
        this.workerService = workerService;
        this.securityService = securityService;
        this.passwordEncoder = passwordEncoder;
    }

    /* -----ADMIN------*/
    @Secured("ROLE_ADMIN")
    @GetMapping("/addWorker")
    public String addWorker(Model model) {
        model.addAttribute("workerForm", new Worker());
        return "addWorker.html";
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/editWorker.html", method = RequestMethod.GET)
    public String showForm(Model model, Optional<Long> id) {
        model.addAttribute("workerForm",
                id.isPresent() ?
                        workerService.getWorker(id.get()) :
                        new Worker());
        return "editWorker.html";
    }

    @Secured("ROLE_ADMIN")
    @PostMapping("/editWorker.html")
    public String editWorker(@ModelAttribute("workerForm") Worker workerForm) {
        workerService.save(workerForm);
        return "redirect:workerList.html";
    }

    @Secured("ROLE_ADMIN")
    @PostMapping("/addWorker")
    public String addWorker(@ModelAttribute("workerForm") Worker workerForm, BindingResult bindingResult) {
        workerService.save(workerForm);
        return "redirect:workerList.html";
    }

    @Secured("ROLE_ADMIN")
    @GetMapping(value = "/workerList.html", params = {"all"})
    public String resetWorkerList(@ModelAttribute("searchCommand") WorkerFilter search) {
        search.clear();
        return "redirect:workerList.html";
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/workerList.html", method = {RequestMethod.GET})
    public String showWorkerList(Model model, Pageable pageable, @Valid @ModelAttribute("searchCommand") WorkerFilter search) {

        model.addAttribute("workerListPage", workerService.getAllWorkers(search, pageable));

        return "workerList";
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/workerList.html", method = {RequestMethod.POST})
    public String showWorkerList2(Model model, Pageable pageable, @Valid @ModelAttribute("searchCommand") WorkerFilter search) {

        model.addAttribute("workerListPage", workerService.getAllWorkers(search, pageable));
        System.out.println("fraza " + search.getPhrase());
        return "workerList";
        // return "redirect:reservationList";
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/workerList.html", params = "id", method = RequestMethod.GET)
    public String deleteWorker(long id, HttpServletRequest request) {
        workerService.deleteWorker(id);
        return "deleteInfo";
    }

}
