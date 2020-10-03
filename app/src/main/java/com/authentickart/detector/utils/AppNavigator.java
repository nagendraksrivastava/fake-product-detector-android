package com.authentickart.detector.utils;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;

import com.authentickart.detector.base.BaseActivity;
import com.authentickart.detector.db.entities.User;
import com.authentickart.detector.features.authentication.activities.AuthenticationActivity;
import com.authentickart.detector.features.brands.activities.BrandsActivity;
import com.authentickart.detector.features.categories.activities.CategoriesActivity;
import com.authentickart.detector.features.landing.activities.LandingActivity;
import com.authentickart.detector.features.signin.activities.SignInActivity;
import com.authentickart.detector.features.signup.activities.SignUpActivity;
import com.authentickart.detector.features.subcategories.activities.SubCategoriesActivity;

/**
 * Created by arif on 23/7/17.
 */

public class AppNavigator {

    private AppNavigator() {

    }

    public static AppNavigator getInstance() {
        return AppNavigatorInstanceHolder.CART_MANAGER_INSTANCE;
    }

    private static void startActivity(BaseActivity from, Bundle extras, Intent intent) {
        if (extras != null)
            intent.putExtras(extras);
        from.startActivity(intent, getTransitionBundle(from));
    }

    private static Bundle getTransitionBundle(BaseActivity activity) {
        return ActivityOptionsCompat.makeSceneTransitionAnimation(activity).toBundle();
    }

    public void startAppropriateActivityPostSplash(BaseActivity from, Bundle extras) {
        User user = UserManager.getInstance().getUser();
        if (user == null) {
            startLandingActivity(from, extras);
        } else {
            startCategoriesActivity(from, extras);
        }
    }

    public void startLandingActivity(BaseActivity from, Bundle extras) {
        Intent intent = new Intent(from, LandingActivity.class);
        startActivity(from, extras, intent);
    }

    public void startSignInActivity(BaseActivity from, Bundle extras) {
        Intent intent = new Intent(from, SignInActivity.class);
        startActivity(from, extras, intent);
    }

    public void startSignUpActivity(BaseActivity from, Bundle extras) {
        Intent intent = new Intent(from, SignUpActivity.class);
        startActivity(from, extras, intent);
    }

    public void startCategoriesActivity(BaseActivity from, Bundle extras) {
        Intent intent = new Intent(from, CategoriesActivity.class);
        startActivity(from, extras, intent);
    }

    public void startSubCategoriesActivity(BaseActivity from, Bundle extras) {
        Intent intent = new Intent(from, SubCategoriesActivity.class);
        startActivity(from, extras, intent);
    }

    public void startBrandsActivity(BaseActivity from, Bundle extras) {
        Intent intent = new Intent(from, BrandsActivity.class);
        startActivity(from, extras, intent);
    }

    public void startAuthenticationActivity(BaseActivity from, Bundle extras){
        Intent intent = new Intent(from, AuthenticationActivity.class);
        startActivity(from, extras, intent);
    }

    private static class AppNavigatorInstanceHolder {
        private static final AppNavigator CART_MANAGER_INSTANCE = new AppNavigator();
    }

}
