package de.bitdroid.adap.model;


import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public final class AreaCache {

	private static final Map<Integer, List<Area>> cachedAreas = new HashMap<>();

	public static Area getCircularArea(Location center, double radius) {
		return cacheAndReturnArea(AreaFactory.createCircularArea(center, radius));
	}


	public static Area getRectangularArea(Location center, double width, double height) {
		return cacheAndReturnArea(AreaFactory.createRectangularArea(center, width, height));
	}


	public static void releaseArea(Area area) {
		if (cachedAreas.get(area.hashCode()) != null) cachedAreas.get(area.hashCode()).remove(area);
	}


	private static Area cacheAndReturnArea(Area area) {
		List<Area> areaList = cachedAreas.get(area.hashCode());
		if (areaList == null) {
			areaList = new LinkedList<>();
			cachedAreas.put(area.hashCode(), areaList);
		}

		int areaIdx = areaList.indexOf(area);
		if (areaIdx != -1) return areaList.get(areaIdx);
		else {
			areaList.add(area);
			return area;
		}
	}


	private AreaCache() { }

}
