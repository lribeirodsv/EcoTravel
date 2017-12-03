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

import ufu.ecotravel.Classes.City;
import ufu.ecotravel.Classes.Place;
import ufu.ecotravel.Interfaces.ItemClickListener;
import ufu.ecotravel.R;

/**
 * Created by Lucas on 29/10/2017.
 */

public class DisplayListCitiesAdapter extends RecyclerView.Adapter<RecyclerViewHolder>{

    private Context DisplayListCitiesAdapterContext;
    private ArrayList<City> arrayList = new ArrayList<>();
    private String estado;

    public DisplayListCitiesAdapter(ArrayList<City> arrayList, Context ctx){

        this.arrayList = arrayList;
        this.DisplayListCitiesAdapterContext = ctx;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.city_row_layout,parent,false);

        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {

        holder.Nome.setText(arrayList.get(position).getNome());

        //region TRATATIVAS-ESTADOS
        if(arrayList.get(position).getEstado().equals("AC")) estado = "Acre";
        if(arrayList.get(position).getEstado().equals("AL")) estado = "Alagoas";
        if(arrayList.get(position).getEstado().equals("AP")) estado = "Amapá";
        if(arrayList.get(position).getEstado().equals("AM")) estado = "Amazonas";
        if(arrayList.get(position).getEstado().equals("BA")) estado = "Bahia";
        if(arrayList.get(position).getEstado().equals("CE")) estado = "Ceará";
        if(arrayList.get(position).getEstado().equals("DF")) estado = "Distrito Federal";
        if(arrayList.get(position).getEstado().equals("ES")) estado = "Espírito Santo";
        if(arrayList.get(position).getEstado().equals("GO")) estado = "Goiás";
        if(arrayList.get(position).getEstado().equals("MA")) estado = "Maranhão";
        if(arrayList.get(position).getEstado().equals("MT")) estado = "Mato Grosso";
        if(arrayList.get(position).getEstado().equals("MS")) estado = "Mato Grosso do Sul";
        if(arrayList.get(position).getEstado().equals("MG")) estado = "Minas Gerais";
        if(arrayList.get(position).getEstado().equals("PA")) estado = "Pará";
        if(arrayList.get(position).getEstado().equals("PB")) estado = "Paraíba";
        if(arrayList.get(position).getEstado().equals("PR")) estado = "Paraná";
        if(arrayList.get(position).getEstado().equals("PE")) estado = "Pernambuco";
        if(arrayList.get(position).getEstado().equals("PI")) estado = "Piauí";
        if(arrayList.get(position).getEstado().equals("RJ")) estado = "Rio de Janeiro";
        if(arrayList.get(position).getEstado().equals("RN")) estado = "Rio Grande do Norte";
        if(arrayList.get(position).getEstado().equals("RS")) estado = "Rio Grande do Sul";
        if(arrayList.get(position).getEstado().equals("RO")) estado = "Rondônia";
        if(arrayList.get(position).getEstado().equals("RR")) estado = "Roraima";
        if(arrayList.get(position).getEstado().equals("SC")) estado = "Santa Catarina";
        if(arrayList.get(position).getEstado().equals("SP")) estado = "São Paulo";
        if(arrayList.get(position).getEstado().equals("SE")) estado = "Sergipe";
        if(arrayList.get(position).getEstado().equals("TO")) estado = "Tocantins";
        //endregion

        holder.Estado.setText(estado);

        Picasso.with(DisplayListCitiesAdapterContext)
                .load(arrayList.get(position).getUrl())
                .placeholder(R.drawable.noimage)
                .error(R.drawable.noimage)
                .into(holder.Fundo);

        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                if(!isLongClick) {
                    Context context = view.getContext();
                    Intent intent = new Intent(context, DisplayDetaisCity.class);
                    intent.putExtra("Nome", arrayList.get(position).getNome());
                    intent.putExtra("Descricao", arrayList.get(position).getDescricao());
                    intent.putExtra("Cod_cidade", arrayList.get(position).getCodigo());
                    context.startActivity(intent);
                }
                if(isLongClick) {}
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }
}

class RecyclerViewHolder extends RecyclerView.ViewHolder implements  View.OnClickListener, View.OnLongClickListener{

    public TextView Nome, Estado;
    public ImageView Fundo;

    private ItemClickListener itemClickListener;

    RecyclerViewHolder(View view){

        super(view);

        Nome = (TextView)view.findViewById(R.id.nome);
        Estado = (TextView)view.findViewById(R.id.estado);
        Fundo = (ImageView)view.findViewById(R.id.fundo);

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
