package com.example.smokingcessationhelper;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.ListFragment;

import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class ContactFragment extends ListFragment {
    ContactListAdapter contactListAdapter = new ContactListAdapter();

    public ContactFragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contact, container, false);
        Button btAdd = (Button) view.findViewById(R.id.ContactFragment_btAddContact);
        Button btDel = (Button) view.findViewById(R.id.ContactFragment_btDelContact);
        final ListView listView = (ListView) view.findViewById(android.R.id.list);
        setListAdapter(contactListAdapter);

        btAdd.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ContactInputActivity.class);
                startActivityForResult(intent, 1000);
            }
        });
        btDel.setOnClickListener(new Button.OnClickListener() {
            SparseBooleanArray checkedItems;
            @Override
            public void onClick(View view) {
                checkedItems = listView.getCheckedItemPositions();
                int i_cnt = contactListAdapter.getCount();

                for (int i = i_cnt - 1; i >= 0; i--)
                    if (checkedItems.get(i))
                        contactListAdapter.removeItem(i);
                listView.clearChoices();
                contactListAdapter.notifyDataSetChanged();
            }
        });

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1000 && resultCode == Activity.RESULT_OK) {
            // 아이템 추가
            contactListAdapter.addItem(data.getStringExtra("name"), data.getStringExtra("phoneNum"));
            contactListAdapter.notifyDataSetChanged();
        }
    }
}
