package co.edu.uptc.project1.services;

import java.util.List;

import co.edu.uptc.linked_list.SimpleUptcList;
import co.edu.uptc.project1.exeptions.ProjectExeption;
import co.edu.uptc.project1.exeptions.TypeMessage;
import co.edu.uptc.project1.models.Location;
import lombok.Getter;

public class LocationService {
    @Getter
    private List<Location> locationList = new SimpleUptcList<Location>();

    public void addSubject(Location location) {
        locationList.add(location);
    }

    public void deleteSubject(String id) throws ProjectExeption {
        boolean exists = false;
        for (Location location : locationList) {
            if (location.getId() == Integer.parseInt(id)) {
                exists = true;
                locationList.remove(location);
            }
        }
        if (!exists) {
            throw new ProjectExeption(TypeMessage.CANT_DELETE);
        }
    }

    public Location updateLocation(Location location) throws ProjectExeption {
        Location locationToReturn = location;
        boolean exists = false;
        for (Location listLocation : locationList) {
            if (listLocation.getId() == location.getId()) {
                exists = true;
                updateLocationFields(listLocation, location);
            }
        }
        if (!exists) {
            throw new ProjectExeption(TypeMessage.NOT_UPDATED);
        }
        return locationToReturn;
    }

    private void updateLocationFields(Location locationToChange, Location location) {
        locationToChange.setName(location.getName());
        locationToChange.setUbication(location.getUbication());
    }
}
