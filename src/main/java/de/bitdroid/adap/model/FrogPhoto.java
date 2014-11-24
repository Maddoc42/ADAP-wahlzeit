package de.bitdroid.adap.model;


import org.wahlzeit.model.Photo;
import org.wahlzeit.model.PhotoId;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public final class FrogPhoto extends Photo {

	public static final String FROG = "frog";

	private FrogType frogType;

	public FrogPhoto() { }


	public FrogPhoto(PhotoId myId) {
		super(myId);
	}


	public FrogPhoto(ResultSet rset) throws SQLException {
		super(rset);
	}


	public void readFrom(ResultSet rset) throws SQLException {
		super.readFrom(rset);
		String frogString = rset.getString("frog");
		try {
			if (frogString != null) frogType = objectMapper.treeToValue(objectMapper.readTree(frogString), FrogType.class);
		} catch (IOException ioe) {
			throw new SQLException(ioe);
		}
	}


	public void writeOn(ResultSet rset) throws SQLException {
		super.writeOn(rset);
		if (hasFrog()) rset.updateString("frog", objectMapper.valueToTree(frogType).toString());
	}


	public FrogType getFrogType() {
		return frogType;
	}


	public void setFrogType(FrogType frogType) {
		this.frogType = frogType;
		incWriteCount();
	}


	public boolean hasFrog() {
		return frogType != null;
	}



}
