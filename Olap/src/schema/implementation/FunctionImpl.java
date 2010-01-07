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
	
	public String getName() {
		return name;
	}

	public Attribute getDomain() {
		return domain;
	}

	public Attribute getRange() {
		return range;
	}
	
	
}
