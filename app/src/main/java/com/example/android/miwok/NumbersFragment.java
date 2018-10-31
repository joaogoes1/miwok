package com.example.android.miwok;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class NumbersFragment extends Fragment {

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

    public NumbersFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.word_list, container, false);

        audioManager = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);

        final ArrayList<Word> words = new ArrayList<Word>();

        words.add(new Word("one", "lutti", R.raw.number_one, R.drawable.number_one));
        words.add(new Word("two", "otiiko", R.raw.number_two, R.drawable.number_two));
        words.add(new Word("three", "tolookosu", R.raw.number_three, R.drawable.number_three));
        words.add(new Word("four", "oyyisa", R.raw.number_four, R.drawable.number_four));
        words.add(new Word("five", "massokka", R.raw.number_five, R.drawable.number_five));
        words.add(new Word("six", "temmokka", R.raw.number_six, R.drawable.number_six));
        words.add(new Word("seven", "kenekaku", R.raw.number_seven, R.drawable.number_seven));
        words.add(new Word("eight", "kawinta", R.raw.number_eight, R.drawable.number_eight));
        words.add(new Word("nine", "wo’e", R.raw.number_nine, R.drawable.number_nine));
        words.add(new Word("ten", "na’aacha", R.raw.number_ten, R.drawable.number_ten));


        WordAdapter adapter = new WordAdapter(getActivity(), words, R.color.category_numbers);
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
