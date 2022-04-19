package io.trieulh.simplegithub.journey.dashboard.screens.detail

import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.lifecycleScope
import coil.load
import coil.transform.CircleCropTransformation
import io.trieulh.simplegithub.R
import io.trieulh.simplegithub.base.BaseFragment
import io.trieulh.simplegithub.journey.dashboard.screens.detail.model.UserDetailUIModel
import io.trieulh.simplegithub.utils.displayNullableText
import io.trieulh.simpledomain.model.base.DataState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import org.koin.androidx.viewmodel.ext.android.viewModel

@ExperimentalCoroutinesApi
@FlowPreview
class DetailScreenFragment : BaseFragment<DetailRouter>(R.layout.fragment_screen_detail) {

    private val toolbar: Toolbar? get() = view?.findViewById(R.id.toolbar)
    private val imgAvatar: AppCompatImageView? get() = view?.findViewById(R.id.img_avatar)
    private val tvName: AppCompatTextView? get() = view?.findViewById(R.id.tv_name)
    private val tvLogin: AppCompatTextView? get() = view?.findViewById(R.id.tv_login)
    private val tvFollowers: AppCompatTextView? get() = view?.findViewById(R.id.tv_followers)
    private val tvFollowing: AppCompatTextView? get() = view?.findViewById(R.id.tv_following)
    private val tvEmail: AppCompatTextView? get() = view?.findViewById(R.id.tv_email)
    private val tvLocation: AppCompatTextView? get() = view?.findViewById(R.id.tv_location)
    private val tvBio: AppCompatTextView? get() = view?.findViewById(R.id.tv_bio)

    private val viewModel by viewModel<DetailViewModel>()

    override fun initViewModel() {
        super.initViewModel()

        lifecycleScope.launchWhenResumed {
            viewModel.userDetail.collect {
                when {
                    it is DataState.Result -> displayUserDetail(it.data)
                    it is DataState.Loading && it.data != null -> displayUserDetail(it.data!!)
                    else -> displayError()
                }
            }
        }

        arguments?.let {
            val args = DetailScreenFragmentArgs.fromBundle(it)
            viewModel.getUserDetail(args.detail.login)
        } ?: { displayError() }
    }

    private fun displayError() {

    }

    private fun displayUserDetail(data: UserDetailUIModel) {
        toolbar?.setNavigationOnClickListener {
            router?.goBack()
        }
        data.avatarURL?.let {
            imgAvatar?.load(it) {
                crossfade(true)
                placeholder(R.drawable.ic_logo_github)
                transformations(CircleCropTransformation())
            }
        }

        tvName?.displayNullableText(data.name)
        tvLogin?.displayNullableText(data.login)
        tvFollowers?.displayNullableText(data.followers?.toString(), stringResId = R.string.followers)
        tvFollowing?.displayNullableText(data.following?.toString(), stringResId = R.string.following)
        tvEmail?.displayNullableText(data.email)
        tvLocation?.displayNullableText(data.location)
        tvBio?.displayNullableText(data.bio)
    }
}