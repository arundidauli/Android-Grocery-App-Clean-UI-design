package com.techastrum.myappcreater.activities;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.Settings;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.techastrum.myappcreater.Interface.Onholderitemclick;
import com.techastrum.myappcreater.R;
import com.techastrum.myappcreater.adapter.ProductAdapter1;
import com.techastrum.myappcreater.model.Product;
import com.techastrum.myappcreater.utils.Prefs;
import com.techastrum.myappcreater.utils.Utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.techastrum.myappcreater.activities.CreateNew.REQUEST_IMAGE;


public class ManageActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private static String TAG=ManageActivity.class.getSimpleName();
    private List<Product> productList;
    private Context context;
    private Intent intent;
    private static final int REQUEST_IMAGE = 100;
    private ImageView image_profile;
    private String image="";
    private Prefs prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage);
        context = ManageActivity.this;
        ButterKnife.bind(this);
        intent = getIntent();
        prefs=new Prefs(context);
        Spinner category = findViewById(R.id.spinner_category);
        Spinner subcategory = findViewById(R.id.spinner_subcategory);
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
        EditText service_name = findViewById(R.id.service_name);
        EditText price = findViewById(R.id.price);
        EditText detail = findViewById(R.id.detail);
        image_profile = findViewById(R.id.image_logo);
        ImageView image_plus = findViewById(R.id.image_picker);

        image_plus.setOnClickListener(v -> onProfileImageClick());
        image_profile.setOnClickListener(v -> onProfileImageClick());


        findViewById(R.id.txt_btn_save).setOnClickListener(v -> {
            if (service_name.getText().toString().equals("")&& price.getText().toString().equals("")&& detail.getText().toString().equals("")){
                Toast.makeText(getApplicationContext(),"Please Fill All Field",Toast.LENGTH_LONG).show();

            }else {
                // productList.add(new Product(service_name.getText().toString(),prefs.GetValue("option"),prefs.GetValue("option"),price.getText().toString(),detail.getText().toString(),image));
                Intent intent=new Intent(context, HomeProfile.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                intent.putExtra("title",service_name.getText().toString());
                intent.putExtra("price",price.getText().toString());
                intent.putExtra("detail",detail.getText().toString());
                intent.putExtra("category",prefs.GetValue("category"));
                intent.putExtra("subcategory",prefs.GetValue("subcategory"));
                intent.putExtra("image",image);
                startActivity(intent);
            }

        });


    }


    /***
     * RecyclerView set up method called
     */
  /*  private void setRecyclerView() {
        productList = new ArrayList<>();
        recyclerView = findViewById(R.id.rv_service);
      //  productAdapter = new ProductAdapter1(productList, context, this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
       // recyclerView.setAdapter(productAdapter);
        if (intent.getStringExtra("title") != null && intent.getStringExtra("image") != null && intent.getStringExtra("price") != null && intent.getStringExtra("detail") != null) {
            productList.add(new Product(intent.getStringExtra("title"), intent.getStringExtra("category"), intent.getStringExtra("subcategory"), "Rs. " + intent.getStringExtra("price"), intent.getStringExtra("detail"), intent.getStringExtra("image")));
            productList.add(new Product(intent.getStringExtra("title"), intent.getStringExtra("category"), intent.getStringExtra("subcategory"), "Rs. " + intent.getStringExtra("price"), intent.getStringExtra("detail"), intent.getStringExtra("image")));

        }

    }*/



    /**
     *
     * @param button
     * Custom Menu popup dialog in setting
     * or toolbar
     */
    public void onPopupButtonClick(View button) {
        PopupMenu popup = new PopupMenu(this, button);
        popup.getMenuInflater().inflate(R.menu.menu_scrolling, popup.getMenu());
        popup.setOnMenuItemClickListener(item -> {
            int id = item.getItemId();
            switch (id) {
                case R.id.enquiry:
                    Toast.makeText(getApplicationContext(), "Item 1 Selected", Toast.LENGTH_LONG).show();
                    return true;
                case R.id.download_app:
                    Toast.makeText(getApplicationContext(), "Item 2 Selected", Toast.LENGTH_LONG).show();
                    return true;
                case R.id.publish_app:
                    Toast.makeText(getApplicationContext(), "Item 3 Selected", Toast.LENGTH_LONG).show();
                    return true;
            }
            return true;
        });
        popup.show();
    }


    /**
     * Replace fragment method to replace fragment any
     * by calling method and pass fragment
     * @param fragment
     */

    public void replace_Fragment(Fragment fragment) {

        String backStateName = fragment.getClass().getName();
        FragmentManager manager = getSupportFragmentManager();
        boolean fragmentPopped = manager.popBackStackImmediate(backStateName, 0);

        if (!fragmentPopped && manager.findFragmentByTag(backStateName) == null) {
            FragmentTransaction ft = manager.beginTransaction();
            ft.replace(R.id.container, fragment, backStateName);
            ft.addToBackStack(backStateName);
            ft.commit();
        }
    }

    /***
     * Window dialog in alert dialog
     * with custom layout parameter
     * @param position
     */
    private void show_dialog(int position) {
        final Dialog dialog = new Dialog(context, R.style.WideDialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); //before
        dialog.setCancelable(true);
        Rect displayRectangle = new Rect();
        Window window = dialog.getWindow();
        assert window != null;
        window.getDecorView().getWindowVisibleDisplayFrame(displayRectangle);
        window.setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        // View layout = getLayoutInflater().inflate(R.layout.fragment_dialog, null);
        // View.inflate(context, R.layout.fragment_dialog, null);
        dialog.setContentView(View.inflate(context, R.layout.fragment_dialog, null));
        Objects.requireNonNull(dialog.getWindow()).getAttributes().windowAnimations = R.style.PopupAnimation;
        EditText et_name = dialog.findViewById(R.id.et_enter_name);
        EditText et_price = dialog.findViewById(R.id.et_price);
        EditText et_details = dialog.findViewById(R.id.et_detail);
        Button btn_save = dialog.findViewById(R.id.btn_save);

        et_name.setText(productList.get(position).getName());
        et_price.setText(productList.get(position).getPrice());
        et_details.setText(productList.get(position).getDetail());


        btn_save.setOnClickListener(v -> {
            if (et_name.getText().toString().isEmpty()) {
                Toast.makeText(getApplicationContext(), "Please enter something", Toast.LENGTH_LONG).show();
            } else {
                productList.get(position).setName(et_name.getText().toString());
                productList.get(position).setPrice(et_price.getText().toString());
                productList.get(position).setDetail(et_details.getText().toString());
               // productAdapter.notifyDataSetChanged();
                dialog.dismiss();
            }


        });

        dialog.show();
    }

    private void SpinnerMethod(ArrayList<String> arrayList, Spinner spinner,String flag) {

        ArrayAdapter<String> adapter = new ArrayAdapter<>(Objects.requireNonNull(context),
                android.R.layout.simple_selectable_list_item, arrayList);
        adapter.setDropDownViewResource(android.R.layout.simple_selectable_list_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
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
        Dexter.withActivity(this)
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
        ImagePickerActivity.showImagePickerOptions(context, new ImagePickerActivity.PickerOptionListener() {
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
        Intent intent = new Intent(context, ImagePickerActivity.class);
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
        Intent intent = new Intent(context, ImagePickerActivity.class);
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
        image_profile.setColorFilter(ContextCompat.getColor(Objects.requireNonNull(context), android.R.color.transparent));
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
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(Objects.requireNonNull(context).getContentResolver(), uri);
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
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
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
        Uri uri = Uri.fromParts("package", Objects.requireNonNull(context).getPackageName(), null);
        intent.setData(uri);
        startActivityForResult(intent, 101);
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


}
