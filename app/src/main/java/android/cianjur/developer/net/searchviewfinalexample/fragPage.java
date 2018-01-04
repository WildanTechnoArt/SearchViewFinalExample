package android.cianjur.developer.net.searchviewfinalexample;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;

import java.util.ArrayList;

public class fragPage extends Fragment {

    private RecyclerViewAdapter adapter;
    private ArrayList<DataFilter> arrayList;
    //Daftar Judul
    private String[] Nama = {"Wildan", "Taufan", "Aish", "Ferdi", "Taufik", "Dzaki", "Annisa"};
    //Daftar Gambar
    private int[] Gambar = {R.drawable.meme1, R.drawable.meme2, R.drawable.meme3, R.drawable.meme4,
            R.drawable.meme5, R.drawable.meme6, R.drawable.meme7};


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view_frag1 = inflater.inflate(R.layout.activity_frag_page, container, false);
        setHasOptionsMenu(true);
        arrayList = new ArrayList<>();
        RecyclerView recyclerView = view_frag1.findViewById(R.id.recycler);
        DaftarItem();
        //Menggunakan Layout Manager, Dan Membuat List Secara Vertical
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        //Membuat Underline pada Setiap Item Didalam List
        DividerItemDecoration itemDecoration = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);
        itemDecoration.setDrawable(ContextCompat.getDrawable(getContext(), R.drawable.line));
        recyclerView.addItemDecoration(itemDecoration);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        adapter = new RecyclerViewAdapter(arrayList);
        //Memasang Adapter pada RecyclerView
        recyclerView.setAdapter(adapter);
        return view_frag1;
    }

    //Code Program pada Method dibawah ini akan Berjalan saat Option Menu Dibuat
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.manu_bar, menu);
        MenuItem searchItem = menu.findItem(R.id.search);
        SearchView searchView  = new SearchView(getActivity());
        searchView.setQueryHint("Cari Sesuatu....");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String nextText) {
                //Data akan berubah saat user menginputkan text/kata kunci pada SearchView
                nextText = nextText.toLowerCase();
                ArrayList<DataFilter> dataFilter = new ArrayList<>();
                for(DataFilter data : arrayList){
                    String nama = data.getNama().toLowerCase();
                    if(nama.contains(nextText)){
                        dataFilter.add(data);
                    }
                }
                adapter.setFilter(dataFilter);
                return true;
            }
        });
        searchItem.setActionView(searchView);
    }

    //Memasukan semua data dari variable Nama dan Gambar ke parameter Class DataFiter(Nama,ImageID)
    private void DaftarItem(){
        int count = 0;
        for (String nama : Nama){
            arrayList.add(new DataFilter(nama, Gambar[count]));
            count++;
        }
    }
}