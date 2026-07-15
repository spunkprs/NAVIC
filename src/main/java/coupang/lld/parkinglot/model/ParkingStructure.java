package coupang.lld.parkinglot.model;

import java.util.List;
import java.util.TreeMap;

public class ParkingStructure {
    private String parkingStructureId;
    private TreeMap<Level, List<Spot>> spotsAgainstLevelMap;
    private String parkingStructureName;
}
