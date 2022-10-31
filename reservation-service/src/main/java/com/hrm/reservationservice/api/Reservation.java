package com.hrm.reservationservice.api;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.hrm.reservationservice.data.ReservationEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Reservation {

    private long reservationId;
    private long roomId;
    private long guestId;
    private String date;

    public Reservation(ReservationEntity entity) {
        super();
        this.roomId = entity.getRoomId();
        this.guestId = entity.getGuestId();
        Date date = new Date(entity.getDate().getTime());
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        this.date = dateFormat.format(date);
        this.reservationId = entity.getReservationId();
    }

    @JsonIgnore
    public ReservationEntity getReservationEntity() throws ParseException {
        ReservationEntity entity = new ReservationEntity();
        entity.setReservationId(this.reservationId);
        entity.setGuestId(this.guestId);
        entity.setRoomId(this.roomId);
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = dateFormat.parse(this.date);
        entity.setDate(new java.sql.Date(date.getTime()));
        return entity;
    }
}
