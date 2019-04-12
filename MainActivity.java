package com.example.nitin.nini;

import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private Stage stage[];
    private List<String> images ;
    private static final String TAG = "abc";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_main);


//
//        DisplayMetrics metrics = this.getResources().getDisplayMetrics();
//        int width = metrics.widthPixels;
//        int height = metrics.heightPixels;
//        Log.d(TAG, "onCreate: "+width);
//        Log.d(TAG, "onCreate: "+height);

        int pixel_w=this.getWindowManager().getDefaultDisplay().getWidth();
        Log.d(TAG, "onCreate: "+pixel_w);
    //    int dp_width =pixel_w/(int)getResources().getDisplayMetrics().density ;

        int pixel_h=this.getWindowManager().getDefaultDisplay().getHeight();
        Log.d(TAG, "onCreate: "+pixel_h);
      //  int dp_height=pixel_h/(int)getResources().getDisplayMetrics().density ;



        images = new ArrayList<>();
        images.add("goku_ui");
        images.add("dbz1");
        images.add("dbz1");
        images.add("dbz1");

        int n = images.size();
        stage = new Stage[n];



        int img_width = pixel_w/3;
        int img_height = 400;
        Log.d(TAG, "onCreate: "+(int)Math.ceil(n/3));

        GridLayout gl = new GridLayout(this);
        gl.setColumnCount(3);
        LinearLayout.LayoutParams stage_details = new LinearLayout.LayoutParams(
                img_width,img_height
        );


        for (int i = 0; i <n ; i++) {

            LinearLayout ll = new LinearLayout(this);
            ll.setId(i);
            stage[i] = new Stage(this);
            stage[i].setImage(images.get(i));

            ll.addView(stage[i]);

            gl.addView(ll,stage_details);

        }

        setContentView(gl);

    }
}
