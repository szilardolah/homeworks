package com.szilardolah.jpa.bean.vehicle;

import java.awt.Color;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 *
 * @author Szilard <szilard.olah@yahoo.com>
 */
@MappedSuperclass
public class Vehicle {
    
    @Id @GeneratedValue
    private Long id;
    
    private Color color;

    public Vehicle() {
        // Default Constructor
    }

    public Vehicle(Color color) {
        this.color = color;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    
}
