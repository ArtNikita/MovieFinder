package ru.nikitaartamonov.moviefinder.ui.main

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import ru.nikitaartamonov.moviefinder.R

private const val PERMISSION_KEY = "PERMISSION_KEY"

class PermissionExplanationDialogFragment : DialogFragment() {
    private var permissionToExplain: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.getString(PERMISSION_KEY)?.let { permissionToExplain = it }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val msg = when (permissionToExplain) {
            MainContract.PERMISSIONS.ACCESS_COARSE_LOCATION.stringValue -> getString(R.string.access_location_permission_explanation)
            else -> throw IllegalStateException("No such permission")
        }
        return AlertDialog.Builder(requireContext())
            .setMessage(msg)
            .create()
    }

    override fun onCancel(dialog: DialogInterface) {
        super.onCancel(dialog)
        (requireActivity() as MainContract.ExplanationDialogActivityLauncher).onCancelExplanationFragment(
            permissionToExplain
        )
    }

    companion object {
        fun newInstance(permission: String): PermissionExplanationDialogFragment {
            val args = Bundle()
            args.putString(PERMISSION_KEY, permission)
            val fragment = PermissionExplanationDialogFragment()
            fragment.arguments = args
            return fragment
        }

        const val TAG = "PERMISSION_EXPLANATION_DIALOG_FRAGMENT_TAG"
    }
}