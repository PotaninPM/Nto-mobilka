package ru.myitschool.work.ui.login

import android.os.Bundle
import androidx.core.os.bundleOf
import kotlinx.serialization.Serializable
import ru.myitschool.work.ui.qr.scan.QrScanFragment

@Serializable
data object LoginDestination {
    const val REQUEST_KEY = "login"
    private const val KEY_QR_DATA = "key_login"

    fun newInstance(): LoginFragment {
        return LoginFragment()
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