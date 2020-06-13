package com.example.smokingcessationhelper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ContactListAdapter extends BaseAdapter {
    private ArrayList<ContactListItem> contactListItems = new ArrayList<ContactListItem>();

    ContactListAdapter() {}

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        Context context = viewGroup.getContext();

        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.contact_list_item, viewGroup, false);
        }

        TextView tvName = (TextView) view.findViewById(R.id.tvName);
        TextView tvPhoneNumber = (TextView) view.findViewById(R.id.tvPhoneNumber);

        ContactListItem contactListItem = contactListItems.get(position);

        tvName.setText(contactListItem.getName());
        tvPhoneNumber.setText(contactListItem.getPhoneNum());

        return view;
    }

    @Override
    public int getCount() {
        return contactListItems.size();
    }

    @Override
    public ContactListItem getItem(int position) {
        return contactListItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void addItem(String name, String phoneNum) {
        ContactListItem contactListItem = new ContactListItem();

        contactListItem.setName(name);
        contactListItem.setPhoneNum(phoneNum);

        contactListItems.add(contactListItem);
    }

    public void removeItem(int position) {
        contactListItems.remove(position);
    }
}
