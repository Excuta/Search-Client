package com.excuta.searchclient.ui.main.search

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.provider.SearchRecentSuggestions
import android.view.*
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.excuta.searchclient.R
import com.excuta.searchclient.data.suggestions.SuggestionsContentProvider
import com.excuta.searchclient.extensions.getApplication
import com.excuta.searchclient.extensions.hideKeyboard
import com.excuta.searchclient.presentation.Error
import com.excuta.searchclient.presentation.Loading
import com.excuta.searchclient.presentation.SearchViewModel
import com.excuta.searchclient.presentation.Success
import com.excuta.searchclient.presentation.di.viewmodel.factory.ViewModelFactory
import com.excuta.searchclient.ui.main.search.di.DaggerSearchComponent
import com.excuta.searchclient.ui.main.webclient.ProgressChromeClient
import com.excuta.searchclient.ui.main.webclient.ProgressWebClient
import io.reactivex.Emitter
import io.reactivex.Observable
import io.reactivex.ObservableOnSubscribe
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.fragment_web_view.*
import javax.inject.Inject


/**
 * A simple [Fragment] subclass.
 */
class SearchFragment : Fragment() {

    private lateinit var searchView: SearchView
    private var disposable: Disposable? = null
    private val queryLiveData = MutableLiveData<String>()

    @field:Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var viewModel: SearchViewModel

    lateinit var searchRecentSuggestions: SearchRecentSuggestions
    lateinit var provider: SelectedSuggestionProvider

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        inject()
        viewModel = ViewModelProviders.of(this, viewModelFactory)[SearchViewModel::class.java]
        searchRecentSuggestions = SearchRecentSuggestions(
            context,
            SuggestionsContentProvider.AUTHORITY,
            SuggestionsContentProvider.MODE
        )
    }

    private fun inject() {
        DaggerSearchComponent.builder()
            .presentationComponent(getApplication()?.presentationComponent)
            .build().inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_web_view, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initWebView()
        observeQuery()
        observeLink()
        observeSuggestionSelected()
    }

    private fun observeSuggestionSelected() {
        provider.getLiveData().observe(viewLifecycleOwner, Observer {
            searchView.setQuery(it, true)
        })
    }

    private fun observeLink() {
        viewModel.linkLiveData.observe(viewLifecycleOwner, Observer {
            when (it) {
                is Success -> {
                    it.data?.let { result ->
                        val link = result.link
                        webView.loadUrl(link)
                        searchView.setQuery(link, false)
                        searchRecentSuggestions.saveRecentQuery(link, result.originalQuery)
                    }
                }
                is Loading -> {

                }
                is Error -> {
                    Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    private fun observeQuery() {
        queryLiveData.observe(viewLifecycleOwner, Observer {
            viewModel.getLinkFor(it)
        })
    }

    private fun initWebView() {
        webView.webChromeClient = ProgressChromeClient(progressBar)
        webView.webViewClient = ProgressWebClient(progressBar)
        webView.settings.javaScriptEnabled = true
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_main, menu)
        val searchItem = menu.findItem(R.id.action_search)
        searchView = searchItem.actionView as SearchView
        disposable?.dispose()
        disposable = createSearchDisposable()
        val searchManager = context?.getSystemService(Context.SEARCH_SERVICE) as SearchManager
        searchView.setSearchableInfo(searchManager.getSearchableInfo(activity?.componentName))
        searchView.isQueryRefinementEnabled = true
    }

    private fun createSearchDisposable(): Disposable? {
        return Observable.create(ObservableOnSubscribe<String> {
            searchView.setOnQueryTextListener(createQueryListener(it))
        })
            .filter { !it.isBlank() }
            .subscribe { queryLiveData.postValue(it) }
    }

    private fun createQueryListener(emitter: Emitter<String>): SearchView.OnQueryTextListener {
        return object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null) {
                    emitter.onNext(query)
                    searchView.clearFocus()
                }
                hideKeyboard()
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is SelectedSuggestionProvider) {
            provider = context
        } else {
            throw IllegalStateException("Host activity must implement interface")
        }
    }

    interface SelectedSuggestionProvider {
        fun getLiveData(): LiveData<String>
    }
}
