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
 * Implements {@link FunctionReference}
 * @author Julien.C
 * @author Reda
 */
public class FunctionReferenceImpl implements FunctionReference {

	public static FunctionReference createFunctionReference(Function function) {
		return new FunctionReferenceImpl(function);
	}

	/**
	 * This is the {@link #Function} this class referred to Function object are
	 * immutable
	 */
	private Function referredFunction;

	/**
	 * Reference to range attribute
	 */
	private List<Attribute> rangeList;
	
	/**
	 * Reference to domain attribute
	 */
	private List<Attribute> domainList;
	
	public FunctionReferenceImpl(Function referredFunction) {
		super();
		//function is immutable, so we don't need to copy it
		this.referredFunction = referredFunction;
		
		//save lists now, because referredFunction is immutable
		//multiple access to these members will be more efficient
		rangeList = new ArrayList<Attribute>();
		rangeList.add(referredFunction.getRange());
		
		domainList = new ArrayList<Attribute>();
		domainList.add(referredFunction.getDomain());
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FunctionReferenceImpl other = (FunctionReferenceImpl) obj;
		if (domainList == null) {
			if (other.domainList != null)
				return false;
		} else if (!domainList.equals(other.domainList))
			return false;
		if (rangeList == null) {
			if (other.rangeList != null)
				return false;
		} else if (!rangeList.equals(other.rangeList))
			return false;
		if (referredFunction == null) {
			if (other.referredFunction != null)
				return false;
		} else if (!referredFunction.equals(other.referredFunction))
			return false;
		return true;
	}

	@Override
	public Iterator<Attribute> getDomain() {
		return this.domainList.iterator();
	}

	
	@Override
	public Iterator<Attribute> getRange() {
		return this.rangeList.iterator();
	}

	
	@Override
	public Function getRefferedFunction() {
		return this.referredFunction;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((domainList == null) ? 0 : domainList.hashCode());
		result = prime * result
				+ ((rangeList == null) ? 0 : rangeList.hashCode());
		result = prime
				* result
				+ ((referredFunction == null) ? 0 : referredFunction.hashCode());
		return result;
	}
}
