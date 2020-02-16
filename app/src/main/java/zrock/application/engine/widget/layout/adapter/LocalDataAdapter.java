package zrock.application.engine.widget.layout.adapter;

import zrock.application.R;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

public class LocalDataAdapter extends RecyclerView.Adapter<LocalDataAdapter.ViewHolder> {
    private int[] images = {R.drawable.menu_engine, R.drawable.menu_root, R.drawable.menu_busybox,
            R.drawable.menu_superuser, R.drawable.menu_scriptme, R.drawable.menu_connection, R.drawable.menu_cloud
           			};

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_image_over, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.imageView.setImageResource(images[position]);
        holder.imageView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return images.length;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        ViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.image);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(v.getContext(), "点击了" + v.getTag(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
