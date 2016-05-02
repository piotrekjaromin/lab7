package com.ejb;



import com.exception.NotEnoughMoneyException;
import com.exception.SeatBookedException;
import com.model.Seat;
import org.jboss.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.*;
import java.util.concurrent.Future;

@Stateful
@Remote(TheatreBooker.class) 
public class TheatreBookerBean implements TheatreBooker {
	private static final Logger logger =
			Logger.getLogger(TheatreBookerBean.class);

	int money;
	@EJB TheatreBox theatreBox;

	@PostConstruct
	public void createCustomer() {
		this.money=100;
	}



	public String bookSeat(int seatId) throws SeatBookedException,NotEnoughMoneyException {
		Seat seat = theatreBox.getSeatList().get(seatId);
		if (seat.isBooked()) {
			throw new SeatBookedException("Seat Already booked!");
		}
		if (seat.getPrice() > money) {
			throw new NotEnoughMoneyException("You don't have enough money to buy this ticket!");
		}
		theatreBox.buyTicket(seatId);
		money = money -seat.getPrice();
		logger.info("Seat booked.");
		return "Seat booked.";
	}

}
