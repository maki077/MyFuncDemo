package com.example.myfuncdemo.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.myfuncdemo.R;
import com.example.myfuncdemo.bean.FuncBean;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Administrator on 2016/7/13.
 */
public class HomeFuncAdapter extends RecyclerView.Adapter<HomeFuncAdapter.FuncViewHolder> {

    private Context context;
    private List<FuncBean> funcList;

    public HomeFuncAdapter(List<FuncBean> funcList, Context context) {
        this.funcList = funcList;
        this.context = context;
        System.out.println("this.funcList:" + this.funcList.size());
    }

    @Override
    public FuncViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //  View view = View.inflate(parent.getContext(), R.layout.item_func_list, null);
        //  View view = LayoutInflater.from(context).inflate(R.layout.item_func_list, null);
        //  View view = LayoutInflater.from(context).inflate(R.layout.item_func_list, null, false);//只有左上角
        View view = LayoutInflater.from(context).inflate(R.layout.item_func_list, parent, false);//占满整行
        // 创建一个ViewHolder
        FuncViewHolder holder = new FuncViewHolder(view);
        return holder;
    }

    /**
     * 这里不用 类型强转
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(FuncViewHolder holder, final int position) {
        if (holder instanceof FuncViewHolder) {
            ((FuncViewHolder) holder).itemFunText.setText(funcList.get(position).funcName);
            ((FuncViewHolder) holder).itemFunCard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Class targetClass = funcList.get(position).itemClass;
                    context.startActivity(new Intent(context, targetClass));
                }
            });
        }


    }

    @Override
    public int getItemCount() {
        if (funcList != null) {
            return funcList.size();
        } else {
            return 0;
        }
    }

    public static class FuncViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.item_func)
        public CardView itemFunCard;

        @Bind(R.id.item_func_txt)
        public TextView itemFunText;

        public FuncViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    /**
     * 不用ButterKnife
     */
    public static class FuncViewHolder_Bak extends RecyclerView.ViewHolder {
        public TextView itemFunText;

        public FuncViewHolder_Bak(View itemView) {
            super(itemView);
            itemFunText = (TextView) itemView.findViewById(R.id.item_func_txt);
        }
    }

}
