package de.bitdroid.adap.model;


import org.wahlzeit.model.PhotoFactory;
import org.wahlzeit.model.PhotoId;

import java.sql.ResultSet;
import java.sql.SQLException;

public final class FrogPhotoFactory extends PhotoFactory {

	private static final FrogPhotoFactory instance = new FrogPhotoFactory();

	public static FrogPhotoFactory getInstance() {
		return instance;
	}


	@Override
	protected FrogPhoto doCreatePhoto() {
		return new FrogPhoto();
	}


	@Override
	protected FrogPhoto doCreatePhoto(PhotoId id) {
		return new FrogPhoto(id);
	}


	@Override
	protected FrogPhoto doCreatePhoto(ResultSet rs) throws SQLException {
		return new FrogPhoto(rs);
	}

}
