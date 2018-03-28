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
import android.support.design.widget.TabLayout;

import it.polimi.mathkit.R;
import it.polimi.mathkit.utilities.Constants;
import it.polimi.mathkit.utilities.OnFragmentNavigationInteractionListener;
import it.polimi.mathkit.fragments.LinearFragment;
import it.polimi.mathkit.fragments.QuadraticFragment;
import it.polimi.mathkit.fragments.SystemFragment;

public class EquationsActivity extends AppCompatActivity
		implements
			OnFragmentNavigationInteractionListener,
			NavigationView.OnNavigationItemSelectedListener {

	private int layoutType;
	private SectionsPagerAdapter mSectionsPagerAdapter;
	private ViewPager mViewPager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_equations);
		Toolbar toolbar = (Toolbar) this.findViewById(R.id.equations_toolbar);
		setSupportActionBar(toolbar);

		// Setup ViewPager (if present) and type of the layout
		mViewPager = (ViewPager) this.findViewById(R.id.equations_container);
		if (mViewPager != null) {
			mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
			mViewPager.setAdapter(mSectionsPagerAdapter);

			// Setup TabLayout
			TabLayout tabLayout = (TabLayout) this.findViewById(R.id.equations_tabs);
			mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
			tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));
			layoutType = Constants.SCENE_TYPE_SINGLE_VC_TAB;

		} else {
			layoutType = Constants.SCENE_TYPE_MULTI_VC;
			// In multiVC layouts fragments are instantiated through the XML layout files.
			// To access them simply call getSupportFragmentManager().findFragmentById(R.id.{FRAGMENT_ID})
		}

		// Setup ActionBarDrawerToggle and NavigationView
		DrawerLayout drawer = (DrawerLayout) findViewById(R.id.equations_drawer_layout);
		ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open,
				R.string.navigation_drawer_close);
		drawer.addDrawerListener(toggle);
		toggle.syncState();

		NavigationView navigationView = (NavigationView) findViewById(R.id.equations_nav_view);
		navigationView.setNavigationItemSelectedListener(this);
	}

	@Override
	public void onBackPressed() {
		DrawerLayout drawer = (DrawerLayout) findViewById(R.id.equations_drawer_layout);
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
				intent = new Intent(EquationsActivity.this, AlgebraActivity.class);
				//use putExtra method of Intent here for passing additional information to AlgebraActivity
				startActivity(intent);
				break;
			case R.id.nav_guide :
				intent = new Intent(EquationsActivity.this, GuideActivity.class);
				//use putExtra method of Intent here for passing additional information to GuideActivity
				startActivity(intent);
				break;
			case R.id.nav_about :
				intent = new Intent(EquationsActivity.this, AboutActivity.class);
				//use putExtra method of Intent here for passing additional information to AboutActivity
				startActivity(intent);
				break;

			default :
				// Do nothing
				break;
		}

		DrawerLayout drawer = (DrawerLayout) findViewById(R.id.equations_drawer_layout);
		drawer.closeDrawer(GravityCompat.START);
		return true;
	}

	@Override
	public void onFragmentNavigationInteraction(String controlId, String clickedItem) {
		switch (controlId) {

			case "calcLEButton" :
				// TODO implement the action
				break;

			case "calcQEButton" :
				// TODO implement the action
				break;

			case "calcSEButton" :
				// TODO implement the action
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
					return LinearFragment.newInstance();
				case 1 :
					return QuadraticFragment.newInstance();
				case 2 :
					return SystemFragment.newInstance();
			}
			return null;
		}

		@Override
		public int getCount() {
			return 3;
		}
	}

}
