package com.sedsoftware.xtrader.commons.provider

import ru.terrakok.cicerone.Router

interface RouterProvider {

  fun getCurrentRouter(): Router
}
