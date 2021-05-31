package com.example.gomart.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gomart.R;
import com.example.gomart.models.AddressModel;

import java.util.List;

public class AddressAdapter  extends RecyclerView.Adapter<AddressAdapter.ViewHolder> {

    Context context;
    List<AddressModel> addressModelList;

    SelectedAddres selectedAddres;

    private RadioButton selectedRadioBtn;
    public AddressAdapter(Context context, List<AddressModel> addressModelList, SelectedAddres selectedAddres) {
        this.context = context;
        this.addressModelList = addressModelList;
        this.selectedAddres = selectedAddres;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.addres_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.addres.setText(addressModelList.get(position).getUserAddres());
        holder.radioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (AddressModel address:addressModelList)
                {
                    address.setSelected(false);
                }
                addressModelList.get(position).setSelected(true);
                if(selectedRadioBtn!=null)
                {
                    selectedRadioBtn.setChecked(false);
                }
                selectedRadioBtn=(RadioButton) v;
                selectedRadioBtn.setChecked(true);
                selectedAddres.setAddress(addressModelList.get(position).getUserAddres());
            }
        });

    }

    @Override
    public int getItemCount() {
        return addressModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView addres;
        RadioButton radioButton;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            addres=itemView.findViewById(R.id.address_add);
            radioButton=itemView.findViewById(R.id.select_address);

        }
    }

    public interface  SelectedAddres{

        void  setAddress(String address);
    }

}
