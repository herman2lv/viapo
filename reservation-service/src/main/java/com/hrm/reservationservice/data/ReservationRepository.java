package com.hrm.reservationservice.data;

import org.springframework.data.repository.CrudRepository;

import java.sql.Date;

public interface ReservationRepository extends CrudRepository<ReservationEntity, Long> {
    Iterable<ReservationEntity> findReservationEntitiesByGuestId(long guestId);

    Iterable<ReservationEntity> findReservationEntitiesByDate(Date date);

    Iterable<ReservationEntity> findReservationEntitiesByDateAndGuestId(Date date, long guestId);
}
