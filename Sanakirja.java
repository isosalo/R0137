import java.util.HashMap;
import java.util.Scanner;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;
import java.util.Map;
import java.util.Map.Entry;
import java.beans.ExceptionListener;
import java.io.*;


public class Sanakirja {
	
		static void serializeToXML(HashMap<String, String> sanavarasto) throws Exception  {
			FileOutputStream fos = new FileOutputStream("Sanasto.xml");
			XMLEncoder encoder = new XMLEncoder(fos);
			encoder.writeObject(sanavarasto);
			encoder.close();
			fos.close();
		}

//		private static Auto deserializeFromXML() throws Exception {

		static  HashMap<String, String> deserializeFromXML(HashMap<String, String> sanavarasto) throws Exception {
			FileInputStream fis = new FileInputStream("Sanasto.xml");
			XMLDecoder decoder = new XMLDecoder(fis);
			HashMap<String, String> decodedSanasto = (HashMap<String, String>) decoder.readObject();
			decoder.close();
			fis.close();
			return decodedSanasto;
		}

	public static void main(String[] args) throws Exception {
		
		
		HashMap<String, String> sanavarasto = new HashMap<>();
		
		String[] suomi = { "kissa", "koira", "hevonen", "auto", "vene" };
		String[] englanti = { "cat", "dog", "horse", "car", "boat" };
		
		for (int i = 0; i < suomi.length; i++) {
			sanavarasto.put(suomi[i], englanti[i]);
			}
		
		
	 	Scanner lukija = new Scanner(System.in);

		
		while (true) {
		
		System.out.println("Sana alkukielellä? (tyhjä lopettaa): ");
		String suomisana = lukija.nextLine();
		
		System.out.println("Sana käännettynä?  (tyhjä lopettaa): ");
		String englantisana = lukija.nextLine();

		sanavarasto.put(suomisana, englantisana);
		
		serializeToXML(sanavarasto);

		

		if (suomisana.isEmpty() || englantisana.isEmpty() ) {
							
			String sana;
			

		while (true) {
		

		
		sanavarasto = deserializeFromXML(sanavarasto);
		
		System.out.println("\nSanat sanakirjasta:\n");

		Iterator<Entry<String, String>> it = sanavarasto.entrySet().iterator();
		while (it.hasNext()) {
			HashMap.Entry<String, String> alkio = (HashMap.Entry<String, String>) it.next();
			System.out.println(alkio.getKey() + " = " + alkio.getValue());
		}
	
 
    System.out.println("Minkä sanan käännöksen haluat tietää? (tyhjä sana lopettaa)");
    sana = lukija.nextLine();
    
	if (sana.isEmpty()) {
		serializeToXML(sanavarasto);
		System.out.println("Ohjelma lopetetaan, kiitos käynnistä!");
		System.exit(0);
	}
	
	if (sanavarasto.get(sana) == null) {
		System.out.println("Käännöstä ei löydy. \n");
	}
	
    else {
    System.out.println("Sanan " + "\"" + sana + "\"" + " käännös on " + "\"" + sanavarasto.get(sana) + "\"");
    System.out.println();
    }	
	}		
		}	
		}
	}

}
