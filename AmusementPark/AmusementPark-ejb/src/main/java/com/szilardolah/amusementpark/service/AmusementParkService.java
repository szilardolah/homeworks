package com.szilardolah.amusementpark.service;

import com.szilardolah.amusementpark.exception.NoEnoughMoneyException;
import com.szilardolah.amusementpark.exception.MachineIsOpenException;
import com.szilardolah.amusementpark.exception.MachineIsFullException;
import com.szilardolah.amusementpark.exception.MachineIsClosedException;
import com.szilardolah.amusementpark.exception.MachineAlreadyInTheParkException;
import com.szilardolah.amusementpark.exception.GuestStateException;
import com.szilardolah.amusementpark.exception.GuestIsAlreadyInTheParkException;
import com.szilardolah.amusementpark.exception.NotEnoughMoneyException;
import com.szilardolah.amusementpark.entity.AmusementPark;
import com.szilardolah.amusementpark.entity.BuyedMachine;
import com.szilardolah.amusementpark.entity.Guest;
import com.szilardolah.amusementpark.entity.Guestbook;
import com.szilardolah.amusementpark.entity.Machine;
import com.szilardolah.amusementpark.entity.Note;
import com.szilardolah.amusementpark.enums.State;
import com.szilardolah.amusementpark.exception.NotEnoughAreaException;
import com.szilardolah.amusementpark.exception.UnknownGuestException;
import com.szilardolah.amusementpark.exception.UnknownMachineException;
import com.szilardolah.amusementpark.repository.AmusementParkRepository;
import com.szilardolah.amusementpark.repository.GuestRepository;
import com.szilardolah.amusementpark.repository.GuestbookRepository;
import com.szilardolah.amusementpark.repository.MachineRepository;
import com.szilardolah.amusementpark.repository.NoteRepository;
import com.szilardolah.amusementpark.util.Util;
import java.io.Serializable;
import java.util.Collection;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Szilard <szilard.olah@yahoo.com>
 */
@Stateless
public class AmusementParkService implements Serializable {

    @Inject
    private AmusementParkRepository parkRepo;
    
    @Inject
    private MachineRepository machineRepo;
   
    @Inject
    private GuestRepository guestRepo;
    
    @Inject
    private GuestbookRepository bookRepo;
    
    @Inject
    private NoteRepository noteRepo;
    
    public AmusementPark createPark(AmusementPark entity) {
        return parkRepo.create(entity);
    }
    
    public AmusementPark deletePark(Long id) {
        Util.checkExistence(AmusementPark.class, parkRepo, id);
        return parkRepo.delete(AmusementPark.class, id);
    }
    
    public AmusementPark modifyPark(AmusementPark entity) {
        Util.checkExistence(AmusementPark.class, parkRepo, entity.getId());
        return parkRepo.update(entity);
    }

    public AmusementPark get(Long id) {
       return Util.checkExistence(AmusementPark.class, parkRepo, id);
    }

    public Collection<AmusementPark> values() {
        return parkRepo.values();
    }

    public void addMachine(Long parkId, Long machineId) {
        Machine machine = Util.checkExistence(Machine.class, machineRepo, machineId);
        AmusementPark a = Util.checkExistence(AmusementPark.class, parkRepo, parkId);
        if (a.hasMachine(machineId)) {
            throw new MachineAlreadyInTheParkException(AmusementPark.PARK_HAS_MACHINE);
        }
        if (a.getArea() < machine.getSize()) {
            throw new NotEnoughAreaException(AmusementPark.NO_ENOUGH_AREA);
        }
        if (a.getFund() < machine.getMachinePrice()) {
            throw new NotEnoughMoneyException(AmusementPark.NO_ENOUGH_MONEY);
        }
        a.setArea(a.getArea() - machine.getSize());
        a.setFund(a.getFund() - machine.getMachinePrice());
        BuyedMachine bm = new BuyedMachine(false, machine);
        parkRepo.create(bm);
        a.addBuyedMachine(bm);
        parkRepo.update(a);
    }
    
    public void openMachine(Long parkId, Long machineId) {
        Util.checkExistence(Machine.class, machineRepo, machineId);
        AmusementPark a = Util.checkExistence(AmusementPark.class, parkRepo, parkId);
        if (!a.hasMachine(machineId)) {
            throw new UnknownMachineException(AmusementPark.PARK_HAS_NOT_MACHINE);
        }
        if (a.isOpen(machineId)) {
            throw new MachineIsOpenException(Machine.MACHINE_OPEN);
        }
        a.openBuyedMachine(machineId);
        parkRepo.update(a);
    }
      
    public void dropMachine(Long parkId, Long machineId) {
        Machine machine = Util.checkExistence(Machine.class, machineRepo, machineId);
        AmusementPark a = Util.checkExistence(AmusementPark.class, parkRepo, parkId);
        if (!a.hasMachine(machineId)) {
            throw new UnknownMachineException(Machine.UNKNOWN);
        }
        if (a.isOpen(machineId)) {
            throw new MachineIsOpenException(Machine.MACHINE_OPEN);
        }
        a.setArea(a.getArea() + machine.getSize());
        a.setFund(a.getFund() + machine.getMachinePrice());
        a.removeBuyedMachine(machineId);
        parkRepo.update(a);
    }
    
    public void closeMachine(Long parkId, Long machineId) {
        Util.checkExistence(Machine.class, machineRepo, machineId);
        AmusementPark a = Util.checkExistence(AmusementPark.class, parkRepo, parkId);
        checkMachineState(a, machineId);
        if (a.numOfGuests(machineId) > 0) {
            throw new IllegalStateException(Machine.CANT_CLOSE_BY_GUESTS);
        }
        a.closeBuyedMachine(machineId);
        parkRepo.update(a);
    }
    
    public Collection<BuyedMachine> machineValues(Long parkId) {
        return parkRepo.machineValues(parkId);
    }
    
    public void addGuestToMachine(Long parkId, Long machineId, Long guestId) {
        Util.checkExistence(Machine.class, machineRepo, machineId);
        AmusementPark a = Util.checkExistence(AmusementPark.class, parkRepo, parkId);
        Guest g = Util.checkExistence(Guest.class, guestRepo, guestId);
        checkMachineState(a, machineId);
        if (!a.hasFreeSeats(machineId)) {
            throw new MachineIsFullException(Machine.MACHINE_IS_FULL);
        }       
        if (!a.hasGuest(g)) {
            throw new IllegalStateException();
        }
        if (g.getState() == State.ON_MACHINE) {
            throw new GuestStateException(Guest.NOT_FOUND);
        }
        if (a.isOldEnough(machineId, g)) {
            throw new IllegalStateException(Guest.TOO_JOUNG);
        }
        g.setState(State.ON_MACHINE);
        g = a.addGuestToMachine(machineId, g);
        guestRepo.update(g);
        parkRepo.update(a);
    }
    
    public void removeGuestFromMachine(Long parkId, Long machineId, Long guestId) {
        Util.checkExistence(Machine.class, machineRepo, machineId);
        AmusementPark a = Util.checkExistence(AmusementPark.class, parkRepo, parkId);
        Guest g = Util.checkExistence(Guest.class, guestRepo, guestId);
        if (!a.isOpen(machineId)) {
            throw new MachineIsClosedException(Machine.MACHINE_CLOSE);
        }
        if (!a.hasGuest(g)) {
            throw new UnknownGuestException(Guest.NOT_FOUND);
        }
        if (g.getState() == State.REST) {
            throw new GuestStateException(Machine.STATE_IS_REST);
        }
        g.setState(State.REST);
        a.removeGuestFromMachine(machineId, guestId);
        guestRepo.update(g);
        parkRepo.update(a);
    }
    
    public void addGuestToPark(Long parkId, Long guestId) {
        AmusementPark a = Util.checkExistence(AmusementPark.class, parkRepo, parkId);
        Guest g = Util.checkExistence(Guest.class, guestRepo, guestId);
        if (a.hasGuest(g)) {
            throw new GuestIsAlreadyInTheParkException();
        }
        if (g.getMoney() < a.getTicketPrice()) {
            throw new NoEnoughMoneyException();
        }
        a.setFund(a.getFund() + a.getTicketPrice());
        g.setMoney(g.getMoney() - a.getTicketPrice());
        a.addGuest(g);
        guestRepo.update(g);
        parkRepo.update(a);
    }
    
    public void removeGuestFromPark(Long parkId, Long guestId) {
        AmusementPark a = Util.checkExistence(AmusementPark.class, parkRepo, parkId);
        Guest g = Util.checkExistence(Guest.class, guestRepo, guestId);
        if (!a.hasGuest(g)) {
            throw new UnknownGuestException(Guest.NOT_FOUND);
        }
        if (g.getState() == State.ON_MACHINE) {
            throw new IllegalStateException(Machine.STATE_IS_ON_BOARD);
        }
        a.removeGuest(g);
        parkRepo.update(a);
    }
    
    public Note writeNote(Long parkId, Long guestId, String text) {
        AmusementPark a = Util.checkExistence(AmusementPark.class, parkRepo, parkId);
        Guest g = Util.checkExistence(Guest.class, guestRepo, guestId);
        if (!a.hasGuest(g)) {
            throw new UnknownGuestException(Guest.NOT_FOUND);
        }
        Guestbook book = a.getGuestbook();
        Note note = book.addNote(g, text);
        noteRepo.create(note);
        bookRepo.update(book);
        return note;
    }
    
    public Collection<AmusementPark> getWithNonZeroFund() {
        return parkRepo.getParksWithNonZeroFund();
    }  
    
    private static void checkMachineState(AmusementPark a, Long machineId) {
        if (!a.hasMachine(machineId)) {
            throw new UnknownMachineException(AmusementPark.PARK_HAS_NOT_MACHINE);
        }
        if (!a.isOpen(machineId)) {
            throw new MachineIsClosedException(Machine.MACHINE_CLOSE);
        }
    }
    
}
