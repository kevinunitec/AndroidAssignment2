package com.example.application_json;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    //private static final TAG = "JSON";
    TextView tvJson;
    Button btnJson;
    RequestQueue queue = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvJson = (TextView) findViewById(R.id.tv_reault);
        btnJson = (Button) findViewById(R.id.btn_resutl);
        queue = Volley.newRequestQueue(this);
        btnJson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jsonResult();
            }
        });

    }
    public void jsonResult(){
        String url = "https://anapioficeandfire.com/api/characters/583";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        //String st = "";
                        StringBuilder builder = new StringBuilder();
                        try {
                            String url = response.getString("url");
                            String name = response.getString(("name"));
                            String gender = response.getString(("gender"));
                            String culture = response.getString(("culture"));
                            String born = response.getString(("born"));
                            String died = response.getString(("died"));

                          JSONArray data1 = response.getJSONArray("titles");

                            String a = "";
                            String b = "";

                            for (int i = 0; i < data1.length() ; i++) {
                                a = data1.getString(i);

                            }
                            JSONArray data2 = response.getJSONArray("aliases");
                            for (int i = 0; i < data2.length() ; i++) {
                                b = b + data2.getString(i) + "\n";
                            }
                            String father = response.getString(("father"));
                            String mother = response.getString(("mother"));
                            String spouse = response.getString(("spouse"));

                            String c = "";
                            JSONArray data3 = response.getJSONArray("allegiances");
                            for (int i = 0; i < data3.length() ; i++) {
                                c = c + data3.getString(i) + "\n";
                            }

                            String d = "";
                            JSONArray data4 = response.getJSONArray("tvSeries");
                            for (int i = 0; i < data4.length() ; i++) {
                                d = d + data4.getString(i) + "\n";
                            }
                            String e = "";
                            JSONArray data5 = response.getJSONArray("playedBy");
                            for (int i = 0; i < data5.length() ; i++) {
                                e = e + data5.getString(i) + "\n";
                            }

                           /* st += url + "\n";
                            st += name + "\n";*/

                            builder.append("url: " + url + "\n"
                                    + "name: " + name + "\n"
                                    + "gender: " + gender + "\n"
                                    + "culture: " + culture + "\n"
                                    + "born: " + born + "\n" +
                                    " died: " + died + "\n"
                                    + "titles" + a + "\n"
                                    + "aliases" + b + "\n"
                                    + "father" + father + "\n"
                                    +"mother" + mother + "\n"
                                    +"spouse" + spouse + "\n"
                                    +"mother" + mother + "\n"
                                    +"allegiances" + c + "\n"
                                    +"tvSeries" + d + "\n"
                                    +"playedBy" + e + "\n");
                            //tvJson.setText(st);
                            tvJson.setText(builder);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        queue.add(request);
    }
}
