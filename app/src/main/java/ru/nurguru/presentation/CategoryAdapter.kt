package ru.nurguru.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import ru.nurguru.R
import ru.nurguru.databinding.CategoryItemBinding
import ru.nurguru.databinding.ProductItemBinding
import ru.nurguru.domain.model.Category
import ru.nurguru.domain.model.Product

class CategoryAdapter(
    private val categories: MutableList<Category>, private val listener: (Category) -> Unit
) : RecyclerView.Adapter<CategoryAdapter.CategoryHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.category_item, parent, false)
        return CategoryHolder(view, listener)
    }

    override fun getItemCount(): Int = categories.size

    override fun onBindViewHolder(holder: CategoryHolder, position: Int) {
        holder.setCategoryData(with(categories[position]) {
            Category(categoryId, categoryImg, categoryName)
        })
    }

    fun updateAdapter(listItems: List<Category>) {
        categories.clear()
        categories.addAll(listItems)
        notifyDataSetChanged()
    }

    class CategoryHolder(categoryItemView: View, private val listener: (Category) -> Unit) :
        RecyclerView.ViewHolder(categoryItemView) {

        private val binding = CategoryItemBinding.bind(categoryItemView)

        //добавляет данные на ячейке категории в RecyclerView (на основном экране)
        fun setCategoryData(category: Category) {
            with(binding) {
                ivCategoryImage.setImageURI(category.categoryImg.toUri())
                tvCategoryName.text = category.categoryName
            }

            itemView.setOnClickListener { listener.invoke(category) }
        }
    }
}