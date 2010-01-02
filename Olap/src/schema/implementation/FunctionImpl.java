/**
 * 
 */
package schema.implementation;

import schema.Function;

/**
 * @author Reda
 *
 */
public class FunctionImpl implements Function {
	private String name;

	public FunctionImpl(){
		
	}
	
	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}
	
	
}
