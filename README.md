# KMeansCore
A simple Kmeans algorith implementation for Java

## Test
```java
     @Test
     public void kmeansTest() {
         Kmeans kmeans = new Kmeans(3); //Create a K-Means machine with dimension 3
         
         kmeans.addPoint(new Double[]{1.0,1.1,2.0});
         kmeans.addPoint(new Double[]{2.0,2.1,3.0});
         kmeans.addPoint(new Double[]{78.0,1.8,6.0});
         kmeans.addPoint(new Double[]{11.0,1.1,4.0});
         kmeans.addPoint(new Double[]{83.0,14.1,4.0});
         kmeans.addPoint(new Double[]{81.0,12.1,4.0});
         kmeans.addPoint(new Double[]{56.0,23.1,4.0});
         kmeans.addPoint(new Double[]{8.0,1.1,4.0});
         
         
         Result r = kmeans.compute(2, ClusteringType.Forgy); //Compute results
         
         for(Cluster c : r.getClusters()){
             System.out.println(ArrayUtils.toString(c.getCentroid().getCoords())); //Show clusters
         }
         
         int cluster = r.clasify(new Point(new Double[]{71.0,1.0,3.0})); //Classify a point
         
         System.out.println("Clasiffy: " + cluster); //index of cluster classification
         
         System.out.println(kmeans.dataToJson()); //Show as JSON data (to send)
     }
```
## Result
```java
Running KmeansTest
{5.5,1.3499999,3.25}
{74.5,12.775,4.5}
Clasiffy: 1
[{"coords":[1.0,1.1,2.0]},{"coords":[2.0,2.1,3.0]},{"coords":[78.0,1.8,6.0]},{"coords":[11.0,1.1,4.0]},{"coords":[83.0,14.1,4.0]},{"coords":[81.0,12.1,4.0]},{"coords":[56.0,23.1,4.0]},{"coords":[8.0,1.1,4.0]}]

```
![Image](https://www.mathworks.com/matlabcentral/mlc-downloads/downloads/submissions/26182/versions/11/screenshot.jpg)
