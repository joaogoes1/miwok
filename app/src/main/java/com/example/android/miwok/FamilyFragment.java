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
import android.widget.TextView;

import java.util.ArrayList;

public class FamilyFragment extends Fragment {

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


    public FamilyFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.word_list, container, false);

        audioManager = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);

        final ArrayList<Word> words = new ArrayList<>();

        words.add(new Word("father", "әpә", R.raw.family_father, R.drawable.family_father));
        words.add(new Word("mother", "әṭa", R.raw.family_mother, R.drawable.family_mother));
        words.add(new Word("son", "angsi", R.raw.family_son, R.drawable.family_son));
        words.add(new Word("daughter", "tune", R.raw.family_daughter, R.drawable.family_daughter));
        words.add(new Word("older brother", "taachi", R.raw.family_older_brother, R.drawable.family_older_brother));
        words.add(new Word("younger brother", "chalitti", R.raw.family_younger_brother, R.drawable.family_younger_brother));
        words.add(new Word("older sister", "teṭe", R.raw.family_older_brother, R.drawable.family_older_sister));
        words.add(new Word("younger sister", "kolliti", R.raw.family_younger_sister, R.drawable.family_younger_sister));
        words.add(new Word("grandmother", "ama", R.raw.family_grandmother, R.drawable.family_grandmother));
        words.add(new Word("grandfather", "paapa", R.raw.family_grandfather, R.drawable.family_grandfather));

        WordAdapter adapter = new WordAdapter(getActivity(), words, R.color.category_family);
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
