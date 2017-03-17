package frontend;

import com.sun.javafx.applet.Splash;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * Created by pighe on 17/03/2017.
 */
public class SplashScreen extends Screen {
    public SplashScreen(Stage stage, Main parent){
        super(stage, parent);
    }

    @Override
    protected void generateScene(){
        Button test = new CircleButton("Test button", 100);
        test.setOnAction(e -> testClick());

        StackPane frame = new StackPane();
        frame.getChildren().add(test);
        setScene(new Scene(frame, getParent().getWidth(), getParent().getHeight()));
    }

    public void testClick(){
        System.out.println("CLICKED!");
    }
}
