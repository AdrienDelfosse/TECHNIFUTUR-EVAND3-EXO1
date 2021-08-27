package com.technipixl.exo1

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.technipixl.exo1.databinding.FragmentCharactersBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*

class CharactersFragment : Fragment() , CharacterAdapter.OnItemClickedListener{
    private val characterSercice by lazy { CharacterServiceImpl() }
    private var binding: FragmentCharactersBinding? = null
    private  var characters = mutableListOf<Result>()

    companion object{
        val publickey = "1f0a53973f88e4560cf41a90fee715e9"
        val privatekey = "e32ee031ba5944e82c18ceaf0f4c08dbe528f122"
        val ts = Date().time
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCharactersBinding.inflate(layoutInflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loadCharacters()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    private fun setupRecyclerView(){


        binding?.charactersRecyclerView?.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        binding?.charactersRecyclerView?.adapter = CharacterAdapter(characters, this)
    }

    private fun loadCharacters() {
        val hash = HashGenerator.generateHash(ts, privatekey, publickey)

        CoroutineScope(Dispatchers.IO).launch {
            val response =
                hash?.let { characterSercice.getCharacters(publickey, ts.toString(),hash , "50") }
            withContext(Dispatchers.Main) {
                if (response != null) {
                    if (response.isSuccessful) {

                        response.body()?.data?.results?.forEach {
                            characters.add(it)


                        }


                        setupRecyclerView()

                    } else {



                    }
                }


            }
        }
    }

    override fun onItemClicked(character: Result) {
        val direction = CharactersFragmentDirections.actionCharactersFragmentToComicsFragment(character.id.toString())
        findNavController().navigate(direction)
    }
}