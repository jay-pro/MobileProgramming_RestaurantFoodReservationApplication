package com.example.restaurantfoodreservationapplication.ui.quanly_mon;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
//import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;
//import com.orhanobut.dialogplus.DialogPlus;
//import com.orhanobut.dialogplus.DialogPlusBuilder;
//import com.orhanobut.dialogplus.ViewHolder;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.logging.Handler;

//import de.hdodenhof.circleimageview.CircleImageView;

import static android.os.Build.VERSION_CODES.R;


public class QUANLY_MONAdapter extends FirebaseRecyclerOptions<QUANLY_MONModel,QUANLY_MONAdapter.myviewholder>{
    public QUANLY_MONAdapter(@NonNull FirebaseRecyclerOptions<QUANLY_MONModel> options){
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull final myviewholder holder, final int position, @NonNull final QUANLY_MONModel quanly_monModel){
        holder.tenmon.setText(quanly_monModel.getTenmon());
        holder.giamon.setText(quanly_monModel.getGiamon());
        Glide.with(holder.img.getContext()).load(quanly_monModel.getPurl().into(holder.img));
        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DialogPlus dialogPlus=DialogPlus.newDialog(holder.img.getContext())
                        .setContentHolder(new RecyclerView.ViewHolder(R.layout.dialogcontent))
                        .setExpanded(true,1100)
                        .create();

                View myview = dialogPlus.getHolderView();
                final EditText purl = myview.findViewById(R.id.upurl);
                final EditText tenmon = myview.findViewById(R.id.utenmon);
                final EditText giamon = myview.findViewById(R.id.ugiamon);
                Button submit = myview.findViewById(R.id.usubmit);

                purl.setText(quanly_monModel.getPurl());
                tenmon.setText(quanly_monModel.getTenmon());
                giamon.setText(quanly_monModel.getGiamon());

                dialogPlus.show();
                submit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Map<String,Object> map = new HashMap<>();
                        map.put("tenmon",tenmon.getText().toString());
                        map.put("giamon",giamon.getText().toString());
                        map.put("purl",purl.getText().toString());

                        FirebaseDatabase.getInstance().getReference().child("quanlymon")
                                .child(getRef(position).getKey().updateChildren(map))
                                .addOnSuccessListener(new OnSuccessListener<Void>(){
                                    @Override
                                    public void onSuccess(Void aVoid){
                                        dialogPlus.dismiss();
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener(){
                                    @Override
                                    public void onFailure(@NonNull Exception e){
                                        dialogPlus.dismiss();
                                    }
                                });
                    }
                });
            }
        });

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(holder.img.getContext());
                builder.setTitle("Delete Panel");
                builder.setMessage("Delete...?");

                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        FirebaseDatabase.getInstance().getReference().child("quanlymon")
                                .child(getRef(position).getKey()).removeValue();
                    }
                });
                builder.show();
            }
        });
    } //End of OnBindViewMethod

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.singlerow,parent,false);
        return new myviewholder(view);
    }

    class myviewholder extends RecyclerView.ViewHolder
    {
        CircleImageView img;
        ImageView edit,delete;
        TextView tenmon,giamon;
        public myviewholder(@NonNull View itemView)
        {
            super(itemView);
            img=(CircleImageView) itemView.findViewById(R.id.img1);
            tenmon=(TextView)itemView.findViewById(R.id.tenmontext);
            giamon=(TextView)itemView.findViewById(R.id.giamontext);

            edit=(ImageView)itemView.findViewById(R.id.editicon);
            delete=(ImageView)itemView.findViewById(R.id.deleteicon);
        }
    }
}
