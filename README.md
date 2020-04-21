## Initial setup

You need to install Java. The workflow has been tested on a Mac running MacOS Mojave 10.14.6.

The commands should work without needing to change the folders in scripts if you clone the project at `~/git/podismaSims/`

R needs the plotrix library to add the simulation parameter table in the simulations. To install, in R:

	install.packages("plotrix", dependencies=TRUE)

To install the program

		mkdir ~/git
		
		git clone https://github.com/parisveltsos/podismaSims.git
		
		cd podismaSims
		
		mkdir output
		
		mv scripts/Podisma.jar output/
		
		mv scripts/icons/ output/
		
The output folder is in the `.gitignore` file. The program is run from there and the output is not synced with github.

## To run

	cd ~/git/podismaSims/output
	
### Run simulations

Run the `Podisma.jar` file.
	
Choose parameters and press the `GO!` button. 

Note: The automatic plotting works with 10,000 generations simulation, and output every 10 generations (default values).

A neutral run on the stochastic mode takes 2 min on a 3.8GHz Inter Core i5 iMac with 8Gb RAM. The Deterministic runs are almost instantaneous.

### Produce plots

Once a run is complete, use the relevant command (deterministic or stochastic)

	source ~/git/podismaSims/scripts/plotDet.sh
	
	source ~/git/podismaSims/scripts/plotStoc.sh
	
The produced plots automatically open with the default pdf viewer. 

If you want to retain the output, change the name of the SIMNAME folder to something that makes sense.
	
## To compile

A compiled version is included so you should not need to do this.

	cd ~/git/podismaSims/scripts/Raw

	javac -Xlint:unchecked *.java

	jar cmf MainClass.txt Podisma.jar *.java *.class icons/
	
	cp Podisma.jar ~/git/podismaSims/output
	
## Version history

Version 1.4 is the release where the plotting has been tested from both the deterministic and stochastic simulations.

Version 1.3.3 added deterministic model plotting.	

Version 1.3.2 cleaned up the code.

Version 1.3.1 added plot functionality with R, including formatting the stochastic simulation output.

Version 1.3 changed the female chr cline plotting to plot only populations where the fusion is above 50%. This allows easy visual inspection of the time required for the fusion to spread or retreat and was used to generate the table on speed of fusion spread. Can find (on00' 0.) in the generated text file to find the first generation where the fusion has spread by 1 Km.

Version 1.2 changed the plotting of the fusion and Y clines, (totalY, ChrCline tick boxes) so that they are relative to the rest of the population and not absolute. Best run at a greater generation interval (output every 500) so that the result can be quickly appreciated from the text files without any Excel involvement, which is the slowest step.

Version 1.1.1 attempted to correct population fitness plotting, so that populations where no males have a Y have a fitness of 1. 

Version 1.1 allowed to stop gene flow at a given generation, to tell apart the effects of gene flow and selection on populations with different frequencies of the karyotypes.

Version 1 was used to obtain most results.

