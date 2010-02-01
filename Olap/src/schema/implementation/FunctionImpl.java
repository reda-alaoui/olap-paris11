/**
 * 
 */
package schema.implementation;

import schema.Attribute;
import schema.Function;

/**
 * Implements {@link Function}
 * @author Reda
 */
public class FunctionImpl implements Function {
	private final String name;
	private final Attribute domain;
	private final Attribute range;
	/**
	 * 
	 * @param name Function's name
	 * @param domain Function's domain
	 * @param range Function's range
	 * @see Attribute
	 */
	public FunctionImpl(String name, Attribute domain, Attribute range) {
		this.name = name;
		this.domain = domain;
		this.range = range;
	}
	
	@Override
	public String getName() {
		return name;
	}

	@Override
	public Attribute getDomain() {
		return domain;
	}

	@Override
	public Attribute getRange() {
		return range;
	}
	
	@Override
	public int hashCode() {
		return 31 * (31 * (31 + name.hashCode()) + domain.hashCode()) + range.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
	    if ( this == obj ) return true;

	    if ( !(obj instanceof Function) ) return false;

	    Function func = (Function)obj;

	    return func.getDomain().equals(domain) && func.getName().equals(name) && func.getRange().equals(range);
	}
}
