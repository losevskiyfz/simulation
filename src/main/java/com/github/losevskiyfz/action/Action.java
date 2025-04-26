package com.github.losevskiyfz.action;

import com.github.losevskiyfz.map.Map;

public interface Action<T> {
    void act(Map<T> map);
}
