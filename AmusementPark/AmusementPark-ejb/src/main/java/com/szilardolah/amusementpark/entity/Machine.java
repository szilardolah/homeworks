package com.szilardolah.amusementpark.entity;

import com.szilardolah.amusementpark.enums.Type;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Szilard <szilard.olah@yahoo.com>
 */
@Entity
public class Machine implements Serializable {

    public static final String UNKNOWN = "Machine is unknown.";   
   
    public static final String NOT_FOUND = "The machine can not be found in the park."; 
  
    public static final String MACHINE_OPEN = "Machine has already opened.";
   
    public static final String MACHINE_CLOSE = "Machine has already closed.";
   
    public static final String CANT_CLOSE_BY_GUESTS = "Can't close. Guests are on the board!";
    
    public static final String MACHINE_IS_FULL = "Machine is full.";
    
    public static final String STATE_IS_REST = "Guest already in REST state.";
  
    public static final String STATE_IS_ON_BOARD = "Guest already in ON_BOARD state.";

    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id; 
    
    @Column(name = "machine_alias", nullable = false, unique = true)
    private String alias;
    
    @Column(name = "machine_size")
    private Long size;
    
    @Column(nullable = false)
    private Long ticketPrice;
    
    @Column(nullable = false)
    private Long machinePrice;
    
    private Integer seats;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Type type;
    
    private Integer minAge;

    public Machine() {
        // default constructor
    }

    public Machine(String alias, Long size, Long ticketPrice, Long machinePrice, Integer seats, Type type, Integer minAge) {
        this.alias = alias;
        this.size = size;
        this.ticketPrice = ticketPrice;
        this.machinePrice = machinePrice;
        this.seats = seats;
        this.type = type;
        this.minAge = minAge;
    }

    public Long getTicketPrice() { return ticketPrice; }

    public void setTicketPrice(Long ticketPrice) { this.ticketPrice = ticketPrice; }

    public Long getMachinePrice() { return machinePrice; }

    public void setMachinePrice(Long machinePrice) { this.machinePrice = machinePrice; }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getAlias() { return alias; }

    public void setAlias(String alias) { this.alias = alias; }

    public Long getSize() { return size; }

    public void setSize(Long size) { this.size = size; }

    public Integer getSeats() { return seats; }

    public void setSeats(Integer seats) { this.seats = seats; }

    public Type getType() { return type; }

    public void setType(Type type) { this.type = type; }

    public Integer getMinAge() { return minAge; }

    public void setMinAge(Integer minAge) { this.minAge = minAge; }  
    
}
