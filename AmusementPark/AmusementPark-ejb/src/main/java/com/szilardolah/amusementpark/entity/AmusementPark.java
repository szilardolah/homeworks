package com.szilardolah.amusementpark.entity;

import com.szilardolah.amusementpark.bean.Address;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author Szilard <szilard.olah@yahoo.com>
 */

@Entity
@Table(name = "park")
@NamedQueries({
    @NamedQuery(name = "AmusementPark.machineList", query = "SELECT l FROM AmusementPark a INNER JOIN a.machineList l WHERE a.id=:park_id"),
    @NamedQuery(name = "AmusementPark.listParksWithFund", query = "SELECT a FROM AmusementPark a WHERE a.fund > 0")
})
public class AmusementPark implements Serializable {
   
    public static final String PARK_IS_UNKNOWN = "Park is unknown.";
    
    public static final String NO_ENOUGH_MONEY = "Park hasn't got enough money.";
    
    public static final String NO_ENOUGH_AREA = "Park hasn't got enough area.";
   
    public static final String PARK_HAS_MACHINE  = "Park has already this machine.";
    
    public static final String PARK_HAS_NOT_MACHINE  = "Park hasn't already this machine.";

    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    
    @Column(nullable = false, unique = true)
    private String name;
    
    @Embedded
    private Address address;

    @Column(nullable = false)
    private Long fund;
    
    @Column(name = "area_km", nullable = false)
    private Long area;

    @OneToMany(fetch = FetchType.LAZY)
    private List<BuyedMachine> machineList;
    
    @OneToMany(fetch = FetchType.LAZY)
    private List<Guest> guestList; 
    
    private Integer ticketPrice;
    
    @OneToOne(cascade = CascadeType.ALL)
    private Guestbook guestbook;
  
    public AmusementPark() {
        this.machineList = new ArrayList<>();
        guestList = new ArrayList<>();
        this.guestbook = new Guestbook(this);
    }

    public AmusementPark(String name, Address address, Long fund, Long area, Integer ticketPrice) {
        this.machineList = new ArrayList<>();
        this.name = name;
        this.address = address;
        this.fund = fund;
        this.area = area;
        this.ticketPrice = ticketPrice;
        this.guestbook = new Guestbook(this);
    }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public Address getAddress() { return address; }

    public void setAddress(Address address) { this.address = address; }

    public Long getFund() { return fund; }

    public void setFund(Long fund) { this.fund = fund; }

    public Long getArea() { return area; }

    public void setArea(Long areaInKm) { this.area = areaInKm; }

    public Integer getTicketPrice() { return ticketPrice; }

    public void setTicketPrice(Integer ticketPrice) { this.ticketPrice = ticketPrice; }
        
    public void addGuest(Guest guest) { guestList.add(guest); }
    
    public void removeGuest(Guest guest) { guestList.remove(guest); }
    
    public boolean hasGuest(Guest guest) { return guestList.contains(guest); }
    
    public Guestbook getGuestbook() { return this.guestbook; }
    
    public Integer numOfGuests(Long machineId) { return getBuyedMachine(machineId).getNumOfGuests(); }
       
    public void addBuyedMachine(BuyedMachine machine) { machineList.add(machine); }
    
    public void removeBuyedMachine(Long machineId) { machineList.remove(getBuyedMachine(machineId)); }
      
    public boolean isOpen(Long machineId) { return getBuyedMachine(machineId).isOpen(); }

    public BuyedMachine getBuyedMachine(Long machineId) {
        for (BuyedMachine bm : machineList) {
            if (bm.getMachine().getId().longValue() == machineId.longValue()) {
                return bm;
            }
        }
        return null;
    }
   
    public boolean hasMachine(Long machineId) {
        return getBuyedMachine(machineId) != null;
    }
    
    public void openBuyedMachine(Long machineId) {
        BuyedMachine bm = getBuyedMachine(machineId);
        bm.setOpen(true);
        machineList.set(machineList.indexOf(bm), bm);
    }
    
    public void closeBuyedMachine(Long machineId) {
        BuyedMachine bm = getBuyedMachine(machineId);
        bm.setOpen(false);
        machineList.set(machineList.indexOf(bm), bm);
    }
    

    public boolean hasFreeSeats(Long machineId) {
        BuyedMachine bm = getBuyedMachine(machineId);
        return bm.getMachine().getSeats() > bm.getNumOfGuests();
    }
    
    public Guest addGuestToMachine(Long machineId, Guest guest) {
        BuyedMachine bm = getBuyedMachine(machineId);
        fund += bm.getMachine().getTicketPrice();
        guest.setMoney(guest.getMoney() -bm.getMachine().getTicketPrice());
        bm.addGuest(guest);
        machineList.set(machineList.indexOf(bm), bm);
        return guest;
    }
    
    public void removeGuestFromMachine(Long machineId, Long guestId) {
        BuyedMachine bm = getBuyedMachine(machineId);
        bm.removeGuest(guestId);
        machineList.set(machineList.indexOf(bm), bm);
    }
        
    public boolean isOldEnough(Long machineId, Guest guest) {
        BuyedMachine bm = getBuyedMachine(machineId);        
        return bm.getMachine().getMinAge() >= guest.getAge();
    }
}
