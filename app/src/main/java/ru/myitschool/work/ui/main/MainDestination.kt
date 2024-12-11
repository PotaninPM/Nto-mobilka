package ru.myitschool.work.ui.main

import android.os.Bundle
import androidx.core.os.bundleOf
import kotlinx.serialization.Serializable
import ru.myitschool.work.ui.qr.scan.QrScanFragment

@Serializable
data object MainDestination {
    const val REQUEST_KEY = "main"
    private const val KEY_QR_DATA = "key_main"

    fun newInstance(): MainFragment {
        return MainFragment()
    }

    fun getDataIfExist(bundle: Bundle): String? {
        return if (bundle.containsKey(KEY_QR_DATA)) {
            bundle.getString(KEY_QR_DATA)
        } else {
            null
        }
    }

    internal fun packToBundle(data: String): Bundle {
        return bundleOf(
            KEY_QR_DATA to data
        )
    }
}
