package com.techhome.pitsan;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.techhome.pitsan.back_end.pitsan_load_single_pit_offer_feeds;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

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
    public static volatile ArrayList<String> seltChoices_users_for_sending_top = new ArrayList<String>();
    public static volatile ArrayList<String> seltChoices_users_for_sending_top_removed = new ArrayList<String>();


    int count;
    ArrayList<String> seltChoices_users = new ArrayList<String>();

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
        singleList.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        singleList.setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {
            @Override
            public void onItemCheckedStateChanged(ActionMode mode, int position, long id, boolean checked) {
                count++;
                seltChoices_users.add("" + position);
                mode.setTitle(count + " request(s) selected!");
            }

            @Override
            public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                MenuInflater inflater1_ = mode.getMenuInflater();
                inflater1_.inflate(R.menu.multiselection_menus, menu);

                return true;
            }

            @Override
            public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                return false;
            }

            @Override
            public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.sendtothis:
                        count = 0;
                        seltChoices_users.clear();
                        Toast.makeText(getActivity(), "okat", Toast.LENGTH_SHORT).show();
                        mode.finish();
                        return true;
                    default:
                        return false;
                }


            }

            @Override
            public void onDestroyActionMode(ActionMode mode) {
                seltChoices_users.clear();
                count = 0;
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
        final FrameLayout this_is_the_pop;
        this_is_the_pop = rootView.findViewById(R.id.this_is_the_pop);
        final TextView the_text_to_count = rootView.findViewById(R.id.the_text_to_count);

        new pitsan_load_single_pit_offer_feeds(getActivity(), Config.PITSAN_SINGLE_PIT_DATA_LOAD_FEEDS.toString(), username, swiperefresh, singleList).execute();
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

        return rootView;
    }

}
