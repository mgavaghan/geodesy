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

import junit.framework.TestCase;

public class GeodeticCalculatorTest extends TestCase
{
   public void testCalculateGeodeticCurve()
   {
      // instantiate the calculator
      GeodeticCalculator geoCalc = new GeodeticCalculator();

      // select a reference elllipsoid
      Ellipsoid reference = Ellipsoid.WGS84;

      // set Lincoln Memorial coordinates
      GlobalCoordinates lincolnMemorial;
      lincolnMemorial = new GlobalCoordinates(38.88922, -77.04978);

      // set Eiffel Tower coordinates
      GlobalCoordinates eiffelTower;
      eiffelTower = new GlobalCoordinates(48.85889, 2.29583);

      // calculate the geodetic curve
      GeodeticCurve geoCurve = geoCalc.calculateGeodeticCurve(reference, lincolnMemorial, eiffelTower);

      assertEquals(6179016.136, geoCurve.getEllipsoidalDistance(), 0.001);
      assertEquals(51.76792142, geoCurve.getAzimuth(), 0.0000001);
      assertEquals(291.75529334, geoCurve.getReverseAzimuth(), 0.0000001);
   }

   public void testCalculateGeodeticMeasurement()
   {
      // instantiate the calculator
      GeodeticCalculator geoCalc = new GeodeticCalculator();

      // select a reference elllipsoid
      Ellipsoid reference = Ellipsoid.WGS84;

      // set Pike's Peak position
      GlobalPosition pikesPeak;
      pikesPeak = new GlobalPosition(38.840511, -105.0445896, 4301.0);

      // set Alcatraz Island coordinates
      GlobalPosition alcatrazIsland;
      alcatrazIsland = new GlobalPosition(37.826389, -122.4225, 0.0);

      // calculate the geodetic measurement
      GeodeticMeasurement geoMeasurement;

      geoMeasurement = geoCalc.calculateGeodeticMeasurement(reference, pikesPeak, alcatrazIsland);

      assertEquals(   -4301.0, geoMeasurement.getElevationChange(), 0.001);
      assertEquals( 1521788.826, geoMeasurement.getPointToPointDistance(), 0.001);
      assertEquals( 1521782.748, geoMeasurement.getEllipsoidalDistance(), 0.001);
      assertEquals(271.21039153, geoMeasurement.getAzimuth(), 0.0000001);
      assertEquals( 80.38029386, geoMeasurement.getReverseAzimuth(), 0.0000001);
   }

   public void testAntiPodal1()
   {
      // instantiate the calculator
      GeodeticCalculator geoCalc = new GeodeticCalculator();

      // select a reference elllipsoid
      Ellipsoid reference = Ellipsoid.WGS84;

      // set position 1
      GlobalCoordinates p1;
      p1 = new GlobalCoordinates(10, 80.6);

      // set position 2
      GlobalCoordinates p2;
      p2 = new GlobalCoordinates(-10, -100);

      // calculate the geodetic measurement
      GeodeticCurve geoCurve;

      geoCurve = geoCalc.calculateGeodeticCurve(reference, p1, p2);

      assertEquals( 19970718.422432076, geoCurve.getEllipsoidalDistance(), 0.001);
      assertEquals(90.0004877491174, geoCurve.getAzimuth(), 0.0000001);
      assertEquals(270.0004877491174, geoCurve.getReverseAzimuth(), 0.0000001);
   }

   public void testAntiPodal2()
   {
      // instantiate the calculator
      GeodeticCalculator geoCalc = new GeodeticCalculator();

      // select a reference elllipsoid
      Ellipsoid reference = Ellipsoid.WGS84;

      // set position 1
      GlobalCoordinates p1;
      p1 = new GlobalCoordinates(11, 80);

      // set position 2
      GlobalCoordinates p2;
      p2 = new GlobalCoordinates(-10, -100);

      // calculate the geodetic measurement
      GeodeticCurve geoCurve;

      geoCurve = geoCalc.calculateGeodeticCurve(reference, p1, p2);

      assertEquals( 19893320.272061437, geoCurve.getEllipsoidalDistance(), 0.001);
      assertEquals(360.0, geoCurve.getAzimuth(), 0.0000001);
      assertEquals(0.0, geoCurve.getReverseAzimuth(), 0.0000001);
   }

   public void testInverseWithDirect()
   {
      // instantiate the calculator
      GeodeticCalculator geoCalc = new GeodeticCalculator();

      // select a reference elllipsoid
      Ellipsoid reference = Ellipsoid.WGS84;

      // set Lincoln Memorial coordinates
      GlobalCoordinates lincolnMemorial;
      lincolnMemorial = new GlobalCoordinates(38.88922, -77.04978);

      // set Eiffel Tower coordinates
      GlobalCoordinates eiffelTower;
      eiffelTower = new GlobalCoordinates(48.85889, 2.29583);

      // calculate the geodetic curve
      GeodeticCurve geoCurve = geoCalc.calculateGeodeticCurve(reference, lincolnMemorial, eiffelTower);

      // now, plug the result into to direct solution
      GlobalCoordinates dest;
      double[] endBearing = new double[1];
      
      dest = geoCalc.calculateEndingGlobalCoordinates(reference, lincolnMemorial, geoCurve.getAzimuth(), geoCurve.getEllipsoidalDistance(), endBearing);
      
      assertEquals( eiffelTower.getLatitude(), dest.getLatitude(), 0.0000001 );
      assertEquals( eiffelTower.getLongitude(), dest.getLongitude(), 0.0000001 );
   }
   
   public void testPoleCrossing()
   {
     // instantiate the calculator
     GeodeticCalculator geoCalc = new GeodeticCalculator();

     // select a reference elllipsoid
     Ellipsoid reference = Ellipsoid.WGS84;

     // set Lincoln Memorial coordinates
     GlobalCoordinates lincolnMemorial;
     lincolnMemorial = new GlobalCoordinates(38.88922, -77.04978);

     // set a bearing of 1.0deg (almost straight up) and a distance
     double startBearing = 1.0;
     double distance = 6179016.13586;

     // set the expected destination
     GlobalCoordinates expected;
     expected = new GlobalCoordinates(85.60006433, 92.17243943);

     // calculate the ending global coordinates
     GlobalCoordinates dest = geoCalc.calculateEndingGlobalCoordinates(reference, lincolnMemorial, startBearing, distance );

     assertEquals(expected.getLatitude(), dest.getLatitude(), 0.0000001);
     assertEquals(expected.getLongitude(), dest.getLongitude(), 0.0000001);
   }
}
