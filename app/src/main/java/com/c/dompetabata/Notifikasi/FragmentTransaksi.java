package com.c.dompetabata.Notifikasi;

import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.c.dompetabata.CetakStruk.DetailTransaksiTruk;
import com.c.dompetabata.R;

public class FragmentTransaksi extends Fragment {

    private FragmentTransaksiViewModel mViewModel;

    public static FragmentTransaksi newInstance() {
        return new FragmentTransaksi();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_transaksi_fragment, container, false);

        LinearLayout lintesst = v.findViewById(R.id.linearnotifikasi);
        lintesst.setOnClickListener(v1 -> {

            Intent intent = new Intent(getActivity(), DetailNotifikasi.class);
            startActivity(intent);
        });
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(FragmentTransaksiViewModel.class);
        // TODO: Use the ViewModel
    }

}