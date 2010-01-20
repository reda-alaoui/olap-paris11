/**
 * 
 */
package query.implementation;

import java.util.ArrayList;
import java.util.Iterator;

import query.UnaryOperation;
import schema.Attribute;

/**
 * @author Reda
 *
 */
public class Projection implements UnaryOperation {

	@Override
	public Iterator<Attribute> getDomain() {
		return null;
	}

	@Override
	public Iterator<Attribute> getRange() {
		return null;
	}
	
	ArrayList<Attribute> getSelectList(){
		return null;
	};

}
