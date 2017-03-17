package frontend;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import datastatistics.Statistic;
import datastatistics.Tutor;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.application.Application;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;
 

public class GraphScreen extends Screen{
	private String mName;
	private List<Double> mData;
    public GraphScreen(Main parent, String name, List<Double> data){
        super(parent);
        mName = name;
        mData = data;
        generateScene();
    }

    protected void generateScene(){
        //defining the axes
        final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();
        
        xAxis.setLabel("Number of Month");
        //creating the chart
        final LineChart<Number,Number> lineChart = 
                new LineChart<Number,Number>(xAxis,yAxis);
                
        lineChart.setTitle(mName);
        //defining a series
        XYChart.Series series = new XYChart.Series();
        //populating the series with data
        int i = 1;
        double max = 0;
        for(Double d : mData){
        	max = Math.max(d,  max);
        	System.out.println(i + " " + d);
        	series.getData().add(new XYChart.Data(i * 10, d / 1000.0));
        	i++;
        }
        lineChart.maxHeight(max);
        lineChart.maxWidth(3);
        setScene(new Scene(lineChart, getParent().getWidth(), getParent().getHeight()));
        
    }
}
