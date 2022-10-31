package com.hrm.guestservice.data;

import org.springframework.data.repository.CrudRepository;

public interface GuestRepository extends CrudRepository<Guest, Long> {
    Iterable<Guest> findGuestsByEmailAddress(String emailAddress);
}
