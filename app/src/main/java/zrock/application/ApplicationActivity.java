package zrock.application;

import zrock.application.engine.app.DashboardFragment;
import zrock.application.engine.app.ProfileFragment;
import zrock.application.engine.app.MessageFragment;
import zrock.application.engine.app.ChartFragment;
import zrock.application.engine.app.FavoriteZRock;
import zrock.application.engine.app.DeviceFragment;
import zrock.application.engine.widget.CircularProgressBar;
import zrock.application.engine.view.menu.DrawerAdapter;
import zrock.application.engine.view.menu.DrawerItem;
import zrock.application.engine.view.menu.SimpleItem;
import zrock.application.engine.view.menu.SpaceItem;
import zrock.application.engine.widget.SlidingRootNav;
import zrock.application.engine.widget.SlidingRootNavBuilder;
import zrock.application.root.checker.RootChecker;
import zrock.application.settings.SettingsActivity;

import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.MenuItemCompat;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.content.Context;
import android.content.Intent;
import android.content.DialogInterface;
import android.content.res.TypedArray;
import android.net.Uri;
import android.os.Bundle;
import android.os.Build;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.ImageView;

import java.util.Arrays;

public class ApplicationActivity extends AppCompatActivity implements DrawerAdapter.OnItemSelectedListener
{
	public static final String TAG  = ApplicationActivity.class.getSimpleName();
	private Toolbar mToolbar;
	private ActionBar mActionBar;
	private BottomNavigationView mBottomNavigationView;
	public static void start(Context mContext){
		Intent mApplication = new Intent(mContext, ApplicationActivity.class);
		mContext.startActivity(mApplication);
	}
	private static final int POS_DASHBOARD = 0;
    private static final int POS_ACCOUNT = 1;
    private static final int POS_MESSAGES = 2;
    private static final int POS_CART = 3;
    private static final int POS_LOGOUT = 5;

    private String[] screenTitles;
    private Drawable[] screenIcons;

    private SlidingRootNav slidingRootNav;
	
    @Override
    protected void onCreate(Bundle savedInstanceState)
	{
		setTheme(R.style.Theme_ZRock_Application);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_application);
        setToolbar();
		
		slidingRootNav = new SlidingRootNavBuilder(this)
			.withToolbarMenuToggle(mToolbar)
			.withMenuOpened(false)
			.withContentClickableWhenMenuOpened(false)
			.withSavedState(savedInstanceState)
			.withMenuLayout(R.layout.menu_left_drawer)
			.inject();

        screenIcons = loadScreenIcons();
        screenTitles = loadScreenTitles();

        DrawerAdapter adapter = new DrawerAdapter(Arrays.asList(
													  createItemFor(POS_DASHBOARD).setChecked(true),
													  createItemFor(POS_ACCOUNT),
													  createItemFor(POS_MESSAGES),
													  createItemFor(POS_CART),
													  new SpaceItem(48),
													  createItemFor(POS_LOGOUT)));
        adapter.setListener(this);

        RecyclerView list = (RecyclerView)findViewById(R.id.list);
        list.setNestedScrollingEnabled(false);
        list.setLayoutManager(new LinearLayoutManager(this));
        list.setAdapter(adapter);
        adapter.setSelected(POS_DASHBOARD);
		
        mBottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        mBottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener(){
				@Override
				public boolean onNavigationItemSelected(@NonNull MenuItem item)
				{
					switch (item.getItemId())
					{
						case R.id.bottomHome: 
							mBottomNavigationView.setItemBackgroundResource(R.color.colorPrimary);
							mActionBar.setSubtitle("HOME");
							switchContent(new DashboardFragment());
							break;
						case R.id.bottomFavorites:
							mBottomNavigationView.setItemBackgroundResource(R.color.colorPrimary);
							mActionBar.setSubtitle("FAVORITE");
							switchContent(new FavoriteZRock());
							break;
						case R.id.bottomAbout:
							mBottomNavigationView.setItemBackgroundResource(R.color.colorPrimary);
							mActionBar.setSubtitle("ABOUT");
							switchContent(new DeviceFragment());
							break;
					}
					return true;
				}
		});
    }
	
	private void setToolbar()
	{
		mToolbar = (Toolbar) findViewById(R.id.toolbar);
        if (mToolbar != null)
		{
            setSupportActionBar(mToolbar);
			mActionBar = getSupportActionBar();
        }

		if (mActionBar != null)
		{
            mActionBar.setTitle(R.string.app_name);
	    }
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// TODO: Implement this method
		getMenuInflater().inflate(R.menu.zrock_menu_toolbar , menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		// TODO: Implement this method
		switch(item.getItemId()){
			case R.id.action_github:
				
				break;
			case R.id.action_settings:
				SettingsActivity.start(ApplicationActivity.this);
				break;
		}
		return super.onOptionsItemSelected(item);
	}


	@Override
    public void onItemSelected(int position)
	{
        if (position == POS_DASHBOARD)
		{
            switchContent(new DashboardFragment());
			mActionBar.setSubtitle("HOME");
        }
		if (position == POS_ACCOUNT)
		{
            switchContent(new ProfileFragment());
			mActionBar.setSubtitle("PROFILE");
        }
		if (position == POS_MESSAGES)
		{
            switchContent(new MessageFragment());
			mActionBar.setSubtitle("MESSAGES");
        }
		if (position == POS_CART)
		{
            switchContent(new ChartFragment());
			mActionBar.setSubtitle("CART");
        }
		if (position == POS_LOGOUT)
		{
            finish();
        }
        slidingRootNav.closeMenu();
    }

    private void switchContent(Fragment fragment)
	{
		getSupportFragmentManager()
			.beginTransaction()
			.replace(R.id.appList, fragment)
			.commit();
	}

    private DrawerItem createItemFor(int position)
	{
        return new SimpleItem(screenIcons[position], screenTitles[position])
			.withIconTint(color(R.color.textColorSecondary))
			.withTextTint(color(R.color.textColorPrimary))
			.withSelectedIconTint(color(R.color.colorAccent))
			.withSelectedTextTint(color(R.color.colorAccent));
    }

    private String[] loadScreenTitles()
	{
        return getResources().getStringArray(R.array.ld_activityScreenTitles);
    }

    private Drawable[] loadScreenIcons()
	{
        TypedArray ta = getResources().obtainTypedArray(R.array.ld_activityScreenIcons);
        Drawable[] icons = new Drawable[ta.length()];
        for (int i = 0; i < ta.length(); i++)
		{
            int id = ta.getResourceId(i, 0);
            if (id != 0)
			{
                icons[i] = ContextCompat.getDrawable(this, id);
            }
        }
        ta.recycle();
        return icons;
    }

    @ColorInt
    private int color(@ColorRes int res)
	{
        return ContextCompat.getColor(this, res);
    }
	
}
