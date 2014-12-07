package de.bitdroid.adap.model;


import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public final class AreaCache {

	private static AreaCache instance;

	public static synchronized AreaCache getInstance() {
		if (instance == null) instance = new AreaCache(new AreaFactory());
		return instance;
	}


	private final Map<Integer, List<Area>> cachedAreas = new HashMap<>();
	private final AreaFactory areaFactory;


	public AreaCache(AreaFactory areaFactory) {
		this.areaFactory = areaFactory;
	}


	public Area getCircularArea(Location center, double radius) {
		return cacheAndReturnArea(areaFactory.createCircularArea(center, radius));
	}


	public Area getRectangularArea(Location center, double width, double height) {
		return cacheAndReturnArea(areaFactory.createRectangularArea(center, width, height));
	}


	public void releaseArea(Area area) {
		if (cachedAreas.get(area.hashCode()) != null) cachedAreas.get(area.hashCode()).remove(area);
	}


	private Area cacheAndReturnArea(Area area) {
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

}
