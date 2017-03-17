package frontend;

import com.sun.javafx.applet.Splash;
import dataHandling.Centre;
import dataHandling.DataInput;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.ColumnConstraintsBuilder;
import javafx.scene.layout.GridPane;
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
        
        List<Centre> centres = null;
        try {
            centres = DataInput.getCentreList();
        }catch(FileNotFoundException e){
            System.out.println("File not found: " + e.getMessage());
        }
        final GridPane grid = new GridPane();
        grid.getColumnConstraints().setAll(
                ColumnConstraintsBuilder.create().percentWidth(100/3.0).build(),
                ColumnConstraintsBuilder.create().percentWidth(100/3.0).build(),
                ColumnConstraintsBuilder.create().percentWidth(100/3.0).build()
        );
        grid.add(new Button("One"),0,0);
        grid.add(new Button("Two"),1,0);
        grid.add(new Button("and three"),2,0);

        
       
        int i =0;
        for(Centre centre : centres){
            Button button = new CircleButton("Centre " + centre.getCentreID(), 100);
            button.setOnAction(e -> centreClicked(centre.getCentreID()));
            grid.add(button,i,0);
            i++;
        }

        setScene(new Scene(grid, getParent().getWidth(), getParent().getHeight()));

    }

    public void centreClicked(int id){
        getParent().pushScreen(new CentreScreen(getParent(), id));
    }
}
