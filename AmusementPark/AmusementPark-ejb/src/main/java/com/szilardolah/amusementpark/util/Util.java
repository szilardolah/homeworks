package com.szilardolah.amusementpark.util;

import com.szilardolah.amusementpark.exception.UnknownGuestException;
import com.szilardolah.amusementpark.entity.AmusementPark;
import com.szilardolah.amusementpark.entity.Guest;
import com.szilardolah.amusementpark.entity.Machine;
import com.szilardolah.amusementpark.exception.UnknownAmusementParkException;
import com.szilardolah.amusementpark.exception.UnknownMachineException;
import com.szilardolah.amusementpark.repository.EntityRepository;
import com.szilardolah.amusementpark.entity.Note;
import com.szilardolah.amusementpark.exception.UnknownNoteException;

/**
 *
 * @author Szilard <szilard.olah@yahoo.com>
 */
public final class Util {

    private Util() {}
    
    public static <T> T checkExistence(Class<T> clazz, EntityRepository repo, Long id) {
        T t = repo.find(clazz, id);
        if (t == null) {
            if (clazz == AmusementPark.class) {
                throw new UnknownAmusementParkException(AmusementPark.PARK_IS_UNKNOWN);
            } else if (clazz == Machine.class) {
                throw new UnknownMachineException(Machine.UNKNOWN);
            } else if (clazz == Guest.class) {
                throw new UnknownGuestException(Guest.UNKNOWN);
            } else if (clazz == Note.class) {
                throw new UnknownNoteException(Note.UNKNOWN);
            }
        }
        return t;
    }
}
