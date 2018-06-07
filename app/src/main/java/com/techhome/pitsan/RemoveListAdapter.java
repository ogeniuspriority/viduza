package com.techhome.pitsan;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

public class RemoveListAdapter extends ArrayAdapter<String> {
    private final Activity context;

    private final String[] offertype;
    private final String[] offerlocation;
    private final String[] requestnumber;

    private final String[] date;

    public RemoveListAdapter(Activity context1, String[] offertype, String[] offerlocation, String[] requestnumber, String[] date) {
        super(context1, R.layout.removelist, offerlocation);
        this.context = context1;
        this.offertype = offertype;
        this.offerlocation = offerlocation;
        this.requestnumber = requestnumber;
        this.date = date;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.removelist, null, true);
        TextView txttype = rowView.findViewById(R.id.remove_type);
        TextView txtrequestn = rowView.findViewById(R.id.remove_request_number);
        TextView txtdate = rowView.findViewById(R.id.remove_dateposted);
        TextView txtlocation = rowView.findViewById(R.id.remove_offer_location);
        Button remove = rowView.findViewById(R.id.remove_btn);
        txttype.setText(offertype[position]);
        txtdate.setText(date[position]);
        txtrequestn.setText(requestnumber[position]);
        txtlocation.setText(offerlocation[position]);
        return rowView;
    }
}
