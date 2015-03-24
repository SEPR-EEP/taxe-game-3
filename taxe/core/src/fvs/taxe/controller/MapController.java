package fvs.taxe.controller;

import fvs.taxe.StationClickListener;
import fvs.taxe.actor.ConnectionActor;
import gameLogic.Player;
import gameLogic.map.Connection;
import gameLogic.map.IPositionable;
import gameLogic.map.Position;
import gameLogic.map.Station;
import gameLogic.resource.Resource;
import gameLogic.resource.Train;

import com.badlogic.gdx.graphics.Color;


/**
 * @author Stefan
 *Map controller class used to handle map modification - adding
 *and removing connection on the map 
 */
public class MapController {

/** The context of the game */
private Context context;

/**The origin station from which a new connection will be added or 
 * an existing connection will be deleted */
private Station origin;

/**The destination station to which a new connection will be added or 
 * an existing connection will be deleted */
private Station destination;

/**Indicates weather or not a connection is being edited */
private boolean isEditing;

/**The width of the connection to be added */
private final int CONNECTION_LINE_WIDTH = 5;


/**Instantiation method.
 * @param context - the context of the game.
 */
public MapController(Context context) {
    this.context = context;
    this.isEditing = true;
    this.origin = null;
    this.destination = null;

    StationController.subscribeStationClick(new StationClickListener() {
        @Override
        public void clicked(Station station) {
            if (isEditing) {
            	editConnection(station);
            }
        }
    });
}

/**Determines weather a connection should be added or removed and
 * calls the appropriate method for either adding or removing a connection.
 * This method is called when a station is clicked.
 * @param station - the clicked station.
 */
public void editConnection(Station station){
	if(origin == null){
		origin = station;
	}else{
		destination = station;
		if(context.getGameLogic().getMap().getConnection(origin.getName(), station.getName()) == null){
			addConnection();
		}else{
//			removeConnection();
		}
	}
}

/**
 * Adds a connection to the map and draws it on the GUI, if the connection
 * does not intersect an existing connection. If the new connection intersects
 * an existing one the function just returns.
 */
private void addConnection(){
	for(Connection connection : context.getGameLogic().getMap().getConnections()){
		if(intersect(connection.getStation1().getLocation(), 
					connection.getStation2().getLocation(),
					origin.getLocation(),
					destination.getLocation()) 
				&& connection.getStation1()!= destination 
				&& connection.getStation2() != origin){
			
//			System.out.println("Connections intersect:\n" +
//								"Connection 1: " + connection.getStation1().getName() + "(" + 
//								connection.getStation1().getLocation().getX() + ", " +
//								connection.getStation1().getLocation().getY() + ")" + " - " + 
//								connection.getStation2().getName() + "(" + 
//								connection.getStation2().getLocation().getX() + ", " + 
//								connection.getStation2().getLocation().getY() + ")" + "\n" +
//								
//								"Connection 2: " + origin.getName() + "(" + 
//								origin.getLocation().getX() + ", " +
//								origin.getLocation().getY() + ")" + " - " + 
//								destination.getName() + "(" + 
//								destination.getLocation().getX() + ", " +
//								destination.getLocation().getY() +")" + "\n");
			origin = null;
			destination = null;
			return;
		}
	}
	Connection connection = new Connection(origin, destination);
	
	final IPositionable start = connection.getStation1().getLocation();
	final IPositionable end = connection.getStation2().getLocation();
	ConnectionActor connectionActor = new ConnectionActor(Color.GRAY, start, end, CONNECTION_LINE_WIDTH);
	connection.setActor(connectionActor);
	
	context.getGameLogic().getMap().addConnection(connection.getStation1(), connection.getStation2());
	context.getGameLogic().getMap().getConnection(connection.getStation1().getName(), connection.getStation2().getName()).setActor(connectionActor);
	context.getStage().addActor(connectionActor);
	
	context.getStage().addActor(connection.getStation1().getActor());
	context.getStage().addActor(connection.getStation2().getActor());
	
	for(Player player : context.getGameLogic().getPlayerManager().getAllPlayers()){
		for(Resource train : player.getActiveTrains()){
			context.getStage().addActor(((Train)train).getActor());
		}
	}
	
	origin = null;
}


/**Determines weather two lines intersect given their coordinates.
 * @param a - first coordinate of Line 1
 * @param b - second coordinate of Line 1
 * @param c - first coordinate of Line 2
 * @param d - second coordinate of Line 2
 * @return - true if the lines intersect false otherwise
 */
private boolean intersect(IPositionable a, IPositionable b, IPositionable c, IPositionable d){
		return counterClockWise(a,c,d) != counterClockWise(b,c,d) && counterClockWise(a,b,c) != counterClockWise(a,b,d);
}

/**Determines weather or not three points are arranged counterclockwise on
 * a plane given their coordinates. This method is used by the intersect method.
 * @param a - coordinate of the first point
 * @param b - coordinate of the second point
 * @param c - coordinate of the third point
 * @return - true if the three point are arranged counterclockwise and false otherwise
 */
private boolean counterClockWise(IPositionable a, IPositionable b, IPositionable c){
	return (c.getY() - a.getY())*(b.getX() - a.getX()) > (b.getY()-a.getY())*(c.getX() - a.getX());
}


}
