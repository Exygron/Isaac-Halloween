package ProjetJeu;

import com.badlogic.gdx.math.Vector2;

import hevs.gdx2d.components.bitmaps.BitmapImage;
import hevs.gdx2d.components.physics.PhysicsStaticBox;
import hevs.gdx2d.lib.GdxGraphics;

public class Porte extends Fixe {

	private String position;
	private boolean open;

	protected static BitmapImage PorteUpOpen;
	protected static BitmapImage PorteDownOpen;
	protected static BitmapImage PorteLeftOpen;
	protected static BitmapImage PorteRightOpen;

	protected static BitmapImage PorteUpClose;
	protected static BitmapImage PorteDownClose;
	protected static BitmapImage PorteLeftClose;
	protected static BitmapImage PorteRightClose;

	public Porte(String position) {
		// TODO Auto-generated constructor stub

		this.position = position;
		open = false;

		if (position == "RIGHT") {
			this.x = 100 + 12;
			this.y = 870 / 2;
			this.width = 24;
			this.height = 100;
		} else if (position == "LEFT") {
			this.x = 1120 + 100 - 12;
			this.y = 870 / 2;
			this.width = 24;
			this.height = 100;
		} else if (position == "UP") {
			this.x = 1320 / 2;
			this.y = 15 + 100 + 640 - 12;
			this.width = 100;
			this.height = 24;
		} else if (position == "DOWN") {
			this.x = 1320 / 2;
			this.y = 15 + 100 + 12;
			this.width = 100;
			this.height = 24;
		}
		Vector2 positionVector = new Vector2(x, y);

		box = new PhysicsStaticBox("Porte", positionVector, width, height);

		box.setBodyActive(false);

		PorteDownOpen = new BitmapImage("data/images/Door_Down_Open.png");
		PorteUpOpen = new BitmapImage("data/images/Door_Up_Open.png");
		PorteRightOpen = new BitmapImage("data/images/Door_Right_Open.png");
		PorteLeftOpen = new BitmapImage("data/images/Door_Left_Open.png");

		PorteDownClose = new BitmapImage("data/images/Door_Down_Close.png");
		PorteUpClose = new BitmapImage("data/images/Door_Up_Close.png");
		PorteRightClose = new BitmapImage("data/images/Door_Right_Close.png");
		PorteLeftClose = new BitmapImage("data/images/Door_Left_Close.png");
	}

	public String getPosition() {
		return position;
	}

	public void move() {
		// TODO Auto-generated method stub

	}

	@Override
	public void draw(GdxGraphics g) {
		// TODO Auto-generated method stub

		if (open == true) {
			if (position == "LEFT") {
				g.drawTransformedPicture(1320 / 2, 870 / 2, 0, 1, PorteLeftOpen);
			} else if (position == "RIGHT") {
				g.drawTransformedPicture(1320 / 2, 870 / 2, 0, 1,
						PorteRightOpen);
			} else if (position == "UP") {
				g.drawTransformedPicture(1320 / 2, 870 / 2, 0, 1, PorteUpOpen);
			} else if (position == "DOWN") {
				g.drawTransformedPicture(1320 / 2, 870 / 2, 0, 1, PorteDownOpen);
			}
		} else {
			if (position == "LEFT") {
				g.drawTransformedPicture(1320 / 2, 870 / 2, 0, 1,
						PorteLeftClose);
			} else if (position == "RIGHT") {
				g.drawTransformedPicture(1320 / 2, 870 / 2, 0, 1,
						PorteRightClose);
			} else if (position == "UP") {
				g.drawTransformedPicture(1320 / 2, 870 / 2, 0, 1, PorteUpClose);
			} else if (position == "DOWN") {
				g.drawTransformedPicture(1320 / 2, 870 / 2, 0, 1,
						PorteDownClose);
			}
		}

	}

}
