package coupang.lld.parkinglot.model;

import java.util.List;
import java.util.Map;

public class ParkingStructure {
    private String parkingStructureId;
    private Map<Level, Map<SpotType, List<Spot>>> spotsAgainstLevelMap;
    private String parkingStructureName;

    public Map<Level, Map<SpotType, List<Spot>>> getSpotsAgainstLevelMap() {
        return spotsAgainstLevelMap;
    }
}
