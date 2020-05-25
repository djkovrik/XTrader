package com.sedsoftware.core.tools.impl.errorhandler

import com.sedsoftware.core.domain.errorhandler.CanShowError
import com.sedsoftware.core.domain.errorhandler.ErrorHandler
import com.sedsoftware.core.domain.exception.CurrencyMapLoadingError
import com.sedsoftware.core.domain.exception.CurrencyPairsLoadingError
import com.sedsoftware.core.domain.exception.CurrencyTickLoadingError
import com.sedsoftware.core.domain.exception.MarketPairsLoadingError
import com.sedsoftware.core.domain.exception.NetworkConnectionMissing
import com.sedsoftware.core.domain.tools.ResourceManager
import com.sedsoftware.core.tools.impl.R
import java.lang.ref.WeakReference
import javax.inject.Inject

class DefaultErrorHandler @Inject constructor(
    private val resourceManager: ResourceManager
) : ErrorHandler {

    private lateinit var view: WeakReference<CanShowError>

    override fun consume(error: Throwable) {
        val message = when (error) {
            is NetworkConnectionMissing -> string(R.string.msg_no_internet_connection)
            is CurrencyMapLoadingError -> string(R.string.msg_currency_loading_error)
            is CurrencyPairsLoadingError -> string(R.string.msg_currency_pairs_loading_error)
            is MarketPairsLoadingError -> string(R.string.msg_market_pairs_loading_error)
            is CurrencyTickLoadingError -> String.format(string(R.string.msg_pair_tick_error), error.symbol)
            else -> String.format(string(R.string.msg_unknown_error), error.message)
        }

        view.get()?.showMessage(message) ?: error("View is not attached to ErrorHandler")
    }

    override fun attachView(view: CanShowError) {
        this.view = WeakReference(view)
    }

    override fun detachView() {
        view.clear()
    }

    private fun string(stringResId: Int): String = resourceManager.getString(stringResId)
}
