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

	public FunctionImpl(){
		
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
	
	
}
