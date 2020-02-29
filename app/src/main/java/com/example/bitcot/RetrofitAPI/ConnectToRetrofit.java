package com.example.bitcot.RetrofitAPI;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.Window;
import android.widget.Toast;

import com.example.bitcot.R;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ConnectToRetrofit {
    private String action = "";
    private RetrofitCallBackListener retrofitCallBackListener;
    private Call<String> call;
    private Dialog dialog;
    private Activity activity;

    public ConnectToRetrofit(RetrofitCallBackListener retrofitCallBackListener, Activity activity, Call<String> _call, String action) {
        this.retrofitCallBackListener = retrofitCallBackListener;
        this.action = action;
        this.call = _call;
        this.activity = activity;

        requestAPI();
    }

    private void requestAPI() {
        try {
            if (!activity.isFinishing()) {
                dialog = new Dialog(activity);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.progress_layout);
                Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                dialog.setCancelable(false);

                if (!dialog.isShowing()) {
                    dialog.show();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        call.enqueue(new Callback<String>() {

            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                try {
                    if (response.code() == 200) {
                        if (retrofitCallBackListener != null) {
                            retrofitCallBackListener.retrofitCallBackListener(response.body(), action);

                        }
                    } else {
                        Toast.makeText(activity, "Some went wrong", Toast.LENGTH_SHORT).show();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (dialog != null && dialog.isShowing()) {
                        dialog.dismiss();
                    }
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                try {
                    Log.d("DEBUG", "onFailure" + t.fillInStackTrace() + "MESSAGE : " + t.getMessage());

                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (dialog != null && dialog.isShowing()) {
                        dialog.dismiss();
                    }
                }

            }
        });
    }
}

