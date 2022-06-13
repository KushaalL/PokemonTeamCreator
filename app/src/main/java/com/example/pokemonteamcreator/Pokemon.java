package com.example.pokemonteamcreator;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

public class Pokemon
{
    private String name, type1, type2, ability1;
    private Bitmap pic;

    public Pokemon(String name, String type1, String type2, String ability1, Bitmap pic)
    {
        this.name = name;
        this.type1 = type1;
        this.type2 = type2;
        this.ability1 = ability1;
        this.pic = pic;
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
    public Bitmap getPic()
    {
        return pic;
    }
    public String toString()
    {
        return name;
    }


}
