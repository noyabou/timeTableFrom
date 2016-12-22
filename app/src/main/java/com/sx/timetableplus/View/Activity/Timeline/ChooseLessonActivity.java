package com.sx.timetableplus.View.Activity.Timeline;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.DrawableUtils;
import android.support.v7.widget.LinearLayoutManager;
import android.view.Menu;
import android.view.MenuItem;

import com.sx.timetableplus.Model.LessonInfo;
import com.sx.timetableplus.R;
import com.sx.timetableplus.Utility.DrawableUtil;
import com.sx.timetableplus.View.Activity.BaseActivity;
import com.sx.timetableplus.View.Adapter.PoisAdapter;
import com.sx.timetableplus.View.Custom.DividerItemDecoration;
import com.sx.timetableplus.databinding.ActivityChooseLessonBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sx on 2016/12/22.
 */

public class ChooseLessonActivity extends BaseActivity {
    ActivityChooseLessonBinding mBinding;
    private List<LessonInfo> lessonList;
    private List<String> mData;
    private PoisAdapter mAdapter;
    private String choosedLessonName;
    private int choosedLessonId;
    private boolean hasSelected;

    @Override

    protected void getLayoutResource() {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_choose_lesson);
    }

    @Override
    protected void initView() {
        lessonList = new ArrayList<>();
        mData = new ArrayList<>();
        hasSelected = false;

        initToolbar();

        getData();

        mAdapter = new PoisAdapter(this, mData);
        mAdapter.setOnItemClickListener(new PoisAdapter.OnItemClickListener() {
            @Override
            public void OnClick(int position) {
                hasSelected = true;
                choosedLessonName = lessonList.get(position).getName();
                choosedLessonId = lessonList.get(position).getId();
                mBinding.myLessonCircleTxt.setText(mData.get(position));
                DrawableUtil.LoadDrawable(getResources().getDrawable(R.mipmap.ic_lesson_circle_enabled), mBinding.myLessonCircleTxt, DrawableUtil.LEFT);
            }
        });
        mBinding.chooseLessonCircleRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mBinding.chooseLessonCircleRecycler.setAdapter(mAdapter);
        mBinding.chooseLessonCircleRecycler.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
    }

    private void getData() {
        for (int i = 0; i < 10; i++) {
            LessonInfo li = new LessonInfo();
            li.setId(i);
            li.setName("高级机器学习");
            lessonList.add(li);
            mData.add(lessonList.get(i).getName());
        }
    }

    @Override
    protected void initToolbar() {
        toolbar = mBinding.chooseLessonToolbar.toolbar;
        toolbar.setTitle(R.string.mine_lesson_circle);
        super.initToolbar();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_confirm, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (hasSelected) {
            Intent intent = new Intent();
            intent.putExtra(AddTimelineActivity.KEY_RETURN_LESSON_NAME, choosedLessonName);
            intent.putExtra(AddTimelineActivity.KEY_RETURN_LESSON_ID, choosedLessonId);
            setResult(RESULT_OK, intent);
        }
        finish();
        return true;
    }
}
