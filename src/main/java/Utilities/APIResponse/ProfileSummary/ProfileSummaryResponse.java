/*
 * Name: Bobby Jonkman
 * Date: May.2.2021
 * Purpose: This class will be the parent class for parsing the ProfileSummaryRequest.
 */

package Utilities.APIResponse.ProfileSummary;

import Model.ProfileSummary.WowAccounts;
import com.google.gson.annotations.SerializedName;

public class ProfileSummaryResponse {
    // instance variables.
    @SerializedName("wow_accounts")
    private WowAccounts[] wowAccounts;

    /* Getters */
    public WowAccounts[] getWowAccounts() {
        return wowAccounts;
    }
}// end of class.
