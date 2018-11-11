package com.teamZero.app.core.populator;

/**
 *
 @param <S> Source(Usually a model object)
 @param <T> Target(Usually a Dto object)
 */
public interface Populator<S, T> {
    T populate(S s);
}

