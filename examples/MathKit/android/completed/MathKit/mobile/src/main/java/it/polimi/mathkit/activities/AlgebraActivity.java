package it.polimi.mathkit.activities;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SwitchCompat;
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
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import it.polimi.mathkit.R;
import it.polimi.mathkit.utilities.Constants;
import it.polimi.mathkit.utilities.OnFragmentNavigationInteractionListener;
import it.polimi.mathkit.fragments.ListFragment;
import it.polimi.mathkit.fragments.FractionsFragment;
import it.polimi.mathkit.fragments.ProbabilityFragment;
import it.polimi.mathkit.fragments.CommonsFragment;
import it.polimi.mathkit.utilities.Utils;

public class AlgebraActivity extends AppCompatActivity
		implements
			OnFragmentNavigationInteractionListener,
			NavigationView.OnNavigationItemSelectedListener {

	private int layoutType;
	private SectionsPagerAdapter mSectionsPagerAdapter;
	private ViewPager mViewPager;
    private ActionBarDrawerToggle mDrawerToggle;
    private boolean mToolBarNavigationListenerIsRegistered = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_algebra);
		Toolbar toolbar = (Toolbar) this.findViewById(R.id.algebra_toolbar);
		setSupportActionBar(toolbar);

		// Set layout type
		if(findViewById(R.id.listContainer) != null)
			layoutType = Constants.SCENE_TYPE_MULTI_VC;
		else
			layoutType = Constants.SCENE_TYPE_SINGLE_VC;

        // Setup ViewPager
        mViewPager = (ViewPager) this.findViewById(R.id.algebra_container);
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mSectionsPagerAdapter);

		// Setup ActionBarDrawerToggle and NavigationView
		DrawerLayout drawer = (DrawerLayout) findViewById(R.id.algebra_drawer_layout);
        mDrawerToggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open,
				R.string.navigation_drawer_close);
		drawer.addDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();

		NavigationView navigationView = (NavigationView) findViewById(R.id.algebra_nav_view);
		navigationView.setNavigationItemSelectedListener(this);
	}

	@Override
	public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.algebra_drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else if((layoutType == Constants.SCENE_TYPE_SINGLE_VC) && (mViewPager.getCurrentItem() != 0)) {
            mViewPager.setCurrentItem(0);
            switchBarButton(false);
        } else {
			super.onBackPressed();
		}
	}

    private void switchBarButton(boolean showBackButton) {

        // To keep states of ActionBar and ActionBarDrawerToggle synchronized,
        // when you enable on one, you disable on the other.
        // And as you may notice, the order for this operation is disable first, then enable - VERY VERY IMPORTANT.
        if(showBackButton) {
            // Remove hamburger
            mDrawerToggle.setDrawerIndicatorEnabled(false);
            // Show back button
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            // when DrawerToggle is disabled i.e. setDrawerIndicatorEnabled(false), navigation icon
            // clicks are disabled i.e. the UP button will not work.
            // We need to add a listener, as in below, so DrawerToggle will forward
            // click events to this listener.
            if(!mToolBarNavigationListenerIsRegistered) {
                mDrawerToggle.setToolbarNavigationClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Doesn't have to be onBackPressed
                        onBackPressed();
                    }
                });

                mToolBarNavigationListenerIsRegistered = true;
            }

        } else {
            // Remove back button
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            // Show hamburger
            mDrawerToggle.setDrawerIndicatorEnabled(true);
            // Remove the/any drawer toggle listener
            mDrawerToggle.setToolbarNavigationClickListener(null);
            mToolBarNavigationListenerIsRegistered = false;
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
				switch (clickedItem) {
                    case "Fractions":
                        mViewPager.setCurrentItem(layoutType == Constants.SCENE_TYPE_MULTI_VC? 0 : 1);
                        break;

                    case "Probability":
                        mViewPager.setCurrentItem(1 + (layoutType == Constants.SCENE_TYPE_MULTI_VC? 0 : 1));
                        break;

                    case "GCF-LCM":
                        mViewPager.setCurrentItem(2 + (layoutType == Constants.SCENE_TYPE_MULTI_VC? 0 : 1));
                        break;
                }
				if(layoutType == Constants.SCENE_TYPE_SINGLE_VC) {
                    if (mViewPager.getCurrentItem() != 0)
                        switchBarButton(true);
                    else
                        switchBarButton(false);
                }
				break;

			case "calcFractionsButton" :
			    String decimalInput = ((EditText) findViewById(R.id.decimalEditText)).getText().toString();
			    if(!decimalInput.equals("")) {
                    TextView dtfResultTextView = (TextView) findViewById(R.id.fractionResultLabel);
                    dtfResultTextView.setText(decimalToFraction(decimalInput));
                }
                String numInput = ((EditText) findViewById(R.id.numEditText)).getText().toString();
                String denInput = ((EditText) findViewById(R.id.denEditText)).getText().toString();
                if(!numInput.equals("") && !denInput.equals("")) {
                    TextView simplifyResultTextView = (TextView) findViewById(R.id.simpleResultLabel);
                    TextView ftdResultTextView = (TextView) findViewById(R.id.decimalResultLabel);
                    simplifyResultTextView.setText(simplifyFraction(Integer.parseInt(numInput), Integer.parseInt(denInput)));
                    ftdResultTextView.setText(fractionToDecimal(Integer.parseInt(numInput), Integer.parseInt(denInput)));
                }
				break;

			case "calcProbButton" :
                String nInput = ((EditText) findViewById(R.id.cardinalityEditText)).getText().toString();
                String kInput = ((EditText) findViewById(R.id.kEditText)).getText().toString();
                boolean order = ((SwitchCompat) findViewById(R.id.orderSwitchButton)).isChecked();
                boolean repetition = ((SwitchCompat) findViewById(R.id.repeatSwitchButton)).isChecked();
                if(!nInput.equals("") && !kInput.equals("")) {
                    TextView permResultTextView = (TextView) findViewById(R.id.resultPermLabel);
                    permResultTextView.setText(permutations(Integer.parseInt(nInput), Integer.parseInt(kInput), order, repetition));
                }
				break;

			case "calcCommButton" :
                String aInput = ((EditText) findViewById(R.id.firstEditText)).getText().toString();
                String bInput = ((EditText) findViewById(R.id.secondEditText)).getText().toString();
                if(!aInput.equals("") && !bInput.equals("")) {
                    TextView gcfTextView = (TextView) findViewById(R.id.gcfResultLabel);
                    TextView lcmTextView = (TextView) findViewById(R.id.lcmResultLabel);
                    gcfTextView.setText(gcf(Integer.parseInt(aInput), Integer.parseInt(bInput)));
                    lcmTextView.setText(lcm(Integer.parseInt(aInput), Integer.parseInt(bInput)));
                }
				break;

			default :
				// Do nothing
				break;
		}
	}

	private String decimalToFraction(String rawInput) {
        if (!rawInput.contains("."))
            return rawInput + " / 1";
        else {
            String[] splittedInput = rawInput.split("\\.");
            int decPart = Integer.parseInt(splittedInput[1]);
            int n = decPart, den = 1;
            while(n % ((int) Math.pow(10, splittedInput[1].length())) != 0) {
                n += decPart;
                den++;
            }
            int num = (int) (Float.parseFloat(rawInput) * den);
            return num + " / " + den;
        }
    }

    private String simplifyFraction(int num, int den) {
	    if(num % den == 0)
	        return (num / den) + " / 1";
        boolean flag = false;
        while(!flag) {
            flag = true;
            for(int i = 2; i < Math.min(num, den) && flag; i++)
                if((num % i == 0) && (den % i == 0)) {
                    num /= i;
                    den /= i;
                    flag = false;
                }
        }
        return num + " / " + den;
    }

    private String fractionToDecimal(int num, int den) {
	    float n = num, d = den;
        return Utils.truncateResult("" + (n / d));
    }

    private String permutations(int n, int k, boolean order, boolean repetiton) {
        if(order && repetiton)
            return "" + ((int) Math.pow(n, k));
        else if(!order && repetiton)
            return "" + (fact(n + k - 1) / (fact(k) * fact(n - 1)));
        else if(order && (n >= k))
            return "" + (fact(n) / (fact(n - k)));
        else if(!order && (n >= k))
            return "" + (fact(n) / (fact(k) * fact(n - k)));
        return "(no result)";
    }

    private int fact(int n) {
	    int result = 1;
	    while(n > 1) {
            result *= n;
            n--;
        }
        return result;
    }

    private String gcf(int a, int b) {
        int gcf = Math.min(a, b);
        while((a % gcf != 0) || (b % gcf != 0)) {
            gcf--;
        }
        return "" + gcf;
    }

    private String lcm(int a, int b) {
        int lcm = Math.max(a, b);
        while((lcm % a != 0) || (lcm % b != 0)) {
            lcm++;
        }
        return "" + lcm;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // app icon in action bar clicked; goto parent activity.
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

	public class SectionsPagerAdapter extends FragmentPagerAdapter {

		public SectionsPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
		    if(layoutType == Constants.SCENE_TYPE_SINGLE_VC) {
                switch (position) {
                    case 0:
                        return ListFragment.newInstance();
                    case 1:
                        return FractionsFragment.newInstance();
                    case 2:
                        return ProbabilityFragment.newInstance();
                    case 3:
                        return CommonsFragment.newInstance();
                }
            } else {
                switch (position) {
                    case 0:
                        return FractionsFragment.newInstance();
                    case 1:
                        return ProbabilityFragment.newInstance();
                    case 2:
                        return CommonsFragment.newInstance();
                }
            }
			return null;
		}

		@Override
		public int getCount() {
            if(layoutType == Constants.SCENE_TYPE_SINGLE_VC)
                return 4;
			return 3;
		}
	}

}
