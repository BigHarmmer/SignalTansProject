package com.meitu.signaltransmissionproject.common;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lyd
 */
public class SignalStorage {

    //bpkList spkList bollList dtList
    private List<List<String>> mTotalList;

    public SignalStorage() {
        mTotalList = new ArrayList<>(4);
        mTotalList.add(stringToList(SharedPreManager.getBPK()));
        mTotalList.add(stringToList(SharedPreManager.getSPK()));
        mTotalList.add(stringToList(SharedPreManager.getBOLL()));
        mTotalList.add(stringToList(SharedPreManager.getDT()));
    }

    public List<String> stringToList(String str) {
        if (TextUtils.isEmpty(str)) {
            return new ArrayList<>(20);
        }
        Gson gson = new Gson();
        return gson.fromJson(str, new TypeToken<List<String>>() {
        }.getType());
    }

    public String listToString(List<String> list) {
        Gson gson = new Gson();
        return gson.toJson(list);
    }

    /**
     * 获取总列表
     *
     * @return 将内容分割成time 和 content的列表
     */
    public List<List<String[]>> getTotalList() {
        List<List<String[]>> newParentList = new ArrayList<>(4);
        for (List<String> list : mTotalList) {
            List<String[]> newChildList = new ArrayList<>();
            for (String str : list) {
                String strs[] = subSignalString(str);
                if (strs != null) {
                    newChildList.add(strs);
                }
            }
            newParentList.add(newChildList);
        }
        return newParentList;
    }


    public void save() {
        SharedPreManager.setBPK(listToString(mTotalList.get(0)));
        SharedPreManager.setSPK(listToString(mTotalList.get(1)));
        SharedPreManager.setBOLL(listToString(mTotalList.get(2)));
        SharedPreManager.setDT(listToString(mTotalList.get(3)));
    }

    public void update(String[] update) {
        addInList(update[0], mTotalList.get(0));
        addInList(update[1], mTotalList.get(1));
        addInList(update[2], mTotalList.get(2));
        addInList(update[3], mTotalList.get(3));
    }

    private void addInList(String update, List<String> list) {
        if (!list.isEmpty() && list.get(0).equals(update)) {
            return;
        }
        if (list.size() < 20) {
            list.add(0, update);
        } else {
            list.remove(19);
            list.add(0, update);
        }
    }

    /**
     * 将信号分割成两个部分
     */
    private String[] subSignalString(String str) {
        String[] a = str.split("-");
        if (a.length < 3) {
            return null;
        }
        return new String[]{a[0] + "-" + a[1], a[2]};
    }
}
