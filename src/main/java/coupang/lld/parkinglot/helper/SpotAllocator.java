package coupang.lld.parkinglot.helper;

import coupang.lld.parkinglot.model.*;
import coupang.lld.parkinglot.strategy.SpotTypePreferenceStrategy;

import java.util.*;
import java.util.stream.Collectors;

public class SpotAllocator {

    public Optional<Spot> allocateSpot(ParkingStructure parkingStructure, SpotTypePreferenceStrategy spotTypePreferenceStrategy) {

        if (parkingStructure != null) {
            Map<Level, Map<SpotType, List<Spot>>> spotsAgainstLevelMap =  parkingStructure.getSpotsAgainstLevelMap();

            Set<Level> externalMapKeys = spotsAgainstLevelMap.keySet();

            List<Level> sortedLevels = externalMapKeys.stream()
                    .sorted(Comparator.comparingInt(Level::getLevel))
                    .collect(Collectors.toList());

            List<Spot> emptyList = new ArrayList<>();

            for (Level level : sortedLevels) {
                Map<SpotType, List<Spot>> internalMap = spotsAgainstLevelMap.get(level);
                for (SpotType spotType : spotTypePreferenceStrategy.fetchSpotTypePreference()) {
                    for (Spot spot : internalMap.getOrDefault(spotType, emptyList)) {
                        if (spot.getStatus() == SpotStatus.Available) {
                            spot.setStatus(SpotStatus.Occupied);
                            return Optional.of(spot);
                        }
                    }
                }
            }
            return Optional.empty();
        }
        throw new RuntimeException("ParkingStructure Is Null !!");
    }
}
