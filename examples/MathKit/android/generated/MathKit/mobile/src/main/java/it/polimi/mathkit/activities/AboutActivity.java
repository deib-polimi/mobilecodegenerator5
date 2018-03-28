package it.polimi.mathkit.activities;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.MenuItem;
import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;

import it.polimi.mathkit.R;
import it.polimi.mathkit.utilities.Constants;
import it.polimi.mathkit.fragments.InfoFragment;

public class AboutActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_about);
		Toolbar toolbar = (Toolbar) this.findViewById(R.id.about_toolbar);
		setSupportActionBar(toolbar);

		// Setup ActionBarDrawerToggle and NavigationView
		DrawerLayout drawer = (DrawerLayout) findViewById(R.id.about_drawer_layout);
		ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open,
				R.string.navigation_drawer_close);
		drawer.addDrawerListener(toggle);
		toggle.syncState();

		NavigationView navigationView = (NavigationView) findViewById(R.id.about_nav_view);
		navigationView.setNavigationItemSelectedListener(this);
	}

	@Override
	public void onBackPressed() {
		DrawerLayout drawer = (DrawerLayout) findViewById(R.id.about_drawer_layout);
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
				intent = new Intent(AboutActivity.this, AlgebraActivity.class);
				//use putExtra method of Intent here for passing additional information to AlgebraActivity
				startActivity(intent);
				break;
			case R.id.nav_equations :
				intent = new Intent(AboutActivity.this, EquationsActivity.class);
				//use putExtra method of Intent here for passing additional information to EquationsActivity
				startActivity(intent);
				break;
			case R.id.nav_guide :
				intent = new Intent(AboutActivity.this, GuideActivity.class);
				//use putExtra method of Intent here for passing additional information to GuideActivity
				startActivity(intent);
				break;

			default :
				// Do nothing
				break;
		}

		DrawerLayout drawer = (DrawerLayout) findViewById(R.id.about_drawer_layout);
		drawer.closeDrawer(GravityCompat.START);
		return true;
	}

}
