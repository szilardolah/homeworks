package com.szilardolah.jpa.bean.vehicle;

import java.awt.Color;
import java.io.Serializable;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

/**
 *
 * @author Szilard <szilard.olah@yahoo.com>
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "disc", discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue("Passenger Vehicle")
public class PassengerVehicle extends Vehicle implements Serializable {
    
    private Integer numberOfPassengers;  

    public PassengerVehicle() {
        super();
    }

    public PassengerVehicle(Integer numberOfPassengers, Color color) {
        super(color);
        this.numberOfPassengers = numberOfPassengers;
    }   

    public Integer getNumberOfPassengers() {
        return numberOfPassengers;
    }

    public void setNumberOfPassengers(Integer numberOfPassengers) {
        this.numberOfPassengers = numberOfPassengers;
    }

    @Override
    public String toString() {
        return "PassengerVehicle{" + "numberOfPassengers=" + numberOfPassengers + '}';
    }
    
    
}
