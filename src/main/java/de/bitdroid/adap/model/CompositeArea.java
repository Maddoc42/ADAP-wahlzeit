package de.bitdroid.adap.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.common.base.Objects;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;


@JsonDeserialize(builder = CompositeArea.Builder.class)
final class CompositeArea extends AbstractArea implements Iterable<Area> {

	private final Collection<Area> areas;


	private CompositeArea(Location center, Collection<Area> areas) {
		super(center);
		this.areas = areas;
	}


	public Collection<Area> getAreas() {
		return areas;
	}


	@Override
	public Iterator<Area> iterator() {
		return areas.iterator();
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


		@JsonProperty("areas")
		public Builder areas(Collection<Area> areas) {
			this.areas.addAll(areas);
			return this;
		}


		@JsonProperty("center")
		public Builder center(Location center) {
			// does nothing!
			// ugly hack due to Jackson limitation, see this discussion https://github.com/FasterXML/jackson-databind/issues/95
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
