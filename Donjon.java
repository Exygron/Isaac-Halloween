package ProjetJeu;

import java.util.Random;
import java.util.Vector;

public class Donjon {

	public Salle[][] donjonSalle = new Salle[7][7];
	private final int RECUSION_NBR_SALLE = 0;
	private final int NBR_SKIN_OBSTACLE = 3;
	private boolean isBoss = false;
	public Hero hero;

	public Donjon() {
		for (int x = 0; x < 7; x++) {
			for (int y = 0; y < 7; y++) {
				donjonSalle[x][y] = new Salle();
			}
		}
		generateSalles(3, 3, 0);
		// generateObstacle(3, 3);
		for (int x = 0; x < 7; x++) {
			for (int y = 0; y < 7; y++) {
				if (donjonSalle[x][y].empty == false) {
					generateObstacle(x, y);
				}
			}
		}
		System.out.println(this);

		hero = new Hero(1320 / 2, 870 / 2, 80, 80, 0, 0, 10, 1, 1);
		hero.box.setBodyAwake(true);

	}

	public int nombreSalle() {
		int toReturn = 0;
		for (int x = 0; x < 7; x++) {
			for (int y = 0; y < 7; y++) {
				if (this.donjonSalle[x][y].empty == false)
					toReturn += 1;
			}
		}

		return toReturn;
	}

	private void generateObstacle(int x, int y) {

		int sallePattern = (int) (Math.random() * 8);

		switch (sallePattern) {
		case 0:
			int posX0[] = { 0, 1, 2, 11, 12, 13, 0, 1, 12, 13, 0, 13, 0, 13, 0,
					1, 12, 13, 0, 1, 2, 11, 12, 13 };
			int posY0[] = { 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 2, 2, 5, 5, 6, 6, 6,
					6, 7, 7, 7, 7, 7, 7 };
			for (int i = 0; i < posX0.length; i++) {
				Obstacle newObstacle = new Obstacle(
						(int) (Math.random() * NBR_SKIN_OBSTACLE),
						posX0[i] * 80 + 100 + 35,
						posY0[i] * 80 + 100 + 35 + 15, 70, 70);

				this.donjonSalle[x][y].objets.add(newObstacle);
			}

			break;
		case 1:
			int posX1[] = { 0, 13, 5, 8, 4, 5, 8, 9, 4, 5, 8, 9, 5, 8, 0, 13 };
			int posY1[] = { 0, 0, 1, 1, 2, 2, 2, 2, 5, 5, 5, 5, 6, 6, 7, 7 };
			for (int i = 0; i < posX1.length; i++) {
				Obstacle newObstacle = new Obstacle(
						(int) (Math.random() * NBR_SKIN_OBSTACLE),
						posX1[i] * 80 + 100 + 35,
						posY1[i] * 80 + 100 + 35 + 15, 70, 70);

				this.donjonSalle[x][y].objets.add(newObstacle);
			}
			break;
		case 2:
			int posX2[] = { 2, 3, 10, 11, 2, 3, 10, 11, 2, 3, 10, 11, 2, 3, 10,
					11, 6, 7, 6, 7 };
			int posY2[] = { 1, 1, 1, 1, 2, 2, 2, 2, 5, 5, 5, 5, 6, 6, 6, 6, 3,
					3, 4, 4 };
			for (int i = 0; i < posX2.length; i++) {
				Obstacle newObstacle = new Obstacle(
						(int) (Math.random() * NBR_SKIN_OBSTACLE),
						posX2[i] * 80 + 100 + 35,
						posY2[i] * 80 + 100 + 35 + 15, 70, 70);

				this.donjonSalle[x][y].objets.add(newObstacle);
			}
			break;
		case 3:
			int posX3[] = { 0, 1, 12, 13, 0, 13, 1, 2, 4, 5, 8, 9, 11, 12, 1,
					2, 4, 5, 8, 9, 11, 12, 0, 13, 0, 1, 12, 13 };
			int posY3[] = { 0, 0, 0, 0, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 5, 5, 5,
					5, 5, 5, 5, 5, 6, 6, 7, 7, 7, 7 };
			for (int i = 0; i < posX3.length; i++) {
				Obstacle newObstacle = new Obstacle(
						(int) (Math.random() * NBR_SKIN_OBSTACLE),
						posX3[i] * 80 + 100 + 35,
						posY3[i] * 80 + 100 + 35 + 15, 70, 70);

				this.donjonSalle[x][y].objets.add(newObstacle);
			}
			break;

		default:
			int nbrObstacle = 4 + (int) (Math.random() * 7); // nbr d'obstacle
			// aléatoir entre 4 et 10
			Vector<Objet> tempObjets = new Vector<Objet>();
			int posX;
			int posY;
			do {
				posX = (int) (Math.random() * 14);
				posY = (int) (Math.random() * 8);
			} while ((posX == 0 && posY == 3) || (posX == 0 && posY == 4)
					|| (posX == 6 && posY == 0) || (posX == 7 && posY == 0)
					|| (posX == 13 && posY == 3) || (posX == 13 && posY == 4)
					|| (posX == 6 && posY == 7) || (posX == 7 && posY == 7));

			// créer le premier obstacle
			Obstacle firstObstacle = new Obstacle(
					(int) (Math.random() * NBR_SKIN_OBSTACLE),
					posX * 80 + 100 + 35, posY * 80 + 100 + 35 + 15, 70, 70);
			tempObjets.add(firstObstacle);

			for (int a = 0; a < nbrObstacle - 2; a++) {
				boolean tooClose;
				do {
					tooClose = false;
					posX = (int) (Math.random() * 14);
					posY = (int) (Math.random() * 8);
					for (int i = 0; i < tempObjets.size(); i++) {
						int posXnew = tempObjets.elementAt(i).getX() / 80;
						int posYnew = tempObjets.elementAt(i).getY() / 80;

						double delatX = Math.abs(posX - posXnew);
						double delatY = Math.abs(posY - posYnew);

						double distance = Math.sqrt(delatY * delatY + delatX
								* delatX);
						if (distance < 2.0) {
							tooClose = true;
						}
					}

				} while (tooClose == true || (posX == 0 && posY == 3)
						|| (posX == 0 && posY == 4) || (posX == 6 && posY == 0)
						|| (posX == 7 && posY == 0)
						|| (posX == 13 && posY == 3)
						|| (posX == 13 && posY == 4)
						|| (posX == 6 && posY == 7) || (posX == 7 && posY == 7));

				Obstacle newObstacle = new Obstacle(
						(int) (Math.random() * NBR_SKIN_OBSTACLE),
						posX * 80 + 100 + 35, posY * 80 + 100 + 35 + 15, 70, 70);
				tempObjets.add(newObstacle);
			}
			this.donjonSalle[x][y].objets.addAll(tempObjets);

			break;
		}

	}

	private void generateSalles(int x, int y, int nRecurtion) {

		Porte PorteUP = new Porte("UP");
		Porte PorteDOWN = new Porte("DOWN");
		Porte PorteRIGHT = new Porte("RIGHT");
		Porte PorteLEFT = new Porte("LEFT");

		// salle actuelle est occupée
		donjonSalle[x][y].empty = false;

		if (nRecurtion == 0) {
			// Ajoute les 4 portes à la salle principale
			donjonSalle[x][y].objets.add(PorteUP);
			donjonSalle[x][y].objets.add(PorteDOWN);
			donjonSalle[x][y].objets.add(PorteRIGHT);
			donjonSalle[x][y].objets.add(PorteLEFT);

			// Ajoute 2 portes sur chaque salles touchant la salle principal
			donjonSalle[x][y + 1].objets.add(PorteUP);
			donjonSalle[x][y - 1].objets.add(PorteDOWN);
			donjonSalle[x + 1][y].objets.add(PorteRIGHT);
			donjonSalle[x - 1][y].objets.add(PorteLEFT);

			donjonSalle[x][y + 1].objets.add(PorteDOWN);
			donjonSalle[x][y - 1].objets.add(PorteUP);
			donjonSalle[x + 1][y].objets.add(PorteLEFT);
			donjonSalle[x - 1][y].objets.add(PorteRIGHT);

			// Ajoute 1 portes sur chaque salles touchant a 2 salle de la salle
			// principale
			donjonSalle[x + 2][y].objets.add(PorteLEFT);
			donjonSalle[x][y - 2].objets.add(PorteUP);
			donjonSalle[x - 2][y].objets.add(PorteRIGHT);
			donjonSalle[x][y + 2].objets.add(PorteDOWN);

			// Occupe les 4 salles touchant la salle principal
			donjonSalle[x][y + 1].empty = false;
			donjonSalle[x - 1][y].empty = false;
			donjonSalle[x][y - 1].empty = false;
			donjonSalle[x + 1][y].empty = false;

			// Continue a generer le donjon
			generateSalles(x, y + 2, nRecurtion + 1);
			generateSalles(x - 2, y, nRecurtion + 1);
			generateSalles(x, y - 2, nRecurtion + 1);
			generateSalles(x + 2, y, nRecurtion + 1);

		} else if (nRecurtion == RECUSION_NBR_SALLE) {
			// choisi une salle de bosse
			if (isBoss == false) {
				donjonSalle[x][y].salleBoss = true;
				isBoss = true;
			}

		} else {
			// chiffre aléatoire du nbr de salle
			int nAutreSalles = (int) (Math.random() * 4);

			for (int i = 0; i < nAutreSalles + 1; i++) {
				// choisis l'emplacement de la salle
				int autreSalle = (int) (Math.random() * 3);

				switch (autreSalle) {
				case 0:
					if (x < 7 && y + 1 < 7 && x > -1 && y + 1 > -1) {
						if (donjonSalle[x][y + 1].empty == true) {
							// ajout porte en haut
							donjonSalle[x][y].objets.add(PorteUP);
							donjonSalle[x][y + 1].objets.add(PorteDOWN);
							// genere salle
							generateSalles(x, y + 1, nRecurtion + 1);
						}
					}

					break;
				case 1:
					if (x - 1 < 7 && y < 7 && x - 1 > -1 && y > -1) {
						if (donjonSalle[x - 1][y].empty == true) {
							// ajout porte a gauche
							donjonSalle[x][y].objets.add(PorteLEFT);
							donjonSalle[x - 1][y].objets.add(PorteRIGHT);
							// genere salle
							generateSalles(x - 1, y, nRecurtion + 1);
						}
					}
					break;
				case 2:
					if (x < 7 && y - 1 < 7 && x > -1 && y - 1 > -1) {
						if (donjonSalle[x][y - 1].empty == true) {
							// ajout porte en bas
							donjonSalle[x][y].objets.add(PorteDOWN);
							donjonSalle[x][y - 1].objets.add(PorteUP);
							// genere salle
							generateSalles(x, y - 1, nRecurtion + 1);
						}
					}
					break;
				case 3:
					if (x + 1 < 7 && y < 7 && x + 1 > -1 && y > -1) {
						if (donjonSalle[x + 1][y].empty == true) {
							// ajout porte a droite
							donjonSalle[x][y].objets.add(PorteRIGHT);
							donjonSalle[x + 1][y].objets.add(PorteLEFT);
							// genere salle
							generateSalles(x + 1, y, nRecurtion + 1);
						}
					}
					break;
				}
			}

		}

	}

	public String toString() {

		String toPrint = "\n\n";
		for (int y = 6; y >= 0; y--) {

			for (int x = 0; x < 7; x++) {
				if (donjonSalle[x][y].empty == true) {
					toPrint += "[     ]  ";
				}

				else {
					String salle = "";

					for (int i = 0; i < donjonSalle[x][y].objets.size(); i++) {
						if (donjonSalle[x][y].objets.get(i) instanceof Porte) {
							Porte Temp = (Porte) donjonSalle[x][y].objets
									.get(i);

							if (Temp.getPosition() == "DOWN") {
								salle += "D";
							} else if (Temp.getPosition() == "UP") {
								salle += "U";
							} else if (Temp.getPosition() == "RIGHT") {
								salle += "R";
							} else if (Temp.getPosition() == "LEFT") {
								salle += "L";
							}
						}
					}
					if (donjonSalle[x][y].salleBoss == true) {
						salle += "B";
					}
					salle = String.format("%5s", salle);
					toPrint += "(";
					toPrint += salle;
					toPrint += ")  ";
				}

			}
			toPrint += "\n\n";
		}

		toPrint += ("\n Nombre de salles : " + this.nombreSalle());

		return toPrint;
	}

}
