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
    private List<String> BPK_List;
    private List<String> SPK_List;
    private List<String> BOLL_List;
    private List<String> DT_List;

    public SignalStorage() {
        BPK_List = stringToList(SharedPreManager.getBPK());
        SPK_List = stringToList(SharedPreManager.getSPK());
        BOLL_List = stringToList(SharedPreManager.getBOLL());
        DT_List = stringToList(SharedPreManager.getDT());
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

    public List<String> getBPK_List() {
        return BPK_List;
    }

    public List<String> getSPK_List() {
        return SPK_List;
    }

    public List<String> getBOLL_List() {
        return BOLL_List;
    }

    public List<String> getDT_List() {
        return DT_List;
    }

    public void save() {
        SharedPreManager.setBPK(listToString(BPK_List));
        SharedPreManager.setSPK(listToString(SPK_List));
        SharedPreManager.setBOLL(listToString(BOLL_List));
        SharedPreManager.setDT(listToString(DT_List));
    }

    public void update(String[] update) {
        addInList(update[0], BPK_List);
        addInList(update[1], SPK_List);
        addInList(update[2], BOLL_List);
        addInList(update[3], DT_List);
    }

    private void addInList(String update, List<String> list) {
        if (list.size() < 20) {
            list.add(0, update);
        } else {
            list.remove(19);
            list.add(0, update);
        }
    }
}
