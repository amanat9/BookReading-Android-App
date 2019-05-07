package com.example.mama.logyus;

/**
 * Created by 16101219 on 12/4/2018.
 */

import android.support.annotation.NonNull;

import com.google.firebase.firestore.Exclude;

public class uEventId {
    @Exclude
    public String uEventId;

    public <T extends uEventId>T withId(@NonNull final String id)
    {
        this.uEventId=id;
        return (T)this;
    }


}
