package com.example.contacts.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import android.widget.Toast;

import com.example.contacts.R;

import java.util.ArrayList;
import java.util.List;

import com.example.contacts.models.Contact;

public class ContactAdapter extends  RecyclerView.Adapter<ContactAdapter.ContactViewHolder> implements Filterable{

    private Context context;
    private List<Contact> contactsList;
    private List<Contact> filteredContactsList;
    private RecyclerView recyclerView;
    private final View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            int itemPosition = recyclerView.getChildLayoutPosition(view);
            Contact contact = contactsList.get(itemPosition);

            if(contact.isSelected()){
                contact.setSelected(false);
            }
            else{
                contact.setSelected(true);
            }
            recyclerView.getAdapter().notifyItemChanged(itemPosition);

            Toast.makeText(context, contact.getContactName(), Toast.LENGTH_LONG).show();
        }
    };


    public ContactAdapter(Context context, List<Contact> contactsList, RecyclerView recyclerView) {
        this.context = context;
        this.contactsList = contactsList;
        this.recyclerView = recyclerView;
        this.filteredContactsList = contactsList;
    }

    public void setContactsList(List<Contact> contactsList) {
        this.contactsList = contactsList;
        this.filteredContactsList = contactsList;
    }

    @Override
    public ContactViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_contact, viewGroup, false);
        v.setOnClickListener(onClickListener);
        ContactViewHolder evh = new ContactViewHolder(v);
        return evh;
    }
    @Override
    public void onBindViewHolder(ContactViewHolder contactViewHolder, int i) {
        contactViewHolder.contactName.setText(filteredContactsList.get(i).getContactName());
        contactViewHolder.contactNumber.setText(filteredContactsList.get(i).getContactNumber());
        if(filteredContactsList.get(i).isSelected())
        {
            contactViewHolder.isSelected.setChecked(true);
        }
        else{
            contactViewHolder.isSelected.setChecked(false);
        }
    }

    @Override
    public int getItemCount() {
        return filteredContactsList.size();
    }

    public void notifyDataChanged(){
        this.notifyDataSetChanged();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    filteredContactsList = contactsList;
                } else {
                    List<Contact> filteredList = new ArrayList<>();
                    for (Contact row : contactsList) {
                        if (row.getContactName().toLowerCase().contains(charString.toLowerCase()) || row.getContactNumber().contains(charSequence)) {
                            filteredList.add(row);
                        }
                    }
                    filteredContactsList = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = filteredContactsList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                filteredContactsList = (ArrayList<Contact>) filterResults.values;
                // refresh the list with filtered data
                notifyDataSetChanged();
            }
        };
    }

    public static class ContactViewHolder extends RecyclerView.ViewHolder {
        TextView contactName;
        TextView contactNumber;
        CheckBox isSelected;

        ContactViewHolder(View itemView) {
            super(itemView);
            contactName = (TextView)itemView.findViewById(R.id.contact_name);
            contactNumber = (TextView)itemView.findViewById(R.id.contact_number);
            isSelected = (CheckBox)itemView.findViewById(R.id.checkBox);
            isSelected.setClickable(false);
        }
    }
}
