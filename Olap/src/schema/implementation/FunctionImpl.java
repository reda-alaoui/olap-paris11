/**
 * 
 */
package schema.implementation;

import schema.Attribute;
import schema.Function;

/**
 * @author Reda
 *
 */
public class FunctionImpl implements Function {
	private final String name;
	private final Attribute domain;
	private final Attribute range;

	public FunctionImpl(String name, Attribute domain, Attribute range) {
		this.name = name;
		this.domain = domain;
		this.range = range;
	}
	
	public String getName() {
		return name;
	}

	public Attribute getDomain() {
		return domain;
	}

	public Attribute getRange() {
		return range;
	}
	
	public int hashCode() {
		return 31 * (31 * (31 + name.hashCode()) + domain.hashCode()) + range.hashCode();
	}

	public boolean equals(Object obj) {
	    if ( this == obj ) return true;

	    if ( !(obj instanceof Function) ) return false;

	    Function func = (Function)obj;

	    return func.getDomain().equals(domain) && func.getName().equals(name) && func.getRange().equals(range);
	}
}
