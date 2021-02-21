import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView

abstract class BaseRecyclerViewAdapter <T,VH : BaseRecyclerViewHolder<T>>() : RecyclerView.Adapter<VH>(){
    var items = listOf<T>()
        set(value){
            field = value
            notifyDataSetChanged()
        }

    @LayoutRes
    abstract fun getLayoutResource(viewType: Int) : Int

    abstract fun createViewHolder(view: View, viewType: Int) : VH

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val view = LayoutInflater.from(parent.context).inflate(getLayoutResource(viewType),parent,false)
        return createViewHolder(view,viewType)
    }

    override fun getItemCount(): Int = items.count()

    override fun onBindViewHolder(holder: VH, position: Int) {
        val item = items[position]
        holder.bind(item)
    }
}

abstract class BaseRecyclerViewHolder<T>(view: View) : RecyclerView.ViewHolder(view){
    abstract fun bind(item : T)
}