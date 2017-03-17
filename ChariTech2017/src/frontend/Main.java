package frontend;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.util.Stack;

public class Main extends Application {
    private Stack<Screen> mScreens;
    private Stage mStage;
    private int mWidth;
    private int mHeight;

    public int getWidth(){
        return mWidth;
    }

    public int getHeight(){
        return mHeight;
    }

    public void pushScreen(Screen screen){
        if(!mScreens.isEmpty()){
            mScreens.peek().pause();
        }
        mScreens.push(screen);
        screen.start();

        mStage.setScene(screen.getScene());
        mStage.sizeToScene();
        mStage.show();
    }

    public void popScreen(){
        mScreens.pop().stop();
        if(!mScreens.isEmpty()){
            mScreens.peek().restart();
        }else{
            System.out.println("Exit program!");
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        mWidth = 1024;
        mHeight = 768;
        mStage = primaryStage;
        mScreens = new Stack<>();
        Screen start_screen = new SplashScreen(mStage, this);

        pushScreen(start_screen);
    }
}