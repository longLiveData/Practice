import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class DNA {

	private static int MINIMUMNUMBEROFCODONS = 5;
	private static double MINIMUMMASSPERCENTAGE = 30.0;
	final private static int UNIQUENUCLEOTIDES = 4;
	final private static int NUCLEOTIDESPERCODON = 3;

	public static void main(String[] args){

		// start program and get input/output file name
		String[] fileName = start();
		String inputFileName = fileName[0];
		String outputFileName = fileName[1];

		// get input file content
		ArrayList<String> contents = readFile(inputFileName);
		for (int i = 0; i< contents.size(); i+= 2) {
			// get original string
			String name = contents.get(i);
			String dna = contents.get(i+1);

			// counts
			String nucleotidesDna = getNucleotides(dna);
			int[] nucleotidesCounts = getNucleotidesCounts(nucleotidesDna);
			int dashCount = getDashCounts(nucleotidesDna);

			// masses
			double[] massList = getMassList(nucleotidesCounts);
			double dashMass = dashCount * 100.0;
			double totalMass = getTotalMass(massList, dashMass);
			double[] massPercent = getMassPercent(massList, totalMass);

			// codons list
			String[] codonsList = getCodonsList(nucleotidesDna);

			//judge if a protein
			boolean ifProtein = isProtein(codonsList, massPercent);

			// output
			printAllFileOutput(name, nucleotidesDna, nucleotidesCounts, massPercent, totalMass, codonsList, ifProtein, outputFileName);

		}
	}

	// start program, print information and get input
	public static String[] start(){
		Scanner sc = new Scanner(System.in);
		String[] res = {"", ""};

		System.out.println("This program reports information about DNA");
		System.out.println("nucleotide sequences that may encode proteins.");
		System.out.print("Input file name?");
		res[0] = sc.nextLine();
		System.out.print("Output file name?");
		res[1] = sc.nextLine();

		return res;
	}

	// read input file
	public static ArrayList<String> readFile(String inputFileName){
		ArrayList<String> fileContent = new ArrayList<>();

		try {
			Scanner sc = new Scanner(new File(inputFileName));
			while(sc.hasNextLine()){
				fileContent.add(sc.nextLine());
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		return fileContent;
	}

	// transform string to nucleotides
	public static String getNucleotides(String dna){
		return dna.toUpperCase();
	}

	// get nucleotides counts
	public static int[] getNucleotidesCounts(String dna) {
		int[] counts = {0, 0, 0, 0};
		String[] names = {"A", "C", "G", "T"};
		for (int index = 0; index < dna.length(); index++) {
			for (int i = 0; i < UNIQUENUCLEOTIDES; i++) {
				if (dna.substring(index, index+1).equals(names[i])) {
					counts[i] += 1;
				}
			}
		}
		return counts;
	}

	// get dash counts
	public static int getDashCounts(String dna){
		int count = 0;
		for (int i=0; i<dna.length(); i++) {
			if (dna.charAt(i) == '-') {
				count += 1;
			}
		}
		return count;
	}

	// get mass list
	public static double[] getMassList(int[] counts){
		double[] massList = {0.0, 0.0, 0.0, 0.0};
		double[] masses = {135.128, 111.103, 151.128, 125.107};
		for (int i=0; i<UNIQUENUCLEOTIDES; i++){
			massList[i] = counts[i] * masses[i];
		}
		return massList;
	}

	// get total mass of the dna
	public static double getTotalMass(double[] massList, double dashMass){
		double totalMass = 0.0;
		for (int i=0; i<UNIQUENUCLEOTIDES; i++) {
			totalMass += massList[i];
		}
		totalMass += dashMass;
		return totalMass;
	}

	// get mass percent list
	public static double[] getMassPercent(double[] massList, double totalMass) {
		double[] massPercent = {0.0, 0.0, 0.0, 0.0};
		for (int i=0; i<UNIQUENUCLEOTIDES; i++) {
			massPercent[i] = round(massList[i] / totalMass * 100);
		}
		return massPercent;
	}

	// rounded to one digit
	public static double round(double a){
		return Math.round(a * 10.0) / 10.0;
	}

	// get codons list
	public static String[] getCodonsList(String nucleotidesDna){
		String str = nucleotidesDna.replace("-", "");
		int len = str.length() / NUCLEOTIDESPERCODON;
		String[] codons = new String[len];
		for (int i = 0; i < len; i++) {
			int start = i * NUCLEOTIDESPERCODON;
			codons[i] = str.substring(start, start+3);
		}
		return codons;
	}

	// if a protein
	public static boolean isProtein(String[] codonsList, double[] massPercent) {
		if (codonsList.length >= MINIMUMNUMBEROFCODONS) {
			if (codonsList[0].equals("ATG")) {
				String last = codonsList[codonsList.length-1];
				if (last.equals("TAA") || last.equals("TAG") || last.equals("TGA")) {
					if (massPercent[1] + massPercent[2] >= MINIMUMMASSPERCENTAGE) {
						return true;
					}
				}
			}
		}
		return false;
	}

	// print out contents
	public static void printAllFileOutput(String name, String nucleotidesDna, int[] nucleotidesCounts,
										  double[] massList, double totalMass, String[] codonsList,
										  boolean ifProtein, String outputFileName){

		String info = "";
		info += "Region Name: " + name + "\n";
		info += "Nucleotides: " + nucleotidesDna + "\n";
		info += "Nuc. Counts: " + Arrays.toString(nucleotidesCounts) + "\n";
		info += "Total Mass%: " + Arrays.toString(massList) + " of " + round(totalMass) + "\n";
		info += "Codons List: " + Arrays.toString(codonsList) + "\n";
		if (ifProtein == true) {
			info += "Is Protein?: " + "YES" + "\n";
		} else {
			info += "Is Protein?: " + "NO" + "\n";
		}

		FileWriter fw = null;
		try {
			File f = new File(outputFileName);
			fw = new FileWriter(f, true);
		} catch (IOException e) {
			e.printStackTrace();
		}
		PrintWriter pw = new PrintWriter(fw);
		pw.println(info);
		pw.flush();
		try {
			fw.flush();
			pw.close();
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
