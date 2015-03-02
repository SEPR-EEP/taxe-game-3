package Util;

import fvs.taxe.actor.GenericActor;

import java.util.HashMap;

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

    public static GenericActor get(String s) {
        return ActorsManager.actors.get(s);
    }

    public static void put(String s, GenericActor g) {
        ActorsManager.actors.put(s, g);
    }

    public static boolean containsKey(String s) { return ActorsManager.actors.containsKey(s); }

}
