package Model.ProfileSummary;

import Utilities.APIRequest.ProfileSummaryRequest;
import Utilities.APIResponse.ProfileSummaryResponse;
import com.google.gson.Gson;

import java.io.IOException;

public class Test
{
    public static void main(String[] args) throws IOException
    {
        ProfileSummaryResponse response = new Gson().fromJson(ProfileSummaryRequest.profileSummaryGet(), ProfileSummaryResponse.class);

        System.out.println("");
    }
}
