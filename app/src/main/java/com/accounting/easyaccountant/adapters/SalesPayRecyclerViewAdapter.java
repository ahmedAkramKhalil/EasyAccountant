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
import com.accounting.easyaccountant.objects.Bill;

import java.util.List;


public class SalesPayRecyclerViewAdapter extends RecyclerView.Adapter<SalesPayRecyclerViewAdapter.ContactViewHolder> {

    public static List<Bill> contactList;

    public SalesPayRecyclerViewAdapter(List<Bill> contactList) {
        this.contactList = contactList;
    }

    @Override
    public int getItemCount() {
        return contactList.size();
    }

    @Override
    public void onBindViewHolder(ContactViewHolder contactViewHolder, int i) {
        Bill bill = contactList.get(i);
        contactViewHolder.payment.setText(bill.getContactsID());
        contactViewHolder.date.setText(bill.getDate());
        contactViewHolder.date2.setText(bill.getDate());
        contactViewHolder.ammount.setText(""+bill.getTotal());

//        contactViewHolder.vTitle.setText(ci.name + " " + ci.surname);
    }

    @Override
    public ContactViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.
                from(viewGroup.getContext()).
                inflate(R.layout.rw_org, viewGroup, false);

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
        TextView payment ;
        TextView date ;
        TextView ammount ;
        TextView date2 ;


        public ContactViewHolder(final View v) {
            super(v);
            swipeLayout = (LinearLayout) itemView.findViewById(R.id.swipe_layout2);
            frontLayout = itemView.findViewById(R.id.front_layout);

            name = (TextView) itemView.findViewById(R.id.item_name);
            payment = (TextView) itemView.findViewById(R.id.ppayment);
            date = (TextView) itemView.findViewById(R.id.pdate);
            ammount = (TextView) itemView.findViewById(R.id.pammount);
            date2 = (TextView) itemView.findViewById(R.id.pdate2);


        }

    }
}
