package max.com.realtimetransportodessa;

import java.util.List;
import java.util.Observable;

import max.com.realtimetransportodessa.model.Master;
import max.com.realtimetransportodessa.model.Route;

public class ContentProvider extends Observable {
    private List<Route>  routeList;
    private List<String> stoppingList;
    private List<Master> masterList;

    public ContentProvider() {}

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
    }

    public List<Master> getMasterList() {
        return masterList;
    }

    public void setMasterList(List<Master> masterList) {
        this.masterList = masterList;
        setChanged();
        notifyObservers("MasterList");
    }
}
