/* INDIVIDUAL STARTS HERE */
public class Individual {
	int[] egg;

	int[] sperm;

	int mtDNA;

	boolean femaleStatus;

	double[] reFr = Storage.getRecFrStandard();

	double indFit;

	public Individual(int[] EGG, int[] SPERM, int MTDNA) {
		//		 REMEBMER that egg cannot be gamete A or C!!! Otherwise the reFr is
		// not calculated correctly (only reads sperm values)
		//		REMEMBER individual must be called after the values have been read
		// from the GUI after pressing GO
		egg = EGG;
		sperm = SPERM;
		mtDNA = MTDNA;
		//			reFr = Storage.getRecFrStandard(); // start with the standard
		// recombination pattern

		//			reFr = {0, .1, .1, .5, .1, .1, 0.5, GUIARecFr, GUIARecFr,
		//				0.5, GUIARecFr, GUIARecFr, 0.5, GUIARecFr, GUIARecFr,
		//				0.5, GUIARecFr, 0.5, GUIARecFr};

		reFr[7] = Storage.GUIARecFr;
		reFr[8] = Storage.GUIARecFr;
		reFr[10] = Storage.GUIARecFr;
		reFr[11] = Storage.GUIARecFr;
		reFr[13] = Storage.GUIARecFr;
		reFr[14] = Storage.GUIARecFr;
		reFr[16] = Storage.GUIARecFr;
		reFr[18] = Storage.GUIARecFr;

		//deal with femalestatus
		if (sperm[0] == 1) {
			femaleStatus = true;
		} else
			femaleStatus = false;
		if (egg[0] == 0)
			System.out.println("An egg arose without an X chromosome!");

		//deal with recombination
		if (!femaleStatus) {
			reFr[1] = 0;
			reFr[2] = 0;
		}// if this is a male there is no recombination of the ancient X
		if ((sperm[3] == 1) || (egg[3] == 1)) {
			reFr[3] = Storage.GUIFRecFr;
		} else
			reFr[3] = 0.5;// if either chromosome is fused, then ancient X will
						  // assort with L3 unless recombination occurs
		if ((sperm[4] == 2) || (egg[4] == 2)) {
			reFr[4] = Storage.GUIYRecFr;
		} // if either L3 is of Y origin, it belongs to a suppressed
		  // recombination region
		if ((sperm[5] == 2) || (egg[5] == 2)) {
			reFr[5] = Storage.GUIYRecFr;
		} // e.g. an inversion

		//		Fitness assignment
		if ((!femaleStatus) && (sperm[4] == 2) && (egg[4] == 1)) {
			indFit = Storage.GUImaleFitness1;
		} //Normal Fused male
		if ((!femaleStatus)
				&& (((sperm[4] == 2) && (egg[4] == 0)) || ((sperm[4] == 0) && (egg[4] == 2)))) {
			indFit = Storage.GUImaleFitness2;
		} //detects problem if NeoX and NeoY have been coevolving
		if ((!femaleStatus) && (sperm[4] == 2) && (egg[4] == 2)) {
			indFit = Storage.GUImaleFitness3;
		} //Y overdose (or Y dosage compensated)
		if ((!femaleStatus) && (sperm[4] == 0) && (egg[4] == 0)) {
			indFit = Storage.GUImaleFitness4;
		} //Normal Unfused male
		if ((!femaleStatus) && (sperm[4] == 0) && (egg[4] == 1)) {
			indFit = Storage.GUImaleFitness5;
		} //No Y so problem if NeoX, NeoY have been coevolving

		if ((femaleStatus) && (sperm[4] == 1) && (egg[4] == 1)) {
			indFit = Storage.GUIfemaleFitness1;
		} //Normal Fused female
		if ((femaleStatus) && (sperm[4] == 0) && (egg[4] == 0)) {
			indFit = Storage.GUIfemaleFitness2;
		} //Normal Unfused female
		if ((femaleStatus)
				&& (((sperm[4] == 0) && (egg[4] == 1)) || ((sperm[4] == 1) && (egg[4] == 0)))) {
			indFit = Storage.GUIfemaleFitness3;
		} //NeoX, X have diverged (because of Y, otherwise same as autosomes
		  // diverging)
		if ((femaleStatus)
				&& (((sperm[4] == 2) && (egg[4] == 1)) || ((sperm[4] == 1) && (egg[4] == 2)))) {
			indFit = Storage.GUIfemaleFitness4;
		} //Y in females
		if ((femaleStatus)
				&& (((sperm[4] == 0) && (egg[4] == 2)) || ((sperm[4] == 2) && (egg[4] == 0)))) {
			indFit = Storage.GUIfemaleFitness5;
		} //Y in females AND no coevolved NeoX
		if ((femaleStatus) && (sperm[4] == 2) && (egg[4] == 2)) {
			indFit = Storage.GUIfemaleFitness6;
		} //2 Ys in females, no L3

	}

	//public String UFM, UFF, FM, FF;

	/*
	 * public Individual(String a) { // REMEBMER that egg cannot be gamete A or
	 * C!!! Otherwise the reFr is not calculated correctly (only reads sperm
	 * values) // REMEMBER individual must be called after the values have been
	 * read from the GUI after pressing GO
	 * 
	 * if (a.valueOf(UFM)) {sperm = Storage.gameteA;} egg = EGG; sperm = SPERM;
	 * mtDNA = MTDNA; // reFr = Storage.getRecFrStandard(); // start with the
	 * standard recombination pattern
	 *  // reFr = {0, .1, .1, .5, .1, .1, 0.5, GUIARecFr, GUIARecFr, // 0.5,
	 * GUIARecFr, GUIARecFr, 0.5, GUIARecFr, GUIARecFr, // 0.5, GUIARecFr, 0.5,
	 * GUIARecFr};
	 * 
	 * 
	 * 
	 * reFr[7] = Storage.GUIARecFr; reFr[8] = Storage.GUIARecFr; reFr[10] =
	 * Storage.GUIARecFr; reFr[11] = Storage.GUIARecFr; reFr[13] =
	 * Storage.GUIARecFr; reFr[14] = Storage.GUIARecFr; reFr[16] =
	 * Storage.GUIARecFr; reFr[18] = Storage.GUIARecFr;
	 * 
	 * //deal with femalestatus if (sperm[0]==1) {femaleStatus = true;} else
	 * femaleStatus = false; if (egg[0]==0) System.out.println("An egg arose
	 * without an X chromosome!");
	 * 
	 * //deal with recombination if (!femaleStatus) {reFr[1]=0;reFr[2]=0;}// if
	 * this is a male there is no recombination of the ancient X // else
	 * reFr[1]=0.15; or Storage.GUIXRecFr or Storage.ReFrStandard[1] if
	 * ((sperm[3]==1)||(egg[3]==1)) {reFr[3]=Storage.GUIFRecFr;} else
	 * reFr[3]=0.5;// if either chromosome is fused, then ancient X will assort
	 * with L3 unless recombination occurs if
	 * ((sperm[4]==2)||(egg[4]==2)){reFr[4]=Storage.GUIYRecFr;} // if either L3
	 * is of Y origin, it belongs to a suppressed recombination region if
	 * ((sperm[5]==2)||(egg[5]==2)){reFr[5]=Storage.GUIYRecFr;} // e.g. an
	 * inversion
	 *  // Fitness assignment if ((!femaleStatus)&&(sperm[4]==2)&&(egg[4]==1))
	 * {indFit = Storage.GUImaleFitness1;} //Normal Fused male if
	 * ((!femaleStatus)&&(((sperm[4]==2)&&(egg[4]==0))||((sperm[4]==0)&&(egg[4]==2))))
	 * {indFit = Storage.GUImaleFitness2;} //detects problem if NeoX and NeoY
	 * have been coevolving if ((!femaleStatus)&&(sperm[4]==2)&&(egg[4]==2))
	 * {indFit = Storage.GUImaleFitness3;} //Y overdose (or Y dosage
	 * compensated) if ((!femaleStatus)&&(sperm[4]==0)&&(egg[4]==0)) {indFit =
	 * Storage.GUImaleFitness4;} //Normal Unfused male if
	 * ((!femaleStatus)&&(sperm[4]==0)&&(egg[4]==1)) {indFit =
	 * Storage.GUImaleFitness5;} //No Y so problem if NeoX, NeoY have been
	 * coevolving
	 * 
	 * if ((femaleStatus)&&(sperm[4]==1)&&(egg[4]==1)) {indFit =
	 * Storage.GUIfemaleFitness1;} //Normal Fused female if
	 * ((femaleStatus)&&(sperm[4]==0)&&(egg[4]==0)) {indFit =
	 * Storage.GUIfemaleFitness2;} //Normal Unfused female if
	 * ((femaleStatus)&&(((sperm[4]==0)&&(egg[4]==1))||((sperm[4]==1)&&(egg[4]==0))))
	 * {indFit = Storage.GUIfemaleFitness3;} //NeoX, X have diverged (because of
	 * Y, otherwise same as autosomes diverging) if
	 * ((femaleStatus)&&(((sperm[4]==2)&&(egg[4]==1))||((sperm[4]==1)&&(egg[4]==2))))
	 * {indFit = Storage.GUIfemaleFitness4;} //Y in females if
	 * ((femaleStatus)&&(((sperm[4]==0)&&(egg[4]==2))||((sperm[4]==2)&&(egg[4]==0))))
	 * {indFit = Storage.GUIfemaleFitness5;} //Y in females AND no coevolved
	 * NeoX if ((femaleStatus)&&(sperm[4]==2)&&(egg[4]==2)) {indFit =
	 * Storage.GUIfemaleFitness6;} //2 Ys in females, no L3
	 * 
	 *  }
	 */
	public int[] Gamete() {
		boolean eggchosen;
		int[] a = new int[egg.length];
		eggchosen = (Math.random() < 0.5);
		if (eggchosen)
			a[0] = egg[0];
		else
			a[0] = sperm[0];

		for (int i = 1; i < a.length; i++) {
			if (Math.random() < reFr[i - 1])
				eggchosen = !(eggchosen);
			if (eggchosen)
				a[i] = egg[i];
			else
				a[i] = sperm[i];
		}
		return a;
	}

	public boolean getFemaleStatus() {
		boolean a = femaleStatus;
		return a;
	}

	public static void questionIndividual(Individual a) {
		System.out.println("This is Individual: " + a);
		System.out.println("mtDNA is: " + a.mtDNA);
		System.out.println("femaleStatus is: " + a.femaleStatus);
		System.out.println("Gametes: ");
		System.out.print("Sperm: \t\t\t");
		for (int i = 0; i < 20; i++)
			System.out.print("\t" + a.sperm[i]);
		System.out.println();
		System.out.print("Egg: \t\t\t");
		for (int i = 0; i < 20; i++)
			System.out.print("\t" + a.egg[i]);
		System.out.println();
		System.out.print("Recombination frequencies:");
		System.out.println("\t" + a.reFr[0] + "\t" + a.reFr[1] + "\t"
				+ a.reFr[2] + "\t" + a.reFr[3] + "\t" + a.reFr[4] + "\t"
				+ a.reFr[5] + "\t" + a.reFr[6] + "\t" + a.reFr[7] + "\t"
				+ a.reFr[8] + "\t" + a.reFr[9] + "\t" + a.reFr[10] + "\t"
				+ a.reFr[11] + "\t" + a.reFr[12] + "\t" + a.reFr[13] + "\t"
				+ a.reFr[14] + "\t" + a.reFr[15] + "\t" + a.reFr[16] + "\t"
				+ a.reFr[17] + "\t" + a.reFr[18]);
		System.out
				.println("\t\t\t\t M/F \t X \t X \t Fusn \t L3 \t L3 \t0.5 \t A \t A \t 0.5 \t A \t A \t 0.5 \t A \t A \t 0.5 \t A \t 0.5 \t A");
		System.out.println("Individual fitness is: " + a.indFit);

	}
}
