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
 *
 */
public interface QueryFactory {

	/**
	 * 
	 * These aggregate function are available aggregate used as by {@link#OLAPQuery}
	 */
	static enum AggregationFunction {COUNT, SUM, MIN, MAX, AVG};
	
	FunctionReference function(String name);
	  
	Composition composition(PathExpression p1, PathExpression p2);
	
	Pairing pairing(PathExpression p1, PathExpression p2);
	
	Projection projection(PathExpression p, List<Attribute> selectList);
	
	OlapQuery olapQuery(	PathExpression c,
	                      	PathExpression m,
	                      	AggregationFunction op);
}
