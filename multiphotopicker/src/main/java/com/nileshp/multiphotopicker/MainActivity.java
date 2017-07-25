package com.nileshp.multiphotopicker;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.nileshp.multiphotopicker.photopicker.activity.PickImageActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private TextView tvResult;
    private static final int READ_STORAGE_CODE = 1001;
    private static final int WRITE_STORAGE_CODE = 1002;
    private ArrayList<String> pathList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnPickImage = (Button) findViewById(R.id.btnPickImage);
        tvResult = (TextView) findViewById(R.id.tvResult);
        btnPickImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openImagePickerIntent();
            }
        });
    }

    private void openImagePickerIntent() {

        if (isPermissionGranted(Manifest.permission.READ_EXTERNAL_STORAGE)) {
            Intent mIntent = new Intent(this, PickImageActivity.class);
            mIntent.putExtra(PickImageActivity.KEY_LIMIT_MAX_IMAGE, 60);
            mIntent.putExtra(PickImageActivity.KEY_LIMIT_MIN_IMAGE, 3);
            startActivityForResult(mIntent, PickImageActivity.PICKER_REQUEST_CODE);
        } else {
            requestPermission(Manifest.permission.READ_EXTERNAL_STORAGE, READ_STORAGE_CODE);
        }

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if (resultCode != RESULT_OK) {
            return;
        }
        if (resultCode == -1 && requestCode == PickImageActivity.PICKER_REQUEST_CODE) {
            this.pathList = intent.getExtras().getStringArrayList(PickImageActivity.KEY_DATA_RESULT);
            if (this.pathList != null && !this.pathList.isEmpty()) {
                StringBuilder sb=new StringBuilder("");
                for(int i=0;i<pathList.size();i++) {
                    sb.append("Photo"+(i+1)+":"+pathList.get(i));
                    sb.append("\n");
                }
                tvResult.setText(sb.toString());
            }
        }
    }

    private boolean isPermissionGranted(String permission) {
        //Getting the permission status
        int result = ContextCompat.checkSelfPermission(this, permission);

        //If permission is granted returning true
        if (result == PackageManager.PERMISSION_GRANTED)
            return true;

        //If permission is not granted returning false
        return false;
    }


    //Requesting permission
    private void requestPermission(String permission, int code) {

        if (ActivityCompat.shouldShowRequestPermissionRationale(this, permission)) {
            //If the user has denied the permission previously your code will come to this block
            //Here you can explain why you need this permission
            //Explain here why you need this permission
        }
        //And finally ask for the permission
        ActivityCompat.requestPermissions(this, new String[]{permission}, code);
    }

    //This method will be called when the user will tap on allow or deny
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        //Checking the request code of our request
        if (requestCode == READ_STORAGE_CODE) {

            //If permission is granted
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                openImagePickerIntent();

            } else {

                MainActivity.this.finish();
            }
        } else if (requestCode == WRITE_STORAGE_CODE) {


            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

            } else {

                MainActivity.this.finish();
            }
        }
    }
}
