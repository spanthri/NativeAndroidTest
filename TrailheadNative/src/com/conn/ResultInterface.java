package com.conn;

import android.os.IInterface;

import com.coke.restobjects.Accounts;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by spanthri on 02/03/16.
 */
public interface ResultInterface {
    void processFinish( Accounts[] listDatat);
}
