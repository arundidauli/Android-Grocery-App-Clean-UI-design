package com.techastrum.myappcreater.fragment;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.techastrum.myappcreater.R;
import com.techastrum.myappcreater.activities.CreateNew;
import com.techastrum.myappcreater.activities.ImagePickerActivity;
import com.techastrum.myappcreater.utils.Prefs;
import com.techastrum.myappcreater.utils.Utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AddFragment extends Fragment {
    private static final int REQUEST_IMAGE = 100;
    private static final String TAG = CreateNew.class.getSimpleName();
    private Prefs prefs;
    private ImageView image_profile;
    private String image="";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        prefs = new Prefs(Objects.requireNonNull(getContext()));
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.add_fragment, container, false);
        Spinner category = view.findViewById(R.id.spinner_category);
        Spinner subcategory = view.findViewById(R.id.spinner_subcategory);
        ArrayList<String> arrayList = new ArrayList<>();
        ArrayList<String> sub_category = new ArrayList<>();
        arrayList.add("Category Name");
        arrayList.add("Category Name1");
        arrayList.add("Category Name2");
        arrayList.add("Category Name3");
        arrayList.add("Category Name4");
        sub_category.add("Sub Category");
        sub_category.add("Sub Category");
        sub_category.add("Sub Category");
        sub_category.add("Sub Category");
        sub_category.add("Sub Category");

        SpinnerMethod(arrayList, category,"category");
        SpinnerMethod(sub_category, subcategory,"subcategory");
        EditText service_name = view.findViewById(R.id.service_name);
        EditText price = view.findViewById(R.id.price);
        EditText detail = view.findViewById(R.id.detail);
        image_profile = view.findViewById(R.id.image_logo);
        ImageView image_plus = view.findViewById(R.id.image_picker);

        image_plus.setOnClickListener(v -> onProfileImageClick());
        image_profile.setOnClickListener(v -> onProfileImageClick());


        view.findViewById(R.id.txt_btn_save).setOnClickListener(v -> {
            if (service_name.getText().toString().equals("")&& price.getText().toString().equals("")&& detail.getText().toString().equals("")){
                Toast.makeText(getActivity(),"Please Fill All Field",Toast.LENGTH_LONG).show();

            }else {
               // productList.add(new Product(service_name.getText().toString(),prefs.GetValue("option"),prefs.GetValue("option"),price.getText().toString(),detail.getText().toString(),image));
                Intent intent=new Intent(getActivity(),CreateNew.class);
                intent.putExtra("title",service_name.getText().toString());
                intent.putExtra("price",price.getText().toString());
                intent.putExtra("detail",detail.getText().toString());
                intent.putExtra("category",prefs.GetValue("category"));
                intent.putExtra("subcategory",prefs.GetValue("subcategory"));
                intent.putExtra("image",image);
                startActivity(intent);
            }

        });


        return view;
    }

    private void SpinnerMethod(ArrayList<String> arrayList, Spinner spinner,String flag) {

        ArrayAdapter<String> adapter = new ArrayAdapter<>(Objects.requireNonNull(getActivity()),
                android.R.layout.simple_selectable_list_item, arrayList);
        adapter.setDropDownViewResource(android.R.layout.simple_selectable_list_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) getActivity());
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.v("item", (String) parent.getItemAtPosition(position));
                prefs.SetValue(flag, (String) parent.getItemAtPosition(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });

    }

    private void onProfileImageClick() {
        Dexter.withActivity(getActivity())
                .withPermissions(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport report) {
                        if (report.areAllPermissionsGranted()) {
                            showImagePickerOptions();
                        }

                        if (report.isAnyPermissionPermanentlyDenied()) {
                            showSettingsDialog();
                        }
                    }
                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
                        token.continuePermissionRequest();
                    }
                }).check();
    }

    private void showImagePickerOptions() {
        ImagePickerActivity.showImagePickerOptions(getActivity(), new ImagePickerActivity.PickerOptionListener() {
            @Override
            public void onTakeCameraSelected() {
                launchCameraIntent();
            }

            @Override
            public void onChooseGallerySelected() {
                launchGalleryIntent();
            }
        });
    }

    private void launchCameraIntent() {
        Intent intent = new Intent(getActivity(), ImagePickerActivity.class);
        intent.putExtra(ImagePickerActivity.INTENT_IMAGE_PICKER_OPTION, ImagePickerActivity.REQUEST_IMAGE_CAPTURE);

        // setting aspect ratio
        intent.putExtra(ImagePickerActivity.INTENT_LOCK_ASPECT_RATIO, true);
        intent.putExtra(ImagePickerActivity.INTENT_ASPECT_RATIO_X, 1); // 16x9, 1x1, 3:4, 3:2
        intent.putExtra(ImagePickerActivity.INTENT_ASPECT_RATIO_Y, 1);

        // setting maximum bitmap width and height
        intent.putExtra(ImagePickerActivity.INTENT_SET_BITMAP_MAX_WIDTH_HEIGHT, true);
        intent.putExtra(ImagePickerActivity.INTENT_BITMAP_MAX_WIDTH, 1000);
        intent.putExtra(ImagePickerActivity.INTENT_BITMAP_MAX_HEIGHT, 1000);

        startActivityForResult(intent, REQUEST_IMAGE);
    }

    private void launchGalleryIntent() {
        Intent intent = new Intent(getActivity(), ImagePickerActivity.class);
        intent.putExtra(ImagePickerActivity.INTENT_IMAGE_PICKER_OPTION, ImagePickerActivity.REQUEST_GALLERY_IMAGE);

        // setting aspect ratio
        intent.putExtra(ImagePickerActivity.INTENT_LOCK_ASPECT_RATIO, true);
        intent.putExtra(ImagePickerActivity.INTENT_ASPECT_RATIO_X, 1); // 16x9, 1x1, 3:4, 3:2
        intent.putExtra(ImagePickerActivity.INTENT_ASPECT_RATIO_Y, 1);
        startActivityForResult(intent, REQUEST_IMAGE);
    }

    private void loadProfile(String url) {
        Log.d(TAG, "Image cache path: " + url);

        Glide.with(this).load(url)
                .into(image_profile);
        image_profile.setColorFilter(ContextCompat.getColor(Objects.requireNonNull(getActivity()), android.R.color.transparent));
    }

   /* private void loadProfileDefault() {
        Glide.with(this).load(R.drawable.ic_person_black_24dp)
                .into(image_profile);
        image_profile.setColorFilter(ContextCompat.getColor(Objects.requireNonNull(getActivity()), R.color.colorAccent));
    }*/

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE) {
            if (resultCode == Activity.RESULT_OK) {
                assert data != null;
                Uri uri = data.getParcelableExtra("path");
                try {
                    // You can update this bitmap to your server
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(Objects.requireNonNull(getActivity()).getContentResolver(), uri);
                    image = Utils.BitMapToString(bitmap);

                    // loading profile image from local cache
                    assert uri != null;
                    loadProfile(uri.toString());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Showing Alert Dialog with Settings option
     * Navigates user to app settings
     * NOTE: Keep proper title and message depending on your app
     */
    private void showSettingsDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(getString(R.string.dialog_permission_title));
        builder.setMessage(getString(R.string.dialog_permission_message));
        builder.setPositiveButton(getString(R.string.go_to_settings), (dialog, which) -> {
            dialog.cancel();
            openSettings();
        });
        builder.setNegativeButton(getString(android.R.string.cancel), (dialog, which) -> dialog.cancel());
        builder.show();

    }

    // navigating user to app settings
    private void openSettings() {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package", Objects.requireNonNull(getActivity()).getPackageName(), null);
        intent.setData(uri);
        startActivityForResult(intent, 101);
    }


}
