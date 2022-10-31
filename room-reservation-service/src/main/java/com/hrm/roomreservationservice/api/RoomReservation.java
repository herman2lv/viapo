package com.hrm.roomreservationservice.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class RoomReservation {
    private long guestId;
    private long roomId;
    private long reservationId;
    private String firstName;
    private String lastName;
    private String Name;
    private String roomNumber;
    private String bedInfo;
    private String date;

}
