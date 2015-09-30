package application;
 
import java.util.ArrayList;
import java.util.List;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.util.Duration;
 
public class Asteroids extends Pane implements EventHandler<KeyEvent>  {
 
    private List<SpaceObject> spaceObjects;
     
    protected void add(SpaceObject so, double x, double y) {
        so.translate(x, y);
        getChildren().add(so);
        spaceObjects.add(so);
    }
    public void init() {
        spaceObjects = new ArrayList<SpaceObject>();
    }
 
    public void run() {
        // setter opp simuleringstakt
        Timeline tickTimer = new Timeline(new KeyFrame(Duration.millis(50), new EventHandler<ActionEvent>() {
            private int tickCount = 0;
            @Override
            public void handle(ActionEvent event) {
                tick(tickCount++);
            }
        }));
        tickTimer.setCycleCount(Timeline.INDEFINITE);
        tickTimer.play();
        requestFocus();
        setOnKeyPressed(this);
        setOnKeyTyped(this);
    }
 
    // kalles når spesialtaster som pilene trykkes
    protected void handleKey(KeyCode keyCode) {
    }
 
    // kalles når vanlige bokstavtaster trykkes
    protected void handleKey(String character) {
        if ("+".equals(character)) {
            zoom(2);
        } else if ("-".equals(character)) {
            zoom(0.5);
        }
    }
     
    private void zoom(double factor) {
        setScaleX(getScaleX() * factor);
        setScaleY(getScaleY() * factor);
    }
     
    @Override
    public void handle(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.UNDEFINED) {
            handleKey(keyEvent.getCharacter());
        } else {
            handleKey(keyEvent.getCode());
        }
    }
 
    private void tick(int tickCount) {
    	for (int i = 0; i < spaceObjects.size(); i++) { // Acceleration due to gravity
    		for (int j = i + 1; j < spaceObjects.size(); j++) {
    			handleGravity(spaceObjects.get(i),spaceObjects.get(j));
    		}
		}
    	
        for (SpaceObject spaceObject : spaceObjects) {
            spaceObject.tick();
        }
 
        for (int i = 0; i < spaceObjects.size(); i++) { // Collision check
        	SpaceObject s1 = spaceObjects.get(i);
    		for (int j = i + 1; j < spaceObjects.size(); j++) {
    			SpaceObject s2 = spaceObjects.get(j);
    			if (s1.intersects(s2)) {
    				s1.setVelocity(-s1.getVelocity().getX(), -s1.getVelocity().getY()); // VERY simplistic collision logic
    				s2.setVelocity(-s2.getVelocity().getX(), -s2.getVelocity().getY());
    				s1.tick(); s2.tick();
    			}
    		}
		}
    }
 
    public void handleGravity(SpaceObject s1, SpaceObject s2) {
    	double m1 = s1.getMass();
    	double m2 = s2.getMass();
    	if (m1 == 0 || m2 == 0) {
			return; // Massless, so no gravity involved
		}
    	Point2D p1 = s1.getCenter(true);
    	Point2D p2 = s2.getCenter(true);
		double distance = p1.distance(p2);
		double force = 5 * m1 * m2 / distance / distance;
		double dx = (p1.getX() - p2.getX()) / distance;
		double dy = (p1.getY() - p2.getY()) / distance;
		s1.applyForce(-force * dx, -force * dy);
		s2.applyForce(force * dx, force * dy);
    }
}