

/**
 * @author grant
 * @version 1.0
 * @created 15-Apr-2020 1:20:18 PM
 */
public abstract class GTFSID {

	private static ArrayList<GTFSID> existingIDs;
	private String id;



	public void finalize() throws Throwable {

	}

	/**
	 * 
	 * @param id
	 */
	public GTFSID GTFSID(String id){
		return null;
	}

	public GTFSID GTFSID(){
		return null;
	}

	/**
	 * 
	 * @param id
	 */
	public abstract static boolean exists(String id);

}