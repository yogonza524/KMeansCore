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
import java.util.List;

public class Point {
    private final Float[] coords;

    public Point(ArrayList<Double> datos) {
	List<Float> puntos = new ArrayList<Float>();
	for (int i = 0; i < datos.size(); i++) {
            float x = datos.get(i).floatValue();
	    puntos.add(x);
	}
	this.coords = puntos.toArray(new Float[datos.size()]);
    }

    public Point(Float[] data) {
	this.coords = data;
    }
    
    public Point(Double[] data){
        this.coords = new Float[data.length];
        for (int i = 0; i < data.length; i++) {
            this.coords[i] = new Float(data[i]);
        }
    }

    public float get(int dimension) {
	return coords[dimension];
    }

    public int getGrado() {
	return coords.length;
    }

    @Override
    public String toString() {
	StringBuilder sb = new StringBuilder();
	sb.append(coords[0]);
	for (int i = 1; i < coords.length; i++) {
	    sb.append(", ");
	    sb.append(coords[i]);
	}
	return sb.toString();
    }

    public Double euclideanDistance(Point target) {
	Double d = 0d;
	for (int i = 0; i < coords.length; i++) {
	    d += Math.pow(coords[i] - target.get(i), 2);
	}
	return Math.sqrt(d);
    }

    @Override
    public boolean equals(Object obj) {
	Point other = (Point) obj;
	for (int i = 0; i < coords.length; i++) {
	    if (coords[i] != other.get(i)) {
		return false;
	    }
	}
	return true;
    }

    public Float[] getCoords() {
        return coords;
    }
    
}
