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
import it.polimi.mathkit.fragments.ListFragment;
import it.polimi.mathkit.fragments.FractionsFragment;
import it.polimi.mathkit.fragments.ProbabilityFragment;
import it.polimi.mathkit.fragments.CommonsFragment;

public class AlgebraActivity extends AppCompatActivity
		implements
			OnFragmentNavigationInteractionListener,
			NavigationView.OnNavigationItemSelectedListener {

	private SectionsPagerAdapter mSectionsPagerAdapter;
	private ViewPager mViewPager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_algebra);
		Toolbar toolbar = (Toolbar) this.findViewById(R.id.algebra_toolbar);
		setSupportActionBar(toolbar);

		// Setup ViewPager
		mViewPager = (ViewPager) this.findViewById(R.id.algebra_container);
		mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
		mViewPager.setAdapter(mSectionsPagerAdapter);

		// Setup ActionBarDrawerToggle and NavigationView
		DrawerLayout drawer = (DrawerLayout) findViewById(R.id.algebra_drawer_layout);
		ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open,
				R.string.navigation_drawer_close);
		drawer.addDrawerListener(toggle);
		toggle.syncState();

		NavigationView navigationView = (NavigationView) findViewById(R.id.algebra_nav_view);
		navigationView.setNavigationItemSelectedListener(this);
	}

	@Override
	public void onBackPressed() {
		DrawerLayout drawer = (DrawerLayout) findViewById(R.id.algebra_drawer_layout);
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
			case R.id.nav_equations :
				intent = new Intent(AlgebraActivity.this, EquationsActivity.class);
				//use putExtra method of Intent here for passing additional information to EquationsActivity
				startActivity(intent);
				break;
			case R.id.nav_guide :
				intent = new Intent(AlgebraActivity.this, GuideActivity.class);
				//use putExtra method of Intent here for passing additional information to GuideActivity
				startActivity(intent);
				break;
			case R.id.nav_about :
				intent = new Intent(AlgebraActivity.this, AboutActivity.class);
				//use putExtra method of Intent here for passing additional information to AboutActivity
				startActivity(intent);
				break;

			default :
				// Do nothing
				break;
		}

		DrawerLayout drawer = (DrawerLayout) findViewById(R.id.algebra_drawer_layout);
		drawer.closeDrawer(GravityCompat.START);
		return true;
	}

	@Override
	public void onFragmentNavigationInteraction(String controlId, String clickedItem) {
		switch (controlId) {

			case "toolsListView" :
				// TODO implement the action
				break;

			case "calcFractionsButton" :
				// TODO implement the action
				break;

			case "calcProbButton" :
				// TODO implement the action
				break;

			case "calcCommButton" :
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
					return ListFragment.newInstance();
				case 1 :
					return FractionsFragment.newInstance();
				case 2 :
					return ProbabilityFragment.newInstance();
				case 3 :
					return CommonsFragment.newInstance();
			}
			return null;
		}

		@Override
		public int getCount() {
			return 4;
		}
	}

}
