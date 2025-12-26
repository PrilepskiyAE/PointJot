package com.prilepskiy.common

interface Reducer<A : MviAction, S : MviState> {
    fun reduce(action: A, state: S): S
}