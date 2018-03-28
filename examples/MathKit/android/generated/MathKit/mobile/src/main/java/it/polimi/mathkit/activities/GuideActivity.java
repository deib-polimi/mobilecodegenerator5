package it.polimi.mathkit.activities;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.MenuItem;
import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import it.polimi.mathkit.R;
import it.polimi.mathkit.utilities.Constants;
import it.polimi.mathkit.utilities.OnFragmentNavigationInteractionListener;
import it.polimi.mathkit.fragments.LinksFragment;
import it.polimi.mathkit.fragments.WebsitesFragment;

public class GuideActivity extends AppCompatActivity
		implements
			OnFragmentNavigationInteractionListener,
			NavigationView.OnNavigationItemSelectedListener {

	private int layoutType;
	private SectionsPagerAdapter mSectionsPagerAdapter;
	private ViewPager mViewPager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_guide);
		Toolbar toolbar = (Toolbar) this.findViewById(R.id.guide_toolbar);
		setSupportActionBar(toolbar);

		// Setup ViewPager (if present) and type of the layout
		mViewPager = (ViewPager) this.findViewById(R.id.guide_container);
		if (mViewPager != null) {
			mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
			mViewPager.setAdapter(mSectionsPagerAdapter);

			layoutType = Constants.SCENE_TYPE_SINGLE_VC;

		} else {
			layoutType = Constants.SCENE_TYPE_MULTI_VC;
			// In multiVC layouts fragments are instantiated through the XML layout files.
			// To access them simply call getSupportFragmentManager().findFragmentById(R.id.{FRAGMENT_ID})
		}

		// Setup ActionBarDrawerToggle and NavigationView
		DrawerLayout drawer = (DrawerLayout) findViewById(R.id.guide_drawer_layout);
		ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open,
				R.string.navigation_drawer_close);
		drawer.addDrawerListener(toggle);
		toggle.syncState();

		NavigationView navigationView = (NavigationView) findViewById(R.id.guide_nav_view);
		navigationView.setNavigationItemSelectedListener(this);
	}

	@Override
	public void onBackPressed() {
		DrawerLayout drawer = (DrawerLayout) findViewById(R.id.guide_drawer_layout);
		if (drawer.isDrawerOpen(GravityCompat.START)) {
			drawer.closeDrawer(GravityCompat.START);
		} else {
			super.onBackPressed();
		}
	}

	@SuppressWarnings("StatementWithEmptyBody")
	@Override
	public boolean onNavigationItemSelected(MenuItem item) {
		int id = item.getItemId();
		Intent intent;

		switch (id) {
			case R.id.nav_algebra :
				intent = new Intent(GuideActivity.this, AlgebraActivity.class);
				//use putExtra method of Intent here for passing additional information to AlgebraActivity
				startActivity(intent);
				break;
			case R.id.nav_equations :
				intent = new Intent(GuideActivity.this, EquationsActivity.class);
				//use putExtra method of Intent here for passing additional information to EquationsActivity
				startActivity(intent);
				break;
			case R.id.nav_about :
				intent = new Intent(GuideActivity.this, AboutActivity.class);
				//use putExtra method of Intent here for passing additional information to AboutActivity
				startActivity(intent);
				break;

			default :
				// Do nothing
				break;
		}

		DrawerLayout drawer = (DrawerLayout) findViewById(R.id.guide_drawer_layout);
		drawer.closeDrawer(GravityCompat.START);
		return true;
	}

	@Override
	public void onFragmentNavigationInteraction(String controlId, String clickedItem) {
		switch (controlId) {

			case "algebraButton" :
				if (layoutType != Constants.SCENE_TYPE_MULTI_VC) {
					mViewPager.setCurrentItem(1);
				} else {
					// Do nothing
				}
				break;
			case "probabilityButton" :
				if (layoutType != Constants.SCENE_TYPE_MULTI_VC) {
					mViewPager.setCurrentItem(1);
				} else {
					// Do nothing
				}
				break;
			case "equationsButton" :
				if (layoutType != Constants.SCENE_TYPE_MULTI_VC) {
					mViewPager.setCurrentItem(1);
				} else {
					// Do nothing
				}
				break;

			default :
				// Do nothing
				break;
		}
	}

	public class SectionsPagerAdapter extends FragmentPagerAdapter {

		public SectionsPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			switch (position) {
				case 0 :
					return LinksFragment.newInstance();
				case 1 :
					return WebsitesFragment.newInstance();
			}
			return null;
		}

		@Override
		public int getCount() {
			return 2;
		}
	}

}
