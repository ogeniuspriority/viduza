package com.techhome.pitsan;

import android.content.Intent;
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
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class MessagesFragment extends Fragment {
    private static final String ARG_SECTION_NUMBER = "section_number";
    SwipeRefreshLayout messageRecyclerView;
    private List<Message> messageList = new ArrayList<>();
    ListView list;

    private MessageRecyclerAdapter mAdapter;

    public MessagesFragment() {

    }

    public static MessagesFragment newInstance(int sectionNumber) {
        MessagesFragment fragment = new MessagesFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_message, container, false);
        messageRecyclerView = rootView.findViewById(R.id.swiperefresh);
        list = rootView.findViewById(R.id.list);

        mAdapter = new MessageRecyclerAdapter(messageList);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Message message = messageList.get(position);
                Bundle messageBundle = new Bundle();
                messageBundle.putString("Name", message.getName());
                Intent intent = new Intent(getContext(), MessagesActivity.class);
                intent.putExtras(messageBundle);
                startActivity(intent);
                Toast.makeText(getContext(), message.getName() + " is selected!", Toast.LENGTH_SHORT).show();

            }
        });

        prepareMessageData();
        return rootView;
    }

    private void prepareMessageData() {


        messageList.add(new Message("Mad Max: Fury Road", "event " + 1, buildRandomDateInCurrentMonth()));


    }

    private Date buildRandomDateInCurrentMonth() {

        Random random = new Random();

        return DateUtils.buildDate(random.nextInt(31) + 1);
    }


}
