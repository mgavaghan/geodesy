# Java Geodesy Library for GPS – Vincenty’s Formulae

BitCoin tips graciously accepted at `1FB63FYQMy7hpC2ANVhZ5mSgAZEtY1aVLf`

Looking for the C# version of the Geodesy library? [Click here.](http://www.gavaghan.org/blog/2007/08/06/c-gps-receivers-and-geocaching-vincentys-formula/)

This will be handy for anyone, particularly [Geocachers](https://www.geocaching.com/), interested in developing software for use with a GPS receiver. The library provides two important functions:

1. Calculate a destination given a starting point, direction, and distance traveled on Earth
1. Calculate the distance between two points on Earth

The fancy schmancy names for these functions are the “direct geodetic problem” and the “the inverse geodetic problem”.

For a deeper discussion of the source code, I recommend reading my blog entry: [Java, GPS Receivers, and Geocaching: Vincenty’s Formula](http://www.gavaghan.org/blog/2007/11/16/java-gps-receivers-and-geocaching-vincentys-formula/).

This is the Java source code for implementations of [Thaddeus Vincenty’s algorithms](http://www.ngs.noaa.gov/PUBS_LIB/inverse.pdf "Vincenty's algorithm") to solve the direct and inverse geodetic problems.

The inputs for the direct solution are the latitude and longitude of a starting point, a starting bearing, a distance to travel, and a reference model of Earth (several are provided). The outputs are the latitude and longitude of the destination and, optionally, the final bearing at the destination.

The inputs for the inverse solution are the latitude and longitude of two points and a reference model of Earth (several are provided). The outputs are 1) the distance between the points along the curve of Earth, and 2) the direction from the starting point to the ending point.

The primary classes in the library are:

**org.gavaghan.geodesy (2-D calculations)**

`Ellipsoid` – an immutable property class representing a model of Earth used for geodetic calculations. Several static instances are included that describe scientifically measured models. WGS84 (the [1984 World Geodetic System](https://en.wikipedia.org/wiki/WGS84)) and GRS80 (the [1980 Geodetic Reference System](https://en.wikipedia.org/wiki/GRS_80)) are the most widely used.

`GlobalCoordinates` – encapsulates a location on Earth as described by two Angles: latitude and longitude.

`GeodeticCurve` – the outcome of an inverse geodetic calculation. It represents the path and ellipsoidal distance between two GlobalCoordinates for a specified reference Ellipsoid.

`GeodeticCalculator` – this is a behavior class that contains the actual implementations of Vincenty’s Formulae.

**org.gavaghan.geodesy (3-D calculations)**

`GlobalPosition` – this is similar to GlobalCoordinates, but it also includes a measurement of elevation, in meters, above or below the reference Ellipsoid.

`GeodeticMeasurement` – this is similar to GeodeticCurve, but it also adds a point-to-point measurement which accounts for a change in elevation between two points in addition to the ellipsoidal distance.

`GeodeticCalculator` – this is the same behavior class described above. It contains methods for both 2-D and 3-D calculations.

**org.gavaghan.geodesy.example**

This is an application project that sets up and computes a direct calculation and a 2-D and 3-D inverse calculation. This is a good starting point for figuring out how to use the library.