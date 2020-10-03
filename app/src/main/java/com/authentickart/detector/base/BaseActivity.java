package com.authentickart.detector.base;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

/**
 * Created by arif on 23/7/17.
 */

public class BaseActivity extends AppCompatActivity {

    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * Method to display toast message for short period of time
     *
     * @param message - Accept message string which it needs to display
     */
    protected void showShortToast(String message) {
        Toast.makeText(BaseActivity.this, message, Toast.LENGTH_SHORT).show();
    }

    /**
     * Method to display toast message for long period of time
     *
     * @param message - Accept message string which it needs to display
     */
    protected void showLongToast(String message) {
        Toast.makeText(BaseActivity.this, message, Toast.LENGTH_LONG).show();
    }


    /**
     * Replace an existing fragment that was added to a container.  This is
     * currently added fragments that were added with the same containerViewId, It will
     * replace the current fragment and last fragment will not be available in back stack
     *
     * @param id          Identifier of the container whose fragment(s) are
     *                    to be replaced.
     * @param fragment    The new fragment to place in the container.
     * @param fragmentTag Optional tag name for the fragment, to later retrieve the
     *                    fragment with {@link android.support.v4.app.FragmentManager#findFragmentByTag(String)
     *                    FragmentManager.findFragmentByTag(String)}.
     */
    protected void changeFragmentWithOutBackStack(int id, Fragment fragment, String fragmentTag) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(id, fragment, fragmentTag)
                .commit();
    }

    /**
     * Add a fragment to back stack.
     *
     * @param id          Identifier of the container whose fragment(s) are
     *                    to be replaced.
     * @param fragment    The new fragment to place in the container.
     * @param fragmentTag Optional tag name for the fragment, to later retrieve the
     *                    fragment with {@link android.support.v4.app.FragmentManager#findFragmentByTag(String)
     *                    FragmentManager.findFragmentByTag(String)}.
     */
    protected void changeFragmentWithBackStack(int id, Fragment fragment, String fragmentTag) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(id, fragment)
                .addToBackStack(fragment.getClass().getSimpleName())
                .commit();
    }

    /**
     * Cancel running progress dialog
     */
    public void cancelProgressDialog() {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
            mProgressDialog = null;
        }
    }

    protected void changeFragment(int id, Fragment fragment) {
        String backStateName = fragment.getClass().getName();
        boolean fragmentPopped = getSupportFragmentManager().popBackStackImmediate(backStateName, 0);
        if (!fragmentPopped) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(id, fragment)
                    .addToBackStack(backStateName)
                    .commit();
        }
    }

    protected void changeFragmentNoBackstack(int id, Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(id, fragment)
                .commit();
    }

    protected void addFragment(int id, Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .add(id, fragment)
                .commit();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    protected boolean isPresenterSubscribed(BasePresenter presenter, BaseView view) {
        return presenter != null && presenter.wasSubscribed(view);
    }

}
