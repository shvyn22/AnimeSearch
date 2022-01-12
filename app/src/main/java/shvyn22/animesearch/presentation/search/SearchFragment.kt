package shvyn22.animesearch.presentation.search

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.View
import androidx.activity.result.ActivityResultRegistry
import androidx.activity.result.contract.ActivityResultContracts.RequestPermission
import androidx.core.net.toUri
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import shvyn22.animesearch.R
import shvyn22.animesearch.databinding.FragmentSearchBinding
import shvyn22.animesearch.util.*

@AndroidEntryPoint
class SearchFragment(
    private val registry: ActivityResultRegistry
) : Fragment(R.layout.fragment_search) {

    private val viewModel: SearchViewModel by viewModels()

    private val requestPermission = registerForActivityResult(RequestPermission()) {
        if (!it) viewModel.onErrorOccurred(
            ResourceError.Specified(getString(R.string.text_error_permission))
        )
    }

    private fun initUI(
        binding: FragmentSearchBinding,
        imagePicker: ImagePicker,
        adapter: SearchAdapter,
    ) {
        binding.apply {
            rvResult.adapter = adapter

            btnBookmarks.setOnClickListener {
                findNavController().navigate(R.id.action_search_to_bookmarks)
            }

            btnFromFile.setOnClickListener {
                imagePicker.pickImageFromFile()
            }

            btnFromCamera.setOnClickListener {
                if (requireActivity().checkSelfPermission(
                        android.Manifest.permission.CAMERA
                    ) == PackageManager.PERMISSION_GRANTED
                ) {
                    imagePicker.pickImageFromCamera()
                } else {
                    requestPermission.launch(android.Manifest.permission.CAMERA)
                }
            }

            btnSearch.setOnClickListener {
                val bytes = requireActivity()
                    .contentResolver
                    .openInputStream(viewModel.uri.value.toUri())
                    ?.readBytes()

                bytes?.let { viewModel.searchImage(it) }
            }
        }
    }

    private fun subscribeToObservers(
        binding: FragmentSearchBinding,
        adapter: SearchAdapter,
    ) {
        binding.apply {
            viewModel.uri.collectOnLifecycle(viewLifecycleOwner) {
                if (it.isNotEmpty()) {
                    groupPreview.isVisible = true
                    Glide.with(root)
                        .load(Uri.parse(it))
                        .defaultRequests()
                        .into(ivPreview)
                }
            }

            viewModel.searchResults.collectOnLifecycle(viewLifecycleOwner) {
                pbLoading.isVisible = it is Resource.Loading

                if (it is Resource.Success) {
                    adapter.submitList(it.data)
                } else if (it is Resource.Error)
                    viewModel.onErrorOccurred(it.error)
            }

            viewModel.events.collectOnLifecycle(viewLifecycleOwner) {
                when (it) {
                    is StateEvent.NavigateToAnilist -> {
                        val intent = Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse(ANILIST_URL + it.id)
                        )
                        startActivity(intent)
                    }
                    is StateEvent.ShowError -> root.showError(
                        when (it.error) {
                            is ResourceError.Fetching -> getString(R.string.text_error_fetching)
                            is ResourceError.Specified -> it.error.msg
                            else -> throw IllegalArgumentException()
                        }
                    )
                }
            }
        }
    }

    private fun processGettingImageResult(uri: Uri?) {
        if (uri == null)
            viewModel.onErrorOccurred(
                ResourceError.Specified(getString(R.string.text_error_loading))
            )
        else
            viewModel.updateSelectedImage(uri.toString())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentSearchBinding.bind(view)
        val cameraUri = requireContext().createTempUri()

        val imagePicker = ImagePicker(
            registry = registry,
            lifecycleOwner = this,
            cameraUri = cameraUri,
            processResult = this::processGettingImageResult
        )

        val adapter = SearchAdapter(
            onNavigateToAnilist = viewModel::onNavigateToAnilist,
            onAddToBookmarks = viewModel::onAddToBookmarks,
            onRemoveFromBookmarks = viewModel::onRemoveFromBookmarks
        )

        initUI(binding, imagePicker, adapter)
        subscribeToObservers(binding, adapter)

        setHasOptionsMenu(true)
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        super.onPrepareOptionsMenu(menu)

        menu.findItem(R.id.action_delete)?.isVisible = false
    }
}