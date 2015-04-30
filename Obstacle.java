package ProjetJeu;

import com.badlogic.gdx.math.Vector2;

import hevs.gdx2d.components.bitmaps.BitmapImage;
import hevs.gdx2d.components.physics.PhysicsStaticBox;
import hevs.gdx2d.lib.GdxGraphics;
import hevs.gdx2d.lib.physics.AbstractPhysicsObject;

public class Obstacle extends Fixe {

	private int obstacleType;
	
	protected static BitmapImage obstacleImage0;
	protected static BitmapImage obstacleImage1;
	protected static BitmapImage obstacleImage2;

	public Obstacle(int obstacleType, int x, int y, int width, int height) {
		this.obstacleType = obstacleType;
		this.height = height;
		this.width = width;
		this.x = x;
		this.y = y;
		Vector2 position = new Vector2(x, y);
		box = new PhysicsStaticBox("obstacle", position, width, height);
		box.setBodyActive(false);
		obstacleImage0 = new BitmapImage("data/images/obstacle_citrouille.png");
		obstacleImage1 = new BitmapImage("data/images/Obstacle_Tombe.png");
		obstacleImage2 = new BitmapImage("data/images/Obstacle_Pierre.png");
		
		
	}

	public void move() {
		// TODO Auto-generated method stub
		
		

	}

	@Override
	public void draw(GdxGraphics g) {
		// TODO Auto-generated method stub
		switch (obstacleType) {
		case 0:
			g.drawTransformedPicture(x, y, 0, 70 / 128f, obstacleImage0);
			break;
		case 1:
			g.drawTransformedPicture(x, y, 0, 70 / 128f, obstacleImage1);
			break;
		default:
			g.drawTransformedPicture(x, y, 0, 70 / 128f, obstacleImage2);
			break;
		}
		

	}

}
