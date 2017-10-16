package com.jonas.olsson.controler;

import com.jonas.olsson.view.View;

import javafx.application.Application;

public class SteamLiteMain {
	
	public static void main(String [] args) {
		
		Application.launch(View.class,args);
		/*
		try {
			Desktop.getDesktop().browse(new URI("steam://runsafe/420"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
	}

}
	