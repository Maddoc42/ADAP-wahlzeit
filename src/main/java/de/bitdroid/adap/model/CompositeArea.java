package de.bitdroid.adap.model;


import com.google.common.base.Objects;

import java.util.Collection;
import java.util.LinkedList;


final class CompositeArea extends AbstractArea {

	private final Collection<Area> areas;


	private CompositeArea(Location center, Collection<Area> areas) {
		super(center);
		this.areas = areas;
	}


	public Collection<Area> getAreas() {
		return areas;
	}


	@Override
	public boolean equals(Object other) {
		if (other == null || !(other instanceof CompositeArea)) return false;
		CompositeArea area = (CompositeArea) other;
		return Objects.equal(center, area.center) && Objects.equal(areas, area.areas);
	}


	@Override
	public int hashCode() {
		return Objects.hashCode(center, areas);
	}


	@Override
	public String toString() {
		return "(center = " + center.toString() + ", #subAreas = " + areas.size() + ")";
	}


	@Override
	public <P,R> R accept(AreaVisitor<P,R> visitor, P param) {
		return visitor.visit(this, param);
	}


	public static final class Builder {

		private static final GpsLocationAdapter gpsAdapter = new GpsLocationAdapter();

		private final Collection<Area> areas = new LinkedList<>();

		public Builder area(Area area) {
			this.areas.add(area);
			return this;
		}


		public CompositeArea build() {
			double lat = 0, lon = 0;
			for (Area area : areas) {
				GpsLocation areaCenter = area.getCenter().accept(gpsAdapter, null);
				lat += areaCenter.getLatitude();
				lon += areaCenter.getLongitude();
			}

			Location center = new GpsLocation(lat / areas.size(), lon / areas.size());
			return new CompositeArea(center, areas);
		}

	}

}
