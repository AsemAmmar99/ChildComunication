package com.inventure.childcomunication.base;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.inventure.childcomunication.activity.Home;

public class BaseActivity extends AppCompatActivity {
    AlertDialog.Builder alertDialog ;
    Context context ;

     protected void initBaseActivity(Context context){
         this.context = context ;
         alertDialog
                 = new AlertDialog.Builder(context);


     }
    protected void setAlertDialog(String massage ,String positiveName, DialogInterface.OnClickListener positive
           ,String nagativeMassage , DialogInterface.OnClickListener nagative){


        alertDialog.setMessage(massage);
        alertDialog.setPositiveButton(positiveName,  positive);
        alertDialog.setNegativeButton(nagativeMassage,nagative);



        alertDialog.setCancelable(false);
        alertDialog.show();
    }

    protected void ToastMassage(String massage){

        Toast.makeText(this, massage, Toast.LENGTH_SHORT).show();

    }
}
