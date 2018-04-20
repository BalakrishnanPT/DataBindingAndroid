package sampletest.firebase.samplefirebase;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.BindingHolder> {
    List<DummyData> list = new ArrayList<>();

    public RecyclerViewAdapter(List<DummyData> list) {
        this.list = list;
    }

    @Override
    public BindingHolder onCreateViewHolder(ViewGroup parent, int viewType) {
          View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_search_result, parent, false);
          BindingHolder holder = new BindingHolder(v);
          return holder;
    }

    @Override
    public void onBindViewHolder(BindingHolder holder, int position) {
        final DummyData book = list.get(position);
        holder.getBinding().setVariable(BR.rv_data, book);
        holder.getBinding().executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class BindingHolder extends RecyclerView.ViewHolder {
        private ViewDataBinding binding;

        public BindingHolder(View v) {
            super(v);
            binding = DataBindingUtil.bind(v);
        }

        public ViewDataBinding getBinding() {
            return binding;
        }
    }
}
