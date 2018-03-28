package it.polimi.mathkit.fragments;

import android.support.annotation.Nullable;
import android.os.Bundle;
import android.content.Intent;
import android.support.v4.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import it.polimi.mathkit.adapters.MyListAdapter;
import it.polimi.mathkit.utilities.OnFragmentNavigationInteractionListener;
import android.content.Context;
import it.polimi.mathkit.R;

public class ListFragment extends Fragment implements OnItemClickListener {

	private View rootView;
	private OnFragmentNavigationInteractionListener mListener;

	private ListView toolsListView;
	private MyListAdapter toolsListViewAdapter;

	private Integer[] toolsListViewImages;
	private String[] toolsListViewContents;

	public ListFragment() {
	}

	/**
	 * Use this factory method to create a new instance of
	 * this fragment using the provided parameters.
	 * TODO: edit method's parameters
	 * @return A new instance of fragment ListFragment.
	 */
	public static ListFragment newInstance() {
		ListFragment fragment = new ListFragment();
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

		View rootView = inflater.inflate(R.layout.fragment_list, container, false);
		this.rootView = rootView;

		//Change this to set a specific image id for each row
		this.toolsListViewImages = new Integer[]{R.drawable.ic_stars_white, R.drawable.ic_stars_white,
				R.drawable.ic_stars_white,};
		this.toolsListViewContents = new String[]{"Fractions", "Probability", "GCF-LCM",};

		this.toolsListViewAdapter = new MyListAdapter(getActivity(), R.layout.toolslistview_image_layout,
				this.toolsListViewContents, this.toolsListViewImages);
		this.toolsListView = (ListView) rootView.findViewById(R.id.toolsListView);
		this.toolsListView.setAdapter(this.toolsListViewAdapter);
		this.toolsListView.setOnItemClickListener(this);

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
	public void onItemClick(AdapterView<?> l, View v, int position, long id) {
		if (l.getId() == R.id.toolsListView) {
			String clickedItem = this.toolsListViewContents[position];
			// Since a fragment may be used in multiple activities, ask parent activity what to do on the click event
			if (mListener != null) {
				mListener.onFragmentNavigationInteraction("toolsListView", clickedItem);
			}
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
