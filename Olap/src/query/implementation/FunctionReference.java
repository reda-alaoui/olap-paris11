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
 * FunctionReference is a PathExpression that only referred to a SchemaFunction
 * For example the simple path "g" is a FunctionReference. Object of this class
 * are immutable
 */
public class FunctionReference implements PathExpression {

	/**
	 * This is the {@link #Function} this class referred to Function object are
	 * immutable
	 */
	private Function referredFunction;
	
	/**
	 * Reference to range attribute iterator
	 */
	private Iterator<Attribute> rangeIterator;
	
	/**
	 * Reference to domain attribute iterator
	 */
	private Iterator<Attribute> domainIterator;

	/**
	 * Construct FunctionReference using referredFunction as parameter
	 * 
	 * @param referredFunction
	 *            - The function to refer to (immutable)
	 */
	public FunctionReference(Function referredFunction) {
		super();
		//function is immutable, so we don't need to copy it
		this.referredFunction = referredFunction;
		
		//save iterators now, because referredFunction is immutable
		//multiple acces to these members will be more efficient
		ArrayList<Attribute> rangeList = new ArrayList<Attribute>();
		rangeList.add(referredFunction.getRange());
		rangeIterator = rangeList.iterator();
		
		ArrayList<Attribute> domainList = new ArrayList<Attribute>();
		domainList.add(referredFunction.getDomain());
		domainIterator = domainList.iterator();
	}

	/**
	 * @return the referredFunction this object referred to
	 */
	Function getRefferedFunction() {
		return this.referredFunction;
	}

	/**
	 * @return the attribute from referredFunction's domain.
	 * @see Function#getDomain();
	 */
	@Override
	public Iterator<Attribute> getDomain() {
		return rangeIterator;
	}

	/**
	 * @return the attribute from referredFunction's domain.
	 * @see Function#getRange();
	 */
	@Override
	public Iterator<Attribute> getRange() {
		return domainIterator;
	}
}
