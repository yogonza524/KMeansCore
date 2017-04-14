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

public class Cluster {
    private List<Point> points = new ArrayList<Point>();
    private Point centroid;
    private boolean finish = false;

    public Point getCentroid() {
	return centroid;
    }

    public void setCentroide(Point centroide) {
	this.centroid = centroide;
    }

    public List<Point> getPoints() {
	return points;
    }

    public boolean finished() {
	return finish;
    }

    public void setFinish(boolean termino) {
	this.finish = termino;
    }

    public void cleanPoints() {
	points.clear();
    }

    @Override
    public String toString() {
	return centroid.toString();
    }
}
