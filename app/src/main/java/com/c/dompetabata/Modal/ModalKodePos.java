package com.c.dompetabata.Modal;

import android.content.Context;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.c.dompetabata.Adapter.AdapterPost;
import com.c.dompetabata.Api.Api;
import com.c.dompetabata.Respon.ResponPost;
import com.c.dompetabata.Helper.RetroClient;
import com.c.dompetabata.Model.ModelPost;
import com.c.dompetabata.R;
import com.c.dompetabata.sharePreference.Preference;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ModalKodePos extends BottomSheetDialogFragment {


    RecyclerView recyclerViewPost;
    AdapterPost adapterPost;
    ArrayList<ModelPost> modelPosts = new ArrayList<>();
    Button tutup, pilih;
    private BottomSheetListenerPost bottomSheetListenerPost;
    SearchView searchView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View v = inflater.inflate(R.layout.modal_layout_post,container,false);


        recyclerViewPost = v.findViewById(R.id.ReyPost);
        tutup = v.findViewById(R.id.tutupPost);
        pilih = v.findViewById(R.id.pilihPost);
        searchView = v.findViewById(R.id.search_kelurahan);


        adapterPost = new AdapterPost(getContext(), modelPosts);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerViewPost.setLayoutManager(mLayoutManager);
        recyclerViewPost.setAdapter(adapterPost);
        getPost();
        tutup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        pilih.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String id =  Preference.getID(getContext());
                String name = Preference.getName(getContext());

                bottomSheetListenerPost.onButtonClickPost(name, id);
                dismiss();
            }
        });



        return v;
    }

    private void getPost() {

        long id = Long.valueOf(Preference.getIDKelurahan(getContext()));

        Api api = RetroClient.getApiServices();
        Call<ResponPost> call = api.getAllPost(id);
        call.enqueue(new Callback<ResponPost>() {
            @Override
            public void onResponse(Call<ResponPost> call, Response<ResponPost> response) {

                modelPosts = (ArrayList<ModelPost>) response.body().getData();
                adapterPost = new AdapterPost(getContext(), modelPosts);
                recyclerViewPost.setAdapter(adapterPost);
            }

            @Override
            public void onFailure(Call<ResponPost> call, Throwable t) {

            }


        });

    }

    public interface BottomSheetListenerPost {
        void onButtonClickPost(String postalcode, String id);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            bottomSheetListenerPost = (BottomSheetListenerPost) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + "must implement bottomsheet Listener");
        }
    }
}
