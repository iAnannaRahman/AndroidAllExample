package com.example.ananna.sociallogin;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.ananna.sociallogin.helper.EndPoints;
import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {


    private LoginButton logInBtn;
    private CallbackManager callbackManager;
    private GraphRequest request;
    private ImageView imagePP;
    private TextView tvName, tvEmail, tvGender;
    private boolean isResumed;
    private AccessTokenTracker accessTokenTracker;
    private GridLayout layoutProfileDetails;
    RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        queue = Volley.newRequestQueue(this);
        imagePP = (ImageView) findViewById(R.id.imagePP);
        tvName = (TextView) findViewById(R.id.tvName);
        tvEmail = (TextView) findViewById(R.id.tvEmail);
        tvGender = (TextView) findViewById(R.id.tvGender);

        layoutProfileDetails = (GridLayout) findViewById(R.id.layoutProfileDetails);
        callbackManager = CallbackManager.Factory.create();
        logInBtn = (LoginButton) findViewById(R.id.login_button);
        logInBtn.setReadPermissions(Arrays.asList("public_profile", "email", "user_birthday", "user_friends", "user_likes", "user_posts"));

        logInBtn.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Toast.makeText(MainActivity.this, "Success", Toast.LENGTH_SHORT).show();
                request = GraphRequest.newMeRequest(loginResult.getAccessToken(),
                        new GraphRequest.GraphJSONObjectCallback() {
                            @Override
                            public void onCompleted(JSONObject object, GraphResponse response) {

                                Log.v("AccessToken", request.getAccessToken().toString());
                                Log.v("LoginActivity", response.toString());
                                String id = object.optString("id", "");
                                String name = object.optString("name", "");

                                String email = object.optString("email", "");

                                String image_url = "http://graph.facebook.com/" + id + "/picture?type=large";

                                String user_birthday = object.optString("user_birthday", "");
                                String gender = object.optString("gender", "");
                                Log.v("LoginActivity", response.toString());

                                Glide.with(getBaseContext()).load(image_url).into(imagePP);

                                tvName.setText(name);
                                tvEmail.setText(email);
                                tvGender.setText(gender);
                                // Application code
                                sendDataToServer(object);


                            }


                        });


                Bundle parameters = new Bundle();
                parameters.putString("fields", "id,name,link,email,birthday,gender,location");
                request.setParameters(parameters);
                request.executeAsync();

            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {
                Toast.makeText(MainActivity.this, error.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        accessTokenTracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken,
                                                       AccessToken currentAccessToken) {

                    /*FragmentManager manager = getSupportFragmentManager();
                    int backStackSize = manager.getBackStackEntryCount();
                    for (int i = 0; i < backStackSize; i++) {
                        manager.popBackStack();
                    }*/
                if (currentAccessToken != null) {
                    //showFragment(SELECTION, false);
                    layoutProfileDetails.setVisibility(View.VISIBLE);
                    Log.v("Success", currentAccessToken.toString());
                } else {
                    //showFragment(SPLASH, false);
                    layoutProfileDetails.setVisibility(View.GONE);
                    Log.v("Logout", "No Token");

                    tvName.setText("");
                    tvEmail.setText("");
                    tvGender.setText("");
                    Glide.with(getBaseContext()).load(R.drawable.com_facebook_profile_picture_blank_square).into(imagePP);
                }

            }
        };

    }

    private void sendDataToServer(final JSONObject jsonObject) {
        StringRequest postRequest = new StringRequest(Request.Method.POST, EndPoints.POST_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("Response", response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Response", error.toString());
            }
        }
        ) {


            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                try {
                    params.put("fb_id", jsonObject.getString("id"));
                    params.put("name", jsonObject.getString("name"));
                    params.put("email", jsonObject.getString("email"));
                    params.put("birthday", jsonObject.getString("birthday"));
                    String pp_url = "http://graph.facebook.com/" + jsonObject.getString("id") + "/picture?type=large";
                    params.put("pp_url", pp_url);
                    Log.i("pp", pp_url.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                return params;
            }
        };
        queue.add(postRequest);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
}
