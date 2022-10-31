package com.hrm.roomreservationservice.client.room;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Room {
    private long roomId;
    private String Name;
    private String roomNumber;
    private String bedInfo;
}
