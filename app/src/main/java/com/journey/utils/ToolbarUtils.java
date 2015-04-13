package com.journey.utils;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.journey.R;

public class ToolbarUtils {

    public static void setCustomToolbar(final ActionBarActivity context) {
        Toolbar toolbar = (Toolbar)context.findViewById(R.id.my_toolbar);
        context.setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.back_arrow_white);
        context.getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.finish();;
            }
        });
        context.getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    public static void setSystemToolbar(final ActionBarActivity context) {
        Toolbar toolbar = (Toolbar)context.findViewById(R.id.my_toolbar);
        context.setSupportActionBar(toolbar);
        context.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        context.getSupportActionBar().setDisplayShowTitleEnabled(true);
    }

    public static void setNoTileToolbar(final ActionBarActivity context) {
        Toolbar toolbar = (Toolbar)context.findViewById(R.id.my_toolbar);
        context.setSupportActionBar(toolbar);
        context.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        context.getSupportActionBar().setDisplayShowTitleEnabled(false);
    }



}
