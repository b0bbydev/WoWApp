/*
 * Name: Bobby Jonkman
 * Date: May.2.2021
 * Purpose: Part of the ProfileSummary deserialization.
 */

package Model.ProfileSummary;

import com.google.gson.annotations.SerializedName;

public class Characters
{
    // instance variables.
    @SerializedName("name")
    private String characterName;
    @SerializedName("realm")
    private Realm characterRealm;
    @SerializedName("playable_class")
    private PlayableClass characterClass;
    @SerializedName("playable_race")
    private PlayableRace characterRace;
    @SerializedName("gender")
    private Gender characterGender;
    @SerializedName("faction")
    private Faction characterFaction;
    @SerializedName("level")
    private int characterLevel;


    /* Getters */
    public String getCharacterName()
    {
        return characterName;
    }

    public Realm getCharacterRealm()
    {
        return characterRealm;
    }

    public PlayableClass getCharacterClass()
    {
        return characterClass;
    }

    public PlayableRace getCharacterRace()
    {
        return characterRace;
    }

    public Gender getCharacterGender()
    {
        return characterGender;
    }

    public Faction getCharacterFaction()
    {
        return characterFaction;
    }

    public int getCharacterLevel()
    {
        return characterLevel;
    }


    public String toString()
    {
        return String.format("%s", characterName);
    }// end of toString().
}// end of class.
