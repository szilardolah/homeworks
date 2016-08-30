package com.szilardolah.amusementpark.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Szilard <szilard.olah@yahoo.com>
 */
@Entity
@NamedQuery(name = "Note.getNoteByGuest", query = "SELECT n FROM Note n WHERE n.guestbook.park.id=:park_id AND n.guest.id=:guest_id")
public class Note implements Serializable {
    
    public static final String UNKNOWN = "Note is unknown.";

    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    
    @NotNull
    private String text;
    
    @OneToOne
    private Guest guest;
    
    @ManyToOne
    private Guestbook guestbook;
          
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "time")
    private Date timestamp;

    public Note() {
        // default constructor 
    }

    public Note(Guestbook guestbook, Guest guest, String text) {
        this.text = text;
        this.guest = guest;
        this.guestbook = guestbook;
        this.timestamp = new Date();
    }

    public Long getId() {  return id; }

    public void setId(Long id) {  this.id = id; }

    public String getText() { return text; }

    public void setText(String text) { this.text = text; }

    public Guest getGuest() { return guest; }

    public void setGuest(Guest guest) { this.guest = guest; }

    public Guestbook getGuestbook() { return guestbook; }

    public void setGuestbook(Guestbook guestbook) { this.guestbook = guestbook; }

    public Date getTimestamp() { return timestamp; }

    public void setTimestamp(Date timestamp) { this.timestamp = timestamp; }

    @Override
    public String toString() {
        return "Note{" +
                "id=" + id + 
                ", text=" + text + 
                ", guest=" + guest + 
                ", guestbook=" + guestbook + 
                ", timestamp=" + timestamp + '}';
    }
    
}
