package com.ananna.firebaseconnect;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ananna.firebaseconnect.model.Author;
import com.ananna.firebaseconnect.model.ISBN;
import com.ananna.firebaseconnect.model.Publisher;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.zxing.Result;

import org.json.JSONObject;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class ScannerActivity extends BaseScannerActivity implements ZXingScannerView.ResultHandler {
    private ZXingScannerView mScannerView;

    @Override
    public void onCreate(Bundle state) {
        super.onCreate(state);
        setContentView(R.layout.activity_simple_scanner);
        setupToolbar();

        ViewGroup contentFrame = (ViewGroup) findViewById(R.id.content_frame);
        mScannerView = new ZXingScannerView(this);
        contentFrame.addView(mScannerView);
    }

    @Override
    public void onResume() {
        super.onResume();
        mScannerView.setResultHandler(this);
        mScannerView.startCamera();
    }

    @Override
    public void onPause() {
        super.onPause();
        mScannerView.stopCamera();
    }

    @Override
    public void handleResult(Result rawResult) {
        Toast.makeText(this, "Contents = " + rawResult.getText() +
                ", Format = " + rawResult.getBarcodeFormat().toString(), Toast.LENGTH_SHORT).show();

        requestToWeb(rawResult.getText());

        // Note:
        // * Wait 2 seconds to resume the preview.
        // * On older devices continuously stopping and resuming camera preview can result in freezing the app.
        // * I don't know why this is the case but I don't have the time to figure out.
        resumeCamera();
    }

    private void resumeCamera() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mScannerView.resumeCameraPreview(ScannerActivity.this);
            }
        }, 2000);
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    private void requestToWeb(final String isbnNo) {
        RequestQueue queue = Volley.newRequestQueue(this);
        if (isNetworkAvailable()) {
            String getUrl = String.format("https://openlibrary.org/api/books?bibkeys=ISBN:%s&jscmd=data&format=json", isbnNo);
            Log.i("URL", getUrl);
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, getUrl, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        if (response.length() == 0) {
                            Toast.makeText(ScannerActivity.this, "No book found", Toast.LENGTH_LONG).show();
                            return;

                        }
                        //JSONObject json2 = response.getJSONObject("ISBN:9780307277671");
                        Gson gson = new Gson();
                        String jsonKey = "ISBN:" + isbnNo;
                        ISBN isbnObject = gson.fromJson(response.getString(jsonKey), ISBN.class);
                        String covername = isbnObject.getTitle();
                        String covername2 = isbnObject.getSubtitle();
                        Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                        Ringtone r = RingtoneManager.getRingtone(getApplicationContext(), notification);
                        r.play();
                        Toast.makeText(ScannerActivity.this, covername, Toast.LENGTH_LONG).show();

                        // get prompts.xml view
                        LayoutInflater li = LayoutInflater.from(ScannerActivity.this);
                        View promptsView = li.inflate(R.layout.dialogview_bookinf, null);

                        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(ScannerActivity.this);

                        // set prompts.xml to alertdialog builder
                        alertDialogBuilder.setView(promptsView);
                        ImageView bookCover = (ImageView) promptsView.findViewById(R.id.imageView);
                        TextView title = (TextView) promptsView.findViewById(R.id.tvTitle);
                        title.setText("Book Title: " + isbnObject.getTitle());

                        TextView author = (TextView) promptsView.findViewById(R.id.tvAuthor);
                        if (isbnObject.getAuthors() != null)
                            author.setText("Author: " + Author.authors(isbnObject.getAuthors()));
                        else {
                            if (!TextUtils.isEmpty(isbnObject.getPublishDate()))
                                author.setText("Published On: " + isbnObject.getPublishDate());
                        }
                        TextView publisher = (TextView) promptsView.findViewById(R.id.tvPublisher);
                        if (isbnObject.getPublishers() != null)
                            publisher.setText("Publisher: " + Publisher.publishers(isbnObject.getPublishers()));

                        TextView others = (TextView) promptsView.findViewById(R.id.tvOthers);
                        others.setText("ISBN: " + isbnNo);

                        Glide.with(ScannerActivity.this)
                                .load(isbnObject.getCover().getLarge())
                                .into(bookCover);


                        // set dialog message
                        alertDialogBuilder
                                .setCancelable(false)
                                .setPositiveButton("OK",
                                        new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int id) {
                                                // get user input and set it to result
                                                // edit text
                                                //result.setText(userInput.getText());

                                            }
                                        })
                                .setNegativeButton("Cancel",
                                        new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int id) {
                                                dialog.cancel();
                                            }
                                        });


                        AlertDialog alertDialog = alertDialogBuilder.create();
                        alertDialog.show();


                    } catch (final Exception error) {
                        error.printStackTrace();
                        Log.e("ERROR", error.toString());
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(ScannerActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
                            }
                        });
                    }

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(final VolleyError error) {
                    Log.e("ERROR", error.toString());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(ScannerActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
                        }
                    });

                }
            });
            jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(50000, 5, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            queue.add(jsonObjectRequest);
        } else {
            Toast.makeText(ScannerActivity.this, "Connect Your Internet Please", Toast.LENGTH_LONG).show();
        }
    }
}
