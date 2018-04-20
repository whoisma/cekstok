package com.ma.display.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;
import com.ma.display.R;
import com.ma.display.listener.ProductListListener;
import com.ma.display.model.ProductModel;
import com.ma.display.utils.Helper;

import java.util.List;

/**
 * Created by MA on 09/01/2018.
 */

public class ProductAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private List<ProductModel> list;
    private Context context;
    private ProductListListener listener;

    public ProductAdapter(List<ProductModel> list, Context context, ProductListListener listener) {
        this.list = list;
        this.context = context;
        this.listener = listener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ItemsHolder(LayoutInflater.from(context).inflate(R.layout.item_product, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        if (holder instanceof ItemsHolder){
            ItemsHolder itemsHolder = (ItemsHolder) holder;
            ProductModel model = list.get(position);

            String string = model.getNama_barang().substring(0, 1).toUpperCase();
            ColorGenerator colorGenerator = ColorGenerator.MATERIAL;
            int color = colorGenerator.getColor(model.getNama_barang());
            TextDrawable drawable = TextDrawable.builder().buildRound(string, color);

            itemsHolder.imgIcon.setImageDrawable(drawable);
            itemsHolder.txtNama.setText(model.getNama_barang());
            itemsHolder.txtKode.setText(model.getUpc());
            itemsHolder.txtToko.setText(model.getToko()+"-"+model.getTokos().getNama_toko());
            itemsHolder.txtHarga.setText(Helper.rupiah(model.getJual()));
            itemsHolder.txtStock.setText(model.getJumlah_stock());

            itemsHolder.txtSupplier.setText("-");
            if (model.getSupplier().size() > 0){

                itemsHolder.txtSupplier.setText(model.getSupplier().get(0).getNama_supplier());
            }

            itemsHolder.content.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    listener.onClickItems(position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ItemsHolder extends RecyclerView.ViewHolder{

        FrameLayout content;
        ImageView imgIcon;
        TextView txtKode, txtNama, txtHarga, txtStock, txtToko, txtSupplier;

        public ItemsHolder(View itemView) {
            super(itemView);

            content = itemView.findViewById(R.id.content_body);
            imgIcon = itemView.findViewById(R.id.img_icon);
            txtKode = itemView.findViewById(R.id.txt_kode);
            txtNama = itemView.findViewById(R.id.txt_nama);
            txtStock = itemView.findViewById(R.id.txt_stok);
            txtHarga = itemView.findViewById(R.id.txt_harga);
            txtToko = itemView.findViewById(R.id.txt_toko);
            txtSupplier = itemView.findViewById(R.id.txt_suplier);

        }
    }
}
