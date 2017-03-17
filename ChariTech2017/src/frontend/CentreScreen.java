package frontend;

import dataHandling.Centre;
import dataHandling.DataInput;
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
    public CentreScreen(Main parent, int id){
        super(parent);
        mCentreId = id;
    }

    @Override
    protected void generateScene(){
        StackPane frame = new StackPane();

        List<DataCategory> categories = Arrays.asList(DataCategory.values());

        for(DataCategory category : categories){
            Button button = new CircleButton(category.toString(), 100);
            button.setOnAction(e -> buttonClicked(category));
            frame.getChildren().add(button);
        }

        setScene(new Scene(frame, getParent().getWidth(), getParent().getHeight()));

    }

    public void buttonClicked(DataCategory category){
        switch(category) {
            case ADMIN:
                getParent().pushScreen(new AdminScreen());

                //todo: rest...
        }
    }
}
