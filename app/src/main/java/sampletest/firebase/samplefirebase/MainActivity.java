package sampletest.firebase.samplefirebase;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import sampletest.firebase.samplefirebase.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private String TAG = getClass().getSimpleName();
    private DatabaseReference mDatabase;
    private ActivityMainBinding binding;
    private UserModel mUserModel;
    private RecyclerViewEntityAdapter adapter;
    private List<Entity> entities = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mUserModel =  ViewModelProviders.of(this).get(UserModel.class);
        Entity entity = new Entity();
        binding.setEntity(entity);
        binding.setActivity(this);
        adapter = new RecyclerViewEntityAdapter(entities,new ClickListener(){
            @Override
            public void onLongpress(String s) {
                mUserModel.remove(s);

            }
        });
        binding.rvEntity.setAdapter(adapter);
        binding.rvEntity.setLayoutManager(new LinearLayoutManager(this));
        update();

    }
    private void update() {
        // Update the list when the data changes
        if(mUserModel != null){
            LiveData<List<Entity>> liveData = mUserModel.getMessageListLiveData();
            liveData.observe(this, (List<Entity> mEntities) -> {
                adapter.setMessageList(mEntities);
            });

        }
    }
    public void onButtonClick() {
        mUserModel.createAndSendToDataBase(binding.getEntity());
    }
    interface ClickListener{
        void onLongpress(String s);
    }

}
