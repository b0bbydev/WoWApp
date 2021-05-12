/*
 * Name: Bobby Jonkman
 * Date: May.2.2021
 * Purpose: This class will be the parent class for parsing the MediaSummaryRequest.
 */

package Utilities.APIResponse.MediaSummary;

import Model.MediaSummary.Assets;

public class MediaSummaryResponse {
    // instance variables.
    private Assets[] assets;
    private int code;

    /* Getters */
    public Assets[] getAssets() {
        return assets;
    }

    public int getCode() {
        return code;
    }
}// end of class.
