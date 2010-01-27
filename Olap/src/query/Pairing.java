/**
 * 
 */
package query;

/**
 * Pairing takes as input two functions f and g, such that def(f)= def(g), </br>
 * and returns a function f ^ g: def(f) -> range(f) x range(g),</br>
 * deﬁned by: (f ^ g)(x)= (f(x), g(x)) , for all x in def(f).
 * @author Julien.C
 */

public interface Pairing extends BinaryOperation {

}
