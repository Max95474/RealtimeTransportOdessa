package max.com.realtimetransportodessa.activities;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import max.com.realtimetransportodessa.ContentProvider;
import max.com.realtimetransportodessa.Loader;
import max.com.realtimetransportodessa.R;
import max.com.realtimetransportodessa.model.Point;
import max.com.realtimetransportodessa.model.Route;
import max.com.realtimetransportodessa.model.Segment;
import max.com.realtimetransportodessa.model.State;
import max.com.realtimetransportodessa.model.Transport;

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback, Observer {
    private static final String TAG = "MapActivity";
    private ContentProvider contentProvider = ContentProvider.getInstnce();
    private Loader loader = Loader.getInstance();
    private GoogleMap mMap;
    private Route currentRoute;
    private List<State> stateList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        int index = getIntent().getIntExtra("routeIndex", 0);
        currentRoute = contentProvider.getLoadedRoutes().get(index);
        ArrayList<String> transortKeys = new ArrayList<>();
        List<Transport> transport = currentRoute.getTransport();
        for(Transport transportEntity : transport) {
            transortKeys.add(transportEntity.getId());
        }
        loader.getState(transortKeys);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // app icon in action bar clicked; goto parent activity.
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng odessa = new LatLng(46.473765, 30.728540);
//        mMap.addMarker(new MarkerOptions().position(odessa).title("Marker in Odessa"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(odessa));
        mMap.animateCamera(CameraUpdateFactory
                .newLatLngZoom(odessa, 12.0f));

        drawRoute(currentRoute);
        contentProvider.addObserver(this);
    }

    @Override
    public void update(Observable observable, Object event) {
        switch ((String)event) {
            case "State":
                stateList = contentProvider.getStates();
                drawStatePoints(stateList);
                break;
        }
    }

    private void drawRoute(Route route) {
        PolylineOptions rectOptions = new PolylineOptions();

        List<Segment> segments = route.getSegments();
        for(Segment segment : segments) {
            List<Point> points = segment.getPoints();
            for(Point point : points) {
                double lat = point.getLat();
                double lng = point.getLng();
                rectOptions.add(new LatLng(lat, lng));
            }
        }

        rectOptions.color(Color.BLUE);
        Polyline polyline = mMap.addPolyline(rectOptions);
    }

    private Point getPointByPosition(List<Point> points, int position) {
        for(Point p : points) {
            if(p.getPosition() == position)
                return p;
        }
        return null;
    }

    private void drawStatePoints(List<State> stateList) {
        for(State state : stateList) {
            LatLng odessa = new LatLng(state.getLat(), state.getLng());
            mMap.addMarker(new MarkerOptions().position(odessa).title("Marker in Odessa"));
        }
    }
}
