package ufu.ecotravel.UserInterface;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import ufu.ecotravel.Classes.Place;
import ufu.ecotravel.R;

/**
 * Created by Lucas on 29/10/2017.
 */

public class DisplayListPlacesAdapter extends RecyclerView.Adapter<RecyclerViewPlacesHolder> {

    Context DisplayListPlacesAdapterContext;
    ArrayList<Place> arrayList = new ArrayList<>();

    public DisplayListPlacesAdapter(ArrayList<Place> arrayList, Context ctx){

        this.arrayList = arrayList;
        this.DisplayListPlacesAdapterContext = ctx;
    }

    @Override
    public RecyclerViewPlacesHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.place_row_layout,parent,false);

        return new RecyclerViewPlacesHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerViewPlacesHolder holder, int position) {

        holder.Nome.setText(arrayList.get(position).getNome());
        holder.Descricao.setText(arrayList.get(position).getDescricao());

        if (position == 0) {
            Picasso.with(DisplayListPlacesAdapterContext)
                    .load(R.mipmap.ce)
                    .placeholder(R.drawable.noimage)
                    .error(R.drawable.noimage)
                    .into(holder.Imagem);
        }
        if (position == 1) {
            Picasso.with(DisplayListPlacesAdapterContext)
                    .load(R.mipmap.ch)
                    .placeholder(R.drawable.noimage)
                    .error(R.drawable.noimage)
                    .into(holder.Imagem);
        }
        if (position == 2) {
            Picasso.with(DisplayListPlacesAdapterContext)
                    .load(R.mipmap.cz)
                    .placeholder(R.drawable.noimage)
                    .error(R.drawable.noimage)
                    .into(holder.Imagem);
        }

    }

    public int getCount()
    {
        return arrayList.size();
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

}

class RecyclerViewPlacesHolder extends RecyclerView.ViewHolder{

    public TextView Nome, Descricao;
    public ImageView Imagem;

    RecyclerViewPlacesHolder(View view){

        super(view);

        Nome = (TextView)view.findViewById(R.id.nomePlace);
        Descricao = (TextView)view.findViewById(R.id.descPlace);
        Imagem = (ImageView)view.findViewById(R.id.imagePlace);
    }

}
