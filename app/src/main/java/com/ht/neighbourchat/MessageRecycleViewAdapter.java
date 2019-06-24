package com.ht.neighbourchat;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ht.neighbourchat.models.Message;

import java.util.List;
import java.util.logging.Logger;

public class MessageRecycleViewAdapter extends RecyclerView.Adapter<com.ht.neighbourchat.MessageRecycleViewAdapter.ViewHolder> {
    private static Logger logger = Logger.getLogger(com.ht.neighbourchat.UserRecyclerViewAdapter.class.toString());
    private final List<Message> messages;
    private final boolean mTwoPane;

    MessageRecycleViewAdapter(
            List<Message> messages,
            boolean twoPane) {
        this.messages = messages;
        mTwoPane = twoPane;
    }

    @Override
    @NonNull
    public com.ht.neighbourchat.MessageRecycleViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.chat_bubble, parent, false);
        return new com.ht.neighbourchat.MessageRecycleViewAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final com.ht.neighbourchat.MessageRecycleViewAdapter.ViewHolder holder, int position) {
        holder.bubble.setText(messages.get(position).getMessageContent());
        if(messages.get(position).getStatus()==1 ){
            holder.bubble.setBackgroundResource(R.drawable.bubble_a);
            holder.bubble.setGravity(View.FOCUS_LEFT);
        }
        else {
            holder.bubble.setBackgroundResource(R.drawable.bubble_b);
            holder.bubble.setGravity(View.FOCUS_RIGHT);
        }
        holder.itemView.setTag(messages.get(position));

    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        final TextView bubble;

        ViewHolder(View view) {
            super(view);
            bubble = view.findViewById(R.id.singleMessage);
        }
    }

}
