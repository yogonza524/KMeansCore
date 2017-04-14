/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.core.util;

/**
 * 
 * @author Gonzalo H. Mendoza
 * email: yogonza524@gmail.com
 * StackOverflow: http://stackoverflow.com/users/5079517/gonza
 */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Result {
    private List<Cluster> clusters = new ArrayList<Cluster>();
    private Double ofv;
    private int k;
    private ArrayList<ArrayList<Cluster>> historialCluster;

    public Result(List<Cluster> clusters, Double ofv, int numOfClusters) {
	super();
	this.ofv = ofv;
	this.clusters = clusters;
        this.k = numOfClusters;
    }

    public List<Cluster> getClusters() {
	return clusters;
    }

    public Double getOfv() {
	return ofv;
    }

    public int getCantCluster() {
        return k;
    }

    private ArrayList<ArrayList<Cluster>> getHistorialCluster() {
        return historialCluster;
    }

    public void setHistorialCluster(ArrayList<ArrayList<Cluster>> historialCluster) {
        this.historialCluster = historialCluster;
    }

    public String ClasificarPuntos(List<Point> points) {
        String salida = "";
        double distancia;
        int numCluster = -1;
        for (int i = 0; i < points.size(); i++) {
            distancia = Double.POSITIVE_INFINITY;
            for (int j = 0; j < k; j++) {
                if (distancia > points.get(i).euclideanDistance(clusters.get(j).getCentroid())) {
                    distancia = points.get(i).euclideanDistance(clusters.get(j).getCentroid());
                    numCluster = j;
                }
            }
            salida = salida + "Punto: " + Arrays.toString(points.get(i).getCoords()) + " corresponde al cluster: " + numCluster + "\n";
        }
        return salida;
    }
    
    public int clasify(Point p){
        int result = -1;
        double distancia = Double.POSITIVE_INFINITY;
        for (int j = 0; j < k; j++) {
            if (distancia > p.euclideanDistance(clusters.get(j).getCentroid())) {
                distancia =p.euclideanDistance(clusters.get(j).getCentroid());
                result = j;
            }
        }
        return result;
    }

}
