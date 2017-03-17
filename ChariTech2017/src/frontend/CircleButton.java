package frontend;

import javafx.scene.control.Button;

/**
 * Created by pighe on 17/03/2017.
 */
public class CircleButton extends Button {
    private int mRadius;

    public CircleButton(String text, int r){
        super(text);
        mRadius = r;
        setStyle(
                "-fx-background-radius: 5em; " +
                        "-fx-min-width: " + r + "px; " +
                        "-fx-min-height: " + r + "px; " +
                        "-fx-max-width: " + r + "px; " +
                        "-fx-max-height: " + r + "px;"
        );
    }
}
