package com.technipixl.exo1

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.squareup.picasso.Picasso
import com.technipixl.exo1.databinding.FragmentComicsBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ComicsFragment : Fragment() {
    private val characterSercice by lazy { CharacterServiceImpl() }
    private var binding: FragmentComicsBinding? = null
    private val args: ComicsFragmentArgs by navArgs()
    private  var comics = mutableListOf<Item>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentComicsBinding.inflate(layoutInflater, container, false)
        return binding?.root
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)

        loadComics()

    }
    private fun loadComics() {
        val hash = HashGenerator.generateHash(
            CharactersFragment.ts,
            CharactersFragment.privatekey,
            CharactersFragment.publickey
        )

        CoroutineScope(Dispatchers.IO).launch {
            val response =
                hash?.let { characterSercice.getComics(args.characterId, CharactersFragment.publickey, CharactersFragment.ts.toString(),hash) }
            withContext(Dispatchers.Main) {
                if (response != null) {
                    if (response.isSuccessful) {
                        response.body()?.data?.results?.forEach {
                            it.comics?.items?.forEach {
                                comics.add(it)
                            }


                            binding?.textView?.text = it.name
                            var url = it.thumbnail?.path +"."+ it.thumbnail?.extension
                            Picasso.get()
                                .load(url)
                                .into(binding?.imageView)


                        }
                        setupRecyclerView()

                    } else {



                    }
                }


            }
        }
    }

    private fun setupRecyclerView(){


        binding?.comicsRecyclerView?.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        binding?.comicsRecyclerView?.adapter = ComicAdapter(comics)
    }
}