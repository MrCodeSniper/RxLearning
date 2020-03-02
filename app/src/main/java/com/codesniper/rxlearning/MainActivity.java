package com.codesniper.rxlearning;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiConsumer;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        Observable equal Flowable 背压版  Observer  equal Subscriber  观察者


//        createRelation();
            test();
    }


    private void createRelation() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                e.onNext(1);
                e.onNext(2);
                e.onNext(3);
                e.onNext(4);
                e.onComplete();

            }
        }).subscribe(new Observer<Integer>() {

            private Disposable mDisposable; //可中断当前发射 并且不会调用完结语句

            @Override
            public void onSubscribe(Disposable d) {
                this.mDisposable = d;
            }

            @Override
            public void onNext(Integer integer) {
                Log.d(TAG, "接收到数据" + integer);
                if (integer == 5) {
                    mDisposable.dispose();
                }
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "onError : value : " + e.getMessage() + "\n");
            }

            @Override
            public void onComplete() {
                Log.e(TAG, "onComplete" + "\n");
            }
        });
    }

    private void test() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                e.onNext(1);
                e.onNext(2);
                e.onNext(3);
                e.onNext(4);
                e.onComplete();

            }
        }).subscribe(new Consumer<Integer>() { //消费者，用于接收onNext的单个值
            @Override
            public void accept(Integer integer) throws Exception {
                Log.d(TAG, "accept" + integer+"\n");
            }
        });
    }

    /**
     * Function用于变换 Predicate用于过滤
     */
    private void test1() {
    }


    /**
     * 1.subscribeOn  用于指定 subscribe() 时所发生的线程 发射事件的线程
     * 2.observeOn  用于指定下游 Observer 回调发生的线程 订阅者接收事件的线程
     */
    private void thread(){

    }

    /**
     * 操作符
     */
    private void operation(){

    }
}
