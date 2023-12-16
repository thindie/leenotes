package com.example.thindie.leenotes.domain

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

fun <T, R, I: Iterable<T>, H : Iterable<R>> Flow<I>.mapCollection( transform:suspend (T) -> R): Flow<H> {
    return map {
         it.map { transform.invoke(it) } as H
    }
}