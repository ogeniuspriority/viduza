package com.techhome.pitsan;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

public class MessageItemAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    @NonNull
    private List<MessageListItem> messageItemList = Collections.emptyList();


    public MessageItemAdapter(List<MessageListItem> messageItemList) {
        this.messageItemList = messageItemList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        switch (viewType) {
            case MessageListItem.TYPE_HEADER: {
                View itemView = inflater.inflate(R.layout.message_list_row_header, parent, false);
                return new HeaderViewHolder(itemView);
            }
            case MessageListItem.TYPE_MESSAGE: {
                View itemView = inflater.inflate(R.layout.bubble_list_item, parent, false);
                return new MyViewHolder(itemView);
            }
            default:
                throw new IllegalStateException("unsupported item type");
        }


    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int viewType = getItemViewType(position);
        switch (viewType) {

            case MessageListItem.TYPE_MESSAGE: {
                MessageItem event = (MessageItem) messageItemList.get(position);
                MyViewHolder holder2 = (MyViewHolder) holder;
                // your logic here
                String myName = event.getMessage().getName();
                if (myName.contains("hris")) {
                    Log.i("LeftBubble", "Chris is here");


                    holder2.messageListDate.setText(event.getMessage().getDate().toString());
                    holder2.messageListCaption.setText(event.getMessage().getMessage());
                } else {
                    RelativeLayout.LayoutParams imageViewParam = new RelativeLayout.LayoutParams(
                            RelativeLayout.LayoutParams.WRAP_CONTENT,
                            RelativeLayout.LayoutParams.WRAP_CONTENT);
                    // create a new ImageView

                    Log.i("RightBubble", "Not chris");
                    RelativeLayout.LayoutParams mymsg = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
                    mymsg.alignWithParent = true;
                    holder2.messageListLayout.setLayoutParams(mymsg);
                    holder2.messageListLayout.setBackgroundColor(Color.BLUE);
                    mymsg.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
                    mymsg.setMargins(8, 8, 8, 8);
                    holder2.messageListDate.setText(event.getMessage().getDate().toString());
                    holder2.messageListCaption.setText(event.getMessage().getMessage());
                }

                break;
            }
            case MessageListItem.TYPE_HEADER: {
                MessageHeaderItem header = (MessageHeaderItem) messageItemList.get(position);
                HeaderViewHolder holder1 = (HeaderViewHolder) holder;
                // your logic here
                holder1.txt_title.setText(DateUtils.formatDate(header.getDate()));
                break;
            }
            default:
                throw new IllegalStateException("unsupported item type");
        }


    }

    @Override
    public int getItemCount() {
        return messageItemList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return messageItemList.get(position).getType();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView messageListDate, messageListCaption;
        public RelativeLayout messageListLayout;

        public MyViewHolder(View itemView) {
            super(itemView);
            messageListLayout = itemView.findViewById(R.id.bubble_list_item);
            messageListDate = itemView.findViewById(R.id.bubble_list_date);
            messageListCaption = itemView.findViewById(R.id.bubble_list_text);
        }
    }


    private class HeaderViewHolder extends MyViewHolder {
        TextView txt_title;

        public HeaderViewHolder(View itemView) {
            super(itemView);
            txt_title = itemView.findViewById(R.id.listrowheader);
        }
    }
}
