package com.crossover.crosschat.android.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.crossover.crosschat.android.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import icepick.Icepick;

/**
 * Created by Mahmoud Abdurrahman (mahmoud.abdurrahman@crossover.com) on 2/8/18.
 */
public abstract class BasicActivity extends MvpAppCompatActivity {

    @Nullable
    @BindView(R.id.toolbar_actionbar)
    protected Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentResource());

        ButterKnife.bind(this);
        Icepick.restoreInstanceState(this, savedInstanceState);

        if (savedInstanceState == null) {
            onLaunch();
        }

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();

        if (toolbar != null && actionBar != null) {
            setSupportActionBar(toolbar);
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowTitleEnabled(true);
            actionBar.setTitle(getToolbarTitle());
        }
    }

    protected void onLaunch() {
        // Nothing to-do basically
    }

    protected String getToolbarTitle() {
        return !TextUtils.isEmpty(super.getTitle())? super.getTitle().toString()
                : getString(R.string.app_name);
    }

    protected abstract int getContentResource();

    protected boolean hasTranslucentStatusBar() {
        return false;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Icepick.saveInstanceState(this, outState);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // By default we handle the Home button like Back
        // unless it's overridden by child Activity
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
