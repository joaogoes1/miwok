/*
package com.example.android.miwok;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

*/
/**
 * A simple {@link Fragment} subclass.
 *//*

public class MainFragment extends Fragment {


    public MainFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_main, container, false);

        TextView numbers = (TextView) getActivity().findViewById(R.id.numbers);
        TextView family = (TextView) getActivity().findViewById(R.id.family);
        TextView phrase = (TextView) getActivity().findViewById(R.id.phrases);
        TextView color = (TextView) getActivity().findViewById(R.id.colors);

        numbers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), NumbersActivity.class);
                startActivity(i);
            }
        });

        family.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), FamilyActivity.class);
                startActivity(i);
            }
        });

        phrase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), PhrasesActivity.class);
                startActivity(i);
            }
        });

        color.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), ColorsActivity.class);
                startActivity(i);
            }
        });
        return rootView;
    }

}
*/
