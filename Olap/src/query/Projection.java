/**
 * 
 */
package query;

import java.util.Iterator;

import schema.Attribute;

/**
 * The projection operator is used to restrict request on specific attributes.
 * It is composed of an Attribute List and a PathExpression
 */
public interface Projection extends PathExpression {
	
	/**
	 * @return the Attribute List to restrict on.
	 */
	Iterator<Attribute> getSelectList();
}
