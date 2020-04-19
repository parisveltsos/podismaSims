## To compile

	cd ~/git/podismaSims/scripts/Raw

	javac -Xlint:unchecked *.java

	jar cmf MainClass.txt test.jar *.java *.class icons/
	
	cp Podisma.jar ~/git/podismaSims/output

## To run

### Simulation run

	cd ~/git/podismaSims/output
	
Run the `Podisma.jar` file.
	
Choose parameters and press the `GO!` button. 

Note: The automatic plotting works with 10,000 generations simulation, and output every 10 generations.

A neutral run on the stochastic mode takes 2 min on a 3.8GHz Inter Core i5 iMac with 8Gb RAM.

### Plot

	source ~/git/podismaSims/scripts/formatAndPlot.sh
	
The produced plots automatically open with the default pdf viewer. Remember to change the name of the SIMNAME folder if the simulation results are worth saving.
	
## Version history

Version 1.3 changed the female chr cline plotting to plot only populations where the fusion is above 50%. This allows easy visual inspection of the time required for the fusion to spread or retreat and was used to generate the table on speed of fusion spread. Can find (on00' 0.) in the generated text file to find the first generation where the fusion has spread by 1 Km.

Version 1.2 changed the plotting of the fusion and Y clines, (totalY, ChrCline tick boxes) so that they are relative to the rest of the population and not absolute. Best run at a greater generation interval (output every 500) so that the result can be quickly appreciated from the text files without any Excel involvement, which is the slowest step.

Version 1.1.1 attempted to correct population fitness plotting, so that populations where no males have a Y have a fitness of 1. 

Version 1.1 allowed to stop gene flow at a given generation, to tell apart the effects of gene flow and selection on populations with different frequencies of the karyotypes.

Version 1 was used to obtain most results.

