package com.task.alalmiyatask.base

class MVPBaseViewImpl<T> : MVPBaseApiView<T> {
    override fun onResponseSuccess(data: T) {

    }

    override fun onResponseError(errorBody: String) {

    }

    override fun onResponseFailure(t: Throwable) {

    }
}