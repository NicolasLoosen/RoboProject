package com.mobileapplicationdev.roboproject.models;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.mobileapplicationdev.roboproject.R;

import java.util.List;

@SuppressWarnings("CanBeFinal")
public class ProfileAdapter extends BaseAdapter {
    private List<RobotProfile> profileList;
    private static LayoutInflater inflater;

    public ProfileAdapter(Activity activity, List<RobotProfile> profileList) {
        this.profileList = profileList;
        inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return profileList.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        RobotProfile robotProfile;

        if (view == null) {
            view = inflater.inflate(R.layout.profile_list_item, null);
        }

        TextView robotName = view.findViewById(R.id.profileListItem);
        robotProfile = profileList.get(position);

        robotName.setText(robotProfile.getName());

        return view;
    }
}
