/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.core.kmeans;

/**
 * 
 * @author Gonzalo H. Mendoza
 * email: yogonza524@gmail.com
 * StackOverflow: http://stackoverflow.com/users/5079517/gonza
 */
import com.core.util.Cluster;
import com.core.util.ClusteringType;
import com.core.util.Result;
import com.core.util.Point;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Kmeans {
    
    List<Point> points;
    private int dimension;

    public Kmeans(int dimension) {
        this.dimension = dimension;
        this.points = new ArrayList<>();
    }
    
    
    
    public Result compute(Integer k, ClusteringType type) {
        ArrayList<ArrayList<Cluster>> historialCluster = new ArrayList<>();
        ArrayList<Cluster> clusters = initCentroids(points, k, type);
        
        historialCluster.add((ArrayList<Cluster>) clusters.clone());
	while (!finalizo(clusters)) {
	    prepare(clusters);
	    assign(points, clusters);
	    computeClusters(clusters);
            historialCluster.add((ArrayList<Cluster>) clusters.clone());
	}

	Double ofv = computeObjetiveFunction(clusters);
        Result resultKMeans = new Result(clusters, ofv, k);
        resultKMeans.setHistorialCluster(historialCluster);
        return resultKMeans;
    }

    private void computeClusters(List<Cluster> clusters) {
	for (Cluster c : clusters) {
	    if (c.getPoints().isEmpty()) {
		c.setFinish(true);
		continue;
	    }

	    Float[] d = new Float[c.getPoints().get(0).getGrado()];
	    Arrays.fill(d, 0f);
	    for (Point p : c.getPoints()) {
		for (int i = 0; i < p.getGrado(); i++) {
		    d[i] += (p.get(i) / c.getPoints().size());
		}
	    }

	    Point newCentroid = new Point(d);

	    if (newCentroid.equals(c.getCentroid())) {
		c.setFinish(true);
	    } else {
		c.setCentroide(newCentroid);
	    }
	}
    }

    private void assign(List<Point> puntos, List<Cluster> clusters) {
	for (Point punto : puntos) {
	    Cluster masCercano = clusters.get(0);
	    Double distanciaMinima = Double.MAX_VALUE;
	    for (Cluster cluster : clusters) {
		Double distancia = punto.euclideanDistance(cluster
			.getCentroid());
		if (distanciaMinima > distancia) {
		    distanciaMinima = distancia;
		    masCercano = cluster;
		}
	    }
	    masCercano.getPoints().add(punto);
	}
    }

    private void prepare(List<Cluster> clusters) {
	for (Cluster c : clusters) {
	    c.cleanPoints();
	}
    }

    private Double computeObjetiveFunction(List<Cluster> clusters) {
	Double ofv = 0d;

	for (Cluster cluster : clusters) {
	    for (Point punto : cluster.getPoints()) {
		ofv += punto.euclideanDistance(cluster.getCentroid());
	    }
	}

	return ofv;
    }

    private boolean finalizo(List<Cluster> clusters) {
	for (Cluster cluster : clusters) {
	    if (!cluster.finished()) {
		return false;
	    }
	}
	return true;
    }

    private ArrayList<Cluster> elegirCentroidesAleatoria(List<Point> puntos, Integer k) {
	ArrayList<Cluster> centroides = new ArrayList<Cluster>();

	List<Float> maximos = new ArrayList<Float>();
	List<Float> minimos = new ArrayList<Float>();
	// me fijo máximo y mínimo jsonElementde cada dimensión

	for (int i = 0; i < puntos.get(0).getGrado(); i++) {
	    Float min = Float.POSITIVE_INFINITY, max = Float.NEGATIVE_INFINITY;

	    for (Point punto : puntos) {
		min = min > punto.get(i) ? punto.get(0) : min;
		max = max < punto.get(i) ? punto.get(i) : max;
	    }

	    maximos.add(max);
	    minimos.add(min);
	}

	Random random = new Random();

	for (int i = 0; i < k; i++) {
	    Float[] data = new Float[puntos.get(0).getGrado()];
	    Arrays.fill(data, 0f);
	    for (int d = 0; d < puntos.get(0).getGrado(); d++) {
		data[d] = random.nextFloat()
			* (maximos.get(d) - minimos.get(d)) + minimos.get(d);
	    }

	    Cluster c = new Cluster();
	    Point centroide = new Point(data);
	    c.setCentroide(centroide);
	    centroides.add(c);
	}

	return centroides;
    }

    private ArrayList<Cluster> initCentroids(List<Point> points, Integer k, ClusteringType tipoInicio) {
        if (tipoInicio.equals(ClusteringType.Random)) {
            return elegirCentroidesAleatoria(points, k);
        } else {
            return elegirCentroidesForgy(points, k);
        }
    }

    private ArrayList<Cluster> elegirCentroidesForgy(List<Point> puntos, Integer cantClus) {
        ArrayList<Cluster> cluster =  new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < cantClus; i++) {
            int index = random.nextInt(puntos.size());
	    Float[] data = puntos.get(index).getCoords();
            
	    Cluster c = new Cluster();
	    Point centroide = new Point(data);
	    c.setCentroide(centroide);
	    cluster.add(c);
	}
//        System.out.println("Calculo con forgy");
        return cluster;
    }

    public void setPoints(ArrayList<ArrayList<Double>> points) {
        this.points = new ArrayList<>();
        //Crear los puntos para el algoritmo Kmeans
	for (int i = 0; i < points.size(); i++) {
	    if (points.get(i).size() != dimension) {
                throw new IllegalArgumentException("Point must have dimension of " + dimension);
            }
            else{
                Point p = new Point(points.get(i));
                this.points.add(p);
            }
	}
    }

    public void addPoint(Double[] point) {
        if (point.length != dimension) {
            
        }
        
        Float[] p = new Float[point.length];
        
        for (int i = 0; i < p.length; i++) {
            p[i] = new Float(point[i]);
        }
        
        points.add(new Point(p));
    }
    
    public String dataToJson(){
        return new Gson().toJson(points);
    }
}
