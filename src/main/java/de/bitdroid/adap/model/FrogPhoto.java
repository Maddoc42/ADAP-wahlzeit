package de.bitdroid.adap.model;


import org.wahlzeit.model.Photo;
import org.wahlzeit.model.PhotoId;
import org.wahlzeit.services.Log;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public final class FrogPhoto extends Photo {

	public static final String FROG = "frog";

	private Frog frog;

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
			if (frogString != null) frog = objectMapper.treeToValue(objectMapper.readTree(frogString), Frog.class);
		} catch (IOException ioe) {
			Log.logError("failed to parse json", ioe.getMessage());
		}
	}


	public void writeOn(ResultSet rset) throws SQLException {
		super.writeOn(rset);
		if (hasFrog()) rset.updateString("frog", objectMapper.valueToTree(frog).toString());
	}


	public Frog getFrog() {
		return frog;
	}


	public void setFrog(Frog frog) {
		this.frog = frog;
		incWriteCount();
	}


	public boolean hasFrog() {
		return frog != null;
	}



}
