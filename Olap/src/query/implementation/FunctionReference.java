/**
 * 
 */
package query.implementation;

import java.util.ArrayList;
import java.util.Iterator;

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

	/**
	 * @return the referredFunction this object referred to
	 */
	Function getRefferedFunction(){
		return this.referredFunction;
	}
	
	/**
	 * Be carefull, referredFunction can be modify by other object and its domain can change.
	 * @return the attribute from referredFunction's domain.
	 * @see Function#getDomain();
	 */
	@Override
	public Iterator<Attribute> getDomain() {
		ArrayList<Attribute> returnList = new ArrayList<Attribute>();
		returnList.add(referredFunction.getDomain());
		
		return returnList.iterator();
	}

	/**
	 * Be carefull, referredFunction can be modify by other object and its domain can change.
	 * @return the attribute from referredFunction's domain.
	 * @see Function#getRange();
	 */
	@Override
	public Iterator<Attribute> getRange() {
		ArrayList<Attribute> returnList = new ArrayList<Attribute>();
		returnList.add(referredFunction.getRange());
		
		return returnList.iterator();
	}
}
