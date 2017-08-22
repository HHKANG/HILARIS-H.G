package com.example.samsung.hilaris;

import android.view.View;

/**
 *NLevelListItem class is implemented NLevelItem.
 */

public interface NLevelListItem {

    public boolean isExpanded();
    public void toggle();
    public NLevelListItem getParent();
    public View getView();
    public boolean isRoutine();
}