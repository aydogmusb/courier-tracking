
Spring Boot project for Courier Tracking System

This project is a restful web application with Java, that mainly takes streaming geolocations of couriers info as input. 

•	Log courier and store when any courier enters radius of 100 meters from Migros stores. Reentries to the same store's circumference over 1 minute should not count as "entrance". Store locations are given as stores.json file.


•	The application must provide a way for querying total distances, over which any courier travels.


### POST Request
**URL:**
``` http://localhost:8080/info```
```json
{
   "form": {
         "40.9923307",
         "29.1244229",
         "1",
         "2020-10-21'T'13:00"
     }
}
```
