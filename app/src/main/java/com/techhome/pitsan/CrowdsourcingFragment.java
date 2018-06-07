package com.techhome.pitsan;

import android.app.Dialog;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CrowdsourcingFragment extends Fragment implements AbsListView.OnScrollListener {
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

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_crowdsourcing, container, false);
        mHandler = new Handler();
        View footer = getLayoutInflater().inflate(R.layout.progress_bar_footer, null);
        progressBar = footer.findViewById(R.id.progressBar);
        list = rootView.findViewById(R.id.crowdlist);
        list.addFooterView(footer);
        list.setItemsCanFocus(false);
        list.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        list.setAdapter(crowdsourcingListAdapter);
        list.setOnScrollListener(this); //listen for a scroll movement to the bottom
        progressBar.setVisibility((10 < date.length) ? View.VISIBLE : View.GONE);
        viewFab = rootView.findViewById(R.id.fabcrowdsourcing);
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
        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                view.setBackgroundColor(getResources().getColor(R.color.colorAccent));


                viewFab.setVisibility(View.VISIBLE);
                selected++;
                selectedList.add(position);
                Log.i("longClicked", selected + "--" + position);
                return true;
            }
        });
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                for (int p = 0; p < selectedList.size() - 1; p++) {
                    if (position == selectedList.get(p)) {
                        view.setBackgroundColor(getResources().getColor(R.color.colorTransparentWhite));
                        if (selected == 1) {
                            selected--;
                            Log.i("shortClicked", selected + "");
                            viewFab.setVisibility(View.GONE);
                        } else if (selected > 1) {
                            selected--;
                        }
                        break;
                    }
                }


            }
        });


        return rootView;
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {

    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        if (firstVisibleItem + visibleItemCount == totalItemCount && !crowdsourcingListAdapter.endReached() && !hasCallback) { //check if we've reached the bottom
            mHandler.postDelayed(showMore, 300);
            hasCallback = true;
        }
    }
}
