import javax.swing.JCheckBox;

public class Storage {
	static int gameteD[] = { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
			0, 0, 0 };

	static int gameteB[] = { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
			1, 1, 1 };

	static int gameteE[] = { 1, 0, 0, 0, 2, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
			0, 0, 0 };

	static int gameteC[] = { 0, 5, 5, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
			0, 0, 0 };

	static int gameteA[] = { 0, 5, 5, 5, 2, 2, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
			1, 1, 1 };

	static Population zonePop1, zonePop2, zonePop3, zonePop4, zonePop5,
			zonePop6, zonePop7, zonePop8, zonePop9, zonePop10, zonePop11,
			zonePop12, zonePop13, zonePop14, zonePop15, zonePop16, zonePop17,
			zonePop18, zonePop19, zonePop20;

	static double GUIXRecFr, GUIARecFr, GUIYRecFr, GUIFRecFr, GUIgeneFlow,
			GUImaleFitness1, GUImaleFitness2, GUImaleFitness3, GUImaleFitness4,
			GUImaleFitness5, GUIfemaleFitness1, GUIfemaleFitness2,
			GUIfemaleFitness3, GUIfemaleFitness4, GUIfemaleFitness5,
			GUIfemaleFitness6;

	static int GUIpopSize, GUIgenerations, GUItxtFiles, GUIZoneWidth,
			GUIOutputEvery, GUIgeneFlowGenerations;

	static double recFrStandard[] = { 0, GUIXRecFr, GUIXRecFr, .5, .1, .1, 0.5,
			GUIARecFr, GUIARecFr, 0.5, GUIARecFr, GUIARecFr, 0.5, GUIARecFr,
			GUIARecFr, 0.5, GUIARecFr, 0.5, GUIARecFr };

	static String boxText, edgeText, GUIsimName;
	static boolean GUIYinFemalesStatus, GUINextToYinFemalesStatus,
			GUImtDNAStatus, GUIAutosomalAlleleStatus, GUITotalYStatus,
			GUIObservedZoneStatus, GUIShowFemalesStatus, GUITotalFitnessStatus,
			GUIMaleFitnessStatus, GUIFemaleFitnessStatus,
			GUIFemaleChrClineStatus, GUIChrClineStatus,
			GUIAllIndividualsStatus;
	static double DfitnessTable[][] = { { 0, 0, 0 }, { 0, 0, 0 }, { 0, 0, 0 },
			{ 0, 0, 0 }, { 0, 0, 0 } };

	public static int[] getGamete(String gameteGotten) {
		int[] a = gameteA; // because I need to initialise it apparently
		if (gameteGotten == "A") {
			a = gameteA;
		} else if (gameteGotten == "B") {
			a = gameteB;
		} else if (gameteGotten == "C") {
			a = gameteC;
		} else if (gameteGotten == "D") {
			a = gameteD;
		} else if (gameteGotten == "E") {
			a = gameteE;
		} else
			System.out.println("Problem getting gamete");

		return a;
	}

	public static double[] getRecFrStandard() {
		double[] a = new double[recFrStandard.length];
		for (int i = 0; i < recFrStandard.length; i++)
			a[i] = recFrStandard[i];
		return a;
	}

	public static void readGUI(PodismaGUI a) {
		Storage.boxText = (String) a.modeBox.getSelectedItem();
		Storage.edgeText = (String) a.zoneEdgeBox.getSelectedItem();
		Storage.GUIpopSize = Integer.parseInt(a.popSizeTxtfld.getText());
		Storage.GUIgenerations = Integer
				.parseInt(a.generationsTxtfld.getText());
		Storage.GUIXRecFr = Double.parseDouble(a.XRecFrTxtfld.getText());
		Storage.GUIARecFr = Double.parseDouble(a.ARecFrTxtfld.getText());
		Storage.GUIFRecFr = Double.parseDouble(a.FRecFrTxtfld.getText());
		Storage.GUIYRecFr = Double.parseDouble(a.YRecFrTxtfld.getText());
		Storage.GUIgeneFlow = Double.parseDouble(a.geneFlowTxtfld.getText());
		Storage.GUIZoneWidth = Integer.parseInt(a.zoneWidthTxtfld.getText());
		Storage.GUItxtFiles = Integer.parseInt(a.txtFilesTxtfld.getText());
		Storage.GUIgeneFlowGenerations = Integer
				.parseInt(a.geneFlowGenerationsTxtfld.getText());
		Storage.GUImaleFitness1 = Double.parseDouble(a.maleFitness1Txtfld
				.getText());
		Storage.GUImaleFitness2 = Double.parseDouble(a.maleFitness2Txtfld
				.getText());
		Storage.GUImaleFitness3 = Double.parseDouble(a.maleFitness3Txtfld
				.getText());
		Storage.GUImaleFitness4 = Double.parseDouble(a.maleFitness4Txtfld
				.getText());
		Storage.GUImaleFitness5 = Double.parseDouble(a.maleFitness5Txtfld
				.getText());
		Storage.GUIfemaleFitness1 = Double.parseDouble(a.femaleFitness1Txtfld
				.getText());
		Storage.GUIfemaleFitness2 = Double.parseDouble(a.femaleFitness2Txtfld
				.getText());
		Storage.GUIfemaleFitness3 = Double.parseDouble(a.femaleFitness3Txtfld
				.getText());
		Storage.GUIfemaleFitness4 = Double.parseDouble(a.femaleFitness4Txtfld
				.getText());
		Storage.GUIfemaleFitness5 = Double.parseDouble(a.femaleFitness5Txtfld
				.getText());
		Storage.GUIfemaleFitness6 = Double.parseDouble(a.femaleFitness6Txtfld				
				.getText());
//		Storage.GUIsimName = String.parseString(a.simName
//				.getText());
		Storage.GUIsimName = a.simName.getText().toString();
		Storage.recFrStandard[0] = 0;
		Storage.recFrStandard[1] = 0.1;
		Storage.recFrStandard[2] = 0.1;
		Storage.recFrStandard[3] = 0.5;
		Storage.recFrStandard[4] = 0.1;
		Storage.recFrStandard[5] = 0.1;
		Storage.recFrStandard[6] = 0.5;
		Storage.recFrStandard[7] = Storage.GUIARecFr;
		Storage.recFrStandard[8] = GUIARecFr;
		Storage.recFrStandard[9] = 0.5;
		Storage.recFrStandard[10] = GUIARecFr;
		Storage.recFrStandard[11] = GUIARecFr;
		Storage.recFrStandard[12] = 0.5;
		Storage.recFrStandard[13] = GUIARecFr;
		Storage.recFrStandard[14] = GUIARecFr;
		Storage.recFrStandard[15] = 0.5;
		Storage.recFrStandard[16] = GUIARecFr;
		Storage.recFrStandard[17] = 0.5;
		Storage.recFrStandard[18] = GUIARecFr;

		Storage.GUIYinFemalesStatus = a.YinFemales.isSelected();
		Storage.GUINextToYinFemalesStatus = a.NextToYinFemales.isSelected();
		Storage.GUImtDNAStatus = a.mtDNA.isSelected();
		Storage.GUIAutosomalAlleleStatus = a.AutosomalAllele.isSelected();
		Storage.GUITotalYStatus = a.TotalY.isSelected();
		Storage.GUIObservedZoneStatus = a.ObservedZone.isSelected();
		Storage.GUIShowFemalesStatus = a.ShowFemales.isSelected();
		Storage.GUITotalFitnessStatus = a.TotalFitness.isSelected();
		Storage.GUIMaleFitnessStatus = a.MaleFitness.isSelected();
		Storage.GUIFemaleFitnessStatus = a.FemaleFitness.isSelected();
		Storage.GUIFemaleChrClineStatus = a.FemaleZone.isSelected();
		Storage.GUIChrClineStatus = a.ChrCline.isSelected();
		Storage.GUIAllIndividualsStatus = a.AllIndividuals.isSelected();

		Storage.GUIOutputEvery = Integer
				.parseInt(a.OutputEveryTxtfld.getText());

		Storage.DfitnessTable[0][0] = Double.parseDouble(a.maleFitness1Txtfld
				.getText());
		;
		Storage.DfitnessTable[1][0] = Double.parseDouble(a.maleFitness5Txtfld
				.getText());
		Storage.DfitnessTable[2][0] = Double.parseDouble(a.femaleFitness1Txtfld
				.getText());
		Storage.DfitnessTable[3][0] = Double.parseDouble(a.femaleFitness3Txtfld
				.getText());
		Storage.DfitnessTable[4][0] = Double.parseDouble(a.femaleFitness4Txtfld
				.getText());
		Storage.DfitnessTable[0][1] = Double.parseDouble(a.maleFitness2Txtfld
				.getText());
		Storage.DfitnessTable[1][1] = Double.parseDouble(a.maleFitness4Txtfld
				.getText());
		Storage.DfitnessTable[2][1] = Double.parseDouble(a.femaleFitness3Txtfld
				.getText());
		Storage.DfitnessTable[3][1] = Double.parseDouble(a.femaleFitness2Txtfld
				.getText());
		Storage.DfitnessTable[4][1] = Double.parseDouble(a.femaleFitness5Txtfld
				.getText());
		Storage.DfitnessTable[0][2] = Double.parseDouble(a.maleFitness3Txtfld
				.getText());
		Storage.DfitnessTable[1][2] = Double.parseDouble(a.maleFitness2Txtfld
				.getText());
		Storage.DfitnessTable[2][2] = Double.parseDouble(a.femaleFitness4Txtfld
				.getText());
		Storage.DfitnessTable[3][2] = Double.parseDouble(a.femaleFitness5Txtfld
				.getText());
		Storage.DfitnessTable[4][2] = Double.parseDouble(a.femaleFitness6Txtfld
				.getText());

		// 14/09/2007 Figure 5.1 karyotypes compare to the following:
		// A-->MaleFitness1 (MF1)
		// B-->MF4, C-->MF2, D-->MF5, E-->MF3, F-->FF1, G-->FF2, H-->FF3,
		// I-->FF4, J-->FF5, K-->FF6

		// This silly looking code because array initialiser can only be used at
		// the beginning.
	}

	/*
	 * these are the original gametes and recombination frequencies we though to
	 * use. They have now been replaced. final double reFrD [] = {0.1, 0.1, 0.5,
	 * 0.1, 0.1, 0.5, 0.1, 0.1, 0.5, 0.1, 0.1, 0.5, 0.1, 0.1, 0.5, 0.1, 0.5,
	 * 0.1, 0.5}; final double reFrB [] = {0.1, 0.1, 0.1, 0.1, 0.1, 0.5, 0.1,
	 * 0.1, 0.5, 0.1, 0.1, 0.5, 0.1, 0.1, 0.5, 0.1, 0.5, 0.1, 0.5}; final double
	 * reFrE [] = {0.1, 0.1, 0.5, 0.01, 0.01, 0.5, 0.1, 0.1, 0.5, 0.1, 0.1, 0.5,
	 * 0.1, 0.1, 0.5, 0.1, 0.5, 0.1, 0.5}; final double reFrC [] = {0, 0, 0.5,
	 * 0.1, 0.1, 0.5, 0.1, 0.1, 0.5, 0.1, 0.1, 0.5, 0.1, 0.1, 0.5, 0.1, 0.5,
	 * 0.1, 0.5}; final double reFrA [] = {0, 0, 0.5, 0.01, 0.01, 0.5, 0.1, 0.1,
	 * 0.5, 0.1, 0.1, 0.5, 0.1, 0.1, 0.5, 0.1, 0.5, 0.1, 0.5};
	 */

}