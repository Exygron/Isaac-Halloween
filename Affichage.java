package ProjetJeu;

import java.util.Vector;

import javax.swing.DebugGraphics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;

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
	BitmapImage loadingImage;
	DebugRenderer dbgRenderer;
	boolean DEBUG = false;
	World world = PhysicsWorld.getInstance();
	int activeX = 3;
	int activeY = 3;
	boolean moveSalle = false;
	String gotoSalle = "";

	int loadNewDonjon = 0;

	BitmapImage fonduBlack;

	float alpha = 0;

	final double FRAME_TIME = 0.05; // Duration of each frime
	float dt = 0;

	Vector2 Position = new Vector2(200f, 200f);

	Vector2 Position1 = new Vector2(1320 / 2, 115 - 50);
	Vector2 Position2 = new Vector2(1320 / 2, 640 + 115 + 50);
	Vector2 Position3 = new Vector2(100 - 50, 870 / 2);
	Vector2 Position4 = new Vector2(100 + 1120 + 50, 870 / 2);

	PhysicsStaticBox mur1 = new PhysicsStaticBox("mur", Position1, 1120f, 100f);
	PhysicsStaticBox mur2 = new PhysicsStaticBox("mur", Position2, 1120f, 100f);
	PhysicsStaticBox mur3 = new PhysicsStaticBox("mur", Position3, 100f, 880f);
	PhysicsStaticBox mur4 = new PhysicsStaticBox("mur", Position4, 100f, 880f);

	Donjon donjon;

	@Override
	public void onInit() {

		setTitle("Isaac's Halloween");
		BackGroudImage = new BitmapImage("data/images/Salle.png");
		loadingImage = new BitmapImage("data/images/loading_screen.png");
		fonduBlack = new BitmapImage("data/images/salle_black.png");

		// No gravity in this world
		world.setGravity(new Vector2(0, 0));

		if (DEBUG == true)
			dbgRenderer = new DebugRenderer();

		// Create the obstacles in the scene
		new PhysicsScreenBoundaries(getWindowWidth(), getWindowHeight());

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
		if (loadNewDonjon == 0) {

			g.drawBackground(loadingImage, 0, 0);

			loadNewDonjon = 1;

		} else if (loadNewDonjon == 1) {
			loadNewDonjon = 2;
			donjon = new Donjon();
		} else {
			g.drawBackground(BackGroudImage, 0, 0);
			// Physics update
			PhysicsWorld.updatePhysics(Gdx.graphics.getDeltaTime());

			PhysicsWorld.getInstance().setContactListener(
					new ContactListener() {

						@Override
						public void preSolve(Contact arg0, Manifold arg1) {
							// TODO Auto-generated method stub

						}

						@Override
						public void postSolve(Contact arg0, ContactImpulse arg1) {
							// TODO Auto-generated method stub

						}

						@Override
						public void endContact(Contact arg0) {
							// TODO Auto-generated method stub

						}

						@Override
						public void beginContact(Contact contact) {

							AbstractPhysicsObject objetA;
							AbstractPhysicsObject objetB;
							AbstractPhysicsObject heroCollision;
							AbstractPhysicsObject enemyCollision;
							AbstractPhysicsObject bulletCollision;
							AbstractPhysicsObject doorCollision;

							if (contact.getFixtureA().getBody().getUserData() instanceof PhysicsStaticBox) {
								objetA = (PhysicsStaticBox) (contact
										.getFixtureA().getBody().getUserData());

							} else if (contact.getFixtureA().getBody()
									.getUserData() instanceof PhysicsBox) {
								objetA = (PhysicsBox) (contact.getFixtureA()
										.getBody().getUserData());

							} else {
								objetA = (AbstractPhysicsObject) (contact
										.getFixtureA().getBody().getUserData());
							}

							if (contact.getFixtureB().getBody().getUserData() instanceof PhysicsStaticBox) {
								objetB = (PhysicsStaticBox) (contact
										.getFixtureB().getBody().getUserData());

							} else if (contact.getFixtureB().getBody()
									.getUserData() instanceof PhysicsBox) {
								objetB = (PhysicsBox) (contact.getFixtureB()
										.getBody().getUserData());

							} else {
								objetB = (AbstractPhysicsObject) (contact
										.getFixtureA().getBody().getUserData());
							}

							if ((objetA.name == "Hero" && (objetB.name == "UP"
									|| objetB.name == "DOWN"
									|| objetB.name == "RIGHT" || objetB.name == "LEFT"))
									|| (objetB.name == "Hero" && (objetA.name == "UP"
											|| objetA.name == "DOWN"
											|| objetA.name == "RIGHT" || objetA.name == "LEFT"))) {
								// TODO

								if (objetA.name == "Hero") {
									heroCollision = objetA;
									doorCollision = objetB;
								} else {
									heroCollision = objetB;
									doorCollision = objetA;
								}

								gotoSalle = doorCollision.name;
								moveSalle = true;

							}

						}
					});

			if (moveSalle == true) {
				alpha = 1;
				moveSalle = false;
				setObjectSalleInActive(activeX, activeY);

				if (gotoSalle == "UP") {
					activeY += 1;
					donjon.hero.box.getBody().setTransform(8.8f, 2.3f, 0);

				} else if (gotoSalle == "DOWN") {
					activeY -= 1;
					donjon.hero.box.getBody().setTransform(8.8f, 9.2f, 0);

				} else if (gotoSalle == "RIGHT") {
					activeX += 1;
					donjon.hero.box.getBody().setTransform(2.1f, 5.8f, 0);

				} else if (gotoSalle == "LEFT") {
					activeX -= 1;
					donjon.hero.box.getBody().setTransform(15.5f, 5.8f, 0);

				}
				setObjectSalleActive(activeX, activeY);
			}

			dt += Gdx.graphics.getDeltaTime();

			// Do we have to display the next frame
			if (dt > FRAME_TIME) {
				dt = 0;
				donjon.hero.currentFrame = (donjon.hero.currentFrame + 1)
						% donjon.hero.nFrames;
			}

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

			if (alpha <= 1 && alpha >= 0.0) {
				g.drawAlphaPicture(getWindowWidth() / 2f,
						getWindowHeight() / 2f, alpha, fonduBlack);
				alpha -= 0.1;

			}

			g.drawFPS();

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
				donjon.hero.box.applyBodyForceToCenter(
						(float) (Math.random() * 50 - 25),
						(float) (Math.random() * 50 - 25), true);

			}
		}
	}

	public void setObjectSalleActive(int x, int y) {
		int sizeObjetVector = donjon.donjonSalle[x][y].objets.size();
		for (int i = 0; i < sizeObjetVector; i++) {
			if (donjon.donjonSalle[x][y].objets.get(i) instanceof Obstacle) {
				donjon.donjonSalle[x][y].objets.get(i).box.setBodyActive(true);

			}
			if (donjon.donjonSalle[x][y].objets.get(i) instanceof Porte) {
				Porte porte = (Porte) (donjon.donjonSalle[activeX][activeY].objets
						.get(i));
				if (porte.getOpen()) {
					donjon.donjonSalle[x][y].objets.get(i).box
							.setBodyActive(true);
				}

			}
		}

	}

	public void setObjectSalleInActive(int x, int y) {
		int sizeObjetVector = donjon.donjonSalle[x][y].objets.size();
		for (int i = 0; i < sizeObjetVector; i++) {
			if (donjon.donjonSalle[x][y].objets.get(i) instanceof Obstacle) {
				donjon.donjonSalle[x][y].objets.get(i).box.setBodyActive(false);

			}
			if (donjon.donjonSalle[activeX][activeY].objets.get(i) instanceof Porte) {
				Porte porte = (Porte) (donjon.donjonSalle[x][y].objets.get(i));
				if (porte.getOpen()) {
					donjon.donjonSalle[x][y].objets.get(i).box
							.setBodyActive(false);
				}

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
