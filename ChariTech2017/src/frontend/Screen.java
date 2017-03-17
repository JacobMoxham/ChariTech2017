package frontend;

import java.util.List;

import com.sun.javafx.applet.Splash;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Created by pighe on 17/03/2017.
 */
public abstract class Screen {
    private Scene mScene;
    private Main mParent;

    public Screen(Main parent){
        this.mParent = parent;
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
    
    protected GridPane drawMetricButtons(List<Metric> metrics, String title){
    	GridPane gridPane = new GridPane();
    	RowConstraints titleRow = new RowConstraints();
    	titleRow.setPercentHeight(0.2);
    	RowConstraints metricsRow = new RowConstraints();
    	titleRow.setPercentHeight(0.8);
    	
    	//Add title
    	Text titleText = new Text();
    	titleText.setText(title);
    	gridPane.add(titleText, 0, 0);
    	
    	//Calculate measure of how good each metric is compared to the others
    	double total=0;
    	for (Metric metric: metrics){
    		total+= metric.getPrimaryData();
    	}
    	
    	//Draw metric buttons
    	int i = 0;
    	for (Metric metric: metrics){
    		//TODO: fine tune button sizes
    		CircleButton metBut = new CircleButton(metric.getName() + Double.toString(metric.getPrimaryData()), 200 + (metric.getPrimaryData()/total)*100);
    		//Handle clicks
    		if (metric.getHasDateData()){
    			metBut.setOnAction(e -> createSingleMetricScreen(metric.getDataByMonth(), metric.getName()));
    		}
    		gridPane.add(metBut, i, 1);
    		i++;
    	}
    	return gridPane;
    	//TODO: make sure this stuff gets drawn
    	
    }
    protected void createSingleMetricScreen(List<Double> list, String name){
    	getParent().pushScreen(new GraphScreen(getParent(), name, list));
    }

    protected abstract void generateScene();

    public void restart(){}
    public void pause(){}
    public void stop(){}
    public void start(){}
}
