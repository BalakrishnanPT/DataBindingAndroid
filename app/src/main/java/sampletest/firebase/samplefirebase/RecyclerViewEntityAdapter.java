package sampletest.firebase.samplefirebase;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class RecyclerViewEntityAdapter extends RecyclerView.Adapter<RecyclerViewEntityAdapter.BindingHolder> {


    private final static String TAG = "Adapter";
    private List<Entity> mMessageList;
    private MainActivity.ClickListener clickListener;

    public RecyclerViewEntityAdapter(List<Entity> list, MainActivity.ClickListener clickListener) {
        this.mMessageList = list;
        this.clickListener = clickListener;
    }

    void setMessageList(final List<Entity> messageList) {
        mMessageList = messageList;
        notifyDataSetChanged();
    }

    @Override
    public BindingHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_search_result, parent, false);
        BindingHolder holder = new BindingHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(BindingHolder holder, int position) {
        final Entity book = mMessageList.get(position);
        holder.getBinding().setVariable(BR.rv_data, book);
        holder.getBinding().executePendingBindings();
        holder.itemView.setOnLongClickListener(v -> {
            clickListener.onLongpress(mMessageList.get(position).getEmail());
            return false;
        });
    }

    @Override
    public int getItemCount() {
        return mMessageList.size();
    }

    public static class BindingHolder extends RecyclerView.ViewHolder {
        private ViewDataBinding binding;
        private View v;

        public BindingHolder(View v) {
            super(v);
            binding = DataBindingUtil.bind(v);
            this.v = v;
        }

        public ViewDataBinding getBinding() {
            return binding;
        }

        View getView() {
            return v;
        }
    }
}
