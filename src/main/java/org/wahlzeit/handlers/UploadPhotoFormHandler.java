/*
 * Copyright (c) 2006-2009 by Dirk Riehle, http://dirkriehle.com
 *
 * This file is part of the Wahlzeit photo rating application.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public
 * License along with this program. If not, see
 * <http://www.gnu.org/licenses/>.
 */

package org.wahlzeit.handlers;

import org.wahlzeit.model.AccessRights;
import org.wahlzeit.model.Photo;
import org.wahlzeit.model.PhotoManager;
import org.wahlzeit.model.Tags;
import org.wahlzeit.model.User;
import org.wahlzeit.model.UserLog;
import org.wahlzeit.model.UserSession;
import org.wahlzeit.services.SysConfig;
import org.wahlzeit.services.SysLog;
import org.wahlzeit.utils.StringUtil;
import org.wahlzeit.webparts.WebPart;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import de.bitdroid.adap.model.FrogFactory;
import de.bitdroid.adap.model.FrogPhoto;
import de.bitdroid.adap.model.FrogType;
import de.bitdroid.adap.model.FrogTypeFactory;
import de.bitdroid.adap.model.LocationFactory;

/**
 * 
 * @author dirkriehle
 *
 */
public class UploadPhotoFormHandler extends AbstractWebFormHandler {
	
	/**
	 *
	 */
	public UploadPhotoFormHandler() {
		initialize(PartUtil.UPLOAD_PHOTO_FORM_FILE, AccessRights.USER);
	}
	
	/**
	 * 
	 */
	protected void doMakeWebPart(UserSession us, WebPart part) {
		Map<String, Object> args = us.getSavedArgs();
		part.addStringFromArgs(args, UserSession.MESSAGE);

		// show all possible frog types in drow down menu
		StringBuilder builder = new StringBuilder();
		Collection<FrogType> frogTypes = FrogTypeFactory.getAllFrogTypes();
		List<String> frogCommonNames = new LinkedList<>();
		for (FrogType type : frogTypes) frogCommonNames.add(type.getCommonName());
		Collections.sort(frogCommonNames);
		for (String commonName : frogCommonNames) {
			builder.append("<option value=\"");
			builder.append(commonName);
			builder.append("\">");
			builder.append(commonName);
			builder.append("</option>\n");
		}
		part.addString("frogOptions", builder.toString());

		part.maskAndAddStringFromArgs(args, Photo.TAGS);
	}
	
	/**
	 * 
	 */
	protected String doHandlePost(UserSession us, Map args) {
		String tags = us.getAndSaveAsString(args, Photo.TAGS);
		String latString = us.getAndSaveAsString(args, Photo.LATITUDE);
		String lonString = us.getAndSaveAsString(args, Photo.LONGITUDE);
		String frogString = us.getAndSaveAsString(args, FrogPhoto.FROG);
		String frogName = us.getAndSaveAsString(args, FrogPhoto.NAME);
		String frogAge = us.getAndSaveAsString(args, FrogPhoto.AGE);

		if (!StringUtil.isLegalTagsString(tags)) {
			us.setMessage(us.cfg().getInputIsInvalid());
			return PartUtil.UPLOAD_PHOTO_PAGE_NAME;
		}

		try {
			PhotoManager pm = PhotoManager.getInstance();
			String sourceFileName = us.getAsString(args, "fileName");
			File file = new File(sourceFileName);
			Photo photo = pm.createPhoto(file);

			String targetFileName = SysConfig.getBackupDir().asString() + photo.getId().asString();
			createBackup(sourceFileName, targetFileName);
		
			User user = (User) us.getClient();
			user.addPhoto(photo); 
			
			photo.setTags(new Tags(tags));
			if (latString != null && lonString != null) {
				try {
					double lat = Double.valueOf(latString);
					double lon = Double.valueOf(lonString);
					photo.setLocation(LocationFactory.createGpsLocation(lat, lon));
				} catch (Exception e) {
					SysLog.logThrowable(e);
				}
			}

			if (frogString != null && photo instanceof FrogPhoto) {
				FrogPhoto frogPhoto = (FrogPhoto) photo;
				frogPhoto.setFrog(FrogFactory.createFrog(
						frogName,
						Double.valueOf(frogAge),
						FrogTypeFactory.getFrogTypeByCommonName(frogString)));
			}

			pm.savePhoto(photo);

			StringBuffer sb = UserLog.createActionEntry("UploadPhoto");
			UserLog.addCreatedObject(sb, "Photo", photo.getId().asString());
			UserLog.log(sb);
			us.setTwoLineMessage(us.cfg().getPhotoUploadSucceeded(), us.cfg().getKeepGoing());
		} catch (Exception ex) {
			SysLog.logThrowable(ex);
			us.setMessage(us.cfg().getPhotoUploadFailed());
		}
		
		return PartUtil.UPLOAD_PHOTO_PAGE_NAME;
	}
	
	/**
	 * 
	 */
	protected void createBackup(String sourceName, String targetName) {
		try {
			File sourceFile = new File(sourceName);
			InputStream inputStream = new FileInputStream(sourceFile);
			File targetFile = new File(targetName);
			OutputStream outputStream = new FileOutputStream(targetFile);
			// @FIXME IO.copy(inputStream, outputStream);
		} catch (Exception ex) {
			SysLog.logSysInfo("could not create backup file of photo");
			SysLog.logThrowable(ex);			
		}
	}
}
