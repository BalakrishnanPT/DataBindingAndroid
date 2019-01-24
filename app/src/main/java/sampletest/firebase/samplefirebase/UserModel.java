package sampletest.firebase.samplefirebase;

import android.arch.core.util.Function;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class UserModel extends ViewModel {

    private static String TAG = "ListViewModel";

    private static final DatabaseReference dataRef =
            FirebaseDatabase.getInstance().getReference().child("Testing");

    List<Entity> mList = new ArrayList<>();

    @NonNull
    LiveData<List<Entity>> getMessageListLiveData() {
        FirebaseQueryLiveData mLiveData = new FirebaseQueryLiveData(dataRef);

        LiveData<List<Entity>> mMessageLiveData =
                Transformations.map(mLiveData, new Deserializer());

        return mMessageLiveData;
    }

    private class Deserializer implements Function<DataSnapshot, List<Entity>> {

        @Override
        public List<Entity> apply(DataSnapshot dataSnapshot) {
            mList.clear();
            for (DataSnapshot snap : dataSnapshot.getChildren()) {
                Entity msg = snap.getValue(Entity.class);
                mList.add(msg);
            }
            return mList;
        }
    }

    private final MutableLiveData<Boolean> messageUploadIsSuccessful = new MutableLiveData<>();

    void createAndSendToDataBase(Entity entity) {
        // push the new message to Firebase
        Task uploadTask = dataRef
                .push()
                .setValue(entity);
        uploadTask.addOnSuccessListener(o -> messageUploadIsSuccessful.setValue(true));
    }

    void remove(String s,Actions actions) {
        Query applesQuery = FirebaseDatabase.getInstance().getReference().child("Testing").orderByChild("email").equalTo(s);
        applesQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot appleSnapshot : dataSnapshot.getChildren()) {
                    appleSnapshot.getRef().removeValue();
                    actions.removed();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e(TAG, "onCancelled", databaseError.toException());
            }
        });
    }
    interface Actions{
        void removed();
    }

}
