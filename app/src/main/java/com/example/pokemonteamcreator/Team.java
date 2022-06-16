package com.example.pokemonteamcreator;


import android.os.Parcel;
import android.os.Parcelable;

public class Team
{
    private String name;
    private Pokemon p1,p2,p3,p4,p5,p6;
    public Team(){

    }
    public Team(String name, Pokemon p1, Pokemon p2, Pokemon p3, Pokemon p4, Pokemon p5, Pokemon p6)
    {
        this.name = name;
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
        this.p4 = p4;
        this.p5 = p5;
        this.p6 = p6;
    }


    public Pokemon getPokemon1()
    {
        return p1;
    }
    public Pokemon getPokemon2()
    {
        return p2;
    }
    public Pokemon getPokemon3()
    {
        return p3;
    }
    public Pokemon getPokemon4()
    {
        return p4;
    }
    public Pokemon getPokemon5()
    {
        return p5;
    }
    public Pokemon getPokemon6()
    {
        return p6;
    }
    public String getName()
    {
        return name;
    }
    public String toString(){return name;}

}
