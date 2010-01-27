/**
 * 
 */
package query;

/**
 * Composition takes as input two functions, f and g, such that <br/>
 * range(f ) = def(g), and returns a function g o f: def(f) -> range(g), <br/>
 * deﬁned by: (g o f )(x)= g(f(x)) for all x in def(f ). 
 * @author Julien.C
 */

public interface Composition extends BinaryOperation {
	
}
