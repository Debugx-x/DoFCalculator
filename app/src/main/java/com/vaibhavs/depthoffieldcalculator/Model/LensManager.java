package com.vaibhavs.depthoffieldcalculator.Model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Lens manager class used to stores the collection of lenses
 */
public class LensManager implements Iterable<Lens>{
    public List<Lens> lenses = new ArrayList<>();

    public void add(Lens lens){
        lenses.add(lens);
    }
    @Override
    public Iterator<Lens> iterator() {
        return lenses.iterator();
    }
}
