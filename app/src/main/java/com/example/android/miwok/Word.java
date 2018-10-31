package com.example.android.miwok;

public class Word {

    /** Variável para tradução em Inglês */
    private String mDefaultTranslation;

    /** Variável para tradução em Miwok */
    private String mMiwokTranslation;

    private int mImageResourceId = -1;

    private int mAudioResourceId;

    /**Criar um novo Objeto Word.
     @param defaultTranslation é a palavra no idioma nativo do usuário
     @param miwokTranslation   é a palavra no idioma Miwok */
    public Word(String defaultTranslation, String miwokTranslation, int audioResourceId) {
        mDefaultTranslation = defaultTranslation;
        mMiwokTranslation = miwokTranslation;
        mAudioResourceId = audioResourceId;
    }

    /**Criar um novo Objeto Word.
     @param defaultTranslation é a palavra no idioma nativo do usuário
     @param miwokTranslation   é a palavra no idioma Miwok */
    public Word(String defaultTranslation, String miwokTranslation, int audioResourceId, int imageResourceId) {
        mDefaultTranslation = defaultTranslation;
        mMiwokTranslation = miwokTranslation;
        mAudioResourceId = audioResourceId;
        mImageResourceId = imageResourceId;
    }

    /** Pegar a tradução em Inglês da palavra */
    public String getDefaultTranslation() {
        return mDefaultTranslation;
    }

    /** Pegar a tradução em Miwok da palavra. */
    public String getMiwokTranslation() {
        return mMiwokTranslation;
    }

    public int getAudioResourceId() { return mAudioResourceId; }

    public int getImageResourceId() { return mImageResourceId; }
}