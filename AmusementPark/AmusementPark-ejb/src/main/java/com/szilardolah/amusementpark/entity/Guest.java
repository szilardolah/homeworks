package com.szilardolah.amusementpark.entity;

import com.szilardolah.amusementpark.enums.State;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Szilard <szilard.olah@yahoo.com>
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "Guest.guestListWithState", query = "SELECT G FROM AmusementPark A INNER JOIN A.guestList G WHERE G.state = com.szilardolah.amusementpark.enums.State.REST and A.id=:park_id"),
    @NamedQuery(name = "Guest.listGuestsOfMachine", query = "SELECT g FROM AmusementPark a INNER JOIN a.machineList m INNER JOIN m.guests g WHERE a.id=:park_id AND m.machine.id=:machine_id"),

})
public class Guest implements Serializable{
    public static final String UNKNOWN = "Guest is unknown.";
    
    public static final String NOT_FOUND = "The guest can not be found in the park.";
   
    public static final String TOO_JOUNG = "Guest is too young.";
    
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Enumerated(EnumType.STRING) @Column(name = "state_of_guest")
    private State state;
    
    private Long money;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateOfEnter;
    
    private Integer age;
    
    @OneToOne(mappedBy = "guest")   
    private Note note;

    public Guest() { 
        // default constructor 
    }

    public Guest(State state, Long money, Integer age) {
        this.state = state;
        this.money = money;
        this.dateOfEnter = new Date();
        this.age = age;
    }

    public State getState() { return state; }

    public void setState(State state) { this.state = state; }

    public Long getMoney() { return money; }

    public void setMoney(Long money) { this.money = money; }

    public Date getDateOfEnter() { return dateOfEnter; }

    public void setDateOfEnter(Date dateOfEnter) { this.dateOfEnter = dateOfEnter; }

    public Integer getAge() { return age; }

    public void setAge(Integer age) { this.age = age; }
    
    public Long getId() { return id; }

    public void setId(Long id) { this.id = id;
    }
}
