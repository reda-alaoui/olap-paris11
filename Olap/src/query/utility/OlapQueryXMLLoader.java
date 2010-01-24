/**
 * 
 */
package query.utility;

import java.io.File;

import query.OlapQuery;

/**
 * @author Reda
 *
 */
public class OlapQueryXMLLoader {
	
	private final OlapQuery query;
	
	public OlapQueryXMLLoader(File xml_file){
		query = null;
	}
	
	public OlapQuery getQuery(){
		return query;
	}
}
