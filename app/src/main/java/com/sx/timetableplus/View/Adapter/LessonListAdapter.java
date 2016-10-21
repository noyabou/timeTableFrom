package com.sx.timetableplus.View.Adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sx.timetableplus.Model.LessonInfo;
import com.sx.timetableplus.R;
import com.sx.timetableplus.databinding.ItemLessonInfoBinding;

import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * Created by sx on 2016/10/21.
 */

public class LessonListAdapter extends RecyclerView.Adapter<LessonListAdapter.MyViewHolder> {
    private List<LessonInfo> mData;
    private Context mContext;
    private OnItemClickListener mOnItemClickListener;

    public LessonListAdapter(List<LessonInfo> mData, Context mContext) {
        this.mData = mData;
        this.mContext = mContext;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_lesson_info, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: lesson item...");
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.OnClick(v);
                    mOnItemClickListener.OnLongClick(v);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public interface OnItemClickListener {
        void OnClick(View v);

        void OnLongClick(View v);
    }

    public void setmOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        ItemLessonInfoBinding mBinding;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.mBinding = DataBindingUtil.bind(itemView);
        }
    }
}
