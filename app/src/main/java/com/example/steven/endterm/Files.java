package com.example.steven.endterm;
/**
 * @(#)Files.java
 *
 *
 * @author
 * @version 1.00 2016/4/29
 */

import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

public class Files{


	public static void main(String[] args) throws IOException{
		Venue v1 = new Venue("999", "pub", "Souths Bar", "O Connell ave", "Traditional Irish pub. Great Guinness");
		writeToFile(v1.toString());
		Venue v2 = new Venue("888", "pub", "O Connells", "Monaleen", "A suburban pub, great craic");
		writeToFile(v2.toString());

		readFile();
	}

	public static void writeToFile(String text) throws IOException{
		File f = new File("venues.txt");
		if (!f.exists()){
			f.createNewFile();
		}
		FileWriter fw = new FileWriter(f, true);
		BufferedWriter bw = new BufferedWriter(fw);
		bw.newLine();
		bw.write(text);
		bw.close();
	}

	public static void readFile() throws IOException{
		File f = new File("venues.txt");
		if (!f.exists()){
			throw new IOException();
		}
		FileReader fr = new FileReader(f);
		BufferedReader reader = new BufferedReader(fr);
		String line = reader.readLine();
		while (line != null) {
    			System.out.println(line);
    			line = reader.readLine();
		}
		fr.close();
	}

}
