/**
 * 
 */
package query.implementation;

import java.util.ArrayList;
import java.util.List;

import query.PathExpression;
import schema.Attribute;
import schema.Function;

/**
 *	FunctionReference is a PathExpression that only referred to a SchemaFunction
 *	For example the simple path "g" is a FunctionReference.
 */
public class FunctionReference implements PathExpression{

	/**
	 * This is the {@link #Function} this class referred to
	 */
	private Function referredFunction;
	
	/**
	 * Construct FunctionReference using referredFunction as parameter
	 * @param referredFunction - The function to refer to
	 */
	public FunctionReference(Function referredFunction) {
		super();
		this.referredFunction = referredFunction;
	}

	Function getRefferedFunction(){
		return this.referredFunction;
	}
	

	@Override
	public List<Attribute> getDomain() {
		ArrayList<Attribute> returnList = new ArrayList<Attribute>();
		returnList.add(referredFunction.getDomain());
		return returnList;
	}

	@Override
	public List<Attribute> getRange() {
		ArrayList<Attribute> returnList = new ArrayList<Attribute>();
		returnList.add(referredFunction.getRange());
		return returnList;
	}
}
