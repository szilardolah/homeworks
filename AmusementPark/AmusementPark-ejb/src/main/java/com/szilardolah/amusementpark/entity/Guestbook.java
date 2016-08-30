package com.szilardolah.amusementpark.entity;

import java.io.Serializable;
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
public class Guestbook implements Serializable{
   
    @Id @GeneratedValue( strategy = GenerationType.SEQUENCE )
    private Long id;
   
    @OneToOne
    private AmusementPark park;
    
    @OneToMany
    private List<Note> notes;

    public Guestbook() { 
        // default constructor
    }

    public Guestbook(AmusementPark park) { this.park = park; }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public AmusementPark getPark() { return park; }

    public void setPark(AmusementPark park) { this.park = park; }

    public List<Note> getNotes() { return notes; }

    public void setNotes(List<Note> notes) { this.notes = notes; }
    
    public Note addNote(Guest guest, String text) {
        Note note = new Note(this, guest, text);
        notes.add(note);
        return note;
    }
}
