package com.szilardolah.jpa.bean.vehicle;

import java.awt.Color;
import java.io.Serializable;
import javax.persistence.DiscriminatorColumn;
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
@DiscriminatorColumn(name = "disc")
@DiscriminatorValue(value = "Transportation Vehicle")
public class TransportationVehicle extends Vehicle implements Serializable{

    private Integer loadCapacity;

    public TransportationVehicle() {
        super();
    }

    public TransportationVehicle(Integer loadCapacity, Color color) {
        super(color);
        this.loadCapacity = loadCapacity;
    }

    public Integer getLoadCapacity() {
        return loadCapacity;
    }

    public void setLoadCapacity(Integer loadCapacity) {
        this.loadCapacity = loadCapacity;
    }

    @Override
    public String toString() {
        return "TransportationVehicle{" + "loadCapacity=" + loadCapacity + '}';
    }

    
}
