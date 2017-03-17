package frontend;

import dataHandling.Centre;
import dataHandling.DataInput;
import datastatistics.Tutor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.ColumnConstraintsBuilder;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;

/**
 * Created by pighe on 17/03/2017.
 */
public class CentreScreen extends Screen{
    private int mCentreId;
    private Tutor mTutor;
    public CentreScreen(Main parent, int id){
        super(parent);
        mCentreId = id;
        try {
            mTutor = new Tutor(id);
        }catch(FileNotFoundException e){
            System.out.println("File not found: " + e.getMessage());
        }
        generateScene();
    }

    @Override
    protected void generateScene(){
        
        List<MetricType> categories = Arrays.asList(MetricType.values());
        
        
        final GridPane grid = new GridPane();
        grid.getColumnConstraints().setAll(
                ColumnConstraintsBuilder.create().percentWidth(100/3.0).build(),
                ColumnConstraintsBuilder.create().percentWidth(100/3.0).build(),
                ColumnConstraintsBuilder.create().percentWidth(100/3.0).build()
        );
        grid.add(new Button("One"),0,0);
        grid.add(new Button("Two"),1,0);
        grid.add(new Button("and three"),2,0);
        int i = 0;
        for(MetricType category : categories){
            Button button = new CircleButton(category.toString(), 100);
            button.setOnAction(e -> buttonClicked(category));
            grid.add(button,i,0);
            i++;
        }

        setScene(new Scene(grid, getParent().getWidth(), getParent().getHeight()));
    }

    public void buttonClicked(MetricType category){
        getParent().pushScreen(new MetricScreen(getParent(), mTutor, category));
    }
}
