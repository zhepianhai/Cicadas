package com.zph.cicadas.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;

import com.hannesdorfmann.mosby3.mvp.MvpPresenter;
import com.hannesdorfmann.mosby3.mvp.MvpView;
import com.hannesdorfmann.mosby3.mvp.delegate.ActivityMvpDelegate;
import com.hannesdorfmann.mosby3.mvp.delegate.ActivityMvpDelegateImpl;

/**
 * An Activity that uses a {@link MvpPresenter} to implement a Model-View-Presenter
 * architecture.
 *
 * @author zph
 * @date 2018/3/20
 * @since 1.0.0
 */

public abstract class MvpActivity <V extends MvpView, P extends MvpPresenter<V>>
        extends BaseAppCompatActivity implements MvpView,
        com.hannesdorfmann.mosby3.mvp.delegate.MvpDelegateCallback<V, P> {

    protected ActivityMvpDelegate mvpDelegate;
    protected P presenter;
    protected boolean retainInstance;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("TAG","CCC");
        getMvpDelegate().onCreate(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        getMvpDelegate().onDestroy();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        getMvpDelegate().onSaveInstanceState(outState);
    }

    @Override
    protected void onPause() {
        super.onPause();
        getMvpDelegate().onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        getMvpDelegate().onResume();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("TAG","onStart");
        getMvpDelegate().onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        getMvpDelegate().onStop();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        getMvpDelegate().onRestart();
    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();
        getMvpDelegate().onContentChanged();
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        Log.i("TAG","onPostCreate");
        getMvpDelegate().onPostCreate(savedInstanceState);
    }

    /**
     * Instantiate a presenter instance
     *
     * @return The {@link MvpPresenter} for this view
     */
    @NonNull
    @Override
    public abstract P createPresenter();

    /**
     * Get the mvp delegate. This is internally used for creating presenter, attaching and detaching
     * view from presenter.
     * <p>
     * <p><b>Please note that only one instance of mvp delegate should be used per Activity
     * instance</b>.
     * </p>
     * <p>
     * <p>
     * Only override this method if you really know what you are doing.
     * </p>
     *
     * @return {@link ActivityMvpDelegateImpl}
     */
    @NonNull
    protected ActivityMvpDelegate<V, P> getMvpDelegate() {
        if (mvpDelegate == null) {
            mvpDelegate = new ActivityMvpDelegateImpl(this, this, true);
        }
        Log.i("TAG","getMvpDelegate");
        return mvpDelegate;
    }

    @NonNull
    @Override
    public P getPresenter() {
        return presenter;
    }

    @Override
    public void setPresenter(@NonNull P presenter) {
        this.presenter = presenter;
    }

    @NonNull
    @Override
    public V getMvpView() {
        return (V) this;
    }
}


