package com.hgosft.testamapfragment;

import android.graphics.PixelFormat;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.hgosft.testamapfragment.Fragment.BaseFragment;
import com.hgosft.testamapfragment.Fragment.BlankFragment;
import com.hgosft.testamapfragment.Fragment.BlankFragment2;
import com.hgosft.testamapfragment.Fragment.BlankFragment3;
import com.hgosft.testamapfragment.Fragment.MapFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity implements BlankFragment.OnFragmentInteractionListener,
        BlankFragment2.OnFragmentInteractionListener, BlankFragment3.OnFragmentInteractionListener {

    private List<BaseFragment> list;//装载对应的页面
    private FragmentManager fm;
    private Fragment mCurFragment;
    FrameLayout frameLayout;
    FrameLayout mapFrameLayout;
    Button button1;
    Button button2;
    Button button3;
    int lastType = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFormat(PixelFormat.TRANSLUCENT);

        setContentView(R.layout.activity_main);

        button1 = (Button) findViewById(R.id.button_1);
        button2 = (Button) findViewById(R.id.button_2);
        button3 = (Button) findViewById(R.id.button_3);

        frameLayout = (FrameLayout) findViewById(R.id.frameLayout);
        mapFrameLayout = (FrameLayout) findViewById(R.id.mapFrameLayout);

        list = new ArrayList<BaseFragment>();
        list.add(new BlankFragment());
        list.add(new BlankFragment2());
        list.add(new BlankFragment3());


        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeFragment(list.get(0));
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeFragment(list.get(1));
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeFragment(list.get(2));
            }
        });

        fm = getSupportFragmentManager();

        changeFragment(list.get(0));
    }

    public void changeFragment(int type) {
        // 1
        //FragmentTransaction fragmentTransaction = fm.beginTransaction();
//        Fragment fragment = fm.findFragmentByTag(type + "");
//        if (mCurFragment != null) {
//            fragmentTransaction.hide(mCurFragment);
//            mCurFragment.setUserVisibleHint(false);
//        }
//        if (fragment == null) {
//            fragmentTransaction.add(R.id.frameLayout, list.get(type), type + "");
//        } else {
//            fragmentTransaction.show(fragment);
//        }
//        fragment.setUserVisibleHint(true);
//        mCurFragment = list.get(type);
//        fragmentTransaction.commit();

//        fragmentTransaction.replace(R.id.frameLayout, list.get(type), type + "").commit();
        // 2
//        FragmentTransaction fragmentTransaction = fm.beginTransaction();
//        if (lastType != type) {
//            if (lastType != -1) {
//                fragmentTransaction.remove(list.get(lastType));
//            }
//            if (type == 1) {
//                frameLayout.setVisibility(View.GONE);
//                mapFrameLayout.setVisibility(View.VISIBLE);
//                fragmentTransaction.replace(R.id.mapFrameLayout, list.get(type)).commit();
//            } else {
//                frameLayout.setVisibility(View.VISIBLE);
//                mapFrameLayout.setVisibility(View.GONE);
//                fragmentTransaction.replace(R.id.frameLayout, list.get(type)).commit();
//            }
//            lastType = type;
//        }
    }

    BaseFragment mLastBaseFragment;

    public void changeFragment(BaseFragment fragment) {
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        if (mLastBaseFragment != fragment) {
            if (fragment instanceof MapFragment) {
                frameLayout.setVisibility(View.GONE);
                mapFrameLayout.setVisibility(View.VISIBLE);
                fragmentTransaction.replace(R.id.mapFrameLayout, fragment);
            } else {
                frameLayout.setVisibility(View.VISIBLE);
                mapFrameLayout.setVisibility(View.GONE);
                fragmentTransaction.replace(R.id.frameLayout, fragment);
            }
            if (mLastBaseFragment != null) {
                fragmentTransaction.remove(mLastBaseFragment);
            }
            fragmentTransaction.commit();
            mLastBaseFragment = fragment;
        }
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
