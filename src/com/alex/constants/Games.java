package com.alex.constants;

import java.util.HashMap;
import java.util.Map;

public class Games {
	public static Map<Long, String> games = new HashMap<Long, String>();
	
	public static void init() {
		
		//minecraft
		games.put(new Long(-1), "images/minecraft/");
		
		//tf2
		games.put(new Long(440), "images/tf2/");
		
		//arma 3
		games.put(new Long(107410), "images/arma-3/");
		
		//csgo
		games.put(new Long(730), "images/csgo");
		
		//rust
		games.put(new Long(252490), "images/rust");
		
		//factorio
		games.put(new Long(427520), "images/factorio");
		
		//boogaloo
		games.put(new Long(317360), "images/boogaloo");
		
		//fistfull of frags
		games.put(new Long(265630), "images/fof");
		
		//gmod
		games.put(new Long(4000), "images/gmod");
		
		//insurgency
		games.put(new Long(222880), "images/insurgency");
		
		//left 4 dead 
		games.put(new Long(500), "images/l4d");
		
		//left 4 dead 2
		games.put(new Long(550), "images/l4d2");
		
		//minecraft bedrock
		games.put(new Long(-2), "images/bedrock");		
		
		//mordhau
		games.put(new Long(629760), "images/mordhau");
		
		//starbound
		games.put(new Long(211820), "images/starbound");
		
		//terraria
		games.put(new Long(105600), "images/terraria");
		
		//unturned
		games.put(new Long(304930), "images/unturned");
		
		//7 days to die
		games.put(new Long(251570), "images/7dtd");
		
		//ark
		games.put(new Long(346110), "images/ark");
		
	
	}
	
	

}
