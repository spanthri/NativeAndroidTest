package com.coke.adopters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.coke.R;
import com.coke.restobjects.Accounts;

/**
 * Created by spanthri on 02/03/16.
 */
public class AccountListAdapter extends ArrayAdapter{

    private  Context context;
    private  Accounts[] accounts;
    public AccountListAdapter(Context _context, Accounts[] _accounts) {
        super(_context, -1, _accounts);
        this.accounts = _accounts;
        this.context = _context;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.rowlayout, parent, false);

        TextView textView = (TextView) rowView.findViewById(R.id.txt1);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
        Accounts account1 = accounts[position];
        textView.setText(account1.getAccountName());

       // TextView textView2 = (TextView) rowView.findViewById(R.id.txt2);
       // textView2.setText(account1.getAccountId());

        return rowView;
    }

}
