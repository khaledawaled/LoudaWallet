package com.example.android.ewallet;


import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import org.json.JSONException;
import org.json.JSONObject;

public class GenerationFragment extends Fragment {


    private static final String TAG = "UserMain";
    private EditText mContent;
    private ImageView qrImage;
    Button genBtn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_generation,container,false);
        return view;
    }

    public void onViewCreated (View view, Bundle savedInstanceState) {

        mContent = view.findViewById(R.id.editText);
        qrImage =  view.findViewById(R.id.imageView);
        genBtn = view.findViewById(R.id.gen);
    }
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        genBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = mContent.getText().toString();
                generateQrCode(content);
            }
        });
    }

    protected void generateQrCode(String content) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("name", content);
            jsonObject.put("data", "today");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
        try {
            BitMatrix bitMatrix = multiFormatWriter.encode(jsonObject.toString(), BarcodeFormat.QR_CODE, 400, 400);
            BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
            Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
            qrImage.setImageBitmap(bitmap);
        } catch (WriterException e) {
            e.printStackTrace();
        }
    }
}
