package frontend;

import com.sun.javafx.applet.Splash;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Created by pighe on 17/03/2017.
 */
public abstract class Screen {
    private Scene mScene;
    private Main mParent;

    public Screen(Main parent){
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

    protected abstract void generateScene();

    public void restart(){}
    public void pause(){}
    public void stop(){}
    public void start(){}
}
