package social.twittersample;

/**
 * Created by Tarun Varshney on 10/14/2015.
 */
public interface CallbackWithObject {

    //To be called while the network call is in progress
    public void onProgress();

    //To be called on completion of the network call
    public void onResult(String result);
}