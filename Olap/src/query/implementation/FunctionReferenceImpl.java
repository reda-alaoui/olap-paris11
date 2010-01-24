/**
 * 
 */
package query.implementation;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import query.FunctionReference;
import schema.Attribute;
import schema.Function;

/**
 * FunctionReference is a PathExpression that only referred to a SchemaFunction
 * For example the simple path "g" is a FunctionReference. Object of this class
 * are immutable
 */
public class FunctionReferenceImpl implements FunctionReference {

	/**
	 * This is the {@link #Function} this class referred to Function object are
	 * immutable
	 */
	private Function referredFunction;
	
	/**
	 * Reference to range attribute
	 */
	private List<Attribute> range;
	
	/**
	 * Reference to domain attribute
	 */
	private List<Attribute> domain;

	public FunctionReferenceImpl(Function referredFunction) {
		super();
		//function is immutable, so we don't need to copy it
		this.referredFunction = referredFunction;
		
		//save lists now, because referredFunction is immutable
		//multiple acces to these members will be more efficient
		List<Attribute> rangeList = new ArrayList<Attribute>();
		rangeList.add(referredFunction.getRange());
		range = rangeList;
		
		List<Attribute> domainList = new ArrayList<Attribute>();
		domainList.add(referredFunction.getDomain());
		domain = domainList;
	}

	@Override
	public Function getRefferedFunction() {
		return this.referredFunction;
	}

	
	@Override
	public Iterator<Attribute> getDomain() {
		return this.domain.iterator();
	}

	
	@Override
	public Iterator<Attribute> getRange() {
		return this.range.iterator();
	}
}
