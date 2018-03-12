package com.meitu.signaltransmissionproject.base;

/**
 * @author lyd
 *         抽象Presenter类，继承该类实现中心管理，连接Model业务逻辑 以及UI显示
 */
public abstract class AbsPresenter<T extends IMvpView> {

    protected AbsPresenter(T t) {
        mMvpView = t;
    }

    protected T mMvpView;

    public void attachView(T mvpView) {
        mMvpView = mvpView;
    }

    public void detachView() {
        mMvpView = null;
    }

    public boolean isViewAttatch() {
        return mMvpView != null;
    }
}
