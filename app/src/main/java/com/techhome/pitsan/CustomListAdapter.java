package com.techhome.pitsan;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

public class CustomListAdapter extends ArrayAdapter<String> {
    private final Activity context;
    private final String[] companyname;
    private final String[] offertype;
    private final String[] offercomment;
    private final String[] requestnumber;
    private final String[] amount;
    private final String[] date;


    public CustomListAdapter(Activity context1, String[] amount, String[] date, String[] companyname1, String[] offertype1, String[] offercomment1, String[] requestnumber1) {
        super(context1, R.layout.mylist, companyname1);
        this.context = context1;
        this.companyname = companyname1;
        this.offertype = offertype1;
        this.offercomment = offercomment1;
        this.requestnumber = requestnumber1;

        this.date = date;
        this.amount = amount;
    }


    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.mylist, null, true);

        TextView txtname = rowView.findViewById(R.id.company_name);
        TextView txttype = rowView.findViewById(R.id.offer_type);
        TextView txtcomment = rowView.findViewById(R.id.comment);
        TextView txtrequestn = rowView.findViewById(R.id.request_number);
        TextView txtdate = rowView.findViewById(R.id.date);
        TextView txtamount = rowView.findViewById(R.id.amount);
        Button accept = rowView.findViewById(R.id.accept_btn);
        Button pass = rowView.findViewById(R.id.pass_btn);

        txtname.setText(companyname[position]);
        txttype.setText(offertype[position]);
        txtcomment.setText(offercomment[position]);
        txtdate.setText(date[position]);
        txtrequestn.setText(requestnumber[position]);
        txtamount.setText(amount[position]);


        return rowView;

    }

}
