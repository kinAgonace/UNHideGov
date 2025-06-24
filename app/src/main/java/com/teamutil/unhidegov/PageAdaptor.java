package com.teamutil.unhidegov;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class PageAdaptor extends FragmentStateAdapter {
    Context context;
    public  PageAdaptor (@NonNull FragmentActivity fragmentActivity){
        super(fragmentActivity);
        this.context = context;
    }
    @NonNull
    @Override
    public Fragment createFragment(int position) {

        switch (position){
            case 0 :
                return new home();
            case 1 :
                return new Search();
            case 2 :
                return new notif();
            default:
                return new User();
        }
    }

    @Override
    public int getItemCount() {
        return 4;
    }
}
