package com.hrm.roomreservationservice.client.reservation;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Reservation {

    private long reservationId;
    private long roomId;
    private long guestId;
    private String date;
}
