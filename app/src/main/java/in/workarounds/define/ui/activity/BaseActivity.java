package in.workarounds.define.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.TextView;

import in.workarounds.define.BuildConfig;
import in.workarounds.define.R;
import in.workarounds.define.service.ClipboardService;

/**
 * Created by madki on 30/09/15.
 */
public abstract class BaseActivity extends AppCompatActivity {
    TextView titleTextView;
    @StringRes int toolbarTitleResId = R.string.app_name;

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if(toolbar != null) {
            setSupportActionBar(toolbar);
            toolbar.setTitle("");
        }
        titleTextView = (TextView) findViewById(R.id.tv_app_title);
        setToolbarTitle(toolbarTitleResId);
    }

    protected void setToolbarTitle(@StringRes int resId){
        toolbarTitleResId = resId;
        if(titleTextView != null) {
            titleTextView.setText(resId);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        if(BuildConfig.DEBUG){
            inflater.inflate(R.menu.menu_debug, menu);
        }
        return true;
    }

    @Override
    protected void onStart() {
        startClipboardService();
        super.onStart();
    }


    @Override
    protected void onResume() {
        startClipboardService();
        super.onResume();
    }


    /**
     * starts the clipboard service if its not up already
     */
    protected void startClipboardService() {
        boolean serviceUp = ClipboardService.isRunning();
        if (!serviceUp) {
            Intent intent = new Intent(getBaseContext(), ClipboardService.class);
            getBaseContext().startService(intent);
        }
    }

    protected abstract String getToolbarTitle();

}