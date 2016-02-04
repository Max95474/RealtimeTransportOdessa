package max.com.realtimetransportodessa.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import max.com.realtimetransportodessa.ContentProvider;
import max.com.realtimetransportodessa.Loader;
import max.com.realtimetransportodessa.R;
import max.com.realtimetransportodessa.model.Route;
import max.com.realtimetransportodessa.model.Segment;

public class MainActivity extends AppCompatActivity implements Observer {
    private final String TAG = "MainActivity";
    private ContentProvider contentProvider = ContentProvider.getInstnce();
    private Loader loader;

    private ListView listView ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loader = Loader.getInstance(this, contentProvider);
        contentProvider.addObserver(this);
        loader.loadRoutesList();
        loader.loadMasterList("ru");
//        loader.loadStoppingList(0, "ru");
        loader.loadRoute("tram", "20", "ru");


    }

    public void toMap(View view) {
        Intent intent = new Intent(this, MapActivity.class);
        startActivity(intent);
    }

    @Override
    public void update(Observable observable, Object data) {
        Log.d(TAG, "Got notification: " + data);
        if(data.equals("RouteList")) {
            List<Route> routes = contentProvider.getRouteList();
            String[] values =  new String[routes.size()];
            for(int i = 0; i < routes.size(); i++) {
                Route route = routes.get(i);
                values[i] = route.getTitle() + " " + route.getType() + " " + route.getDistance();
            }
            setList(values);
            Log.d(TAG, contentProvider.getRouteList().toString());
        } else if(data.equals("MasterList")) {
            Log.d(TAG, contentProvider.getMasterList().toString());
        } else if(data.equals("Route")) {
            Route route = contentProvider.getRoute();
            List<Segment> segments = route.getSegments();
            List<Integer> stoppings = new ArrayList<>();
            for(Segment segment : segments)
                stoppings.add(segment.getStopping());
            loader.loadStoppingList(stoppings, "ru");
            Log.d(TAG, route.toString());
        } else if(data.equals("StoppingList")) {
            List<String> stoppings = contentProvider.getStoppingList();
            Log.d(TAG, stoppings.toString());
        }
    }

    private void setList(String[] values) {
        listView = (ListView) findViewById(R.id.routesList);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, values);
        listView.setAdapter(adapter);
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                int itemPosition     = position;
//                String  itemValue    = (String) listView.getItemAtPosition(position);
//                Toast.makeText(getApplicationContext(),
//                        "Position :" + itemPosition + "  ListItem : " + itemValue, Toast.LENGTH_LONG)
//                        .show();
//            }
//        });
    }
}