
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintWriter;

import javax.swing.*;

public class PodismaGUI extends JFrame {
	
	ImageIcon podismaImage = new ImageIcon("icons/podisma.jpg", "Podisma image");
	ImageIcon maleImage1 = new ImageIcon("icons/MImg1.jpg", "Male1");
	ImageIcon maleImage2 = new ImageIcon("icons/MImg2.jpg", "Male2");
	ImageIcon maleImage3 = new ImageIcon("icons/MImg3.jpg", "Male3");
	ImageIcon maleImage4 = new ImageIcon("icons/MImg4.jpg", "Male4");
	ImageIcon maleImage5 = new ImageIcon("icons/MImg5.jpg", "Male5");
	ImageIcon femaleImage1 = new ImageIcon("icons/FImg1.jpg", "Female1");
	ImageIcon femaleImage2 = new ImageIcon("icons/FImg2.jpg", "Female2");
	ImageIcon femaleImage3 = new ImageIcon("icons/FImg3.jpg", "Female3");
	ImageIcon femaleImage4 = new ImageIcon("icons/FImg4.jpg", "Female4");
	ImageIcon femaleImage5 = new ImageIcon("icons/FImg5.jpg", "Female5");
	ImageIcon femaleImage6 = new ImageIcon("icons/FImg6.jpg", "Female6");

	JLabel Title = new JLabel("Welcome to the Podisma modelling program!");

	JLabel popSizeLbl = new JLabel("Population size: ");
	JTextField popSizeTxtfld = new JTextField("100", 5);

	JLabel runForLbl = new JLabel("Run for: ");
	JTextField generationsTxtfld = new JTextField("10000", 5);
	JLabel generationsLbl = new JLabel("generations ");

	JLabel ARecFrLbl = new JLabel("Autosomal loci recombination frequency: ");
	JTextField ARecFrTxtfld = new JTextField("0.1", 5);

	JLabel XRecFrLbl = new JLabel("X chromosome loci recombination frequency: ");
	JTextField XRecFrTxtfld = new JTextField("0.1", 5);

	JLabel YRecFrLbl = new JLabel("Y chromosome loci recombination frequency: ");
	JTextField YRecFrTxtfld = new JTextField("0.01", 5);

	JLabel FRecFrLbl = new JLabel("Fusion recombination frequency: ");
	JTextField FRecFrTxtfld = new JTextField("0", 5);

	JLabel geneFlowLbl = new JLabel("Percentage of gene flow: ");
	JTextField geneFlowTxtfld = new JTextField("0.16", 5);

	JLabel zoneWidthLbl = new JLabel("Zone width is: ");
	JTextField zoneWidthTxtfld = new JTextField("40", 5);
	JLabel populationsLbl = new JLabel("populations ");

	JLabel produceTxtLbl = new JLabel("Produce: ");
	JTextField txtFilesTxtfld = new JTextField("1", 5);
	JLabel txtFilesLbl = new JLabel("text files ");

	JTextArea outputFld = new JTextArea(4, 40);

	JLabel maleFitness1 = new JLabel("", maleImage1, JLabel.CENTER);
	JLabel maleFitness2 = new JLabel("", maleImage2, JLabel.CENTER);
	JLabel maleFitness3 = new JLabel("", maleImage3, JLabel.CENTER);
	JLabel maleFitness4 = new JLabel("", maleImage4, JLabel.CENTER);
	JLabel maleFitness5 = new JLabel("", maleImage5, JLabel.CENTER);
	JLabel femaleFitness1 = new JLabel("", femaleImage1, JLabel.CENTER);
	JLabel femaleFitness2 = new JLabel("", femaleImage2, JLabel.CENTER);
	JLabel femaleFitness3 = new JLabel("", femaleImage3, JLabel.CENTER);
	JLabel femaleFitness4 = new JLabel("", femaleImage4, JLabel.CENTER);
	JLabel femaleFitness5 = new JLabel("", femaleImage5, JLabel.CENTER);
	JLabel femaleFitness6 = new JLabel("", femaleImage6, JLabel.CENTER);

	JTextField maleFitness1Txtfld = new JTextField("1", 5);
	JTextField maleFitness2Txtfld = new JTextField("1", 5);
	JTextField maleFitness3Txtfld = new JTextField("1", 5);
	JTextField maleFitness4Txtfld = new JTextField("1", 5);
	JTextField maleFitness5Txtfld = new JTextField("1", 5);
	JTextField femaleFitness1Txtfld = new JTextField("1", 5);
	JTextField femaleFitness2Txtfld = new JTextField("1", 5);
	JTextField femaleFitness3Txtfld = new JTextField("1", 5);
	JTextField femaleFitness4Txtfld = new JTextField("1", 5);
	JTextField femaleFitness5Txtfld = new JTextField("1", 5);
	JTextField femaleFitness6Txtfld = new JTextField("1", 5);

	JTextField simName = new JTextField("SIMNAME", 5);

	JCheckBox YinFemales = new JCheckBox("YinFemales", true);
	JCheckBox NextToYinFemales = new JCheckBox("NextToYinFemales", true);
	JCheckBox mtDNA = new JCheckBox("mtDNA", true);
	JCheckBox AutosomalAllele = new JCheckBox("AutosomalAllele", true);
	JCheckBox TotalY = new JCheckBox("TotalY", true);
	JCheckBox ObservedZone = new JCheckBox("ObservedZone", true);
	JCheckBox ShowFemales = new JCheckBox("ShowFemales", true);
	JCheckBox TotalFitness = new JCheckBox("TotalFitness", true);
	JCheckBox MaleFitness = new JCheckBox("MaleFitness", true);
	JCheckBox FemaleFitness = new JCheckBox("FemaleFitness", true);
	JCheckBox FemaleZone = new JCheckBox("ReportFusionSpeed", true);
	JCheckBox ChrCline = new JCheckBox("ChrCline", true);
	JCheckBox AllIndividuals = new JCheckBox("PlotAllIndividuals", true);

	JScrollPane scroll = new JScrollPane(outputFld,
			JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
			JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

	JLabel OutputEveryLbl = new JLabel("Output result every: ");
	JLabel VersionNumberLbl = new JLabel("Version 1.4 ");
	
	JTextField OutputEveryTxtfld = new JTextField("10", 5);

	JLabel generationsLbl2 = new JLabel("generations ");
	
	JLabel generationsLbl3 = new JLabel("generations ");
	
	JLabel StopGeneFlowLbl = new JLabel("Stop gene flow after: ");
	JTextField geneFlowGenerationsTxtfld = new JTextField("10000", 5);

	JButton go = new JButton("Go!", podismaImage);
	//JButton deterministic = new JButton("Deterministic", podismaImage);
	
	JLabel modeLbl = new JLabel("Choose simulation mode: ");
	JComboBox modeBox = new JComboBox();
	JLabel edgeLbl = new JLabel("Allow edge effects: ");
	JComboBox zoneEdgeBox = new JComboBox();
	
	PodismaEvent podEv = new PodismaEvent(this);

	public PodismaGUI() {
		//the constructor deals with layout only. If variables are declared
		// here they overwritte the ones outside and they are not visible.
		super("Podisma modeling!");
		setSize(1200, 750);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container pane = getContentPane();

		Font f = new Font("Courrier", Font.BOLD, 24);
		GridBagLayout gridbag = new GridBagLayout();
		GridBagConstraints constraints = new GridBagConstraints();
		pane.setLayout(gridbag);

		buildConstraints(constraints, 0, 0, 5, 1, 0, 100);
		constraints.fill = GridBagConstraints.NONE;
		constraints.anchor = GridBagConstraints.EAST;
		Title.setFont(f);
		gridbag.setConstraints(Title, constraints);
		pane.add(Title);
		
		buildConstraints(constraints, 0, 1, 1, 1, 0, 100);
		constraints.fill = GridBagConstraints.NONE;
		constraints.anchor = GridBagConstraints.EAST;
		//	JLabel popSizeLbl = new JLabel("Population size: ");
		gridbag.setConstraints(popSizeLbl, constraints);
		pane.add(popSizeLbl);

		buildConstraints(constraints, 1, 1, 1, 1, 0, 100);
		constraints.fill = GridBagConstraints.HORIZONTAL;
		//	JTextField popSizeTxtfld = new JTextField("100" ,5);
		gridbag.setConstraints(popSizeTxtfld, constraints);
		popSizeTxtfld.addActionListener(podEv);
		pane.add(popSizeTxtfld);

		buildConstraints(constraints, 0, 2, 1, 1, 0, 100);
		constraints.fill = GridBagConstraints.NONE;
		constraints.anchor = GridBagConstraints.EAST;
		//	JLabel runForLbl = new JLabel("Run for: ");
		gridbag.setConstraints(runForLbl, constraints);
		pane.add(runForLbl);

		buildConstraints(constraints, 1, 2, 1, 1, 0, 100);
		constraints.fill = GridBagConstraints.HORIZONTAL;
		//	JTextField generationsTxtfld = new JTextField("500" ,5);
		gridbag.setConstraints(generationsTxtfld, constraints);
		pane.add(generationsTxtfld);

		buildConstraints(constraints, 2, 2, 1, 1, 0, 100);
		constraints.fill = GridBagConstraints.NONE;
		constraints.anchor = GridBagConstraints.WEST;
		//	JLabel generationsLbl = new JLabel("generations ");
		gridbag.setConstraints(generationsLbl, constraints);
		pane.add(generationsLbl);

		buildConstraints(constraints, 0, 3, 1, 1, 0, 100);
		constraints.fill = GridBagConstraints.NONE;
		constraints.anchor = GridBagConstraints.EAST;
		//	JLabel ARecFrLbl = new JLabel("Autosomal loci recombination
		// frequency: ");
		gridbag.setConstraints(ARecFrLbl, constraints);
		pane.add(ARecFrLbl);

		buildConstraints(constraints, 1, 3, 1, 1, 0, 100);
		constraints.fill = GridBagConstraints.HORIZONTAL;
		//	JTextField ARecFrTxtfld = new JTextField("0.1" ,5);
		gridbag.setConstraints(ARecFrTxtfld, constraints);
		pane.add(ARecFrTxtfld);

		buildConstraints(constraints, 0, 4, 1, 1, 0, 100);
		constraints.fill = GridBagConstraints.NONE;
		constraints.anchor = GridBagConstraints.EAST;
		gridbag.setConstraints(XRecFrLbl, constraints);
		pane.add(XRecFrLbl);

		buildConstraints(constraints, 1, 4, 1, 1, 0, 100);
		constraints.fill = GridBagConstraints.HORIZONTAL;
		gridbag.setConstraints(XRecFrTxtfld, constraints);
		pane.add(XRecFrTxtfld);

		buildConstraints(constraints, 0, 5, 1, 1, 0, 100);
		constraints.fill = GridBagConstraints.NONE;
		constraints.anchor = GridBagConstraints.EAST;
		//	JLabel YRecFrLbl = new JLabel("Y chromosome loci recombination
		// frequency: ");
		gridbag.setConstraints(YRecFrLbl, constraints);
		pane.add(YRecFrLbl);

		buildConstraints(constraints, 1, 5, 1, 1, 0, 100);
		constraints.fill = GridBagConstraints.HORIZONTAL;
		//	JTextField YRecFrTxtfld = new JTextField("0.01" ,5);
		gridbag.setConstraints(YRecFrTxtfld, constraints);
		pane.add(YRecFrTxtfld);

		buildConstraints(constraints, 0, 6, 1, 1, 0, 100);
		constraints.fill = GridBagConstraints.NONE;
		constraints.anchor = GridBagConstraints.EAST;
		gridbag.setConstraints(FRecFrLbl, constraints);
		pane.add(FRecFrLbl);

		buildConstraints(constraints, 1, 6, 1, 1, 0, 100);
		constraints.fill = GridBagConstraints.HORIZONTAL;
		gridbag.setConstraints(FRecFrTxtfld, constraints);
		pane.add(FRecFrTxtfld);

		buildConstraints(constraints, 0, 7, 1, 1, 0, 100);
		constraints.fill = GridBagConstraints.NONE;
		constraints.anchor = GridBagConstraints.EAST;
		//	JLabel geneFlowLbl = new JLabel("Percentage of gene flow: ");
		gridbag.setConstraints(geneFlowLbl, constraints);
		pane.add(geneFlowLbl);

		buildConstraints(constraints, 1, 7, 1, 1, 0, 100);
		constraints.fill = GridBagConstraints.HORIZONTAL;
		//	JTextField geneFlowTxtfld = new JTextField("0.1" ,5);
		gridbag.setConstraints(geneFlowTxtfld, constraints);
		pane.add(geneFlowTxtfld);

		buildConstraints(constraints, 0, 8, 1, 1, 0, 100);
		constraints.fill = GridBagConstraints.NONE;
		constraints.anchor = GridBagConstraints.EAST;
		gridbag.setConstraints(zoneWidthLbl, constraints);
		pane.add(zoneWidthLbl);

		buildConstraints(constraints, 1, 8, 1, 1, 0, 100);
		constraints.fill = GridBagConstraints.HORIZONTAL;
		gridbag.setConstraints(zoneWidthTxtfld, constraints);
		pane.add(zoneWidthTxtfld);

		buildConstraints(constraints, 2, 8, 1, 1, 0, 100);
		constraints.fill = GridBagConstraints.NONE;
		constraints.anchor = GridBagConstraints.WEST;
		gridbag.setConstraints(populationsLbl, constraints);
		pane.add(populationsLbl);

		buildConstraints(constraints, 0, 9, 1, 1, 0, 100);
		constraints.fill = GridBagConstraints.NONE;
		constraints.anchor = GridBagConstraints.EAST;
		//	JLabel produceTxtLbl = new JLabel("Produce: ");
		gridbag.setConstraints(produceTxtLbl, constraints);
		pane.add(produceTxtLbl);

		buildConstraints(constraints, 1, 9, 1, 1, 0, 100);
		constraints.fill = GridBagConstraints.HORIZONTAL;
		//	JTextField txtFilesTxtfld = new JTextField("10" ,5);
		gridbag.setConstraints(txtFilesTxtfld, constraints);
		pane.add(txtFilesTxtfld);

		buildConstraints(constraints, 2, 9, 1, 1, 0, 100);
		constraints.fill = GridBagConstraints.NONE;
		constraints.anchor = GridBagConstraints.WEST;
		//	JLabel txtFilesLbl = new JLabel("text files ");
		gridbag.setConstraints(txtFilesLbl, constraints);
		pane.add(txtFilesLbl);

		buildConstraints(constraints, 0, 10, 1, 1, 0, 100);
		constraints.fill = GridBagConstraints.NONE;
		constraints.anchor = GridBagConstraints.EAST;
		gridbag.setConstraints(maleFitness1, constraints);
		maleFitness1.setToolTipText("Normal Fused male");
		pane.add(maleFitness1);

		buildConstraints(constraints, 0, 11, 1, 1, 0, 100);
		constraints.fill = GridBagConstraints.NONE;
		gridbag.setConstraints(maleFitness1Txtfld, constraints);
		pane.add(maleFitness1Txtfld);

		buildConstraints(constraints, 1, 10, 1, 1, 0, 100);
		constraints.fill = GridBagConstraints.NONE;
		constraints.anchor = GridBagConstraints.EAST;
		gridbag.setConstraints(maleFitness2, constraints);
		maleFitness2
				.setToolTipText("detects problem if NeoX and NeoY have been coevolving");
		pane.add(maleFitness2);

		buildConstraints(constraints, 1, 11, 1, 1, 0, 100);
		constraints.fill = GridBagConstraints.NONE;
		gridbag.setConstraints(maleFitness2Txtfld, constraints);
		pane.add(maleFitness2Txtfld);

		buildConstraints(constraints, 2, 10, 1, 1, 0, 100);
		constraints.fill = GridBagConstraints.NONE;
		constraints.anchor = GridBagConstraints.EAST;
		gridbag.setConstraints(maleFitness3, constraints);
		maleFitness3.setToolTipText("Y overdose (or Y dosage compensated)");
		pane.add(maleFitness3);

		buildConstraints(constraints, 2, 11, 1, 1, 0, 100);
		constraints.fill = GridBagConstraints.NONE;
		gridbag.setConstraints(maleFitness3Txtfld, constraints);
		pane.add(maleFitness3Txtfld);

		buildConstraints(constraints, 3, 10, 1, 1, 0, 100);
		constraints.fill = GridBagConstraints.NONE;
		constraints.anchor = GridBagConstraints.EAST;
		gridbag.setConstraints(maleFitness4, constraints);
		maleFitness4.setToolTipText("Normal Unfused male");
		pane.add(maleFitness4);

		buildConstraints(constraints, 3, 11, 1, 1, 0, 100);
		constraints.fill = GridBagConstraints.NONE;
		gridbag.setConstraints(maleFitness4Txtfld, constraints);
		pane.add(maleFitness4Txtfld);

		buildConstraints(constraints, 4, 10, 1, 1, 0, 100);
		constraints.fill = GridBagConstraints.NONE;
		constraints.anchor = GridBagConstraints.EAST;
		gridbag.setConstraints(maleFitness5, constraints);
		maleFitness5
				.setToolTipText("No Y so problem if NeoX, NeoY have been coevolving");
		pane.add(maleFitness5);

		buildConstraints(constraints, 4, 11, 1, 1, 0, 100);
		constraints.fill = GridBagConstraints.NONE;
		gridbag.setConstraints(maleFitness5Txtfld, constraints);
		pane.add(maleFitness5Txtfld);

		buildConstraints(constraints, 0, 12, 1, 1, 0, 100);
		constraints.fill = GridBagConstraints.NONE;
		constraints.anchor = GridBagConstraints.EAST;
		gridbag.setConstraints(femaleFitness1, constraints);
		femaleFitness1.setToolTipText("Normal Fused female");
		pane.add(femaleFitness1);

		buildConstraints(constraints, 0, 13, 1, 1, 0, 100);
		constraints.fill = GridBagConstraints.NONE;
		gridbag.setConstraints(femaleFitness1Txtfld, constraints);
		pane.add(femaleFitness1Txtfld);

		buildConstraints(constraints, 1, 12, 1, 1, 0, 100);
		constraints.fill = GridBagConstraints.NONE;
		constraints.anchor = GridBagConstraints.EAST;
		gridbag.setConstraints(femaleFitness2, constraints);
		femaleFitness2.setToolTipText("Normal Unfused female");
		pane.add(femaleFitness2);

		buildConstraints(constraints, 1, 13, 1, 1, 0, 100);
		constraints.fill = GridBagConstraints.NONE;
		gridbag.setConstraints(femaleFitness2Txtfld, constraints);
		pane.add(femaleFitness2Txtfld);

		buildConstraints(constraints, 2, 12, 1, 1, 0, 100);
		constraints.fill = GridBagConstraints.NONE;
		constraints.anchor = GridBagConstraints.EAST;
		gridbag.setConstraints(femaleFitness3, constraints);
		femaleFitness3
				.setToolTipText("NeoX, X have diverged (because of Y, otherwise same as autosomes diverging)");
		pane.add(femaleFitness3);

		buildConstraints(constraints, 2, 13, 1, 1, 0, 100);
		constraints.fill = GridBagConstraints.NONE;
		gridbag.setConstraints(femaleFitness3Txtfld, constraints);
		pane.add(femaleFitness3Txtfld);

		buildConstraints(constraints, 3, 12, 1, 1, 0, 100);
		constraints.fill = GridBagConstraints.NONE;
		constraints.anchor = GridBagConstraints.EAST;
		gridbag.setConstraints(femaleFitness4, constraints);
		femaleFitness4.setToolTipText("Y in females");
		pane.add(femaleFitness4);

		buildConstraints(constraints, 3, 13, 1, 1, 0, 100);
		constraints.fill = GridBagConstraints.NONE;
		gridbag.setConstraints(femaleFitness4Txtfld, constraints);
		pane.add(femaleFitness4Txtfld);

		buildConstraints(constraints, 4, 12, 1, 1, 0, 100);
		constraints.fill = GridBagConstraints.NONE;
		constraints.anchor = GridBagConstraints.EAST;
		gridbag.setConstraints(femaleFitness5, constraints);
		femaleFitness5.setToolTipText("Y in females AND no coevolved NeoX");
		pane.add(femaleFitness5);

		buildConstraints(constraints, 4, 13, 1, 1, 0, 100);
		constraints.fill = GridBagConstraints.NONE;
		gridbag.setConstraints(femaleFitness5Txtfld, constraints);
		pane.add(femaleFitness5Txtfld);

		buildConstraints(constraints, 5, 12, 1, 1, 0, 100);
		constraints.fill = GridBagConstraints.NONE;
		constraints.anchor = GridBagConstraints.EAST;
		gridbag.setConstraints(femaleFitness6, constraints);
		femaleFitness6.setToolTipText("2 Ys in females, no L3");
		pane.add(femaleFitness6);

		buildConstraints(constraints, 5, 13, 1, 1, 0, 100);
		constraints.fill = GridBagConstraints.NONE;
		gridbag.setConstraints(femaleFitness6Txtfld, constraints);
		pane.add(femaleFitness6Txtfld);

		buildConstraints(constraints, 1, 15, 1, 1, 0, 100);
		constraints.fill = GridBagConstraints.NONE;
		gridbag.setConstraints(YinFemales, constraints);
		YinFemales.setToolTipText("Outputs occurence of the Y in females");
		pane.add(YinFemales);

		buildConstraints(constraints, 2, 15, 1, 1, 0, 100);
		constraints.fill = GridBagConstraints.NONE;
		gridbag.setConstraints(NextToYinFemales, constraints);
		NextToYinFemales
				.setToolTipText("Outputs occurence of locus next to Y in females: shows how fast LD between them is broken (as this allele is not directly selected agains");
		pane.add(NextToYinFemales);

		buildConstraints(constraints, 3, 15, 1, 1, 0, 100);
		constraints.fill = GridBagConstraints.NONE;
		gridbag.setConstraints(mtDNA, constraints);
		mtDNA
				.setToolTipText("Outputs mtDNA variant occurence: Use to check weather the UF mtDNA variant is displaced by the F one");
		pane.add(mtDNA);

		buildConstraints(constraints, 4, 15, 1, 1, 0, 100);
		constraints.fill = GridBagConstraints.NONE;
		gridbag.setConstraints(AutosomalAllele, constraints);
		AutosomalAllele
				.setToolTipText("Outputs autosomal alleles  of position [10] of all individuals");
		pane.add(AutosomalAllele);

		buildConstraints(constraints, 5, 15, 1, 1, 0, 100);
		constraints.fill = GridBagConstraints.NONE;
		gridbag.setConstraints(TotalY, constraints);
		TotalY
				.setToolTipText("Outputs total Y occurence in all individuals (the selected locus): Use to check if it dissappears from the zone");
		pane.add(TotalY);

		buildConstraints(constraints, 6, 15, 1, 1, 0, 100);
		constraints.fill = GridBagConstraints.NONE;
		gridbag.setConstraints(ObservedZone, constraints);
		ObservedZone
				.setToolTipText("Shows Zone as observed under the microscope");
		pane.add(ObservedZone);

		buildConstraints(constraints, 1, 16, 1, 1, 0, 100);
		constraints.fill = GridBagConstraints.NONE;
		gridbag.setConstraints(ShowFemales, constraints);
		ShowFemales
				.setToolTipText("Outputs the three possible Y containing females: use to check their proportions and contribution to the observed result");
		pane.add(ShowFemales);

		buildConstraints(constraints, 2, 16, 1, 1, 0, 100);
		constraints.fill = GridBagConstraints.NONE;
		gridbag.setConstraints(TotalFitness, constraints);
		TotalFitness
				.setToolTipText("Outputs average of total fitness per M/F pair in a population: Use to observe at which location along the zone selection is acting, correlate with other changes");
		pane.add(TotalFitness);

		buildConstraints(constraints, 3, 16, 1, 1, 0, 100);
		constraints.fill = GridBagConstraints.NONE;
		gridbag.setConstraints(MaleFitness, constraints);
		MaleFitness.setToolTipText("Fitness of males only");
		pane.add(MaleFitness);

		buildConstraints(constraints, 4, 16, 1, 1, 0, 100);
		constraints.fill = GridBagConstraints.NONE;
		gridbag.setConstraints(FemaleFitness, constraints);
		FemaleFitness.setToolTipText("Fitness of females only");
		pane.add(FemaleFitness);

		// I used old code to add fusion speed determination functionality. The names of the parts used by the code have remained the same, the code use to calculate the frequency of the fusion observed from females.
		buildConstraints(constraints, 5, 16, 1, 1, 0, 100);
		constraints.fill = GridBagConstraints.NONE;
		gridbag.setConstraints(FemaleZone, constraints);
		FemaleZone.setToolTipText("Reports only populations with >50% fusion. Easy to determine fusion speed by looking at output file (find on00' 0.)");
		pane.add(FemaleZone);
		
		buildConstraints(constraints, 6, 16, 1, 1, 0, 100);
		constraints.fill = GridBagConstraints.NONE;
		gridbag.setConstraints(ChrCline, constraints);
		ChrCline.setToolTipText("RealChrCline from both sexes, plots F/UF ratio");
		pane.add(ChrCline);
		
		buildConstraints(constraints, 6, 14, 1, 1, 0, 100);
		constraints.fill = GridBagConstraints.NONE;
		gridbag.setConstraints(AllIndividuals, constraints);
		AllIndividuals.setToolTipText("Provides output of presence of all individuals in all populations");
		pane.add(AllIndividuals);
		
		buildConstraints(constraints, 0, 17, 5, 1, 0, 100);
		constraints.fill = GridBagConstraints.NONE;
		constraints.anchor = GridBagConstraints.WEST;
		//	JTextArea outputFieldTxtfld = new JTextArea(4, 40);
		gridbag.setConstraints(scroll, constraints);
		outputFld.setEditable(false);
		outputFld.setLineWrap(true);
		outputFld.setWrapStyleWord(true);
		outputFld
				.setToolTipText("Output information while running appears here");
		pane.add(scroll);

		buildConstraints(constraints, 4, 17, 1, 1, 0, 100);
		constraints.fill = GridBagConstraints.NONE;
		gridbag.setConstraints(OutputEveryLbl, constraints);
		pane.add(OutputEveryLbl);

		buildConstraints(constraints, 6, 0, 1, 1, 0, 100);
		constraints.fill = GridBagConstraints.NONE;
		gridbag.setConstraints(VersionNumberLbl, constraints);
		pane.add(VersionNumberLbl);
		
		buildConstraints(constraints, 5, 17, 1, 1, 0, 100);
		constraints.fill = GridBagConstraints.NONE;
		gridbag.setConstraints(OutputEveryTxtfld, constraints);
		pane.add(OutputEveryTxtfld);

		buildConstraints(constraints, 5, 1, 1, 1, 0, 100);
		constraints.fill = GridBagConstraints.NONE;
		gridbag.setConstraints(simName, constraints);
		pane.add(simName);

		buildConstraints(constraints, 6, 17, 1, 1, 0, 100);
		constraints.fill = GridBagConstraints.NONE;
		constraints.anchor = GridBagConstraints.WEST;
		gridbag.setConstraints(generationsLbl2, constraints);
		generationsLbl2
				.setToolTipText("This needs to be increased by 1 for every 1000 generations the simulation is run, so the excel macro still works!");
		pane.add(generationsLbl2);

		buildConstraints(constraints, 6, 1, 1, 2, 0, 100);
		constraints.fill = GridBagConstraints.BOTH;
		constraints.anchor = GridBagConstraints.CENTER;
		//	JButton go = new JButton("Go!");
		go.setFont(f);
		gridbag.setConstraints(go, constraints);
		go.setToolTipText("Run simulation");
		go.setEnabled(false);
		//	Add listeners
		go.addActionListener(podEv);
		pane.add(go);

//		buildConstraints(constraints, 6, 3, 1, 2, 0, 100);
//		constraints.fill = GridBagConstraints.BOTH;
//		constraints.anchor = GridBagConstraints.CENTER;
//		deterministic.setFont(f);
//		gridbag.setConstraints(deterministic, constraints);
//		deterministic.setToolTipText("Run deterministic simulation");
//		//	Add listeners
//		deterministic.addActionListener(podEv);
//		pane.add(deterministic);
	
		buildConstraints(constraints, 4, 3, 1, 1, 0, 100);
		constraints.fill = GridBagConstraints.NONE;
		constraints.anchor = GridBagConstraints.EAST;
		gridbag.setConstraints(modeLbl, constraints);
		pane.add(modeLbl);
		
		buildConstraints(constraints, 5, 3, 1, 2, 0, 100);
		constraints.fill = GridBagConstraints.BOTH;
		constraints.anchor = GridBagConstraints.CENTER;
		gridbag.setConstraints(modeBox, constraints);
		modeBox.setToolTipText("Choose somulation mode");
		modeBox.addItem(" ");
		modeBox.addItem("deterministicMode");
		modeBox.addItem("stochasticMode");
		//	Add listeners
		modeBox.addActionListener(podEv);
		pane.add(modeBox);
		
		buildConstraints(constraints, 4, 5, 1, 1, 0, 100);
		constraints.fill = GridBagConstraints.NONE;
		constraints.anchor = GridBagConstraints.EAST;
		gridbag.setConstraints(edgeLbl, constraints);
		pane.add(edgeLbl);
		 
		buildConstraints(constraints, 5, 5, 1, 2, 0, 100);
		constraints.fill = GridBagConstraints.BOTH;
		constraints.anchor = GridBagConstraints.CENTER;
		gridbag.setConstraints(zoneEdgeBox, constraints);
		zoneEdgeBox.setToolTipText("Allow edge effects");
		zoneEdgeBox.addItem(" ");
		zoneEdgeBox.addItem("edgeEffects");
		zoneEdgeBox.addItem("noEdgeEffects");
		//	Add listeners
		zoneEdgeBox.addActionListener(podEv);
		pane.add(zoneEdgeBox);
		
		buildConstraints(constraints, 4, 7, 1, 1, 0, 100);
		constraints.fill = GridBagConstraints.NONE;
		constraints.anchor = GridBagConstraints.EAST;
		gridbag.setConstraints(StopGeneFlowLbl, constraints);
		pane.add(StopGeneFlowLbl);
		
		buildConstraints(constraints, 5, 7, 1, 1, 0, 100);
		constraints.fill = GridBagConstraints.HORIZONTAL;
		gridbag.setConstraints(geneFlowGenerationsTxtfld, constraints);
		pane.add(geneFlowGenerationsTxtfld);
		
		buildConstraints(constraints, 6, 7, 1, 1, 0, 100);
		constraints.fill = GridBagConstraints.NONE;
		constraints.anchor = GridBagConstraints.WEST;
		gridbag.setConstraints(generationsLbl3, constraints);
		pane.add(generationsLbl3);
				
		setContentPane(pane);
		setVisible(true);

		//	  int GUIpopSize, GUIgenerations, GUItxtFiles;
	}

	public static void main(String[] arguments) {
		PodismaGUI podisma = new PodismaGUI();

		System.out.println("GUIpopSize when start run is: "
				+ Storage.GUIpopSize);
	}

	//	Helper method that allows easy definition of the constraints values for
	// each GUI component
	public void buildConstraints(GridBagConstraints gbc, int gx, int gy,
			int gw, int gh, int wx, int wy) {
		gbc.gridx = gx;
		gbc.gridy = gy;
		gbc.gridwidth = gw;
		gbc.gridheight = gh;
		gbc.weightx = wx;
		gbc.weighty = wy;
	}
	/*
	 * public void ReadGUI(Individual pedestris) { pedestris.femaleStatus =
	 * true; }
	 * 
	 *  
	 */
}