package io.trieulh.simplegithub.journey.dashboard.screens.search.adapter.items

import io.trieulh.simplegithub.R
import io.trieulh.simplegenericadapter.holder.SimpleViewHolder
import io.trieulh.simplegenericadapter.module.EmptyModule

class UsersEmptyModule : EmptyModule() {
    override val layoutRes: Int = R.layout.view_item_empty_users

    override fun onBind(holder: SimpleViewHolder) {
        // Do nothing now
    }

}
