/*
 * Name: Bobby Jonkman
 * Date: May.2.2021
 * Purpose: This class will be the parent class for parsing the MediaSummaryRequest.
 */

package Utilities.APIResponse.MediaSummary;

import Model.MediaSummary.Assets;

public class MediaSummaryResponse
{
    // instance variables.
    private Assets[] assets;


    /* Getters */
    public Assets[] getAssets()
    {
        return assets;
    }
}// end of class.
