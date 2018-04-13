package com.meitu.signaltransmissionproject.common;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

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
        List<List<String[]>> parentList = new ArrayList<>(5);
        List<String[]> firstChildList = new ArrayList<>(4);
        for (List<String> list : mTotalList) {
            List<String[]> newChildList = new ArrayList<>();
            for (int i = 0; i < list.size(); i++) {
                String str = list.get(i);
                String strs[] = subSignalString(str);
                if (strs != null) {
                    newChildList.add(strs);
                    if (i == 0) {
                        firstChildList.add(strs);
                    }
                }
            }
            parentList.add(newChildList);
        }
        parentList.add(0, firstChildList);
        return parentList;
    }


    public void save() {
        SharedPreManager.setBPK(listToString(mTotalList.get(0)));
        SharedPreManager.setSPK(listToString(mTotalList.get(1)));
        SharedPreManager.setBOLL(listToString(mTotalList.get(2)));
        SharedPreManager.setDT(listToString(mTotalList.get(3)));
    }

    public boolean update(String[] update) {
        boolean check1 = addInList(update[0], mTotalList.get(0));
        boolean check2 = addInList(update[1], mTotalList.get(1));
        boolean check3 = addInList(update[2], mTotalList.get(2));
        boolean check4 = addInList(update[3], mTotalList.get(3));
        return check1 || check2 || check3 || check4;
    }

    private boolean addInList(String update, List<String> list) {
        if (!list.isEmpty() && list.get(0).equals(update)) {
            return false;
        }
        if (list.size() < 20) {
            list.add(0, update);
        } else {
            list.remove(19);
            list.add(0, update);
        }
        return true;
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
