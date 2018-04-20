package sampletest.firebase.samplefirebase;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;

import sampletest.firebase.samplefirebase.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private String TAG = getClass().getSimpleName();
    private DatabaseReference mDatabase;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //      FirebaseDatabase database = FirebaseDatabase.getInstance();
//        mDatabase = FirebaseDatabase.getInstance().getReference();
//        final String userId="New user";
//        mDatabase.child("users").child(userId).addListenerForSingleValueEvent(
//                new ValueEventListener() {
//                    @Override
//                    public void onDataChange(DataSnapshot dataSnapshot) {
//                            String title = "some title";
//                            String body = "Some Text";
//                            writeNewPost(userId, "Some Name", title, body);
//       git                }
//
//                    @Override
//                    public void onCancelled(DatabaseError databaseError) {
//                        Log.w(TAG, "getUser:onCancelled", databaseError.toException());
//                        // [START_EXCLUDE]
//                        // [END_EXCLUDE]
//                    }
//                });
////        DatabaseReference myRef = database.getReference("message");
////        myRef.setValue("Hello, World!");
////
////        myRef.addValueEventListener(new ValueEventListener() {
////            @Override
////            public void onDataChange(DataSnapshot dataSnapshot) {
////                // This method is called once with the initial value and again
////                // whenever data at this location is updated.
////                String value = dataSnapshot.getValue(String.class);
////                Log.d(TAG, "Value is: " + value);
////            }
////
////            @Override
////            public void onCancelled(DatabaseError error) {
////                // Failed to read value
////                Log.w(TAG, "Failed to read value.", error.toException());
////            }
////        });

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        User user = new User();
        user.setName("Learn2Crack");
        binding.setUser(user);
        binding.setActivity(this);
    }


    public void onButtonClick(String email) {

        Log.d(TAG, "Email :" + binding.getUser().getEmail());
        Log.d(TAG, "Email : " + email);
        Toast.makeText(this, email, Toast.LENGTH_SHORT).show();
    }


    private void writeNewPost(String userId, String username, String title, String body) {
////         Create new post at /user-posts/$userid/$postid and at
////         /posts/$postid simultaneously
//        String key = mDatabase.child("posts").push().getKey();
//        Post post = new Post(userId, username, title, body);
//        Map<String, Object> postValues = post.toMap();
//        List list = new ArrayList();
//        list.add(postValues);
//        list.add(postValues);
//
//        Map<String, Object> childUpdates = new HashMap<>();
//        childUpdates.put("/posts/","no where" );
//        childUpdates.put("/user-posts/" + "list test" + "/" + "podcast", list);
//        mDatabase.updateChildren(childUpdates);
    }

    public class Presenter {
        public void onSaveClick() {
            Log.d("Logged", "onSaveClick: clicked");
            Toast.makeText(MainActivity.this, "Success", Toast.LENGTH_SHORT).show();
        }
    }
}
