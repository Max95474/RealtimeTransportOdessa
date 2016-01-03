package max.com.realtimetransportodessa;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import max.com.realtimetransportodessa.model.Route;

public class Loader {
    private static final String TAG = "Loader";
    private static Loader loader;
    private Context context;
    private RequestQueue requestQueue;
    private Map<String, String> urls;

    private Loader(Context context) {
        this.context = context;
        requestQueue = Volley.newRequestQueue(context);

        urls = new HashMap<>();
        urls.put("LoadingListRoutes", "http://transport.odessa.ua/php/LoadingListRoutes.php");
        urls.put("LoadingRoute", "http://transport.odessa.ua/php/LoadingRoute.php");
    }

    public static Loader getInstance(Context context) {
        if (loader == null) {
            loader = new Loader(context);
        }
        return loader;
    }

    public void loadRoutesList() {
        String URL = "http://transport.odessa.ua/php/LoadingListRoutes.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject json = new JSONObject(response);
                            if(json.getBoolean("success")) {
                                List<Route> routes = new ArrayList<>();
                                JSONArray routesJson = json.getJSONArray("list");
                                for(int i = 0; i < routesJson.length(); i++) {
                                    JSONObject routeJSON = routesJson.getJSONObject(i);
                                    Route route = Route.newBuilder()
                                            .setId(routeJSON.getInt("id"))
                                            .setNumber(routeJSON.getInt("Number"))
                                            .setType(routeJSON.getString("Type"))
                                            .setColor(routeJSON.getString("color"))
                                            .setCost(routeJSON.getDouble("cost"))
                                            .setDistance(routeJSON.getDouble("distance"))
                                            .setTitle(routeJSON.getString("title")).build();
                                    routes.add(route);
                                }
                                Log.d(TAG, "Got routes list: " + routes.toString());
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e(TAG, "Load routes list error: ", error);
                    }
                });
        requestQueue.add(stringRequest);
    }

    public void loadRoute(final String type, final String number, final String language) {
        String url ="http://transport.odessa.ua/php/LoadingRoute.php";

        StringRequest strRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d(TAG, "LoadRoute: " + response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e(TAG, "loadRouteError: " + error);
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("type", type);
                params.put("number", number);
                params.put("language", language);
                return params;
            }
        };

        requestQueue.add(strRequest);
    }
}
