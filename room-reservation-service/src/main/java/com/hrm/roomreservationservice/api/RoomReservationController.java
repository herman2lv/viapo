package com.hrm.roomreservationservice.api;

import com.hrm.roomreservationservice.client.guest.Guest;
import com.hrm.roomreservationservice.client.guest.GuestServiceClient;
import com.hrm.roomreservationservice.client.reservation.Reservation;
import com.hrm.roomreservationservice.client.reservation.ReservationServiceClient;
import com.hrm.roomreservationservice.client.room.Room;
import com.hrm.roomreservationservice.client.room.RoomServiceClient;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("roomReservations")
public class RoomReservationController {

    private final GuestServiceClient guestServiceClient;
    private final ReservationServiceClient reservationServiceClient;
    private final RoomServiceClient roomServiceClient;

    public RoomReservationController(GuestServiceClient guestServiceClient, ReservationServiceClient reservationServiceClient, RoomServiceClient roomServiceClient) {
        this.guestServiceClient = guestServiceClient;
        this.reservationServiceClient = reservationServiceClient;
        this.roomServiceClient = roomServiceClient;
    }

    @GetMapping
    public Collection<RoomReservation> getRoomReservations(@RequestParam(value = "date", required = false) String dateString) {
        if (!StringUtils.hasLength(dateString)) {
            Date date = new Date(System.currentTimeMillis());
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            dateString = dateFormat.format(date);
        }
        final String usableDateString = dateString;
        //get all rooms first
        List<Room> rooms = this.roomServiceClient.getAll();
        //now build a room reservation for each one
        Map<Long, RoomReservation> roomReservations = new HashMap<>(rooms.size());
        rooms.forEach(room -> {
            RoomReservation roomReservation = new RoomReservation();
            roomReservation.setRoomId(room.getRoomId());
            roomReservation.setRoomNumber(room.getRoomNumber());
            roomReservation.setBedInfo(room.getBedInfo());
            roomReservation.setName(room.getName());
            roomReservation.setDate(usableDateString);
            roomReservations.put(room.getRoomId(), roomReservation);
        });
        List<Reservation> reservations = this.reservationServiceClient.getAll(usableDateString, null);
        reservations.forEach(reservation -> {
            RoomReservation roomReservation = roomReservations.get(reservation.getRoomId());
            roomReservation.setReservationId(reservation.getReservationId());
            roomReservation.setGuestId(reservation.getGuestId());
            Guest guest = this.guestServiceClient.getGuest(roomReservation.getGuestId());
            roomReservation.setFirstName(guest.getFirstName());
            roomReservation.setLastName(guest.getLastName());
        });
        return roomReservations.values();
    }
}
