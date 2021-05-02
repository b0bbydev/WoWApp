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
}// end of class.
