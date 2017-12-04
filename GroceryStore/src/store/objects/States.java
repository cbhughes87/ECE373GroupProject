package store.objects;

import java.util.ArrayList;
import java.util.HashMap;

public class States {
	public static HashMap<String, String> stateCodeToNameMap = new HashMap<String, String>();
	static{
		stateCodeToNameMap.put("AK", "Alaska");
	    stateCodeToNameMap.put("AL", "Alabama");
	    stateCodeToNameMap.put("AR", "Akansas");
	    stateCodeToNameMap.put("AZ", "Arizona");
	    stateCodeToNameMap.put("CA", "California");
	    stateCodeToNameMap.put("CO", "Colorado");
	    stateCodeToNameMap.put("CT", "Connecticut");
	    stateCodeToNameMap.put("DC", "District of Columbia");
	    stateCodeToNameMap.put("DE", "Delaware");
	    stateCodeToNameMap.put("FL", "Florida");
	    stateCodeToNameMap.put("GA", "Georgia");
	    stateCodeToNameMap.put("HI", "Hawaii");
	    stateCodeToNameMap.put("IA", "Iowa");
	    stateCodeToNameMap.put("ID", "Idaho");
	    stateCodeToNameMap.put("IL", "Illinois");
	    stateCodeToNameMap.put("IN", "Indiana");
	    stateCodeToNameMap.put("KS", "Kansas");
	    stateCodeToNameMap.put("KY", "Kentucky");
	    stateCodeToNameMap.put("LA", "Louisiana");
	    stateCodeToNameMap.put("MA", "Massachusetts");
	    stateCodeToNameMap.put("MD", "Maryland");
	    stateCodeToNameMap.put("ME", "Maine");
	    stateCodeToNameMap.put("MI", "Michigan");
	    stateCodeToNameMap.put("MN", "Minnesota");
	    stateCodeToNameMap.put("MO", "Missouri");
	    stateCodeToNameMap.put("MS", "Mississippi");
	    stateCodeToNameMap.put("MT", "Montana");
	    stateCodeToNameMap.put("NC", "North Carolina");
	    stateCodeToNameMap.put("ND", "North Dakota");
	    stateCodeToNameMap.put("NE", "Nebraska");
	    stateCodeToNameMap.put("NH", "New Hampshire");
	    stateCodeToNameMap.put("NJ", "New Jersey");
	    stateCodeToNameMap.put("NM", "New Mexico");
	    stateCodeToNameMap.put("NV", "Nevada");
	    stateCodeToNameMap.put("NY", "New York");
	    stateCodeToNameMap.put("OH", "Ohio");
	    stateCodeToNameMap.put("OK", "Oklahoma");
	    stateCodeToNameMap.put("OR", "Oregon");
	    stateCodeToNameMap.put("PA", "Pennsylvania");
	    stateCodeToNameMap.put("RI", "Rhode Island");
	    stateCodeToNameMap.put("SC", "South Carolina");
	    stateCodeToNameMap.put("SD", "South Dakota");
	    stateCodeToNameMap.put("TN", "Tennessee");
	    stateCodeToNameMap.put("TX", "Texas");
	    stateCodeToNameMap.put("UT", "Utah");
	    stateCodeToNameMap.put("VA", "Virginia");
	    stateCodeToNameMap.put("VT", "Vermont");
	    stateCodeToNameMap.put("WA", "Washington");
	    stateCodeToNameMap.put("WI", "Wisconsin");
	    stateCodeToNameMap.put("WV", "West Virginia");
	    stateCodeToNameMap.put("WY", "Wyoming");
	    stateCodeToNameMap.put("GU", "Guam");
	    stateCodeToNameMap.put("VI", "Virgin Islands");
	    stateCodeToNameMap.put("PR", "Puerto Rico");
	    stateCodeToNameMap.put("AE", "Armed forces - Europe");
	    stateCodeToNameMap.put("AA", "Armed forces - America");
	    stateCodeToNameMap.put("AP", "Armed forces - Pacific");
	}
	public static ArrayList<String> getStateCodes(){
		return new ArrayList<String>(stateCodeToNameMap.keySet());
	}
	public static ArrayList<String> getStateNames(){
		return new ArrayList<String>(stateCodeToNameMap.values());
	}
}
