/**
 * 
 */
package query;

import java.util.Iterator;

import schema.Attribute;

/**
 * @author Reda
 *
 */
public interface Projection extends UnaryOperation {
	Iterator<Attribute> getSelectList();
}
