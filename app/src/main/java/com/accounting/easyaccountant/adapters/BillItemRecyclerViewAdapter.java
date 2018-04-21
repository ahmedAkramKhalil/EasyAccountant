package com.accounting.easyaccountant.adapters;

/**
 * Created by ahmed on 4/20/2018.
 */


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.accounting.easyaccountant.R;
import com.accounting.easyaccountant.objects.Item;

import java.util.List;

/**
 * Created by asd on 02/18/2018.
 */

public class BillItemRecyclerViewAdapter extends RecyclerView.Adapter<BillItemRecyclerViewAdapter.ContactViewHolder> {

    public static List<Item> contactList;

    public BillItemRecyclerViewAdapter(List<Item> contactList) {
        this.contactList = contactList;
    }

    @Override
    public int getItemCount() {
        return contactList.size();
    }

    @Override
    public void onBindViewHolder(ContactViewHolder contactViewHolder, int i) {
        Item item = contactList.get(i);
        contactViewHolder.name.setText(item.getName());
        contactViewHolder.desc.setText(item.getDisc());
        contactViewHolder.price.setText(item.getQuantity() + " x " + item.getPrice());
        float i2 = item.getQuantity() * item.getPrice() ;
        contactViewHolder.total.setText("" + i2);

//        contactViewHolder.vTitle.setText(ci.name + " " + ci.surname);
    }

    @Override
    public ContactViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.
                from(viewGroup.getContext()).
                inflate(R.layout.rw, viewGroup, false);

        return new ContactViewHolder(itemView);
    }


    public static class ContactViewHolder extends RecyclerView.ViewHolder {
//        protected TextView vName;
//        protected TextView vSurname;
//        protected TextView vEmail;
//        protected TextView vTitle;

        private LinearLayout swipeLayout;
        private View frontLayout;
        private View deleteLayout;
        private TextView name;
        TextView desc ;
        TextView price ;
        TextView total ;


        public ContactViewHolder(final View v) {
            super(v);
            swipeLayout = (LinearLayout) itemView.findViewById(R.id.swipe_layout2);
            frontLayout = itemView.findViewById(R.id.front_layout);
            deleteLayout = itemView.findViewById(R.id.delete_layout);
            name = (TextView) itemView.findViewById(R.id.item_name);
            desc = (TextView) itemView.findViewById(R.id.item_desc);
            price = (TextView) itemView.findViewById(R.id.item_price);
            total = (TextView) itemView.findViewById(R.id.item_total);


        }

    }
}
