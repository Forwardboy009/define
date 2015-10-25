package in.workarounds.define.ui.activity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.os.Bundle;
import android.view.View;

import in.workarounds.define.R;
import in.workarounds.define.util.PrefUtils;

/**
 * Created by manidesto on 24/10/15.
 */
public class TutorialActivity extends BaseActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial);

        findViewById(R.id.button_copy).setOnClickListener(copyClickListener);
    }

    @Override
    protected String getToolbarTitle() {
        return getString(R.string.app_name);
    }

    private View.OnClickListener copyClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            ClipboardManager clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
            ClipData clip = ClipData.newPlainText("Define", getString(R.string.selection));
            clipboard.setPrimaryClip(clip);
            PrefUtils.setTutorialDone(true, TutorialActivity.this);
        }
    };
}