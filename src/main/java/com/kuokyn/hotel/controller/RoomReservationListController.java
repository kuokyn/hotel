package com.kuokyn.hotel.controller;

import com.kuokyn.hotel.filter.RoomReservationFilter;
import com.kuokyn.hotel.service.RoomReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@SessionAttributes("searchCommand")
public class RoomReservationListController {

    @Autowired
    private RoomReservationService roomReservationService;

    @GetMapping({"/deleteInfo"})
    public String showDeleteInfo(Model model) {
        return "deleteInfo.html";
    }

    @ModelAttribute("searchCommand")
    public RoomReservationFilter getSimpleSearch(){
        return new RoomReservationFilter();
    }

    @GetMapping(value="/reservationList.html", params = {"all"})
    public String resetreservationList(@ModelAttribute("searchCommand") RoomReservationFilter search){
        search.clear();
        return "redirect:reservationList.html";
    }
    @RequestMapping(value="/reservationList.html", method = {RequestMethod.GET})
    public String showReservationList(Model model, Pageable pageable, @Valid @ModelAttribute("searchCommand") RoomReservationFilter search){

        model.addAttribute("roomReservationListPage", roomReservationService.getAllRoomReservations(search, pageable));

        return "reservationList";
    }
    @RequestMapping(value="/reservationList.html", method = {RequestMethod.POST})
    public String showReservationList2(Model model, Pageable pageable, @Valid @ModelAttribute("searchCommand") RoomReservationFilter search){
        model.addAttribute("roomReservationListPage", roomReservationService.getAllRoomReservations(search, pageable));
        return "reservationList";
    }

    @RequestMapping(value="/yourReservationList.html", method = {RequestMethod.GET})
    public String showYourReservationList(Model model, Pageable pageable, @Valid @ModelAttribute("searchCommand") RoomReservationFilter search, Principal principal){
      search.setPhrase(principal.getName());
      model.addAttribute("roomReservationListPage", roomReservationService.getUserRoomReservations(search, pageable));
      return "yourReservationList";
    }


    @RequestMapping(value="/reservationList.html", params = "id", method = RequestMethod.GET)
    public String deleteRoomReservation(long id, HttpServletRequest request){
        roomReservationService.deleteRoomReservation(id);
        String queryString = prepareQueryString(request.getQueryString());
        return "deleteInfo";
    }

    @RequestMapping(value="/yourReservationList.html", params = "id", method = RequestMethod.GET)
    public String deleteRoomReservationUSR(long id, HttpServletRequest request){
        roomReservationService.deleteRoomReservation(id);
        String queryString = prepareQueryString(request.getQueryString());
        return "deleteInfo";
    }
    private String prepareQueryString(String queryString) {//np., did=20&page=2&size=20
        return queryString.substring(queryString.indexOf("&")+1);//obcinamy parametr did, bo inaczej znowu będzie wywołana metoda deleteVihicle
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        CustomDateEditor dateEditor = new CustomDateEditor(dateFormat, false);
        binder.registerCustomEditor(Date.class, "reservationEndDate", dateEditor);

        SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat2.setLenient(false);
        CustomDateEditor dateEditor2 = new CustomDateEditor(dateFormat2, false);
        binder.registerCustomEditor(Date.class, "reservationStartDate", dateEditor2);

    }

}
