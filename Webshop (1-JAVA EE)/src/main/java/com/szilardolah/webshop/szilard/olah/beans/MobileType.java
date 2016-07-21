package com.szilardolah.webshop.szilard.olah.beans;

import com.szilardolah.webshop.szilard.olah.constraints.MobileColor;
import com.szilardolah.webshop.szilard.olah.enums.Color;
import com.szilardolah.webshop.szilard.olah.enums.Currency;
import com.szilardolah.webshop.szilard.olah.enums.Manufacturer;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Szilard <szilard.olah@yahoo.com>
 */
@MobileColor
public class MobileType {  
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
    
    
    
}
