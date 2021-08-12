package com.testmovie.testmovie.Movies

import android.view.LayoutInflater
import android.view.ViewGroup
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import com.testmovie.testmovie.R
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.GridLayoutManager.SpanSizeLookup
import com.testmovie.testmovie.utils.ItemOffsetDecoration
import androidx.fragment.app.FragmentActivity
import com.testmovie.testmovie.utils.Injection
import androidx.lifecycle.ViewModelProviders

class MoviesFragment : Fragment() {
    private var viewModel: MoviesViewModel? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_movies, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = obtainViewModel(activity)
        setupListAdapter()
    }

    private fun setupListAdapter() {
        val recyclerView: RecyclerView = requireActivity().findViewById(R.id.rv_movie_list)
        val discoverMoviesAdapter =
            MoviesAdapter(viewModel)
        val layoutManager = GridLayoutManager(activity, resources.getInteger(R.integer.span_count))

        // draw network status and errors messages to fit the whole row(3 spans)
        layoutManager.spanSizeLookup = object : SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return when (discoverMoviesAdapter.getItemViewType(position)) {
                    R.layout.item_network_state -> layoutManager.spanCount
                    else -> 1
                }
            }
        }

        // setup recyclerView
        recyclerView.adapter = discoverMoviesAdapter
        recyclerView.layoutManager = layoutManager
        val itemDecoration = ItemOffsetDecoration(requireActivity(), R.dimen.item_offset)
        recyclerView.addItemDecoration(itemDecoration)
        // observe paged list
        viewModel!!.pagedList.observe(viewLifecycleOwner, { movies ->
            Log.d("list", movies.size.toString() + "")
            discoverMoviesAdapter.submitList(movies)
        })
        // observe network state
        viewModel!!.networkState.observe(
            viewLifecycleOwner,
            { resource -> discoverMoviesAdapter.setNetworkState(resource) })
    }

    companion object {
        @JvmStatic
        fun newInstance(): MoviesFragment {
            return MoviesFragment()
        }

        fun obtainViewModel(activity: FragmentActivity?): MoviesViewModel {
            val factory = activity?.let { Injection.provideViewModelFactory(it) }
            return ViewModelProviders.of(activity!!, factory).get(
                MoviesViewModel::class.java
            )
        }
    }
}