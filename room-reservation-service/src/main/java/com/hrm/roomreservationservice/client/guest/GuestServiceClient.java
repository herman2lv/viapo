package com.hrm.roomreservationservice.client.guest;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "guest-service", url = "https://guest-service-dot-bsuir-viapo.uc.r.appspot.com")
public interface GuestServiceClient {

    @GetMapping("/guests")
    List<Guest> getAll();

    @PostMapping("/guests")
    Guest addGuest(@RequestBody Guest guest);

    @GetMapping("/guests/{id}")
    Guest getGuest(@PathVariable("id") long id);

    @PutMapping("/guests/{id}")
    void updateGuest(@PathVariable("id") long id, @RequestBody Guest guest);

    @DeleteMapping("/guests/{id}")
    void deleteGuest(@PathVariable("id") long id);

}
