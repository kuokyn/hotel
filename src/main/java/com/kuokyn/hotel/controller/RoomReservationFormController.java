package com.kuokyn.hotel.controller;

import com.kuokyn.hotel.entity.Booking;
import com.kuokyn.hotel.filter.RoomFilter;
import com.kuokyn.hotel.entity.Room;
import com.kuokyn.hotel.entity.User;
import com.kuokyn.hotel.service.RoomReservationService;
import com.kuokyn.hotel.service.RoomService;
import com.kuokyn.hotel.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Optional;
import java.util.Set;


@Controller
@RequestMapping
@SessionAttributes({"booking", "roomListPage"})
public class RoomReservationFormController {

    private final RoomReservationService roomReservationService;

    private final RoomService roomService;

    private final UserService userService;

    private static SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);

    @Autowired
    public RoomReservationFormController(RoomReservationService roomReservationService, RoomService roomService, UserService userService) {
        this.roomReservationService = roomReservationService;
        this.roomService = roomService;
        this.userService = userService;
    }

    @RequestMapping(value = "/reservationFormUSR.html", method = RequestMethod.GET)
    public String showFormUSR(Model model, Optional<Long> id) {

        model.addAttribute("booking",
                id.isPresent() ?
                        roomReservationService.getRoomReservation(id.get()) :
                        new Booking());

        return "reservationFormUSR.html";
    }

    @RequestMapping(value = "/reservationFormADM.html", method = RequestMethod.GET)
    public String showFormADM(Model model, Optional<Long> id) {

        model.addAttribute("booking",
                id.isPresent() ?
                        roomReservationService.getRoomReservation(id.get()) :
                        new Booking());

        return "reservationFormADM.html";
    }

    @RequestMapping(value = "/editReservationFormADM.html", method = RequestMethod.GET)
    public String showEditFormADM(Model model, Optional<Long> id) {

        model.addAttribute("booking",
                id.isPresent() ?
                        roomReservationService.getRoomReservation(id.get()) :
                        new Booking());

        return "editReservationFormADM.html";
    }

    @RequestMapping(value = "/availableRooms.html", method = RequestMethod.GET)
    public String showFormUSR2(Model model, @ModelAttribute("booking") Booking v, @ModelAttribute("roomListPage") Page<Room> r) {

        model.addAttribute("roomListPage", r);
        model.addAttribute("booking", v);

        return "availableRooms.html";
    }

    @RequestMapping(value = "/reservationFormUSR.html", method = RequestMethod.POST)
    public String processFormUSR(Model model, @RequestBody @Valid @ModelAttribute("booking") Booking v, Pageable pageable, RoomFilter search, Principal principal) {
//        System.out.println("###############" + v.getReservationStartDate());
//        System.out.println("###############" + v.getReservationEndDate());
        search.setNumberOfPeople(v.getNumberOfPeople());
/*        if (errors.hasErrors()) {
            return "reservationFormUSR";
        }*/

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String strDate = dateFormat.format(v.getReservationStartDate());
        search.setReservationStartDate(strDate);
        strDate = dateFormat.format(v.getReservationEndDate());
        search.setReservationEndDate(strDate);

        v.setUser(userService.getUserByPhone(principal.getName()));
        model.addAttribute("roomListPage", roomService.getAllRooms(search, pageable));
        model.addAttribute("booking", v);
        return "redirect:/availableRooms.html";

    }

    @RequestMapping(value = "/reservationFormADM.html", method = RequestMethod.POST)
    public String processFormADM(Model model, @RequestBody @ModelAttribute("booking") Booking v, Pageable pageable, RoomFilter search, Principal principal) {
/*
        if (errors.hasErrors()) {

            return "reservationFormADM";
        }*/
//        System.out.println("###############" + v.getReservationStartDate());
//        System.out.println("###############" + v.getReservationEndDate());

        User usertemp = userService.getUserById(v.getUser().getId());
        v.setUser(usertemp);
        search.setNumberOfPeople(v.getNumberOfPeople());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String strDate = dateFormat.format(v.getReservationStartDate());
        search.setReservationStartDate(strDate);
        strDate = dateFormat.format(v.getReservationEndDate());
        search.setReservationEndDate(strDate);


        model.addAttribute("roomListPage", roomService.getAllRooms(search, pageable));
        model.addAttribute("booking", v);
        return "redirect:/availableRooms.html";

    }

    @RequestMapping(value = "/editReservationFormADM.html", method = RequestMethod.POST)
    public String processEditFormADM(@Valid @ModelAttribute("booking") Booking v) {
        roomReservationService.saveRoomReservation(v);
        return "redirect:/reservationList.html";

    }

    @RequestMapping(value = "/availableRooms.html", method = RequestMethod.POST)
    public String processFormUSR2(@Valid @ModelAttribute("booking") Booking v, @ModelAttribute("roomListPage") Page<Room> r, Principal principal) {
        roomReservationService.saveRoomReservation(v);

        Long idRoom = v.getRoom().getId();
        Room rr = roomService.getRoom(idRoom);

        Set<Booking> bookings = rr.getBookings();
        bookings.add(v);
        rr.setBookings(bookings);

        if (principal.getName().equals("ROLE_ADMIN")) {
            return "redirect:reservationList.html";
        } else {
            return "redirect:yourReservationList.html";
        }
    }


    /*@InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        CustomDateEditor dateEditor = new CustomDateEditor(dateFormat, false);
        binder.registerCustomEditor(Date.class, "check_in_date", dateEditor);
        binder.registerCustomEditor(Date.class, "check_out_date", dateEditor);
        binder.registerCustomEditor(Date.class, "checkInDate", dateEditor);
        binder.registerCustomEditor(Date.class, "checkOutDate", dateEditor);

        DecimalFormat numberFormat = new DecimalFormat("#0.00");
        numberFormat.setMaximumFractionDigits(2);
        numberFormat.setMinimumFractionDigits(2);
        numberFormat.setGroupingUsed(false);
        binder.registerCustomEditor(Float.class, "price", new CustomNumberEditor(Float.class, numberFormat, false));
    }*/
}
