package com.ejb;


import com.exception.SeatBookedException;

public interface TheatreBooker {
   public String bookSeat(int seatId) throws SeatBookedException;
}