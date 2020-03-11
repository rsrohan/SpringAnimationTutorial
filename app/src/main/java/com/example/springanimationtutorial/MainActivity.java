package com.example.springanimationtutorial;

import androidx.appcompat.app.AppCompatActivity;
import androidx.dynamicanimation.animation.DynamicAnimation;
import androidx.dynamicanimation.animation.SpringAnimation;
import androidx.dynamicanimation.animation.SpringForce;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import static androidx.dynamicanimation.animation.SpringForce.DAMPING_RATIO_MEDIUM_BOUNCY;
import static androidx.dynamicanimation.animation.SpringForce.STIFFNESS_LOW;
import static androidx.dynamicanimation.animation.SpringForce.STIFFNESS_MEDIUM;

public class MainActivity extends AppCompatActivity {

    Button tapMe;
    Button smallBtns[] = new Button[4];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tapMe=findViewById(R.id.showAnimationButton);
        smallBtns[0] = findViewById(R.id.btn1);
        smallBtns[1] = findViewById(R.id.btn2);
        smallBtns[2] = findViewById(R.id.btn3);
        smallBtns[3] = findViewById(R.id.btn4);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        float width = displayMetrics.widthPixels;
        final float customWidth = (width / 2.0f) - (0.1041f * width);
        final float veloDp = 0f;

        for (int i=0;i<4;i++)
        {
            smallBtns[i].animate().scaleX(0f).scaleY(0f);
        }

        //setting bounce when tapped.
        for (int i=0;i<4;i++)
        {
            final int finalI = i;
            smallBtns[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getHighBounceScaleX(smallBtns[finalI], 6f, 1f, DAMPING_RATIO_MEDIUM_BOUNCY, STIFFNESS_MEDIUM).start();
                    getHighBounceScaleY(smallBtns[finalI], 6f, 1f, DAMPING_RATIO_MEDIUM_BOUNCY, STIFFNESS_MEDIUM).start();
                }
            });
        }

        tapMe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getHighBounceScaleX(tapMe, 6f, 1f, DAMPING_RATIO_MEDIUM_BOUNCY, STIFFNESS_MEDIUM).start();
                getHighBounceScaleY(tapMe, 6f, 1f, DAMPING_RATIO_MEDIUM_BOUNCY, STIFFNESS_MEDIUM).start();
                if (smallBtns[0].getScaleX()==0f)
                {
                    for (int i=0;i<4;i++)
                    {
                        smallBtns[i].animate().scaleX(1f).scaleY(1f).setDuration(150);
                    }
                    getHighBounceAnimX(smallBtns[0], veloDp, customWidth).start();
                    getHighBounceAnimY(smallBtns[1], veloDp, -customWidth).start();
                    getHighBounceAnimX(smallBtns[2], veloDp, -customWidth).start();
                    getHighBounceAnimY(smallBtns[3], veloDp, customWidth).start();
                }else{
                    for (int i=0;i<4;i++)
                    {
                        smallBtns[i].animate().scaleX(0f).scaleY(0f);
                    }
                    getHighBounceAnimX(smallBtns[0], veloDp, 0).start();
                    getHighBounceAnimY(smallBtns[1], veloDp, 0).start();
                    getHighBounceAnimX(smallBtns[2], veloDp, 0).start();
                    getHighBounceAnimY(smallBtns[3], veloDp, 0).start();
                }


            }
        });
    }

    private SpringForce getSpringForce(float dampingRatio, float stiffness, float finalPosition) {
        SpringForce force = new SpringForce();
        force.setDampingRatio(dampingRatio).setStiffness(stiffness);
        force.setFinalPosition(finalPosition);
        return force;
    }



    private float getVelocity(float velocityDp) {
        //Get Velocity in pixels per second from dp per second
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, velocityDp,
                getResources().getDisplayMetrics());
    }



    private SpringAnimation getHighBounceAnimX(TextView view, float velocityDp, float finalPosition) {
        final SpringAnimation anim = new SpringAnimation(view, DynamicAnimation.TRANSLATION_X);
        anim.setStartVelocity(getVelocity(velocityDp));
        anim.animateToFinalPosition(finalPosition);
        anim.setSpring(getSpringForce(DAMPING_RATIO_MEDIUM_BOUNCY, STIFFNESS_LOW, finalPosition));
        return anim;
    }




    private SpringAnimation getHighBounceAnimY(TextView view, float velocityDp, float finalPosition) {
        final SpringAnimation anim = new SpringAnimation(view, DynamicAnimation.TRANSLATION_Y);
        anim.setStartVelocity(getVelocity(velocityDp));
        anim.animateToFinalPosition(finalPosition);
        anim.setSpring(getSpringForce(DAMPING_RATIO_MEDIUM_BOUNCY, STIFFNESS_LOW, finalPosition));
        return anim;
    }




    private SpringAnimation getHighBounceScaleX(View view, float velocityDp, float finalPosition, float DAMPING, float STIFFNESS) {
        final SpringAnimation anim = new SpringAnimation(view, DynamicAnimation.SCALE_X);
        anim.setStartVelocity(getVelocity(velocityDp));
        anim.animateToFinalPosition(finalPosition);
        anim.setSpring(getSpringForce(DAMPING, STIFFNESS, finalPosition));
        return anim;
    }





    private SpringAnimation getHighBounceScaleY(View view, float velocityDp, float finalPosition, float DAMPING, float STIFFNESS) {
        final SpringAnimation anim = new SpringAnimation(view, DynamicAnimation.SCALE_Y);
        anim.setStartVelocity(getVelocity(velocityDp));
        anim.animateToFinalPosition(finalPosition);
        anim.setSpring(getSpringForce(DAMPING, STIFFNESS, finalPosition));
        return anim;
    }
}
