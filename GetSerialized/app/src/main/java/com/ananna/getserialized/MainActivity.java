package com.ananna.getserialized;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.ananna.getserialized.adapter.UserAdapter;
import com.ananna.getserialized.helper.EndPoints;
import com.ananna.getserialized.model.User;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private EditText tvName, tvEmail, tvPhone;
    private Button btnSave;
    RequestQueue queue;
    private ListView listView;
    User user;
    private ArrayList<User> arrayList;
    private UserAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        queue = Volley.newRequestQueue(MainActivity.this);
        tvName = (EditText) findViewById(R.id.tvName);
        tvEmail = (EditText) findViewById(R.id.tvEmail);
        tvPhone = (EditText) findViewById(R.id.tvPhone);

        btnSave = (Button) findViewById(R.id.btnSave);

        listView = findViewById(R.id.listView);
        arrayList = new ArrayList<User>();

        adapter = new UserAdapter(MainActivity.this, arrayList);
        listView.setAdapter(adapter);
        getDataFormServer();
        //getNews();
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                user = new User();
                user.setUserName(tvName.getText().toString());
                user.setEmail(tvEmail.getText().toString());
                user.setPhone(tvPhone.getText().toString());
                sendDataToServer(user);
                clearData();
                getDataFormServer();
            }
        });

    }

    private void clearData() {
        tvName.setText("");
        tvEmail.setText("");
        tvPhone.setText("");
    }

    private void getDataFormServer() {

        if (isNetworkAvailable()) {
            JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, EndPoints.GETDATA, new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {
                    Log.e("send", response.toString());
                    int length = response.length();
                    for (int i = 0; i < length; i++) {
                        try {
                            User userObject = new User();
                            userObject.setUserName(response.getJSONObject(i).optString("name"));
                            userObject.setEmail(response.getJSONObject(i).optString("email"));
                            userObject.setPhone(response.getJSONObject(i).optString("phone"));
                            // arrayList.add(userObject.getUserName().toString() + "\n" + userObject.getPhone().toString()+ "\n" + userObject.getEmail());
                            arrayList.add(userObject);

                            adapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e("Error", error.getCause().toString());
                }
            });
            queue.add(jsonArrayRequest);
        } else {
            Toast.makeText(MainActivity.this, "Connect Your Internet Please", Toast.LENGTH_LONG).show();
        }
    }


    //region Blocked


/*    private void getNews(){

       JsonArrayRequest jsonObjReq = new JsonArrayRequest(Request.Method.GET, EndPoints.NEWS_URL, new Response.Listener<JSONArray>() {

           @Override
           public void onResponse(JSONArray response) {
               Log.d("", response.toString());

           }
       }, new Response.ErrorListener() {

           @Override
           public void onErrorResponse(VolleyError error) {
               VolleyLog.d("", "Error: " + error.getMessage());
               //pDialog.hide();
           }
       }) {

           *//**
     * Passing some request headers
     * *//*
           @Override
           public Map<String, String> getHeaders() throws AuthFailureError {
               HashMap<String, String> headers = new HashMap<String, String>();
               headers.put("Content-Type", "application/json");
               headers.put("apiKey", EndPoints.API_KEY);
               return headers;
           }

       };
       queue.add(jsonObjReq);
   }*/
    //endregion

    private void sendDataToServer(final User userObject) {
        StringRequest postRequest = new StringRequest(Request.Method.POST, EndPoints.POST_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("Response", response);
                        Toast.makeText(MainActivity.this, response, Toast.LENGTH_SHORT).show();
                        adapter.RemoveAll();
                        getDataFormServer();
                        queue.getCache().clear();
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
                    params.put("name", userObject.getUserName());
                    params.put("email", userObject.getEmail());
                    params.put("phone", userObject.getPhone());


                } catch (Error e) {
                    e.printStackTrace();
                }
                return params;
            }
        };
        queue.add(postRequest);
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

}
