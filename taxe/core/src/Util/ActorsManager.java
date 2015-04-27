package Util;

import fvs.taxe.actor.GenericActor;
import fvs.taxe.actor.TrainActor;
import sun.net.www.content.text.Generic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * This class can be used statically as an HashMap
 * <String, GenericActor> with the following methods:
 * - GenericActor Util.ActorsManager.get(String)
 * - Util.ActorsManager.put(String, GenericActor)
 *
 * Created by alfioemanuele on 3/2/15.
 */
public class ActorsManager {

    private static HashMap<String, GenericActor> actors = new HashMap<String, GenericActor>();
    private static List<GenericActor> trainActors = new ArrayList<GenericActor>();

    public static GenericActor get(String s) {
        return ActorsManager.actors.get(s);
    }

    public static void put(String s, GenericActor g) {
        ActorsManager.actors.put(s, g);
    }
    public static boolean containsKey(String s) { return ActorsManager.actors.containsKey(s); }

    public static void addTrainActor(GenericActor a) {
        ActorsManager.trainActors.add(a);
    }

    public static List<GenericActor> getTrainActors() {
        return ActorsManager.trainActors;
    }

    public static void interruptAllTrains() {
        for ( GenericActor i: trainActors ) {
            TrainActor x = (TrainActor) i;
            x.setPosition(-1, -1);
            x.setVisible(false);
            x.clearActions();
        }
    }

}
