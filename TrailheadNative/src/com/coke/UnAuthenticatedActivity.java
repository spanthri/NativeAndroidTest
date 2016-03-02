package com.coke;

import android.accounts.Account;
import android.app.Activity;
import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.coke.adopters.AccountListAdapter;
import com.coke.restobjects.Accounts;
import com.conn.ResultInterface;

import java.util.ArrayList;
import java.util.List;


public class UnAuthenticatedActivity extends Activity implements ResultInterface
{
    AccountListAdapter accountListAdapter =null;
    //public final static String apiURL = "https://panthrirestapitest-developer-edition.na30.force.com/services/apexrest/Widgets";
    public final static String apiURL = "https://panthrirestapitest-developer-edition.na30.force.com/services/apexrest/Accounts";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CallApi callAPI = new CallApi();
        callAPI.delegate = this;
        callAPI.execute(apiURL);
        //callAPI.listData.



    }
    public AccountListAdapter getListAdapter() {
        return accountListAdapter;
    }

//    @Override
//    public void processFinish(ArrayList arrayList) {
//        ListView lstView = new ListView(this);
//       //Adapter adapter = new ArrayAdapter().add(arrayList);
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
//                android.R.layout.simple_list_item_1, android.R.id.text1, arrayList);
//
//       lstView.setAdapter(adapter);
//        System.out.println("ArrayList "+arrayList);
//        setContentView(lstView);
//
//    }

    @Override
    public void processFinish(Accounts[] listDatat) {
        ListView listView = new ListView(this);
        accountListAdapter = new AccountListAdapter(this, listDatat);
        listView.setAdapter(accountListAdapter);
        setContentView(listView);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                final Accounts account = (Accounts) parent.getItemAtPosition(position);
                Toast.makeText(getApplicationContext(),
                        "Account Id:  " + account.getAccountId(), Toast.LENGTH_LONG)
                        .show();
            }
        });


    }



}
