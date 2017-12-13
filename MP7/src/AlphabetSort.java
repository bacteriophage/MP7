import java.util.Scanner;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URI;
import java.net.URISyntaxException;

public class AlphabetSort {

	public static void main(String[] args) {
		String[] output = mergesort(getInput(), 0, getInput().length - 1);
		for (int i = 0; i < output.length; i++) {
			System.out.println(output[i]);
		}
	}
	public static String[] getInput() {
		String path = AlphabetSort.class.getClassLoader()
                .getResource("text.txt").getFile();
        try {
			path = new URI(path).getPath();
		} catch (URISyntaxException e1) {
			e1.printStackTrace();
		}
		File file = new File(path);
		String input = "";
		Scanner sc;
		try {
			sc = new Scanner(file);
			while (sc.hasNextLine()) {
				input = input + sc.nextLine() + "\n";
			}
			sc.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		String[] output = input.split("\n");
		return output;
	}	
	public static String[] mergesort(String[] input, int lo, int hi) {
		int mid = (lo + hi) / 2;
		if (lo == hi) {
			String[] output = new String[1];
			output[0] = input[lo];
			return output;
		} else if (hi - lo == 1) {
			String[] output = new String[2];
			if (input[lo].compareToIgnoreCase(input[hi]) > 0) {
				output[0] = input[hi];
				output[1] = input[lo];
			} else {
				output[0] = input[lo];
				output[1] = input[hi];
			}
			return output;
		} else {
			String[] sorted1 = mergesort(input, lo, mid);
			String[] sorted2 = mergesort(input, mid + 1, hi);
			return merge(sorted1, sorted2);
		}		
	}
	public static String[] merge(String[] input1, String[] input2) {
		String[] merged = new String[input1.length + input2.length];
		int j = 0;
		int k = 0;
		for (int i = 0; i < merged.length; i++) {
			if (j < input1.length && k < input2.length) {
				if (input1[j].compareToIgnoreCase(input2[k]) < 0) {
					merged[i] = input1[j];
					j++;
				} else {
					merged[i] = input2[k];
					k++;
				}
			} else if (j >= input1.length && k < input2.length) {
				merged[i] = input2[k];
				k++;
			} else if (j < input1.length && k >= input2.length) {
				merged[i] = input1[j];
				j++;
			}
		}
		return merged;
	}
}

