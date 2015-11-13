package com.app.worktrackerapp.activity.login;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpStack;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.app.worktrackerapp.R;
import com.app.worktrackerapp.constants.ServiceURLConstants;
import com.app.worktrackerapp.model.User;
import com.app.worktrackerapp.utils.ApplicationController;
import com.app.worktrackerapp.utils.VolleyNetworkService;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class LoginActivity extends ActionBarActivity  {
    ProgressDialog progressDialog;
    public String url = ServiceURLConstants.HOST_URL + ServiceURLConstants.LOGIN_URL;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final User user = new User();

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please Wait");
        progressDialog.setCancelable(false);


        Button b = (Button) findViewById(R.id.button_login_submit);
        b.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {


                    EditText login_id = (EditText) findViewById(R.id.editText_login_id);
                    EditText password = (EditText) findViewById(R.id.editText_login_password);

                    user.setUser_email(login_id.toString());
                    user.setUser_password(password.toString());

                    new LoginAsyncService(user).BackGroundTask();
            }
        });
    }

    public void showDialog()
    {
        if(!progressDialog.isShowing())
        {
            progressDialog.show();
        }
    }

    public void hideDialog()
    {
        if(progressDialog.isShowing())
        {
            progressDialog.hide();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public class LoginAsyncService
    {


        User user = new User();
        JSONObject jsonObject;


        public LoginAsyncService(User user) {

            this.user = user;
        }

        public void BackGroundTask() {
            showDialog();
            String url = ServiceURLConstants.HOST_URL + ServiceURLConstants.LOGIN_URL;
            JsonObjectRequest req = new JsonObjectRequest(Request.Method.POST, url,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            jsonObject = response;
                            Log.d("RESPONSE", response.toString());
                            hideDialog();
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.d("TAG", "Error: " + error.getCause());
                            hideDialog();
                        }
                    }) {
                @Override
                public Map<String, String> getParams() {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("login_id", user.getUser_email());
                    params.put("password", user.getUser_password());
                    return params;
                }

                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    HashMap<String, String> headers = new HashMap<String, String>();
                    headers.put("Content-Type", "application/json");
                    return headers;
                }

            };
            Volley.newRequestQueue(LoginActivity.this).add(req);


        }


    }

}
