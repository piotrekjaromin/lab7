package com.ejb;

import com.model.Seat;
import org.jboss.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.Lock;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import java.util.ArrayList;

import static javax.ejb.LockType.READ;
import static javax.ejb.LockType.WRITE;


@Singleton
@Startup
 
public class TheatreBox {

	private ArrayList<Seat> seatList;
	private static final Logger logger =
			Logger.getLogger(TheatreBox.class);

	@PostConstruct
	public void setupTheatre(){
		seatList = new ArrayList<Seat>();
		int id = 0;
		for (int i=0;i<5;i++) {
			Seat seat = new Seat(++id, "Stalls",40);
			seatList.add(seat);
		}
		for (int i=0;i<5;i++) {
			Seat seat = new Seat(++id,"Circle",20);
			seatList.add(seat);
		}
		for (int i=0;i<5;i++) {
			Seat seat = new Seat(++id, "Balcony",10);
			seatList.add(seat);
		}
		logger.info("Seat Map constructed.");
	 
	}

	@Lock(READ)  
	public ArrayList<Seat> getSeatList() {

		return seatList;
	}
	@Lock(READ)  
	public int getSeatPrice(int id) {

		return getSeatList().get(id).getPrice();
	}

	@Lock(WRITE)  
	public void buyTicket(int seatId )     {
		Seat seat = getSeatList().get(seatId);

		seat.setBooked(true);


	}

}
