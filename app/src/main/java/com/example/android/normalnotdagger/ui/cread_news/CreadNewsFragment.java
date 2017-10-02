package com.example.android.normalnotdagger.ui.cread_news;


import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.android.normalnotdagger.R;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.app.Activity.RESULT_OK;

public class CreadNewsFragment extends Fragment implements CreadNewsMVP {


    SharedPreferences user;
    CreadNewsPresentr creadNewsPresentr;
    static final int GALLERY_REQUEST = 1;

    List<Uri> imags = new ArrayList<>();
    List<ImageView> imagsView = new ArrayList<>();

    @BindView(R.id.gallery_imag1)
    ImageView imageView1;
    @BindView(R.id.gallery_imag2)
    ImageView imageView2;
    @BindView(R.id.gallery_imag3)
    ImageView imageView3;
    @BindView(R.id.title)
    EditText title;
    @BindView(R.id.shorts)
    EditText shorts;
    @BindView(R.id.text)
    EditText text;

    @BindView(R.id.cread)
    Button cread;
    @BindView(R.id.addimag)
    FloatingActionButton addImag;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.cread_new, container, false);
        ButterKnife.bind(this, view);
        user = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
        creadNewsPresentr = new CreadNewsPresentr(this, user, this.getActivity());
        addImag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, GALLERY_REQUEST);
            }
        });
        imagsView.add(imageView1);
        imagsView.add(imageView2);
        imagsView.add(imageView3);

        imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (imags.size()){
                    case 1:{
                        imags.remove(0);
                        imageView1.setVisibility(View.INVISIBLE);
                        Log.e("size",imags.size()+"");
                        break;
                    }
                    case 2:{
                        final InputStream imageStream;
                        Log.e("2",imags.get(1)+"");
                        try {
                            imageStream = getActivity().getContentResolver().openInputStream(imags.get(1));
                            final Bitmap selectedImages = BitmapFactory.decodeStream(imageStream);
                            imageView1.setImageBitmap(selectedImages);
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                        imags.remove(0);
                        imageView2.setVisibility(View.INVISIBLE);
                        Log.e("size",imags.size()+"");
                        break;
                    }
                    case 3:{
                        final InputStream imageStream;
                        final InputStream imageStream1;
                        try {
                            imageStream = getActivity().getContentResolver().openInputStream(imags.get(1));
                            final Bitmap selectedImages = BitmapFactory.decodeStream(imageStream);
                            imageView1.setImageBitmap(selectedImages);
                            imageStream1 = getActivity().getContentResolver().openInputStream(imags.get(2));
                            final Bitmap selectedImage1 = BitmapFactory.decodeStream(imageStream1);
                            imageView2.setImageBitmap(selectedImage1);
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                        imags.remove(0);
                        imageView3.setVisibility(View.INVISIBLE);
                        Log.e("size",imags.size()+"");
                        addImag.setVisibility(View.VISIBLE);
                        break;
                    }
                }

            }
        });
        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (imags.size()){
                    case 2:{
                        imags.remove(0);
                        imageView2.setVisibility(View.INVISIBLE);
                        break;
                    }
                    case 3:{
                        final InputStream imageStream;
                        try {
                            imageStream = getActivity().getContentResolver().openInputStream(imags.get(2));
                            final Bitmap selectedImages = BitmapFactory.decodeStream(imageStream);
                            imageView2.setImageBitmap(selectedImages);
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                        imags.remove(2);
                        imageView3.setVisibility(View.INVISIBLE);
                        Log.e("size",imags.size()+"");
                        addImag.setVisibility(View.VISIBLE);
                        break;
                    }
                }

            }
        });
        imageView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imags.remove(2);
                imageView3.setVisibility(View.INVISIBLE);
                addImag.setVisibility(View.VISIBLE);
            }
        });

        cread.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                creadNewsPresentr.loadNew(title.getText().toString(), shorts.getText().toString(), text.getText().toString(), imags);
            }
        });

        return view;
    }

    @Override
    public void showStatus(String status) {

        Toast.makeText(getActivity(), status, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showError(String error) {
        Toast.makeText(getActivity(), error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void startProgresBar() {

    }

    @Override
    public void stopProgresBar() {

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Bitmap bitmap = null;
        switch (requestCode) {
            case GALLERY_REQUEST:
                if (resultCode == RESULT_OK) {
                    Uri selectedImage = data.getData();
                    imags.add(selectedImage);
                    for(int i = 0; i < imags.size(); i++){
                        imagsView.get(i).setVisibility(View.VISIBLE);
                        final InputStream imageStream;
                        try {
                            imageStream = getActivity().getContentResolver().openInputStream(imags.get(i));
                            final Bitmap selectedImages = BitmapFactory.decodeStream(imageStream);
                            imagsView.get(i).setImageBitmap(selectedImages);
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                        if(i == 2){
                            addImag.setVisibility(View.INVISIBLE);
                        }
                    }
                    Log.e("Image", "URL: " + selectedImage);
                }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        //null.unbind();
    }
}
