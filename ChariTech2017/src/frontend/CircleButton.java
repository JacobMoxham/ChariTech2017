package frontend;

import javafx.scene.control.Button;

/**
 * Created by pighe on 17/03/2017.
 */
public class CircleButton extends Button {
    private double mRadius;

    public CircleButton(String text, double d){
        super(text);
        mRadius = d;
        setStyle(
                "-fx-background-radius: 5em; " +
                        "-fx-min-width: " + d + "px; " +
                        "-fx-min-height: " + d + "px; " +
                        "-fx-max-width: " + d + "px; " +
                        "-fx-max-height: " + d + "px;"
        );
    }
}
