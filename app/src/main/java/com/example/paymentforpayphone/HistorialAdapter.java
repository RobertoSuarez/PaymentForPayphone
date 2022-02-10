package com.example.paymentforpayphone;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;

import java.util.List;

public class HistorialAdapter extends RecyclerView.Adapter<HistorialAdapter.ViewHolder> {

    private List<HistorialPagoItem> hData;
    private LayoutInflater mInflater;
    private Context context;

    public HistorialAdapter(List<HistorialPagoItem> items, Context context) {
        this.mInflater = LayoutInflater.from(context);
        this.context = context;
        this.hData = items;
    }

    @Override
    public int getItemCount() { return hData.size(); }

    @NonNull
    @Override
    public HistorialAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.historial_pagoitem, parent, false);
        return new HistorialAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HistorialAdapter.ViewHolder holder, int position) {
        holder.bindData(hData.get(position));
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title, subtitle;
        MaterialButton btnVerEstado;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.pago_item_title);
            subtitle = itemView.findViewById(R.id.pago_item_subtitle);
            btnVerEstado = itemView.findViewById(R.id.pago_item_btn);
        }


        public void bindData(final HistorialPagoItem historialPagoItem) {
            title.setText(historialPagoItem.getTitulo());
            subtitle.setText(historialPagoItem.getSubTitle());
            btnVerEstado.setText("Ver Estado");
            btnVerEstado.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, historialPagoItem.getIdTransaction(), Toast.LENGTH_LONG).show();
                }
            });
        }
    }
}
