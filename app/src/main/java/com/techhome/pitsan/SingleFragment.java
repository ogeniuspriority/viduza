package com.techhome.pitsan;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.techhome.pitsan.back_end.pitsan_load_single_pit_offer_feeds;

import static com.techhome.pitsan.TruckDashboard.username;

public class SingleFragment extends Fragment {
    private static final String ARG_SECTION_NUMBER = "section_number";
    static String[] location;
    static String[] volume;
    static String[] date;
    SingleListAdapter singleListAdapter;
    boolean selected = false;
    int selected_position;
    private ListView singleList;
    private android.support.design.widget.FloatingActionButton viewFab;
    private Dialog replyToSingleRequest;
    SwipeRefreshLayout swiperefresh;

    public SingleFragment() {
    }

    public static SingleFragment newInstance(String[] d, String[] l, String[] v) {
        SingleFragment fragment = new SingleFragment();
        date = d;
        location = l;
        volume = v;
        return fragment;

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        singleListAdapter = new SingleListAdapter(getActivity(), location, volume, date);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_single, container, false);
        singleList = rootView.findViewById(R.id.list);
        singleList.setItemsCanFocus(false);
        //singleList.setAdapter(singleListAdapter);
        viewFab = rootView.findViewById(R.id.fabsingle);
        viewFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog replyToRequest = new Dialog(getContext());
                replyToRequest.setContentView(R.layout.dialog_replytorequest);
                TextView text_selected = replyToRequest.findViewById(R.id.textView6);
                text_selected.setText(" requests selected");
                replyToRequest.show();
            }
        });
        singleList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                if (!selected) {
                    view.setBackgroundColor(getResources().getColor(R.color.colorAccent));

                    selected = true;
                    selected_position = position;
                    viewFab.setVisibility(View.VISIBLE);

                } else {
                    Toast.makeText(getContext(), "Reply to one request at a time", Toast.LENGTH_SHORT).show();
                }
                return true;
            }
        });
        singleList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (selected_position == position) {
                    view.setBackgroundColor(getResources().getColor(R.color.colorTransparentWhite));

                    selected = false;
                    selected_position = -1;
                    viewFab.setVisibility(View.GONE);
                }
            }
        });
        //-------
        swiperefresh = rootView.findViewById(R.id.swiperefresh);
        swiperefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new pitsan_load_single_pit_offer_feeds(getActivity(), Config.PITSAN_SINGLE_PIT_DATA_LOAD_FEEDS.toString(), username, swiperefresh, singleList).execute();
            }
        });
        //-----------
        new pitsan_load_single_pit_offer_feeds(getActivity(), Config.PITSAN_SINGLE_PIT_DATA_LOAD_FEEDS.toString(), username, swiperefresh, singleList).execute();



        return rootView;
    }

}
