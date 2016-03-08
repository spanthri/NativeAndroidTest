package com.conn;

import java.util.ArrayList;

/**
 * Created by spanthri on 07/03/16.
 */
public interface RestMethodCalls {

    ArrayList requestGetURL(String connUrl);
    void requestPost(String connUrl);
    void requestDelete();



}
