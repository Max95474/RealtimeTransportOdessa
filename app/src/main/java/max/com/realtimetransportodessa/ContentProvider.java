package max.com.realtimetransportodessa;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import max.com.realtimetransportodessa.model.Master;
import max.com.realtimetransportodessa.model.Route;
import max.com.realtimetransportodessa.model.State;

public class ContentProvider extends Observable {
    private static final String TAG = "ContentProvider";
    private static ContentProvider contentProvider;
    private List<Route>  routeList;
    private List<String> stoppingList;
    private List<Master> masterList;
    private Route route;
    private List<Route> loadedRoutes;

    private ContentProvider() {
        loadedRoutes = new ArrayList<>();
    }

    public static ContentProvider getInstnce() {
        if(contentProvider == null) {
            contentProvider = new ContentProvider();
            return contentProvider;
        } else {
            return contentProvider;
        }
    }

    public List<Route> getRouteList() {
        return routeList;
    }

    public void setRouteList(List<Route> routeList) {
        this.routeList = routeList;
        setChanged();
        notifyObservers("RouteList");
    }

    public List<String> getStoppingList() {
        return stoppingList;
    }

    public void setStoppingList(List<String> stoppingList) {
        this.stoppingList = stoppingList;
        setChanged();
        notifyObservers("StoppingList");
    }

    public List<Master> getMasterList() {
        return masterList;
    }

    public void setMasterList(List<Master> masterList) {
        this.masterList = masterList;
        setChanged();
        notifyObservers("MasterList");
    }

    public void setRoute(Route route) {
        this.route = route;
        loadedRoutes.add(route);
        Log.d(TAG, "Route loaded " + route.getId());
        setChanged();
        notifyObservers("Route");
    }

    public Route getRoute() {
        return this.route;
    }

    public List<Route> getLoadedRoutes() {
        return loadedRoutes;
    }
}
