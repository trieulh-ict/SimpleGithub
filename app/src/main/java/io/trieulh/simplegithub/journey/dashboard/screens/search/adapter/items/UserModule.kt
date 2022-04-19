package io.trieulh.simplegithub.journey.dashboard.screens.search.adapter.items

import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import coil.load
import coil.transform.CircleCropTransformation
import io.trieulh.simplegithub.R
import io.trieulh.simplegithub.journey.dashboard.screens.search.model.UserUIModel
import io.trieulh.simplegithub.utils.onDebounceCLick
import io.trieulh.simplegenericadapter.diff.Diffable
import io.trieulh.simplegenericadapter.holder.SimpleViewHolder
import io.trieulh.simplegenericadapter.module.ItemModule

class UserModule(val listener: OnModuleClickListener) : ItemModule<UserUIModel>() {
    override val layoutRes: Int = R.layout.view_item_user

    override fun getType(): Int = SearchItemType.USER.value

    override fun isModule(item: Diffable): Boolean = item is UserUIModel

    override fun isStickyModule(item: UserUIModel): Boolean = false

    override fun onBind(item: UserUIModel, holder: SimpleViewHolder) {
        holder.itemView.onDebounceCLick {
            listener.onUserClicked(item)
        }
        holder.itemView.findViewById<AppCompatTextView>(R.id.tv_name).text = item.login
        item.avatarURL?.let {
            holder.itemView.findViewById<AppCompatImageView>(R.id.img_avatar).load(it) {
                crossfade(true)
                placeholder(R.drawable.ic_logo_github)
                transformations(CircleCropTransformation())
            }
        }
    }

    interface OnModuleClickListener {
        fun onUserClicked(item: UserUIModel)
    }
}