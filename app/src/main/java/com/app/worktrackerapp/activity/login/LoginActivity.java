package com.app.worktrackerapp.activity.login;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.app.worktrackerapp.R;
import com.app.worktrackerapp.activity.dashboard.DashboardActivity;
import com.app.worktrackerapp.constants.ServiceURLConstants;
import com.app.worktrackerapp.model.User;

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

                    user.setUser_email(login_id.getText().toString());
                    user.setUser_password(password.getText().toString());


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
        User users = new User();
        JSONObject jsonObject;



        public LoginAsyncService(User user) {

            users = user;
        }

        public void BackGroundTask() {
            showDialog();
            String url = ServiceURLConstants.HOST_URL + ServiceURLConstants.LOGIN_URL;

            JSONObject obj = new JSONObject();
            try {
                obj.put("login_id", users.getUser_email());
                obj.put("password", users.getUser_password());
            } catch (JSONException e) {
                e.printStackTrace();
            }


            JsonObjectRequest req = new JsonObjectRequest(Request.Method.POST, url,obj,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response)
                        {
                            Log.d("RESPONSEEEE",response.toString());
                            jsonObject = response;
                            try {

                                String status = jsonObject.getString("status");
                                if(status.equals("Success"))
                                {
                                    JSONObject responseObject = new JSONObject(response.toString());
                                    JSONObject resultObject   =  responseObject.getJSONObject("response");
                                    //Log.d("RESULTOBJ",resultObject.toString());
                                    //Gson gson = new Gson();
                                    //User user = gson.fromJson(resultObject.toString(),User.class);
                                    hideDialog();
                                    Intent intent = new Intent(LoginActivity.this, DashboardActivity.class);
                                    intent.putExtra("USERDETAILS",resultObject.toString());
                                    startActivity(intent);
                                }
                                else
                                {
                                    Log.d("STATUSFAILURE",status);
                                    Toast.makeText(getApplicationContext(),"Failure",Toast.LENGTH_LONG).show();
                                    hideDialog();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

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
                public Map<String, String> getHeaders()  {
                    HashMap<String, String> headers = new HashMap<String, String>();
                    headers.put("Content-Type", "application/json");
                    return headers;
                }

            };
            Volley.newRequestQueue(LoginActivity.this).add(req);
        }

    }

}
