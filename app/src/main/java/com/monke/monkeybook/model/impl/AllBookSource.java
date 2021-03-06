package com.monke.monkeybook.model.impl;

import com.monke.monkeybook.bean.BookSourceBean;
import com.monke.monkeybook.dao.DbHelper;
import com.monke.monkeybook.model.IStationBookModel;
import com.monke.monkeybook.model.content.FqxswModelImpl;
import com.monke.monkeybook.model.content.GxwztvBookModelImpl;
import com.monke.monkeybook.model.content.LingdiankanshuModelImpl;
import com.monke.monkeybook.model.content.XBQGModelImpl;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by GKF on 2017/12/15.
 * 所有书源
 */

public class AllBookSource {

    public static AllBookSource getInstance() {
        return new AllBookSource();
    }

    //所有书源TAG
    public static String[] getAllBookSourceTag() {
        return new String[]{
                XBQGModelImpl.TAG,
                LingdiankanshuModelImpl.TAG,
                GxwztvBookModelImpl.TAG
        };
    }

    public static List<BookSourceBean> getAllBookSource() {
        return DbHelper.getInstance().getmDaoSession().getBookSourceBeanDao().queryBuilder().list();
    }

    public static void saveBookSourceToDb() {
        List<BookSourceBean> bookSourceList = new ArrayList<>();
        bookSourceList.add(new BookSourceBean(XBQGModelImpl.TAG, XBQGModelImpl.name, 1, true));
        bookSourceList.add(new BookSourceBean(LingdiankanshuModelImpl.TAG, LingdiankanshuModelImpl.name, 2, true));
        bookSourceList.add(new BookSourceBean(GxwztvBookModelImpl.TAG, GxwztvBookModelImpl.name, 3, true));

        for (BookSourceBean bookSourceBean : bookSourceList) {
            DbHelper.getInstance().getmDaoSession().getBookSourceBeanDao().insertOrReplace(bookSourceBean);
        }
    }

    //获取book source class
    static IStationBookModel getBookSourceModel(String tag) {
        switch (tag) {
            case GxwztvBookModelImpl.TAG:
                return GxwztvBookModelImpl.getInstance();
            case LingdiankanshuModelImpl.TAG:
                return LingdiankanshuModelImpl.getInstance();
            case XBQGModelImpl.TAG:
                return XBQGModelImpl.getInstance();
            case FqxswModelImpl.TAG:
                return FqxswModelImpl.getInstance();
        }
        return null;
    }
}
