package com.movie.ticket.booking.system.service.dtos;

public enum BookingStatus {
    PENDING,        // The initial status when a booking request is submitted
    CANCELLED,      // The booking request has been canceled, and the reserved seats are released
    CONFIRMED,      // The booking is confirmed after payment, and the tickets are issued
    REFUNDED        // The booking was canceled, and the refund has been processed
}
