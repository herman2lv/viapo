package com.hrm.roomreservationservice.client.reservation;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("reservation-service")
public interface ReservationServiceClient {

    @GetMapping("/reservations")
    List<Reservation> getAll(@RequestParam(name = "date", required = false) String dateString,
                             @RequestParam(name = "guestId", required = false) Long guestId);

    @PostMapping("/reservations")
    Reservation addReservation(@RequestBody Reservation reservation);

    @GetMapping("/reservations/{id}")
    Reservation getReservation(@PathVariable("id") long id);

    @PutMapping("/reservations/{id}")
    void updateReservation(@PathVariable("id") long id, @RequestBody Reservation reservation);

    @DeleteMapping("/reservations/{id}")
    void deleteReservation(@PathVariable("id") long id);

}
