package com.jonas.olsson.controler;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class SteamLiteMain {
	
	public static void main(String [] args) {
	
		try {
			Desktop.getDesktop().browse(new URI("steam://runsafe/666"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
