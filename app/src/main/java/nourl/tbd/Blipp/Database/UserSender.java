package nourl.tbd.Blipp.Database;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import nourl.tbd.Blipp.BlippConstructs.User;

public class UserSender extends AsyncTask<Void, Boolean, Void> {

    FirebaseDatabase db;
    DatabaseReference location;
    User user;
    UserSenderCompletion completion;
    Handler uiThread;

    public UserSender(User user, UserSenderCompletion completion, Context context) {
        this.user = user;
        this.completion = completion;
        db = FirebaseDatabase.getInstance("https://blipp-15ee8.firebaseio.com/");
        location = db.getReference().child("user");
        uiThread = new Handler(context.getMainLooper());
        this.execute();
    }

    @Override
    protected Void doInBackground(Void... voids) {
        DatabaseReference here = location.child(user.getId());
        here.setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task)
            {
               taskDone(task.isSuccessful());
            }
        });
        return null;
    }

   void taskDone(final boolean isSuccessful)
   {
       uiThread.post(new Runnable() {
           @Override
           public void run() {
               completion.userSenderDone(isSuccessful);
           }
       });
   }
}
