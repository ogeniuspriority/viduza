package com.techhome.pitsan;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;

import com.techhome.pitsan.back_end.pitsan_load_message_feeds_responses;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

public class MessagesActivity extends AppCompatActivity {
    RecyclerView messageItemRecyclerView;
    private List<Message> messageItemList = new ArrayList<>();
    @NonNull
    private List<MessageListItem> items = new ArrayList<>();
    private MessageItemAdapter mAdapter;
    SwipeRefreshLayout swiperefresh;
    ListView list;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        Toolbar myToolbar = findViewById(R.id.message_toolbar);
        setSupportActionBar(myToolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        Intent in = getIntent();
        Bundle bundle = in.getExtras();
        final String TheidOfThisThread = bundle.getString("TheidOfThisThread");
        actionBar.setTitle("Message Chats");

        //---------
        list = findViewById(R.id.list);

        // prepareMessageData();
        swiperefresh = findViewById(R.id.swiperefresh);
        //-------
        swiperefresh = findViewById(R.id.swiperefresh);
        swiperefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new pitsan_load_message_feeds_responses(MessagesActivity.this, Config.PITSAN_MESSAGES_RESPONSES_PIT_DATA_LOAD_FEEDS.toString(), TheidOfThisThread, swiperefresh, list).execute();
            }
        });
        //-----------
        new pitsan_load_message_feeds_responses(MessagesActivity.this, Config.PITSAN_MESSAGES_RESPONSES_PIT_DATA_LOAD_FEEDS.toString(), TheidOfThisThread, swiperefresh, list).execute();


    }

    private void prepareMessageData() {
      /*  Message message = new Message("Mad Max: Fury Road", "I want some Action & Adventure, this what you will get when you have a tornado open for discussion iver mandazi nation", "2015/02/15");
        messageItemList.add(message);

        message = new Message("Mad Max: Fury Road", "Action & Adventure", "2015/12/12");
        messageItemList.add(message);
        message = new Message("Mad Max: Fury Road", "Action & Adventure", "2015/12/12");
        messageItemList.add(message);
        message = new Message("Christian", "Action & Adventure", "2015/12/12");
        messageItemList.add(message);
        message = new Message("Mad Max: Fury Road", "Action & Adventure", "2015/12/12");
        messageItemList.add(message);
        message = new Message("Mad Max: Fury Road", "Action & Adventure", "2015/12/12");
        messageItemList.add(message);
        message = new Message("Mad Max: Fury Road", "Action & Adventure", "2015/12/12");
        messageItemList.add(message);
        message = new Message("Mad Max: Fury Road", "Action & Adventure", "2015/12/12");
        messageItemList.add(message);
        message = new Message("Christian", "Action & Adventure", "2015/12/12");
        messageItemList.add(message);
        message = new Message("Mad Max: Fury Road", "Action & Adventure", "2015/12/12");
        messageItemList.add(message);
        message = new Message("Mad Max: Fury Road", "Action & Adventure", "2015/12/12");
        messageItemList.add(message);
        message = new Message("Mad Max: Fury Road", "Action & Adventure", "2015/12/12");
        messageItemList.add(message);
        message = new Message("Mad Max: Fury Road", "Action & Adventure", "2015/12/12");
        messageItemList.add(message);
        message = new Message("Mad Max: Fury Road", "Action & Adventure", "2015/12/12");
        messageItemList.add(message);
        message = new Message("Christian", "Action & Adventure", "2015/12/12");
        messageItemList.add(message);
        message = new Message("Mad Max: Fury Road", "Action & Adventure", "2015/12/12");
        messageItemList.add(message);
        message = new Message("Mad Max: Fury Road", "Action & Adventure", "2015/12/12");
        messageItemList.add(message);*/
    }

    @NonNull
    private List<Message> loadMessages() {
        List<Message> messages = new ArrayList<>();
        for (int i = 1; i < 50; i++) {
            messages.add(new Message("Mad Max: Fury Road", "event " + i, buildRandomDateInCurrentMonth()));
            messages.add(new Message("Christian", "event me " + i, buildRandomDateInCurrentMonth()));
        }
        return messages;
    }

    private Date buildRandomDateInCurrentMonth() {

        Random random = new Random();

        return DateUtils.buildDate(random.nextInt(31) + 1);
    }

    @NonNull
    private Map<Date, List<Message>> toMap(@NonNull List<Message> messages) {
        Map<Date, List<Message>> map = new TreeMap<>();
        for (Message message : messages) {
            List<Message> value = map.get(message.getDate());
            if (value == null) {
                value = new ArrayList<>();
                map.put(message.getDate(), value);
            }
            value.add(message);
        }
        return map;
    }
}
