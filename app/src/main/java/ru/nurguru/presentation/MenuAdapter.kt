package ru.nurguru.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import ru.nurguru.R
import ru.nurguru.databinding.ProductItemBinding
import ru.nurguru.domain.model.Product

class MenuAdapter(
    private val products: MutableList<Product>,
    private val listener: (Product) -> Unit
) : RecyclerView.Adapter<MenuAdapter.ProductHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.product_item, parent, false)
        return ProductHolder(view, listener)
    }

    override fun getItemCount(): Int = products.size

    override fun onBindViewHolder(holder: ProductHolder, position: Int) {
        holder.setProductData(
            with(products[position]) {
                Product(productId, productImg, productName, category, price, primeCost)
            }
        )
    }

    fun updateAdapter(listItems: List<Product>) {
        products.clear()
        products.addAll(listItems)
        notifyDataSetChanged()
    }

    class ProductHolder(productItemView: View, private val listener: (Product) -> Unit) :
        RecyclerView.ViewHolder(productItemView) {

        private val binding = ProductItemBinding.bind(productItemView)

        //добавляет данные на ячейке товара в RecyclerView (на основном экране)
        fun setProductData(product: Product) {
            with(binding) {
                ivProductImageInMenu.setImageURI(product.productImg.toUri())
                tvProductName.text = product.productName
                tvProductCategory.text = product.category
                tvProductPrice.text = product.price.toString()
                tvProductPrimeCost.text = product.primeCost.toString()
            }

            itemView.setOnClickListener { listener.invoke(product) }
        }
    }
}