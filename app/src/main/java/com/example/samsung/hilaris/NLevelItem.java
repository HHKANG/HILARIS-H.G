package com.example.samsung.hilaris;


import android.view.View;

public class NLevelItem implements NLevelListItem
{

    private Object wrappedObject;
    private Object wrappedObject2;
    private NLevelItem parent;
    private NLevelView nLevelView;
    private boolean isExpanded = false;
    private boolean isRoutine = false;

    public NLevelItem(Object wrappedObject, NLevelItem parent, NLevelView nLevelView) {
        this.wrappedObject = wrappedObject;
        this.parent = parent;
        this.nLevelView = nLevelView;
    }

    public NLevelItem(Object wrappedObject, NLevelItem parent, NLevelView nLevelView, Boolean isRoutine) {
        this.wrappedObject = wrappedObject;
        this.parent = parent;
        this.nLevelView = nLevelView;
        this.isRoutine =isRoutine;
    }


    public NLevelItem(Object wrappedObject,Object wrappedObject2, NLevelItem parent, NLevelView nLevelView) {
        this.wrappedObject = wrappedObject;
        this.wrappedObject2 = wrappedObject2;
        this.parent = parent;
        this.nLevelView = nLevelView;
    }

    public Object getWrappedObject() {
        return wrappedObject;
    }




    @Override
    public boolean isExpanded() {
        return isExpanded;
    }

    @Override
    public boolean isRoutine() {
        return isRoutine;
    }

    @Override
    public NLevelListItem getParent() {
        return parent;
    }
    @Override
    public View getView() {
        return nLevelView.getView(this);
    }
    @Override
    public void toggle() {
        isExpanded = !isExpanded;
    }

}
