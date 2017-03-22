package com.infullmobile.android.recyclerviewwithdynamicsubtitles;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.Collection;

public class EmptyDynamicRecyclerViewAdapterOfString
        extends DynamicRecyclerViewAdapter<RecyclerView.ViewHolder, RecyclerView.ViewHolder, String> {

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent) {
        return null;
    }

    @Override
    public RecyclerView.ViewHolder onCreateTitleViewHolder(@NonNull ViewGroup parent) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder,
                                 @NonNull String item,
                                 int position) {
        // NO OP
    }

    @Override
    public void onBindTitleViewHolder(@NonNull RecyclerView.ViewHolder holder,
                                      @NonNull String sectionName,
                                      @NonNull Collection dataItems,
                                      int position) {
        // NO OP
    }
}
