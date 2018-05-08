package com.example.admin.tutoserevices;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.inputmethod.InputMethodManager;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by admin on 08/03/2018.
 */

public class Functions {
    private SessionManager session;


    //Main URL
 // private static String MAIN_URL = "http://192.168.253.4/mehatdev/reclamamtionprojectdev/app/serviceslogin.php?op=login";

   private static String MAIN_URL = "http://10.0.2.2/reclam/auth.php?auth=login";
    //private static String MAIN_URL = "http://192.168.8.105/reclamamtionprojectdev/app/serviceslogin.php?op=login";

    // Login URL
    public static String LOGIN_URL = MAIN_URL;

    // Register URL
    public static String GETLIST_URL = "http://10.0.2.2/reclamamtionprojectdev/app/serviceslogin.php?op=getliste";

    // OTP Verification
    public static String REGISTER_URL =  "http://10.0.2.2/reclamamtionprojectdev/app/registerservice.php";

    // Forgot Password
    public static String OTP_VERIFY_URL = MAIN_URL + "";
    public static  String ajoutrec_url ="http://10.0.2.2/reclamamtionprojectdev/app/ajouterRec.php";

//    public static boolean isValidEmail(String email)
//    {
//        boolean isValidEmail = false;
//
//        String emailExpression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
//        CharSequence inputStr = email;
//
//        Pattern pattern = Pattern.compile(emailExpression, Pattern.CASE_INSENSITIVE);
//        Matcher matcher = pattern.matcher(inputStr);
//        if (matcher.matches())
//        {
//            isValidEmail = true;
//        }
//        return isValidEmail;
//    }
   public static boolean isValidEmailAddress(String email) {
        String ePattern = "";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(email);
        return m.matches();
    }


    public static void hideSoftKeyboard(Activity activity) {
        InputMethodManager inputMethodManager =
                (InputMethodManager) activity.getSystemService(
                        Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(
                activity.getCurrentFocus().getWindowToken(), 0);
    }

        public boolean logoutUser(Context context){
            DatabaseHandler db = new DatabaseHandler(context);
            db.resetTables();
            return true;
        }


    }
