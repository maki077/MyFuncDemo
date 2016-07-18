package com.example.myfuncdemo.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myfuncdemo.R;
import com.example.myfuncdemo.bean.ViewDemoBean;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Administrator on 2016/7/15.
 */
public class RecyclerViewDemoAdapter extends RecyclerView.Adapter<RecyclerViewDemoAdapter.RecyclerDemoViewHolder> {
    private Context context;
    private List<ViewDemoBean> beanList;

    public RecyclerViewDemoAdapter(Context context, List<ViewDemoBean> beanList) {
        this.context = context;
        this.beanList = beanList;
    }

    @Override
    public RecyclerDemoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_recycler_view_demo, parent, false);
        RecyclerDemoViewHolder viewHolder = new RecyclerDemoViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerDemoViewHolder holder, int position) {
            holder.itemText.setText(beanList.get(position).title);
            holder.itemImage.setImageResource(R.drawable.splash_default);

    }

    @Override
    public int getItemCount() {
        if (beanList != null) {
            return beanList.size();
        } else {
            return 0;
        }
    }

    public static class RecyclerDemoViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.item_recycler_image)
        public ImageView itemImage;

        @Bind(R.id.item_recycler_text)
        public TextView itemText;

        public RecyclerDemoViewHolder(View item) {
            super(item);
            ButterKnife.bind(this, itemView);
        }
    }
}
