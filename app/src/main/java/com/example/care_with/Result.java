package com.example.care_with;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Result extends AppCompatActivity {
    TextView scoreTv, resultTv;
    ImageView emotion;
    Button reBtn, guideBtn, hospital;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        setTitle("진단 결과");

        scoreTv=findViewById(R.id.scoreTv);
        resultTv=findViewById(R.id.resultTv);
        emotion=findViewById(R.id.emotion);
        reBtn=findViewById(R.id.reBtn);
        guideBtn=findViewById(R.id.guideBtn);
        hospital=findViewById(R.id.hospital);

        Intent intent=getIntent();
        int score=intent.getIntExtra("Score", 0);

        scoreTv.setText(score+"점");

        if(5<score && score<11){
            emotion.setImageResource(R.drawable.wow);
            resultTv.setText("주의수준에 해당합니다.\n" +
                    "우울증을 의심해 볼 수 있으므로\n 약간의 주의가 필요합니다.");
        }
        else if(10<score && score<16){
            emotion.setImageResource(R.drawable.sad);
            resultTv.setText("경고수준에 해당합니다\n"+
                    " 우울증이 있을 가능성이 높아\n의료인에게 상담받는 것을 권고합니다.");
        }

        reBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentHome = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intentHome);
            }
        });

        guideBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new PreferenceManager(getApplicationContext()).clearPreference();
                Intent intentGuide = new Intent(getApplicationContext(), GuideActivity.class);
                startActivity(intentGuide);
                finish();
            }
        });
        hospital.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new PreferenceManager(getApplicationContext()).clearPreference();
                Intent intentGuide = new Intent(getApplicationContext(), GuideActivity.class);
                startActivity(intentGuide);
                finish();
            }
        });


    }
}