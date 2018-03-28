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
import android.widget.EditText;
import android.widget.TextView;

import it.polimi.mathkit.R;
import it.polimi.mathkit.utilities.Constants;
import it.polimi.mathkit.utilities.OnFragmentNavigationInteractionListener;
import it.polimi.mathkit.fragments.LinearFragment;
import it.polimi.mathkit.fragments.QuadraticFragment;
import it.polimi.mathkit.fragments.SystemFragment;
import it.polimi.mathkit.utilities.Utils;

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
				String mInput = ((EditText) findViewById(R.id.mEditText)).getText().toString();
				String qInput = ((EditText) findViewById(R.id.qEditText)).getText().toString();
				if(!mInput.equals("") && !qInput.equals("")) {
					TextView linResultTextView = (TextView) findViewById(R.id.resultLELabel);
					linResultTextView.setText(linearEquation(Float.parseFloat(mInput), Float.parseFloat(qInput)));
				}
				break;

			case "calcQEButton" :
                String aInput = ((EditText) findViewById(R.id.aEditText)).getText().toString();
                String bInput = ((EditText) findViewById(R.id.bEditText)).getText().toString();
                String cInput = ((EditText) findViewById(R.id.cEditText)).getText().toString();
                if(!aInput.equals("") && !bInput.equals("") && !cInput.equals("")) {
                    TextView x1ResultTextView = (TextView) findViewById(R.id.x1Label);
                    TextView x2ResultTextView = (TextView) findViewById(R.id.x2Label);
                    String[] results = quadraticEquation(Float.parseFloat(aInput), Float.parseFloat(bInput), Float.parseFloat(cInput));
                    x1ResultTextView.setText(results[0]);
                    x2ResultTextView.setText(results[1]);
                }
				break;

			case "calcSEButton" :
                String a1Input = ((EditText) findViewById(R.id.a1EditText)).getText().toString();
                String b1Input = ((EditText) findViewById(R.id.b1EditText)).getText().toString();
                String c1Input = ((EditText) findViewById(R.id.c1EditText)).getText().toString();
                String a2Input = ((EditText) findViewById(R.id.a2EditText)).getText().toString();
                String b2Input = ((EditText) findViewById(R.id.b2EditText)).getText().toString();
                String c2Input = ((EditText) findViewById(R.id.c2EditText)).getText().toString();
                if(!a1Input.equals("") && !b1Input.equals("") && !c1Input.equals("") && !a2Input.equals("") && !b2Input.equals("") && !c2Input.equals("")) {
                    TextView xResultTextView = (TextView) findViewById(R.id.xSELabel);
                    TextView yResultTextView = (TextView) findViewById(R.id.ySELabel);
                    String[] results = twoEquationsSystem(Float.parseFloat(a1Input), Float.parseFloat(b1Input), Float.parseFloat(c1Input), Float.parseFloat(a2Input), Float.parseFloat(b2Input), Float.parseFloat(c2Input));
                    xResultTextView.setText(results[0]);
                    yResultTextView.setText(results[1]);
                }
				break;

			default :
				// Do nothing
				break;
		}
	}

	private String linearEquation(float m, float q) {
		return Utils.truncateResult("" + (- q / m));
	}

    private String[] quadraticEquation(float a, float b, float c) {
        String[] results = new String[2];
        double delta = Math.pow(b, 2) - (4 * a * c);
        if(delta >= 0) {
            double result1 = (-b + Math.sqrt(delta)) / (2 * a);
            double result2 = (-b - Math.sqrt(delta)) / (2 * a);
            results[0] = Utils.truncateResult("" + result1);
            results[1] = Utils.truncateResult("" + result2);
        } else {
            results[0] = "\u0394 < 0";
            results[1] = "\u0394 < 0";
        }
        return results;
    }

    private String[] twoEquationsSystem(float a1, float b1, float c1, float a2, float b2, float c2) {
        String[] results = new String[2];
        float yContributeFromEq1 = (- b1 / a1) * a2;
        float kContributeFromEq1 = (c1 / a1) * a2;
        b2 += yContributeFromEq1;
        c2 -= kContributeFromEq1;
        float yResult = c2 / b2;
        float xResult = ((- b1 / a1) * yResult) + (c1 / a1);
        String rawXResult = "" + xResult;
        String rawYResult = "" + yResult;
        results[0] = Utils.truncateResult(rawXResult);
        results[1] = Utils.truncateResult(rawYResult);
        return results;
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
