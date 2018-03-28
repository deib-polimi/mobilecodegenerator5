package it.polimi.mathkit.fragments;

import android.support.annotation.Nullable;
import android.os.Bundle;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.widget.SwitchCompat;
import android.support.v7.widget.AppCompatEditText;
import android.util.Log;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.TextView;
import it.polimi.mathkit.utilities.OnFragmentNavigationInteractionListener;
import android.content.Context;
import it.polimi.mathkit.R;

public class ProbabilityFragment extends Fragment {

	private View rootView;
	private OnFragmentNavigationInteractionListener mListener;
	private Button calcProbButton;

	private AppCompatEditText cardinalityEditText;
	private AppCompatEditText kEditText;
	private SwitchCompat orderSwitchButton;
	private SwitchCompat repeatSwitchButton;

	public ProbabilityFragment() {
	}

	/**
	 * Use this factory method to create a new instance of
	 * this fragment using the provided parameters.
	 * TODO: edit method's parameters
	 * @return A new instance of fragment ProbabilityFragment.
	 */
	public static ProbabilityFragment newInstance() {
		ProbabilityFragment fragment = new ProbabilityFragment();
		// TODO: put needed arguments
		// Bundle args = new Bundle();
		// args.putInt("example", 0);
		// fragment.setArguments(args);
		return fragment;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (getArguments() != null) {
			// TODO: setup fragment's attributes with passed arguments (ex. getArguments().getInt("example") )
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.fragment_probability, container, false);
		this.rootView = rootView;

		this.calcProbButton = (Button) rootView.findViewById(R.id.calcProbButton);
		this.calcProbButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// Since a fragment may be used in multiple activities, ask parent activity what to do on the click event
				if (mListener != null) {
					mListener.onFragmentNavigationInteraction("calcProbButton", null);
				}
			}
		});

		this.cardinalityEditText = (AppCompatEditText) rootView.findViewById(R.id.cardinalityEditText);
		this.cardinalityEditText.setText("");
		this.kEditText = (AppCompatEditText) rootView.findViewById(R.id.kEditText);
		this.kEditText.setText("");

		this.orderSwitchButton = (SwitchCompat) rootView.findViewById(R.id.orderSwitchButton);
		this.orderSwitchButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				Log.i("onCheckedChanged", "orderSwitchButton is checked : " + isChecked);
			}
		});
		this.repeatSwitchButton = (SwitchCompat) rootView.findViewById(R.id.repeatSwitchButton);
		this.repeatSwitchButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				Log.i("onCheckedChanged", "repeatSwitchButton is checked : " + isChecked);
			}
		});

		return rootView;
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
	}

	@Override
	public void setUserVisibleHint(boolean isVisibleToUser) {
		super.setUserVisibleHint(isVisibleToUser);

		if (isVisibleToUser) {

		} else {

		}

	}

	@Override
	public void onAttach(Context context) {
		super.onAttach(context);
		if (context instanceof OnFragmentNavigationInteractionListener) {
			mListener = (OnFragmentNavigationInteractionListener) context;
		} else {
			throw new RuntimeException(context.toString() + " must implement OnFragmentNavigationInteractionListener");
		}
	}

	@Override
	public void onDetach() {
		super.onDetach();
		mListener = null;
	}

	@Override
	public void onResume() {
		super.onResume();
	}

	@Override
	public void onPause() {
		super.onPause();
	}

}
