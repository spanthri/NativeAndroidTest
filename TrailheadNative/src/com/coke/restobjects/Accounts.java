package com.coke.restobjects;

import android.location.Address;
import android.location.Location;

/**
 * Created by spanthri on 02/03/16.
 */
public class Accounts {

    String accName = null;
    Address address = null;
    Location loc = null;
    String id = null;

    public Accounts(String _accountName, String _id) {
        accName = _accountName;
        id = _id;
    }

    public String getAccountName() {
       return accName;
    }
    public String getAccountId() {
        return id;
    }
}
