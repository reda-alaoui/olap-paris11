/**
 * 
 */
package query;

import java.util.List;

import schema.Attribute;

/**
 * @author Reda
 *
 */
public interface Projection extends UnaryOperation {
	List<Attribute> getSelectList();
}
