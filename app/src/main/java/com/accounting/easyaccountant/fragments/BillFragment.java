/*
 * Copyright (c) 2017. Truiton (http://www.truiton.com/).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Contributors:
 * Mohit Gupt (https://github.com/mohitgupt)
 *
 */

package com.accounting.easyaccountant.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.accounting.easyaccountant.adapters.BillItemRecyclerViewAdapter;
import com.accounting.easyaccountant.adapters.BillPayRecyclerViewAdapter;
import com.accounting.easyaccountant.DatabaseHandler;
import com.accounting.easyaccountant.MainActivity;
import com.accounting.easyaccountant.R;
import com.accounting.easyaccountant.objects.Bill;
import com.accounting.easyaccountant.objects.Contact;
import com.accounting.easyaccountant.objects.Item;
import com.accounting.easyaccountant.objects.Payment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BillFragment extends Fragment {

     List<Item> itemRet = new ArrayList<>();
    List<Payment   > payRet = new ArrayList<>();
    float totalBill = 0;
    float dueBill = 0;
    Contact contactRet = null;

    public static BillFragment newInstance() {
        BillFragment fragment = new BillFragment();
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_bill, container, false);
    }

    LinearLayout addCustomer;
    LinearLayout customer;
    LinearLayout addItem;
    LinearLayout addPayment;

    TextView name;
    TextView email;
    RecyclerView recyclerView;
    RecyclerView recyclerView2;
    Button button;
    BillItemRecyclerViewAdapter adapter;
     BillPayRecyclerViewAdapter adapter2 ;
     TextView subT ;
     TextView total ;
     TextView due ;
     TextView totalDis ;
     EditText dateBill ;


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        addCustomer = (LinearLayout) getView().findViewById(R.id.add_customer);
        customer = (LinearLayout) getView().findViewById(R.id.cusomerl);
        addItem = (LinearLayout) getView().findViewById(R.id.itemadd);
        addPayment = (LinearLayout) getView().findViewById(R.id.pay_add);
        name = (TextView) getView().findViewById(R.id.name);
        email = (TextView) getView().findViewById(R.id.ed_email);
        button = (Button) getView().findViewById(R.id.btn10);
        recyclerView = (RecyclerView) getView().findViewById(R.id.recyclerView2);
        recyclerView2 = (RecyclerView) getView().findViewById(R.id.recyclerView3);

        dateBill = (EditText)getView().findViewById(R.id.datebill) ;

        subT = (TextView) getView().findViewById(R.id.Subtotal);
        total = (TextView) getView().findViewById(R.id.total);
        due = (TextView) getView().findViewById(R.id.due_amount);
        totalDis = (TextView) getView().findViewById(R.id.totaldis);



        adapter = new BillItemRecyclerViewAdapter(itemRet);
        adapter2 = new BillPayRecyclerViewAdapter(payRet);
        recyclerView.setAdapter(adapter);
        recyclerView2.setAdapter(adapter2);


        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//        customAdapter = new CustomAdapter(db.getAllDevices());
        recyclerView.setHasFixedSize(true);

//        customAdapter.notifyAll();

        recyclerView2.setItemAnimator(new DefaultItemAnimator());

        recyclerView2.setLayoutManager(new LinearLayoutManager(getContext()));
//        customAdapter = new CustomAdapter(db.getAllDevices());
        recyclerView2.setHasFixedSize(true);

//        customAdapter.notifyAll();

        recyclerView.setItemAnimator(new DefaultItemAnimator());

         button.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {

                 Bill bill = new Bill() ;
                 bill.setContactsID(contactRet.get_name());
                 bill.setDate(dateBill.getText().toString());
                 bill.setTotal(totalBill);
                 DatabaseHandler.getInstance().addBill(bill);
                 MainActivity.changeMenuVisiblity();
                 FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                 FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                 fragmentTransaction.replace(R.id.frame_layout, DashboardFragment.newInstance());
                 fragmentTransaction.commit();
             }
         });

        addCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCustomers();
            }
        });
        addPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAddPayment(Payment.PAY);
            }
        });
        addItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showItems();
            }
        });


    }

    AlertDialog dsItems;

    public void showItems() {
        final AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.show_dialog, null);
        dialogBuilder.setView(dialogView);
        dsItems = dialogBuilder.create();
         final List<Item> listOfNumbers = DatabaseHandler.getInstance().getAllItems();
        final List<String> strings = new ArrayList<>();
        final HashMap<String, Integer> map = new HashMap<>();
        for (Item item : listOfNumbers) {
            map.put(item.getName(), item.getId());
            strings.add(item.getName());
        }

        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1);
        arrayAdapter.addAll(strings);
        ListView listView = (ListView) dialogView.findViewById(R.id.list);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                for (Item item : listOfNumbers) {
                    if (item.getId() == map.get(strings.get(position))) {
                        itemRet.add(item);
                        updateBill();
                        dsItems.dismiss();


                    }
                }
            }
        });

        Button button = (Button) dialogView.findViewById(R.id.btn);
        button.setText("Create New Item");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAddItem();
                dsItems.dismiss();
            }
        });

        dsItems = dialogBuilder.create();
        dsItems.show();

    }

    AlertDialog dItem;

    public void showAddItem() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.add_item_dialog, null);
        dialogBuilder.setView(dialogView);
        dItem = dialogBuilder.create();
        final EditText name = (EditText) dialogView.findViewById(R.id.ed_nam);
        final EditText desc = (EditText) dialogView.findViewById(R.id.ed_des);
        final EditText unit = (EditText) dialogView.findViewById(R.id.ed_unit);
        final EditText price = (EditText) dialogView.findViewById(R.id.ed_price);
        final EditText quantity = (EditText) dialogView.findViewById(R.id.ed_quantity);
        final EditText descount = (EditText) dialogView.findViewById(R.id.ed_discount);

        Button button = (Button) dialogView.findViewById(R.id.btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Item item = new Item();
                item.setName(name.getText().toString());
                item.setDisc(desc.getText().toString());
                item.setUnit(unit.getText().toString());
                item.setPrice(Integer.parseInt(price.getText().toString()));
                item.setQuantity(Integer.parseInt(quantity.getText().toString()));
                item.setDiscount(Integer.parseInt(descount.getText().toString()));
                Item item1 = item;

                DatabaseHandler.getInstance().addItem(item1);
                itemRet.add(item1);
                updateBill();
                dItem.dismiss();

            }
        });

        dItem = dialogBuilder.create();
        dItem.show();

    }


    AlertDialog dsCustomer;

    public void showCustomers() {
        final AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.show_dialog, null);
        dialogBuilder.setView(dialogView);
        dsItems = dialogBuilder.create();
        final List<Contact> listOfNumbers = DatabaseHandler.getInstance().getAllCustomer();
        final List<String> strings = new ArrayList<>();
        final HashMap<String, Integer> map = new HashMap<>();
        for (Contact item : listOfNumbers) {
            map.put(item.getName(), item.get_id());
            strings.add(item.getName());
        }

        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1);
        arrayAdapter.addAll(strings);
        ListView listView = (ListView) dialogView.findViewById(R.id.list);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                for (Contact item : listOfNumbers) {
                    if (item.get_id() == map.get(strings.get(position))) {
                        contactRet = item;
                        updateBillContact();
                        dsItems.dismiss();

                    }
                }
            }
        });

        Button button = (Button) dialogView.findViewById(R.id.btn);
        button.setText("Create New Customer");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                               showAddCustomer(Contact.CUSTOMER);
                dsItems.dismiss();
            }
        });

        dsItems = dialogBuilder.create();
        dsItems.show();

    }

    AlertDialog dacustomer;

    public void showAddCustomer(final int i) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.add_customer_dialog, null);
        dialogBuilder.setView(dialogView);
        dacustomer = dialogBuilder.create();
        final EditText company = (EditText) dialogView.findViewById(R.id.ed_company);
        final EditText name = (EditText) dialogView.findViewById(R.id.ed_name);
        final EditText phone = (EditText) dialogView.findViewById(R.id.ed_phone);
        final EditText email = (EditText) dialogView.findViewById(R.id.ed_email);

        Button button = (Button) dialogView.findViewById(R.id.btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Contact contact = new Contact();
                contact.set_name(company.getText().toString());
                contact.set_sname(name.getText().toString());
                contact.set_email(email.getText().toString());
                contact.set_type(i);
                contact.set_phone_number(phone.getText().toString());

                final Contact contact1 = contact;

                DatabaseHandler.getInstance().addContact(contact1);
                contactRet = contact1;
                updateBillContact();
                dacustomer.dismiss();

            }
        });

        dacustomer = dialogBuilder.create();
        dacustomer.show();
    }


    AlertDialog dsSupplier;

    public void showSuppliers() {
        final AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.show_dialog, null);
        dialogBuilder.setView(dialogView);
        dsItems = dialogBuilder.create();
        final List<Contact> listOfNumbers = DatabaseHandler.getInstance().getAllCustomer();
        final List<String> strings = new ArrayList<>();
        final HashMap<String, Integer> map = new HashMap<>();
        for (Contact item : listOfNumbers) {
            map.put(item.getName(), item.get_id());
            strings.add(item.getName());
        }

        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1);
        arrayAdapter.addAll(strings);
        ListView listView = (ListView) dialogView.findViewById(R.id.list);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                for (Contact item : listOfNumbers) {
                    if (item.get_id() == map.get(strings.get(position))) {
                        contactRet = item;
                        updateBillContact();
                        dsItems.dismiss();

                    }
                }
            }
        });

        Button button = (Button) dialogView.findViewById(R.id.btn);
        button.setText("Create New Supplier");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAddCustomer(Contact.SUPPLIER);
                dsItems.dismiss();
            }
        });

        dsItems = dialogBuilder.create();
        dsItems.show();

    }

    AlertDialog bpay;

    public void showAddPayment(final int i) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.add_payment_dialog, null);
        dialogBuilder.setView(dialogView);
        bpay = dialogBuilder.create();
        final EditText ammount = (EditText) dialogView.findViewById(R.id.ed_ammount);
        final EditText desc = (EditText) dialogView.findViewById(R.id.ed_description);
        final EditText date = (EditText) dialogView.findViewById(R.id.ed_date);

        Button button = (Button) dialogView.findViewById(R.id.btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Payment payment = new Payment();
                payment.setAmmount(Float.parseFloat(ammount.getText().toString()));
                payment.setDesc(desc.getText().toString());
                payment.setDate(date.getText().toString());
                payment.setType(i);
                final Payment payment1 = payment;
                payRet.add(payment);
                DatabaseHandler.getInstance().addPayment(payment1);
                updatepay();
                bpay.dismiss();
            }
        });

        bpay = dialogBuilder.create();
        bpay.show();
    }

    private void updatepay() {
        show();
        adapter.notifyDataSetChanged();

    }


    private void updateBillContact() {
      customer.setVisibility(View.VISIBLE);
      addCustomer.setVisibility(View.GONE);
      name.setText(contactRet.get_name());
      email.setText(contactRet.get_email());
    }


    private void updateBill() {
        show();
        adapter.notifyDataSetChanged();
    }
    void show (){
        try {
            float sub = 0;
            int toD;
            int totali;
            float duei = 0;
            float jj = 0 ;
            for (Item item : itemRet) {
                sub = sub + (item.getPrice() * item.getQuantity());

            }
            for (Payment payment : payRet) {
                duei = duei + payment.getAmmount();
            }

            jj = sub - duei ;
            totalBill = jj ;
            subT.setText("" + sub);
            total.setText("" + sub);
            due.setText("" + jj);



        }catch (Exception e){}

    }
}
