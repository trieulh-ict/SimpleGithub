package io.trieulh.simplegithub.utils

import android.view.View
import androidx.annotation.StringRes
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.widget.doOnTextChanged
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.onCompletion

@ExperimentalCoroutinesApi
@FlowPreview
fun AppCompatEditText.textChanges(): Flow<CharSequence?> {
    val query = MutableStateFlow<String?>("")

    val listener = doOnTextChanged { text, start, count, after ->
        text?.toString()?.let {
            query.value = it
        }
    }

    return query.onCompletion {
        removeTextChangedListener(listener)
    }
}

fun View.onDebounceCLick(onClickListener: View.OnClickListener) {
    val clickWithDebounce: (view: View) -> Unit = debounce(scope = MainScope()) {
        onClickListener.onClick(it)
    }
    setOnClickListener(clickWithDebounce)
}

fun AppCompatTextView.displayNullableText(text: String?, @StringRes stringResId: Int? = null) {
    text?.let {
        this.visibility = View.VISIBLE
        when {
            stringResId != null -> {
                this.text = this.context.getString(stringResId, text)
            }
            else -> {
                this.text = it
            }
        }
    } ?: kotlin.run { this.visibility = View.GONE }
}