package com.parkit.parkingsystem.service;

import java.math.BigDecimal;

import com.parkit.parkingsystem.constants.Fare;
import com.parkit.parkingsystem.model.Ticket;

public class FareCalculatorService {

    public void calculateFare(Ticket ticket){
        if( (ticket.getOutTime() == null) || (ticket.getOutTime().before(ticket.getInTime())) ){
            throw new IllegalArgumentException("Out time provided is incorrect:"+ticket.getOutTime().toString());
        }

        long inHour = ticket.getInTime().getTime();
        long outHour = ticket.getOutTime().getTime();

        //TODO: Some tests are failing here. Need to check if this logic is correct
        double duration = (outHour - inHour)/1000.00/60.00/60.00;
        BigDecimal durationBd = new BigDecimal(duration);
        
        if (duration < 0.5) {
        	ticket.setPrice(new BigDecimal(0));
        }
        
        else {
        switch (ticket.getParkingSpot().getParkingType()){
            case CAR: {
                ticket.setPrice((durationBd.multiply(Fare.CAR_RATE_PER_HOUR)));
                if (ticket.getUserExists() == true) {
                	ticket.setPrice(ticket.getPrice().multiply(Fare.DISCOUNT_RECURENT_USER));
                }
                break;
            }
            case BIKE: {
                ticket.setPrice((durationBd.multiply(Fare.BIKE_RATE_PER_HOUR)));
                if (ticket.getUserExists() == true) {
                	ticket.setPrice((ticket.getPrice().multiply(Fare.DISCOUNT_RECURENT_USER)));
                }
                break;
            }
            default: throw new IllegalArgumentException("Unkown Parking Type");
            }
        }
    }
}