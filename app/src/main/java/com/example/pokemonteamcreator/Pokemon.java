package com.example.pokemonteamcreator;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Base64;

import java.io.ByteArrayOutputStream;

public class Pokemon
{
    private String name, type1, type2, ability1, pic;
    public Pokemon()
    {

    }
    public Pokemon(String name, String type1, String type2, String ability1, Bitmap image)
    {
        this.name = name;
        this.type1 = type1;
        this.type2 = type2;
        this.ability1 = ability1;
        ByteArrayOutputStream stream=new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG,100,stream);
        byte[] bytes=stream.toByteArray();
        pic = Base64.encodeToString(bytes,Base64.DEFAULT);
    }

    public String getName()
    {
        return name;
    }
    public String getType1()
    {
        return type1;
    }
    public String getType2()
    {
        return type2;
    }
    public String getAbility1()
    {
        return ability1;
    }
    public String getPic()
    {
        return pic;
    }
    public String toString()
    {
        return name;
    }

}
