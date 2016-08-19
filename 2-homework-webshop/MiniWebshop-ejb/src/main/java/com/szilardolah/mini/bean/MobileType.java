package com.szilardolah.mini.bean;


import com.szilardolah.mini.annotation.WebshopBean;
import com.szilardolah.mini.constraint.MobileColor;
import com.szilardolah.mini.enums.Color;
import com.szilardolah.mini.enums.Currency;
import com.szilardolah.mini.enums.Manufacturer;
import java.io.Serializable;
import java.util.Objects;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Szilard <szilard.olah@yahoo.com>
 */
@WebshopBean
@MobileColor
public class MobileType implements Serializable{  
    @NotNull @Size( min = 36, max = 36 )
    private String id;
    
    @NotNull @Size( min = 3 )
    private String type;
    
    @NotNull
    private Manufacturer manufacturer;
    
    @NotNull @Min(1)
    private Integer price;
    
    @NotNull
    private Currency currency;
    
    @NotNull
    private Color color;

    
    public MobileType() {
        //Default constructor for ObjectMapper
    }
    
    public MobileType(String type, Manufacturer manufacturer, Integer price, Currency currency, Color color) {
        this.type = type;
        this.manufacturer = manufacturer;
        this.price = price;
        this.currency = currency;
        this.color = color;
    }
    
    public MobileType(String id, String type, Manufacturer manufacturer, Integer price, Currency currency, Color color) {
        this.id = id;
        this.type = type;
        this.manufacturer = manufacturer;
        this.price = price;
        this.currency = currency;
        this.color = color;
    }

    
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }


    @Override
    public int hashCode() {
        int hash = 7;
        hash = 73 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final MobileType other = (MobileType) obj;
        return Objects.equals(this.id, other.id);
    }

    
    
    
    
}
