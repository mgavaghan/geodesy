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

/**
 * This is the outcome of a three dimensional geodetic calculation. It
 * represents the path a between two GlobalPositions for a specified reference
 * ellipsoid.
 * 
 * @author <a href="mailto:mike@gavaghan.org">Mike Gavaghan</a>
 */
public class GeodeticMeasurement extends GeodeticCurve
{
	/**
	 * The elevation change, in meters, going from the starting to the ending
	 * point.
	 */
	private final double mElevationChange;

	/** The distance travelled, in meters, going from one point to the next. */
	private final double mP2P;

	/**
	 * Creates a new instance of GeodeticMeasurement.
	 * 
	 * @param ellipsoidalDistance
	 *            ellipsoidal distance in meters
	 * @param azimuth
	 *            azimuth in degrees
	 * @param reverseAzimuth
	 *            reverse azimuth in degrees
	 * @param elevationChange
	 *            the change in elevation, in meters, going from the starting
	 *            point to the ending point
	 */
	public GeodeticMeasurement(double ellipsoidalDistance, double azimuth, double reverseAzimuth, double elevationChange)
	{
		super(ellipsoidalDistance, azimuth, reverseAzimuth);
		mElevationChange = elevationChange;
		mP2P = Math.sqrt(ellipsoidalDistance * ellipsoidalDistance + mElevationChange * mElevationChange);
	}

	/**
	 * Creates a new instance of GeodeticMeasurement.
	 * 
	 * @param averageCurve
	 *            average geodetic curve
	 * @param elevationChange
	 *            the change in elevation, in meters, going from the starting
	 *            point to the ending point
	 */
	public GeodeticMeasurement(GeodeticCurve averageCurve, double elevationChange)
	{
		this(averageCurve.getEllipsoidalDistance(), averageCurve.getAzimuth(), averageCurve.getReverseAzimuth(), elevationChange);
	}

	/**
	 * Get the elevation change.
	 * 
	 * @return elevation change, in meters, going from the starting to the
	 *         ending point
	 */
	public double getElevationChange()
	{
		return mElevationChange;
	}

	/**
	 * Get the point-to-point distance.
	 * 
	 * @return the distance travelled, in meters, going from one point to the
	 *         next
	 */
	public double getPointToPointDistance()
	{
		return mP2P;
	}

	/**
	 * Get the GeodeticMeasurement as a string.
	 */
	@Override
	public String toString()
	{
		StringBuffer buffer = new StringBuffer();

		buffer.append(super.toString());
		buffer.append("elev12=");
		buffer.append(mElevationChange);
		buffer.append(";p2p=");
		buffer.append(mP2P);

		return buffer.toString();
	}

}
