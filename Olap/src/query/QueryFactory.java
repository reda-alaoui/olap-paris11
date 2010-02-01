package query;

import java.util.List;

import query.implementation.FunctionReferenceImpl;
import query.implementation.PairingImpl;
import query.implementation.ProjectionImpl;
import query.utility.OlapQueryValidationException;
import query.utility.PathExpressionValidationException;
import schema.Attribute;
import schema.Function;

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
 * @author Julien.C
 */
public interface QueryFactory {

	/**
	 * 
	 * These aggregate function are available aggregate used as by {@link OlapQuery}
	 */
	public static enum AggregationFunction {COUNT, SUM, MIN, MAX, AVG};
	
	/**
	 * Create a {@link FunctionReferenceImpl} by passing its name
	 * @param name - the name of the function
	 * @return a new function reference
	 * @see Function
	 */
	FunctionReference function(String name) throws PathExpressionValidationException;;
	 
	/**
	 * Create a {@link Composition} by passing its two params ({@link PathExpression})
	 * @param p1 - expression1
	 * @param p2 - expression2
	 * 
	 * @return a new composition
	 * @throws Exception 
	 */
	Composition composition(PathExpression p1, PathExpression p2) throws PathExpressionValidationException;
	
	/**
	 * Create a {@link PairingImpl} by passing its two params ({@link PathExpression})
	 * @param p1 - expression1
	 * @param p2 - expression2
	 * 
	 * @return a new pairing
	 */
	Pairing pairing(PathExpression p1, PathExpression p2) throws PathExpressionValidationException;;
	
	/**
	 * Create a {@link ProjectionImpl} object by passing Path Expression and a list of attribute to restrict on
	 * @param domain - the domain of the projection
	 * @param selectList - the range to restrict on
	 * @return a new projection
	 */
	Projection projection(List<Attribute> domain, List<Attribute> selectList) throws PathExpressionValidationException;;
	
	/**
	 * Build the OlapQuery by passing its classifier, measure and aggregate operator.
	 * Theses params can be provided by OlapQuery itself.
	 * @param c - the classifier	
	 * @param m - the measure
	 * @param op - the aggregate operator
	 * @return a new Olap query
	 * @see OlapQuery
	 */
	OlapQuery olapQuery(	PathExpression c,
	                      	PathExpression m,
	                      	AggregationFunction op) throws OlapQueryValidationException;

}
