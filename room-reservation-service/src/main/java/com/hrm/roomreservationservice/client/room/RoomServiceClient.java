package com.hrm.roomreservationservice.client.room;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "room-service", url = "https://room-service-dot-bsuir-viapo.uc.r.appspot.com")
public interface RoomServiceClient {

    @GetMapping("/rooms")
    List<Room> getAll();

    @PostMapping("/rooms")
    Room addRoom(@RequestBody Room room);

    @GetMapping("/rooms/{id}")
    Room getRoom(@PathVariable("id") long id);

    @PutMapping("/rooms/{id}")
    void updateRoom(@PathVariable("id") long id, @RequestBody Room room);

    @DeleteMapping("/rooms/{id}")
    void deleteRoom(@PathVariable("id") long id);
}
