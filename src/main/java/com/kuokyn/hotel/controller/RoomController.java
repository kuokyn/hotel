package com.kuokyn.hotel.controller;

import com.kuokyn.hotel.entity.Room;
import com.kuokyn.hotel.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping
public class RoomController {

    private final RoomService roomService;

    @Autowired
    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping({"/roomDetails"})
    public String showRoomDetails() {
        return "roomDetails";
    }

    @RequestMapping(value = "/roomDetails.html", params = {"id"}, method = RequestMethod.GET)
    public String showRoomDetails(Model model, Long id) {
        Room r = roomService.getRoom(id);
        model.addAttribute("room", r);
        return "roomDetails";
    }

    @RequestMapping(value = "/roomList.html", method = {RequestMethod.GET})
    public String showRoomList(Model model, Pageable pageable) {
        model.addAttribute("roomListPage", roomService.getAllRooms2(pageable));
        return "roomList";
    }


    @RequestMapping(value = "/rooms.html", method = {RequestMethod.GET})
    public String showRooms(Model model, Pageable pageable) {
        model.addAttribute("roomListPage", roomService.getAllRooms2(pageable));
        return "rooms";
    }

}
