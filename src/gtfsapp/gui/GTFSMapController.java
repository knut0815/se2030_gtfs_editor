package gtfsapp.gui;

import gtfsapp.file.GTFSElement;
import gtfsapp.file.Stop;

import java.util.ArrayList;

/**
 * @author grant
 * @version 1.0
 * @created 15-Apr-2020 1:20:18 PM
 */
public class GTFSMapController extends GTFSController {

    private ArrayList<Stop> associatedStops;
    private GTFSMainController mainController;
    private GTFSElement selectedElement;

    /**
     *
     * @return
     */
    public GTFSMainController getMainController() {
        return mainController;
    }

    /**
     *
     * @return
     */
    public GTFSElement getSelectedElement() {
        return null;
    }

    /**
     *
     */
    public void invokeEditDialog() {

    }

    /**
     * @param msg
     */
    public void invokeErrorDialog(String msg) {

    }

    /**
     * @param selectedElement
     */
    public void setSelectedElement(GTFSElement selectedElement) {
        this.selectedElement = selectedElement;
    }

    /**
     *
     */
    public void updateMap() {

    }

}