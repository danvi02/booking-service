package com.movie.ticket.booking.system.service.services;

import com.movie.ticket.booking.system.service.brokers.PaymentServiceBroker;
import com.movie.ticket.booking.system.service.dtos.BookingDTO;
import com.movie.ticket.booking.system.service.dtos.BookingStatus;
import com.movie.ticket.booking.system.service.services.impl.BookingService;
import com.movie.ticket.booking.system.service.entities.BookingEntity;
import com.movie.ticket.booking.system.service.repositories.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private PaymentServiceBroker paymentService;

    @Override
    @Transactional
    public BookingDTO createBooking(BookingDTO bookingDTO) {
        BookingEntity bookingEntity = BookingEntity.builder()
                .bookingAmount(bookingDTO.getBookingAmount())
                .seatsSelected(bookingDTO.getSeatsSelected())
                .bookingStatus(BookingStatus.PENDING)
                .movieId(bookingDTO.getMovieId())
                .userId(bookingDTO.getUserId())
                .showDate(bookingDTO.getShowDate())
                .showTime(bookingDTO.getShowTime())
                .build();
        this.bookingRepository.save(bookingEntity); // create a booking with booking status as PENDING
        bookingDTO.setBookingId(bookingEntity.getBookingId());
        bookingDTO.setBookingStatus(BookingStatus.PENDING);
        // call payment service
        String paymentResponse = this.paymentService.createPayment();
        return BookingDTO.builder()
                .bookingId(bookingEntity.getBookingId())
                .bookingAmount(bookingEntity.getBookingAmount())
                .bookingStatus(BookingStatus.CONFIRMED)
                .movieId(bookingEntity.getMovieId())
                .showTime(bookingEntity.getShowTime())
                .showDate(bookingEntity.getShowDate())
                .bookingAmount(bookingEntity.getBookingAmount())
                .userId(bookingEntity.getUserId())
                .seatsSelected(bookingEntity.getSeatsSelected())
                .build();
    }
}
