package gameLogic.map;

import Util.HasActor;
import fvs.taxe.actor.ConnectionActor;

/**A connection describes the link between 2 stations.*/
public class Connection extends HasActor<ConnectionActor> {
	/**The first station of the connection.*/
	private Station station1;
	
	/**The second station of the connection.*/
	private Station station2;

	/**Instantiation method.
	 * @param station1 The first station for the connection.
	 * @param station2 The second station for the connection.
	 */
	public Connection(Station station1, Station station2) {
		this.station1 = station1;
		this.station2 = station2;
	}
	
	/**@return The first station used in this connection.*/
	public Station getStation1() {
		return this.station1;
	}

	/**@return The second station used in this connection.*/
	public Station getStation2() {
		return this.station2;
	}

}
