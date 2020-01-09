package com.techastrum.myappcreater.fragmentdialog;

import android.content.ContextWrapper;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.techastrum.myappcreater.R;


import java.util.Objects;

public class PayInfo extends BottomSheetDialogFragment {
    //Button
    public PayInfo() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.payment_info, container, false);
        EditText name = view.findViewById(R.id.name);
        EditText email = view.findViewById(R.id.email);
        EditText mobno = view.findViewById(R.id.mobileno);
        EditText productName = view.findViewById(R.id.productname);
        EditText amount = view.findViewById(R.id.amount);




        return view;
    }
}
