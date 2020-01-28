package com.techastrum.myappcreater.fragmentdialog;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import com.techastrum.myappcreater.R;
import com.techastrum.myappcreater.utils.Prefs;

import java.util.Objects;

public class Fragment_dialog extends DialogFragment {
    private Prefs prefs;
    private String key;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        prefs=new Prefs(Objects.requireNonNull(getContext()));


    }

    public Fragment_dialog(String key) {
        this.key = key;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dialog, container, false);
        EditText et_enter_name = view.findViewById(R.id.et_enter_name);
        String Name = et_enter_name.getText().toString();
        et_enter_name.setText(key);

        view.findViewById(R.id.btn_save).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (et_enter_name.getText().toString().isEmpty()) {
                    Toast.makeText(getContext(), "Field is empty!", Toast.LENGTH_LONG).show();

                } else {
                    prefs.SetValue("key",Name);

                    Objects.requireNonNull(getActivity()).onBackPressed();
                }


            }
        });


        return view;
    }

}
