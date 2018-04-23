package sampletest.firebase.samplefirebase;

import android.arch.core.util.Function;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class UserModel extends ViewModel {

    private static String TAG = "ListViewModel";

    private static final DatabaseReference dataRef =
            FirebaseDatabase.getInstance().getReference().child("Testing");

    private List<Entity> mList = new ArrayList<>();

    @NonNull
    public LiveData<List<Entity>> getMessageListLiveData(){
        FirebaseQueryLiveData mLiveData = new FirebaseQueryLiveData(dataRef);

        LiveData<List<Entity>> mMessageLiveData =
                Transformations.map(mLiveData, new Deserializer());

        return mMessageLiveData;
    }

    private class Deserializer implements Function<DataSnapshot, List<Entity>> {

        @Override
        public List<Entity> apply(DataSnapshot dataSnapshot) {
            mList.clear();
            for(DataSnapshot snap : dataSnapshot.getChildren()){
                Entity msg = snap.getValue(Entity.class);
                mList.add(msg);
            }
            return mList;
        }
    }

    private final MutableLiveData<Boolean> messageUploadIsSuccessful = new MutableLiveData<>();

    public void createAndSendToDataBase(Entity entity){
        // push the new message to Firebase
        Task uploadTask = FirebaseDatabase.getInstance()
                .getReference()
                .child("Testing")
                .push()
                .setValue(entity);
        uploadTask.addOnSuccessListener(o -> messageUploadIsSuccessful.setValue(true));
    }


}
