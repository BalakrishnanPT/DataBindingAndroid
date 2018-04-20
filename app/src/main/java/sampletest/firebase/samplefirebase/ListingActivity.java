package sampletest.firebase.samplefirebase;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import sampletest.firebase.samplefirebase.databinding.ActivityListingBinding;
import sampletest.firebase.samplefirebase.databinding.ActivityMainBinding;

public class ListingActivity extends AppCompatActivity {

ActivityListingBinding binding;
    private RecyclerView recyclerView;
    private List<DummyData> model = new ArrayList<>();
    private RecyclerViewAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_listing);
        model.add(new DummyData("1","name1"));
        model.add(new DummyData("2","name2"));
        model.add(new DummyData("3","name3"));
        model.add(new DummyData("4","name4"));
        adapter= new RecyclerViewAdapter(model);
        binding.rvListing.setAdapter(adapter);
        binding.rvListing.setLayoutManager(new LinearLayoutManager(this));
    }
}
