package com.c.dompetabata.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.c.dompetabata.Model.ModelPost;
import com.c.dompetabata.Model.ModelProvinsi;
import com.c.dompetabata.R;
import com.c.dompetabata.sharePreference.Preference;

import java.util.ArrayList;
import java.util.List;

public class AdapterPost extends RecyclerView.Adapter<AdapterPost.ViewHolder>  {

    private Context context;
    private List<ModelPost> modelPosts;
    private int selectedPosition = 0;
    private ArrayList<Integer> selectCheck = new ArrayList<>();
    public AdapterPost(Context context, List<ModelPost> modelPosts) {
        this.context = context;
        this.modelPosts = modelPosts;


        for (int i = 0; i < modelPosts.size(); i++) {
            selectCheck.add(0);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list, parent, false);
        ViewHolder holder = new ViewHolder(v);
        return holder;

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {


        ModelPost modelPost = modelPosts.get(position);
        holder.name.setText(modelPost.getPostal_code());

        if (selectCheck.get(position) == 1) {
            holder.chekP.setChecked(true);
        } else {
            holder.chekP.setChecked(false);
        }


        holder.chekP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                for(int k=0; k<selectCheck.size(); k++) {
                    if(k==position) {
                        selectCheck.set(k,1);
                    } else {
                        selectCheck.set(k,0);
                    }
                }
                notifyDataSetChanged();
                Preference.getSharedPreference(context);
                Preference.setName(context,modelPost.getPostal_code());
                Preference.setID(context,modelPost.getId());


            }

        });



    }

    @Override
    public int getItemCount() {
        return modelPosts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        CheckBox chekP;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.nameList);
            chekP = itemView.findViewById(R.id.chekProvinsi);

        }
    }


}
