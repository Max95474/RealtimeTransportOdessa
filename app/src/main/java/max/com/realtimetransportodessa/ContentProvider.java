package max.com.realtimetransportodessa;

import java.util.List;
import java.util.Observable;

import max.com.realtimetransportodessa.model.Master;
import max.com.realtimetransportodessa.model.Route;

public class ContentProvider extends Observable {
    private static ContentProvider contentProvider;
    private List<Route>  routeList;
    private List<String> stoppingList;
    private List<Master> masterList;
    private Route route;

    private ContentProvider() {}

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
        setChanged();
        notifyObservers("Route");
    }

    public Route getRoute() {
        return this.route;
    }
}
