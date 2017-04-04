package no.ntnu.tdt4240.geoquiz9000.activities;

import android.graphics.Typeface;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import no.ntnu.tdt4240.geoquiz9000.R;

/**
 * Created by MikhailV on 20.03.2017.
 */

public abstract class GeoActivity extends AppCompatActivity
{
    private static final String SAVED_FRAGMENT = "GeoActivity.SAVED_FRAGMENT";

    private Typeface m_textFont;
    private Typeface m_titleFont;
    private Fragment m_state;

    public Typeface getTextFont()
    {
        return m_textFont;
    }
    public Typeface getTitleFont()
    {
        return m_titleFont;
    }
    protected void replaceState(Fragment nextState)
    {
        m_state = nextState;
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, nextState)
                .addToBackStack(null)
                .commit();
    }
    protected abstract Fragment getInitialState();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        String textFontName = getResources().getString(R.string.text_font_name);
        m_textFont = Typeface.createFromAsset(getAssets(), textFontName);
        String titleFontName = getResources().getString(R.string.title_font_name);
        m_titleFont = Typeface.createFromAsset(getAssets(), titleFontName);

        setContentView(R.layout.activity_geo);

        final TextView title = (TextView)findViewById(R.id.main_title);
        title.setTypeface(getTitleFont());

        Fragment state = savedInstanceState == null ? getInitialState()
                : getSupportFragmentManager().getFragment(savedInstanceState, SAVED_FRAGMENT);
        replaceState(state);
    }
    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState)
    {
        super.onSaveInstanceState(outState, outPersistentState);
        if (m_state != null) {
            getSupportFragmentManager().putFragment(outState, SAVED_FRAGMENT, m_state);
        }
    }
    protected void gotoPreviousState()
    {
        FragmentManager fm = getSupportFragmentManager();
        if (fm.getBackStackEntryCount() > 0)
            fm.popBackStack();
    }
    @Override
    public void onBackPressed()
    {
        // disable back button
    }
}
