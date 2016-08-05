package com.szilardolah.jpa.bean.vehicle;

import com.szilardolah.jpa.bean.person.Person;
import com.szilardolah.jpa.enums.GearShift;
import java.awt.Color;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Szilard <szilard.olah@yahoo.com>
 */

@Entity
@DiscriminatorValue("Car")
@AttributeOverride(name = "id", column = @Column(name = "car_id"))
@NamedQueries({
        @NamedQuery(name = "Car.listCarsByGearShift", query = "select c from Car c WHERE c.gearShiftType=:paramGearShift"),
        @NamedQuery(name = "Car.getLatestCar", query = "SELECT c FROM Car c ORDER BY c.registrationDate asc")
})
public class Car extends PassengerVehicle implements Serializable{

    @Enumerated(EnumType.STRING)
    private GearShift gearShiftType;
    
    @Temporal(TemporalType.DATE)
    private Date registrationDate;

    @ManyToMany(mappedBy = "passengerVehicles")
    private List<Person> owners = new ArrayList<>();
        
    public Car() {
        super();
    }

    public Car(GearShift gearShiftType, Date registrationDate, Integer numberOfPassengers, Color color) {
        super(numberOfPassengers, color);
        this.gearShiftType = gearShiftType;
        this.registrationDate = registrationDate;
    }

    public GearShift getGearShiftType() {
        return gearShiftType;
    }

    public void setGearShiftType(GearShift gearShiftType) {
        this.gearShiftType = gearShiftType;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public List<Person> getOwners() {
        return owners;
    }

    public void setOwners(List<Person> owners) {
        this.owners = owners;
    }

    @Override
    public String toString() {
        return "Car{" + "gearShiftType=" + gearShiftType + ", registrationDate=" + registrationDate + ", owners=" + owners + '}';
    }
    
    
}
