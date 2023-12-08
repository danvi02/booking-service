package com.movie.ticket.booking.system.service.apis;

import com.movie.ticket.booking.system.service.dtos.BookingDTO;
import com.movie.ticket.booking.system.service.services.impl.BookingService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping ("/bookings")
@Slf4j
public class BookingApi {

    @Autowired
    private BookingService bookingService;

    @PostMapping
    public BookingDTO createBooking(@Valid @RequestBody BookingDTO bookingDTO) {
        return this.bookingService.createBooking(bookingDTO);
    }

}
