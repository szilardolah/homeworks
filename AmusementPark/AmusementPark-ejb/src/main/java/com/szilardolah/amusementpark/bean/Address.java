package com.szilardolah.amusementpark.bean;

import java.io.Serializable;
import javax.persistence.Embeddable;

/**
 *
 * @author Szilard <szilard.olah@yahoo.com>
 */

@Embeddable
public class Address implements Serializable {

    private String postalCode;
    
    private String country;
    
    private String city;
    
    private String street;
    
    private String houseNumber;

    public Address() {
        // default constructor
    }

    public Address(String postalCode, String country, String city, String street, String houseNumber) {
        this.postalCode = postalCode;
        this.country = country;
        this.city = city;
        this.street = street;
        this.houseNumber = houseNumber;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }


    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }
    
    
}
