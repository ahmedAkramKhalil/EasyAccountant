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
import com.accounting.easyaccountant.objects.Payment;

import java.util.List;


public class BillPayRecyclerViewAdapter extends RecyclerView.Adapter<BillPayRecyclerViewAdapter.ContactViewHolder> {

    public static List<Payment> contactList;

    public BillPayRecyclerViewAdapter(List<Payment> contactList) {
        this.contactList = contactList;
    }

    @Override
    public int getItemCount() {
        return contactList.size();
    }

    @Override
    public void onBindViewHolder(ContactViewHolder contactViewHolder, int i) {
        Payment payment = contactList.get(i);
        contactViewHolder.payment.setText(payment.getDesc());
        contactViewHolder.date.setText(payment.getDate());
        contactViewHolder.ammount.setText(""+payment.getAmmount());

//        contactViewHolder.vTitle.setText(ci.name + " " + ci.surname);
    }

    @Override
    public ContactViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.
                from(viewGroup.getContext()).
                inflate(R.layout.rw_payment, viewGroup, false);

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


        public ContactViewHolder(final View v) {
            super(v);
            swipeLayout = (LinearLayout) itemView.findViewById(R.id.swipe_layout2);
            frontLayout = itemView.findViewById(R.id.front_layout);

            name = (TextView) itemView.findViewById(R.id.item_name);
            payment = (TextView) itemView.findViewById(R.id.ppayment);
            date = (TextView) itemView.findViewById(R.id.pdate);
            ammount = (TextView) itemView.findViewById(R.id.pammount);


        }

    }
}
