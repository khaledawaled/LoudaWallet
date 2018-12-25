package com.example.android.ewallet;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.json.JSONException;
import org.json.JSONObject;

import at.markushi.ui.CircleButton;
import de.hdodenhof.circleimageview.CircleImageView;

public class ScanFragment extends Fragment {

    private TextView  nameResult,dateResult ;
    private CircleButton scanBtn;
    private IntentIntegrator integrator;
    private static final String TAG = "UserMain";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view1 = inflater.inflate(R.layout.fragment_scan,container,false);
        return view1;
    }

    @Override
    public void onViewCreated (View view, Bundle savedInstanceState) {

        dateResult =  view.findViewById(R.id.textView2);
        nameResult =  view.findViewById(R.id.textView);
        scanBtn = view.findViewById(R.id.scan_btn);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        scanBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                scanQrCode();
            }
        });
    }

    protected void scanQrCode() {
        integrator = IntentIntegrator.forSupportFragment(ScanFragment.this);
        integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
        integrator.setPrompt("Scan a barcode");
        integrator.setCameraId(0);  // Use a specific camera of the device
        integrator.setBeepEnabled(false);
        integrator.initiateScan();

    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode,resultCode,data);
        if(result != null) {
            String name,date;
            try {
                JSONObject jsonObject = new JSONObject(result.getContents());
                name = jsonObject.getString("name");
                date = jsonObject.getString("data");
                Log.d("loudaaa", "onActivityResult: " + name + "    " + date);
                nameResult.setText(name);
                dateResult.setText(date);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            //String myData = result.getContents();

            //Log.d(TAG, "onActivityResult: " + myData );
        }
        else {
            Log.d(TAG, "onActivityResult: + something wrong happened");
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        Toast.makeText(getActivity(), "Scan Fragment Started", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResume() {
        super.onResume();
        Toast.makeText(getActivity(), "Scan Fragment Resumed", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onStop() {
        super.onStop();
        Toast.makeText(getActivity(), "Scan Fragment Stopped", Toast.LENGTH_SHORT).show();
    }
}
