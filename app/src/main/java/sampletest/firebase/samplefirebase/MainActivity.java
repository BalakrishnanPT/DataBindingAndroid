package sampletest.firebase.samplefirebase;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import sampletest.firebase.samplefirebase.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private String TAG = getClass().getSimpleName();
    private DatabaseReference mDatabase;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        User user = new User();
        binding.setUser(user);
        binding.setActivity(this);

        Query queryRef = mDatabase.child("UserDetails").orderByChild("email");
        List<User> userList = new ArrayList<>();
        queryRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

//        Query myTopPostsQuery = mDatabase.child("UserDetails")
//                .orderByChild("starCount");
//        myTopPostsQuery.addChildEventListener(new ChildEventListener() {
//            @Override
//            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
//                Toast.makeText(MainActivity.this, "Added", Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
//
//            }
//
//            @Override
//            public void onChildRemoved(DataSnapshot dataSnapshot) {
//                Toast.makeText(MainActivity.this, "Removed", Toast.LENGTH_SHORT).show();
//
//            }
//
//            @Override
//            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
//
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//                Toast.makeText(MainActivity.this, "Cancelled", Toast.LENGTH_SHORT).show();
//
//            }
//        });
//         }


    public void onButtonClick() {
        String key = mDatabase.child("UserDetails").push().getKey();
        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put("/UserDetails/"+ key,binding.getUser());
        User user = binding.getUser();
        Log.d(TAG, "onButtonClick: "+user.getName()+"\n"+user.getEmail()+"\n"+user.getPhno());
        mDatabase.updateChildren(childUpdates);
    }


}
