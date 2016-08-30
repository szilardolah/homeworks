package com.szilardolah.amusementpark.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 *
 * @author Szilard <szilard.olah@yahoo.com>
 */
@Entity
public class BuyedMachine implements Serializable{
    
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
        
    private Boolean open;
    
    @OneToMany
    private List<Guest> guests;
    
    @OneToOne
    private Machine machine;

    public BuyedMachine() { guests = new ArrayList<>(); }

    public BuyedMachine(Boolean open, Machine machine) {
        this.open = open;
        this.machine = machine;
    }

    public void setOpen(Boolean open) { this.open = open; }
    
    public boolean isOpen() { return open; }

    public Integer getNumOfGuests() { return guests.size(); }

    public void addGuest(Guest guest) { this.guests.add(guest); }

    public Guest removeGuest(Long guestId) {
        for (Guest g : guests) {
            if (g.getId().longValue() == guestId.longValue()) {
                guests.remove(g);
                return g;
            }
        }
        throw new IllegalStateException(Guest.NOT_FOUND);
    }

    public Machine getMachine() { return machine; }

    public void setMachine(Machine machine) { this.machine = machine; }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }
      
}
