package nourl.tbd.Blipp.BlippConstructs;

import com.google.firebase.auth.FirebaseAuth;

public class Like
{
    private boolean isDislike;
    private String blipId;
    private String userId;

    //This is the constructor that will be used for Loading likes. Note that all information is passed manually all the time. This is because we will likely load likes that were not generated by the current user on many different blips.
    public Like(boolean isDislike, String blipId, String userId)
    {
        this.isDislike = isDislike;
        this.blipId = blipId;
        this.userId = userId;
    }

    //This is the constructor that will be used for generating a like. This automatically grabs the current user id and the blip id from the blip.
    public Like(Blipp blip, boolean isDislike)
    {
        this.isDislike = isDislike;
        this.blipId = blip.getId();
        this.userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
    }

    //Note: We still use push to store likes to generate unique keys we just do not store the unique keys inside the object, there is no benefit.

    public boolean isDislike()
    {
        return isDislike;
    }

    public String getBlipId()
    {
        return blipId;
    }

    public String getUserId()
    {
        return userId;
    }
}
