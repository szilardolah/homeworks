package com.szilardolah.jpa.bean.person;

import com.szilardolah.jpa.bean.vehicle.PassengerVehicle;
import com.szilardolah.jpa.bean.vehicle.TransportationVehicle;
import com.szilardolah.jpa.enums.Sex;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Szilard <szilard.olah@yahoo.com>
 */
@Entity
@NamedQueries({
        @NamedQuery(name = "Person.ListPersons", query = "select p from Person p"),
        @NamedQuery(name = "Person.getPerson", query = "select p from Person p where p.name = :paramName"),
        @NamedQuery(name = "Person.listFemales", query = "select p from Person p WHERE p.name = 'FEMALE'"),
        @NamedQuery(name = "Person.listMales", query = "select p from Person p WHERE p.name = 'MALE'")
})
public class Person implements Serializable {

    @Id @GeneratedValue
    private Long id;
    
    private String name;
    
    @Enumerated(EnumType.STRING)
    private Sex sex;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateOfBirth;
    
    @ElementCollection
    private Collection<Address> addresses;

    @ManyToMany
    @JoinTable(name = "person_passengervehicle", 
               joinColumns = @JoinColumn(name = "person_fk"),
               inverseJoinColumns = @JoinColumn (name = "vehicle_fk"))
    private List<PassengerVehicle> passengerVehicles;
    
    @OneToMany
    @JoinTable(name = "person_transportvehicle", 
               joinColumns = @JoinColumn(name = "person_fk"),
               inverseJoinColumns = @JoinColumn (name = "vehicle_fk"))
    private List<TransportationVehicle> transportationVehicles;

    public Person() {
        this.transportationVehicles = new ArrayList<>();
        this.passengerVehicles = new ArrayList<>();
        this.addresses = new HashSet<>();
    }

    public Person(String name, Sex sex, Date dateOfBirth) {
        this.transportationVehicles = new ArrayList<>();
        this.passengerVehicles = new ArrayList<>();
        this.addresses = new HashSet<>();
        this.name = name;
        this.sex = sex;
        this.dateOfBirth = dateOfBirth;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Collection<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(Collection<Address> addresses) {
        this.addresses = addresses;
    }

    public List<PassengerVehicle> getPassengerVehicles() {
        return passengerVehicles;
    }

    public void setPassengerVehicles(List<PassengerVehicle> passengerVehicles) {
        this.passengerVehicles = passengerVehicles;
    }

    public List<TransportationVehicle> getTransportationVehicles() {
        return transportationVehicles;
    }

    public void setTransportationVehicles(List<TransportationVehicle> transportationVehicles) {
        this.transportationVehicles = transportationVehicles;
    }



    @Override
    public String toString() {
        return "Person{" + "id=" + id + ", name=" + name + ", sex=" + sex + ", dateOfBirth=" + dateOfBirth + ", addresses=" + addresses + ", passengerVehicles=" + passengerVehicles + ", transportationVehicles=" + transportationVehicles + '}';
    }
}
