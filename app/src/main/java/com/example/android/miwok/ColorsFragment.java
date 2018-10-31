package com.example.android.miwok;


import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class ColorsFragment extends Fragment {


    private MediaPlayer player;
    private AudioManager audioManager;

    private MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            releaseMediaPlayer();
        }
    };

    private AudioManager.OnAudioFocusChangeListener focusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {
            switch (focusChange) {
                case AudioManager.AUDIOFOCUS_LOSS:
                    releaseMediaPlayer();
                    break;
                case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT:
                    player.pause();
                    player.seekTo(0);
                    break;
                case AudioManager.AUDIOFOCUS_GAIN:
                    player.start();
                    break;
                case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK:
                    player.pause();
                    player.seekTo(0);
                    break;
            }
        }
    };


    public ColorsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.word_list, container, false);

        audioManager = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);

        final ArrayList<Word> words = new ArrayList<>();

        words.add(new Word("red", "weṭeṭṭi", R.raw.color_red, R.drawable.color_red));
        words.add(new Word("green", "chokokki", R.raw.color_green, R.drawable.color_green));
        words.add(new Word("brown", "ṭakaakki", R.raw.color_brown, R.drawable.color_brown));
        words.add(new Word("gray", "ṭopoppi", R.raw.color_gray, R.drawable.color_gray));
        words.add(new Word("black", "kululli", R.raw.color_black, R.drawable.color_black));
        words.add(new Word("white", "kelelli", R.raw.color_white, R.drawable.color_white));
        words.add(new Word("dusty yellow", "ṭopiisә", R.raw.color_dusty_yellow, R.drawable.color_dusty_yellow));
        words.add(new Word("mustard yellow", "chiwiiṭә", R.raw.color_mustard_yellow, R.drawable.color_mustard_yellow));


        WordAdapter adapter = new WordAdapter(getActivity(), words, R.color.category_colors);
        ListView listView = rootView.findViewById(R.id.list);
        listView.setAdapter(adapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                releaseMediaPlayer();
                int focusResult = audioManager.requestAudioFocus(focusChangeListener, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
                if (focusResult == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    player = MediaPlayer.create(getActivity(), words.get(position).getAudioResourceId());
                    player.start();
                    player.setOnCompletionListener(mCompletionListener);
                }
            }
        });

        return rootView;
    }

    @Override
    public void onPause() {
        super.onPause();

        releaseMediaPlayer();
    }

    private void releaseMediaPlayer() {
        if (player != null) {
            player.release();
            player = null;
            audioManager.abandonAudioFocus(focusChangeListener);
        }
    }

}
