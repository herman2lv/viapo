package com.hrm.reservationservice.api;

import com.hrm.reservationservice.data.ReservationEntity;
import com.hrm.reservationservice.data.ReservationRepository;
import com.hrm.reservationservice.error.BadReqeustException;
import com.hrm.reservationservice.error.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/reservations")
public class ReservationController {

    private final ReservationRepository reservationRepository;
    private final static Logger LOGGER = LoggerFactory.getLogger(ReservationController.class);

    public ReservationController(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    @GetMapping
    public List<Reservation> getAll(@RequestParam(value = "guestId", required = false) Long guestId, @RequestParam(value = "date", required = false) String dateString) {
        Iterable<ReservationEntity> entities = null;
        List<Reservation> reservations = new ArrayList<>();
        if (StringUtils.hasLength(dateString) && guestId != null) {
            try {
                Date date = this.getDateFromString(dateString);
                entities = this.reservationRepository.findReservationEntitiesByDateAndGuestId(date, guestId);
            } catch (ParseException e) {
                LOGGER.error("unable to translate date", e);
                throw new BadReqeustException("unable to translate date string");
            }
        } else if (StringUtils.hasLength(dateString)) {
            try {
                Date date = this.getDateFromString(dateString);
                entities = this.reservationRepository.findReservationEntitiesByDate(date);
            } catch (ParseException e) {
                LOGGER.error("unable to translate date", e);
                throw new BadReqeustException("unable to translate date string");
            }
        } else if (guestId != null) {
            entities = this.reservationRepository.findReservationEntitiesByGuestId(guestId);
        } else {
            entities = this.reservationRepository.findAll();
        }
        entities.forEach(entity -> reservations.add(new Reservation(entity)));
        return reservations;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Reservation createReservation(@RequestBody Reservation reservation) {
        try {
            ReservationEntity entity = reservation.getReservationEntity();
            entity = this.reservationRepository.save(entity);
            return new Reservation(entity);
        } catch (ParseException e) {
            LOGGER.error("unable to translate date", e);
            throw new BadReqeustException("unable to translate date string");
        }
    }

    @GetMapping("/{id}")
    public Reservation getReservation(@PathVariable("id") long id) {
        Optional<ReservationEntity> entity = this.reservationRepository.findById(id);
        if (entity.isEmpty()) {
            throw new NotFoundException("id not found " + id);
        }
        return new Reservation(entity.get());
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateReservation(@PathVariable("id") long id, @RequestBody Reservation reservation) {
        if (id != reservation.getReservationId()) {
            throw new BadReqeustException("id in body doesn't match path");
        }
        try {
            ReservationEntity entity = reservation.getReservationEntity();
            this.reservationRepository.save(entity);
        } catch (ParseException e) {
            LOGGER.error("unable to translate date", e);
            throw new BadReqeustException("unable to translate date string");
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.RESET_CONTENT)
    public void deleteReservation(@PathVariable("id") long id) {
        this.reservationRepository.deleteById(id);
    }

    private Date getDateFromString(String dateString) throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date date = dateFormat.parse(dateString);
        return new Date(date.getTime());
    }


}
