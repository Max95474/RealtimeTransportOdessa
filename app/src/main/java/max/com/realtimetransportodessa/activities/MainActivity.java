package max.com.realtimetransportodessa.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import max.com.realtimetransportodessa.Loader;
import max.com.realtimetransportodessa.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void loadRoutes(View view) {
        final TextView routesText = (TextView) findViewById(R.id.routesText);
        Loader loader = Loader.getInstance(this);
        loader.loadRoute("trolleybuses", "2", "ru");
        loader.loadRoutesList();
//        RequestQueue queue = Volley.newRequestQueue(this);
//        String url = "http://transport.odessa.ua/php/LoadingListRoutes.php";
//        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        routesText.setText(response);
//                        try {
//                            JSONObject json = new JSONObject(response);
//                            routesText.setText("Is success: " + json.getBoolean("success"));
//                            JSONArray routes = json.getJSONArray("list");
//                            for(int i = 0; i < routes.length(); i++) {
//                                JSONObject routeObject = routes.getJSONObject(i);
//                                routesText.append(routeObject.getString("Number") + " " +
//                                routeObject.getString("Type") + "\n");
//                            }
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                            routesText.setText(e.toString());
//                        }
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        routesText.setText(error.toString());
//                    }
//                });
//        queue.add(stringRequest);
    }
}

