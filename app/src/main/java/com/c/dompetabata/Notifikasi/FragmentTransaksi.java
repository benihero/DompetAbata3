package com.c.dompetabata.Notifikasi;

import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.c.dompetabata.Api.Api;
import com.c.dompetabata.CetakStruk.DetailTransaksiTruk;
import com.c.dompetabata.Fragment.RiwayatTransaksi.AdapterFragmentSaldoServer;
import com.c.dompetabata.Fragment.RiwayatTransaksi.ResponTransaksi;
import com.c.dompetabata.Helper.RetroClient;
import com.c.dompetabata.R;
import com.c.dompetabata.sharePreference.Preference;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentTransaksi extends Fragment {

    private FragmentTransaksiViewModel mViewModel;
    AdapterFragmentTransaksi adapterFragmentTransaksi;
    ArrayList<ResponTransaksiN.DataTransaksi> mData = new ArrayList<>();


    public static FragmentTransaksi newInstance() {
        return new FragmentTransaksi();
    }

    RecyclerView recyclerView;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_transaksi_fragment, container, false);

        recyclerView = v.findViewById(R.id.reyTransaksiNotifikasi);

        adapterFragmentTransaksi = new AdapterFragmentTransaksi(getContext(), mData);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(adapterFragmentTransaksi);

//        LinearLayout lintesst = v.findViewById(R.id.linearnotifikasi);
//        lintesst.setOnClickListener(v1 -> {
//            Intent intent = new Intent(getActivity(), DetailNotifikasi.class);
//            startActivity(intent);
//        });
        getDataa();
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(FragmentTransaksiViewModel.class);
        // TODO: Use the ViewModel
    }

    public void getDataa(){

        String token = "Bearer " + Preference.getToken(getContext());
        Api api = RetroClient.getApiServices();
        Call<ResponTransaksiN> call = api.getHistoriTransaksiN(token);
        call.enqueue(new Callback<ResponTransaksiN>() {
            @Override
            public void onResponse(Call<ResponTransaksiN> call, Response<ResponTransaksiN> response) {
                String code = response.body().getCode();
                if(code.equals("200")){
                    mData = response.body().getData();
                    adapterFragmentTransaksi = new AdapterFragmentTransaksi(getContext(),mData);
                    recyclerView.setAdapter(adapterFragmentTransaksi);
                }

            }
            @Override
            public void onFailure(Call<ResponTransaksiN> call, Throwable t) {

            }
        });

    }



}