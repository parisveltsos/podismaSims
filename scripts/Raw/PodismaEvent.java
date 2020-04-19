
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

import javax.swing.JCheckBox;


//public class PodismaEvent implements ActionListener, Runnable {
public class PodismaEvent implements ActionListener {
	public PodismaGUI gui;

	public PodismaEvent(PodismaGUI in) {
		gui = in;
	}

	public void actionPerformed(ActionEvent event) {
		  
		//	if (event.getSource() == go) {
		String command = event.getActionCommand();
		Storage.readGUI(gui); //updates all values in storage with the ones in GUI, the first thing to do!
		 		
		if (command == "comboBoxChanged") {
				if (Storage.boxText == " ") {
				gui.outputFld.setText("please select a mode to run the program");
			
			}
			else if (Storage.boxText == "deterministicMode") {
				gui.popSizeTxtfld.setEnabled(false);
				gui.ARecFrTxtfld.setEnabled(false);
				gui.XRecFrTxtfld.setEnabled(false);
				gui.YRecFrTxtfld.setEnabled(false);
				gui.FRecFrTxtfld.setEnabled(false);
				gui.geneFlowGenerationsTxtfld.setEnabled(true);
				
				gui.NextToYinFemales.setEnabled(false);
				gui.mtDNA.setEnabled(false);
				gui.ShowFemales.setEnabled(false);
				
				gui.FemaleZone.setEnabled(true);
				gui.ChrCline.setEnabled(true);
				gui.AllIndividuals.setEnabled(true);
				
				gui.outputFld.setText("Deterministic mode selected: no genetic drift");
			
			}
			else if (Storage.boxText == "stochasticMode") {
				gui.popSizeTxtfld.setEnabled(true);
				gui.ARecFrTxtfld.setEnabled(true);
				gui.XRecFrTxtfld.setEnabled(true);
				gui.YRecFrTxtfld.setEnabled(true);
				gui.FRecFrTxtfld.setEnabled(true);
				gui.geneFlowGenerationsTxtfld.setEnabled(false);
				
				gui.NextToYinFemales.setEnabled(true);
				gui.mtDNA.setEnabled(true);
				gui.ShowFemales.setEnabled(true);
				
				gui.FemaleZone.setEnabled(false);
				gui.ChrCline.setEnabled(false);
				gui.AllIndividuals.setEnabled(false);
				
				gui.outputFld.setText("Stochastic mode selected: result will be different between different runs");
			
			}
	
			if (Storage.edgeText == " ") {
				gui.outputFld.setText("please select whether to allow edge effects");
			}
			else if (Storage.edgeText == "edgeEffects") {
				gui.outputFld.setText("The zone will be disturbed by edge effects");
				
			}
			else if (Storage.edgeText == "noEdgeEffects") {
				gui.outputFld.setText("Pure populations at the edges can be replaced by recombinant populations");
	
				}
				
			
			if (((Storage.edgeText == "edgeEffects") || (Storage.edgeText == "noEdgeEffects")) && 
			((Storage.boxText == "stochasticMode") || (Storage.boxText == "deterministicMode"))) {
				gui.go.setEnabled(true);
				gui.repaint();
			}
			else gui.go.setEnabled(false);
			gui.repaint();
		
		//	else System.out.println("Unknown edgeBox command"); //this is not needed unless the box functionality changes.
			}
		
		if (command == "Go!") {
				if (Storage.boxText == "stochasticMode") {
			
			System.out.println("Stochastic is working ");
			System.out.println("Storage mtDNA status is: " + Storage.GUImtDNAStatus);

			//			gui.outputFld.setText("Text file creation in progress.....");

			// I need individuals to make my starting populations with. Remember
			// that the egg position should only recieve egg gametes.
			Individual hopper1 = new Individual(Storage.getGamete("D"), Storage
					.getGamete("D"), 0);
			//starting female for the first population, the one below is a
			// starting male
			Individual hopper2 = new Individual(Storage.getGamete("B"), Storage
					.getGamete("A"), 1); //UF individual has mtDNA of 0 while
			// the other has 1
			Individual UFM = new Individual(Storage.getGamete("D"), Storage
					.getGamete("C"), 0); //THESE need to be replaced by a
			// method, this particular one should
			// give the same gametes from the
			// opposite combination of sperm and
			// egg 50% of the time too!
			Individual UFF = new Individual(Storage.getGamete("D"), Storage
					.getGamete("D"), 0);
			Individual FM = new Individual(Storage.getGamete("B"), Storage
					.getGamete("A"), 1);
			Individual FF = new Individual(Storage.getGamete("B"), Storage
					.getGamete("B"), 1);

			/*
			 * System.out.println(); System.out.println("hopper 1 is: ");
			 * Individual.questionIndividual(hopper1);
			 */

			String TrYinFemalesName, TrNextToYinFemalesName, TrmtDNAName, TrAutosomalAlleleName, TrTotalYName, TrObservedZoneName, TrShowFemalesName, TrTotalFitnessName, TrMaleFitnessName, TrFemaleFitnessName, TrFemaleMtDNAName;

			for (int txtFileNbr = 0; txtFileNbr < Storage.GUItxtFiles; txtFileNbr++) {
				Population[] newTransect = new Population[Storage.GUIZoneWidth];
				Population[] oldTransect = new Population[Storage.GUIZoneWidth];
				Population[] beforeFlowTransect = new Population[Storage.GUIZoneWidth];
				for (int j = 0; j < Storage.GUIZoneWidth; j++) {
					newTransect[j] = new Population();
					beforeFlowTransect[j] = new Population();
				}

				for (int i = 0; i < Storage.GUIZoneWidth; i++) {
					if (i < (int) (Storage.GUIZoneWidth / 2))
						oldTransect[i] = new Population(UFF, UFM,
								Storage.GUIpopSize);
					if (i >= (int) (Storage.GUIZoneWidth / 2))
						oldTransect[i] = new Population(FF, FM,
								Storage.GUIpopSize);
				

				}
				// 	  		System.out.println("This is the very first transect before
				// anything happens");
				// 	  		QuestionZone2(oldTransect);

				PrintWriter TrYinFemalesOutput;
				PrintWriter TrNextToYinFemalesOutput;
				PrintWriter TrmtDNAOutput;
				PrintWriter TrAutosomalAlleleOutput;
				PrintWriter TrTotalYOutput;
				PrintWriter TrObservedZoneOutput;
				PrintWriter TrShowFemalesOutput;
				PrintWriter TrTotalFitnessOutput;
				PrintWriter TrMaleFitnessOutput;
				PrintWriter TrFemaleFitnessOutput;
				PrintWriter TrFemaleMtDNAOutput;
				String xmlDir = Storage.GUIsimName;
				File xmlDirectory = new File(xmlDir); 
				xmlDirectory.mkdirs();
				
				try {
					TrYinFemalesName = Storage.GUIsimName + "/Repl" + txtFileNbr
							+ "TrOutputYinFemales.txt";
					TrNextToYinFemalesName = Storage.GUIsimName + "/Repl" + txtFileNbr
							+ "TrNextToYinFemales.txt";
					TrmtDNAName = Storage.GUIsimName + "/Repl" + txtFileNbr + "TrmtDNA.txt";
					TrAutosomalAlleleName = Storage.GUIsimName + "/Repl" + txtFileNbr
							+ "TrAutosomalAllele.txt";
					TrTotalYName = Storage.GUIsimName + "/Repl" + txtFileNbr + "TrTotalY.txt";
					TrObservedZoneName = Storage.GUIsimName + "/Repl" + txtFileNbr
							+ "TrObservedZone.txt";
					TrShowFemalesName = Storage.GUIsimName + "/Repl" + txtFileNbr
							+ "TrShoeFemales.txt";
					TrTotalFitnessName = Storage.GUIsimName + "/Repl" + txtFileNbr
							+ "TrTotalFitness.txt";
					TrMaleFitnessName = Storage.GUIsimName + "/Repl" + txtFileNbr
							+ "TrMaleFitness.txt";
					TrFemaleFitnessName = Storage.GUIsimName + "/Repl" + txtFileNbr
							+ "TrFemaleFitness.txt";
					TrFemaleMtDNAName = Storage.GUIsimName + "/Repl" + txtFileNbr
							+ "TrFemaleMtDNA.txt";

					TrYinFemalesOutput = new PrintWriter(new FileWriter(
							TrYinFemalesName));
					TrNextToYinFemalesOutput = new PrintWriter(new FileWriter(
							TrNextToYinFemalesName));
					TrmtDNAOutput = new PrintWriter(new FileWriter(TrmtDNAName));
					TrAutosomalAlleleOutput = new PrintWriter(new FileWriter(
							TrAutosomalAlleleName));
					TrTotalYOutput = new PrintWriter(new FileWriter(
							TrTotalYName));
					TrObservedZoneOutput = new PrintWriter(new FileWriter(
							TrObservedZoneName));
					TrShowFemalesOutput = new PrintWriter(new FileWriter(
							TrShowFemalesName));
					TrTotalFitnessOutput = new PrintWriter(new FileWriter(
							TrTotalFitnessName));
					TrMaleFitnessOutput = new PrintWriter(new FileWriter(
							TrMaleFitnessName));
					TrFemaleFitnessOutput = new PrintWriter(new FileWriter(
							TrFemaleFitnessName));
					TrFemaleMtDNAOutput = new PrintWriter(new FileWriter(
							TrFemaleMtDNAName));
					
					for (int j = 0; j < Storage.GUIgenerations; j++) {
						beforeFlowTransect = nextGeneration(oldTransect);
						newTransect = geneFlow(beforeFlowTransect);
						if ((j%Storage.GUIOutputEvery)==0) {
						for (int f = 0; f < Storage.GUIZoneWidth; f++) {
							//this if statement is to add a zero in the 1-9
							// outputs so that excel can sort them properly
							if (f < 10) {
								if (Storage.GUIYinFemalesStatus) {
									TrYinFemalesOutput.print("'Generation " + j
											+ "' 'ZoneLocation0" + f + "'");
								}
								if (Storage.GUINextToYinFemalesStatus) {
									TrNextToYinFemalesOutput
											.print("'Generation " + j
													+ "' 'ZoneLocation0" + f
													+ "'");
								}
								if (Storage.GUImtDNAStatus) {
									TrmtDNAOutput.print("'Generation " + j
											+ "' 'ZoneLocation0" + f + "'");
								}
								if (Storage.GUIAutosomalAlleleStatus) {
									TrAutosomalAlleleOutput
											.print("'Generation " + j
													+ "' 'ZoneLocation0" + f
													+ "'");
								}
								if (Storage.GUITotalYStatus) {
									TrTotalYOutput.print("'Generation " + j
											+ "' 'ZoneLocation0" + f + "'");
								}
								if (Storage.GUIObservedZoneStatus) {
									TrObservedZoneOutput.print("'Generation "
											+ j + "' 'ZoneLocation0" + f + "'");
								}
								if (Storage.GUIShowFemalesStatus) {
									TrShowFemalesOutput.print("'Generation "
											+ j + "' 'ZoneLocation0" + f + "'");
								}
								if (Storage.GUITotalFitnessStatus) {
									TrTotalFitnessOutput.print("'Generation "
											+ j + "' 'ZoneLocation0" + f + "'");
								}
								if (Storage.GUIMaleFitnessStatus) {
									TrMaleFitnessOutput.print("'Generation "
											+ j + "' 'ZoneLocation0" + f + "'");
								}
								if (Storage.GUIFemaleFitnessStatus) {
									TrFemaleFitnessOutput.print("'Generation "
											+ j + "' 'ZoneLocation0" + f + "'");
								}
								TrFemaleMtDNAOutput.print("'Generation " + j
										+ "' 'ZoneLocation0" + f + "'");
							}

							else {
								if (Storage.GUIYinFemalesStatus) {
									TrYinFemalesOutput.print("'Generation " + j
											+ "' 'ZoneLocation" + f + "'");
								}
								if (Storage.GUINextToYinFemalesStatus) {
									TrNextToYinFemalesOutput
											.print("'Generation " + j
													+ "' 'ZoneLocation" + f
													+ "'");
								}
								if (Storage.GUImtDNAStatus) {
									TrmtDNAOutput.print("'Generation " + j
											+ "' 'ZoneLocation" + f + "'");
								}
								if (Storage.GUIAutosomalAlleleStatus) {
									TrAutosomalAlleleOutput
											.print("'Generation " + j
													+ "' 'ZoneLocation" + f
													+ "'");
								}
								if (Storage.GUITotalYStatus) {
									TrTotalYOutput.print("'Generation " + j
											+ "' 'ZoneLocation" + f + "'");
								}
								if (Storage.GUIObservedZoneStatus) {
									TrObservedZoneOutput.print("'Generation "
											+ j + "' 'ZoneLocation" + f + "'");
								}
								if (Storage.GUIShowFemalesStatus) {
									TrShowFemalesOutput.print("'Generation "
											+ j + "' 'ZoneLocation" + f + "'");
								}
								if (Storage.GUITotalFitnessStatus) {
									TrTotalFitnessOutput.print("'Generation "
											+ j + "' 'ZoneLocation" + f + "'");
								}
								if (Storage.GUIMaleFitnessStatus) {
									TrMaleFitnessOutput.print("'Generation "
											+ j + "' 'ZoneLocation" + f + "'");
								}
								if (Storage.GUIFemaleFitnessStatus) {
									TrFemaleFitnessOutput.print("'Generation "
											+ j + "' 'ZoneLocation" + f + "'");
								}
								TrFemaleMtDNAOutput.print("'Generation " + j
										+ "' 'ZoneLocation" + f + "'");
							}

							if (Storage.GUIYinFemalesStatus) {
								newTransect[f]
										.ShowYinFemales(TrYinFemalesOutput);
							}
							if (Storage.GUINextToYinFemalesStatus) {
								newTransect[f]
										.ShowNextToYinFemales(TrNextToYinFemalesOutput);
							}
							if (Storage.GUImtDNAStatus) {
								newTransect[f].ShowmtDNA(TrmtDNAOutput);
							}
							if (Storage.GUIAutosomalAlleleStatus) {
								newTransect[f]
										.ShowAutosomalAllele(TrAutosomalAlleleOutput);
							}
							if (Storage.GUITotalYStatus) {
								newTransect[f].ShowTotalY(TrTotalYOutput);
							}
							if (Storage.GUIObservedZoneStatus) {
								newTransect[f]
										.ShowObservedZone(TrObservedZoneOutput);
							}
							if (Storage.GUIShowFemalesStatus) {
								newTransect[f].ShowFemales(TrShowFemalesOutput);
							}
							if (Storage.GUITotalFitnessStatus) {
								newTransect[f]
										.ShowTotalFitness(TrTotalFitnessOutput);
							}
							if (Storage.GUIMaleFitnessStatus) {
								newTransect[f]
										.ShowMaleFitness(TrMaleFitnessOutput);
							}
							if (Storage.GUIFemaleFitnessStatus) {
								newTransect[f]
										.ShowFemaleFitness(TrFemaleFitnessOutput);
							}
							newTransect[f].ShowFemalemtDNA(TrFemaleMtDNAOutput);
						}
					}
						oldTransect = newTransect;

						// 			QuestionZone2(newTransect);
						// 				System.out.println("");

					}
					TrYinFemalesOutput.println();
					oldTransect[(int) (Storage.GUIZoneWidth / 2)]
							.addSimParameters(TrYinFemalesOutput);
					TrYinFemalesOutput.close();
					TrNextToYinFemalesOutput.println();
					oldTransect[(int) (Storage.GUIZoneWidth / 2)]
							.addSimParameters(TrNextToYinFemalesOutput);
					TrNextToYinFemalesOutput.close();
					TrmtDNAOutput.println();
					oldTransect[(int) (Storage.GUIZoneWidth / 2)]
							.addSimParameters(TrmtDNAOutput);
					TrmtDNAOutput.close();
					TrAutosomalAlleleOutput.println();
					oldTransect[(int) (Storage.GUIZoneWidth / 2)]
							.addSimParameters(TrAutosomalAlleleOutput);
					TrAutosomalAlleleOutput.close();
					TrTotalYOutput.println();
					oldTransect[(int) (Storage.GUIZoneWidth / 2)]
							.addSimParameters(TrTotalYOutput);
					TrTotalYOutput.close();
					TrObservedZoneOutput.println();
					oldTransect[(int) (Storage.GUIZoneWidth / 2)]
							.addSimParameters(TrObservedZoneOutput);
					TrObservedZoneOutput.close();
					TrShowFemalesOutput.println();
					oldTransect[(int) (Storage.GUIZoneWidth / 2)]
							.addSimParameters(TrShowFemalesOutput);
					TrShowFemalesOutput.close();
					TrTotalFitnessOutput.println();
					oldTransect[(int) (Storage.GUIZoneWidth / 2)]
							.addSimParameters(TrTotalFitnessOutput);
					TrTotalFitnessOutput.close();
					TrMaleFitnessOutput.println();
					oldTransect[(int) (Storage.GUIZoneWidth / 2)]
							.addSimParameters(TrMaleFitnessOutput);
					TrMaleFitnessOutput.close();
					TrFemaleFitnessOutput.println();
					oldTransect[(int) (Storage.GUIZoneWidth / 2)]
							.addSimParameters(TrFemaleFitnessOutput);
					TrFemaleFitnessOutput.close();
					TrFemaleMtDNAOutput.println();
					oldTransect[(int) (Storage.GUIZoneWidth / 2)]
							.addSimParameters(TrFemaleMtDNAOutput);
					TrFemaleMtDNAOutput.close();
					/*
					 * Scanner sc = new Scanner(ReplT0rOutputYinFemales.txt);
					 * System.out.println(sc.next());
					 * System.out.println(sc.next());
					 * System.out.println(sc.next());
					 * System.out.println(sc.next());
					 * System.out.println(sc.next());
					 */

				}

				catch (IOException ioException) {
					System.out.println("Error with file");
				}

			}
			gui.outputFld.setText("Output stochastic files are done!!!");
		} 
		
		//DETERMINISTIC MODEL STARTS HERE
		 if (Storage.boxText == "deterministicMode") {
			System.out.println("to koubi patithike");
			Storage.readGUI(gui); //updates all values in storage with the ones in GUI
System.out.println("Deterministic is working ");
System.out.println("Storage fitness table is: ");
System.out.println(" " + Storage.DfitnessTable[0][0] +" " +Storage.DfitnessTable[1][0] + " " +Storage.DfitnessTable[2][0] + " " +Storage.DfitnessTable[3][0] + " " +Storage.DfitnessTable[4][0]);
System.out.println(" " + Storage.DfitnessTable[0][1] +" " +Storage.DfitnessTable[1][1] + " " +Storage.DfitnessTable[2][1] + " " +Storage.DfitnessTable[3][1] + " " +Storage.DfitnessTable[4][1]);
System.out.println(" " + Storage.DfitnessTable[0][2] +" " +Storage.DfitnessTable[1][2] + " " +Storage.DfitnessTable[2][2] + " " +Storage.DfitnessTable[3][2] + " " +Storage.DfitnessTable[4][2]);
			
		String DetM1Name, DetM2Name, DetM3Name, DetM4Name, DetM5Name, DetF1Name, DetF2Name, DetF3Name, DetF4Name, DetF5Name, DetF6Name;	
		String DetYinFemalesName, DetTotalYName, DetAllFitnessName, DetMaleFitnessName, DetFemaleFitnessName, DetMaleChrClineName, DetFemaleChrClineName, DetChrClineName;
	
		//Will use these to initialise the first population
		DPopulation UFDP = new DPopulation();
		UFDP.DPopulationUnfused();
		//Zaba
		//UFDP.DPopulationUnfusedY();
		DPopulation FDP = new DPopulation();
		FDP.DPopulationFused();
		
		for (int txtFileNbr=0; txtFileNbr<Storage.GUItxtFiles; txtFileNbr++) {
			DPopulation[] fitnessedDpopTransect = new DPopulation[Storage.GUIZoneWidth];
			DPopulation[] startingDpopTransect = new DPopulation[Storage.GUIZoneWidth];
			DPopulation[] geneFlowedDpopTransect = new DPopulation[Storage.GUIZoneWidth];
			DPopulation[] nextGenDpopTransect = new DPopulation[Storage.GUIZoneWidth];
			double[][] gametesDpopTransect = new double[Storage.GUIZoneWidth][8];
				
				for (int j=0;j<Storage.GUIZoneWidth;j++) {
					fitnessedDpopTransect[j] = new DPopulation(); //probably dont need to use this one
					geneFlowedDpopTransect[j] = new DPopulation();
					nextGenDpopTransect[j] = new DPopulation();
						for (int r=0;r<8;r++) {
						gametesDpopTransect[j][r]=0;
						}
					}
				for (int i=0;i<Storage.GUIZoneWidth;i++) {
					if (i < (int) (Storage.GUIZoneWidth / 2))
						startingDpopTransect[i] = UFDP;
					if (i >= (int) (Storage.GUIZoneWidth / 2))
						startingDpopTransect[i] = FDP;
				}

				
		PrintWriter DetM1NameOutput, DetM2NameOutput, DetM3NameOutput, DetM4NameOutput, DetM5NameOutput, DetF1NameOutput, DetF2NameOutput, DetF3NameOutput, DetF4NameOutput, DetF5NameOutput, DetF6NameOutput;	
		PrintWriter DetYinFemalesNameOutput, DetTotalYNameOutput, DetAllFitnessNameOutput, DetMaleFitnessNameOutput, DetFemaleFitnessNameOutput, DetMaleChrClineNameOutput, DetFemaleChrClineNameOutput, DetChrClineNameOutput;
			
		try {
			DetM1Name = "Repl" +txtFileNbr + "DetM1.txt";
			DetM2Name = "Repl" +txtFileNbr + "DetM2.txt";
			DetM3Name = "Repl" +txtFileNbr + "DetM3.txt";
			DetM4Name = "Repl" +txtFileNbr + "DetM4.txt";
			DetM5Name = "Repl" +txtFileNbr + "DetM5.txt";
			DetF1Name = "Repl" +txtFileNbr + "DetF1.txt";
			DetF2Name = "Repl" +txtFileNbr + "DetF2.txt";
			DetF3Name = "Repl" +txtFileNbr + "DetF3.txt";
			DetF4Name = "Repl" +txtFileNbr + "DetF4.txt";
			DetF5Name = "Repl" +txtFileNbr + "DetF5.txt";
			DetF6Name = "Repl" +txtFileNbr + "DetF6.txt";	
			DetYinFemalesName = "Repl" +txtFileNbr + "DetYinFemales.txt";
			DetTotalYName = "Repl" +txtFileNbr + "DetTotalY.txt";
			DetAllFitnessName = "Repl" +txtFileNbr + "DetAllFitness.txt";
			DetMaleFitnessName = "Repl" +txtFileNbr + "DetMaleFitness.txt";
			DetFemaleFitnessName = "Repl" +txtFileNbr + "DetFemaleFitness.txt";
			DetMaleChrClineName = "Repl" +txtFileNbr + "DetMaleChrCline.txt";
			DetFemaleChrClineName = "Repl" +txtFileNbr + "DetFemaleChrCline.txt";
			DetChrClineName = "Repl" +txtFileNbr + "DetChrCline.txt";
			
			DetM1NameOutput = new PrintWriter(new FileWriter(DetM1Name));
			DetM2NameOutput = new PrintWriter(new FileWriter(DetM2Name));
			DetM3NameOutput = new PrintWriter(new FileWriter(DetM3Name));
			DetM4NameOutput = new PrintWriter(new FileWriter(DetM4Name));
			DetM5NameOutput = new PrintWriter(new FileWriter(DetM5Name));
			DetF1NameOutput = new PrintWriter(new FileWriter(DetF1Name));
			DetF2NameOutput = new PrintWriter(new FileWriter(DetF2Name));
			DetF3NameOutput = new PrintWriter(new FileWriter(DetF3Name));
			DetF4NameOutput = new PrintWriter(new FileWriter(DetF4Name));
			DetF5NameOutput = new PrintWriter(new FileWriter(DetF5Name));
			DetF6NameOutput = new PrintWriter(new FileWriter(DetF6Name));
			DetYinFemalesNameOutput = new PrintWriter(new FileWriter(DetYinFemalesName));
			DetTotalYNameOutput = new PrintWriter(new FileWriter(DetTotalYName));
			DetAllFitnessNameOutput = new PrintWriter(new FileWriter(DetAllFitnessName));
			DetMaleFitnessNameOutput = new PrintWriter(new FileWriter(DetMaleFitnessName));
			DetFemaleFitnessNameOutput = new PrintWriter(new FileWriter(DetFemaleFitnessName));
			DetMaleChrClineNameOutput = new PrintWriter(new FileWriter(DetMaleChrClineName));
			DetFemaleChrClineNameOutput = new PrintWriter(new FileWriter(DetFemaleChrClineName));
			DetChrClineNameOutput = new PrintWriter(new FileWriter(DetChrClineName));
			
			for (int j=0; j<Storage.GUIgenerations; j++) {
				fitnessedDpopTransect = determineFitnessPop(startingDpopTransect);
				geneFlowedDpopTransect = determineGeneFlow(fitnessedDpopTransect, j);
				gametesDpopTransect = determinePopGametes(geneFlowedDpopTransect);
				nextGenDpopTransect = determinePop(gametesDpopTransect);
				
				if ((j%Storage.GUIOutputEvery)==0) {
					for (int f = 0; f < Storage.GUIZoneWidth; f++) {
						//this if statement is to add a zero in the 1-9
						// outputs so that excel can sort them properly
						if (f < 10) {
							if (Storage.GUIAllIndividualsStatus) {
							DetM1NameOutput.print("'Generation " + j + "' 'ZoneLocation0" + f + "'");
							DetM2NameOutput.print("'Generation " + j + "' 'ZoneLocation0" + f + "'");
							DetM3NameOutput.print("'Generation " + j + "' 'ZoneLocation0" + f + "'");
							DetM4NameOutput.print("'Generation " + j + "' 'ZoneLocation0" + f + "'");
							DetM5NameOutput.print("'Generation " + j + "' 'ZoneLocation0" + f + "'");
							DetF1NameOutput.print("'Generation " + j + "' 'ZoneLocation0" + f + "'");
							DetF2NameOutput.print("'Generation " + j + "' 'ZoneLocation0" + f + "'");
							DetF3NameOutput.print("'Generation " + j + "' 'ZoneLocation0" + f + "'");
							DetF4NameOutput.print("'Generation " + j + "' 'ZoneLocation0" + f + "'");
							DetF5NameOutput.print("'Generation " + j + "' 'ZoneLocation0" + f + "'");
							DetF6NameOutput.print("'Generation " + j + "' 'ZoneLocation0" + f + "'");
							}
							
							if (Storage.GUIYinFemalesStatus) {
								DetYinFemalesNameOutput.print("'Generation " + j
										+ "' 'ZoneLocation0" + f + "'");
							}
							if (Storage.GUITotalYStatus) {
								DetTotalYNameOutput.print("'Generation " + j
										+ "' 'ZoneLocation0" + f + "'");
							}
							if (Storage.GUITotalFitnessStatus) {
								DetAllFitnessNameOutput.print("'Generation " + j
										+ "' 'ZoneLocation0" + f + "'");
							}
							if (Storage.GUIMaleFitnessStatus) {
								DetMaleFitnessNameOutput.print("'Generation " + j
										+ "' 'ZoneLocation0" + f + "'");
							}
							if (Storage.GUIFemaleFitnessStatus) {
								DetFemaleFitnessNameOutput.print("'Generation " + j
										+ "' 'ZoneLocation0" + f + "'");
							}
							if (Storage.GUIObservedZoneStatus) {
								DetMaleChrClineNameOutput.print("'Generation " + j
										+ "' 'ZoneLocation0" + f + "'");
							}
							if (Storage.GUIFemaleChrClineStatus) {
								DetFemaleChrClineNameOutput.print("'Generation " + j
										+ "' 'ZoneLocation0" + f + "'");
							}
							if (Storage.GUIChrClineStatus) {
								DetChrClineNameOutput.print("'Generation " + j
										+ "' 'ZoneLocation0" + f + "'");
							}
							
							
						}
							else {
								if (Storage.GUIAllIndividualsStatus) {
									DetM1NameOutput.print("'Generation " + j + "' 'ZoneLocation" + f + "'");
									DetM2NameOutput.print("'Generation " + j + "' 'ZoneLocation" + f + "'");
									DetM3NameOutput.print("'Generation " + j + "' 'ZoneLocation" + f + "'");
									DetM4NameOutput.print("'Generation " + j + "' 'ZoneLocation" + f + "'");
									DetM5NameOutput.print("'Generation " + j + "' 'ZoneLocation" + f + "'");
									DetF1NameOutput.print("'Generation " + j + "' 'ZoneLocation" + f + "'");
									DetF2NameOutput.print("'Generation " + j + "' 'ZoneLocation" + f + "'");
									DetF3NameOutput.print("'Generation " + j + "' 'ZoneLocation" + f + "'");
									DetF4NameOutput.print("'Generation " + j + "' 'ZoneLocation" + f + "'");
									DetF5NameOutput.print("'Generation " + j + "' 'ZoneLocation" + f + "'");
									DetF6NameOutput.print("'Generation " + j + "' 'ZoneLocation" + f + "'");
									}
								
								if (Storage.GUIYinFemalesStatus) {
									DetYinFemalesNameOutput.print("'Generation " + j
											+ "' 'ZoneLocation" + f + "'");
								}
								if (Storage.GUITotalYStatus) {
									DetTotalYNameOutput.print("'Generation " + j
											+ "' 'ZoneLocation" + f + "'");
								}
								if (Storage.GUITotalFitnessStatus) {
									DetAllFitnessNameOutput.print("'Generation " + j
											+ "' 'ZoneLocation" + f + "'");
								}
								if (Storage.GUIMaleFitnessStatus) {
									DetMaleFitnessNameOutput.print("'Generation " + j
											+ "' 'ZoneLocation" + f + "'");
								}
								if (Storage.GUIFemaleFitnessStatus) {
									DetFemaleFitnessNameOutput.print("'Generation " + j
											+ "' 'ZoneLocation" + f + "'");
								}
								if (Storage.GUIObservedZoneStatus) {
									DetMaleChrClineNameOutput.print("'Generation " + j
											+ "' 'ZoneLocation" + f + "'");
								}
								if (Storage.GUIFemaleChrClineStatus) {
									DetFemaleChrClineNameOutput.print("'Generation " + j
											+ "' 'ZoneLocation" + f + "'");
								}
								if (Storage.GUIChrClineStatus) {
									DetChrClineNameOutput.print("'Generation " + j
											+ "' 'ZoneLocation" + f + "'");
								}
							}
								
						if (Storage.GUIAllIndividualsStatus) {
							nextGenDpopTransect[f].printM1(DetM1NameOutput);
							nextGenDpopTransect[f].printM2(DetM2NameOutput);
							nextGenDpopTransect[f].printM3(DetM3NameOutput);
							nextGenDpopTransect[f].printM4(DetM4NameOutput);
							nextGenDpopTransect[f].printM5(DetM5NameOutput);
							nextGenDpopTransect[f].printF1(DetF1NameOutput);
							nextGenDpopTransect[f].printF2(DetF2NameOutput);
							nextGenDpopTransect[f].printF3(DetF3NameOutput);
							nextGenDpopTransect[f].printF4(DetF4NameOutput);
							nextGenDpopTransect[f].printF5(DetF5NameOutput);
							nextGenDpopTransect[f].printF6(DetF6NameOutput);
							}
							if (Storage.GUIYinFemalesStatus) {
								nextGenDpopTransect[f].printDetYinFemales(DetYinFemalesNameOutput);
							}
							if (Storage.GUITotalYStatus) {
								nextGenDpopTransect[f].printDetTotalY(DetTotalYNameOutput);
							}
							if (Storage.GUITotalFitnessStatus) {
								nextGenDpopTransect[f].printDetTotalFitness(DetAllFitnessNameOutput);
							}
							if (Storage.GUIMaleFitnessStatus) {
								nextGenDpopTransect[f].printDetMaleFitness(DetMaleFitnessNameOutput);
							}
							if (Storage.GUIFemaleFitnessStatus) {
								nextGenDpopTransect[f].printDetFemaleFitness(DetFemaleFitnessNameOutput);
							}
							if (Storage.GUIObservedZoneStatus) {
								nextGenDpopTransect[f].printDetMaleChrCline(DetMaleChrClineNameOutput);
							}
							if (Storage.GUIFemaleChrClineStatus) {
								nextGenDpopTransect[f].printDetFemaleChrCline(DetFemaleChrClineNameOutput);
							}
							if (Storage.GUIChrClineStatus) {
								nextGenDpopTransect[f].printDetChrCline(DetChrClineNameOutput);
							}	
							
							
							
							
							
						}
				}
				
				
				
				
				
				startingDpopTransect = nextGenDpopTransect;
				
				
			}
			DetM1NameOutput.println();
			nextGenDpopTransect[(int)(Storage.GUIZoneWidth/2)].addSimParameters(DetM1NameOutput);
			DetM1NameOutput.close();
			DetM2NameOutput.println();
			nextGenDpopTransect[(int)(Storage.GUIZoneWidth/2)].addSimParameters(DetM2NameOutput);
			DetM2NameOutput.close();
			DetM3NameOutput.println();
			nextGenDpopTransect[(int)(Storage.GUIZoneWidth/2)].addSimParameters(DetM3NameOutput);
			DetM3NameOutput.close();
			DetM4NameOutput.println();
			nextGenDpopTransect[(int)(Storage.GUIZoneWidth/2)].addSimParameters(DetM4NameOutput);
			DetM4NameOutput.close();
			DetM5NameOutput.println();
			nextGenDpopTransect[(int)(Storage.GUIZoneWidth/2)].addSimParameters(DetM5NameOutput);
			DetM5NameOutput.close();
			DetF1NameOutput.println();
			nextGenDpopTransect[(int)(Storage.GUIZoneWidth/2)].addSimParameters(DetF1NameOutput);
			DetF1NameOutput.close();
			DetF2NameOutput.println();
			nextGenDpopTransect[(int)(Storage.GUIZoneWidth/2)].addSimParameters(DetF2NameOutput);
			DetF2NameOutput.close();
			DetF3NameOutput.println();
			nextGenDpopTransect[(int)(Storage.GUIZoneWidth/2)].addSimParameters(DetF3NameOutput);
			DetF3NameOutput.close();
			DetF4NameOutput.println();
			nextGenDpopTransect[(int)(Storage.GUIZoneWidth/2)].addSimParameters(DetF4NameOutput);
			DetF4NameOutput.close();
			DetF5NameOutput.println();
			nextGenDpopTransect[(int)(Storage.GUIZoneWidth/2)].addSimParameters(DetF5NameOutput);
			DetF5NameOutput.close();
			DetF6NameOutput.println();
			nextGenDpopTransect[(int)(Storage.GUIZoneWidth/2)].addSimParameters(DetF6NameOutput);
			DetF6NameOutput.close();
			
			DetYinFemalesNameOutput.println();
			nextGenDpopTransect[(int)(Storage.GUIZoneWidth/2)].addSimParameters(DetYinFemalesNameOutput);
			DetYinFemalesNameOutput.close();
			
			DetTotalYNameOutput.println();
			nextGenDpopTransect[(int)(Storage.GUIZoneWidth/2)].addSimParameters(DetTotalYNameOutput);
			DetTotalYNameOutput.close();
			
			DetAllFitnessNameOutput.println();
			nextGenDpopTransect[(int)(Storage.GUIZoneWidth/2)].addSimParameters(DetAllFitnessNameOutput);
			DetAllFitnessNameOutput.close();
			
			DetMaleFitnessNameOutput.println();
			nextGenDpopTransect[(int)(Storage.GUIZoneWidth/2)].addSimParameters(DetMaleFitnessNameOutput);
			DetMaleFitnessNameOutput.close();
			
			DetFemaleFitnessNameOutput.println();
			nextGenDpopTransect[(int)(Storage.GUIZoneWidth/2)].addSimParameters(DetFemaleFitnessNameOutput);
			DetFemaleFitnessNameOutput.close();
			
			DetMaleChrClineNameOutput.println();
			nextGenDpopTransect[(int)(Storage.GUIZoneWidth/2)].addSimParameters(DetMaleChrClineNameOutput);
			DetMaleChrClineNameOutput.close();
			
			DetFemaleChrClineNameOutput.println();
			nextGenDpopTransect[(int)(Storage.GUIZoneWidth/2)].addSimParameters(DetFemaleChrClineNameOutput);
			DetFemaleChrClineNameOutput.close();
			
			DetChrClineNameOutput	.println();
			nextGenDpopTransect[(int)(Storage.GUIZoneWidth/2)].addSimParameters(DetChrClineNameOutput);
			DetChrClineNameOutput.close();
		
		}
		catch (IOException ioException) {
			System.out.println("Error with file");
		}
			
		gui.outputFld.setText("Output deterministic files done!");
		}
		}
	}
		  
	//	else 	System.out.println("Ti patises megale!" + event);
	
		}

	public void QuestionYinFemalesConsole(Population[] a) {
		for (int i = 0; i < Storage.GUIZoneWidth; i++) {
			System.out.print("Zone location " + i + ":  ");
			for (int j = 0; j < Storage.GUIpopSize; j++) {
				if ((a[i].females[j].femaleStatus)
						&& ((a[i].females[j].egg[4] == 2)))
					System.out.print(" 1");
				else
					System.out.print(" 0");
			}
			System.out.println();
		}
	}

	public void QuestionNextToYinFemalesConsole(Population[] a) {
		for (int i = 0; i < Storage.GUIZoneWidth; i++) {
			System.out.print("Zone location " + i + ":  ");
			for (int j = 0; j < Storage.GUIpopSize; j++) {
				if ((a[i].females[j].femaleStatus)
						&& ((a[i].females[j].egg[1] == 0)))
					System.out.print(" 1");
				else
					System.out.print(" 0");
			}
			System.out.println();
		}
	}

	public static Population[] nextGeneration(Population[] previousGeneration) {
		Population[] a = new Population[Storage.GUIZoneWidth];
		for (int i = 0; i < Storage.GUIZoneWidth; i++) {
			a[i] = new Population(previousGeneration[i], Storage.GUIpopSize);
		}
		return a;
	}

	public static Population[] geneFlow(Population[] zoneNoflow) {
		Individual UFM = new Individual(Storage.getGamete("D"), Storage
				.getGamete("C"), 0); //THESE need to be replaced by a method,
		// this particular one should give the same
		// gametes from the opposite combination of
		// sperm and egg 50% of the time too!
		Individual UFF = new Individual(Storage.getGamete("D"), Storage
				.getGamete("D"), 0);
		Individual FM = new Individual(Storage.getGamete("B"), Storage
				.getGamete("A"), 1);
		Individual FF = new Individual(Storage.getGamete("B"), Storage
				.getGamete("B"), 1);

		Population[] a = new Population[Storage.GUIZoneWidth];
		int b;
		for (int j = 0; j < Storage.GUIZoneWidth; j++) {
			a[j] = new Population();
		}

		if (Storage.edgeText == "edgeEffects") {
		for (int i = 0; i < Storage.GUIZoneWidth; i++) {
			b = 0;
			if (i == 0) {
				for (b = 0; b < (int) ((1 - Storage.GUIgeneFlow) * (Storage.GUIpopSize)); b++) {
					a[i].males[b] = zoneNoflow[i].males[b];
					a[i].females[b] = zoneNoflow[i].females[b];
				}
				for (b = (int) ((1 - Storage.GUIgeneFlow) * (Storage.GUIpopSize)); b < (int) ((1 - (Storage.GUIgeneFlow / 2)) * (Storage.GUIpopSize)); b++) {
					a[i].males[b] = UFM;
					a[i].females[b] = UFF;
				}
				for (b = (int) ((1 - (Storage.GUIgeneFlow / 2)) * (Storage.GUIpopSize)); b < Storage.GUIpopSize; b++) { //-1
					// here
					// too!
					a[i].males[b] = zoneNoflow[i + 1].males[b];
					a[i].females[b] = zoneNoflow[i + 1].females[b];
				}
			}

			if (0 < i && i < Storage.GUIZoneWidth - 1) {
				for (b = 0; b < (int) ((1 - Storage.GUIgeneFlow) * (Storage.GUIpopSize)); b++) {
					a[i].males[b] = zoneNoflow[i].males[b];
					a[i].females[b] = zoneNoflow[i].females[b];
				}
				for (b = (int) ((1 - Storage.GUIgeneFlow) * (Storage.GUIpopSize)); b < (int) ((1 - (Storage.GUIgeneFlow / 2)) * (Storage.GUIpopSize)); b++) {
					a[i].males[b] = zoneNoflow[i - 1].males[b];
					a[i].females[b] = zoneNoflow[i - 1].females[b];
				}
				for (b = (int) ((1 - (Storage.GUIgeneFlow / 2)) * (Storage.GUIpopSize)); b < Storage.GUIpopSize; b++) { //-1
					// here
					// too!
					a[i].males[b] = zoneNoflow[i + 1].males[b];
					a[i].females[b] = zoneNoflow[i + 1].females[b];
					//				a[i] = zoneNoflow[i]; //but now this line is the same as
					// in the code below
					//If line is a[i] = zoneNoflow[i+1]; there is an
					// arrayOutofBounds
				}
			}
			if (i == Storage.GUIZoneWidth - 1) {
				for (b = 0; b < (int) ((1 - Storage.GUIgeneFlow) * (Storage.GUIpopSize)); b++) {
					a[i].males[b] = zoneNoflow[i].males[b];
					a[i].females[b] = zoneNoflow[i].females[b];
				}
				for (b = (int) ((1 - Storage.GUIgeneFlow) * (Storage.GUIpopSize)); b < (int) ((1 - (Storage.GUIgeneFlow / 2)) * (Storage.GUIpopSize)); b++) {
					a[i].males[b] = zoneNoflow[i - 1].males[b];
					a[i].females[b] = zoneNoflow[i - 1].females[b];
				}
				for (b = (int) ((1 - (Storage.GUIgeneFlow / 2)) * (Storage.GUIpopSize)); b < Storage.GUIpopSize; b++) { //-1
					// here
					// too!
					a[i].males[b] = FM;
					a[i].females[b] = FF;
				}

			}

		}
	//	return a;
		}
		//No edge effect implementation
		else if (Storage.edgeText == "noEdgeEffects") {

			for (int i = 0; i < Storage.GUIZoneWidth; i++) {
				b = 0;
				if (i == 0) {
					for (b = 0; b < (int) ((1 - Storage.GUIgeneFlow) * (Storage.GUIpopSize)); b++) {
						a[i].males[b] = zoneNoflow[i].males[b];
						a[i].females[b] = zoneNoflow[i].females[b];
					}
					for (b = (int) ((1 - Storage.GUIgeneFlow) * (Storage.GUIpopSize)); b < (int) ((1 - (Storage.GUIgeneFlow / 2)) * (Storage.GUIpopSize)); b++) {
						a[i].males[b] = zoneNoflow[i].males[b]; //changed this for no edge effect
						a[i].females[b] =  zoneNoflow[i].females[b]; //changed this for no edge effect
					}
					for (b = (int) ((1 - (Storage.GUIgeneFlow / 2)) * (Storage.GUIpopSize)); b < Storage.GUIpopSize; b++) { //-1
						// here
						// too!
						a[i].males[b] = zoneNoflow[i + 1].males[b];
						a[i].females[b] = zoneNoflow[i + 1].females[b];
					}
				}

				if (0 < i && i < Storage.GUIZoneWidth - 1) {
					for (b = 0; b < (int) ((1 - Storage.GUIgeneFlow) * (Storage.GUIpopSize)); b++) {
						a[i].males[b] = zoneNoflow[i].males[b];
						a[i].females[b] = zoneNoflow[i].females[b];
					}
					for (b = (int) ((1 - Storage.GUIgeneFlow) * (Storage.GUIpopSize)); b < (int) ((1 - (Storage.GUIgeneFlow / 2)) * (Storage.GUIpopSize)); b++) {
						a[i].males[b] = zoneNoflow[i - 1].males[b];
						a[i].females[b] = zoneNoflow[i - 1].females[b];
					}
					for (b = (int) ((1 - (Storage.GUIgeneFlow / 2)) * (Storage.GUIpopSize)); b < Storage.GUIpopSize; b++) { //-1
						// here
						// too!
						a[i].males[b] = zoneNoflow[i + 1].males[b];
						a[i].females[b] = zoneNoflow[i + 1].females[b];
						//				a[i] = zoneNoflow[i]; //but now this line is the same as
						// in the code below
						//If line is a[i] = zoneNoflow[i+1]; there is an
						// arrayOutofBounds
					}
				}
				if (i == Storage.GUIZoneWidth - 1) {
					for (b = 0; b < (int) ((1 - Storage.GUIgeneFlow) * (Storage.GUIpopSize)); b++) {
						a[i].males[b] = zoneNoflow[i].males[b];
						a[i].females[b] = zoneNoflow[i].females[b];
					}
					for (b = (int) ((1 - Storage.GUIgeneFlow) * (Storage.GUIpopSize)); b < (int) ((1 - (Storage.GUIgeneFlow / 2)) * (Storage.GUIpopSize)); b++) {
						a[i].males[b] = zoneNoflow[i - 1].males[b];
						a[i].females[b] = zoneNoflow[i - 1].females[b];
					}
					for (b = (int) ((1 - (Storage.GUIgeneFlow / 2)) * (Storage.GUIpopSize)); b < Storage.GUIpopSize; b++) { //-1
						// here
						// too!
						a[i].males[b] = zoneNoflow[i].males[b];
						a[i].females[b] = zoneNoflow[i].females[b];
					}

				}

			}
	//		return a;
			
		}
		return a;
	}
	
	
	//methods required to create the DETERMINISTIC files
	public static DPopulation[] determineFitnessPop(DPopulation[] a) {
		DPopulation[] b = new DPopulation[Storage.GUIZoneWidth];
		for (int f=0; f<Storage.GUIZoneWidth; f++){
		b[f] = new DPopulation();
		}
		
		for (int h=0; h<Storage.GUIZoneWidth; h++) {
		double totalGametes = 0;
		for (int i=0; i<5; i++) {
			for (int k=0;k<3;k++) {
				b[h].DPop[i][k] = a[h].DPop[i][k] * Storage.DfitnessTable[i][k];
				totalGametes = totalGametes + b[h].DPop[i][k];
			}
		}
		//this is done to standardise to 1 total gametes if they do not add up to 1 
		//THIS MAKES THE FITNESS CALCULATED RELATIVE!!!
		if (totalGametes != 1) {
		for (int i=0; i<5; i++) {
			for (int k=0;k<3;k++) {
			b[h].DPop[i][k] = b[h].DPop[i][k]/totalGametes;	
			}
		}
		}
		}
		return b;
	}
	
	
	
	
	public static DPopulation[] determineGeneFlow(DPopulation[] DZoneNoFlow, int currentGen) {
		DPopulation[] a = new DPopulation[Storage.GUIZoneWidth];
		for (int j=0;j<Storage.GUIZoneWidth;j++) {
			a[j] = new DPopulation();
		}
		
		//Stop gene flow implmentation
		if (currentGen>Storage.GUIgeneFlowGenerations) {
			for (int i=0;i<Storage.GUIZoneWidth;i++) {
			for (int k=0;k<5;k++) {
				for (int g=0;g<3;g++) {
	//				a[i].DPop[k][g] = DZoneNoFlow[i].DPop[k][g]*(1-Storage.GUIgeneFlow); 
					a[i].DPop[k][g] = DZoneNoFlow[i].DPop[k][g]; 
				}
				}
		}
		}
		
		//edge effects implementation
		else if  (Storage.edgeText == "edgeEffects")  {
		for (int i=0;i<Storage.GUIZoneWidth;i++) {
		if (i==0) {
			DPopulation UFDP = new DPopulation();
			UFDP.DPopulationUnfused();
			
//			double[][] Unfused = DPopulationUnfused( UFDP);
			for (int k=0;k<5;k++) {
				for (int g=0;g<3;g++) {
					a[i].DPop[k][g] = DZoneNoFlow[i].DPop[k][g]*(1-Storage.GUIgeneFlow) + UFDP.DPop[k][g]*Storage.GUIgeneFlow/2 + DZoneNoFlow[i+1].DPop[k][g]*Storage.GUIgeneFlow/2; 
		}
			}
		}
		if (0<i&&i<Storage.GUIZoneWidth-1) {	
				for (int k=0;k<5;k++) {
				for (int g=0;g<3;g++) {
					a[i].DPop[k][g] = DZoneNoFlow[i].DPop[k][g]*(1-Storage.GUIgeneFlow) + DZoneNoFlow[i-1].DPop[k][g]*Storage.GUIgeneFlow/2 + DZoneNoFlow[i+1].DPop[k][g]*Storage.GUIgeneFlow/2; 
				}
				}
				
			}
		if (i==Storage.GUIZoneWidth-1) {
			DPopulation FDP = new DPopulation();
			FDP.DPopulationFused();
			for (int k=0;k<5;k++) {
				for (int g=0;g<3;g++) {
					a[i].DPop[k][g] = DZoneNoFlow[i].DPop[k][g]*(1-Storage.GUIgeneFlow) + DZoneNoFlow[i-1].DPop[k][g]*Storage.GUIgeneFlow/2 + FDP.DPop[k][g]*Storage.GUIgeneFlow/2; 
	
		}
		}
		}
			
		}
//		return a;
		}
		//No edge effects implementation
		else if (Storage.edgeText == "noEdgeEffects") {
			for (int i=0;i<Storage.GUIZoneWidth;i++) {
				if (i==0) {
					
					for (int k=0;k<5;k++) {
						for (int g=0;g<3;g++) {
							a[i].DPop[k][g] = DZoneNoFlow[i].DPop[k][g]*(1-Storage.GUIgeneFlow) + DZoneNoFlow[i].DPop[k][g]*Storage.GUIgeneFlow/2 + DZoneNoFlow[i+1].DPop[k][g]*Storage.GUIgeneFlow/2; 
				}
					}
				}
				if (0<i&&i<Storage.GUIZoneWidth-1) {	
						for (int k=0;k<5;k++) {
						for (int g=0;g<3;g++) {
							a[i].DPop[k][g] = DZoneNoFlow[i].DPop[k][g]*(1-Storage.GUIgeneFlow) + DZoneNoFlow[i-1].DPop[k][g]*Storage.GUIgeneFlow/2 + DZoneNoFlow[i+1].DPop[k][g]*Storage.GUIgeneFlow/2; 
						}
						}
						
					}
				if (i==Storage.GUIZoneWidth-1) {
					for (int k=0;k<5;k++) {
						for (int g=0;g<3;g++) {
							a[i].DPop[k][g] = DZoneNoFlow[i].DPop[k][g]*(1-Storage.GUIgeneFlow) + DZoneNoFlow[i-1].DPop[k][g]*Storage.GUIgeneFlow/2 + DZoneNoFlow[i].DPop[k][g]*Storage.GUIgeneFlow/2; 
			
				}
				}
				}
					
				}
	//	return a;
		}
		return a;
		}
	
	// This makes a population when given all different gametes
	public DPopulation[] determinePop(double[][] DTransectGametes) {
		DPopulation[] a = new DPopulation[Storage.GUIZoneWidth];
		for (int k=0;k<Storage.GUIZoneWidth;k++) {
			a[k] = new DPopulation();
		}
		
		for (int i=0; i<Storage.GUIZoneWidth; i++) {
		
		a[i].DPop[0][0] = DTransectGametes[i][0] * DTransectGametes[i][5]; //ma * fb;
		a[i].DPop[1][0] = DTransectGametes[i][2] * DTransectGametes[i][5]; //mc * fb;
		a[i].DPop[2][0] = DTransectGametes[i][1] * DTransectGametes[i][5]; //mb * fb;
		a[i].DPop[3][0] = DTransectGametes[i][3] * DTransectGametes[i][5]; //md * fb;
		a[i].DPop[4][0] = DTransectGametes[i][4] * DTransectGametes[i][5]; //me * fb;
		a[i].DPop[0][1] = DTransectGametes[i][0] * DTransectGametes[i][6]; //ma * fd;
		a[i].DPop[1][1] = DTransectGametes[i][2] * DTransectGametes[i][6]; //mc * fd;
		a[i].DPop[2][1] = DTransectGametes[i][1] * DTransectGametes[i][6]; //mb * fd;
		a[i].DPop[3][1] = DTransectGametes[i][3] * DTransectGametes[i][6]; //md * fd;
		a[i].DPop[4][1] = DTransectGametes[i][4] * DTransectGametes[i][6]; //me * fd;
		a[i].DPop[0][2] = DTransectGametes[i][0] * DTransectGametes[i][7]; //ma * fe;
		a[i].DPop[1][2] = DTransectGametes[i][2] * DTransectGametes[i][7]; //mc * fe;
		a[i].DPop[2][2] = DTransectGametes[i][1] * DTransectGametes[i][7]; //mb * fe;
		a[i].DPop[3][2] = DTransectGametes[i][3] * DTransectGametes[i][7]; //md * fe;
		a[i].DPop[4][2] = DTransectGametes[i][4] * DTransectGametes[i][7]; //me * fe;
		
		}
		return a;
	}
	
	//this produces the next generation gametes from an existing population
	public double[][] determinePopGametes(DPopulation[] a) {
		double[][] transectGametes = new double[Storage.GUIZoneWidth][8];
		//initialise popGametes array
		for (int j=0;j<Storage.GUIZoneWidth;j++) {
			for (int k=0;k<8;k++) {
				transectGametes[j][k] = 0;
			}
		}
		
		for (int i=0;i<Storage.GUIZoneWidth;i++) {
		//popGametes[0] = ma, [1] =mb, [2]=mc, [3]=md, [4]=me, [5]=fb, [6]=fd, [7]=fe BEWARE that mb and mc
		//I've changed the names in the past, I use what is on the 21/3/06 table
		transectGametes[i][0] = a[i].DPop[0][0] + a[i].DPop[0][1]*0.5 + a[i].DPop[0][2] + a[i].DPop[1][2]*0.5;
		transectGametes[i][1] = a[i].DPop[0][0] + a[i].DPop[1][0];
		transectGametes[i][2] = a[i].DPop[1][0] + a[i].DPop[1][1] + a[i].DPop[1][2]*0.5 + a[i].DPop[0][1]*0.5;
		transectGametes[i][3] = a[i].DPop[0][1]*0.5 + a[i].DPop[1][1] + a[i].DPop[1][2]*0.5;
		transectGametes[i][4] = a[i].DPop[0][2] + a[i].DPop[1][2]*0.5 + a[i].DPop[0][1]*0.5;
		transectGametes[i][5] = a[i].DPop[2][0]*2 + a[i].DPop[3][0] + a[i].DPop[4][0] + a[i].DPop[2][1] + a[i].DPop[2][2];
		transectGametes[i][6] = a[i].DPop[3][0] + a[i].DPop[2][1] + a[i].DPop[3][1]*2 + a[i].DPop[4][1] + a[i].DPop[3][2];
		transectGametes[i][7] = a[i].DPop[4][0] + a[i].DPop[4][1] + a[i].DPop[2][2] + a[i].DPop[3][2] + a[i].DPop[4][2]*2;
		}
		return transectGametes;
	}
	

	
}