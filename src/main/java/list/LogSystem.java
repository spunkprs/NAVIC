package list;

import java.util.ArrayList;
import java.util.List;
import java.util.NavigableMap;
import java.util.TreeMap;

public class LogSystem {
	
	private TreeMap<Long, List<Integer>> map = new TreeMap<Long, List<Integer>>();
	
	public LogSystem() {
        
    }
	
	public void put(int id, String timestamp) {
        String st[] = timestamp.split(":");
        Long convertedNumber = convertTimeStampToNumber(st);
        if (map.containsKey(convertedNumber)) {
        	List<Integer> ids = map.get(convertedNumber);
        	ids.add(id);
        } else {
        	List<Integer> ids = new ArrayList<Integer>();
        	ids.add(id);
        	map.put(convertedNumber, ids);
        }
    }
    
    private Long convertTimeStampToNumber(String[] st) {
		Long number = new Long(0);
		number = (Long.parseLong(st[0]) - Long.parseLong("1999")) * 365 * 24 * 60 * 60 + Long.parseLong(st[1]) * 30 * 24 * 60 * 60 + Long.parseLong(st[2]) * 24 * 60 * 60 + 
				Long.parseLong(st[3]) * 60 * 60 + Long.parseLong(st[4]) * 60 + Long.parseLong(st[5]);
		return number;
	}

	public List<Integer> retrieve(String start, String end, String granularity) {
		List<Integer> resultList = new ArrayList<Integer>();
		String startTimestamp[] = start.split(":");
		String endTimestamp[] = end.split(":");
		Long startTime = convertToTimestampAccordingToGranularity(startTimestamp, granularity, false);
		Long endTime = convertToTimestampAccordingToGranularity(endTimestamp, granularity, true);
		NavigableMap<Long, List<Integer>> navigableMap = map.subMap(startTime, true, endTime, true);
		for (Long number : navigableMap.keySet()) {
			resultList.addAll(map.get(number));
		}
        return resultList;
    }

	private Long convertToTimestampAccordingToGranularity(String[] timestamp, String granularity, boolean flag) {
		Long number = new Long(0);
		if (granularity.equals("Year")) {
			number = (Long.parseLong(timestamp[0]) - Long.parseLong("1999")) * 365 * 24 * 60 * 60;
			if (flag) {
				number = (Long.parseLong(timestamp[0]) - Long.parseLong("1999")) * 365 * 24 * 60 * 60 + Long.parseLong("12") * 30 * 24 * 60 * 60 + Long.parseLong("31") * 24 * 60 * 60 + 
						Long.parseLong("23") * 60 * 60 + Long.parseLong("60") * 60 + Long.parseLong("59");
			}
		} else if (granularity.equals("Month")) {
			number = (Long.parseLong(timestamp[0]) - Long.parseLong("1999")) * 365 * 24 * 60 * 60 + Long.parseLong(timestamp[1]) * 30 * 24 * 60 * 60;
			if (flag) {
				number = (Long.parseLong(timestamp[0]) - Long.parseLong("1999")) * 365 * 24 * 60 * 60 + Long.parseLong(timestamp[1]) * 30 * 24 * 60 * 60 + Long.parseLong("31") * 24 * 60 * 60 + 
						Long.parseLong("23") * 60 * 60 + Long.parseLong("60") * 60 + Long.parseLong("59");
			}
		} else if (granularity.equals("Day")) {
			number = (Long.parseLong(timestamp[0]) - Long.parseLong("1999")) * 365 * 24 * 60 * 60 + Long.parseLong(timestamp[1]) * 30 * 24 * 60 * 60 + Long.parseLong(timestamp[2]) * 24 * 60 * 60;
			if (flag) {
				number = (Long.parseLong(timestamp[0]) - Long.parseLong("1999")) * 365 * 24 * 60 * 60 + Long.parseLong(timestamp[1]) * 30 * 24 * 60 * 60 + Long.parseLong(timestamp[2]) * 24 * 60 * 60 + 
						Long.parseLong("23") * 60 * 60 + Long.parseLong("60") * 60 + Long.parseLong("59");
			}
		} else if (granularity.equals("Hour")) {
			number = (Long.parseLong(timestamp[0]) - Long.parseLong("1999")) * 365 * 24 * 60 * 60 + Long.parseLong(timestamp[1]) * 30 * 24 * 60 * 60 + Long.parseLong(timestamp[2]) * 24 * 60 * 60 + 
					Long.parseLong(timestamp[3]) * 60 * 60;
			if (flag) {
				number += Long.parseLong("59") * 60 + Long.parseLong("59");
			}
		} else if (granularity.equals("Minute")) {
			number = (Long.parseLong(timestamp[0]) - Long.parseLong("1999")) * 365 * 24 * 60 * 60 + Long.parseLong(timestamp[1]) * 30 * 24 * 60 * 60 + Long.parseLong(timestamp[2]) * 24 * 60 * 60 + 
					Long.parseLong(timestamp[3]) * 60 * 60 + Long.parseLong(timestamp[4]) * 60;
			if (flag) {
				number += Long.parseLong("59");
			}
		} else {
			number = (Long.parseLong(timestamp[0]) - Long.parseLong("1999")) * 365 * 24 * 60 * 60 + Long.parseLong(timestamp[1]) * 30 * 24 * 60 * 60 + Long.parseLong(timestamp[2]) * 24 * 60 * 60 + 
					Long.parseLong(timestamp[3]) * 60 * 60 + Long.parseLong(timestamp[4]) * 60 + Long.parseLong(timestamp[5]);
		}
		return number;
	}

}
