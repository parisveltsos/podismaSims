import java.io.PrintWriter;

public class DPopulation {
	
	public double[][] DPop = {{0, 0, 0}, {0, 0, 0}, {0, 0, 0}, {0, 0, 0}, {0, 0, 0}}; 
	
	public double m1 = DPop[0][0];
	public double m2 = DPop[0][1] + DPop[1][2];
	public double m3 = DPop[2][0];
	public double m4 = DPop[1][1];
	public double m5 = DPop[1][0];
	public double f1 = DPop[2][0];
	public double f2 = DPop[3][1];
	public double f3 = DPop[3][0] + DPop[2][1];
	public double f4 = DPop[4][0] + DPop[2][2];
	public double f5 = DPop[4][1] + DPop[3][2];
	public double f6 = DPop[4][2];
//public double allDPop = m1 + m2 + m3 + m4 + m5 + f1 + f2 + f3 + f4 + f5 + f6;
	
	public double getAllDPop(DPopulation a) {
		double toReturn = 0;
		for (int i=0;i<5;i++) {
			for (int j=0;j<3;j++) {
				toReturn = toReturn + DPop[i][j];
			}
		}
		return toReturn;
	}
	
	//constructor to initialise local objects
	public DPopulation() {
		for (int i=0; i<5; i++) {
			for (int k=0; k<3; k++) {
				DPop[i][k] = 0;
			}
		}
	}
	
//	Method to make pure unfused population
	public void DPopulationUnfused() {
		for (int i=0; i<5; i++) {
			for (int k=0; k<3; k++) {
				 DPop[i][k] = 0;
			}
		}
		
		DPop[3][1] = 0.5; //the only females available are F2
		DPop[1][1] = 0.5; //the only males available are M4
		
	}
//	Method to make pure fused population
	public void DPopulationFused() {
		for (int i=0; i<5; i++) {
			for (int k=0; k<3; k++) {
				DPop[i][k] = 0;
			}
		}
		
		DPop[2][0] = 0.5; //the only females available are F1
		DPop[0][0] = 0.5; //the only males available are M1
	}
	
//	Method to make unfused with Y population
	public void DPopulationUnfusedY() {
		for (int i=0; i<5; i++) {
			for (int k=0; k<3; k++) {
				DPop[i][k] = 0;
			}
		}
		
		DPop[0][2] = 0.5; //the only females available are F1
		DPop[4][2] = 0.5; //the only males available are M1
	}
	
	//method adjusts the gamete proportions according to fitness, first multiply with fitness table
	// and then adjust to 1 so that pop size is the same. Opportunity to  introduce sex dependent density 
	// dependent selection is here
	
	

	public double determineM1(DPopulation a) {
		double M1 = a.DPop[0][0];
		return M1;	
	}
	
	public double determineM2(DPopulation a) {
		double M2 = a.DPop[0][1] + a.DPop[1][2];
		return M2;
	}
	
	public double determineM3(DPopulation a) {
		double M3 = a.DPop[0][2];
		return M3;	
	}
	
	public double determineM4(DPopulation a) {
		double M4 = a.DPop[1][1];
		return M4;	
	}
	
	public double determineM5(DPopulation a) {
		double M5 = a.DPop[1][0];
		return M5;	
	}
	
	public double determineF1(DPopulation a) {
		double F1 = a.DPop[2][0];
		return F1;	
	}
	
	public double determineF2(DPopulation a) {
		double F2 = a.DPop[3][1];
		return F2;	
	}
	
	public double determineF3(DPopulation a) {
		double F3 = a.DPop[3][0] + a.DPop[2][1];
		return F3;	
	}
	
	public double determineF4(DPopulation a) {
		double F4 = a.DPop[4][0] + a.DPop[2][2];
		return F4;	
	}
	
	public double determineF5(DPopulation a) {
		double F5 = a.DPop[4][1] + a.DPop[3][2];
		return F5;	
	}
	
	public double determineF6(DPopulation a) {
		double F6 = a.DPop[4][2];
		return F6;	
	}
	
// Made this output standardised so that populations from various positions in the transect can be compared
//	
public void printM1(PrintWriter pw) {
	double allDPop = 0;
	for (int i=0;i<5;i++) {
		for (int j=0;j<3;j++) {
			allDPop = allDPop + DPop[i][j];
		}
	}
		pw.print(" " + DPop[0][0]/allDPop);
		pw.println();
}

public void printM2(PrintWriter pw) {
	double allDPop = 0;
	for (int i=0;i<5;i++) {
		for (int j=0;j<3;j++) {
			allDPop = allDPop + DPop[i][j];
		}
	}
	pw.print(" " + (DPop[0][1]/allDPop + DPop[1][2]/allDPop));
	pw.println();
}

public void printM3(PrintWriter pw) {
	double allDPop = 0;
	for (int i=0;i<5;i++) {
		for (int j=0;j<3;j++) {
			allDPop = allDPop + DPop[i][j];
		}
	}
	pw.print(" " + DPop[0][2]/allDPop);
	pw.println();
}

public void printM4(PrintWriter pw) {
	double allDPop = 0;
	for (int i=0;i<5;i++) {
		for (int j=0;j<3;j++) {
			allDPop = allDPop + DPop[i][j];
		}
	}
		
	pw.print(" " + DPop[1][1]/allDPop);
	pw.println();
}

public void printM5(PrintWriter pw) {
	double allDPop = 0;
	for (int i=0;i<5;i++) {
		for (int j=0;j<3;j++) {
			allDPop = allDPop + DPop[i][j];
		}
	}
	pw.print(" " + DPop[1][0]/allDPop);
	pw.println();
}

public void printF1(PrintWriter pw) {
	double allDPop = 0;
	for (int i=0;i<5;i++) {
		for (int j=0;j<3;j++) {
			allDPop = allDPop + DPop[i][j];
		}
	}
	pw.print(" " + DPop[2][0]/allDPop);
	pw.println();
}

public void printF2(PrintWriter pw) {
	double allDPop = 0;
	for (int i=0;i<5;i++) {
		for (int j=0;j<3;j++) {
			allDPop = allDPop + DPop[i][j];
		}
	}
	pw.print(" " + DPop[3][1]/allDPop);
	pw.println();
}

public void printF3(PrintWriter pw) {
	double allDPop = 0;
	for (int i=0;i<5;i++) {
		for (int j=0;j<3;j++) {
			allDPop = allDPop + DPop[i][j];
		}
	}
	pw.print(" " + (DPop[3][0]/allDPop + DPop[2][1]/allDPop));
	pw.println();
}

public void printF4(PrintWriter pw) {
	double allDPop = 0;
	for (int i=0;i<5;i++) {
		for (int j=0;j<3;j++) {
			allDPop = allDPop + DPop[i][j];
		}
	}
	pw.print(" " + (DPop[4][0]/allDPop + DPop[2][2]/allDPop));
	pw.println();
}

public void printF5(PrintWriter pw) {
	double allDPop = 0;
	for (int i=0;i<5;i++) {
		for (int j=0;j<3;j++) {
			allDPop = allDPop + DPop[i][j];
		}
	}
	pw.print(" " + (DPop[4][1]/allDPop + DPop[3][2]/allDPop));
	pw.println();
}

public void printF6(PrintWriter pw) {
	double allDPop = 0;
	for (int i=0;i<5;i++) {
		for (int j=0;j<3;j++) {
			allDPop = allDPop + DPop[i][j];
		}
	}
	pw.print(" " + DPop[4][2]/allDPop);
	pw.println();
}

public void printDetYinFemales(PrintWriter pw) {
	double allDPop = 0;
	for (int i=0;i<5;i++) {
		for (int j=0;j<3;j++) {
			allDPop = allDPop + DPop[i][j];
		}
	}
	pw.print(" " + (DPop[4][0]/allDPop + DPop[4][1]/allDPop +DPop[2][2]/allDPop + DPop[3][2]/allDPop + DPop[4][2]/allDPop * 2));
	pw.println();
	// x2 because this individual contains 2 Ys (not because 2 gamete combinations can give rise to it)
}

//This was changed in V 1.2 so that the Y calculated is relative to autosomes
public void printDetTotalY(PrintWriter pw) {
	double allDPop = 0;
	for (int i=0;i<5;i++) {
		for (int j=0;j<3;j++) {
			allDPop = allDPop + DPop[i][j];
		}
	}
	pw.print(" " + (DPop[0][0] + DPop[0][1] + DPop[0][2] * 2 + DPop[1][2] + DPop[4][0] + DPop[4][1] +DPop[2][2] + DPop[3][2] + DPop[4][2] * 2)/
	(DPop[0][0] + DPop[0][1] * 2 + DPop[1][2] * 2 + DPop[0][2] * 2 + DPop[1][1] * 2 + DPop[1][0] +
	DPop[3][1] * 2 + DPop[3][0] + DPop[2][1] + DPop[4][0] + DPop[2][2] + DPop[4][1] * 2 + DPop[3][2] * 2 + DPop[4][2] * 2));
	pw.println();
	
}

//For version 1.1.1 the total fitness was changed to be calculated depending on whether males with a Y exist in a population or not.

public void printDetMaleFitness(PrintWriter pw) {
	double allDPop = 0;
		for (int i=0;i<5;i++) {
		for (int j=0;j<3;j++) {
			allDPop = allDPop + DPop[i][j];
		}
	}
// This creates a plotting problem	
// 	if (DPop[0][0] + DPop[0][1] + DPop[0][2] + DPop[1][2] < 0.01) {
// 		pw.print(" " + (2 * ( //everything times 2 so that fitness adds up to 1.
// 				DPop[0][0]/allDPop + 
// 				DPop[0][1]/allDPop + 
// 				DPop[0][2]/allDPop + 
// 				DPop[1][0]/allDPop * Storage.DfitnessTable[1][0] +
// 				DPop[1][1]/allDPop * Storage.DfitnessTable[1][1] +
// 				DPop[1][2]/allDPop  )));
// pw.println();
// 	}
// 	else
	pw.print(" " + (2 * ( //everything times 2 so that fitness adds up to 1.
					DPop[0][0]/allDPop * Storage.DfitnessTable[0][0] + 
					DPop[0][1]/allDPop * Storage.DfitnessTable[0][1] + 
					DPop[0][2]/allDPop * Storage.DfitnessTable[0][2] + 
					DPop[1][0]/allDPop * Storage.DfitnessTable[1][0] +
					DPop[1][1]/allDPop * Storage.DfitnessTable[1][1] +
					DPop[1][2]/allDPop * Storage.DfitnessTable[1][2] )));
	pw.println();
}

public void printDetFemaleFitness(PrintWriter pw) {
	double allDPop = 0;
	for (int i=0;i<5;i++) {
		for (int j=0;j<3;j++) {
			allDPop = allDPop + DPop[i][j];
		}
	}
	pw.print(" " + (2 * (
					DPop[2][0]/allDPop * Storage.DfitnessTable[2][0] + 
					DPop[2][1]/allDPop * Storage.DfitnessTable[2][1] + 
					DPop[2][2]/allDPop * Storage.DfitnessTable[2][2] + 
					DPop[3][0]/allDPop * Storage.DfitnessTable[3][0] +
					DPop[3][1]/allDPop * Storage.DfitnessTable[3][1] +
					DPop[3][2]/allDPop * Storage.DfitnessTable[3][2] + 
					DPop[4][0]/allDPop * Storage.DfitnessTable[4][0] +
					DPop[4][1]/allDPop * Storage.DfitnessTable[4][1] +
					DPop[4][2]/allDPop * Storage.DfitnessTable[4][2] )));
	pw.println();
}	

public void printDetTotalFitness(PrintWriter pw) {
	double allDPop = 0;
	for (int i=0;i<5;i++) {
		for (int j=0;j<3;j++) {
			allDPop = allDPop + DPop[i][j];
		}
	}
// This creates a plotting problem	
// 	if (DPop[0][0] + DPop[0][1] + DPop[0][2] + DPop[1][2] < 0.01) {
// 		pw.print(" " + (2 * ( //everything times 2 fitness adds up to 2
// 				DPop[0][0]/allDPop + 
// 				DPop[0][1]/allDPop + 
// 				DPop[0][2]/allDPop + 
// 				DPop[1][0]/allDPop * Storage.DfitnessTable[1][0] +
// 				DPop[1][1]/allDPop * Storage.DfitnessTable[1][1] +
// 				DPop[1][2]/allDPop + 
// 					DPop[2][0]/allDPop * Storage.DfitnessTable[2][0] + 
// 					DPop[2][1]/allDPop * Storage.DfitnessTable[2][1] + 
// 					DPop[2][2]/allDPop * Storage.DfitnessTable[2][2] + 
// 					DPop[3][0]/allDPop * Storage.DfitnessTable[3][0] +
// 					DPop[3][1]/allDPop * Storage.DfitnessTable[3][1] +
// 					DPop[3][2]/allDPop * Storage.DfitnessTable[3][2] + 
// 					DPop[4][0]/allDPop * Storage.DfitnessTable[4][0] +
// 					DPop[4][1]/allDPop * Storage.DfitnessTable[4][1] +
// 					DPop[4][2]/allDPop * Storage.DfitnessTable[4][2] )));
// pw.println();
// 	}
// 	else
// 	
	pw.print(" " + (1 * ( //everything times 2 so that fitness adds up to 1. I am not sure whether it will though
			DPop[0][0]/allDPop * Storage.DfitnessTable[0][0] + 
			DPop[0][1]/allDPop * Storage.DfitnessTable[0][1] + 
			DPop[0][2]/allDPop * Storage.DfitnessTable[0][2] + 
			DPop[1][0]/allDPop * Storage.DfitnessTable[1][0] +
			DPop[1][1]/allDPop * Storage.DfitnessTable[1][1] +
			DPop[1][2]/allDPop * Storage.DfitnessTable[1][2] +
					DPop[2][0]/allDPop * Storage.DfitnessTable[2][0] + 
					DPop[2][1]/allDPop * Storage.DfitnessTable[2][1] + 
					DPop[2][2]/allDPop * Storage.DfitnessTable[2][2] + 
					DPop[3][0]/allDPop * Storage.DfitnessTable[3][0] +
					DPop[3][1]/allDPop * Storage.DfitnessTable[3][1] +
					DPop[3][2]/allDPop * Storage.DfitnessTable[3][2] + 
					DPop[4][0]/allDPop * Storage.DfitnessTable[4][0] +
					DPop[4][1]/allDPop * Storage.DfitnessTable[4][1] +
					DPop[4][2]/allDPop * Storage.DfitnessTable[4][2] )));
	pw.println();
}

//Used to plot F/UF in males ie microscopically observed fusion %. But result gave infinity in fused side so changed to plotting just the fusion
public void printDetMaleChrCline(PrintWriter pw) {
	double allDPop = 0;
	for (int i=0;i<5;i++) {
		for (int j=0;j<3;j++) {
			allDPop = allDPop + DPop[i][j];
		}
	}
	pw.print(" " + ( DPop[0][0]/allDPop + DPop[1][0]/allDPop) );
	pw.println();
}

public void printDetFemaleChrCline(PrintWriter pw) { //Completely changed this method to show the first population where the fusion has moved above 50%. The name is the same throughout the code 25/01/2008. Changed in version 1.3
	double allDPop = 0;
	for (int i=0;i<5;i++) {
		for (int j=0;j<3;j++) {
			allDPop = allDPop + DPop[i][j];
		}
	}
	if 
		(DPop[2][0]/allDPop *2 + DPop[3][0]/allDPop + DPop[4][0]/allDPop + DPop[2][1]/allDPop + DPop[2][2]/allDPop>0.5)
		{
		pw.print(" " + (DPop[2][0]/allDPop *2 + DPop[3][0]/allDPop + DPop[4][0]/allDPop + DPop[2][1]/allDPop + DPop[2][2]/allDPop));
		pw.println();
		}
	
	else {
	pw.println(); 
	}
}

// code changed to calculate the relative fusion frequency in version 1.2 onwards
public void printDetChrCline(PrintWriter pw) {
	double allDPop = 0;
	for (int i=0;i<5;i++) {
		for (int j=0;j<3;j++) {
			allDPop = allDPop + DPop[i][j];
		}
	}
	pw.print(" " + (DPop[0][0] + DPop[1][0] + DPop[2][0] *2 + DPop[3][0] + DPop[4][0] + DPop[2][1] + DPop[2][2])/
	(DPop[0][0] + DPop[0][1] + DPop[1][2] + DPop[0][2] + DPop[1][1] + DPop[1][0] + 2*DPop[2][0] + 2*DPop[3][1] + 2*DPop[3][0] + 2*DPop[2][1] + 2*DPop[4][0] + 2*DPop[2][2] + 2*DPop[4][1] + 2*DPop[3][2] + 2*DPop[4][2]));
	pw.println();
	
}

//need this here, just copied the original version from the Population class
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
