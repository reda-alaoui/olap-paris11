/**
 * 
 */
package query.utility;

import query.OlapQuery;

/**
 * @author Reda
 *
 */
public class PathExpressionValidator {
	private final OlapQuery query;
	private final boolean validation;
	
	public PathExpressionValidator(OlapQuery query){
		this.query = query;
		this.validation = validate();
	}
	
	public boolean getValidation(){
		return validation;
	}
	
	private boolean validate(){
		return true;
	}
}
