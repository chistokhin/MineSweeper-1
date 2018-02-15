package com.minesweeper.s6h.minesweeper;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeMenuFragment extends Fragment {

    TextView mPlayTextView;
    TextView mHelpTextView;


    public HomeMenuFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home_menu, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();

        View view = getView();

        mPlayTextView = view.findViewById(R.id.play_textView);
        mHelpTextView = view.findViewById(R.id.help_textView);

        mPlayTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gameIntent = new Intent(getContext(), GameActivity.class);
                startActivity(gameIntent);
            }
        });

        mHelpTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent helpIntent = new Intent(getContext(), HelpActivity.class);
                startActivity(helpIntent);
            }
        });
    }
}
