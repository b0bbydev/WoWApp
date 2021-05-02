/*
 * Name: Bobby Jonkman
 * Date: May.2.2021
 * Purpose: Part of the ProfileSummary deserialization.
 */

package Model.ProfileSummary;

import com.google.gson.annotations.SerializedName;

public class Gender
{
    // instance variables.
    @SerializedName("name")
    private String gender;


    /* Getters */
    public String getGender()
    {
        return gender;
    }


    public String toString()
    {
        return String.format("%s", gender);
    }// end of toString().
}// end of class.
