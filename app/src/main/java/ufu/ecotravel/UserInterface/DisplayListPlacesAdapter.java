package ufu.ecotravel.UserInterface;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import ufu.ecotravel.Classes.Place;
import ufu.ecotravel.Interfaces.ItemClickListener;
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

        Picasso.with(DisplayListPlacesAdapterContext)
                .load(arrayList.get(position).getUrl())
                .placeholder(R.drawable.noimage)
                .error(R.drawable.noimage)
                .into(holder.Imagem);

        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                if(!isLongClick) {
                    Context context = view.getContext();

                    Intent intent = new Intent(context, DisplayMap.class);
                    intent.putExtra("Cod_place", arrayList.get(position).getCodigo());
                    Log.d("TESTE2",String.valueOf(arrayList.get(position).getCodigo()));
                    context.startActivity(intent);
                }
                if(isLongClick) {}
            }
        });

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

class RecyclerViewPlacesHolder extends RecyclerView.ViewHolder  implements  View.OnClickListener, View.OnLongClickListener{

    public TextView Nome, Descricao;
    public ImageView Imagem;

    private ItemClickListener itemClickListener;

    RecyclerViewPlacesHolder(View view){

        super(view);

        Nome = (TextView)view.findViewById(R.id.nomePlace);
        Descricao = (TextView)view.findViewById(R.id.descPlace);
        Imagem = (ImageView)view.findViewById(R.id.imagePlace);

        view.setOnClickListener(this);
        view.setOnLongClickListener(this);
    }

    public void setItemClickListener(ItemClickListener itemClickListener){
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View view) {
        itemClickListener.onClick(view,getAdapterPosition(),false);
    }

    @Override
    public boolean onLongClick(View view) {
        itemClickListener.onClick(view,getAdapterPosition(),true);
        return false;
    }
}
