package com.example.zhuk;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

    private List<CartItem> cartItems;

    public CartAdapter(List<CartItem> cartItems, CartItemListener cartItemListener) {
        this.cartItems = cartItems;
        this.cartItemListener = cartItemListener;
    }

    public void setCartItemListener(CartItemListener cartItemListener) {
        this.cartItemListener = cartItemListener;
    }

    private CartItemListener cartItemListener;

    public interface CartItemListener {
        void onCartItemDeleted(int position);
        void deleteCartItemFromFirestore(String cartItemId);
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cartitem, parent, false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        CartItem cartItem = cartItems.get(position);
        holder.bind(cartItem);
    }

    @Override
    public int getItemCount() {
        return cartItems.size();
    }

    public class CartViewHolder extends RecyclerView.ViewHolder {
        private ImageView itemImageView;
        private TextView itemNameTextView;
        private TextView itemPriceTextView;
        private Button deleteButton;

        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            itemImageView = itemView.findViewById(R.id.cartitemphotoimageview);
            itemNameTextView = itemView.findViewById(R.id.cartNameTView);
            itemPriceTextView = itemView.findViewById(R.id.cartPriceTView);
            deleteButton = itemView.findViewById(R.id.deletecartbutton);

            deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        String cartItemId = cartItems.get(position).getId();

                        cartItemListener.deleteCartItemFromFirestore(cartItemId);

                        cartItems.remove(position);
                        notifyItemRemoved(position);
                        notifyItemRangeChanged(position, cartItems.size());
                    }
                }
            });
        }

        public void bind(CartItem cartItem) {
            itemNameTextView.setText(cartItem.getName());
            itemPriceTextView.setText(cartItem.getPrice());
            Log.d("CartAdapterLog", cartItem.getPhotourl());
            Picasso.get().load(cartItem.getPhotourl()).into(itemImageView);
        }
    }
}
