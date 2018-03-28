package it.polimi.mathkit.fragments;

import android.support.annotation.Nullable;
import android.os.Bundle;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatEditText;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import it.polimi.mathkit.utilities.OnFragmentNavigationInteractionListener;
import android.content.Context;
import it.polimi.mathkit.R;

public class QuadraticFragment extends Fragment {

	private View rootView;
	private OnFragmentNavigationInteractionListener mListener;
	private Button calcQEButton;

	private AppCompatEditText aEditText;
	private AppCompatEditText bEditText;
	private AppCompatEditText cEditText;
	private ImageView guideQEImageView;

	public QuadraticFragment() {
	}

	/**
	 * Use this factory method to create a new instance of
	 * this fragment using the provided parameters.
	 * TODO: edit method's parameters
	 * @return A new instance of fragment QuadraticFragment.
	 */
	public static QuadraticFragment newInstance() {
		QuadraticFragment fragment = new QuadraticFragment();
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

		View rootView = inflater.inflate(R.layout.fragment_quadratic, container, false);
		this.rootView = rootView;

		this.calcQEButton = (Button) rootView.findViewById(R.id.calcQEButton);
		this.calcQEButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// Since a fragment may be used in multiple activities, ask parent activity what to do on the click event
				if (mListener != null) {
					mListener.onFragmentNavigationInteraction("calcQEButton", null);
				}
			}
		});

		this.aEditText = (AppCompatEditText) rootView.findViewById(R.id.aEditText);
		this.aEditText.setText("");
		this.bEditText = (AppCompatEditText) rootView.findViewById(R.id.bEditText);
		this.bEditText.setText("");
		this.cEditText = (AppCompatEditText) rootView.findViewById(R.id.cEditText);
		this.cEditText.setText("");

		this.guideQEImageView = (ImageView) rootView.findViewById(R.id.guideQEImageView);

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
