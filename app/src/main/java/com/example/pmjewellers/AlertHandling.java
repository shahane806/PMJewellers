package com.example.pmjewellers;

import static androidx.core.content.ContextCompat.startActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.widget.Toast;

public class AlertHandling {


    public Context context;


    public AlertHandling(Context context) {
        this.context =context;
    }
    public void emailRequiredDialog() {
        new AlertDialog.Builder(context)
                    .setIcon(R.drawable.error)
                    .setTitle("Error !!!")
                    .setMessage("EmailID required...")
                    .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    })
                    .setNeutralButton("Help", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Toast.makeText(context, "TO Login the app emailId is compulsory. ", Toast.LENGTH_LONG).show();
                        }
                    })
                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    }).show();
        Toast.makeText(context,context.toString(),Toast.LENGTH_LONG).show();
        }

        public void passwordRequiredDialog() {

            new AlertDialog.Builder(context)
                    .setIcon(R.drawable.error)
                    .setTitle("Error !!!")
                    .setMessage("Password  required...")
                    .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    })
                    .setNeutralButton("Help", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Toast.makeText(context, "TO Login the app Passswords are compulsory.", Toast.LENGTH_LONG).show();
                        }
                    })
                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                        }
                    }).show();
        }

        public void loginFailedDialog(){
            new AlertDialog.Builder(context)
                    .setIcon(R.drawable.error)
                    .setTitle("Error !!!")
                    .setMessage("No such account found. Check enterd details and try again.")
                    .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    })
                    .setNeutralButton("Help", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                            //Add Information fregment

                            Toast.makeText(context, "Help fregment will available  soon", Toast.LENGTH_LONG).show();
                        }
                    })
                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                        }
                    }).show();
        }

        public void registrationFailedDialog()
        {
            new AlertDialog.Builder(context)
                    .setIcon(R.drawable.error)
                    .setTitle("Error !!!")
                    .setMessage("Something went wrong. Try another way. ")
                    .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    })
                    .setNeutralButton("Help", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                            //Add Information fregment

                            Toast.makeText(context, "Help fregment will available  soon", Toast.LENGTH_LONG).show();
                        }
                    })
                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                        }
                    }).show();
        }

    public void passwordConfirmationFailedDialog(){
        new AlertDialog.Builder(context)
                .setIcon(R.drawable.error)
                .setTitle("Error !!!")
                .setMessage("Password and Confirm password should be same.")
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setNeutralButton("Help", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        //Add Information fregment

                        Toast.makeText(context, "Help fregment will available  soon", Toast.LENGTH_LONG).show();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                }).show();
    }

    public void UserNameRequiredDialog(){
        new AlertDialog.Builder(context)
                .setIcon(R.drawable.error)
                .setTitle("Error !!!")
                .setMessage("Password and Confirm password should be same.")
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setNeutralButton("Help", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        //Add Information fregment

                        Toast.makeText(context, "Help fregment will available  soon", Toast.LENGTH_LONG).show();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                }).show();
    }

    public void logoutConfirmationDialog() {




    }



    public void exitAppAlertDialog() {
        new AlertDialog.Builder(context)
                .setIcon(R.drawable.warning)
                .setTitle("Warning !!!")
                .setMessage("Are you sure to Exit ?")
                .setPositiveButton("Exit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent a = new Intent(Intent.ACTION_MAIN);
                        a.addCategory(Intent.CATEGORY_HOME);
                        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(context,a,null);
                    }
                })
                .setNeutralButton("Help", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(context, "Press Exit button to close the app, else press Cancel ", Toast.LENGTH_LONG).show();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                }).show();
    }


}
