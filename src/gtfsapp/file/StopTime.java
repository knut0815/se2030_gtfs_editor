package gtfsapp.file;

import gtfsapp.id.RouteID;
import gtfsapp.id.StopTimeID;
import gtfsapp.id.TripID;
import gtfsapp.util.Time;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Mason Schlax
 * @version 1.0
 * @created 15-Apr-2020 1:20:18 PM
 */
public class StopTime extends GTFSElement implements Comparable<GTFSElement> {

    /**
     * The feed the stop time belongs to
     */
    private final Feed feed;
    /**
     * The time the trip arrives at the stop
     */
    private Time arrivalTime;
    /**
     * The time the trip departs from the stop
     */
    private Time departureTime;
    /**
     * The stop that the stop time occurs at
     */
    private Stop stop;

    /**
     * The head sign of the stop time
     */
    private String headSign;

    /**
     * Constructor for StopTime object, with three parameters
     * @param feed - the feed that contains the stop time
     * @param stop - the stop that the stop time occurs at
     * @param arrivalTime - the time the trip arrives at the stop
     * @param departureTime - the time the trip departs from the stop
     */
    public StopTime(Feed feed, Stop stop, Time arrivalTime, Time departureTime) {
        super(new StopTimeID());
        this.feed = feed;
        this.stop = stop;
        this.arrivalTime = arrivalTime;
        this.departureTime = departureTime;
    }

    /**
     * Gets the trip that contains this stop time
     *
     * @return a the trip that contains this stop time
     */
    public Trip getTrip() {

        // for each trip in the feed
        for (Trip trip : feed.getTrips()) {

            // if the trip contains this stop time's ID
            if (trip.containsStopTime((StopTimeID) getID())) {

                // return the trip
                return trip;

            }
        }

        // return null if nothing is found
        return null;

    }

    /**
     * Gets the ID of the trip that contains this stop time
     * @return the ID of the trip that contains this stop time
     */
    public TripID getTripID() {
        return (TripID) getTrip().getID();
    }

    /**
     * Gets a list of all routes that contain this stop time
     * @return a list of all routes that contain this stop time
     */
    public List<Route> getContainingRoutes() {

        List<Route> containingRoutes = new ArrayList<>();

        // for each route in the feed
        for (Route route : feed.getRoutes()) {

            // check to see if the route's trips contains our stop time's trip
            if (route.getTrips().contains(getTrip())) {

                // if they do, add the route to our set of containing route
                containingRoutes.add(route);

            }
        }

        return containingRoutes;

    }

    /**
     * Gets a list of the IDs of all routes that contain this stop time
     * @return a list of the IDs of all routes that contain this stop time
     */
    public List<RouteID> getContainingRouteIDs() {
        return getContainingRoutes().stream()
                .map(e -> (RouteID) e.getID())
                .collect(Collectors.toList());
    }

    /**
     * Getter for the arrival times
     * @return the arrival times
     */
    public Time getArrivalTime() {
        return arrivalTime;
    }

    /**
     * Sets the arrival time for the stop
     * @param arrivalTime the time the trip arrives at the stop
     */
    public void setArrivalTime(Time arrivalTime) {
        this.arrivalTime = arrivalTime;

    }

    /**
     * Getter for the departure times for the stop
     * @return the time the trip leaves the stop
     */
    public Time getDepartureTime() {
        return this.departureTime;
    }

    /**
     * Sets the departure time for the stop
     * @param departureTime the time the trip leaves the stop
     */
    public void setDepartureTime(Time departureTime) {
        this.departureTime = departureTime;
    }

    /**
     * Getter for the stop this stop time occurs at
     *
     * @return the stop that this stop time occurs at
     */
    public Stop getStop() {
        return stop;
    }

    /**
     * Sets the stop that this stop time occurs at
     *
     * @param stop the stop that this stop time occurs at
     */
    public void setStop(Stop stop) {
        this.stop = stop;
    }

    /**
     * Getter for the head sign for the current stop time
     *
     * @return the head sign
     */
    public String getHeadSign() {
        return headSign;
    }

    /**
     * Sets the head sign for the stop time
     *
     * @param headSign the head sign
     */
    public void setHeadSign(String headSign) {
        this.headSign = headSign;
    }

    /**
     * Checks if the current time of the system is between the arrival and departure time for the stop
     * to see if the stopTime is active
     *
     * @return true if the stopTime is active, otherwise false
     */
    public boolean isActive() {
        long currentTime = System.currentTimeMillis();
        long arrivalTime = this.getArrivalTime().getMillis();
        long departTime = this.getDepartureTime().getMillis();
        if(currentTime > arrivalTime && currentTime < departTime){
            return true;
        }else{
            return false;
        }
    }

    /**
     * Getter for the feed the stop belongs to
     *
     * @return the feed the stop belongs to
     */
    public Feed getFeed() {
        return feed;
    }

    /**
     * Gets the stop time's title to be displayed in the GUI
     * @return the stop time's title
     */
    @Override
    public String getTitle() {
        return stop.getTitle();
    }

    /**
     * Gets the stop time's subtitle to be displayed in the GUI
     * @return the stop time's subtitle
     */
    @Override
    public String getSubtitle() {
        return String.format("Arrives %s - Departs %s", arrivalTime.toString(), departureTime.toString());
    }

    /**
     * Gets the stop time's attributes to be displayed in the GUI
     * @return a Map<Attribute Title, Attribute Value> of the stop time's attributes
     */
    @Override
    public Map<String, String> getAttributes() {
        Map<String, String> attributes = new LinkedHashMap<>();
        attributes.put("Stop", stop.getSubtitle());
        attributes.put("Arrival Time", arrivalTime.toString());
        attributes.put("Departure Time", arrivalTime.toString());
        return attributes;
    }

    /**
     * Gets the stop time's color
     * @return the stop time's color
     */
    @Override
    public Color getColor() {
        return getTrip().getRoute().getColor();
    }

    /**
     * Determines when one stop time's arrival time occurs with relevance to another
     * @param element - the element to compare to
     * @return -1 if the other stop time's arrival time occurs before this stop time's arrival time , 0 if the stop
     * time's arrival times occur simultaneously, or 1 if the other stop time's arrival time occurs after this stop
     * time's arrival time
     */
    @Override
    public int compareTo(GTFSElement element) {
        if (element instanceof StopTime) {
            return getArrivalTime().compareTo(((StopTime) element).getArrivalTime());
        } else {
            return super.compareTo(element);
        }
    }
}