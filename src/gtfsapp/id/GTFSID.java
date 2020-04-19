package gtfsapp.id;

import java.util.HashSet;

/**
 * @author Michael Primeau
 * @version 1.0
 * @created 15-Apr-2020 1:20:18 PM
 */
public abstract class GTFSID {

    private static HashSet<GTFSID> existingIDs = new HashSet<>();
    private String id;


    /**
     * Constructor for GTFSID
     * @param id String ID to be associated with this object
     */
    public GTFSID(String id) {
        this.id = id;
        existingIDs.add(this);
    }

    /**
     * Check if an ID already exists in the data structure
     * @param id ID to check for existence
     */
    public static boolean exists(GTFSID id) {
        return existingIDs.contains(id);
    }

    @Override
    public boolean equals(Object obj) {
        boolean stringMatch = id.equals(obj.toString());
        boolean classMatch = getClass().equals(obj.getClass());
        return stringMatch && classMatch;
    }

    @Override
    public String toString() {
        return id;
    }

}