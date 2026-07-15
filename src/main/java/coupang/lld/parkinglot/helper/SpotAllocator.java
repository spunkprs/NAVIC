package coupang.lld.parkinglot.helper;

import coupang.lld.parkinglot.model.*;
import coupang.lld.parkinglot.strategy.SpotTypePreferenceStrategy;


import java.util.List;
import java.util.TreeMap;

public class SpotAllocator {

    public Spot allocateSpot(ParkingStructure parkingStructure, SpotTypePreferenceStrategy spotTypePreferenceStrategy) {

        if (parkingStructure != null) {
            TreeMap<LevelSpotTypeCombination, List<Spot>> spotsAgainstLevelMap =  parkingStructure.getSpotsAgainstLevelMap();

            for (LevelSpotTypeCombination level : spotsAgainstLevelMap.keySet()) {
                for (SpotType spotType : spotTypePreferenceStrategy.fetchSpotTypePreference()) {
                    LevelSpotTypeCombination key = new LevelSpotTypeCombination(level.getLevel(), spotType);
                    for (Spot spot : spotsAgainstLevelMap.get(key)) {
                        if (spot.getStatus() == SpotStatus.Available) {
                            spot.setStatus(SpotStatus.Occupied);
                            return spot;
                        }
                    }
                }
            }
            throw new RuntimeException("No Spot Available !!");
        }
        throw new NullPointerException("ParkingStructure Is Null !!");
    }
}
