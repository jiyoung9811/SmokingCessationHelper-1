package com.example.smokingcessationhelper;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class MainFragment extends Fragment {
    final String noSmokingStr[] = {
            "금연을 위한 글 1",
            "금연을 위한 글 2",
            "금연을 위한 글 3",
            "금연을 위한 글 4",
            "금연을 위한 글 5",
    };

    public MainFragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        ImageView ivSelectedImage = (ImageView) view.findViewById(R.id.MainFragment_ivSelectedImage);
        TextView tvBlackBox = (TextView) view.findViewById(R.id.MainFragment_tvBlackBox);
        TextView tvNoSmokingStr = (TextView) view.findViewById(R.id.MainFragment_tvNoSmokingString);
        Random rand = new Random();
        int alpha = 90; // 투명도

        //이미지 설정
        ivSelectedImage.setImageResource(R.drawable.no_smoking);

        //투명도 조절
        tvBlackBox.setBackgroundColor(Color.argb(alpha, 0, 0, 0));

        tvNoSmokingStr.setText(noSmokingStr[rand.nextInt(5)]);

        return view;
    }

}