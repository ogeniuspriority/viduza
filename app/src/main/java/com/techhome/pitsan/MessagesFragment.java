package com.techhome.pitsan;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class MessagesFragment extends Fragment {
    private static final String ARG_SECTION_NUMBER = "section_number";
    RecyclerView messageRecyclerView;
    private List<Message> messageList = new ArrayList<>();

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
        messageRecyclerView = rootView.findViewById(R.id.messageRecyclerView);


        mAdapter = new MessageRecyclerAdapter(messageList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        messageRecyclerView.setLayoutManager(mLayoutManager);
        messageRecyclerView.setItemAnimator(new DefaultItemAnimator());
        messageRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
        messageRecyclerView.setAdapter(mAdapter);
        messageRecyclerView.addOnItemTouchListener(new RecyclerTouchListener(getContext(), messageRecyclerView, new RecyclerTouchListener.ClickListener() {

            @Override
            public void onClick(View view, int position) {
                Message message = messageList.get(position);
                Bundle messageBundle = new Bundle();
                messageBundle.putString("Name", message.getName());
                Intent intent = new Intent(getContext(), MessagesActivity.class);
                intent.putExtras(messageBundle);
                startActivity(intent);
                Toast.makeText(getContext(), message.getName() + " is selected!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
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
