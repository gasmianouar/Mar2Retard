package com.marre2retard.mar2retard.tools;

import android.text.Editable;
import android.text.TextWatcher;

/**
 * Created by gasmi on 31/12/2017.
 */

public interface TextChangedListener extends TextWatcher {
    @Override
    void beforeTextChanged(CharSequence s, int start, int count, int after);

    @Override
    void afterTextChanged(Editable s);

    @Override
    void onTextChanged(CharSequence s, int start, int before, int count);
}

