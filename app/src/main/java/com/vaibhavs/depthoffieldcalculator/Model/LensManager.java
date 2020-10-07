package com.vaibhavs.depthoffieldcalculator.Model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Lens manager class used to stores the collection of lenses
 */
public class LensManager implements Iterable<Lens>{
    public List<Lens> lenses = new ArrayList<>();

    private static LensManager instance;
    private LensManager(){

    }
    public static LensManager getInstance(){
        if (instance == null){
            instance = new LensManager();
        }
        return instance;
    }
    public List<Lens> getlens() {
        return lenses;
    }
    public void add(Lens lens){
        lenses.add(lens);
    }
    public  void remove(Lens lens) { lenses.remove(lens); }
    @Override
    public Iterator<Lens> iterator() {
        return lenses.iterator();
    }

}
