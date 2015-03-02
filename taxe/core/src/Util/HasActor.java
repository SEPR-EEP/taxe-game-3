package Util;

import fvs.taxe.actor.GenericActor;

import java.math.BigInteger;
import java.security.SecureRandom;


/**
 * Created by alfioemanuele on 3/2/15.
 */
public class HasActor<Type> {
    String myUniqueID;

    private void generateRandomUniqueID() {
        do {
            SecureRandom random = new SecureRandom();
            myUniqueID = new BigInteger(130, random).toString(32);
        } while ( ActorsManager.containsKey(myUniqueID) );
    }

    private void ensureRandomUniqueID() {
        if ( myUniqueID == null ) {
            generateRandomUniqueID();
        }
    }

    public Type getActor() {
        ensureRandomUniqueID();
        return (Type) ActorsManager.get(myUniqueID);
    }

    public void setActor(Type a) {
        ensureRandomUniqueID();
        ActorsManager.put(myUniqueID, (GenericActor) a);
    }

}