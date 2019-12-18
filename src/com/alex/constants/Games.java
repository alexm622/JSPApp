package com.alex.constants;

import java.util.HashMap;
import java.util.Map;

public class Games {
	public static Map<Long, String> games = new HashMap<Long, String>();
	public static final String path = "images/games/";
	
	
	public static void init() {
		
		//minecraft
		games.put(new Long(-1), path + "minecraft/");
		
		//tf2
		games.put(new Long(440), path + "tf2/");
		
		//arma 3
		games.put(new Long(107410), path + "arma-3/");
		
		//csgo
		games.put(new Long(730), path + "csgo/");
		
		//rust
		games.put(new Long(252490), path + "rust/");
		
		//factorio
		games.put(new Long(427520), path + "factorio/");
		
		//boogaloo
		games.put(new Long(317360), path + "boogaloo/");
		
		//fistfull of frags
		games.put(new Long(265630), path + "fof/");
		
		//gmod
		games.put(new Long(4000), path + "gmod/");
		
		//insurgency
		games.put(new Long(222880), path + "insurgency/");
		
		//left 4 dead 
		games.put(new Long(500), path + "l4d/");
		
		//left 4 dead 2
		games.put(new Long(550), path + "l4d2/");
		
		//minecraft bedrock
		games.put(new Long(-2), path + "bedrock/");		
		
		//mordhau
		games.put(new Long(629760), path + "mordhau/");
		
		//starbound
		games.put(new Long(211820), path + "starbound/");
		
		//terraria
		games.put(new Long(105600), path + "terraria/");
		
		//unturned
		games.put(new Long(304930), path + "unturned/");
		
		//7 days to die
		games.put(new Long(251570), path + "7dtd/");
		
		//ark
		games.put(new Long(346110), path + "ark/");
		
	
	}
	
	

}
