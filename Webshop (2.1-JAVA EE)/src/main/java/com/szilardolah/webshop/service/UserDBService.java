package com.szilardolah.webshop.service;

import com.szilardolah.webshop.bean.UserDTO;
import com.szilardolah.webshop.database.Instance;
import com.szilardolah.webshop.database.UserDB;
import com.szilardolah.webshop.interceptor.Validation;

/**
 *
 * @author Szilard <szilard.olah@yahoo.com>
 */
@Validation
public class UserDBService {
    
    public UserDTO registrateUser(UserDTO user) {
        return UserDB.getInstance(Instance.EXISTING).registrate(user);
    }

}
