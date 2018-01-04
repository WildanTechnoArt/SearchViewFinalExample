package android.cianjur.developer.net.searchviewfinalexample;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

//Class Adapter ini Digunakan Untuk Mengatur Bagaimana Data akan Ditampilkan
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{

    //Variable ArrayList dengan Parameter dari Class DataFilter (Nama, ImageID)
    private ArrayList<DataFilter> arrayList;

    RecyclerViewAdapter(ArrayList<DataFilter> arrayList){
        this.arrayList = arrayList;
    }

    //ViewHolder Digunakan Untuk Menyimpan Referensi Dari View-View
    class ViewHolder extends RecyclerView.ViewHolder{

        private TextView JudulMeme;
        private ImageView Meme;

        ViewHolder(View itemView) {
            super(itemView);

            //Menginisialisasi View-View untuk kita gunakan pada RecyclerView
            JudulMeme = itemView.findViewById(R.id.memetitle);
            Meme = itemView.findViewById(R.id.meme);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //Membuat View untuk Menyiapkan dan Memasang Layout yang Akan digunakan pada RecyclerView
        View V = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_design, parent, false);
        return new ViewHolder(V);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        //Mengambil Data dari method getNama seseuai Posisi Index pada Class DataFilter
        final String Nama = arrayList.get(position).getNama();
        holder.JudulMeme.setText(Nama);

        //Mengambil Data dari method getImageID seseuai Posisi Index pada Class DataFilter
        holder.Meme.setImageResource(arrayList.get(position).getImageID());
    }

    @Override
    public int getItemCount() {
        //Menghitung Ukuran/Jumlah Data Yang Akan Ditampilkan Pada RecyclerView
        return arrayList.size();
    }

    void setFilter(ArrayList<DataFilter> filterList){
        arrayList = new ArrayList<>();
        arrayList.addAll(filterList);
        notifyDataSetChanged();
    }
}