/**
 * 
 */
package query;

import java.util.Iterator;

import schema.Attribute;

/**
 * The path expression represent an Olap path.
 * It is a tree, its leaf are FunctionReference and its root is a FunctionReference or an Operator
 */
public interface PathExpression {	
	
	/**
	 * @return the domain as Attributes iterator
	 */
	Iterator<Attribute> getDomain();
	
	/**
	 * 
	 * @return the range as Attributes iterator
	 */
	Iterator<Attribute> getRange();
}
