/*
 * Name: Bobby Jonkman
 * Date: May.2.2021
 * Purpose: Part of the ProfileSummary deserialization.
 */

package Model.ProfileSummary;

import com.google.gson.annotations.SerializedName;

public class PlayableClass
{
    // instance variables.
    @SerializedName("name")
    private String playableClass;


    /* Getters */
    public String getPlayableClass()
    {
        return playableClass;
    }
}// end of class.
