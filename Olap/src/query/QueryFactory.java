package query;

import java.util.List;

import query.implementation.Composition;
import query.implementation.FunctionReference;
import query.implementation.Pairing;
import query.implementation.Projection;
import schema.Attribute;

/**
 * QueryFactory is abbe to create an OLAPQuery by provide elementary parameter :
 * - 1 path as classifier
 * - 1 path as measure
 * - 1 aggregate function as aggregate {@link AggregationFunction}
 * successively apply olap operations.
 * <blockquote><pre> 
 * usage :
 * QueryFactory q = new QueryFactoryImpl(schema);
 *
 *	OLAPQuery query =
 *	 q.olapQuery(
 *	   q.pairing(
 *	     q.composition(
 *	       q.function("f1"),
 *	       q.function("f")),
 *	     q.function("g")),
 *	   q.function("q"),
 *	   q.SUM);
 * </pre></blockquote>
 */
public interface QueryFactory {

	/**
	 * 
	 * These aggregate function are available aggregate used as by {@link#OLAPQuery}
	 */
	static enum AggregationFunction {COUNT, SUM, MIN, MAX, AVG};
	
	/**
	 * Create a {@link FunctionReference} by passing its name
	 * @param name - the name of the function
	 * @see function of schema
	 * 
	 * @return
	 */
	FunctionReference function(String name);
	 
	/**
	 * Create a {@link Composition} by passing its two params ({@link PathExpression})
	 * @param p1 - expression1
	 * @param p2 - expression2
	 * 
	 * @return
	 */
	Composition composition(PathExpression p1, PathExpression p2);
	
	/**
	 * Create a {@link Pairing} by passing its two params ({@link PathExpression})
	 * @param p1 - expression1
	 * @param p2 - expression2
	 * 
	 * @return
	 */
	Pairing pairing(PathExpression p1, PathExpression p2);
	
	/**
	 * Create a {@link Projection} object by passing Path Expression and a list of attribute to restrict on
	 * @param p - the PathExpression
	 * @param selectList - the list of attributes to restrict on 
	 * @return
	 */
	Projection projection(PathExpression p, List<Attribute> selectList);
	
	/**
	 * Build the OlapQuery by passing its classifier, measure and aggregate operator.
	 * Theses params can be provide by OlapQuery itself.
	 * @see {@link OlapQuery}
	 * @param c - the classifier	
	 * @param m - the measure
	 * @param op - the aggregate operator
	 * @return
	 */
	OlapQuery olapQuery(	PathExpression c,
	                      	PathExpression m,
	                      	AggregationFunction op);
}
