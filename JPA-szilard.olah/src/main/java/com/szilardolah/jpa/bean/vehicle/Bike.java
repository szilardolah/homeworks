package com.szilardolah.jpa.bean.vehicle;

import com.szilardolah.jpa.bean.person.Person;
import com.szilardolah.jpa.enums.BikeType;
import java.awt.Color;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToMany;

/**
 *
 * @author Szilard <szilard.olah@yahoo.com>
 */
@Entity
@DiscriminatorValue("Bike")
@AttributeOverride(name = "id", column = @Column(name = "bike_id"))
public class Bike extends PassengerVehicle implements Serializable{
    
    @Enumerated(EnumType.ORDINAL)
    private BikeType bikeType;
    
    private Integer wheelSize;
    
    private boolean bell;
    
    @ManyToMany(mappedBy = "passengerVehicles")
    private List<Person> owners = new ArrayList<>();

    public Bike() {
        super();
    }

    public Bike(BikeType bikeType, Integer wheelSize, boolean bell, Integer numberOfPassengers, Color color) {
        super(numberOfPassengers, color);
        this.bikeType = bikeType;
        this.wheelSize = wheelSize;
        this.bell = bell;
    }

    public BikeType getBikeType() {
        return bikeType;
    }

    public void setBikeType(BikeType bikeType) {
        this.bikeType = bikeType;
    }

    public Integer getWheelSize() {
        return wheelSize;
    }

    public void setWheelSize(Integer wheelSize) {
        this.wheelSize = wheelSize;
    }

    public boolean isBell() {
        return bell;
    }

    public void setBell(boolean bell) {
        this.bell = bell;
    }

    public List<Person> getOwners() {
        return owners;
    }

    public void setOwners(List<Person> owners) {
        this.owners = owners;
    }

    @Override
    public String toString() {
        return "Bike{" + "bikeType=" + bikeType + ", wheelSize=" + wheelSize + ", bell=" + bell + ", owners=" + owners + '}';
    }

    
}
