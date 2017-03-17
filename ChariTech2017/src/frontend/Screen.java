package frontend;

import com.sun.javafx.applet.Splash;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.stage.Stage;

/**
 * Created by pighe on 17/03/2017.
 */
public abstract class Screen {
    private Scene mScene;
    private Main mParent;

    public Screen(Stage stage, Main parent){
        this.mParent = parent;
        generateScene();
    }

    protected void setScene(Scene scene){
        this.mScene = scene;
    }

    protected Scene getScene(){
        return mScene;
    }

    protected Main getParent(){
        return mParent;
    }
    
    protected void drawMetricButtons(List<Metric> metrics){
    	GridPane gridPane = new GridPane();
    	RowConstraints titleRow = new RowConstraints();
    	titleRow.setPercentHeight(0.2);
    	RowConstraints metricsRow = new RowConstraints();
    	titleRow.setPercentHeight(0.8);
    	
    	//Calculate measure of how good each metric is compared to the others
    	double total=0;
    	for (Metric metric: Metrics){
    		total+= metric.getValue();
    	}
    	
    	//Draw metric buttons
    	int i = 0;
    	for (Metric metric: metrics){
    		//TODO: fine tune button sizes
    		CircleButton metBut = new CircleButton(metric.getValue(), 50 + (metric.getValue()/total)*200);
    		//Handle clicks
    		if (metric.thatBooleanThing){
    			metBut.setOnAction(e -> createMetricScreen(metric.getTimeSensitiveMetrics));
    		}
    		gridPane.add(metBut, i, 1);
    		i++;
    	}
    	//TODO: make sure this stuff gets drawn
    	
    }
    protected void createMetricScreen(List<Metric> timeMetrics){
    	
    }

    protected abstract void generateScene();

    public void restart(){}
    public void pause(){}
    public void stop(){}
    public void start(){}
}
