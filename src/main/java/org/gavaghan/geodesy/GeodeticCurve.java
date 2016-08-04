/* 
 *  Geodesy by Mike Gavaghan
 * 
 *      http://www.gavaghan.org/blog/free-source-code/geodesy-library-vincentys-formula/
 * 
 *  Copyright 2007 Mike Gavaghan - mike@gavaghan.org
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

/*
 * BitCoin tips graciously accepted at 1FB63FYQMy7hpC2ANVhZ5mSgAZEtY1aVLf
 */
package org.gavaghan.geodesy;

import java.io.Serializable;

/**
 * This is the outcome of a geodetic calculation. It represents the path and
 * ellipsoidal distance between two GlobalCoordinates for a specified reference
 * ellipsoid.
 * 
 * @author <a href="mailto:mike@gavaghan.org">Mike Gavaghan</a>
 */
public class GeodeticCurve implements Serializable
{
	/** Ellipsoidal distance (in meters). */
	private final double mEllipsoidalDistance;

	/** Azimuth (degrees from north). */
	private final double mAzimuth;

	/** Reverse azimuth (degrees from north). */
	private final double mReverseAzimuth;

	/**
	 * Create a new GeodeticCurve.
	 * 
	 * @param ellipsoidalDistance
	 *            ellipsoidal distance in meters
	 * @param azimuth
	 *            azimuth in degrees
	 * @param reverseAzimuth
	 *            reverse azimuth in degrees
	 */
	public GeodeticCurve(double ellipsoidalDistance, double azimuth, double reverseAzimuth)
	{
		mEllipsoidalDistance = ellipsoidalDistance;
		mAzimuth = azimuth;
		mReverseAzimuth = reverseAzimuth;
	}

	/**
	 * Get the ellipsoidal distance.
	 * 
	 * @return ellipsoidal distance in meters
	 */
	public double getEllipsoidalDistance()
	{
		return mEllipsoidalDistance;
	}

	/**
	 * Get the azimuth.
	 * 
	 * @return azimuth in degrees
	 */
	public double getAzimuth()
	{
		return mAzimuth;
	}

	/**
	 * Get the reverse azimuth.
	 * 
	 * @return reverse azimuth in degrees
	 */
	public double getReverseAzimuth()
	{
		return mReverseAzimuth;
	}

	/**
	 * Get curve as a string.
	 * 
	 * @return string representation of the curve
	 */
	@Override
	public String toString()
	{
		StringBuffer buffer = new StringBuffer();

		buffer.append("s=");
		buffer.append(mEllipsoidalDistance);
		buffer.append(";a12=");
		buffer.append(mAzimuth);
		buffer.append(";a21=");
		buffer.append(mReverseAzimuth);
		buffer.append(";");

		return buffer.toString();
	}
}
