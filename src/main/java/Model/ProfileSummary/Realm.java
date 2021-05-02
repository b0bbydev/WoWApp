/*
 * Name: Bobby Jonkman
 * Date: May.2.2021
 * Purpose: Part of the ProfileSummary deserialization.
 */

package Model.ProfileSummary;

import com.google.gson.annotations.SerializedName;

public class Realm
{
    @SerializedName("name")
    private String realm;


    /* Getters */
    public String getRealm()
    {
        return realm;
    }
}// end of class.
