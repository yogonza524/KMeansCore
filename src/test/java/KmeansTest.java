/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.core.kmeans.Kmeans;
import com.core.util.Cluster;
import com.core.util.ClusteringType;
import com.core.util.Point;
import com.core.util.Result;
import com.google.gson.Gson;
import java.util.ArrayList;
import org.apache.commons.lang3.ArrayUtils;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author gonza
 */
public class KmeansTest {
    
    public KmeansTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
     @Test
     public void kmeansTest() {
         Kmeans kmeans = new Kmeans(3);
         
         kmeans.addPoint(new Double[]{1.0,1.1,2.0});
         kmeans.addPoint(new Double[]{2.0,2.1,3.0});
         kmeans.addPoint(new Double[]{78.0,1.8,6.0});
         kmeans.addPoint(new Double[]{11.0,1.1,4.0});
         kmeans.addPoint(new Double[]{83.0,14.1,4.0});
         kmeans.addPoint(new Double[]{81.0,12.1,4.0});
         kmeans.addPoint(new Double[]{56.0,23.1,4.0});
         kmeans.addPoint(new Double[]{8.0,1.1,4.0});
         
         
         Result r = kmeans.compute(2, ClusteringType.Forgy);
         
         for(Cluster c : r.getClusters()){
             System.out.println(ArrayUtils.toString(c.getCentroid().getCoords()));
         }
         
         int cluster = r.clasify(new Point(new Double[]{71.0,1.0,3.0}));
         
         System.out.println("Clasiffy: " + cluster);
         
         System.out.println(kmeans.dataToJson());
         
         System.out.println("History");
     }
}
