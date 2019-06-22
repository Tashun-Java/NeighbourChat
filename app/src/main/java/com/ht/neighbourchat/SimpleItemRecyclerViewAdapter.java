package com.ht.neighbourchat;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ht.neighbourchat.dummy.DummyContent;
import com.ht.neighbourchat.models.UserDoa;

import java.util.List;

public class SimpleItemRecyclerViewAdapter
        extends RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder> {

    private final ItemListActivity mParentActivity;
    private final List<UserDoa> users;
    private final boolean mTwoPane;
    private final View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            UserDoa userDoa = (UserDoa) view.getTag();
            if (mTwoPane) {
//                Bundle arguments = new Bundle();
//                arguments.putString(ItemDetailFragment.ARG_ITEM_ID, userDoa.getId());
//                ItemDetailFragment fragment = new ItemDetailFragment();
//                fragment.setArguments(arguments);
//                mParentActivity.getSupportFragmentManager().beginTransaction()
//                        .replace(R.id.item_detail_container, fragment)
//                        .commit();
                Context context = view.getContext();
                Intent intent = new Intent(context, ItemDetailActivity.class);
                intent.putExtra(ItemDetailFragment.ARG_ITEM_ID, userDoa.getId());
                context.startActivity(intent);


                context.startActivity(intent);
            } else {
                Context context = view.getContext();
                Intent intent = new Intent(context, ItemDetailActivity.class);
                intent.putExtra(ItemDetailFragment.ARG_ITEM_ID, userDoa.getId());

                context.startActivity(intent);
            }
        }
    };

    SimpleItemRecyclerViewAdapter(ItemListActivity parent,
                                  List<UserDoa> items,
                                  boolean twoPane) {
        users = items;
        mParentActivity = parent;
        mTwoPane = twoPane;
    }

    @Override
    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list_content_recyclable, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        holder.mIdView.setText(users.get(position).getId());
        holder.mContentView.setText(users.get(position).getUserName());

        holder.itemView.setTag(users.get(position));
        holder.itemView.setOnClickListener(mOnClickListener);
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        final TextView mIdView;
        final TextView mContentView;

        ViewHolder(View view) {
            super(view);
            mIdView = view.findViewById(R.id.userId);
            mContentView = view.findViewById(R.id.userLastSeen);
        }
    }
}
