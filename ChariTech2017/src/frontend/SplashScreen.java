package frontend;

import com.sun.javafx.applet.Splash;
import dataHandling.Centre;
import dataHandling.DataInput;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;

/**
 * Created by pighe on 17/03/2017.
 */
public class SplashScreen extends Screen {
    public SplashScreen(Main parent){
        super(parent);
    }

    Map<CircleButton, Integer> button_ids;

    @Override
    protected void generateScene(){
        StackPane frame = new StackPane();

        List<Centre> centres = null;
        try {
            centres = DataInput.getCentreList();
        }catch(FileNotFoundException e){
            System.out.println("File not found: " + e.getMessage());
        }

        for(Centre centre : centres){
            Button button = new CircleButton("Centre " + centre.getCentreID(), 100);
            button.setOnAction(e -> centreClicked(centre.getCentreID()));
            frame.getChildren().add(button);
        }

        setScene(new Scene(frame, getParent().getWidth(), getParent().getHeight()));

    }

    public void centreClicked(int id){
        getParent().pushScreen(new CentreScreen(getParent(), id));
    }
}
