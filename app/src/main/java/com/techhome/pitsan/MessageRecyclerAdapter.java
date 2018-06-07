package com.techhome.pitsan;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class MessageRecyclerAdapter extends RecyclerView.Adapter<MessageRecyclerAdapter.MyViewHolder> {
    private List<Message> messageList;

    public MessageRecyclerAdapter(List<Message> messageList) {
        this.messageList = messageList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.message_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Message message = messageList.get(position);
        holder.messageListTitle.setText(message.getName());
        holder.messageListDate.setText(message.getDate().toString());
        holder.messageListCaption.setText(message.getMessage());
    }

    @Override
    public int getItemCount() {
        return messageList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView messageListTitle, messageListDate, messageListCaption;

        public MyViewHolder(View itemView) {
            super(itemView);
            messageListTitle = itemView.findViewById(R.id.message_title);
            messageListDate = itemView.findViewById(R.id.message_date);
            messageListCaption = itemView.findViewById(R.id.message_caption);
        }
    }
}
