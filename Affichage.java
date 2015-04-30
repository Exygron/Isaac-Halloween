package ProjetJeu;

import java.util.Vector;

import javax.swing.DebugGraphics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

import hevs.gdx2d.components.bitmaps.BitmapImage;
import hevs.gdx2d.components.physics.PhysicsBox;
import hevs.gdx2d.components.physics.PhysicsStaticBox;
import hevs.gdx2d.components.physics.utils.PhysicsScreenBoundaries;
import hevs.gdx2d.demos.physics.car.DemoCarDriving;
import hevs.gdx2d.lib.GdxGraphics;
import hevs.gdx2d.lib.PortableApplication;
import hevs.gdx2d.lib.physics.AbstractPhysicsObject;
import hevs.gdx2d.lib.physics.DebugRenderer;
import hevs.gdx2d.lib.physics.PhysicsWorld;

public class Affichage extends PortableApplication {

	BitmapImage BackGroudImage;
	DebugRenderer dbgRenderer;
	boolean DEBUG = false;
	World world = PhysicsWorld.getInstance();
	int activeX = 3;
	int activeY = 3;
	
	final double FRAME_TIME = 0.05; // Duration of each frime	
	float dt = 0;

	Vector2 Position = new Vector2(200f, 200f);

	Vector2 Position1 = new Vector2(1320 / 2, 115-50);
	Vector2 Position2 = new Vector2(1320 / 2, 640+115+50);
	Vector2 Position3 = new Vector2(100-50, 870 / 2);
	Vector2 Position4 = new Vector2(100+1120+50, 870 / 2);

	PhysicsStaticBox mur1 = new PhysicsStaticBox("mur", Position1, 1120f, 100f);
	PhysicsStaticBox mur2 = new PhysicsStaticBox("mur", Position2, 1120f, 100f);
	PhysicsStaticBox mur3 = new PhysicsStaticBox("mur", Position3, 100f, 880f);
	PhysicsStaticBox mur4 = new PhysicsStaticBox("mur", Position4, 100f, 880f);

	Donjon donjon;

	@Override
	public void onInit() {

		setTitle("Isaac's Halloween");
		BackGroudImage = new BitmapImage("data/images/Salle.png");
		// No gravity in this world
		world.setGravity(new Vector2(0, 0));

		if (DEBUG == true)
			dbgRenderer = new DebugRenderer();

		// Create the obstacles in the scene
		new PhysicsScreenBoundaries(getWindowWidth(), getWindowHeight());

		donjon = new Donjon();

	}

	public Affichage(boolean onAndroid) {
		super(onAndroid, 1320, 870);
	}

	public static void main(String[] args) {
		new Affichage(false);
	}

	@Override
	public void onGraphicRender(GdxGraphics g) {
		
		g.clear();
		g.drawBackground(BackGroudImage, 0, 0);
		// Physics update
		PhysicsWorld.updatePhysics(Gdx.graphics.getDeltaTime());
		
		dt += Gdx.graphics.getDeltaTime();

		// Do we have to display the next frame
		if (dt > FRAME_TIME) {
			dt = 0;
			donjon.hero.currentFrame = (donjon.hero.currentFrame + 1) % donjon.hero.nFrames;
		}

		/**
		 * Move the car according to key presses
		 */
		int sizeObjetVector = donjon.donjonSalle[activeX][activeY].objets
				.size();
		for (int i = 0; i < sizeObjetVector; i++) {
			if (donjon.donjonSalle[activeX][activeY].objets.get(i) instanceof Porte) {
				donjon.donjonSalle[activeX][activeY].objets.get(i).draw(g);

			}
			if (donjon.donjonSalle[activeX][activeY].objets.get(i) instanceof Obstacle) {
				donjon.donjonSalle[activeX][activeY].objets.get(i).draw(g);


			}
		}
		
		donjon.hero.draw(g);
		
		
		if (DEBUG == true)
			dbgRenderer.render(world, g.getCamera().combined);

		g.drawFPS();
		g.drawSchoolLogo();
		
		

		// constamment tant que la touche est presse
		if (Gdx.input.isKeyPressed(Input.Keys.A)
				&& Gdx.input.isKeyPressed(Input.Keys.S)) {
			donjon.hero.textureY = 3;
			donjon.hero.box.applyBodyForceToCenter(-40, -40, true);
		}
		if (Gdx.input.isKeyPressed(Input.Keys.D)
				&& Gdx.input.isKeyPressed(Input.Keys.S)) {
			donjon.hero.textureY = 2;
			donjon.hero.box.applyBodyForceToCenter(40, -40, true);
		}
		if (Gdx.input.isKeyPressed(Input.Keys.D)
				&& Gdx.input.isKeyPressed(Input.Keys.W)) {
			donjon.hero.textureY = 2;
			donjon.hero.box.applyBodyForceToCenter(40, 40, true);
		}
		if (Gdx.input.isKeyPressed(Input.Keys.A)
				&& Gdx.input.isKeyPressed(Input.Keys.W)) {
			donjon.hero.textureY = 2;
			donjon.hero.box.applyBodyForceToCenter(-40, 40, true);
		}
		if (Gdx.input.isKeyPressed(Input.Keys.A)) {
			donjon.hero.textureY = 3;
			donjon.hero.box.applyBodyForceToCenter(-70, 0, true);

		} else if (Gdx.input.isKeyPressed(Input.Keys.S)) {
			donjon.hero.textureY = 0;
			donjon.hero.box.applyBodyForceToCenter(0, -70, true);

		} else if (Gdx.input.isKeyPressed(Input.Keys.D)) {
			donjon.hero.textureY = 2;
			donjon.hero.box.applyBodyForceToCenter(70, 0, true);

		} else if (Gdx.input.isKeyPressed(Input.Keys.W)) {
			donjon.hero.textureY = 1;
			donjon.hero.box.applyBodyForceToCenter(0, 70, true);

		} else if (Gdx.input.isKeyPressed(Input.Keys.Q)) {
			donjon.hero.box.applyBodyForceToCenter((float) (Math.random() * 50 - 25),
					(float) (Math.random() * 50 - 25), true);

		}
	}

	public void setObjectSalleActive(int x, int y) {
		int sizeObjetVector = donjon.donjonSalle[x][y].objets.size();
		for (int i = 0; i < sizeObjetVector; i++) {
			if (donjon.donjonSalle[activeX][activeY].objets.get(i) instanceof Obstacle) {
				donjon.donjonSalle[x][y].objets.get(i).box.setBodyActive(true);

			}
		}

	}

	public void setObjectSalleInActive(int x, int y) {
		int sizeObjetVector = donjon.donjonSalle[x][y].objets.size();
		for (int i = 0; i < sizeObjetVector; i++) {
			if (donjon.donjonSalle[activeX][activeY].objets.get(i) instanceof Obstacle) {
				donjon.donjonSalle[x][y].objets.get(i).box.setBodyActive(false);

			}
		}

	}

	@Override
	public void onKeyUp(int keycode) {
		super.onKeyUp(keycode);
		switch (keycode) {

		case Input.Keys.A:

			break;
		default:

			break;
		}

	}

	// seulement quand on press
	@Override
	public void onKeyDown(int keycode) {

		setObjectSalleInActive(activeX, activeY);
		super.onKeyDown(keycode);

		switch (keycode) {

		case Input.Keys.DOWN:
			activeY -= 1;

			break;

		case Input.Keys.UP:
			activeY += 1;

			break;
		case Input.Keys.RIGHT:
			activeX += 1;

			break;

		case Input.Keys.LEFT:
			activeX -= 1;

			break;

		default:
			break;
		}

		if (activeX < 0)
			activeX = 0;
		if (activeX > 6)
			activeX = 6;
		if (activeY < 0)
			activeY = 0;
		if (activeY > 6)
			activeY = 6;
		setObjectSalleActive(activeX, activeY);

	}

}
