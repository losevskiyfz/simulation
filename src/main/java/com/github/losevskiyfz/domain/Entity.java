package com.github.losevskiyfz.domain;

import com.github.losevskiyfz.path.Passable;

/**
 * <p>
 * Must have no-args constructor for all subclasses.
 * </p>
 *
 * @author Fyodor Losevksiy
 * @version 1.0
 */
public abstract class Entity implements Passable {
    public abstract String view();
}
