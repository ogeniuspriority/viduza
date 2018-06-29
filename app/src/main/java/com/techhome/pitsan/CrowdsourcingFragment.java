package com.techhome.pitsan;

import android.app.Dialog;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.techhome.pitsan.back_end.pitsan_load_crowdsourcingsingle_pit_offer_feeds;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import static com.techhome.pitsan.TruckDashboard.username;

public class CrowdsourcingFragment extends Fragment {
    private static final String ARG_SECTION_NUMBER = "section_number";
    static String[] location;
    static String[] volume;
    static String[] date;
    Map entrySelected = new HashMap();
    ListView list;
    int selected = 0;
    ArrayList<Integer> selectedList = new ArrayList<Integer>();
    CrowdsourcingListAdapter crowdsourcingListAdapter;
    private android.support.design.widget.FloatingActionButton viewFab;
    private Dialog replyToRequest;
    private Handler mHandler;
    private ProgressBar progressBar;
    private boolean hasCallback;
    SwipeRefreshLayout swiperefresh;
    public static volatile ArrayList<String> seltChoices_users_for_sending_top = new ArrayList<String>();
    public static volatile ArrayList<String> seltChoices_users_for_sending_top_removed = new ArrayList<String>();
    static volatile boolean menuVisible = true;
    ArrayList<String> seltChoices_users = new ArrayList<String>();
    private Runnable showMore = new Runnable() {
        public void run() {
            boolean noMoreToShow = crowdsourcingListAdapter.showMore(); //show more views and find out if
            progressBar.setVisibility(noMoreToShow ? View.GONE : View.VISIBLE);
            hasCallback = false;
        }
    };


    public CrowdsourcingFragment() {
    }


    public static CrowdsourcingFragment newInstance(String[] d, String[] l, String[] v) {
        CrowdsourcingFragment fragment = new CrowdsourcingFragment();
        date = d;
        location = l;
        volume = v;
        return fragment;
    }

    @Override
    public void onPause() {
        super.onPause();

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        crowdsourcingListAdapter = new CrowdsourcingListAdapter(getActivity(), location, volume, date, 10, 5);
    }

    int count;
    FrameLayout this_is_the_pop;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.fragment_crowdsourcing, container, false);

//        View footer = getLayoutInflater().inflate(R.layout.progress_bar_footer, null);
//        progressBar = footer.findViewById(R.id.progressBar);
        list = rootView.findViewById(R.id.list);
        list.setItemsCanFocus(false);
        list.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);


        swiperefresh = rootView.findViewById(R.id.swiperefresh);
        //-------
        swiperefresh = rootView.findViewById(R.id.swiperefresh);
        swiperefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new pitsan_load_crowdsourcingsingle_pit_offer_feeds(getActivity(), Config.PITSAN_CROWDSOURCING_PIT_DATA_LOAD_FEEDS.toString(), username, swiperefresh, list).execute();
            }
        });
        //-----------
        this_is_the_pop = rootView.findViewById(R.id.this_is_the_pop);
        final TextView the_text_to_count = rootView.findViewById(R.id.the_text_to_count);
        new pitsan_load_crowdsourcingsingle_pit_offer_feeds(getActivity(), Config.PITSAN_CROWDSOURCING_PIT_DATA_LOAD_FEEDS.toString(), username, swiperefresh, list).execute();
        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (seltChoices_users_for_sending_top != null) {
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            for (String k : seltChoices_users_for_sending_top_removed) {
                                try {
                                    seltChoices_users_for_sending_top.remove(seltChoices_users_for_sending_top.indexOf(k));
                                } catch (RuntimeException ff) {

                                }
                            }
                            seltChoices_users_for_sending_top_removed.clear();


                            if (seltChoices_users_for_sending_top.size() == 0) {
                                this_is_the_pop.setVisibility(View.GONE);
                            } else {
                                the_text_to_count.setText("" + seltChoices_users_for_sending_top.size() + "Request(s)");
                                this_is_the_pop.setVisibility(View.VISIBLE);
                            }
                        }
                    });

                }
            }
        }, 0, 200);
        //-------------
        FloatingActionButton fabcrowdsourcing = rootView.findViewById(R.id.fabcrowdsourcing);
        fabcrowdsourcing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        return rootView;
    }


}
