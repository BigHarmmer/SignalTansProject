package com.meitu.signaltransmissionproject.message;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.meitu.signaltransmissionproject.R;

import java.util.List;

/**
 * @author lyd
 */
public class SignalExpandableAdapter extends BaseExpandableListAdapter {

    private String[] mGroupTitle;
    private List<List<String[]>> mList;
    private Context mContext;

    public SignalExpandableAdapter(String[] groupTitle, Context context, List<List<String[]>> lists) {
        mContext = context;
        mList = lists;
        mGroupTitle = groupTitle;
    }

    @Override
    public int getGroupCount() {
        return mList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return mList.get(groupPosition).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return mList.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return mList.get(groupPosition).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        ParentViewHolder parentViewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_message_title, parent, false);
            parentViewHolder = new ParentViewHolder();
            parentViewHolder.title = convertView.findViewById(R.id.item_title_tv);
            convertView.setTag(parentViewHolder);
        } else {
            parentViewHolder = (ParentViewHolder) convertView.getTag();
        }
        parentViewHolder.title.setText(mGroupTitle[groupPosition]);
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ChildViewHolder childViewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_message_detail, parent, false);
            childViewHolder = new ChildViewHolder();
            childViewHolder.time = convertView.findViewById(R.id.item_time_tv);
            childViewHolder.detail = convertView.findViewById(R.id.item_detail_tv);
            convertView.setTag(childViewHolder);
        } else {
            childViewHolder = (ChildViewHolder) convertView.getTag();
        }
        String[] content = mList.get(groupPosition).get(childPosition);
        childViewHolder.time.setText(content[0]);
        childViewHolder.detail.setText(content[1]);
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    static class ChildViewHolder {
        TextView time;
        TextView detail;
    }

    static class ParentViewHolder {
        TextView title;
    }
}
