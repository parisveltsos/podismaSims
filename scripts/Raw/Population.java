
import java.io.PrintWriter;

/* POPULATION STARTS HERE */
public class Population {
	Individual[] males, females;

	public Population() {
		Individual UFM = new Individual(Storage.getGamete("D"), Storage.getGamete("C"), 0); 
			//THESE need to be replaced by a method,
			// this particular one should give the same
			// gametes from the opposite combination of
			// sperm and egg 50% of the time too!
		Individual UFF = new Individual(Storage.getGamete("D"), Storage.getGamete("D"), 0);
		males = new Individual[Storage.GUIpopSize];
		females = new Individual[Storage.GUIpopSize];
		for (int i = 0; i < Storage.GUIpopSize; i++) {
			males[i] = UFM; //all males are UFM
			females[i] = UFF; //all females are UFF
		}
	}

	//This method is used once to make and fill a new populations with the
	// given males and females
	public Population(Individual female, Individual male, int halfpopsize) {
		males = new Individual[halfpopsize];
		females = new Individual[halfpopsize];
		for (int i = 0; i < halfpopsize; i++) {
			males[i] = male;
			females[i] = female;
		}
	}

	//This will be used to initialise the zone populations
	public Population(Population old, int halfpopsize) {
		males = new Individual[halfpopsize];
		females = new Individual[halfpopsize];
		Individual newborn = new Individual(Storage.getGamete("D"), Storage.getGamete("D"), 0);
		// ATTENTION why does the newborn need to be initialised? It shouldnt
		// matter what the initial values are?
		int j, k;
		boolean malechosen, femalechosen, femalePass;

		for (int i = 0; i < halfpopsize; i++) {
			malechosen = false;
			femalechosen = false;
			while (!(malechosen && femalechosen)) {
				k = (int) (halfpopsize * (Math.random()));

				/*
				 * This loop chooses males and females at random but also
				 * depending on the fitness of each
				 */
				if (old.males[k].indFit > Math.random()) {
					int[] maleGamete = old.males[k].Gamete();
					femalePass = false;
					while (femalePass == false) {
						j = (int) (halfpopsize * (Math.random()));
						if (old.females[j].indFit > Math.random()) {
							newborn = new Individual(old.females[j].Gamete(),
									maleGamete, old.females[j].mtDNA);
							//mtDNA always inherited from mother  
							femalePass = true;
						}
					}
				}

				if (newborn.sperm[0] == 0)
					malechosen = true;
				else
					femalechosen = true; //ATTENTION! Is this newborn the one
										 // defined in the loop or in the
										 // begining of this class?
				if ((malechosen == true) && (males[i] == null))
					males[i] = newborn;
				else if ((femalechosen == true) && (females[i] == null))
					females[i] = newborn;
			}
		}
	}

	public void ReadPopulation() {
		for (int j = 0; j < males.length; j++) {
			System.out.println("\nIndividual " + j + ":\t");
			System.out.print("\tsperm giving rise to females :\t");
			for (int i = 0; i < 20; i++)
				System.out.print(" " + females[j].sperm[i]);
			System.out.println("  female fitness: " + females[j].indFit);
			System.out.print("\tegg giving rise to females :\t");
			for (int i = 0; i < 20; i++)
				System.out.print(" " + females[j].egg[i]);
			System.out.println();
			System.out.print("\tsperm giving rise to males :\t");
			for (int i = 0; i < 20; i++)
				System.out.print(" " + males[j].sperm[i]);
			System.out.println("  male fitness: " + males[j].indFit);
			System.out.print("\tegg giving rise to males :\t");
			for (int i = 0; i < 20; i++)
				System.out.print(" " + males[j].egg[i]);
		}
		System.out.println();
	}

	public void YPopulation() {
		for (int j = 0; j < males.length; j++) {
			//       	System.out.print("\tIndividual " +j +":\t");
			//       	if (females[j].sperm[3]==0 && (females[j].sperm[4]==2 ||
			// females[j].sperm[5]==2))
			if (females[j].indFit == 1)
				System.out.print(" 0 " + females[j].femaleStatus);
			else if (females[j].indFit == 0.9)
				System.out.print(" 1 " + females[j].femaleStatus);
			else
				System.out.print(" 2 " + females[j].femaleStatus);
		}
		System.out.println();
	}

	public void ShowYinFemales(PrintWriter pw) {
		int a = 0, b = 0;
		for (int j = 0; j < males.length; j++) {
			if ((females[j].sperm[0] == 1) && ((females[j].egg[4] == 2)))
				a = 1;
			else
				a = 0;
			if ((females[j].sperm[0] == 1) && ((females[j].sperm[4] == 2)))
				b = 1;
			else
				b = 0;
			pw.print(" " + (a + b));
		}
		pw.println();
	}

	public void ShowNextToYinFemales(PrintWriter pw) {
		int a = 0, b = 0;
		for (int j = 0; j < males.length; j++) {
			if ((females[j].sperm[0] == 1) && ((females[j].egg[5] == 2)))
				a = 1;
			else
				a = 0;
			if ((females[j].sperm[0] == 1) && ((females[j].sperm[5] == 2)))
				b = 1;
			else
				b = 0;
			pw.print(" " + (a + b));
		}
		pw.println();
	}

	public void ShowmtDNA(PrintWriter pw) {
		for (int j = 0; j < males.length; j++) {
			pw.print(" " + (females[j].mtDNA + males[j].mtDNA));
		}
		pw.println();
	}

	public void ShowAutosomalAllele(PrintWriter pw) {
		for (int j = 0; j < males.length; j++) {
			pw.print(" "
					+ (females[j].sperm[10] + males[j].sperm[10]
							+ females[j].egg[10] + males[j].egg[10]));
		}
		pw.println();
	}

	public void ShowObservedZone(PrintWriter pw) {
		for (int j = 0; j < males.length; j++) {
			pw.print(" " + males[j].egg[3]);
		}
		pw.println();
	}

	public void ShowTotalY(PrintWriter pw) {
		int a = 0, b = 0, c = 0, d = 0;
		for (int j = 0; j < males.length; j++) {
			if (females[j].egg[4] == 2)
				a = 1;
			else
				a = 0;
			if (males[j].egg[4] == 2)
				b = 1;
			else
				b = 0;
			if (females[j].sperm[4] == 2)
				c = 1;
			else
				c = 0;
			if (males[j].sperm[4] == 2)
				d = 1;
			else
				d = 0;
			pw.print(" " + (a + b + c + d));
		}
		pw.println();
	}

	public void ShowFemales(PrintWriter pw) {
		for (int j = 0; j < males.length; j++) {
			if ((females[j].femaleStatus)
					&& (((females[j].sperm[4] == 2) && (females[j].egg[4] == 1)) || ((females[j].sperm[4] == 1) && (females[j].egg[4] == 2)))) {
				pw.print(" Y1");
			} //Y in females
			if ((females[j].femaleStatus)
					&& (((females[j].sperm[4] == 0) && (females[j].egg[4] == 2)) || ((females[j].sperm[4] == 2) && (females[j].egg[4] == 0)))) {
				pw.print(" Y2");
			} //Y in females AND no coevolved NeoX
			if ((females[j].femaleStatus) && (females[j].sperm[4] == 2)
					&& (females[j].egg[4] == 2)) {
				pw.print(" 2Ys");
			}
			; //2 Ys in females, no L3
		}
		pw.println();
	}

	public void ShowTotalFitness(PrintWriter pw) {
		for (int j = 0; j < males.length; j++) {
			pw.print(" " + (females[j].indFit + males[j].indFit) / 2);
		}
		pw.println();
	}

	public void ShowMaleFitness(PrintWriter pw) {
		for (int j = 0; j < males.length; j++) {
			pw.print(" " + males[j].indFit);
		}
		pw.println();
	}

	public void ShowFemaleFitness(PrintWriter pw) {
		for (int j = 0; j < males.length; j++) {
			pw.print(" " + females[j].indFit);
		}
		pw.println();
	}

	public void ShowFemalemtDNA(PrintWriter pw) {
		int a = 0;
		for (int j = 0; j < males.length; j++) {
			if ((females[j].egg[4] == 0 || females[j].egg[4] == 0)
					&& (females[j].mtDNA == 1))
				pw.print(" 1");
			if ((females[j].egg[4] == 1 || females[j].egg[4] == 1)
					&& (females[j].mtDNA == 0))
				pw.print(" -1");
			//pw.print(" '("+ females[j].egg[4] + " " + females[j].sperm[4] + "
			// " + females[j].mtDNA + ")'");
		}
		pw.println();
	}

	public void addSimParameters(PrintWriter pw) {
		pw.println();
		pw.println();
		pw.println("'Population size:' " + Storage.GUIpopSize);
		pw.println("'Run for generations:' " + Storage.GUIgenerations);
		pw.println("'Autosomal recombination freq:' " + Storage.GUIARecFr);
		pw.println("'Y recombination freq:' " + Storage.GUIYRecFr);
		pw.println("'X recombination freq:' " + Storage.GUIXRecFr);
		pw.println("'Fusion recombination freq:' " + Storage.GUIFRecFr);
		pw.println("'Percent gene flow:' " + Storage.GUIgeneFlow);
		pw.println("'Zone Width:' " + Storage.GUIZoneWidth);
		pw.println("'Produce text file Nbr:' " + Storage.GUItxtFiles);
		pw.println("'Output every:' " + Storage.GUIOutputEvery);
		pw.println("'male fitness 1:' " + Storage.GUImaleFitness1);
		pw.println("'male fitness 2:' " + Storage.GUImaleFitness2);
		pw.println("'male fitness 3:' " + Storage.GUImaleFitness3);
		pw.println("'male fitness 4:' " + Storage.GUImaleFitness4);
		pw.println("'male fitness 5:' " + Storage.GUImaleFitness5);
		pw.println("'female fitness 1:' " + Storage.GUIfemaleFitness1);
		pw.println("'female fitness 2:' " + Storage.GUIfemaleFitness2);
		pw.println("'female fitness 3:' " + Storage.GUIfemaleFitness3);
		pw.println("'female fitness 4:' " + Storage.GUIfemaleFitness4);
		pw.println("'female fitness 5:' " + Storage.GUIfemaleFitness5);
		pw.println("'female fitness 6:' " + Storage.GUIfemaleFitness6);
		pw.println("'Program version: 1.4' ");
	}
}
