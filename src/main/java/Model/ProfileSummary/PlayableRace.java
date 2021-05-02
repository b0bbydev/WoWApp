/*
 * Name: Bobby Jonkman
 * Date: May.2.2021
 * Purpose: Part of the ProfileSummary deserialization.
 */

package Model.ProfileSummary;

import com.google.gson.annotations.SerializedName;

public class PlayableRace
{
    // instance variables.
    @SerializedName("name")
    private String playableRace;


    /* Getters */
    public String getPlayableRace()
    {
        return playableRace;
    }


    public String toString()
    {
        return String.format("%s", playableRace);
    }// end of toString().
}// end of class.
