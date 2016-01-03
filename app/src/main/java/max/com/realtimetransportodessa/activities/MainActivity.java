package max.com.realtimetransportodessa.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.Observable;
import java.util.Observer;

import max.com.realtimetransportodessa.ContentProvider;
import max.com.realtimetransportodessa.Loader;
import max.com.realtimetransportodessa.R;

public class MainActivity extends AppCompatActivity implements Observer {
    private final String TAG = "MainActivity";
    private ContentProvider contentProvider = new ContentProvider();
    private Loader loader;

    private TextView routesText;
    private TextView masterListText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        routesText = (TextView) findViewById(R.id.routesText);
        masterListText = (TextView) findViewById(R.id.masterListText);
        loader = Loader.getInstance(this, contentProvider);
        contentProvider.addObserver(this);
    }

    public void loadRoutes(View view) {
        loader.loadRoutesList();
    }

    public void loadMasterList(View view) {
        loader.loadMasterList("ru");
    }

    @Override
    public void update(Observable observable, Object data) {
        Log.d(TAG, "Got notification: " + data);
        if(data.equals("RouteList"))
            routesText.setText(contentProvider.getRouteList().toString());
        else if(data.equals("MasterList"))
            masterListText.setText(contentProvider.getMasterList().toString());
    }
}

