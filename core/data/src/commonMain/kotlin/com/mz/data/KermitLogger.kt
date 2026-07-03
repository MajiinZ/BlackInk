package com.mz.data

import co.touchlab.kermit.Logger
import com.mz.domain.BlackInkLogger


object KermitLogger: BlackInkLogger {

    override fun debug(message: String) {
        Logger.d(message)
    }

    override fun info(message: String) {
       Logger.i(message)
    }

    override fun warn(message: String) {
        Logger.w(message)
    }

    override fun error(message: String, throwable: Throwable?) {
        Logger.e(message)
    }

}