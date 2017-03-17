package frontend;

import dataHandling.Centre;
import dataHandling.DataInput;
import datastatistics.Tutor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
    }

    @Override
    protected void generateScene(){
        StackPane frame = new StackPane();

        List<MetricType> categories = Arrays.asList(MetricType.values());

        int start = 400;
        for(MetricType category : categories){
            Button button = new CircleButton(category.toString(), 100);
            button.setOnAction(e -> buttonClicked(category));
            frame.getChildren().add(button);
        }

        setScene(new Scene(frame, getParent().getWidth(), getParent().getHeight()));
    }

    public void buttonClicked(MetricType category){
        getParent().pushScreen(new MetricScreen(getParent(), mTutor, category));
    }
}
