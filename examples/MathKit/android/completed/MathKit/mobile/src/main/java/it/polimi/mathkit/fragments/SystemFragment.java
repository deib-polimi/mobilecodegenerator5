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

public class SystemFragment extends Fragment {

	private View rootView;
	private OnFragmentNavigationInteractionListener mListener;
	private Button calcSEButton;

	private AppCompatEditText a1EditText;
	private AppCompatEditText b1EditText;
	private AppCompatEditText c1EditText;
	private AppCompatEditText a2EditText;
	private AppCompatEditText b2EditText;
	private AppCompatEditText c2EditText;
	private ImageView guideSEImageView;

	public SystemFragment() {
	}

	/**
	 * Use this factory method to create a new instance of
	 * this fragment using the provided parameters.
	 * TODO: edit method's parameters
	 * @return A new instance of fragment SystemFragment.
	 */
	public static SystemFragment newInstance() {
		SystemFragment fragment = new SystemFragment();
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

		View rootView = inflater.inflate(R.layout.fragment_system, container, false);
		this.rootView = rootView;

		this.calcSEButton = (Button) rootView.findViewById(R.id.calcSEButton);
		this.calcSEButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// Since a fragment may be used in multiple activities, ask parent activity what to do on the click event
				if (mListener != null) {
					mListener.onFragmentNavigationInteraction("calcSEButton", null);
				}
			}
		});

		this.a1EditText = (AppCompatEditText) rootView.findViewById(R.id.a1EditText);
		this.a1EditText.setText("");
		this.b1EditText = (AppCompatEditText) rootView.findViewById(R.id.b1EditText);
		this.b1EditText.setText("");
		this.c1EditText = (AppCompatEditText) rootView.findViewById(R.id.c1EditText);
		this.c1EditText.setText("");
		this.a2EditText = (AppCompatEditText) rootView.findViewById(R.id.a2EditText);
		this.a2EditText.setText("");
		this.b2EditText = (AppCompatEditText) rootView.findViewById(R.id.b2EditText);
		this.b2EditText.setText("");
		this.c2EditText = (AppCompatEditText) rootView.findViewById(R.id.c2EditText);
		this.c2EditText.setText("");

		this.guideSEImageView = (ImageView) rootView.findViewById(R.id.guideSEImageView);

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
