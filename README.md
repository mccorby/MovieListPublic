Completable -> suspend () -> Unit
Single<T> -> suspend () -> T
Maybe<T> -> suspend () -> T?
Observable<T>/Flowable<T> -> Flow<T>
Subject<T> -> Channel<T>