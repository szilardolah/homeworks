package com.szilardolah.jpa.bean.vehicle;

import com.szilardolah.jpa.bean.person.Person;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 *
 * @author Szilard <szilard.olah@yahoo.com>
 */
@Entity
@DiscriminatorValue(value = "Truck")
public class Truck extends TransportationVehicle{

    private Integer numberOfContainers;
    
    @ManyToOne(targetEntity = Person.class)
    private List<Person> owners = new ArrayList<>();
        
    public Truck() {
        super();
    }

    public Truck(Integer numberOfContainers) {
        this.numberOfContainers = numberOfContainers;
    }

    public Truck(Integer loadCapacity, Color color) {
        super(loadCapacity, color);
    }

    public Integer getNumberOfContainers() {
        return numberOfContainers;
    }

    public void setNumberOfContainers(Integer numberOfContainers) {
        this.numberOfContainers = numberOfContainers;
    }

    public List<Person> getOwners() {
        return owners;
    }

    public void setOwners(List<Person> owners) {
        this.owners = owners;
    }

    @Override
    public String toString() {
        return "Truck{" + "numberOfContainers=" + numberOfContainers + ", owners=" + owners + '}';
    }
    
    
}
