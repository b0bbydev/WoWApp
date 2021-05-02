/*
 * Name: Bobby Jonkman
 * Date: May.2.2021
 * Purpose: Part of the ProfileSummary deserialization.
 */

package Model.ProfileSummary;

import com.google.gson.annotations.SerializedName;

public class Faction
{
    // instance variables.
    @SerializedName("name")
    private String faction;


    /* Getters */
    public String getFaction()
    {
        return faction;
    }
}// end of class.
