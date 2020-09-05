package com.aneonex.bitcoinchecker.datamodule.model.market

import com.aneonex.bitcoinchecker.datamodule.model.CheckerInfo
import com.aneonex.bitcoinchecker.datamodule.model.Market
import com.aneonex.bitcoinchecker.datamodule.model.Ticker
import com.aneonex.bitcoinchecker.datamodule.model.currency.Currency
import com.aneonex.bitcoinchecker.datamodule.model.currency.CurrencyPairsMap
import com.aneonex.bitcoinchecker.datamodule.model.currency.VirtualCurrency
import org.json.JSONObject

/**
 * Created by naveed on 17/01/2017.
 */
class Urdubit : Market(NAME, TTS_NAME, CURRENCY_PAIRS) {
    companion object {
        private const val NAME = "Urdubit"
        private const val TTS_NAME = NAME
        private const val URL = "https://api.blinktrade.com/api/v1/%2\$s/ticker?crypto_currency=%1\$s"
        private val CURRENCY_PAIRS: CurrencyPairsMap = CurrencyPairsMap()

        init {
            CURRENCY_PAIRS[VirtualCurrency.BTC] = arrayOf(
                    Currency.PKR
            )
        }
    }

    override fun getUrl(requestId: Int, checkerInfo: CheckerInfo): String {
        return String.format(URL, checkerInfo.currencyBase, checkerInfo.currencyCounter)
    }

    @Throws(Exception::class)
    override fun parseTickerFromJsonObject(requestId: Int, jsonObject: JSONObject, ticker: Ticker, checkerInfo: CheckerInfo) {
        ticker.bid = jsonObject.getDouble("buy")
        ticker.ask = jsonObject.getDouble("sell")
        ticker.vol = jsonObject.getDouble("vol")
        ticker.high = jsonObject.getDouble("high")
        ticker.low = jsonObject.getDouble("low")
        ticker.last = jsonObject.getDouble("last")
        //ticker.timestamp = jsonObject.getLong("timestamp");
    }
}