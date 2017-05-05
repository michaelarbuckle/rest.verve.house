package house.verve.model.io;

import org.springframework.data.mongodb.core.geo.GeoJsonPolygon;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;

public class GeoPolygonJsonDeserializer extends JsonDeserializer<GeoJsonPolygon> {

    private final static String GEOJSON_TYPE_POLYGON = "Polygon";

    private final static String JSON_KEY_GEOJSON_TYPE = "type";
    private final static String JSON_KEY_GEOJSON_COORDS = "coordinates";


  


	@Override
	public GeoJsonPolygon deserialize(JsonParser jp, DeserializationContext dc)
			throws IOException, JsonProcessingException {
	     final JsonNode tree = jp.getCodec().readTree(jp);
	        final String type = tree.get(JSON_KEY_GEOJSON_TYPE).asText();
	        final JsonNode coordsNode = tree.get(JSON_KEY_GEOJSON_COORDS);

	       List<Point> coordinates = new ArrayList<Point>(); 
	        
	        if(GEOJSON_TYPE_POLYGON.equalsIgnoreCase(type)) { 
	        	for (JsonNode ring : coordsNode) {

					// currently we do not support holes in polygons.
	        		coordinates.addAll(toPoints((ArrayNode) ring));
				}
 	        }

	        else {
	            System.out.println(String.format("No logic present to deserialize %s ", tree.asText()));
	        }
	        
	        GeoJsonPolygon polygon = new GeoJsonPolygon(coordinates);
			
 

	        return polygon;
		 
	}
	protected List<Point> toPoints(ArrayNode node) {

		if (node == null) {
			return Collections.emptyList();
		}

		List<Point> points = new ArrayList<Point>(node.size());

		for (JsonNode coordinatePair : node) {
			if (coordinatePair.isArray()) {
				points.add(toPoint((ArrayNode) coordinatePair));
			}
		}
		return points;
	}
	protected Point toPoint(ArrayNode node) {

		if (node == null) {
			return null;
		}

		return new Point(node.get(0).asDouble(), node.get(1).asDouble());
	}
}
