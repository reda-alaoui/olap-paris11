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
	private String name;
	private Attribute domain;
	private Attribute range;

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
	public boolean equals(Object obj) {
	    if ( this == obj ) return true;

	    if ( !(obj instanceof Function) ) return false;

	    Function func = (Function)obj;

	    return func.getDomain().equals(domain) && func.getName().equals(name) && func.getRange().equals(range);
	}
	
	
}
