package max.com.realtimetransportodessa;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
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

import max.com.realtimetransportodessa.model.Master;
import max.com.realtimetransportodessa.model.Point;
import max.com.realtimetransportodessa.model.Route;
import max.com.realtimetransportodessa.model.Segment;

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
        urls.put("LoadingListStopping", "http://transport.odessa.ua/php/LoadingListStopping.php");
        urls.put("LoadingListMaster", "http://transport.odessa.ua/php/LoadingListMaster.php");
    }

    public static Loader getInstance(Context context) {
        if (loader == null) {
            loader = new Loader(context);
        }
        return loader;
    }

    public void loadRoutesList() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                urls.get("LoadingListRoutes"),
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
        StringRequest strRequest = new StringRequest(Request.Method.POST,
                urls.get("LoadingRoute"),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject json = new JSONObject(response);
                            if(json.getBoolean("success")) {
                                Route.Builder routeBuilder = Route.newBuilder();
                                JSONObject data = json.getJSONObject("data");
                                routeBuilder
                                        .setNumber(data.getInt("Number"))
                                        .setType(data.getString("Type"))
                                        .setColor(data.getString("color"))
                                        .setCost(data.getDouble("cost"))
                                        .setDistance(data.getDouble("distance"))
                                        .setId(data.getInt("id"))
                                        .setTitle(data.getString("title"));

                                JSONArray segmentsJSON = data.getJSONArray("segments");
                                List<Segment> segments = new ArrayList<>();
                                for(int i = 0; i < segmentsJSON.length(); i++) {
                                    JSONObject segmentJSON = segmentsJSON.getJSONObject(i);
                                    Segment.Builder segmentBuilder = Segment.newBuilder()
                                            .setBuilt(segmentJSON.getInt("built"))
                                            .setDirection(segmentJSON.getInt("direction"))
                                            .setId(segmentJSON.getInt("id"))
                                            .setPosition(segmentJSON.getInt("position"))
                                            .setRouteId(segmentJSON.getInt("routeId"))
                                            .setStopping(segmentJSON.getInt("stoppingId"));

                                    JSONArray pointsJSON = segmentJSON.getJSONArray("points");
                                    List<Point> points = new ArrayList<>();
                                    for(int j = 0; j < pointsJSON.length(); j++) {
                                        JSONObject pointJSON = pointsJSON.getJSONObject(j);
                                        Point point = Point.newBuilder()
                                                .setId(pointJSON.getInt("id"))
                                                .setLat(pointJSON.getDouble("lat"))
                                                .setLng(pointJSON.getDouble("lng"))
                                                .setPosition(pointJSON.getInt("position"))
                                                .setSegmentId(pointJSON.getInt("segmentId"))
                                                .build();
                                        points.add(point);
                                    }
                                    segmentBuilder.setPoints(points);
                                    segments.add(segmentBuilder.build());
                                }
                                routeBuilder.setSegments(segments);
                                Route route = routeBuilder.build();
                                Log.d(TAG, "Route: " + route.toString());
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
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

    public void loadStoppingList(final String language) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                urls.get("LoadingListStopping"),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject json = new JSONObject(response);
                            if(json.getBoolean("success")) {
                                JSONArray listJSON = json.getJSONArray("list");
                                List<String> stoppingList = new ArrayList<>();
                                for(int i = 0; i < listJSON.length(); i++) {
                                    stoppingList.add(listJSON.getJSONObject(i).getString("title"));
                                }
                                Log.d(TAG, "Stopping list: " + stoppingList);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e(TAG, "Error loading stopping list: " + error);
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("language", language);
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }

    public void loadMasterList(final String language) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                urls.get("LoadingListMaster"),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject json = new JSONObject(response);
                            if(json.getBoolean("success")) {
                                JSONArray listJSON = json.getJSONArray("list");
                                List<Master> masters = new ArrayList<>();
                                for(int i = 0; i < listJSON.length(); i++) {
                                    JSONObject masterJSON = listJSON.getJSONObject(i);
                                    Master master = Master.newBuilder()
                                            .setId(masterJSON.getInt("id"))
                                            .setTitle(masterJSON.getString("title"))
                                            .build();
                                    masters.add(master);
                                }
                                Log.d(TAG, "Masters list: " + masters);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e(TAG, "Error loading master list: " + error);
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("language", language);
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }
}
