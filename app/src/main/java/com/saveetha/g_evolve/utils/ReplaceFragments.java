package com.saveetha.g_evolve.utils;

import android.content.Context;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.saveetha.g_evolve.R;

public class ReplaceFragments extends AppCompatActivity{

    Context context;
    public static void replaceFragment(Fragment fragment, boolean addToBackStack) {

        FragmentManager fragmentManager = fragment.getFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        FragmentManager fm = fragment.getChildFragmentManager();
        for (int i = 0; i < fm.getBackStackEntryCount(); ++i) {
            fm.popBackStack();
        }
        transaction.replace(R.id.fragmentContainer, fragment);
        if (addToBackStack) {
            transaction.addToBackStack(null);
        }
        transaction.commit();
    }
}
