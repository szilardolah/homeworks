package com.szilardolah.webshop.service;

import com.szilardolah.webshop.bean.MobileType;
import com.szilardolah.webshop.database.Instance;
import com.szilardolah.webshop.database.MobileInventory;
import com.szilardolah.webshop.interceptor.Validation;

/**
 *
 * @author Szilard <szilard.olah@yahoo.com>
 */
@Validation
public class MobileInventoryService {
    
    public  MobileType addMobileType(MobileType mobileType) {
        return MobileInventory.getInstance(Instance.EXISTING).addNewMobileType(mobileType);
    }

}
