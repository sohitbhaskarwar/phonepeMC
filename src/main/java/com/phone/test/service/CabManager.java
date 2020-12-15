package com.phone.test.details;

import com.phonepe.test.models.Cab;
import com.phonepe.test.models.City;

import java.util.*;

public class CabManager {

    HashMap<String, City> cityDetailsMap = new HashMap<>();
    HashMap<Integer, Cab> cabDetailsMap = new HashMap<>();
    Integer counterForCityId = 0;
    Integer counterForCabId = 0;

    public boolean addNewCity(String cityName, String stateName) {

        if(cityDetailsMap.containsKey(cityName)){
            return false; // city already rpresent
        }
        else{
            counterForCityId++;
            City city = new City(counterForCityId, cityName, stateName);
            cityDetailsMap.put(city.getCityName(), city);

            return true;
        }
    }

    public boolean addNewCab(String cityName,String cabModel){

        if(cityDetailsMap.containsKey(cityName)){
            counterForCabId++;
            City city = cityDetailsMap.get(cityName);
            Cab cab = new Cab(counterForCabId, city, cabModel);
            cabDetailsMap.put(cab.getCabId(), cab);
        }
    }


}
