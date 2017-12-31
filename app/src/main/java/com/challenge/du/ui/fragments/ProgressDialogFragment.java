package com.challenge.du.ui.fragments;

import android.app.Dialog;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

import com.challenge.du.R;


public class ProgressDialogFragment extends DialogFragment {
    private static final String KEY_AUTO_HIDE = "key_auto_hide_dialog";
    private static final String KEY_TITLE = "key_title";
    private float rotateAngle;
    private Handler mHandler = new Handler();

    public static void show(FragmentActivity activity, String title) {
        if (activity == null) {
            return;
        }
        show(activity, title, -1);
    }

    public static void show(FragmentActivity activity, String title, boolean cancelable) {
        if (activity == null) {
            return;
        }
        show(activity, title, -1, cancelable);
    }

    public static void show(FragmentActivity activity) {
        if (activity != null) {
            show(activity, "", -1);
        }

    }

    public static ProgressDialogFragment show(FragmentActivity activity, String title, int autoHideTimeOutMillis) {
        FragmentManager fm = activity.getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        hideInternal(ft, fm);
        // Create and show the dialog.
        ProgressDialogFragment newFragment = new ProgressDialogFragment();
        Bundle args = new Bundle();
        args.putString(KEY_TITLE, title);
        if (autoHideTimeOutMillis != -1) {
            args.putInt(KEY_AUTO_HIDE, autoHideTimeOutMillis);
        }
        newFragment.setArguments(args);
        newFragment.show(ft, ProgressDialogFragment.class.getName());
        return newFragment;
    }

    public static ProgressDialogFragment show(FragmentActivity activity, String title, int autoHideTimeOutMillis, boolean cancelable) {
        FragmentManager fm = activity.getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        hideInternal(ft, fm);
        // Create and show the dialog.
        ProgressDialogFragment newFragment = new ProgressDialogFragment();
        Bundle args = new Bundle();
        args.putString(KEY_TITLE, title);
        if (autoHideTimeOutMillis != -1) {
            args.putInt(KEY_AUTO_HIDE, autoHideTimeOutMillis);
        }
        newFragment.setArguments(args);
        newFragment.setCancelable(cancelable);
        newFragment.show(ft, ProgressDialogFragment.class.getName());
        return newFragment;
    }

    private static void hideInternal(FragmentTransaction ft, FragmentManager fm) {
        Fragment prev = fm.findFragmentByTag(ProgressDialogFragment.class.getName());
        if (prev != null) {
            ft.remove(prev);
        }
    }

    public static void hide(FragmentActivity activity) {
        if (activity == null) {
            return;
        }
        if (!activity.isFinishing()) {
            FragmentManager fm = activity.getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            hideInternal(ft, fm);
            ft.commitAllowingStateLoss();
        }
    }

    public float getRotateTest() {
        return rotateAngle;
    }

    public void setRotateTest(float angle) {
        rotateAngle = angle;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Bundle args = getArguments();
        final FragmentActivity activity = getActivity();
        Dialog builder = new Dialog(activity, R.style.ProgressDialogFragment);
        View view = View.inflate(getActivity(), R.layout.fragment_progress_dialog, null);

        builder.setContentView(view);

        int autoHideTime = args.getInt(KEY_AUTO_HIDE, -1);
        if (autoHideTime != -1) {
            mHandler.postDelayed(new Runnable() {

                @Override
                public void run() {
                    hide(activity);
                }
            }, autoHideTime);
        }

        return builder;
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
